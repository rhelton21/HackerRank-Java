package hackerRank;
/*
https://www.hackerrank.com/challenges/the-love-letter-mystery/problem?isFullScreen=false&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen

The Love-Letter Mystery

108/563 challenges solved
Rank: 26677|Points: 2522.88
Problem Solving
Problem
Submissions
Leaderboard
Discussions
Editorial
Topics
James found a love letter that his friend Harry has written to his girlfriend. James is a prankster, so he decides to meddle with the letter. He changes all the words in the letter into palindromes.

To do this, he follows two rules:

He can only reduce the value of a letter by , i.e. he can change d to c, but he cannot change c to d or d to b.
The letter  may not be reduced any further.
Each reduction in the value of any letter is counted as a single operation. Find the minimum number of operations required to convert a given string into a palindrome.

Example

The following two operations are performed: cde → cdd → cdc. Return .

Function Description

Complete the theLoveLetterMystery function in the editor below.

theLoveLetterMystery has the following parameter(s):

string s: the text of the letter
Returns

int: the minimum number of operations
Input Format

The first line contains an integer , the number of queries.
The next  lines will each contain a string .

Constraints


 | s |
All strings are composed of lower case English letters, ascii[a-z], with no spaces.

Sample Input

STDIN   Function
-----   --------
4       q = 4
abc     query 1 = 'abc'
abcba
abcd
cba
Sample Output

2
0
4
2
Explanation

For the first query, abc → abb → aba.
For the second query, abcba is already a palindromic string.
For the third query, abcd → abcc → abcb → abca → abba.
For the fourth query, cba → bba → aba.
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
class LoveLetterMystery {

    /*
     * Complete the 'theLoveLetterMystery' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    public static int theLoveLetterMystery(String s) {
        int operations = 0;
        int length = s.length();
        for (int i = 0; i < length / 2; i++) {
            operations += Math.abs(s.charAt(i) - s.charAt(length - i - 1));
        }
        return operations;
    }
/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String s = bufferedReader.readLine();

                int result = LoveLetterMystery.theLoveLetterMystery(s);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}