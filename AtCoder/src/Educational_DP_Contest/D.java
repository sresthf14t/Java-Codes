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
public class D {
    static int n;
    static int capacity;
    static int wei[];
    static long val[];
    static long dp[][];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        n=input.nextInt();
        capacity=input.nextInt();
        wei=new int[n];
        val=new long[n];
        dp=new long[n][(int)capacity+1];
        for(int i=0;i<n;i++) {
            wei[i]=input.nextInt();
            val[i]=input.nextLong();
        }
        for(int i=0;i<dp.length;i++) {
            for(int j=0;j<dp[0].length;j++) {
                dp[i][j]=-1L;
            }
        }
        System.out.println(solve(n-1,capacity));
    }
    public static long solve(int n,int cap) {
        if(n==-1 || cap==0) {
            return 0;
        }
        if(dp[n][cap]==-1L) {
            long tmp=Integer.MIN_VALUE;
            if(cap>=wei[n]) {
                tmp=Math.max(tmp, solve(n-1,cap-wei[n])+val[n]);
            }
            tmp=Math.max(tmp, solve(n-1,cap));
            dp[n][cap]=tmp;
        }
        return dp[n][cap];
    }
}
