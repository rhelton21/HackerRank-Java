package hackerRank;

import java.util.Scanner;
public class Billboards {
    static int n;
    static int k;
    static long a[];
    static long f[];
    static int heap[];
    static int heapSize;
    static int where[];
    static void swap(int a[], int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
    static boolean lessThan(int i, int j) {
        return f[i - 1] + a[i] < f[j - 1] + a[j];
    }
    static void siftUp(int i) {
        if (i > 1 && lessThan(heap[i], heap[i / 2])) {
            swap(heap, i, i / 2);
            swap(where, heap[i], heap[i / 2]);
            siftUp(i / 2);
        }
    }
    static void siftDown(int i) {
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
    static void insert(int element) {
        where[element] = ++heapSize;
        heap[heapSize] = element;
        siftUp(heapSize);
    }
    static void remove(int element) {
        if (where[element] < heapSize) {
            where[heap[heapSize]] = where[element];
            heap[where[element]] = heap[heapSize--];
            siftDown(where[element]);
            siftUp(where[element]);
        } else heapSize--;
        where[element] = 0;
    }
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        n = cin.nextInt();
        k = cin.nextInt();
        a = new long[n + 1];
        f = new long[n + 1];
        heap = new int[n + 1];
        where = new int[n + 1];
        long sum = 0;
        System.out.println("n -->" + n);
        for (int i = 1; i <= n; i++) {
            a[i] = cin.nextLong();
            sum += a[i];
        }
        System.out.println("sum -->" +sum);
        System.out.println("k -->" + k);
        for (int i = 1; i <= k; i++)
            insert(i);
        for (int i = k + 1; i <= n; i++) {
            insert(i);
            f[i] = f[heap[1] - 1] + a[heap[1]];
            remove(i - k);
        }
        System.out.println(sum - f[n]);
        cin.close();
    }
}
