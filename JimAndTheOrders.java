package hackerRank;


//https://www.hackerrank.com/challenges/jim-and-the-orders

import java.io.*;
import java.util.*;

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
 */public class JimAndTheOrders {
/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    public static void main(String[] args) throws IOException {

        //Get input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final short N = Short.parseShort(br.readLine());
        final Fan[] fans = new Fan[N];
        for (int i = 0; i < N; ++i) {
            String[] line = br.readLine().split(" ");
            fans[i] = new Fan((short) (i + 1), Integer.parseInt(line[0]) + Integer.parseInt(line[1]));
        }
        br.close();

        //Sort fans
        Arrays.sort(fans, 0, N);

        //Print output
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < N; sb.append(fans[i++].id + " ")) {
        }
        System.out.print(sb);
    }

    private static class Fan implements Comparator<Fan>, Comparable<Fan> {
        public final short id;
        public final int time;

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */        public Fan(short id, int time) {
            this.id = id;
            this.time = time;
        }

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */        public int compare(Fan a, Fan b) {
            return (a.time < b.time) ? -1 : (a.time > b.time) ? 1 : (a.id < b.id) ? -1 : 1;
        }

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */        public int compareTo(Fan b) {
            return this.compare(this, b);
        }
    }
}