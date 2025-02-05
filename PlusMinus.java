package hackerRank;

/**
 *
 * Problem Statement-
 * [Plus Minus](https://www.hackerrank.com/challenges/plus-minus)
 * [Tutorial](https://youtu.be/aLS4HYPfzUw)
 *
 */
        import java.util.Scanner;
        import java.io.*;
        import java.math.*;
        import java.security.*;
        import java.text.*;
        import java.util.*;
        import java.util.concurrent.*;
        import java.util.regex.*;

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
 */public class PlusMinus {

    // Complete the plusMinus function below.
/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    static void plusMinus(List<Integer> array)  {
        Integer[] arr = array.toArray(new Integer[0]);
        float p=0;
        float n=0;
        float z=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i].intValue() == 0){
                z++;
            }
            else{
                if(arr[i].intValue() > 0){
                    p++;
                }
                else{
                    n++;
                }
            }
        }
        p/= arr.length;
        n/= arr.length;
        z/= arr.length;
        System.out.println(p);
        System.out.println(n);
        System.out.println(z);
    }

    private static final Scanner scanner = new Scanner(System.in);

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

  //      plusMinus(arr);

        scanner.close();
    }
}
