package hackerRank;


import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * This Java file is part of a HackerRank solution.
 * It solves a specific algorithmic challenge.
 *
 * Author: [Your Name]
 * Date: [Date]
 */
class SuperFunctionalStrings2 {
    static final int MOD = 1_000_000_007;
    static final int MAX = 100000;
    static class Suffix {
        int index;
        int[] rank = new int[2];
    }
    static Comparator<Suffix> cmp =
            (a, b) -> {
                return (a.rank[0] == b.rank[0]) ? a.rank[1] - b.rank[1]
                        : a.rank[0] - b.rank[0];
            };

    static int[] invSuff = new int[MAX];
    static int[] suffixArr = new int[MAX];
    static int[] lcp = new int[MAX];

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    static void buildSuffixArray(char[] txt) {
        int n = txt.length;
        Suffix[] suffixes = new Suffix[n];
        for (int i = 0; i < n; i++) {
            suffixes[i] = new Suffix();
            suffixes[i].index = i;
            suffixes[i].rank[0] = txt[i] - 'a';
            suffixes[i].rank[1] = ((i + 1) < n) ? (txt[i + 1] - 'a') :
                    -1;
        }
        Arrays.sort(suffixes, cmp);
        int[] ind = new int[n];
        for (int k = 4; k < 2 * n; k = k * 2) {
            int rank = 0;
            int prev_rank = suffixes[0].rank[0];
            suffixes[0].rank[0] = rank;
            ind[suffixes[0].index] = 0;
            for (int i = 1; i < n; i++) {
                if (suffixes[i].rank[0] == prev_rank &&
                        suffixes[i].rank[1] == suffixes[i - 1].rank[1]) {
                    prev_rank = suffixes[i].rank[0];
                    suffixes[i].rank[0] = rank;
                } else {
                    prev_rank = suffixes[i].rank[0];
                    suffixes[i].rank[0] = ++rank;
                }
                ind[suffixes[i].index] = i;
            }
            for (int i = 0; i < n; i++) {
                int nextindex = suffixes[i].index + k / 2;
                suffixes[i].rank[1] = (nextindex < n) ?
                        suffixes[ind[nextindex]].rank[0] : -1;
            }
            Arrays.sort(suffixes, cmp);
        }
        for (int i = 0; i < n; i++) {
            suffixArr[i] = suffixes[i].index;
            invSuff[suffixArr[i]] = i;
        }
    }
/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    static void kasai(char[] txt) {
        int n = txt.length;
        int k = 0;
        for (int i = 0; i < n; i++) {
            if (invSuff[i] == n - 1) {
                k = 0;
                continue;
            }
            int j = suffixArr[invSuff[i] + 1];
            while (i + k < n && j + k < n && txt[i + k] == txt[j + k])
            {
                k++;
            }
            lcp[invSuff[i]] = k;
            if (k > 0) {                 k--;
            }
        }
    }

    static long superFunctionalStrings(char[] s, int[][] map) {
        buildSuffixArray(s);
        kasai(s);
        int len = s.length;
        @SuppressWarnings("unchecked")
        Map<Integer, Integer>[] letterPlaceArr = new Map[len];
        letterPlaceArr[len - 1] = new HashMap<>();
        letterPlaceArr[len - 1].put((s[len - 1]) - 'a', len - 1);
        for (int i = len - 2; i >= 0; i--) {
            letterPlaceArr[i] = new HashMap<>(letterPlaceArr[i + 1]);
            letterPlaceArr[i].put(s[i] - 'a', i);
        }
        long result = 0;
        for (int i = 0; i < len; i++) {
            int nowLen = i == 0 ? 0 : lcp[i - 1];
            int startIndex = suffixArr[i];

            List<Integer> tempArr = new ArrayList<Integer>
                    (letterPlaceArr[startIndex].values());
            tempArr.add(len);
            Collections.sort(tempArr);
            for (int j = 1, tempLen = tempArr.size(); j < tempLen;
                 j++) {
                if (tempArr.get(j) - startIndex <= nowLen) {
                    continue;
                }
                int diff =
                        map[tempArr.get(j) - startIndex][j] -
                                map[Math.max(tempArr.get(j-1) - startIndex, nowLen)][j];
                if (diff < 0) {
                    diff += MOD;
                }
                result = (result + diff) % MOD;
            }
        }
        return result;
    }
    static int[][] init() {
        int[][] map = new int[100001][28];
        for (int i = 1; i <= 100000; i++) {
            long temp = 1;
            for (int j = 1; j <= 26; j++) {
                temp = (temp * i) % MOD;
                map[i][j] = (int)temp;
            }
        }
        for (int j = 1; j <= 26; j++) {
            map[0][j] = 0;
            int temp = map[1][j];
            for (int i = 2; i <= 100000; i++) {
                map[i][j] = (temp + map[i][j]) % MOD;
                temp = map[i][j];
            }
        }
        return map;
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
        int[][] map = init();

        IntStream.range(0, t).forEach(tItr -> {
            try {
                char[] s = bufferedReader.readLine().toCharArray();

                long result = superFunctionalStrings(s, map);
                System.out.println(String.valueOf(result));
                //               bufferedWriter.write(String.valueOf(result));
                //               bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

    }
}