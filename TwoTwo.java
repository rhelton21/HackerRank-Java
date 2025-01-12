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

public class TwoTwo {

    /*
     * Complete the 'twoTwo' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING a as parameter.
     */

    public static int twoTwo(String s) {
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

    public static void main(String[] args) {

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

