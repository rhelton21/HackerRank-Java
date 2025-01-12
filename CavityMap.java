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

public class CavityMap {

    /*
     * Complete the 'cavityMap' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts STRING_ARRAY grid as parameter.
     */

    public static List<String> cavityMap(List<String> list) {

        String[] grid = list.toArray(new String[0]);
        for(int i = 1; i < grid.length - 1; i++)
        {
            for(int j = 1; j < grid[i].length() - 1; j++)
            {
                Character c = grid[i].charAt(j);
                Character cT = grid[i - 1].charAt(j);
                Character cB = grid[i + 1].charAt(j);
                Character cL = grid[i].charAt(j - 1);
                Character cR = grid[i].charAt(j + 1);

                if(cT == 'X' || cB == 'X' || cL == 'X' || cR == 'X') continue;

                if(c.charValue() > cT.charValue() && c.charValue() > cB.charValue() && c.charValue() > cL.charValue() && c.charValue() > cR.charValue())
                    grid[i] = grid[i].substring(0, j) + 'X' + grid[i].substring(j + 1);
            }
        }

        List<String> gridList = Arrays.asList(grid);
        return gridList;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> grid = IntStream.range(0, n).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .collect(toList());

        List<String> result = CavityMap.cavityMap(grid);

        bufferedWriter.write(
                result.stream()
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
