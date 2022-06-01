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
public class Longest_Palindromic_Substring {
    static String str;
    static boolean dp[][];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        str=input.next();
        System.out.println(solve());
    }
    public static String solve() {
        int n=str.length();
        dp=new boolean[n][n];
        for(int i=0;i<n;i++) {
            dp[i][i]=true;
        }
        for(int i=0;i<n-1;i++) {
            if(str.charAt(i)==str.charAt(i+1)) {
                dp[i][i+1]=true;
            }
        }
        for(int d=2;d<n;d++) {
            for(int i=0;i<n-d;i++) {
                if(str.charAt(i)==str.charAt(i+d)) {
                    dp[i][i+d]=dp[i+1][i+d-1];
                }
            }  
        }
        int max_len=0,l=-1,r=-1;
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(dp[i][j]) {
                    if(j-i+1>max_len) {
                        max_len=j-i+1;
                        l=i;
                        r=j;
                    }
                }
            }
        }
        StringBuilder pal=new StringBuilder("");
        for(int i=l;i<=r;i++) {
            pal.append(str.charAt(i));
        }
        return ""+pal;
    }
}
