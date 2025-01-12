package hackerRank;
/*

https://www.hackerrank.com/challenges/determining-dna-health/problem?isFullScreen=false&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen


Determining DNA Health

109/563 challenges solved
Rank: 26096|Points: 2542.88
Problem Solving
Problem
Submissions
Leaderboard
Discussions
Editorial
DNA is a nucleic acid present in the bodies of living things. Each piece of DNA contains a number of genes, some of which are beneficial and increase the DNA's total health. Each gene has a health value, and the total health of a DNA is the sum of the health values of all the beneficial genes that occur as a substring in the DNA. We represent genes and DNA as non-empty strings of lowercase English alphabetic letters, and the same gene may appear multiple times as a susbtring of a DNA.

Given the following:

An array of beneficial gene strings, . Note that these gene sequences are not guaranteed to be distinct.
An array of gene health values, , where each  is the health value for gene .
A set of  DNA strands where the definition of each strand has three components, , , and , where string  is a DNA for which genes  are healthy.
Find and print the respective total healths of the unhealthiest (minimum total health) and healthiest (maximum total health) strands of DNA as two space-separated values on a single line.

Input Format

The first line contains an integer, , denoting the total number of genes.
The second line contains  space-separated strings describing the respective values of  (i.e., the elements of ).
The third line contains  space-separated integers describing the respective values of  (i.e., the elements of ).
The fourth line contains an integer, , denoting the number of strands of DNA to process.
Each of the  subsequent lines describes a DNA strand in the form start end d, denoting that the healthy genes for DNA strand  are  and their respective correlated health values are .

Constraints

 the sum of the lengths of all genes and DNA strands
It is guaranteed that each  consists of lowercase English alphabetic letters only (i.e., a to z).
Output Format

Print two space-separated integers describing the respective total health of the unhealthiest and the healthiest strands of DNA.

Sample Input 0

6
a b c aa d b
1 2 3 4 5 6
3
1 5 caaab
0 4 xyz
2 4 bcdybc
Sample Output 0

0 19
Explanation 0

In the diagrams below, the ranges of beneficial genes for a specific DNA on the left are highlighed in green and individual instances of beneficial genes on the right are bolded. The total healths of the  strands are:

image
The total health of caaab is .
image
The total health of xyz is , because it contains no beneficial genes.
image
The total health of bcdybc is .
The unhealthiest DNA strand is xyz with a total health of , and the healthiest DNA strand is caaab with a total health of . Thus, we print 0 19 as our answer.
 */



/*
 * Complete the 'determineDNAHealth' function below.
 *
 * The function is expected to return a LONG_INTEGER.
 * The function accepts following parameters:
 *  1. INTEGER n
 *  2. LIST<STRING> genes
 *  3. LIST<INTEGER> health
 *  4. INTEGER s
 *  5. List<String[]> strands
 */
import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;


class DetermineDNAHealth {
        public static String determineDNAHealth(int n, List<String> genes, List<Integer> health, int s, List<String[]> strands) {
            Map<String, Integer> geneHealthMap = new HashMap<>();
            for (int i = 0; i < n; i++) {
                geneHealthMap.put(genes.get(i), health.get(i));
            }

            long[] healthValues = new long[s];
            for (int i = 0; i < s; i++) {
                int first = Integer.parseInt(strands.get(i)[0]);
                int last = Integer.parseInt(strands.get(i)[1]);
                String dna = strands.get(i)[2];
                long strandHealth = 0;
                for (int j = first; j <= last; j++) {
                    String gene = genes.get(j - 1);
                    if (dna.contains(gene)) {
                        strandHealth += geneHealthMap.get(gene);
                    }
                }
                healthValues[i] = strandHealth;
            }

            return Arrays.stream(healthValues).min().orElse(0) + " " + Arrays.stream(healthValues).max().orElse(0);
        }
        public static void main(String[] args) throws IOException {
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            List<String> genes = IntStream.range(0, n).mapToObj(i -> scanner.next()).collect(Collectors.toList());
            List<Integer> health = IntStream.range(0, n).mapToObj(i -> scanner.nextInt()).collect(Collectors.toList());
            int s = scanner.nextInt();
            List<String[]> strands = IntStream.range(0, s).mapToObj(i -> new String[] {scanner.nextInt() + "", scanner.nextInt() + "", scanner.next()}).collect(Collectors.toList());
            System.out.println(DetermineDNAHealth.determineDNAHealth(n, genes, health, s, strands));
        }
    }
