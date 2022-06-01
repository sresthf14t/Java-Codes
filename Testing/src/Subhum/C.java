/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subhum;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class C {
    static int n;
    static long arr[],dp[][][];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        n=input.nextInt();
        arr=new long[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.nextInt();
        }
        dp=new long[n][n][2];
        for(int i=0;i<dp.length;i++) {
            for(int j=0;j<dp[0].length;j++) {
                for(int k=0;k<dp[0][0].length;k++) {
                    dp[i][j][k]=-1;
                }
            }
        }
        System.out.println(solve(0,n-1,false));
    }
    public static long solve(int l,int r,boolean strt) {
        if(l>=r) {
            return 0;
        }
        if(dp[l][r][strt?1:0]!=-1) {
            return dp[l][r][strt?1:0];
        }
        long ans=0;
        if(!strt) {
            ans=Math.max(ans,solve(l+1,r,strt));
            ans=Math.max(ans,solve(l,r-1,strt));
            ans=Math.max(ans,(arr[l]*arr[r])+solve(l+1,r-1,true));
        }
        else {
            ans=Math.max(ans,(arr[l]*arr[r])+solve(l+1,r-1,strt));
        }
        dp[l][r][strt?1:0]=ans;
        return ans;
    }
}
