/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Medium_Practice;

/**
 *
 * @author User
 */
import java.util.Scanner;
public class NUMFACT {
    static Scanner input=new Scanner(System.in);
    static boolean sieve[];
    static int primes[]=new int[78498];
    public static void main(String args[]) {
        sieve=new boolean[1000001];
        Sieve();
        for(int i=0,j=0;i<sieve.length;i++) {
            if(!sieve[i]) {
                primes[j]=i;
                j++;
            }
        }
        int test=input.nextInt();
        for(int t=0;t<test;t++) {
            int q=input.nextInt();
            int fact_count[]=new int[78498];
            for(int Q=0;Q<q;Q++) {   
                int n=input.nextInt();
                for(int i=0;i<primes.length && primes[i]<=n;i++) {
                    //System.out.println(i+" "+primes[i]);
                    while(n%primes[i]==0) {
                        n/=primes[i];
                        fact_count[i]++;
                        //System.out.println("n="+n);
                    }
                }
            }
            int pro=1;
            for(int i=0;i<fact_count.length;i++) {
                pro*=(fact_count[i]+1);
            }
            System.out.println(pro);
        }
    }
    
    public static void Sieve() {
        //false for prime
        sieve[0]=sieve[1]=true;
        for(int i=2;i<sieve.length;i++) {
            if(!sieve[i]) {
                for(int j=2;i*j<sieve.length;j++) {
                    sieve[i*j]=true;
                } 
            }
        }
     }
}
