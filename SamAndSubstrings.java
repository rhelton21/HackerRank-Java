package hackerRank;

//https://www.hackerrank.com/challenges/sam-and-substrings
//See: https://www.hackerrank.com/contests/w3/challenges/sam-and-substrings
import java.io.*;

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
 */public class SamAndSubstrings {

    private final static int MOD = 1000000007;

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    public static void main(String[] args) throws IOException {

        int[] balls = strNumToArr((new BufferedReader(new InputStreamReader(System.in))).readLine());

        int n = balls.length;
        for(int i = n - 2; i >= 0; --i){
            balls[i] = (int)((balls[i+1] + (((long)balls[i])*(n - i))%MOD)%MOD);
        }

        int pow = 1;
        int total = 0;
        for(int i = 0; i < n; ++i){
            total = (int)((total + (((long)balls[i])*pow)%MOD)%MOD);
            pow = (int)((pow*10L)%MOD);
        }

        System.out.print(total);
    }

    private static int[] strNumToArr(String str){
        int n = str.length();
        int[] ar = new int[n];
        for(char c : str.toCharArray()){
            ar[--n] = c - '0';
        }
        return ar;
    }
}