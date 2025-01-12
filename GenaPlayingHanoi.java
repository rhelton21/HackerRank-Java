package hackerRank;

import java.util.*;
import java.util.stream.Collectors;

public class GenaPlayingHanoi {

    public static int[] readIntArray3(Scanner in, int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = in.nextInt();
        }
        return arr;
    }



    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        int cases = 1;//in.nextInt();
        for (int testcase = 0; testcase < cases; testcase++) {
            int n = in.nextInt();
            int[] arr = readIntArray3(in, n);
            List<Integer> posts = Arrays.stream(arr)        // IntStream
                            .boxed()          // Stream<Integer>
                            .collect(Collectors.toList());
            hanoi(n, posts);
        }
    }

    public static int hanoi(int n, List<Integer> posts) {
        int[] locs = posts.stream()
                .mapToInt(Integer::intValue)
                .toArray();
        int[] powers = new int[]{1,4,16,64,256,1024,4096,16384,65536,262144};
        int score = 0;
        for (int i = 0; i < n; i++) {
            score += powers[i]*(locs[i]-1);
        }
        Set<Integer> allTests = new HashSet();
        Set<Integer> currentTests = new HashSet();
        currentTests.add(score);
        allTests.add(score);
        int currentMoves = 0;
//        printScore(score, n);
        while (!currentTests.contains(0)) {
            Set<Integer> nextTests = new HashSet();
            for (int test : currentTests) {
//                System.out.print("Looking at position "); printScore(test, n);
                int[] tops = new int[]{-1,-1,-1,-1};
                for (int i = n-1; i >=0; i--) {
                    int loc = (test>>(2*i))%4;
                    tops[loc] = i;
                }
                for (int j = 0; j < 4; j++) {
                    if (tops[j] >= 0) {
                        for (int k = 0; k < 4; k++) {
                            if ( k != j) {
                                if (tops[k] == -1 || tops[k] > tops[j]) {
                                    int newTest = test - (j<<(2*tops[j])) + (k<<(2*tops[j]));
                                    if (!allTests.contains(newTest)) {
//                                    System.out.print("Found new position "); printScore(newTest, n);
                                        nextTests.add(newTest);
                                    } else {
//                                        System.out.print("Already seen position "); printScore(newTest, n);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            currentTests = nextTests;
            allTests.addAll(currentTests);
            currentMoves++;
        }
        return currentMoves;
    }
}
