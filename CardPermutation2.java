package hackerRank;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toList;

public class CardPermutation2 {

    private static final int MAXN = 300005;
    private static final int MOD = 1000000007;
    private static final int INV2 = (MOD + 1) / 2;

    private static int[] c;
    private static int[] pre;
    private static int[] fac;
    private static int N;
    private static int count;
    private static int lex;
    private static int[] a;
    private static int cur;
    private static int ans;
    private static int sum;

    private static String[] inputString;
    private static int currentLine = 0;

    private static int readLineAsInt() {
        return Integer.parseInt(inputString[currentLine++]);
    }

    private static int[] readLineAsIntArray() {
        return Stream.of(inputString[currentLine++].split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static int add(int a, int b) {
        a += b;
        return a >= MOD ? a - MOD : a;
    }

    private static int pop(int a, int b) {
        a -= b;
        return a < 0 ? a + MOD : a;
    }

    private static int mul(int a, int b) {
        return (int) ((long) a * b % MOD);
    }

    private static int lowbit(int i) {
        return i & -i;
    }

    private static void update(int o, int v) {
        int i = o + 1;
        while (i <= N) {
            c[i] = add(c[i], v);
            i += lowbit(i);
        }
    }

    private static int calc(int o) {
        int s = 0;
        int i = o + 1;
        while (i >= 1) {
            s = add(s, c[i]);
            i -= lowbit(i);
        }
        return s;
    }

    private static int cal2(int n) {
        return mul(mul(n, pop(n, 1)), INV2);
    }

    private static void prepare() {
        for (int i = 1; i <= N; i++) {
            a[i] -= 1;
            if (a[i] == -1) {
                count += 1;
            }
            if (a[i] >= 0) {
                pre[a[i]] = 1;
            }
        }
        fac[0] = 1;
        for (int i = 1; i <= N; i++) {
            fac[i] = mul(i, fac[i - 1]);
        }
        for (int i = 1; i < N; i++) {
            pre[i] += pre[i - 1];
        }
        lex = mul(mul(N, pop(N, 1)), INV2);
        for (int i = 1; i <= N; i++) {
            if (a[i] != -1) {
                lex = pop(lex, a[i]);
            }
        }
    }

    private static int solve(List<Integer> x) {
        a = x.stream()
                .filter(Objects::nonNull)
                .mapToInt(Integer::intValue)
                .toArray();
        N = a.length - 1;
        c = new int[N + 1];
        pre = new int[N];
        fac = new int[N + 1];
        prepare();
        for (int i = 1; i <= N; i++) {
            if (a[i] == -1) {
                cur = mul(count, mul(cal2(N - i),
                        cal2(i - pre[N - i - 1])));
                ans = add(ans, cur);
                update(N - i, mul(count, fac[N - i - 1]));
                count -= 1;
            } else {
                cur = calc(N - a[i] - 1);
                ans = add(ans, mul(cur, fac[N - i]));
                update(N - a[i], fac[N - i]);
            }
        }
        sum = 0;
        for (int i = 1; i <= N; i++) {
            if (a[i] == -1) {
                sum = add(sum, fac[N - i]);
            }
        }
        ans = mul(ans, fac[count]);
        ans = mul(ans, sum);
        ans = mul(ans, lex);
        return ans;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
 //       int[] a = new int[n];
        String[] input = br.readLine().split(" ");

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int a = Integer.parseInt(input[i]);
            list.add(a);
        }
        long result = solve(list);
        //       BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

//        int n = Integer.parseInt(bufferedReader.readLine().trim());

//        List<Integer> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
//                .map(Integer::parseInt)
//                .collect(toList());

//        long result = CardPermutation2.solve(a);
        System.out.println(String.valueOf(result));

 //       bufferedWriter.write(String.valueOf(result));
 //       bufferedWriter.newLine();

//        bufferedReader.close();
 //       bufferedWriter.close();
    }
}