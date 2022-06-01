/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package April_Challenge_2020_Division_1;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
import java.math.*;
public class STRNO {
    static boolean sieve[];
    static int primes[];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        primes=new int[664579];
        sieve=new boolean[10000001];
        sieve(10000001);
        int test=input.nextInt();
        for(int t=1;t<=test;t++) {
            int x=input.nextInt();
            int k=input.nextInt();
            int sum=0;
            for(int i=0;i<primes.length;i++) {
                while(x%primes[i]==0) {
                    x/=primes[i];
                    sum++;
                }
                if(sum>=k || x==1) {
                    break;
                }
            }
            if(x!=1 && is_prime(x)) {
                sum++;
            }  
            if(sum>=k) {
                System.out.println(1);
            }
            else {
                System.out.println(0);
            }
        }
    }
    
    
    //false for prime number and true for composite number
    public static void sieve(int n) {
        int k=-1;
        for(int i=2;i<n;i++) {
            if(!sieve[i]) {
                k++;
                primes[k]=i;
                for(int j=2*i;j<n;j=j+i) {
                    sieve[j]=true;
                }
            }
        }
    }
    
    public static boolean is_prime(int n) {
        int lim=(int)Math.sqrt(n);
        for(int i=2;i<=lim;i++) {
            if(n%i==0) {
                return false;
            }
        }
        return true;
    }
}
