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
public class C_Woodcutters {
    static int dp[][];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        long arr[]=new long[n];
        long height[]=new long[n];
        dp=new int[3][n];   //Don't cut,left,right
        for(int i=0;i<dp.length;i++) {
            for(int j=0;j<dp[i].length;j++) {
                dp[i][j]=-1;
            }
        }
        for(int i=0;i<n;i++) {
            arr[i]=input.nextInt();
            height[i]=input.nextInt();
        }
        System.out.println(count(arr, height, n-1, false));
    }
    public static int count(long[] arr,long[] height,int n,boolean prev_cut_lft) { 
        if(n==0) {
            return 1;
        }
        if(n==arr.length-1) {
            return count(arr,height,n-1,false)+1;
        }
        int max=-1;
        //Don't Cut
        if(dp[0][n]==-1) {
            dp[0][n]=count(arr,height,n-1,false);
        }
        max=Math.max(max,dp[0][n]);
        //Left
        if(height[n]<arr[n]-arr[n-1]) {
            if(dp[1][n]==-1) {
                dp[1][n]=count(arr,height,n-1,true)+1;
            }
            max=Math.max(max,dp[1][n]);
        }
        //Right
        if(!prev_cut_lft && height[n]<arr[n+1]-arr[n]) {
            if(dp[2][n]==-1) {
                dp[2][n]=count(arr,height,n-1,false)+1;
            }
            max=Math.max(max,dp[2][n]);
        }
        if(prev_cut_lft && height[n]<arr[n+1]-arr[n]-height[n+1]) {
            if(dp[2][n]==-1) {
                dp[2][n]=count(arr,height,n-1,false)+1;
            }
            max=Math.max(max,dp[2][n]);
        }
        return max;
    }
}
