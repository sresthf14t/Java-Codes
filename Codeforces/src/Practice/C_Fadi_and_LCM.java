/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class C_Fadi_and_LCM {
    static long primes[],product;
    static ArrayList<Long> arrli;
    public static void main(String args[]) throws IOException {
        Scanner input=new Scanner(System.in);
        long n=input.nextLong();
        primes=new long[78498];
        sieve(1000001);
        arrli=new ArrayList<>();
        for(int i=0;i<primes.length;i++) {
            long pro=1;
            while(n%primes[i]==0) {
                n/=primes[i];
                pro*=primes[i];
            }
            if(pro!=1) {
                arrli.add(pro);
            }
        }
        if(n!=1) {
            arrli.add(n);
        }
        product=1;
        for(int i=0;i<arrli.size();i++) {
            product*=arrli.get(i);
        }
        long a=solve(0,1);
        long b=product/a;
        System.out.println(a+" "+b);
    }
    public static long solve(int indx,long a) {
//        System.out.println(indx+" "+a);
        if(indx==arrli.size()) {
            long b=product/a;
            return Math.max(a,b);
        }
        long min=Long.MAX_VALUE;
        min=Math.min(min,solve(indx+1,a*arrli.get(indx)));
        min=Math.min(min,solve(indx+1,a));
        return min;
    }
    //false for prime number and true for composite number
    public static void sieve(int n) {
        int indx=0;
        boolean sieve[]=new boolean[n];
        for(int i=2;i<n;i++) {
            if(!sieve[i]) {
                primes[indx]=i;
                indx++;
                for(int j=2*i;j<n;j=j+i) {
                    sieve[j]=true;
                } 
            } 
        }
//        System.out.println(indx);
    }
}
