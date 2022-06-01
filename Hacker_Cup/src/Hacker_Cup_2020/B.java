/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hacker_Cup_2020;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class B {
    static StringBuilder out=new StringBuilder("");
    public static void main(String args[]) throws IOException {
        Scanner input=new Scanner(new File("C:\\Users\\Srest\\Desktop\\Hacker Cup\\Input\\alchemy_input.txt"));
//        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int T=1;T<=test;T++) {
            out.append("Case #"+T+": ");
            int len=input.nextInt();
            String str=input.next();
            solve(len,str);
        }
        Write_to_File(out);
//        System.out.println(ans);
    }
    public static void solve(int len,String str) {
        int a=0;
        for(int i=0;i<len;i++) {
            if(str.charAt(i)=='A') {
                a++;
            }
        }
        if(a==len/2 || a==(len/2)+1) {
            out.append("Y\n");
        }
        else {
            out.append("N\n");
        }
    }
    public static void Write_to_File(StringBuilder ans) {
        PrintWriter outputstream=null;
        try {
        outputstream=new PrintWriter(new FileOutputStream("C:\\Users\\Srest\\Desktop\\Hacker Cup\\Output\\B_final_out.txt",false));
        }
        catch(FileNotFoundException e) {
        System.exit(0);
        }
        outputstream.println(ans);
        outputstream.close();
    }
}
