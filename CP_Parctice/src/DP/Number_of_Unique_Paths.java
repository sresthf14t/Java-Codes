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
Given a M X N matrix with your initial position at top-left cell, 
find the number of possible unique paths to reach the bottom right 
cell of the matrix from the initial position.
Note: Possible moves can be either down or right at any point in time,
i.e., we can move to matrix[i+1][j] or matrix[i][j+1] from matrix[i][j].
*/
import java.util.*;
import java.io.*;
public class Number_of_Unique_Paths {
    static int dp[][];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=1;t<=test;t++) {
            int n=input.nextInt();
            int m=input.nextInt();
            dp=new int[n][m];
            System.out.println(paths(n,m));
        }
    }
    static int paths(int n,int m) {
        for(int i=0;i<n;i++) {
            dp[i][0]=1;
        }
        for(int i=0;i<m;i++) {
            dp[0][i]=1;
        }
        for(int i=1;i<n;i++) {
            for(int j=1;j<m;j++) {
                dp[i][j]=dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[n-1][m-1];
    }
}
