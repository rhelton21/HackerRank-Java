package hackerRank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * This Java file is part of a HackerRank solution.
 * It solves a specific algorithmic challenge.
 *
 * Author: [Your Name]
 * Date: [Date]
 */
class CluesOnBinaryPath {

    /*
     * Complete the 'cluesOnBinaryPath' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER d
     *  3. 2D_INTEGER_ARRAY roads
     */

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    public static int cluesOnBinaryPath(int n, int m, int d, List<List<Integer>> roads) {
        System.out.println("d---->" + d);
        System.out.println("n---->" + n);
        System.out.println("m---->" + m);
        int[] x = new int[m];
        int[] y = new int[m];
        int[] w = new int[m];

        int l = (d + 1) / 2;
        int[][] dp = new int[n][1 << l];
        int[][] ndp = new int[n][1 << l];
        System.out.println("l---->" + l);
        int k = 0;
        for (List<Integer> road : roads) {
            x[k] = road.get(0);
            x[k]--;

            y[k] = road.get(1);
            y[k]--;

            w[k] = road.get(2);
            k++;
        }
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < (1 << l); ++j)
                dp[i][j] = ndp[i][j] = 0;
        dp[0][0] = 1;
        for (int i = 0; i < n; ++i)
            dp[i][0] |= 2;
        for (int c = 0; c < l; ++c) {
            for (int i = 0; i < m; ++i) {
                int u = x[i];
                int v = y[i];
                int b = w[i];
                for (int t = 0; t < (1 << c); ++t) {
                    ndp[u][(t << 1) | b] |= dp[v][t];
                    ndp[v][(t << 1) | b] |= dp[u][t];
                }
            }
            if (c + 1 < l) {
                for (int i = 0; i < n; ++i) {
                    for (int j = 0; j < (1 << (c + 1)); ++j) {
                        dp[i][j] = ndp[i][j];
                        ndp[i][j] = 0;
                    }
                }
            }
        }
        Boolean[] can = new Boolean[1 << d];
        for (int i = 0; i < (1 << d); ++i)
            can[i] = false;
        int r = d - l;
        for (int v = 0; v < n; ++v) {
            for (int t = 0; t < (1 << r); ++t) {
                int c = (l == r ? ndp[v][t] : dp[v][t]);
                if ((c & 1) == 0)
                    continue;
                for (int s = 0; s < (1 << l); ++s) {
                    if ((ndp[v][s] & 2) > 0)
                        can[(t << l) ^ s] = true;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < (1 << d); ++i)
            if (can[i])
                res++;

        return res;

    }


/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        int d = Integer.parseInt(firstMultipleInput[2]);

        List<List<Integer>> roads = new ArrayList<>();

        IntStream.range(0, m).forEach(i -> {
            try {
                roads.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = CluesOnBinaryPath.cluesOnBinaryPath(n, m, d, roads);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
