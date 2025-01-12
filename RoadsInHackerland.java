package hackerRank;


//https://www.hackerrank.com/challenges/johnland/problem?isFullScreen=false

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toList;

public class RoadsInHackerland {
    /*
     * Complete the 'roadsInHackerland' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. 2D_INTEGER_ARRAY roads
     */

    public static String roadsInHackerland(int m, int n, List<List<Integer>> roads) {
        N = n;
        M = m;
        Edge[] edges = new Edge[M];
        results = new long[2 * M];
        nodes = new Node[N];
        for (int i = 0; i < N; i++)
            nodes[i] = new Node(i);
        for (int j = 0; j < M; j++) {
            List<Integer> road = roads.get(j);
            int node1 = road.get(0) - 1;
            int node2 = road.get(1) - 1;
            int power = road.get(2);
            edges[power] = new Edge(node1, node2, power);
        }
        ArrayList<Edge> bucket = new ArrayList<>();
        // build MST
        forests = new int[N];
        Arrays.fill(forests, -1);
        for (int j = 0; j < M; j++) {
            int n1 = edges[j].node1, n2 = edges[j].node2;
            if (find(n1) != find(n2)) {
                join(find(n1), find(n2));
                nodes[n1].edges.add(edges[j]);
                nodes[n2].edges.add(edges[j]);
                bucket.add(edges[j]);
            }
        }         // calculate distances
        Node root = nodes[bucket.get(0).node1];
        descend(null, root);
        for (Edge edge : bucket)
            results[edge.power] = edge.count *
                    (N - edge.count);
        // binary output
        long carry;
        long nm;
        long[] buffer = new long[2 * M];
        for (int i = 0; i < 2 * M; i++) {
            nm = results[i];
            int j = 0;
            while (nm != 0) {
                buffer[i + j] += nm % 2;
                nm /= 2;
                j++;
            }
        }
        carry = 0;
        Arrays.fill(results, 0);
        for (int i = 0; i < 2 * M; i++) {
            results[i] = (buffer[i] + carry) % 2;
            carry = (buffer[i] + carry) / 2;
        }
        boolean init = false;
        String returnStr = "";
        for (int i = 2 * M - 1; i >= 0; i--) {
            if (results[i] == 0 && init){
                returnStr+="0";
            }
            else if (results[i] == 1) {
                returnStr+="1";
                init = true;
            }
        }

    // Write your code here
        return returnStr;
    }



    public static class Edge {
        public int node1, node2, power;
        long count;

        public Edge(int node1, int node2, int power) {
            this.node1 = node1;
            this.node2 = node2;
            this.power = power;
        }
    }

    public static class Node {
        public int id;
        public ArrayList<Edge> edges;

        public Node(int id) {
            this.id = id;
            edges = new ArrayList<>();
        }
    }

    static long[] results;
    static int N, M;
    static Node[] nodes;
    // disjoint set implementation
    static int[] forests;

    static int find(int node) {
        if (forests[node] < 0) return node;
        return forests[node] = find(forests[node]);
    }

    static void join(int root1, int root2) {
        if (forests[root2] < forests[root1]) forests[root1] = root2;
        else {
            if (forests[root1] == forests[root2]) forests[root1]--;
            forests[root2] = root1;
        }
    }

    // count edge uses
    static int descend(Node parent, Node node) {
        int total = 1;
        for (Edge edge : node.edges) {
            if (parent != null && (edge.node1 == parent.id ||
                    edge.node2 == parent.id)) continue;
            Node target;
            if (edge.node1 == node.id) target = nodes[edge.node2];
            else target = nodes[edge.node1];
            edge.count = descend(node, target);
            total += edge.count;
        }
        return total;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
        int n = Integer.parseInt(firstMultipleInput[0]);
        int m = Integer.parseInt(firstMultipleInput[1]);
        List<List<Integer>> roads = new ArrayList<>();
        IntStream.range(0, m).forEach(i -> {
            try {
                roads.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        String result = roadsInHackerland(m, n, roads);
        System.out.println(result);
        bufferedReader.close();
    }

}