/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DP;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class Codeforces_A_QAQ {
    public static void main(String rags[]) {
        Scanner input=new Scanner(System.in);
        String str=input.next();
        StringBuilder strb=new StringBuilder("");
        for(int i=0;i<str.length();i++) {
            if(str.charAt(i)=='A'||str.charAt(i)=='Q') {
                strb.append(str.charAt(i));
            }
        }
        int Qs[]=new int[strb.length()];
        int count=0;
        for(int i=strb.length()-1;i>=0;i--) {
            if(strb.charAt(i)=='Q') {
                count++;
            }
            Qs[i]=count;
        }
//        System.out.println(strb);
        count=0;
        for(int i=0;i<strb.length()-2;i++) {
            if(strb.charAt(i)=='Q') {
                for(int j=i+1;j<strb.length()-1;j++) {
                    if(strb.charAt(j)=='A') {
                        count+=Qs[j+1];
                    }
                }
            }
        }
        System.out.println(count);
    }
}
