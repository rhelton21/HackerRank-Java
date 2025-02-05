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
class TwoTwo3 {

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
 */    //   public static int twoTwo(String a) {
    // Write your code here

    //   }
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
        Trie trie = new Trie();
        BigInteger x = BigInteger.ONE;
        trie.insert(x.toString());
        for(int i = 0; i < 800; ++i) {
            x = x.shiftLeft(1);
            trie.insert(x.toString());
        }
        int count = 0;
        for(int i = 0; i < a.length(); ++i) {
            Trie node = trie.children[a.charAt(i) - '0'];
            if(node == null) continue;
            if(node.end) ++count;
            for(int j = 1; j < 243; ++j) {
                if(i - j < 0) break;
                node = node.children[a.charAt(i - j) - '0'];
                if(node == null) break;
                if(node.end) ++count;
            }
        }
        return count;
    }

    static class Trie {
        boolean end;
        Trie[] children;

        Trie() {
            end = false;
            children = new Trie[10];
        }

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */        void insert(String val) {
            if (val.length() == 0) {
                end = true;
                return;
            }
            int index = val.charAt(val.length() - 1) - '0';
            if (children[index] == null) {
                children[index] = new Trie();
            }
            children[index].insert(val.substring(0, val.length() - 1));
        }
    }

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String a = bufferedReader.readLine();

                int result = TwoTwo3.twoTwo(a);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
