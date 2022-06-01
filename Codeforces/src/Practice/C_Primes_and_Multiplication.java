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
public class C_Primes_and_Multiplication {
    static long primes[];
    public static void main(String args[]) throws IOException {
        Scanner input=new Scanner(System.in);
        primes=new long[9592];
        sieve(100001);
        long x=input.nextLong();
        long n=input.nextLong();
        System.out.println(solve(x,n));
    }
    public static long solve(long x,long n) {
        
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
    }
    static long power(long x, long y, long p) 
    { 
        // Initialize result 
        long res = 1L;      
         
        // Update x if it is more   
        // than or equal to p 
        x = x % p;  
  
       if (x == 0) return 0; // In case x is divisible by p;    
  
        while (y > 0) 
        { 
            // If y is odd, multiply x 
            // with result 
            if((y & 1)==1) 
                res = (res * x) % p; 
      
            // y must be even now 
            // y = y / 2 
            y = y >> 1;  
            x = (x * x) % p;  
        } 
        return res; 
    } 
}
