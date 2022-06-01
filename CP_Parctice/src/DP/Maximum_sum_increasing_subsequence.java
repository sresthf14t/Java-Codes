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
Given an array A of N positive integers. Find the sum of maximum sum 
increasing subsequence of the given array.
*/
import java.util.*;
import java.io.*;
public class Maximum_sum_increasing_subsequence {
    static int dp[];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.nextInt();
        }
        System.out.println(max_sum_subseq(n, arr));
    }
    public static int max_sum_subseq(int n,int arr[]) {
        dp=new int[n];
        for(int i=0;i<n;i++) {
            dp[i]=arr[i];
        }
        for(int i=0;i<n;i++) {
            int sum=0;
            for(int j=i-1;j>=0;j--) {
                if(arr[j]<arr[i]) {
                    sum=Math.max(sum,dp[j]);
                }
            }
            dp[i]+=sum;
        }
        int max=-1;
        for(int i=0;i<n;i++) {
            max=Math.max(max,dp[i]);
        }
        return max;
    }
}
