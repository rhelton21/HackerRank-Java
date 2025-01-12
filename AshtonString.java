package hackerRank;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class AshtonString {
    public static char ashtonString(String string, int k) {
        char[] arr = string.toCharArray();
         Object[] letters = new Object[26];

        for (int i = 0; i < arr.length; i++) {
            Bucket bucket = (Bucket) letters[(arr[i]) - 97];
            if (bucket == null) {
                bucket = new Bucket(String.valueOf(arr[i]));
                letters[(arr[i]) - 97] = bucket;
            }

            bucket.positions.add(i);
        }

        Stack<Bucket> bucketStack = new Stack<>();

        for (int i = letters.length - 1; i >= 0; i--) {
            if (letters[i] != null) {
                bucketStack.push((Bucket) letters[i]);
                letters[i] = null;
            }
        }

        int count = 0;
        while (!bucketStack.isEmpty()) {
            Bucket b = bucketStack.pop();
//            if (!fastSet.contains(b.str)) {
            //              fastSet.add(b.str);
            if (count + b.str.length() >= k) {
                return b.str.charAt(k - count - 1);
            } else {
                count += b.str.length();
            }
            //        }

            for (int position : b.positions) {
                if (arr.length > position + b.str.length()) {

                    int newCharPosition = position + b.str.length();
                    Bucket bucket = (Bucket) letters[(arr[newCharPosition]) - 97];
                    if (bucket == null) {
                        String currStr = b.str + arr[newCharPosition];
                        bucket = new Bucket(currStr);
                        letters[(arr[newCharPosition]) - 97] = bucket;
                    }

                    bucket.positions.add(position);
                }
            }

            for (int i = letters.length - 1; i >= 0; i--) {
                if (letters[i] != null) {
                    bucketStack.push((Bucket) letters[i]);
                    letters[i] = null;
                }
            }
        }

        return '-';
    }

    public static class Bucket implements Comparable<Bucket> {

        String str;
        List<Integer> positions = new ArrayList<>();

        public Bucket(final String str) {
            this.str = str;
        }

        @Override
        public int compareTo(final Bucket o) {
            return -str.compareTo(o.str);
        }

        @Override
        public int hashCode() {
            return str.hashCode();
        }

        @Override
        public boolean equals(final Object obj) {
            return str.equals(((Bucket) obj).str);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String s = br.readLine();

                int k = Integer.parseInt(br.readLine().trim());

                char res = ashtonString(s, k);
                System.out.println(res);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        br.close();
    }

}




