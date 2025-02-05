package hackerRank;
/*
Gemstones

105/563 challenges solved
Rank: 28481|Points: 2462.88
Problem Solving
Problem
Submissions
Leaderboard
Discussions
Editorial
Topics
There is a collection of rocks where each rock has various minerals embeded in it. Each type of mineral is designated by a lowercase letter in the range . There may be multiple occurrences of a mineral in a rock. A mineral is called a gemstone if it occurs at least once in each of the rocks in the collection.

Given a list of minerals embedded in each of the rocks, display the number of types of gemstones in the collection.

Example

The minerals  and  appear in each rock, so there are  gemstones.

Function Description

Complete the gemstones function in the editor below.

gemstones has the following parameter(s):

string arr[n]: an array of strings
Returns

int: the number of gemstones found
Input Format

The first line consists of an integer , the size of .
Each of the next  lines contains a string  where each letter represents an occurence of a mineral in the current rock.

Constraints


 | arr[i] |
Each composition  consists of only lower-case Latin letters ('a'-'z').

Sample Input

STDIN       Function
-----       --------
3           arr[] size n = 3
abcdde      arr = ['abcdde', 'baccd', 'eeabg']
baccd
eeabg
 */
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
class Gemstones {

    /*
     * Complete the 'gemstones' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING_ARRAY arr as parameter.
     */

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    public static int gemstones(List<String> arr) {
        int[] count = new int[26];
        Arrays.fill(count, 0);

        for (String rock : arr) {
            int[] rockCount = new int[26];
            for (char c : rock.toCharArray()) {
                rockCount[c - 'a'] = 1;
            }

            for (int i = 0; i < 26; i++) {
                if (rockCount[i] == 1) {
                    count[i]++;
                }
            }
        }

        int gemstones = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] == arr.size()) {
                gemstones++;
            }
        }

        return gemstones;


    }

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> arr = IntStream.range(0, n).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .collect(toList());

        int result = Gemstones.gemstones(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}


