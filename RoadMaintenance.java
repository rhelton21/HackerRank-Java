package hackerRank;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class RoadMaintenance {
    static int mod = (int) 1e9 + 7;
    static int N = 2 * 100000 + 5;
    static int n, m, x, y;
    static long[][][] dp = new long[N][11][11];
    static long[][][] temp = new long[N][11][11];
    static List<Integer>[] v = new ArrayList[N];

    public static void dfs(int node, int root) {
        dp[node][0][0] = 1;
        for (int it : v[node]) {
            if (it == root) continue;
            dfs(it, node);
            for (int i = 0; i <= 10; i++) {
                for (int j = 0; j <= 10; j++) {
                    temp[node][i][j] = dp[node][i][j];
                    dp[node][i][j] = 0;
                }
            }
            for (int i = 0; i <= m; i++) {
                for (int j = 0; j <= m - i; j++) {
                    for (int k = 0; k <= m; k++) {
                        dp[node][i + j][k] = (dp[node][i + j][k] + temp[node][i][k] * dp[it][j][0]) % mod;
                        dp[node][i + j][k + 1] = (dp[node][i + j][k + 1] + temp[node][i][k] * dp[it][j][1]) % mod;
                        if (k > 0)
                            dp[node][i + j + 1][k - 1] = (dp[node][i + j + 1][k - 1] + temp[node][i][k] * dp[it][j][1] * k) % mod;
                        dp[node][i + j + 1][k] = (dp[node][i + j + 1][k] + temp[node][i][k] * dp[it][j][1]) % mod;
                    }
                }
            }
        }
        for (int i = 0; i <= m; i++) {
            dp[node][i][1] = (dp[node][i][1] + dp[node][i][0]) % mod;
        }
    }
    public static int roadMaintenance(int n, int m, List<List<Integer>> edges) {
        RoadMaintenance.m = m;
        RoadMaintenance.n = n;

        v = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            v[i] = new ArrayList<>();
        }
        for (List<Integer> edge : edges) {
            int x = edge.get(0) - 1;
            int y = edge.get(1) - 1;
            v[x].add(y);
            v[y].add(x);
        }
        dfs(0, -1);
        return (int) dp[0][m][0];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            v[i] = new ArrayList<Integer>();
        }
        for (int i = 2; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            v[x].add(y);
            v[y].add(x);
            List<Integer> test = new ArrayList<>();
            test.add(y);
            test.add(x);
            edges.add(test);

        }
        int test2 = RoadMaintenance.roadMaintenance(n,m,edges);
 //       dfs(1, 0);
        System.out.println(test2);
    }
}
