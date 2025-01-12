package hackerRank;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Bikeracers2 {


    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        int k = Integer.parseInt(firstMultipleInput[2]);

        List<List<Integer>> bikers = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                bikers.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<List<Integer>> bikes = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                bikes.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        long result = bikeRacers(n, m, k, bikers, bikes);
        System.out.println(String.valueOf(result));
        bufferedReader.close();
/*


        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

*/

        //      String line = in.readLine();
        //       String[] data = line.split("\\s+");


    }

    public static long bikeRacers(int n, int m, int k, List<List<Integer>> bikers, List<List<Integer>> bikes) {
        Node source;
        Node sink;
        Node[] bikersArr;
        Node[] bikesArr;       // Write your code here
        int numBikers = n;
        int numBikes = m;
        int numRequired = k;

        source = new Node();
        sink = new Node(true);
        bikersArr = new Node[numBikers];
        bikesArr = new Node[numBikes];

        Coordinate[] bikerPos = new Coordinate[numBikers];

        for (int i = 0; i < numBikers; i++) {
            bikersArr[i] = new Node();
            source.addConnection(bikersArr[i]);
            List<Integer> bikersLine = bikers.get(i);
            bikerPos[i] = new Coordinate(bikersLine.get(0), bikersLine.get(1));
        }

        ArrayList<BikerBikeDistance> bbd = new ArrayList<>();

        for (int j = 0; j < numBikes; j++) {
            bikesArr[j] = new Node();
            bikesArr[j].addConnection(sink);
            List<Integer> bikesLine = bikes.get(j);
            //           line = in.readLine();
            //           data = line.split("\\s+");
            int bx = bikesLine.get(0);
            int by = bikesLine.get(1);
            for (int i = 0; i < numBikers; i++) {
                bbd.add(new BikerBikeDistance(i, j, getCost(bx, by, bikerPos[i].x, bikerPos[i].y)));
            }
        }

        Collections.sort(bbd);


        int total = 0;
        long dist = 0;
        for (int i = 0; total < numRequired; i++) {
            BikerBikeDistance cbbd = bbd.get(i);
            dist = cbbd.cost;
            bikersArr[cbbd.biker].addConnection(bikesArr[cbbd.bike]);
            if (source.dfsAndReverse(i)) {
                total++;
            }
        }
        return dist;

    }

    private static long getCost(long x1, long y1, long x2, long y2) {
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }

    private static class Coordinate {
        final int x;
        final int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class BikerBikeDistance implements Comparable<BikerBikeDistance> {
        final int biker;
        final int bike;
        final long cost;
        String name;

        public BikerBikeDistance(int biker, int bike, long cost) {
            this.biker = biker;
            this.bike = bike;
            this.cost = cost;
        }

        @Override
        public int compareTo(BikerBikeDistance o) {
            if (cost < o.cost) {
                return -1;
            }
            if (cost > o.cost) {
                return 1;
            }
            return 0;
        }
    }

    private static class Node {
        private LinkedList<Node> connections;
        private int visitedNum;
        private boolean isTerminus;

        public Node() {
            connections = new LinkedList<Node>();
            visitedNum = -999;
            isTerminus = false;
        }

        public Node(boolean terminus) {
            connections = new LinkedList<Node>();
            visitedNum = -999;
            isTerminus = terminus;
        }

        public int getVisited() {
            return visitedNum;
        }

        public void addConnection(Node n) {
            connections.add(n);
        }

        public boolean dfsAndReverse(int v) {
            if (isTerminus) {
                return true;
            }
            visitedNum = v;
            Iterator<Node> i = connections.iterator();
            while (i.hasNext()) {
                Node n = i.next();
                if (n.getVisited() != v) {
                    if (n.dfsAndReverse(v)) {
                        n.addConnection(this);
                        i.remove();
                        return true;
                    }
                }
            }
            return false;
        }
    }
}