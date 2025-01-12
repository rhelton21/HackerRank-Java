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

class BearandSteadyGene {

    /*
     * Complete the 'steadyGene' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING gene as parameter.
     */

    public static int steadyGene(String gene) {
        // Count the occurrence of each character in the gene string
        Map<Character, Integer> count = new HashMap<>();
        for (char ch : gene.toCharArray()) {
            count.put(ch, count.getOrDefault(ch, 0) + 1);
        }

        // Calculate the minimum number of characters to replace to make the gene steady
        int n = gene.length();
        int left = 0, right = 0, minReplacements = n;
        while (right < n) {
            char ch = gene.charAt(right);
            count.put(ch, count.get(ch) - 1);
            while (isSteady(count, n) && (left < n)) {
                minReplacements = Math.min(minReplacements, right - left + 1);
                try {
//                    if(left < n) {
                        char leftCh = gene.charAt(left);
                        count.put(leftCh, count.get(leftCh) + 1);
//                    }
                    left++;
                }catch(Exception ex){
                    System.out.println(ex);
                }
            }
            right++;
        }
        return minReplacements;
    }

    private static boolean isSteady(Map<Character, Integer> count, int n) {
        // Check if the count of each character is less than or equal to n/4
        for (int c : count.values()) {
            if (c > n / 4) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String gene = bufferedReader.readLine();

        int result = BearandSteadyGene.steadyGene(gene);
        System.out.println(result);

//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();

        bufferedReader.close();
//        bufferedWriter.close();
    }
}
