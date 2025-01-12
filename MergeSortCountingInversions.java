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

class MergeSortCountingInversions {
    private static long mergeWhileSorting(int arr[], int leftNdx, int middle, int rightNdx) {
        long inversionCount = 0;

        // copy data to temp arrays
        int leftsize = middle-leftNdx+1;
        int rightsize = rightNdx-middle;
        int[] tempL = new int[leftsize];
        int[] tempR = new int[rightsize];
        for (int i=0; i<leftsize; i++) tempL[i] = arr[leftNdx+i];
        for (int i=0; i<rightsize; i++) tempR[i] = arr[middle+1+i];

        // merge temp arrays into a sorted one as far as can go
        // inversion/swapping only occurs here
        int l=0, r=0;
        int a=leftNdx;
        while (l<leftsize && r<rightsize) {
            if (tempL[l] <= tempR[r])
                arr[a] = tempL[l++];
            else {
                arr[a] = tempR[r++];
                inversionCount += (leftsize - l);
            }
            a++;
        }

        // copy remaining tempL[] and tempR[] elements (if any)
        while (l<leftsize)
            arr[a++] = tempL[l++];
        while (r<rightsize)
            arr[a++] = tempR[r++];

        return inversionCount;
    }

    private static long recursivelyPartition(int[] arr, int leftNdx, int rightNdx) {
        long inversionCount = 0;
        if (leftNdx < rightNdx) { // stop condition for left==right -> single element
            int middle = (leftNdx + rightNdx)/2;
            inversionCount += recursivelyPartition(arr, leftNdx, middle);
            inversionCount += recursivelyPartition(arr, middle+1, rightNdx);
            inversionCount += mergeWhileSorting(arr, leftNdx, middle, rightNdx);
        }
        return inversionCount;
    }
    /*
     * Complete the 'countInversions' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static long countInversions(List<Integer> integerList) {
        // Write your code here
        long count = recursivelyPartition(integerList.stream().mapToInt(i->i).toArray(), 0, integerList.size()-1);
        return count;
    }
/*
 * Complete the 'countInversions' function below.
 *
 * The function is expected to return a LONG_INTEGER.
 * The function accepts INTEGER_ARRAY arr as parameter.
 */

//   public static long countInversions(List<Integer> arr) {
// Write your code here

//   }



    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList());

                long result = countInversions(arr);

 //               bufferedWriter.write(String.valueOf(result));
 //               bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
 //       bufferedWriter.close();
    }
}
