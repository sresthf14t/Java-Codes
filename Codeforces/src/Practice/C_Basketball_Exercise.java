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
public class C_Basketball_Exercise {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        long arr[]=new long[n];
        long brr[]=new long[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.nextLong();
        }
        for(int i=0;i<n;i++) {
            brr[i]=input.nextLong();
        }
        System.out.println(solve(n,arr,brr));
    }
    
    public static long solve(int n,long[] arr,long[] brr) {
        long dp[][]=new long[4][n];   //0->not take  1->take
        dp[1][0]=arr[0];
        dp[3][0]=brr[0];
        for(int i=1;i<n;i++) {
            dp[0][i]=Math.max(Math.max(dp[0][i-1],dp[1][i-1]), Math.max(dp[2][i-1],dp[3][i-1]));
            dp[1][i]=Math.max(dp[0][i-1], Math.max(dp[2][i-1],dp[3][i-1]))+arr[i];
            dp[2][i]=Math.max(Math.max(dp[0][i-1],dp[1][i-1]), Math.max(dp[2][i-1],dp[3][i-1]));
            dp[3][i]=Math.max(dp[2][i-1], Math.max(dp[00][i-1],dp[1][i-1]))+brr[i];
        }
        return Math.max(Math.max(dp[0][n-1],dp[1][n-1]), Math.max(dp[2][n-1],dp[3][n-1]));
    }
}
