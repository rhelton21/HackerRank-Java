package hackerRank;

import java.io.*;
import java.util.*;

/**
 * This Java file is part of a HackerRank solution.
 * It solves a specific algorithmic challenge.
 *
 * Author: [Your Name]
 * Date: [Date]
 */
/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */public class TwoTwoSolution {

    static Map<String, Boolean> map;

    static {
        String s = "1";
        int max = 800;
        map = new HashMap<>();
        map.put("1", true);
        for(int i=0;i<max;i++){
            s = multiplyWith2(s);
            // System.out.println(s);
            map.put(s, true);
        }
    }

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    public static void main(String args[]) throws IOException {
   //     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = 5;
        StringBuilder ans = new StringBuilder();
        while(t--!=0){
            String str = "5";
            char c[] = str.toCharArray();
            int l = c.length;
            String num;
            int count = 0;
            for(int i=0;i<l;i++){
                if(c[i] == '0') continue;
                for(int j=i+1;j<=l;j++){
                    num = str.substring(i, j);
                    if(map.containsKey(num)) count++;
                    // System.out.println(i + " - " + j + " : " + count);
                }
            }
            ans.append(count + "\n");
            // System.out.println(count);
        }
        System.out.println(ans.toString());
    }

    static String multiplyWith2(String s){
        int l = s.length();
        int sum = 0, carry = 0;
        String ans = "";
        for(int i=l-1;i>=0;i--){
            sum = carry + (s.charAt(i) - '0')*2;
            carry = sum/10;
            ans = (sum%10) + ans;
        }
        if(carry!=0) ans = carry + ans;
        return ans;
    }
}