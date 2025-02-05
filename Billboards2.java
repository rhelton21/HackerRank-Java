package hackerRank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


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
 */public class Billboards2 {
    static int n;
    static int k;
    static long a[];
    static long f[];
    static int heap[];
    static int heapSize;
    static int where[];


/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    public static long billboards(int n, int k, List<Integer> revenue) {
        System.out.println("n -->" + n);
        System.out.println("k -->" + k);
        System.out.println("revenue.size() -->" +revenue.size());
        a = new long[n + 1];
        f = new long[n + 1];
        heap = new int[n + 1];
        where = new int[n + 1];
        long sum = 0;
        for (int i = 1; i <= n; i++) {
            a[i] = (long) revenue.get(i - 1);
            System.out.println("i-->" +i);
            System.out.println("a[i] -->" +i);
            sum += a[i];
        }
        System.out.println("sum -->" +sum);
        for (int i = 1; i <= k; i++){
            insert(i);
        }
        for (int i = k + 1; i <= n; i++) {
            insert(i);
            f[i] = f[heap[1] - 1] + a[heap[1]];
            remove(i - k);
        }
        return  (sum - f[n]);


    }
/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    static void swap(int a[], int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    static boolean lessThan(int i, int j) {
        return f[i - 1] + a[i] < f[j - 1] + a[j];
    }

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    static void siftUp(int i) {
        if (i > 1 && lessThan(heap[i], heap[i / 2])) {
            swap(heap, i, i / 2);
            swap(where, heap[i], heap[i / 2]);
            siftUp(i / 2);
        }
    }

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    static void siftDown(int i) {
        int which = i;
        if (2 * i <= heapSize && lessThan(heap[2 * i],
                heap[which])) which = 2 * i;
        if (2 * i + 1 <= heapSize && lessThan(heap[2 * i + 1],
                heap[which])) which = 2 * i + 1;
        if (which != i) {
            swap(heap, i, which);
            swap(where, heap[i], heap[which]);
            siftDown(which);
        }
    }

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    static void insert(int element) {
        where[element] = ++heapSize;
        heap[heapSize] = element;
        siftUp(heapSize);
    }

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    static void remove(int element) {
        if (where[element] < heapSize) {
            where[heap[heapSize]] = where[element];
            heap[where[element]] = heap[heapSize--];
            siftDown(where[element]);
            siftUp(where[element]);
        } else heapSize--;
        where[element] = 0;
    }

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        List<Integer> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            edges.add(x);
        }
        long result = Billboards2.billboards(n, k, edges);
        System.out.println(result);

    }
}

