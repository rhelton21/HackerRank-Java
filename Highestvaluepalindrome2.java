package hackerRank;

import java.util.Scanner;

public class Highestvaluepalindrome2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int k = scanner.nextInt();
        char[] c = scanner.next().toCharArray();

        String s = new String(c);
        String str = highestValuePalindrome(s,n,k);
        System.out.println(str);

    }

    public static String highestValuePalindrome(String s, int n, int k) {
        char[] c = s.toCharArray();
        boolean[] ch = new boolean[n];
        for (int i = 0; i < n / 2; ++i) {
            if (c[i] != c[n - i - 1]) {
                c[i] = c[n - i - 1] = (char) Math.max(c[i], c[n - i - 1]);
                ch[i] = true;
                --k;
            }
        }
        if (k < 0) {
            System.out.println(-1);
            return "-1";
        }
        for (int i = 0; i < n / 2; ++i) {
            if (c[i] != '9') {
                if (ch[i] && k > 0) {
                    c[i] = c[n - i - 1] = '9';
                    --k;
                }
                if (!ch[i] && k > 1) {
                    c[i] = c[n - i - 1] = '9';
                    k -= 2;
                }
            }
        }
        if (n % 2 == 1 && k > 0) {
            c[n / 2] = '9';
        }
        return new String(c);

    }
}