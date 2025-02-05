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
    class BuildString {

        /*
         * Complete the 'buildString' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts following parameters:
         *  1. INTEGER a
         *  2. INTEGER b
         *  3. STRING s
         */

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */        public static int buildString(int N, int A, int B, String S){
            int[] dp = new int[N];
            dp[0] = A;
            int lastL = 0;
            for(int k=1;k<N;++k){
                dp[k] = dp[k-1]+A;
                int L = lastL+1;
                while(L>0){
                    String cur = S.substring(k-L+1, k+1);
                    int idx = S.substring(0, k-L+1).indexOf(cur);
                    if( -1==idx )
                        L--;
                    else{
                        dp[k] = Math.min(dp[k], dp[k-L]+B);
                        break;
                    }
                }
                lastL = L;
            }
            return dp[N-1];
        }
/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            int t = Integer.parseInt(bufferedReader.readLine().trim());

            IntStream.range(0, t).forEach(tItr -> {
                try {
                    String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                    int n = Integer.parseInt(firstMultipleInput[0]);

                    int a = Integer.parseInt(firstMultipleInput[1]);

                    int b = Integer.parseInt(firstMultipleInput[2]);

                    String s = bufferedReader.readLine();

                    int result = BuildString.buildString(n,a, b, s);
                    bufferedWriter.write(Integer.toString(result));
                    bufferedWriter.newLine();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            bufferedReader.close();
            bufferedWriter.close();
        }
    }
