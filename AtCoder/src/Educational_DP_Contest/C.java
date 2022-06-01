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
public class C {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int a[]=new int[n];
        int b[]=new int[n];
        int c[]=new int[n];
        for(int i=0;i<n;i++) {
            a[i]=input.nextInt();
            b[i]=input.nextInt();
            c[i]=input.nextInt();
        }
        System.out.println(solve(n,a,b,c));
    }
    public static int solve(int n,int a[],int b[],int c[]) {
        int dp[][]=new int[3][n];   //0->a  1->b    2->c
        dp[0][0]=a[0];
        dp[1][0]=b[0];
        dp[2][0]=c[0];
        for(int i=1;i<n;i++) {
            dp[0][i]=Math.max(dp[1][i-1],dp[2][i-1])+a[i];
            dp[1][i]=Math.max(dp[0][i-1],dp[2][i-1])+b[i];
            dp[2][i]=Math.max(dp[0][i-1],dp[1][i-1])+c[i];
        }
        return Math.max(dp[0][n-1],Math.max(dp[1][n-1],dp[2][n-1]));
    }
}
