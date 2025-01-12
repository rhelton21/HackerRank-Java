package hackerRank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class CutTheSticks {

    /*
     * Complete the 'cutTheSticks' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static List<Integer> cutTheSticks(List<Integer> arr) {


        int[] array = arr.stream().mapToInt(i -> i).toArray();
        // Runs in O(n) time
        Arrays.sort(array);
        List<Integer> list = new ArrayList<>();
        int i = 0;
        int lowIdx = 0;
        int minStickLength = array[0];
        while (i < array.length) {
            if (array[i] > minStickLength) {
                list.add(array.length - (lowIdx));
                lowIdx = i;
                minStickLength += array[i] - minStickLength;
            }
            i++;
            // Add last
            if (i == array.length) {
                list.add(array.length - (lowIdx));
            }
        }
        // Convert to int array
//        int[] result = new int[list.size()];
//        for (int j = 0; j < list.size(); j++) result[j] = list.get(j);
        return list;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String[] arrTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        List<Integer> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrTemp[i]);
            arr.add(arrItem);
        }

        List<Integer> result = CutTheSticks.cutTheSticks(arr);

        for (int i = 0; i < result.size(); i++) {
            bufferedWriter.write(String.valueOf(result.get(i)));

            if (i != result.size() - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
