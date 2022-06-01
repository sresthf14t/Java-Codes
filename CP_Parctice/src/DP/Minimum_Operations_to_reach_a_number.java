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
You are given a number N. You have to find the number of operations required 
to reach N from 0. You have 2 operations available:
    Double the number
    Add one to the number

*/
import java.util.*;
import java.io.*;
public class Minimum_Operations_to_reach_a_number {
    static int dp[];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=1;t<=test;t++) {
            int n=input.nextInt();
            dp=new int[n+1];
            System.out.println(ways(n));
        }
    }
    
    static int ways(int n) {
        for(int i=1;i<=n;i++) {
            if(i%2==1) {
                dp[i]=1+dp[i-1];
            }
            else {
                dp[i]=1+Math.min(dp[i-1],dp[i/2]);
            }
        }
        return dp[n];
    }
}
