package hackerRank;
/*
https://www.hackerrank.com/challenges/game-of-thrones/problem?utm_campaign=challenge-recommendation&utm_medium=email&utm_source=24-hour-campaign

Dothraki are planning an attack to usurp King Robert's throne. King Robert learns of this conspiracy from Raven and plans to lock the single door through which the enemy can enter his kingdom.

door

But, to lock the door he needs a key that is an anagram of a palindrome. He starts to go through his box of strings, checking to see if they can be rearranged into a palindrome. Given a string, determine if it can be rearranged into a palindrome. Return the string YES or NO.

Example

One way this can be arranged into a palindrome is . Return YES.

Function Description
Complete the gameOfThrones function below.

gameOfThrones has the following parameter(s):

string s: a string to analyze
Returns

string: either YES or NO
Input Format

A single line which contains .

Constraints

 |s|
 contains only lowercase letters in the range
Sample Input 0

aaabbbb
Sample Output 0

YES
Explanation 0

A palindromic permutation of the given string is bbaaabb.

Sample Input 1

cdefghmnopqrstuvw
Sample Output 1

NO
Explanation 1

Palindromes longer than 1 character are made up of pairs of characters. There are none here.

Sample Input 2

cdcdcdcdeeeef
Sample Output 2

YES
Explanation 2

An example palindrome from the string: ddcceefeeccdd.

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

class GameofThrones {

    /*
     * Complete the 'gameOfThrones' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String gameOfThrones(String s) {
        int[] charCount = new int[26];
        for (char c : s.toCharArray()) {
            charCount[c - 'a']++;
        }
        int oddCount = 0;
        for (int count : charCount) {
            if (count % 2 == 1) {
                oddCount++;
            }
        }
        return (oddCount <= 1) ? "YES" : "NO";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        String result = GameofThrones.gameOfThrones(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

