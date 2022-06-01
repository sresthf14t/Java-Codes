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
public class Count_number_of_hops {
    static int dp[];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=1;t<=test;t++) {
            int n=input.nextInt();
            if(n==1) {
                System.out.println(1);
                continue;
            }
            if(n==2) {
                System.out.println(2);
                continue;
            }
            if(n==3) {
                System.out.println(4);
                continue;
            }
            dp=new int[n+1];
            System.out.println(ways(n));
        }
    }
    
    static int ways(int n) {
        dp[0]=dp[1]=1;
        dp[2]=2;
        dp[3]=4;
        for(int i=4;i<=n;i++) {
            dp[i]=dp[i-1]+dp[i-2]+dp[i-3];
        }
        return dp[n];
    }
}
