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
class TwoTwo2 {

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
 */    public static int twoTwo(String a) {
        // Write your code here
        Set<BigInteger> powersOfTwo = new HashSet<>();
        BigInteger powerOfTwo = BigInteger.ONE;
        for (int i = 0; i <= 800; i++) {
            powersOfTwo.add(powerOfTwo);
            powerOfTwo = powerOfTwo.multiply(BigInteger.valueOf(2));
        }

        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == '0') {
                continue; // skip groups starting with 0
            }
            BigInteger strength = BigInteger.ZERO;
            for (int j = i; j < a.length(); j++) {
                int digit = a.charAt(j) - '0';
                strength = strength.multiply(BigInteger.TEN).add(BigInteger.valueOf(digit));
                if (powersOfTwo.contains(strength)) {
                    count++;
                }
            }
        }
        return count;

    }

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
 //       BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String a = bufferedReader.readLine();

                int result = TwoTwo.twoTwo(a);

                System.out.println(result);
//                bufferedWriter.write(String.valueOf(result));
 //               bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
//        bufferedWriter.close();
    }
}
