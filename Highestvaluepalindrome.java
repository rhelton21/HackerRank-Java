package hackerRank;

import java.util.Scanner;

public class Highestvaluepalindrome {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int k = scanner.nextInt();
        char[] c = scanner.next().toCharArray();
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
            return;
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
        System.out.println(new String(c));
    }
}