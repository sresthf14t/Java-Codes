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

//polynomial rolling hash function
//hash(s)=s[0]+s[1]⋅p+s[2]⋅p^2+...+s[n−1]⋅p^(n−1) mod m //p and m are primes

import java.util.*;
import java.io.*;
public class String_Hashing_Single_Hash_Function {
    static long pow[];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        long p=131;
        long m=1000000007;
        
        //Calculating power (p^n%)m
        pow=new long[1000000];
        pow[0]=1;
        for(int i=1;i<pow.length;i++) {
            pow[i]=pow[i-1]*p;
            pow[i]%=m;
        }
        
        String str1=input.next();
        String str2=input.next();
        long hash1=hash(str1,p,m);
        long hash2=hash(str2,p,m);
        System.out.println("Hash 1= "+hash1+"\nHash 2= "+hash2);
        if(hash1==hash2) {
            System.out.println("SAME");
        }
        else {
            System.out.println("DIFFERENT");
        }
    }
    public static long hash(String str,long p,long m) {
        long hash=0;
        for(int i=0;i<str.length();i++) {
            hash+=(str.charAt(i)*pow[i])%m;
            hash%=m;
        }
        return hash;
    }

}
