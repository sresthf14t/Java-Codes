/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AtCoder_Beginner_Contest_169;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class D {
    static int primes[],freq[];
    public static void main(String args[]) throws IOException {
        Scanner input=new Scanner(System.in);
        long n=input.nextLong();
        primes=new int[78498];
        sieve(1000001);
        int cnt[]=new int[primes.length+1];
        for(int i=0;i<primes.length;i++) {
            while(n%primes[i]==0) {
                n/=primes[i];
                cnt[i]++;
            }
        }
        if(n!=1) {
            cnt[cnt.length-1]++;
        }
        int count=0;
        for(int i=0;i<cnt.length;i++) {
            if(cnt[i]>0) {
                count++;
            }
        }
        freq=new int[count];
        int indx=0;
        for(int i=0;i<cnt.length;i++) {
           if(cnt[i]>0) {
               freq[indx]=cnt[i];
               indx++;
           } 
        }
        System.out.println(solve(0,1));
    }
    public static int solve(int indx,int curr) {
//        System.out.println(indx+" "+curr);
        if(indx==freq.length) {
            return 0;
        }
        int max=Integer.MIN_VALUE;
        max=Math.max(max,solve(indx+1,1));
        if(curr<freq[indx]) {
            max=Math.max(max,solve(indx,curr+1));
        }
        if(curr<=freq[indx]) {
            freq[indx]-=curr;
            max=Math.max(max,1+solve(indx,curr+1));
            freq[indx]+=curr;
        }
        return max;
    }
    //false for prime number and true for composite number
    public static void sieve(int n) {
        boolean sieve[]=new boolean[n];
        int indx=0;
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
