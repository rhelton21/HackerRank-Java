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
 */public class TwoTwo {

    /*
     * Complete the 'twoTwo' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING a as parameter.
     */

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    public static int twoTwo(String s) {
        int l = s.length();
        int sum = 0, carry = 0;
        String ans = "";
        for(int i=l-1;i>=0;i--){
            sum = carry + (s.charAt(i) - '0')*2;
            carry = sum/10;
            ans = (sum%10) + ans;
        }
        if(carry!=0) ans = carry + ans;
        return Integer.parseInt(ans); // read in one command-line argument
    }

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    public static void main(String[] args) {

        /*  IN
        5
2222222
24256
65536
023223
33579
         */
                int result = twoTwo("5");
                System.out.println(result);

    /*
    7
4
1
4
0
     */
        }
}

