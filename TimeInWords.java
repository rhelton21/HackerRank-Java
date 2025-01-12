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

public class TimeInWords {

    /*
     * Complete the 'timeInWords' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. INTEGER h
     *  2. INTEGER m
     */

    public static String timeInWords(int h, int m) {
        HashMap<Integer, String> words = new HashMap<>();
        ///////////////////////////////////////////////////////
        words.put(1,"one");words.put(2,"two");
        words.put(3,"three");words.put(4,"four");
        words.put(5,"five");words.put(6,"six");
        words.put(7,"seven");words.put(8,"eight");
        words.put(9,"nine");words.put(10,"ten");
        words.put(11,"eleven");words.put(12,"twelve");

        words.put(13,"thirteen");
        words.put(14,"fourteen");
        words.put(15,"fifteen");
        words.put(16,"sixteen");
        words.put(17,"seventeen");
        words.put(18,"eighteen");
        words.put(19,"nineteen");

        ///////////////////////////////////////////////////////

        if (m == 0) {
            return words.get(h) + " o\' clock";
        }
        if(m < 30)
        {
            if(m > 1)
            {
                if(m > 20)
                {
                    return "twenty "+words.get(m-20)+" minutes past "+words.get(h);
                }
                else if(m == 15)
                {
                    return "quarter past "+words.get(h);
                }
                else
                {
                   return words.get(m)+" minutes past "+words.get(h);
                }
            }
            else
            {
                return words.get(m)+" minute past "+words.get((h+1)%12);
            }
        }
        else if(m > 30)
        {
            if(m==45)
            {
                return "quarter to "+words.get((h+1)%12);
            }
            else if(60-m > 20)
            {
                return "twenty "+words.get(60-m-20)+" minutes to "+words.get((h+1)%12);
            }
            else if(60-m < 20){
                return words.get(60-m)+" minutes to "+words.get((h+1)%12);
            }
            else{
                return "twenty minutes to "+words.get((h+1)%12);
            }
        }
        else
        {
            return "half past "+words.get(h);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int h = Integer.parseInt(bufferedReader.readLine().trim());

        int m = Integer.parseInt(bufferedReader.readLine().trim());

        String result = TimeInWords.timeInWords(h, m);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
