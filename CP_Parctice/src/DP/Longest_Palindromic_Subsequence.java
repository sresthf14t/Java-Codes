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
public class Longest_Palindromic_Subsequence {
    static int dp[][];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        String str=input.next();
        dp=new int[str.length()][str.length()];
        System.out.println(lps(str,str.length()));
    }
    public static int lps(String str,int n) {
        dp=new int[n][n];
        for(int i=0;i<n;i++) {
            dp[i][i]=1;
        }
        for(int dist=1;dist<=n;dist++) {
            for(int i=0,j=i+dist;i<n && j<n;i++,j++) {
                if(str.charAt(i)==str.charAt(j)) {
                    dp[i][j]=2+dp[i+1][j-1];
                }
                else {
                    dp[i][j]=Math.max(dp[i+1][j],dp[i][j-1]);
                }
            }
        }
        return dp[0][n-1];
    }
}
