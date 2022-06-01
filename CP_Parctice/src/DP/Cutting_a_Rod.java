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
Given a rod of length n inches and an array of prices that contains prices 
of all pieces of size smaller than n. Determine the maximum value obtainable
by cutting up the rod and selling the pieces.
*/
import java.util.*;
import java.io.*;
public class Cutting_a_Rod {
    static int dp[][];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int length=input.nextInt();
        int n=input.nextInt();
        int arr[]=new int[n];
        int values[]=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.nextInt();
        }
        for(int i=0;i<n;i++) {
            values[i]=input.nextInt();
        }
        System.out.println(max_sum(length,arr,values,n));
    }
    public static int max_sum(int length,int arr[],int values[],int n) {
        dp=new int[length+1][n];
        for(int i=1;i<=length;i++) {
            for(int j=0;j<n;j++) {
                if(j==0) {
                    dp[i][j]=(i/arr[j])*values[j];
                }
                else if(arr[j]>i) {
                    dp[i][j]=dp[i][j-1];
                }
                else {
                    dp[i][j]=Math.max(dp[i-arr[j]][j]+values[j],dp[i][j-1]);
                }
            }
        }
        return dp[length][n-1];
    }
}
