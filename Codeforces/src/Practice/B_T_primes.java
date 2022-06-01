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
public class B_T_primes {
    static long primes[];
    static boolean sieve[];
    static int indx;
    public static void main(String args[]) throws IOException {
        Scanner input=new Scanner(System.in);
        primes=new long[78498];
        indx=0;
        sieve(1000001);
        Set<Long> hashset=new HashSet<>();
        for(int i=0;i<primes.length;i++) {
            hashset.add(primes[i]*primes[i]);
        }
        int n=input.nextInt();
        StringBuilder ans=new StringBuilder("");
        for(int i=0;i<n;i++) {
            long tmp=input.nextLong();
            if(hashset.contains(tmp)) {
                ans.append("YES\n");
            }
            else {
                ans.append("NO\n");
            }
        }
        System.out.println(ans);
    }
    //false for prime number and true for composite number
    public static void sieve(int n) {
        sieve=new boolean[n];
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
}
