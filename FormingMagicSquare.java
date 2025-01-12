package hackerRank;

//https://www.hackerrank.com/challenges/magic-square-forming/problem

import java.io.*;
        import java.math.*;
        import java.security.*;
        import java.text.*;
        import java.util.*;
        import java.util.concurrent.*;
        import java.util.regex.*;

public class FormingMagicSquare {

    // Complete the formingMagicSquare function below.
    public static int formingMagicSquare(List<List<Integer>> list) {

        int[][] arr = list.stream()
                .map(l -> l.stream().mapToInt(Integer::intValue).toArray())
                .toArray(int[][]::new);
        ArrayList<int[][]> options = new ArrayList<>();
        int[][] tmp = new int[][]{{8, 1, 6}, {3, 5, 7}, {4, 9, 2}};
        options.add(tmp);
        tmp = new int[][]{{6, 1, 8}, {7, 5, 3}, {2, 9, 4}};
        options.add(tmp);
        tmp = new int[][]{{8, 1, 6}, {3, 5, 7}, {4, 9, 2}};
        options.add(tmp);
        tmp = new int[][]{{4, 9, 2}, {3, 5, 7}, {8, 1, 6}};
        options.add(tmp);
        tmp = new int[][]{{8, 3, 4}, {1, 5, 9}, {6, 7, 2}};
        options.add(tmp);
        tmp = new int[][]{{4, 3, 8}, {9, 5, 1}, {2, 7, 6}};
        options.add(tmp);
        tmp = new int[][]{{6, 7, 2}, {1, 5, 9}, {8, 3, 4}};
        options.add(tmp);
        tmp = new int[][]{{2, 7, 6}, {9, 5, 1}, {4, 3, 8}};
        options.add(tmp);

        int min = 100;
        int sum = 0;
        int temp =0;
        for (int[][] option : options) {

            for (int i = 0; i <3; i++) {
                for (int j = 0; j < 3; j++) {
                    sum = sum + Math.abs(option[i][j] - arr[i][j]);
                }
            }
            sum = Math.abs(sum);
            if(sum<min){
                min = sum;
            }
            sum = 0;
        }
        return min;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int[][] s = new int[3][3];

        for (int i = 0; i < 3; i++) {
            String[] sRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int sItem = Integer.parseInt(sRowItems[j]);
                s[i][j] = sItem;
            }
        }

 //       int result = formingMagicSquare(s);

  //      bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}