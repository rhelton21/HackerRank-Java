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
class CoveringStains {

    private static final int MOD = (int) 1e9 + 7;
    /*
     * Complete the 'coveringStains' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER k
     *  2. 2D_INTEGER_ARRAY coordinates
     */

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    public static int coveringStains(int n, int k, List<List<Integer>> coordinates) {
        k = n - k;

        int[][] stains = new int[n + 1][2];
        int[] vals = new int[] { 0, 100000, 0, 100000 };
        for (int i = 1; i <= n; i++) {
            stains[i][0] = coordinates.get(i - 1).get(0);
            stains[i][1] = coordinates.get(i - 1).get(1);
            vals[0] = Math.max(vals[0], stains[i][0]);
            vals[1] = Math.min(vals[1], stains[i][0]);
            vals[2] = Math.max(vals[2], stains[i][1]);
            vals[3] = Math.min(vals[3], stains[i][1]);
        }

        if (k == 0) {
            return 1;
        }

        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int mask = 0;
            for (int j = 0; j < 4; j++) {
                if (vals[j] == stains[i][j / 2]) {
                    mask |= (1 << j);
                }
            }
            arr[i] = mask;
        }

        int[][][] dp = new int[k + 1][n + 1][16];

        for (int j = 1; j <= n; j++) {
            dp[1][j][arr[j]] = 1;
            for (int i = 0; i < 16; i++) {
                dp[1][j][i] = (dp[1][j][i] + dp[1][j - 1][i]) % MOD;
            }
        }

        for (int i = 1; i < k; i++) {
            for (int j = i; j < n; j++) {
                for (int m = 0; m < 16; m++) {
                    dp[i + 1][j + 1][m | arr[j + 1]] = (dp[i + 1][j + 1][m | arr[j + 1]] + dp[i][j][m]) % MOD;
                    dp[i + 1][j + 1][m] = (dp[i + 1][j + 1][m] + dp[i + 1][j][m]) % MOD;
                }
            }
        }

        int ans = 0;
        for (int m = 0; m < 15; m++) {
            System.out.println("ans-->" + ans);
            System.out.println("dp[k][n][m]-->" + dp[k][n][m]);
            ans = (ans + dp[k][n][m]) % MOD;

        }

        return ans;
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

        int k = Integer.parseInt(firstMultipleInput[1]);

        List<List<Integer>> coordinates = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                coordinates.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = CoveringStains.coveringStains(n, k, coordinates);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
