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
public class SQNUMBF {
    static Scanner input=new Scanner(System.in);
    static boolean sieve[];
    static long primes[]=new long[5761455];
    public static void main(String args[]) {
        sieve=new boolean[100000001];
        Sieve();
        primes[0]=2;
        for(int i=3,j=1;i<sieve.length;i+=2) {
            if(!sieve[i]) {
                primes[j]=i;
                j++;
            }
        }
        int test=input.nextInt();
        for(int t=0;t<test;t++) {
            int q=input.nextInt();
            int fact_count[]=new int[65761455];
            long n[]=new long[q];
            for(int i=0;i<q;i++) {
                n[i]=input.nextLong();
            }
            for(int N=0;N<q;N++) {
                boolean cont=false;
                for(int i=0;i<primes.length && primes[i]<=n[N];i++) {

                    //System.out.println(i+" "+primes[i]);
                    while(n[N]%primes[i]==0) {
                        n[N]/=primes[i];
                        fact_count[i]++;
                        //System.out.println("n="+n);
                        if(fact_count[i]>=2) {
                            System.out.println(primes[i]);
                            cont=true;
                            break;
                        }
                    }
                    if(cont) {
                        break;
                    }
                }
                if(cont) {
                    break;
                }
            }
        }
    }
    
    public static void Sieve() {
        //false for prime
        sieve[0]=sieve[1]=true;
        for(int j=2;2*j<sieve.length;j++) {
            sieve[2*j]=true;
        }
        for(int i=3;i<sieve.length;i+=2) {
            if(!sieve[i]) {
                for(int j=2;i*j<sieve.length;j++) {
                    sieve[i*j]=true;
                } 
            }
        }
     }
}
