package hackerRank;


import java.util.Arrays;
import java.util.ArrayDeque;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.awt.Point;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Comparator;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

class StringCal {
    static class Node {
        static int[] a;
        final static int neg = -1;
        final int start;
        final int end;
        final int min;
        Node[] nodes;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
            if (start < end) {
                int min = neg;
                int[] pos = {start, (start + end) / 2, end};
                nodes = new Node[2];
                for (int i = 0; i < 2; i++) {
                    nodes[i] = new Node(pos[i] + i, pos[i + 1]);
                    if (min == -1 || a[nodes[i].min] < a[min]) {
                        min = nodes[i].min;
                    }
                }
                this.min = min;
            } else {
                min = start;
            }
        }

        public int query(int queryStart, int queryEnd) {
            if (queryEnd < start || end < queryStart) {
                return neg;
            }
            if (queryStart <= start && end <= queryEnd) {
                return min;
            }
            int ans = neg;
            for (Node node : nodes) {
                int temp = node.query(queryStart, queryEnd);
                if (temp > neg && (ans == neg || a[temp] <
                        a[ans])) {
                    ans = temp;
                }
            }
            return ans;
        }
    }

    public void solve(int testNumber, InputReader in,
                      OutputWriter out) {
        String s = in.next();
        int[] lcp = XString.lcp(s);
        Node.a = lcp;
        Node root = new Node(0, lcp.length - 2);
        ArrayDeque<Point> stack = new ArrayDeque<>();
        stack.push(new Point(0, lcp.length - 2));
        long ans = s.length();
        while (!stack.isEmpty()) {
            Point point = stack.pop();
            int start = point.x;
            int end = point.y;
            if (start <= end) {
                int min = root.query(start, end);
                ans = Math.max(ans, (long) lcp[min] * (end -
                        start + 2));
                stack.push(new Point(start, min - 1));
                stack.push(new Point(min + 1, end));
            }
        }
        out.println(ans);
    }
}

class InputReader {
    private BufferedReader input;
    private StringTokenizer line = new StringTokenizer("");

    public InputReader(InputStream in) {
        input = new BufferedReader(new InputStreamReader(in));
    }

    public void fill() {
        try {
            if (!line.hasMoreTokens()) line = new
                    StringTokenizer(input.readLine());
        } catch (IOException io) {
            io.printStackTrace();
            System.exit(0);
        }
    }

    public String next() {
        fill();
        return line.nextToken();
    }
}

class OutputWriter {
    private PrintWriter output;

    public OutputWriter(OutputStream out) {
        output = new PrintWriter(out);
    }

    public void println(Object o) {
        output.println(o);
    }

    public void close() {
        output.close();
    }
}

class XString {
    public static int[] lcp(String str, int[] suffix) {
        final int n = str.length();
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[suffix[i]] = i;
        }
        int[] lcp = new int[n];
        for (int i = 0, w = 0; i < n; i++) {
            if (pos[i] < n - 1) {
                int j = suffix[pos[i] + 1];
                while (Math.max(i, j) + w < n && str.charAt(i +
                        w) == str.charAt(j + w)) {
                    w++;
                }
                lcp[pos[i]] = w;
                if (w > 0) {
                    w--;
                }
            }
        }
        return lcp;
    }

    public static int[] lcp(String str) {
        int[] suffix = suffixArray(str);
        return lcp(str, suffix);
    }

    public static int[] suffixArray(String str) {
        final int n = str.length();
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) {
            order[i] = n - i - 1;
        }
        Arrays.sort(order, (a, b) -> str.charAt(a) -
                str.charAt(b));
        int[] suffix = new int[n];
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            suffix[i] = order[i];
            rank[suffix[i]] = str.charAt(suffix[i]);
        }
        for (int len = 1; len < n; len <<= 1) {
            int[] r = Arrays.copyOf(rank, n);
            for (int i = 0; i < n; i++) {
                if (i > 0 && r[suffix[i - 1]] == r[suffix[i]] &&
                        suffix[i - 1] + len < n &&
                        r[suffix[i - 1] + len / 2] ==
                                r[suffix[i] + len / 2]) {
                    rank[suffix[i]] = rank[suffix[i - 1]];
                } else {
                    rank[suffix[i]] = i;
                }
            }
            int[] pos = new int[n];
            for (int i = 0; i < n; i++) {
                pos[i] = i;
            }
            int[] s = Arrays.copyOf(suffix, n);
            for (int i = 0; i < n; i++) {
                int t = s[i] - len;
                if (t >= 0) {
                    suffix[pos[rank[t]]++] = t;
                }
            }
        }
        return suffix;
    }
}

public class StringFunctionCalc {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        StringCal solver = new StringCal();
        solver.solve(1, in, out);
        out.close();
    }
}