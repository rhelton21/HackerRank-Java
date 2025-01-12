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

class SuperFunctionalStrings {

    /*
     * Complete the 'superFunctionalStrings' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */

    public static long superFunctionalStrings(String s) {
        String subs = "";
        long sum = 0;
        long count = 0;
        List<String> distSubs = new ArrayList<String>();
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < s.length() - i; j++) {
                subs = s.substring(j, s.length() - i);
                if (!distSubs.contains(subs)) {
                    distSubs.add(subs);
                    count = subs.chars().distinct().count();
                    sum = (long) (sum + Math.pow(subs.length(), count));
                }
            }
        }
        sum = sum % 1000000007;
        System.out.println(sum);
        return sum;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
 //       BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String s = bufferedReader.readLine();

                long result = superFunctionalStrings(s);
                System.out.println(String.valueOf(result));
 //               bufferedWriter.write(String.valueOf(result));
 //               bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
//        bufferedWriter.close();
    }
}
