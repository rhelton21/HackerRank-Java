package hackerRank;

import java.io.*;
import java.util.*;

public class CardPermutation {

    static long N = (long) Math.pow(10, 9) + 7;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(input[i]);
        }
        long result = solve(a);
        bw.write(String.valueOf(result));
        bw.newLine();
        bw.flush();
    }

    public static long solve(int[] P) {
        int n = P.length;
        int[] fixed = new int[n];
        Arrays.fill(fixed, 0);
        for (int v : P) {
            if (v > 0) {
                fixed[v - 1] = 1;
            }
        }
        List<Integer> idZ = new ArrayList<>();
        List<Integer> idNZ = new ArrayList<>();
        List<Integer> vP = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (P[i] == 0) {
                idZ.add(i);
            } else {
                idNZ.add(i);
            }
            if (fixed[i] == 0) {
                vP.add(i + 1);
            }
        }
        int nz = idZ.size();
        List<Integer> nV = new ArrayList<>();
        nV.add(0);
        for (int i = 1; i < n; i++) {
            nV.add(nV.get(i - 1) + (fixed[i - 1] == 0 ? 1 : 0));
        }
        List<Integer> nZ = new ArrayList<>();
        nZ.add(0);
        for (int i = 1; i < n; i++) {
            nZ.add(nZ.get(i - 1) + (P[i - 1] == 0 ? 1 : 0));
        }
        long[] f = new long[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; i++) {
            f[i] = (f[i - 1] * i) % N;
        }
        int[] nP = getnP(P, fixed);
        long Tnz = 0, Tz = 0, svP = 0;
        for (int i : idNZ) {
            Tnz = (Tnz + (P[i] - 1 - nP[i]) * f[n - i - 1]) % N;
        }
        for (int i : idZ) {
            Tz = (Tz + (i - nZ.get(i)) * f[n - i - 1]) % N;
        }
        long S = (f[nz] * (Tnz - Tz + 1)) % N;
        if (nz > 0) {
            List<Integer> snPV = new ArrayList<>();
            snPV.add(0);
            for (int i = 1; i < n; i++) {
                System.out.println("i --> "+i);
                System.out.println("snPV.get(i - 1) --> "+snPV.get(i - 1));
                System.out.println("snPV.get(i - 1) --> "+snPV.get(i - 1));

                snPV.add(snPV.get(i - 1) + nV.get(P[i - 1] - 1) * (P[i - 1] > 0 ? 1 : 0));
            }
            for (int i : idNZ) {
                Tnz = (Tnz + (snPV.get(i) - snPV.get(nP[i])) * f[n - i - 1]) % N;
            }
            for (int i : idZ) {
                svP = (svP + nV.get(i) * f[n - i - 1]) % N;
            }
            S = (S - f[nz - 1] * svP % N + N) % N;
            S = (S + f[nz - 1] * Tnz % N) % N;
        }
        return S;
    }

    public static int[] getnP(int[] P, int[] fixed) {
        int n = P.length;
        int[] nP = new int[n];
        for (int i = 0; i < n; i++) {
            if (P[i] > 0) {
                nP[i] = fixed[P[i] - 1] == 0 ? P[i] - 1 : nP[P[i] - 1];
            }
        }
        return nP;
    }
}





