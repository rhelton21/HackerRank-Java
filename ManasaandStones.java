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

public class ManasaandStones {

    /*
     * Complete the 'stones' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER a
     *  3. INTEGER b
     */
    public static TreeSet<Integer> set = new TreeSet<>();
    public static HashMap<String, Boolean> map = new HashMap<>();

    public static List<Integer> stones(int n, int a, int b) {
        // Write your code here
        map.clear();
        set.clear();
        if (n == 0) {
            return new ArrayList<Integer>();
        }
        compute(0, a, b, n-1);
        compute(0, a, b, n-1);
        int[] result = new int[set.size()];
        int i = 0;
        while (set.size() > 0) {
            result[i] = set.pollFirst();
            i++;
        }
        List<Integer> list = Arrays.stream(result).boxed().collect(Collectors.toList());
        return list;
    }

    public static void compute(int num, int a, int b, int n) {
        if (map.containsKey("" + num + "," + n)) {
            return;
        }

        if (n == 0) {
            set.add(num);
            return;
        } else {
            map.put("" + num + "," + n, true);
        }

        compute(num + b, a, b, n-1);
        compute(num + a, a, b, n-1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int T = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, T).forEach(TItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                int a = Integer.parseInt(bufferedReader.readLine().trim());

                int b = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> result = ManasaandStones.stones(n, a, b);

                bufferedWriter.write(
                        result.stream()
                                .map(Object::toString)
                                .collect(joining(" "))
                                + "\n"
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
