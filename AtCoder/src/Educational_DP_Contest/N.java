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
public class N {
    static long arr[],dp[][],pre_sum[];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        arr=new long[n];
        pre_sum=new long[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.nextLong();
            if(i==0) {
                pre_sum[i]=arr[i];
            }
            else {
                pre_sum[i]=pre_sum[i-1]+arr[i];
            }
        }
        dp=new long[n][n];
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                dp[i][j]=-1;
            }
        }
        System.out.println(solve(0,n-1));
    }
    public static long solve(int l,int r) {
        if(l==r) {
            return 0;
        }
        if(dp[l][r]!=-1) {
            return dp[l][r];
        }
        long cost=Long.MAX_VALUE;
        for(int i=l;i<r;i++) {
            long cst=solve(l,i)+solve(i+1,r)+(prefix_sum(l,i)+prefix_sum(i+1,r));
            cost=Math.min(cost,cst);
        }
        dp[l][r]=cost;
        return dp[l][r];
    }
    public static long prefix_sum(int l,int r) {
        if(l==0) {
            return pre_sum[r];
        }
        else {
            return pre_sum[r]-pre_sum[l-1];
        }
    }
}
