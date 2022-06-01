/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_612Div2;

/**
 *
 * @author User
 */
import java.util.Scanner;
import java.util.Arrays;
public class B_Hyperset {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int k1=input.nextInt();
        String str[]=new String[n];
        for(int i=0;i<str.length;i++) {
            str[i]=input.next();
        }
        System.out.println(count_sets(str));
    }
    public static int count_sets(String[] str) {
        int count=0;
        for(int i=0;i<str.length-2;i++) {
            for(int j=i+1;j<str.length-1;j++) {
                for(int k=j+1;k<str.length;k++) {
                    if(check(str[i],str[j],str[k])) {
                        count++;
                        break;
                    }
                }
            }
        }
        return count;
    }
    public static boolean check(String a,String b,String c) {
        for(int i=0;i<a.length();i++) {
            if( (a.charAt(i)==b.charAt(i)) && (a.charAt(i)==c.charAt(i)) );
            else if( (a.charAt(i)!=b.charAt(i)) && (a.charAt(i)!=c.charAt(i)) && (b.charAt(i)!=c.charAt(i)) );
            else {
                return false;
            }
        }
        return true;
    }
}
