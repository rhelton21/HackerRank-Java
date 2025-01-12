package hackerRank;
/*
https://www.hackerrank.com/challenges/beautiful-binary-string/problem?isFullScreen=false&h_r=next-challenge&h_v=zen
Beautiful Binary String

107/563 challenges solved
Rank: 27309|Points: 2502.88
Problem Solving
Problem
Submissions
Leaderboard
Discussions
Editorial
Alice has a binary string. She thinks a binary string is beautiful if and only if it doesn't contain the substring .

In one step, Alice can change a  to a  or vice versa. Count and print the minimum number of steps needed to make Alice see the string as beautiful.

Example


She can change any one element and have a beautiful string.

Function Description

Complete the beautifulBinaryString function in the editor below.

beautifulBinaryString has the following parameter(s):

string b: a string of binary digits
Returns

int: the minimum moves required
Input Format

The first line contains an integer , the length of binary string.
The second line contains a single binary string .

Constraints

.
Output Format

Print the minimum number of steps needed to make the string beautiful.

Sample Input 0

STDIN       Function
-----       --------
7           length of string n = 7
0101010     b = '0101010'
Sample Output 0

2
Explanation 0:

In this sample,

The figure below shows a way to get rid of each instance of :

binary.png

Make the string beautiful by changing  characters ( and ).

Sample Input 1

5
01100
Sample Output 1

0
Sample Case 1:

In this sample

Explanation 1

The substring  does not occur in , so the string is already beautiful in  moves.

Sample Input 2

10
0100101010
Sample Output 2

3
Explanation 2

In this sample

One solution is to change the values of  to form a beautiful string.
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

class BeautifulBinaryString {

    /*
     * Complete the 'beautifulBinaryString' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING b as parameter.
     */

    public static int beautifulBinaryString(String b) {
        int count = 0;
        for (int i = 0; i < b.length() - 2; i++) {
            if (b.charAt(i) == '0' && b.charAt(i + 1) == '1' && b.charAt(i + 2) == '0') {
                count++;
                i += 2;
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String b = bufferedReader.readLine();

        int result = BeautifulBinaryString.beautifulBinaryString(b);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

