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
 */public class ReverseShuffleMerge {
/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    static public class CharData {
        int total;
        int skipped;
        int taken;
        boolean hasToTake(){
            return 2*skipped == total;
        }
        boolean hasToSkip(){
            return 2*taken == total;
        }
/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */        void putBack(){
            taken--;
            skipped++;
        }
    }
/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = new
                StringBuilder(in.nextLine()).reverse().toString();
        CharData cd[] = new CharData['z' - 'a' + 1];
        for(int i=0;i<cd.length;i++){
            cd[i]=new CharData();
        }
        for (int i = 0; i < s.length(); i++) {
            cd[s.charAt(i)-'a'].total++;
        }
        char [] r = new char[s.length()/2];
        int ri=0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            CharData di = cd[ch-'a'];
            if(di.hasToSkip()){
                di.skipped++;
            }else {
                while(ri>0 && r[ri-1]>ch &&
                        !cd[r[ri-1]-'a'].hasToTake()){
                    cd[r[--ri]-
                            'a'].putBack();
                }
                r[ri++]=ch;
                di.taken++;
            }
        }
        System.out.println(new String(r));
        in.close();
    }
}