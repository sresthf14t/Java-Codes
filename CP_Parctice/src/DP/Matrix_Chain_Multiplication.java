/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DP;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class Matrix_Chain_Multiplication {
    static int arr[],dp[][];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        arr=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.nextInt();
        }
        dp=new int[n][n];
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                dp[i][j]=-1;
            }
        }
        System.out.println(solve(1,n-1));
    }
    public static int solve(int l,int r) {
        if(l==r) {
            return 0;
        }
        if(dp[l][r]!=-1) {
            return dp[l][r];
        }
        int cost=Integer.MAX_VALUE;
        for(int i=l;i<r;i++) {
            int cst=solve(l,i)+solve(i+1,r)+(arr[l-1]*arr[i]*arr[r]);
            cost=Math.min(cost,cst);
        }
        dp[l][r]=cost;
        return dp[l][r];
    }
}
