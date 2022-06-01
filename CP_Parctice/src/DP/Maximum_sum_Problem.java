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
Given a number n, we can divide it in only three parts n/2, n/3 and n/4 
(we will consider only integer part). The task is to find the maximum sum we 
can make by dividing number in three parts recursively and summing up them 
together.
*/
import java.util.*;
import java.io.*;
public class Maximum_sum_Problem {
    static int dp[];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=1;t<=test;t++) {
            int n=input.nextInt();
            dp=new int[n+1];
            dp[0]=0;
            dp[1]=1;
            dp[2]=2;
            dp[3]=3;
            dp[4]=4;
            for(int i=5;i<=n;i++) {
                dp[i]=-1;
            }
            max_sum(n);
            System.out.println(max_sum(n));
        }
    }
    
    static int max_sum(int n) {
        
        if(dp[n/2]==-1) {
            dp[n/2]=max_sum(n/2);
        }
        if(dp[n/3]==-1) {
            dp[n/3]=max_sum(n/3);
        }
        if(dp[n/4]==-1) {
            dp[n/4]=max_sum(n/4);
        }
        if(dp[n]==-1) {
            dp[n]=Math.max(n,Math.max(dp[n/2],n/2)+Math.max(dp[n/3],n/3)+Math.max(dp[n/4],n/4));
        }
        return dp[n];
    }
}
