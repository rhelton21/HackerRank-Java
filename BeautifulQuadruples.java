package hackerRank;

import java.util.Arrays;
import java.util.Scanner;

/**
 * This Java file is part of a HackerRank solution.
 * It solves a specific algorithmic challenge.
 *
 * Author: [Your Name]
 * Date: [Date]
 */
/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */public class BeautifulQuadruples {
/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int A = in.nextInt();
        int B = in.nextInt();
        int C = in.nextInt();
        int D = in.nextInt();

        int[] m = new int[] {A, B, C, D};
        Arrays.sort(m);

        long[][] count = new long[3001][4096];

        for (int i=1; i<=m[0]; i++) {
            for (int j=i; j<=m[1]; j++) {
                count[j][i ^ j] ++;
            }
        }
        for (int i=0; i<4096; i++) {
            for (int j=1; j<=3000; j++) {
                count[j][i] += count[j-1][i];
            }
        }


        long[] sum = new long[3001];
        for (int j=1; j<=3000; j++) {
            for (int i=0; i<4096; i++) {
                sum[j] += count[j][i];
            }
        }

        long res=0;
        for (int i=1; i<=m[2]; i++) {
            for (int j=i; j<=m[3]; j++) {
                int x = i ^ j;
                res += sum[i] - count[i][i^j];
            }
        }
        System.out.println(res);
    }
}