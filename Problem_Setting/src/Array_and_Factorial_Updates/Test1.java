/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Array_and_Factorial_Updates;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class Test1 {
    static long fact[];
    static boolean sieve[];
    public static void main(String args[]) throws IOException {
        int max=0;
        for(int i=9;i<=9;i++) {
            int val=(int)(Math.pow(10, i));
            int tmp=count(10000,val);
            max=Math.max(max,tmp);
            System.out.println(val+" "+tmp);
        }
        System.out.println("\nMax="+max);
    }
    public static int count(int n,int mod) {
        int max=0;
        fact=new long[100*n];
        fact[0]=1;
        for(int i=1;i<n;i++) {
            long fac=1;
            for(int j=1;j<=i;j++) {
                if(fac==0) {
                    break;
                }
                fac*=j;
                fac%=mod;
            }
            fact[i]=fac;
        }
        
        for(int i=3;i<n;i++) {
            HashSet<Long> set=new HashSet<>();
            long val=i;
            int cnt=0;
            while(val!=1) {
                if(val>=fact.length) {
                    val=0;
                }
                set.add(val);
                val=fact[(int)val];
                cnt++;
            }
            System.out.println(i+" "+cnt);
            max=Math.max(max,cnt);
        }
        return max;
    }
}
