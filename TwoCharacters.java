package hackerRank;
/*
https://www.hackerrank.com/challenges/two-characters/problem?h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen

Given a string, remove characters until the string is made up of any two alternating characters. When you choose a character to remove, all instances of that character must be removed. Determine the longest string possible that contains just two alternating letters.

Example


Delete a, to leave bcdbd. Now, remove the character c to leave the valid string bdbd with a length of 4. Removing either b or d at any point would not result in a valid string. Return .

Given a string , convert it to the longest possible string  made up only of alternating characters. Return the length of string . If no string  can be formed, return .

Function Description

Complete the alternate function in the editor below.

alternate has the following parameter(s):

string s: a string
Returns.

int: the length of the longest valid string, or  if there are none
Input Format

The first line contains a single integer that denotes the length of .
The second line contains string .

Constraints

Sample Input

STDIN       Function
-----       --------
10          length of s = 10
beabeefeab  s = 'beabeefeab'
Sample Output

5
Explanation

The characters present in  are a, b, e, and f. This means that  must consist of two of those characters and we must delete two others. Our choices for characters to leave are [a,b], [a,e], [a, f], [b, e], [b, f] and [e, f].

If we delete e and f, the resulting string is babab. This is a valid  as there are only two distinct characters (a and b), and they are alternating within the string.

If we delete a and f, the resulting string is bebeeeb. This is not a valid string  because there are consecutive e's present. Removing them would leave consecutive b's, so this fails to produce a valid string .

Other cases are solved similarly.

babab is the longest string we can create.


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
class TwoCharacters {

    /*
     * Complete the 'alternate' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    public static int alternate(String s) {
        //Loop through all letter pairs
        int maxPattern = 0;
        for(int i = 0; i < 26; i++)
        {
            nextLetter:
            for(int j = i + 1; j < 26; j++)
            {
                char one = (char) ('a' + i); //First char in pair
                char two = (char) ('a' + j); //Second char in pair
                char lastSeen = '\u0000';
                int patternLength = 0;

                for(char letter : s.toCharArray())
                {
                    if(letter == one || letter == two)
                    {
                        if(letter == lastSeen)//Duplicate found
                        {
                            continue nextLetter;
                        }
                        //Not a duplicate
                        patternLength++;
                        lastSeen = letter;
                    }
                }//for char : s

                maxPattern = (patternLength > maxPattern) ? patternLength : maxPattern; //Keep a running max

            }//for j
        }//for i
        return maxPattern;
    }


/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int l = Integer.parseInt(bufferedReader.readLine().trim());

        String s = bufferedReader.readLine();

        int result = TwoCharacters.alternate(s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
