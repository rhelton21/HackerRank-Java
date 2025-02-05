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
 * HackerRank Challenge: ACM ICPC Team
 *
 * Problem Overview:
 * - Given a list of binary strings, each representing the topics a person knows (1 = knows, 0 = does not know).
 * - Find:
 *   1. The maximum number of topics any two-person team can cover.
 *   2. The number of teams that can cover this maximum number of topics.
 *
 * Example Input:
 * 4 5
 * 10101
 * 11100
 * 11010
 * 00101
 *
 * Example Output:
 * 5
 * 2
 *
 * Explanation:
 * - Max Topics Covered by a Team = 5
 * - Number of Teams Covering 5 Topics = 2
 *
 * Complexity Analysis:
 * - Nested loops (O(n² * m)) → Compares every team.
 * - Efficient for small n, but slower for large inputs.
 *
 * Optimized Approach:
 * - Instead of manually checking each character, use bitwise operations:
 *   int tmp = Integer.bitCount(Integer.parseInt(a, 2) | Integer.parseInt(b, 2));
 * - This approach is more efficient.
 */

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
 */public class AcmTeam {
    /*
     * Complete the 'acmTeam' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts STRING_ARRAY topic as parameter.
     */
/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    public static List<Integer> acmTeam(List<String> topic) {
        int maxTopics = 0;
        int maxTeams = 0;
        for (int i = 0; i < topic.size(); i++) {
            for (int j = i + 1; j < topic.size(); j++) {
                int tmp = or(topic.get(i), topic.get(j));
                if (tmp > maxTopics) {
                    maxTopics = tmp;
                    maxTeams = 1;
                } else if (tmp == maxTopics) {
                    maxTeams++;
                }
            }
        }
        int[] ints = {maxTopics, maxTeams};
        return Arrays.stream(ints).boxed().collect(Collectors.toList());
    }

    static int or(String a, String b) {
        int counter = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == '1' || b.charAt(i) == '1') {
                counter++;
            }
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
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
        int n = Integer.parseInt(firstMultipleInput[0]);
        int m = Integer.parseInt(firstMultipleInput[1]);

        List<String> topic = IntStream.range(0, n).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .collect(toList());

        List<Integer> result = AcmTeam.acmTeam(topic);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
