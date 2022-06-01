/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Educational_DP_Contest;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class M {
    static int arr[];
    static long dp[][],mod=1000000007L;
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int k=input.nextInt();
        arr=new int[n];
        long sum=0;
        for(int i=0;i<n;i++) {
            arr[i]=input.nextInt();
            sum+=arr[i];
        }
//        if(sum<k) {
//            System.out.println(0);
//            System.exit(0);
//        }
        System.out.println(solve(n,k));
    }
    public static long solve(int n,int k) {
        dp=new long[n][k+1];
        for(int i=0;i<=arr[0];i++) {
            dp[0][i]=1;
        }
        for(int i=1;i<n;i++) {
            dp[i][0]=dp[i-1][0]%mod;
            for(int j=1;j<=arr[i];j++) {
                dp[i][j]=(dp[i][j-1]+dp[i-1][j])%mod;
            }
            for(int j=arr[i]+1;j<=k;j++) {
                dp[i][j]=(dp[i][j-1]+dp[i-1][j]-dp[i-1][j-arr[i]-1])%mod;
            }
        }
        return dp[n-1][k]%mod;
    }
}
