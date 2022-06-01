/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package String_Algorithms;

/**
 *
 * @author User
 */

//To search for a given substring in a given String[or a given pattern in a String] in O(S+T) [S->substring length   T->String length]

import java.util.*;
import java.io.*;
public class Rabin_Karp_Algorithm_string_matching {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        String s=input.next();  //Pattern
        String t=input.next();  //Text String
        System.out.println(Rabin_Karp(s,t));
    }
    public static boolean Rabin_Karp(String s,String t) {   //|t|>=|s|
        long p=131;
        long m=1000000007;
        long s_hash=0;
        long p_inv=Inverse(p,m);
        //Calculating power (p^n%)m
        long pow[]=new long[1000000];
        pow[0]=1;
        
        for(int i=1;i<pow.length;i++) {
            pow[i]=pow[i-1]*p;
            pow[i]%=m;
        }
        
        for(int i=0;i<s.length();i++) {
            s_hash+=(s.charAt(i)*pow[i])%m;
            s_hash%=m;
        }
        long t_hash=0;
        for(int i=0;i<s.length();i++) {
            t_hash+=(t.charAt(i)*pow[i])%m;
            t_hash%=m;
        }
        if(t_hash==s_hash) {
            if(check(s,t,s.length()-1)) {
                return true;
            }
        }
        for(int i=s.length();i<t.length();i++) {
            t_hash-=t.charAt(i-s.length());
            t_hash*=p_inv;
            t_hash%=m;
            t_hash+=(t.charAt(i)*pow[s.length()-1])%m;
            t_hash%=m;
//            System.out.println(i+" "+s_hash+" "+t_hash);
            if(t_hash==s_hash) {
                if(check(s,t,i)) {
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean check(String s,String t,int end) {
        int strt=end-s.length()+1;
        for(int i=0;i<s.length();i++) {
            if(s.charAt(i)!=t.charAt(i+strt)) {
                return false;
            }
        }
        return true;
    }
    
    
    public static long Inverse(long a,long n) {
        long q,r1=n,r2=a,r,t1=0,t2=1,t;
        while(true) {
            q=r1/r2;
            r=r1%r2;
            t=t1-(q*t2);
            r1=r2;
            r2=r;
            t1=t2;
            t2=t;
            if(r2==0) {
                    break;
            }
        }
        if(r1!=1) {
                return -1;
        }
        t1%=n;
        if(t1<0) {
                t1+=n;
        }
        return t1;
    }
}
