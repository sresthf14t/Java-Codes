/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Programming_Contest_1_Chapter_Ramayan;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class RPOWER {
    public static void main(String args[]) throws IOException {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            long a=input.nextLong();
            long b=input.nextLong();
            long d=input.nextLong();
            long e=input.nextLong();
            ans.append(solve(a,b,d,e));
            ans.append("\n");
        }
        System.out.println(ans);
    }
    public static long solve(long a,long b,long d,long e) {
        StringBuilder x=(dec_to_bin(a^b));
        StringBuilder y=(dec_to_bin(d));
        StringBuilder z=(dec_to_bin(e));
        while(x.length()!=60) {
            x.insert(0, '0');
        }
        while(y.length()!=60) {
            y.insert(0, '0');
        }
        while(z.length()!=60) {
            z.insert(0, '0');
        }
        StringBuilder ans=new StringBuilder("");
        for(int i=0;i<x.length();i++) {
            if(x.charAt(i)=='1') {
                ans.append('0');
                continue;
            }
            if(y.charAt(i)=='0' && z.charAt(i)=='0') {
                ans.append('1');
            }
            else {
                ans.append('0');
            }
        }
        return bin_to_dec(ans);
    }
    public static StringBuilder dec_to_bin(long n) {
        StringBuilder ans=new StringBuilder("");
        while(n!=0) {
            ans.append(n%2);
            n/=2;
        }
        ans.reverse();
        return ans;
    }
    public static long bin_to_dec(StringBuilder str) {
        long ans=0;
        for(int i=0;i<str.length();i++) {
            if(str.charAt(i)=='1') {
                ans+=(long)Math.pow(2,str.length()-i-1);
            }
        }
        return ans;
    }
}
