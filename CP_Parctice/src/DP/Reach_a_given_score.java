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
Consider a game where a player can score 3 or 5 or 10 points in a move. 
Given a total score n, find number of distinct combinations to reach the
given score.
*/
import java.util.*;
import java.io.*;
public class Reach_a_given_score {
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
        dp[0]=1;
        for(int i=3;i<=n;i++) {
            dp[i]+=dp[i-3];
        }
        for(int i=5;i<=n;i++) {
            dp[i]+=dp[i-5];
        }
        for(int i=10;i<=n;i++) {
            dp[i]+=dp[i-10];
        }
        return dp[n];
    }
}
