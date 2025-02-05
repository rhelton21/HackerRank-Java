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
/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */public class QueensAttack {

    /*
     * Complete the 'queensAttack' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER k
     *  3. INTEGER r_q
     *  4. INTEGER c_q
     *  5. 2D_INTEGER_ARRAY obstacles
     */

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    public static int queensAttack(int n, int k, int r, int c, List<List<Integer>> obstacles) {
        int[][] arr = obstacles.stream()
                .map(l -> l.stream().mapToInt(Integer::intValue).toArray())
                .toArray(int[][]::new);      HashMap<Integer, HashSet<Integer>> cache = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (cache.containsKey(arr[i][0])) {
                cache.get(arr[i][0]).add(arr[i][1]);
            } else {
                cache.put(arr[i][0], new HashSet<Integer>());
                cache.get(arr[i][0]).add(arr[i][1]);
            }
        }
        int counter = 0;
        // right
        for (int i = c + 1; i <= n; i++) {
            if (cache.containsKey(r) && cache.get(r).contains(i)) {
                break;
            }
            counter++;
        }

        // left
        for (int i = c - 1; i >= 1; i--) {
            if (cache.containsKey(r) && cache.get(r).contains(i)) {
                break;
            }
            counter++;
        }

        // down
        for (int i = r + 1; i <= n; i++) {
            if (cache.containsKey(i) && cache.get(i).contains(c)) {
                break;
            }
            counter++;
        }

        // up
        for (int i = r - 1; i >= 1; i--) {
            if (cache.containsKey(i) && cache.get(i).contains(c)) {
                break;
            }
            counter++;
        }

        // up-left
        for (int i = r - 1, j = c - 1; i >= 1 && j >= 1; i--, j--) {
            if (cache.containsKey(i) && cache.get(i).contains(j)) {
                break;
            }
            counter++;
        }

        // up-right
        for (int i = r - 1, j = c + 1; i >= 1 && j <= n; i--, j++) {
            if (cache.containsKey(i) && cache.get(i).contains(j)) {
                break;
            }
            counter++;
        }

        // down-right
        for (int i = r + 1, j = c + 1; i <= n && j <= n; i++, j++) {
            if (cache.containsKey(i) && cache.get(i).contains(j)) {
                break;
            }
            counter++;
        }

        // down-left
        for (int i = r + 1, j = c - 1; i <= n && j >= 1; i++, j--) {
            if (cache.containsKey(i) && cache.get(i).contains(j)) {
                break;
            }
            counter++;
        }

        return counter;

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

        String[] secondMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int r_q = Integer.parseInt(secondMultipleInput[0]);

        int c_q = Integer.parseInt(secondMultipleInput[1]);

        List<List<Integer>> obstacles = new ArrayList<>();

        IntStream.range(0, k).forEach(i -> {
            try {
                obstacles.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = QueensAttack.queensAttack(n, k, r_q, c_q, obstacles);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
