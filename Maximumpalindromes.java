package hackerRank;

import java.util.Scanner;

public class Maximumpalindromes {


    static int[][] count;
    static long[] f;
    static long mod = 1000000007;

    static void initialize(String s) {
        count = new int[s.length()][26];

        for (int i = 0; i < s.length(); i++) {
            if (i > 0) {
                for (int j = 0; j < 26; j++) count[i][j] = count[i - 1][j];
            }

            count[i][s.charAt(i) - 'a']++;
        }

        f = new long[100000];
        f[0] = 1;

        for (int i = 1; i < 100000; i++) f[i] = f[i - 1] * i % mod;
    }

    static int c[] = new int[26];

    static int answerQuery(int l, int r) {
        // Return the answer for this query modulo 1000000007.

        l--;
        r--;

        for (int i = 0; i < 26; i++) {
            c[i] = count[r][i];

            if (l > 0) {
                c[i] -= count[l - 1][i];
            }
        }

        int len = 0;
        int oddCount = 0;

        for (int i = 0; i < 26; i++) {
            len += c[i] / 2;
            if (c[i] % 2 == 1) oddCount++;
        }

        long ans = f[len];
        long div = 1;

        for (int i = 0; i < 26; i++) {
            if (c[i] >= 4) {
                div *= f[c[i] / 2];
                div %= mod;
            }
        }

        if (div != 1) ans = solveCongruence(div, ans, mod);

        if (oddCount > 0) {
            ans *= oddCount;
            ans %= mod;
        }

        return (int) ans;
    }

    static long solveCongruence(long a, long b, long mod) {
        if (a == 1) {
            return b;
        }

        long b1 = -b % a;

        if (b1 != 0) {
            b1 += a;
        }

        long y = solveCongruence(mod % a, b1, a);
        return (mod * y + b) / a % mod;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        initialize(s);
        int q = in.nextInt();
        for (int a0 = 0; a0 < q; a0++) {
            int l = in.nextInt();
            int r = in.nextInt();
            int result = answerQuery(l, r);
            System.out.println(result);
        }
        in.close();
    }
}