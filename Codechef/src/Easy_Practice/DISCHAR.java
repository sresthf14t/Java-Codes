/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Easy_Practice;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class DISCHAR {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int t=input.nextInt();
        for(int test=0;test<t;test++) {
            String str=input.next();
            System.out.println(longest_subsequence(str));
        }
    }
    
    public static int check_distinct(String s,int l,int r,char key) {
        for(int i=l;i<=r;i++) {
            if(s.charAt(i)==key) {
                return i;
            }
        }
        return -1;
    }
    
    public static String create_Str(int l,int r, String s) {
        String str="";
        for(int i=l;i<=r;i++) {
            str+=s.charAt(i);
        }
        return str;
    }
    
    public static int longest_subsequence(String str) {
        int len=0;
        int l=0;
        for(int r=1;r<str.length();r++) {
            if(check_distinct(str,l,r-1,str.charAt(r))==-1) {
            }
            else {
                l=check_distinct(str,l,r-1,str.charAt(r))+1;
            }
            if(r-l>len) {
                len=r-l;
            }
            System.out.println(create_Str(l,r,str));
        }
        return len+1;
    }
}
