package hackerRank;


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
 */public class MinimumSwaps {
    // Complete the minimumSwaps function below.
    static int minimumSwaps(int[] arr) {
        int i = 0;
        int count = 0;
        int temp;
        int n = arr.length;
        while (i < n) {
            if (arr[i] != i + 1) {
                temp = arr[i];
                arr[i] = arr[temp - 1];
                arr[temp - 1] = temp;
                count++;
            } else {
                i++;
            }
        }
        return count;
    }

    private static final Scanner scanner = new
            Scanner(System.in);

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(System.getenv("OUTPUT_PATH")));
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        int[] arr = new int[n];
        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }
        int res = minimumSwaps(arr);
        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();
        bufferedWriter.close();
        scanner.close();
    }
}