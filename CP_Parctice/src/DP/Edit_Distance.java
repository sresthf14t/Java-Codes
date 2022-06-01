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
Given two strings str1 and str2 and below operations that can performed on str1
Find minimum number of edits (operations) required to convert ‘str1’ into ‘str2’.
    Insert
    Remove
    Replace
All of the above operations are of equal cost.
*/
import java.util.*;
import java.io.*;
public class Edit_Distance {
    static int dp[][];
    static String str1,str2;
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        str1=input.next();
        str2=input.next();
        dp=new int[str1.length()+1][str2.length()+1];
        System.out.println(edit_dist(str1.length(),str2.length()));
    }
    
    static int edit_dist(int n, int m) {
        for(int i=0;i<=n;i++) {
            for(int j=0;j<=m;j++) {
                if(i==0) {
                    dp[i][j]=j;
                }
                else if(j==0) {
                    dp[i][j]=i;
                }
                else if(str1.charAt(i-1)==str2.charAt(j-1)) {
                    dp[i][j]=dp[i-1][j-1];
                }
                else {
                    dp[i][j]=1+min(dp[i-1][j],
                            dp[i][j-1],
                            dp[i-1][j-1]);
                }
            }
        }
        return dp[n][m];
    }
    
    static int min(int a,int b,int c) {
        return Math.min(Math.min(a,b), c);
    }
}
