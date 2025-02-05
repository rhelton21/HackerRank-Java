package hackerRank;

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
 */public class BuildPalindrome5 {
        static int N = 100000;
        static int M = 4 * N + 3;
        static char[] a = new char[M];
        static int[] sa = new int[M];
        static int[] isa = new int[M];
/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */        static void iota(int v[], int end, int val) {
            for (int i = 0; i < end; i++) {
                v[i] = val++;
            }
        }
/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */        static void suffixArray(int n, int m, int h[], int x[]) {
            Arrays.fill(h, 0, m, 0);
            for (int i = 0; i < n; i++) {
                isa[i] = a[i];
            }
            for (int i = 0; i < n; i++) {
                h[isa[i]]++;
            }
            for (int i = 1; i < m; i++) {
                h[i] += h[i - 1];
            }
            for (int i = n; --i >= 0;) {
                sa[--h[isa[i]]] = i;
            }
            int k = 1;
            for (;; k <<= 1) {
                iota(x, k, n - k);
                int j = k;
                for (int i = 0; i < n; i++) {
                    if (sa[i] >= k) {
                        x[j++] = sa[i] - k;
                    }
                }
                Arrays.fill(h, 0, m, 0);
                for (int i = 0; i < n; i++) {
                    h[isa[x[i]]]++;
                }
                for (int i = 1; i < m; i++) {
                    h[i] += h[i - 1];
                }
                for (int i = n; --i >= 0;) {
                    sa[--h[isa[x[i]]]] = x[i];
                }
                Arrays.fill(h, 0, m, 0);
                m = 1;
                h[sa[0]] = 0;
                for (int i = 1; i < n; i++) {
                    if (isa[sa[i]] != isa[sa[i - 1]] || Math.max(sa[i], sa[i
                            - 1]) >= n - k
                            || isa[sa[i] + k] != isa[sa[i - 1] + k]) {                         m++;
                    }
                    h[sa[i]] = m - 1;
                }
                System.arraycopy(h, 0, isa, 0, n);
                if (m == n) {
                    break;
                }
            }
            k = 0;
            h[0] = 0;
            for (int i = 0; i < n; i++) {
                if (isa[i] > 0) {
                    for (int j = sa[isa[i] - 1]; i + k < n && j + k < n &&
                            a[i + k] == a[j + k]; k++)
                        ;
                    h[isa[i]] = k;
                    if (k > 0) {
                        k--;
                    }
                }
            }
        }
        static int[][] tab = new int[19][M];
        static int lcp(int x, int y) {
            if (x < 0 || y < 0) {
                return 0;
            }
            x = isa[x];
            y = isa[y];
            if (x > y) {
                int t = x;
                x = y;
                y = t;
            }
            x++;
            int k = 0;
            while (1 << k + 1 < y - x + 1) {
                k++;
            }
            return Math.min(tab[k][x], tab[k][y - (1 << k) + 1]);
        }
        static long[] z = new long[2 * N + 1];
        static int[] len = new int[N];

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */        static void manacher(int from, int n) {
            int m = 2 * n + 1;
            z[0] = 1;
            for (int f = 0, g = 0, i = 1; i < m; i++) {
                if (i < g && z[2 * f - i] != g - i) {
                    z[i] = Math.min(z[2 * f - i], g - i);
                } else {
                    g = Math.max(g, f = i);
                    for (; g < m && 2 * f - g >= 0 && (g % 2 == 0 || a[from
                            + (2 * f - g) / 2] == a[from + g / 2]); g++) {
                        len[(g - 1) / 2] = g - f;
                    }
                    z[f] = g - f;
                }
            }
        }
        static int[] L = new int[M];
        static int[] R = new int[M];
        static String buildPalindrome(String a2, String b1) {
            char[] a1 = a2.toCharArray();
            int na = a1.length;
            char[] b = b1.toCharArray();
            System.arraycopy(a1, 0, a, 0, na);
            a[na] = 0;
            int ra = na + 1;
            for (int i = 0; i < na; i++) {
                a[ra + i] = a[na - 1 - i];
            }
            a[ra + na] = 1;
            int nb = b.length;
            int b0 = ra + na + 1;
            System.arraycopy(b, 0, a, b0, nb);
            a[b0 + nb] = 2;
            int rb = b0 + nb + 1;
            for (int i = 0; i < nb; i++) {
                a[rb + i] = b[nb - 1 - i];
            }
            int n = 2 * na + 2 * nb + 3;
            suffixArray(n, 'z' + 1, tab[0], L);
            for (int i = 1; 1 << i < n; i++) {
                for (int j = n - (1 << i); j > 0; j--) {
                    tab[i][j] = Math.min(tab[i - 1][j], tab[i - 1][j + (1 <<
                            i - 1)]);
                }
            }
            int bma = na + 1 + na + 1;
            for (int i = 0; i < n; i++) {
                if (bma <= sa[i] && sa[i] < bma + nb) {
                    L[i] = sa[i];
                } else {
                    L[i] = i > 0 ? L[i - 1] : -1;
                }             }
            for (int i = n; --i >= 0;) {
                if (bma <= sa[i] && sa[i] < bma + nb) {
                    R[i] = sa[i];
                } else {
                    R[i] = i + 1 < n ? R[i + 1] : -1;
                }
            }
            manacher(na + 1, na);
            int opt = 0;
            int optp = 0;
            int optx = 0;
            int opty = 0;

            for (int i = 0; i < na; i++) {
                int pal = i > 0 ? len[i - 1] : 0;
                int ii = na + 1 + i;
                int j = L[isa[ii]];
                if (lcp(ii, R[isa[ii]]) > lcp(ii, j))
                    j = R[isa[ii]];
                int comm = lcp(ii, j);
                if (comm > 0) {
                    int len = pal + 2 * comm;
                    int pos = na - (i + comm);
                    if (len > opt || len == opt && isa[pos] < isa[optp]) {
                        opt = len;
                        optp = pos;
                        optx = pal + comm;
                        opty = comm;
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                if (na + 1 <= sa[i] && sa[i] < na + 1 + na) {
                    L[i] = sa[i];
                } else {
                    L[i] = i > 0 ? L[i - 1] : -1;
                }
            }
            for (int i = n; --i >= 0;) {
                if (na + 1 <= sa[i] && sa[i] < na + 1 + na) {
                    R[i] = sa[i];
                } else {
                    R[i] = i + 1 < n ? R[i + 1] : -1;
                }
            }
            manacher(bma, nb);
            for (int i = 0; i < nb; i++) {
                int pal = i > 0 ? len[i - 1] : 0;
                int ii = bma + i;                 int j = L[isa[ii]];
                if (lcp(ii, R[isa[ii]]) > lcp(ii, j)) {
                    j = R[isa[ii]];
                }
                int comm = lcp(ii, j);
                if (comm > 0) {
                    int len = pal + 2 * comm, pos = n - (i + comm);
                    if (len > opt || len == opt && isa[pos] < isa[optp]) {
                        opt = len;
                        optp = pos;
                        optx = comm;
                        opty = pal + comm;
                    }
                }
            }
            if (opt == 0) {
                return "-1";
            }
            char[] result = new char[optx + opty];
            for (int i = 0; i < optx; i++) {
                result[i] = a[optp + i];
            }
            for (int i = 0; i < opty; i++) {
                result[optx + i] = a[optp + opty - i - 1];
            }
            return new String(result);
        }
/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new
                    InputStreamReader(System.in));
//            BufferedWriter bw = new BufferedWriter(new
//                    FileWriter(System.getenv("OUTPUT_PATH")));
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            for (int tItr = 0; tItr < t; tItr++) {
                char[] a = br.readLine().toCharArray();
                char[] b = br.readLine().toCharArray();
                String stringA = new String(a);
                String stringB = new String(a);
                System.out.println(buildPalindrome(stringA, stringB));
 //               bw.newLine();
            }
 //           bw.close();
            br.close();
        }
    }