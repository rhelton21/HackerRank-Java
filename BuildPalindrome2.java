package hackerRank;


import java.util.ArrayList;
import java.util.Scanner;

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
 */public class BuildPalindrome2 {
/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();

        ArrayList<String> ta=new ArrayList<String>();

        for(int x=0;x<t;++x){

            String s1=sc.next();
            String s2=sc.next();

            int c=0;
            for(int i=0;i<s1.length();++i){
                if(s2.contains(""+s1.charAt(i))){
                    ++c;
                    break;
                }
            }

            if(c==0){
                ta.add("-1");
            }
            else{

                String fst="";
                int len=0;

                for(int v=1;v<=s1.length();++v){
                    // System.out.println("\nv: "+v);

                    for(int i=0;i<s1.length();++i){
                        // System.out.println("\ti: "+i);

                        int p1=i;
                        int p2=v+p1;
                        // System.out.println("\t\tp1: "+p1+"\tp2: "+p2);

                        if(p2<=s1.length()){
                            String st=s1.substring(p1, p2);
                            // System.out.println("\t\tst: "+st);

                            String sr=reverse(st);
                            // System.out.println("\t\tsr: "+sr);

                            if(s2.contains(sr)){

                                int iye=p1+st.length();
                                String s1e=s1.substring(p2);
                                // System.out.println("11 "+s1e);

                                int cd=s1.length();

                                while(s1e.length()!=0){
                                    boolean flag=isPalin(s1e);
                                    if(flag==true){
                                        break;
                                    }
                                    else{
                                        s1e=s1.substring(iye, --cd);
                                        // System.out.println("12 "+s1e);
                                    }
                                }
                                // System.out.println("\ns1e: "+s1e+"\n");

                                int ize=s2.lastIndexOf(sr);
                                String s2e=s2.substring(0, ize);
                                // System.out.println("21 "+s2e);

                                int ce=0;

                                while(s2e.length()!=0){
                                    boolean flag=isPalin(s2e);
                                    if(flag==true){
                                        break;
                                    }
                                    else{
                                        s2e=s2.substring(++ce, ize);
                                        // System.out.println("22 "+s2e);
                                    }
                                }
                                // System.out.println("\ns2e: "+s2e+"\n");

                                String f="";
                                if(s1e.length()==0 && s2e.length()==0){
                                    f=st+sr;
                                }
                                else if(s1e.length()==0 || s2e.length()==0){
                                    String ct=(s1e.length()==0)?s2e:s1e;
                                    f=st+ct+sr;
                                }
                                else if(s1e.length()==s2e.length()){
                                    int zc=s1e.compareTo(s2e);
                                    String et=(zc<0)?s1e:s2e;
                                    f=st+et+sr;
                                }
                                else{
                                    String ct=(s1e.length()>s2e.length())?s1e:s2e;
                                    f=st+ct+sr;
                                }
                                // System.out.println("\t\tf: "+f);

                                int lent=f.length();
                                if(lent>0 && lent>len){
                                    len=lent;
                                    fst=f;
                                }
                                else if(lent>0 && lent==len){
                                    int zc=f.compareTo(fst);
                                    fst=(zc<0)?f:fst;
                                }
                            }
                        }
                    }
                }
                System.out.println(fst);
                ta.add(fst);
            }
        }

        for(int z=0;z<ta.size();++z){
            System.out.println(ta.get(z));
        }
    }

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    public static String reverse(String s){
        StringBuilder sb=new StringBuilder(s);
        sb=sb.reverse();
        return (new String(sb));
    }

/**
 * Method description:
 * - Explain what this method does.
 * - List input parameters.
 * - Describe expected return values.
 */    public static boolean isPalin(String s){
        boolean flag=true;

        for(int i=0;i<s.length()/2;++i){
            char chL=s.charAt(i);
            char chR=s.charAt(s.length()-i-1);
            if(chL!=chR){
                flag=false;
                break;
            }
        }

        return flag;
    }
}
