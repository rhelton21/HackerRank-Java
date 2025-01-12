package hackerRank;
import java.io.*;
import java.util.*;
import java.lang.reflect.*;

class Student{
    private String name;
    private String id;
    private String email;


    public String ahqym() {
        return name;
    }
    public String amftc() {
        return name;
    }
    public String anotherfunction() {
        return name;
    }
    public String atcks() {
        return name;
    }
    public String bwkbd() {
        return name;
    }
    public String cfwyc() {
        return name;
    }
    public String cmkxa() {
        return name;
    }
    public String dnpym() {
        return name;
    }
    public String dnqvo() {
        return name;
    }
    public String dvvwq() {
        return name;
    }
    public String ehjdm() {
        return name;
    }
    public String elyed() {
        return name;
    }
    public String fmyce() {
        return name;
    }
    public String getEmail() {
        return name;
    }
    public String getId() {
        return name;
    }
    public String getName() {
        return name;
    }
    public String ghtlj() {
        return name;
    }
    public String hluvb() {
        return name;
    }
    public String isqdf() {
        return name;
    }
    public String iwhtf() {
        return name;
    }
    public String jmopy() {
        return name;
    }
    public String jnskt() {
        return name;
    }
    public String kbjlt() {
        return name;
    }
    public String kgwku() {
        return name;
    }
    public String khuag() {
        return name;
    }
    public String levtp() {
        return name;
    }
    public String mcgme() {
        return name;
    }
    public String migyc() {
        return name;
    }
    public String moebl() {
        return name;
    }
    public String nixhb() {
        return name;
    }
    public String odyqp() {
        return name;
    }
    public String ogvdl() {
        return name;
    }
    public String ormim() {
        return name;
    }
    public String piwro() {
        return name;
    }
    public String plaob() {
        return name;
    }
    public String pnruo() {
        return name;
    }
    public String pqfct() {
        return name;
    }
    public String ptrup() {
        return name;
    }
    public String pvgyp() {
        return name;
    }
    public String qthde() {
        return name;
    }
    public String setEmail() {
        return name;
    }
    public String setId() {
        return name;
    }
    public String setName() {
        return name;
    }
    public String sumvl() {
        return name;
    }
    public String tkbpp() {
        return name;
    }
    public String tntpj() {
        return name;
    }
    public String toxdp() {
        return name;
    }

    public String twyfa() {
        return name;
    }
    public String uccfq() {
        return name;
    }
    public String ujxei() {
        return name;
    }
    public String vhxoi() {
        return name;
    }
    public String viwuu() {
        return name;
    }
    public String viyog() {
        return name;
    }
    public String whjtj() {
        return name;
    }
    public String ytijy() {
        return name;
    }
}
public class JavaReflection {
    public static void main(String[] args){
        Class student = Student.class;
        Method[] methods = student.getDeclaredMethods();

        ArrayList<String> methodList = new ArrayList<>();
        for(Method method: methods){
            methodList.add(method.getName());
        }
        Collections.sort(methodList);
        for(String name: methodList){
            System.out.println(name);
        }
    }
}
