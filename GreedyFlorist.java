package hackerRank;


//https://www.hackerrank.com/challenges/flowers
import java.io.*;
import java.util.*;

/**
 * This Java file is part of a HackerRank solution.
 * It solves a specific algorithmic challenge.
 *
 * Author: [Your Name]
 * Date: [Date]
 */
class GreedyFlorist{
/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    public static void main( String args[] ) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String temp[] = br.readLine().split(" ");
        byte N = Byte.parseByte(temp[0]);
        byte K = Byte.parseByte(temp[1]);
        temp = br.readLine().split(" ");
        int[] ar = new int[N];
        for(byte i = 0; i < N; i++){
            ar[i] = Integer.parseInt(temp[i]);
        }
        Arrays.sort(ar);
        byte x = 1;
        int total = 0;
        for(byte i = N; i > 0; x++){
            for(byte k = 0; i > 0 && k < K; k++){
                total += x*ar[--i];
            }
        }
        System.out.println(total);
    }
}