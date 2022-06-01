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
/*
Given weights and values of n items, put these items in a knapsack of capacity W 
to get the maximum total value in the knapsack. In other words, given two 
integer arrays val[0..n-1] and wt[0..n-1] which represent values and weights 
associated with n items respectively. Also given an integer W which represents
knapsack capacity, find out the maximum value subset of val[] such that sum of
the weights of this subset is smaller than or equal to W. You cannot break an 
item, either pick the complete item, or don’t pick it (0-1 property).
*/
import java.util.*;
import java.io.*;
public class _0_1_Knapsack_Problem {
    static int dp[][];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        //Weight & value
        int n=input.nextInt();
        int weight[]=new int[n];
        int value[]=new int[n];
        for(int i=0;i<n;i++) {
            weight[i]=input.nextInt();
        }
        for(int i=0;i<n;i++) {
            value[i]=input.nextInt();
        }
        int capacity=input.nextInt();
        dp=new int[n+1][capacity+1];
        for(int i=0;i<n+1;i++) {
            for(int j=0;j<capacity+1;j++) {
                dp[i][j]=-1;
            }
        }
        System.out.println(knapsack(weight ,value ,n ,capacity));
    }
    
    public static int knapsack(int weight[],int value[],int n,int capacity) {
        if(n==0 || capacity==0) {
            dp[n][capacity]=0;
            return dp[n][capacity];
        }
        if(weight[n-1]>capacity) {
            if(dp[n][capacity]==-1) {
                dp[n][capacity]=knapsack(weight,value,n-1,capacity);
            }
            return dp[n][capacity];
        }
        if(dp[n][capacity]==-1) {
            dp[n][capacity]=Math.max(knapsack(weight,value,n-1,capacity-weight[n-1])+value[n-1],knapsack(weight,value,n-1,capacity));
        }
        return dp[n][capacity];
    }
}
