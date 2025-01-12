package hackerRank;

import java.util.Scanner;
public class MorganandaString {


    /*
     * Complete the 'morganAndString' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING a
     *  2. STRING b
     */

    public static String morganAndString(String A, String B) {
        int i = 0, j = 0;
        StringBuffer sb = new StringBuffer();
        while(i < A.length() && j < B.length()) {
            if (A.charAt(i) < B.charAt(j)) {
                sb.append(A.charAt(i++));
            } else if (A.charAt(i) > B.charAt(j)) {
                sb.append(B.charAt(j++));
            } else {
                int x = i, y = j;
                char a = A.charAt(i);
                for(; x < A.length() && y < B.length(); x++,
                        y++) {
                    if (A.charAt(x) != B.charAt(y)) {
                        break;
                    } else if (A.charAt(x) > a) {
                        sb.append(A.substring(i,
                                x)).append(B.substring(j, y));
                        i = x; j = y;
                        a = A.charAt(x);
                    }
                }
                if (x == A.length()) {
                    sb.append(B.charAt(j));
                    j++;
                } else if (y == B.length()) {
                    sb.append(A.charAt(i));
                    i++;
                } else {
                    if (A.charAt(x) < B.charAt(y)) {
                        sb.append(A.charAt(i));
                        i++;
                    } else {
                        sb.append(B.charAt(j));
                        j++;
                    }
                }
            }
        }
        sb.append(A.substring(i)).append(B.substring(j));
        return sb.toString();
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        in.nextLine();
        while(t-- > 0) {
            String A = in.nextLine();
            String B = in.nextLine();
            String sb = MorganandaString.morganAndString(A,B);
            System.out.println(sb);
        }
    }
}