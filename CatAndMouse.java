package hackerRank;

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
 */public class CatAndMouse {

    // Complete the catAndMouse function below.
    static String catAndMouse(int x, int y, int z) {
        int firstCatLocation = getLocation(x, z);
        int secondCatLocation = getLocation(y, z);
        if (firstCatLocation == secondCatLocation) {
            return "Mouse C";
        } else if (firstCatLocation > secondCatLocation) {
            return "Cat B";
        } else {
            return "Cat A";
        }

    }

    private static int getLocation(int catLocation, int mouseLocation) {
        return Math.abs(catLocation - mouseLocation);
    }

    private static final Scanner scanner = new Scanner(System.in);

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] xyz = scanner.nextLine().split(" ");

            int x = Integer.parseInt(xyz[0]);

            int y = Integer.parseInt(xyz[1]);

            int z = Integer.parseInt(xyz[2]);

            String result = catAndMouse(x, y, z);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
