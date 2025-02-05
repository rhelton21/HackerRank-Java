package hackerRank;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * This Java file is part of a HackerRank solution.
 * It solves a specific algorithmic challenge.
 *
 * Author: [Your Name]
 * Date: [Date]
 */
class HourglassSum2 {

    /*
     * Complete the 'hourglassSum' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY arr as parameter.
     */

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    public static int hourglassSum(List<List<Integer>> arr) {
        int[][] array = arr.stream()
                .map(l -> l.stream().mapToInt(Integer::intValue).toArray())
                .toArray(int[][]::new);
        int sum=-1000;
        for(int i =0 ; i<4;i++){
            for(int x =0 ; x<4; x++){

                int top = array[i][x]+array[i][x+1]+array[i][x+2];
                int middle = array[i+1][x+1];
                int bottom = array[i+2][x]+array[i+2][x+1]+array[i+2][x+2];
                if(top+middle+bottom>sum){sum=top+middle+bottom;}
            }
        }
        return sum;
    }


/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
 //       BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        List<List<Integer>> arr = new ArrayList<>();

        IntStream.range(0, 6).forEach(i -> {
            try {
                arr.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = hourglassSum(arr);
        System.out.println(String.valueOf(result));

 //       bufferedWriter.write(String.valueOf(result));
 //       bufferedWriter.newLine();

        bufferedReader.close();
//        bufferedWriter.close();
    }
}
