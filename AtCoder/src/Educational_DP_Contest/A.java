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
public class A {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int cost[]=new int[n];
        for(int i=0;i<n;i++) {
            cost[i]=input.nextInt();
        }
        System.out.println(solve(n,cost));
    }
    public static int solve(int n,int[] cost) {
        int dp[]=new int[n];
        dp[1]=Math.abs(cost[0]-cost[1]);
        for(int i=2;i<n;i++) {
            dp[i]=Math.min(dp[i-1]+Math.abs(cost[i]-cost[i-1]),dp[i-2]+Math.abs(cost[i]-cost[i-2]));
        }
        return dp[n-1];
    }
}
