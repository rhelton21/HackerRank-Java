package hackerRank;


//https://www.hackerrank.com/challenges/xor-and-sum

import java.io.*;
import java.util.*;

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
 */public class XorAndSum {

    final private static int MAX_I = 314159;
    final private static int MOD = 1000000007;

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    public static void main(String[] args) throws IOException {

        //INPUT
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        byte[] A = toByteArray(br.readLine().toCharArray());
        byte[] B = toByteArray(br.readLine().toCharArray());
        br.close();
        br = null;

        reverse(A);
        reverse(B);

        //SOLVE
        long sum = 0L;
        A = extend(A, B.length);
        B = extend(B, A.length);
        final int len = A.length;

        int i;
        long pow = 1L;
        int oneCount = 0;

        for (i = 0; i < len; ++i) {
            oneCount += B[i];
            int multiplier = (A[i] == 0) ? oneCount : MAX_I - oneCount + 1;
            sum = (sum + pow * multiplier) % MOD;
            pow = (pow << 1L) % MOD;
        }

        while (i < MAX_I) {
            ++i;
            sum = (sum + pow * oneCount) % MOD;
            pow = (pow << 1L) % MOD;
        }

        for (i = 0; i < len; ++i) {
            sum = (sum + pow * oneCount) % MOD;
            pow = (pow << 1L) % MOD;
            oneCount -= B[i];
        }

        //OUTPUT
        System.out.print(sum);
    }

    private static byte[] toByteArray(final char[] arr) {
        final int n = arr.length;
        final byte[] ret = new byte[n];
        for (int i = 0; i < n; ++i) {
            ret[i] = (byte) (arr[i] - '0');
        }
        return ret;
    }

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    private static void reverse(final byte[] arr) {
        for (int i = 0, j = arr.length - 1; i < j; ++i, --j) {
            final byte c = arr[i];
            arr[i] = arr[j];
            arr[j] = c;
        }
    }

    private static byte[] extend(final byte[] arr, final int n) {
        return (arr.length >= n) ? arr : Arrays.copyOf(arr, n);
    }
}