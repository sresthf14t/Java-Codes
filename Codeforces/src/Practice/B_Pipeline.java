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
public class B_Pipeline {
    public static void main(String args[]) throws IOException {
        Scanner input=new Scanner(System.in);
        long n=input.nextLong();
        long k=input.nextInt();
        System.out.println(solve(n,k));
    }
    public static long solve(long n,long k) {
        if(n<=k) {
            return 1;
        }
        long total=(k*(k+1))/2;
        total-=(k-1);
        if(n>total) {
            return -1L;
        }
        long cnt=0,pipes=1;
        for(long i=k;i>1;i--) {
            if(pipes+i-1<=n) {
                pipes+=i-1;
                cnt++;
            }
            if(n-pipes+2<i) {
                i=n-pipes+2;
            }
            if(pipes==n) {
                break;
            }
        }
        if(pipes==n) {
            return cnt;
        }
        return -1;
    }
}
