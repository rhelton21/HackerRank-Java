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

public class AcmTeam {

    /*
     * Complete the 'acmTeam' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts STRING_ARRAY topic as parameter.
     */

    public static List<Integer> acmTeam(List<String> topic) {
        int maxTopics = 0;
        int maxTeams = 0;
        for (int i = 0; i < topic.size(); i++) {
            for (int j = i + 1; j < topic.size(); j++) {
                int tmp = or(topic.get(i), topic.get(j));
                if (tmp > maxTopics) {
                    maxTopics = tmp;
                    maxTeams = 1;
                } else if (tmp == maxTopics) {
                    maxTeams++;
                }
            }
        }
        int[] ints = {maxTopics, maxTeams};
        List<Integer> list = Arrays.stream(ints).boxed().collect(Collectors.toList());
        return list;
    }

    static int or(String a, String b) {
        int counter = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == '1' || b.charAt(i) == '1') {
                counter++;
            }
        }
        return counter;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        List<String> topic = IntStream.range(0, n).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .collect(toList());

        List<Integer> result = AcmTeam.acmTeam(topic);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
