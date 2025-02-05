package hackerRank;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;


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
 */public class StringFunctionCalc2 {
/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    public static long solve(String str) {
        SuffixAutomata a = new SuffixAutomata(str);
        return a.root.dp();
    }

    private static class SuffixAutomata {
        private Vertex root;
        private Vertex last;

        private class Vertex {
            Vertex suffixLink = null;
            Vertex[] edges;
            int log = 0;
            boolean visited = false;
            int terminals = 0;

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */            public Vertex(Vertex o, int log) {
                edges = o.edges.clone();
                this.log = log;
            }

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */            public Vertex(int log) {
                edges = new Vertex[26];
                this.log = log;
            }

            long dp() {
                if (visited) {
                    return 0;
                }
                visited = true;
                long r = 0;
                for (Vertex v : edges) {
                    if (v != null) {
                        r = Math.max(r, v.dp());
                        terminals += v.terminals;
                    }
                }
                return Math.max(r, 1L * log * terminals);
            }
        }

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */        public SuffixAutomata(String str) {
            last = root = new Vertex(0);
            for (int i = 0; i < str.length(); i++) {
                addChar(str.charAt(i));
            }
            addTerminal();
        }

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */        private void addChar(char c) {
            Vertex cur = last;
            last = new Vertex(cur.log + 1);
            while (cur != null && cur.edges[c - 'a'] == null) {
                cur.edges[c - 'a'] = last;
                cur = cur.suffixLink;
            }
            if (cur != null) {
                Vertex q = cur.edges[c - 'a'];
                if (q.log == cur.log + 1) {
                    last.suffixLink = q;
                } else {
                    Vertex r = new Vertex(q, cur.log + 1);
                    r.suffixLink = q.suffixLink;
                    q.suffixLink = r;
                    last.suffixLink = r;
                    while (cur != null) {
                        if (cur.edges[c - 'a'] == q) {
                            cur.edges[c - 'a'] = r;
                        } else {
                            break;
                        }
                        cur = cur.suffixLink;
                    }
                }
            } else {
                last.suffixLink = root;
            }
        }

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */        private void addTerminal() {
            Vertex cur = last;
            while (cur != null) {
                cur.terminals++;
                cur = cur.suffixLink;
            }
        }
    }

    static class FastScanner {

        private static BufferedReader reader;

        private static StringTokenizer tokenizer;

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */        public FastScanner(InputStream in) throws Exception {
            reader = new BufferedReader(new InputStreamReader(in));
            tokenizer = new StringTokenizer(reader.readLine().trim());
        }

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */        public int numTokens() throws Exception {
            if (!tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine().trim());
                return numTokens();
            }
            return tokenizer.countTokens();
        }

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */        public boolean hasNext() throws Exception {
            if (!tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine().trim());
                return hasNext();
            }
            return true;
        }

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */        public String next() throws Exception {
            if (!tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine().trim());
                return next();
            }
            return tokenizer.nextToken();
        }

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */        public double nextDouble() throws Exception {
            return Double.parseDouble(next());
        }

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */        public float nextFloat() throws Exception {
            return Float.parseFloat(next());
        }

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */        public long nextLong() throws Exception {
            return Long.parseLong(next());
        }

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */        public int nextInt() throws Exception {
            return Integer.parseInt(next());
        }

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */        public int[] nextIntArray() throws Exception {
            String[] line = reader.readLine().trim().split(" ");
            int[] out = new int[line.length];
            for (int i = 0; i < line.length; i++) {
                out[i] = Integer.valueOf(line[i]);
            }
            return out;
        }

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */        public double[] nextDoubleArray() throws Exception {
            String[] line = reader.readLine().trim().split(" ");
            double[] out = new double[line.length];
            for (int i = 0; i < line.length; i++) {
                out[i] = Double.valueOf(line[i]);
            }
            return out;
        }

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */        public Integer[] nextIntegerArray() throws Exception {
            String[] line = reader.readLine().trim().split(" ");
            Integer[] out = new Integer[line.length];
            for (int i = 0; i < line.length; i++) {
                out[i] = Integer.valueOf(line[i]);
            }
            return out;
        }

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */        public BigInteger[] nextBigIngtegerArray() throws Exception {
            String[] line = reader.readLine().trim().split(" ");
            BigInteger[] out = new BigInteger[line.length];
            for (int i = 0; i < line.length; i++) {
                out[i] = new BigInteger(line[i]);
            }
            return out;
        }

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */        public BigInteger nextBigInteger() throws Exception {
            return new BigInteger(next());
        }

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */        public String nextLine() throws Exception {
            return reader.readLine().trim();
        }

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */        public long[] nextLongArray() throws Exception {
            String[] line = reader.readLine().trim().split(" ");
            long[] out = new long[line.length];
            for (int i = 0; i < line.length; i++) {
                out[i] = Long.valueOf(line[i]);
            }
            return out;
        }
    }
/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    public static void main(String[] args) throws Exception {
        FastScanner sc = new FastScanner(System.in);
        String str = sc.next();
        System.out.println(solve(str));
    }
}