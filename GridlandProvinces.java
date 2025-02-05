package hackerRank;


import java.io.*;
import java.util.HashSet;
        import java.util.Scanner;
        import java.util.Set;
import java.util.stream.IntStream;

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
 */public class GridlandProvinces {
    private final static Scanner scanner =
            new Scanner(System.in);
    private final static long mod1 = 2147483607,
            f1 = 107, f2 = 101;
    private final static long[] arr1 = new long[10000],
            arr2 = new long[10000];
    private final static Set<Long> result = new HashSet<>();



/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
 //       BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int p = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, p).forEach(pItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                String s1 = bufferedReader.readLine();

                String s2 = bufferedReader.readLine();

                int result = GridlandProvinces.gridlandProvinces(n, s1, s2);

                System.out.println(String.valueOf(result));
 //               bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
//        bufferedWriter.close();

/*

        for (int i = 0; i < arr1.length; ++i) {
            arr1[i] = i > 0 ? arr1[i - 1] * f1 % mod1 : 1;
            arr2[i] = i > 0 ? arr2[i - 1] * f2 % mod1 : 1;
        }

        for (int t = scanner.nextInt(); t > 0; --t) {
            result.clear();
            scanner.nextInt();
            char[] c1 = scanner.next().toCharArray();
            char[] c2 = scanner.next().toCharArray();

            for (int i = 0; i < c1.length; ++i) {
                process(c1, c2, i, false);
                process(c2, c1, i, false);
                process(c1, c2, i, true);
                process(c2, c1, i, true);
            }
            reverse(c1);
            reverse(c2);
            for (int i = 0; i < c1.length; ++i) {
                process(c1, c2, i, false);
                process(c2, c1, i, false);
                process(c1, c2, i, true);
                process(c2, c1, i, true);
            }
            System.out.println(result.size());
        }

 */
    }

    private static int gridlandProvinces(int n, String s1, String s2) {

        char[] c1 = s1.toCharArray();
        System.out.println("s1 -->" + s1);
        char[] c2 = s2.toCharArray();
        System.out.println("s2 -->" + s2);
        for (int i = 0; i < arr1.length; ++i) {
            arr1[i] = i > 0 ? arr1[i - 1] * f1 % mod1 : 1;
            arr2[i] = i > 0 ? arr2[i - 1] * f2 % mod1 : 1;
        }

        for (int i = 0; i < c1.length; ++i) {
            process(c1, c2, i, false);
            process(c2, c1, i, false);
            process(c1, c2, i, true);
            process(c2, c1, i, true);
        }
        reverse(c1);
        reverse(c2);
        for (int i = 0; i < c1.length; ++i) {
            process(c1, c2, i, false);
            process(c2, c1, i, false);
            process(c1, c2, i, true);
            process(c2, c1, i, true);
        }
        return result.size();

    }

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    static void process(char[] s1, char[] s2, int k, boolean b) {
        long p1 = 0, p2 = 0, p3 = 0, p4 = 0;
        for (int i = 0; i < k; ++i) {
            p1 = (p1 + s1[i] * arr1[k - 1 - i]) % mod1;
            p1 = (p1 + s2[i] * arr1[k + i]) % mod1;
            p3 = (p3 + s1[i] * arr2[k - 1 - i]) % mod1;
            p3 = (p3 + s2[i] * arr2[k + i]) % mod1;
        }
        if (b) {
            p1 = (p1 + s2[k] * arr1[k * 2]) % mod1;
            p1 = (p1 + s1[k] * arr1[k * 2 + 1]) % mod1;
            p3 = (p3 + s2[k] * arr2[k * 2]) % mod1;
            p3 = (p3 + s1[k] * arr2[k * 2 + 1]) % mod1;
            char[] s = s1; s1 = s2; s2 = s;
            ++k;
        }
        for (int i = k; i < s1.length; ++i) {
            p2 = (p2 + s1[i] * arr1[s1.length * 2 + k - 1 - i]) % mod1;
            p2 = (p2 + s2[i] * arr1[i + k]) % mod1;
            p4 = (p4 + s1[i] * arr2[s1.length * 2 + k - 1 - i]) % mod1;
            p4 = (p4 + s2[i] * arr2[i + k]) % mod1;
        }
        result.add(((p1 + p2) % mod1) * mod1 + (p3 + p4) % mod1);

        for (int i = k; i < s1.length - 1; i += 2) {
            p1 = (p1 + s2[i] * arr1[i * 2]) % mod1;
            p1 = (p1 + s1[i] * arr1[i * 2 + 1]) % mod1;
            p1 = (p1 + s1[i + 1] * arr1[i * 2 + 2]) % mod1;
            p1 = (p1 + s2[i + 1] * arr1[i * 2 + 3]) % mod1;
            p2 = (p2 + s2[i] * (mod1 - arr1[i * 2])) % mod1;
            p2 = (p2 + s2[i+1] * (mod1 - arr1[i * 2 + 1])) % mod1;
            p2 = (p2 + s1[i] * (mod1 - arr1[s1.length * 2 - 1])) % mod1;
            p2 = (p2 + s1[i+1] * (mod1 - arr1[s1.length * 2 - 2])) % mod1;
            p2 = (p2 * f1 * f1) % mod1;

            p3 = (p3 + s2[i] * arr2[i * 2]) % mod1;
            p3 = (p3 + s1[i] * arr2[i * 2 + 1]) % mod1;
            p3 = (p3 + s1[i + 1] * arr2[i * 2 + 2]) % mod1;
            p3 = (p3 + s2[i + 1] * arr2[i * 2 + 3]) % mod1;
            p4 = (p4 + s2[i] * (mod1 - arr2[i * 2])) % mod1;
            p4 = (p4 + s2[i+1] * (mod1 - arr2[i * 2 + 1])) % mod1;
            p4 = (p4 + s1[i] * (mod1 - arr2[s1.length * 2 - 1])) % mod1;
            p4 = (p4 + s1[i+1] * (mod1 - arr2[s1.length * 2 - 2])) % mod1;
            p4 = (p4 * f2 * f2) % mod1;

            result.add(((p1 + p2) % mod1) * mod1 + (p3 + p4) % mod1);
        }
    }

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    private static void reverse(char[] str) {
        for (int i = str.length / 2 - 1; i >= 0; --i) {
            char t = str[i];
            str[i] = str[str.length - 1 - i];
            str[str.length - 1 - i] = t;
        }
    }

}