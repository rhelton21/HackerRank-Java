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
 */public class GCDMatrix {
    static final int MAXN = 100000;
    static final int MAXV = MAXN + 5;
    static final int[] a = new int[MAXV];
    static final int[] b = new int[MAXV];
    static final long[] d = new long[MAXV];
    static final int[] va = new int[MAXV];
    static final int[] vb = new int[MAXV];
    static int gdcMatrix(int w, int x, int y, int z) {
        for (int i = 1; i < MAXV; i++) {
            va[i] = 0;
            vb[i] = 0;
            d[i] = 0;
        }
        for (int i = w; i <= y; i++) {
            va[a[i]]++;
        }
        for (int i = x; i <= z; i++) {
            vb[b[i]]++;
        }
        for (int i = 1; i <= MAXN; i++) {
            int j = i;
            int v1 = 0;
            int v2 = 0;
            while (j <= MAXN) {
                v1 += va[j];
                v2 += vb[j];
                j += i;
            }
            va[i] = v1;
            vb[i] = v2;
        }
        int cnt = 0;
        for(int i = MAXN; i >= 1; i--) {
            int j = i;
            long ans = 0;
            ans = ((long) va[i]) * vb[i];
            while(j <= MAXN) {
                ans -= d[j];
                j += i;
            }
            d[i] = ans;
            if (d[i] > 0) cnt++;
        }
        return cnt;
    }
/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new
                InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new
                FileWriter(System.getenv("OUTPUT_PATH")));
        String[] nmq = br.readLine().replaceAll("\\s+$", "").split(" ");
        int n = Integer.parseInt(nmq[0]);
        int m = Integer.parseInt(nmq[1]);
        int q = Integer.parseInt(nmq[2]);
        String[] aItems = br.readLine().replaceAll("\\s+$","").split(" ");
        for (int i = 0; i < n; i++) {
            int aItem = Integer.parseInt(aItems[i]);
            a[i] = aItem;
        }
        String[] bItems = br.readLine().replaceAll("\\s+$",
                "").split(" ");
        for (int i = 0; i < m; i++) {
            int bItem = Integer.parseInt(bItems[i]);
            b[i] = bItem;
        }
        for (int qItr = 0; qItr < q; qItr++) {
            String[] r1C1R2C2 =
                    br.readLine().replaceAll("\\s+$", "").split(" ");
            int r1 = Integer.parseInt(r1C1R2C2[0]);
            int c1 = Integer.parseInt(r1C1R2C2[1]);
            int r2 = Integer.parseInt(r1C1R2C2[2]);
            int c2 = Integer.parseInt(r1C1R2C2[3]);
            // Write Your Code Here
            int result = gdcMatrix(r1, c1, r2, c2);
            bw.write(String.valueOf(result));
            bw.newLine();
        }
        br.close();
        bw.close();
    }
}
