/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subhum;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class Test1 {
    public static void main(String args[]) throws IOException {
        Scanner input=new Scanner(System.in);
        
        int n=input.nextInt();
        int m=input.nextInt();
        int arr[][]=new int[n][m];
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                arr[i][j]=input.nextInt();
            }
        }
        
        int x=input.nextInt();
        int y=input.nextInt();
        
        if(x==0) {
            System.out.println(top(n,m,arr,x,y));
        }
        else {
            System.out.println(down(n,m,arr,x,y));
        }
    }
    
    public static int top(int n,int m,int arr[][],int x,int y) {
        int dp[][]=new int[n][m];
        
        dp[x][y]=arr[x][y];
        for(int i=1;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(j!=0) {
                    dp[i][j]=Math.max(dp[i][j],arr[i][j]+dp[i-1][j-1]);
                }
                dp[i][j]=Math.max(dp[i][j],arr[i][j]+dp[i-1][j]);
                if(j!=m-1) {
                    dp[i][j]=Math.max(dp[i][j],arr[i][j]+dp[i-1][j+1]);
                }
            }
        }
        int ans=0;
        for(int i=0;i<m;i++) {
            ans=Math.max(ans,dp[n-1][i]);
        }
        
        return ans;
    }
    
    public static int down(int n,int m,int arr[][],int x,int y) {
        int dp[][]=new int[n][m];
        
        dp[x][y]=arr[x][y];
        for(int i=n-2;i>=0;i--) {
            for(int j=0;j<m;j++) {
                if(j!=0) {
                    dp[i][j]=Math.max(dp[i][j],arr[i][j]+dp[i+1][j-1]);
                }
                dp[i][j]=Math.max(dp[i][j],arr[i][j]+dp[i+1][j]);
                if(j!=m-1) {
                    dp[i][j]=Math.max(dp[i][j],arr[i][j]+dp[i+1][j+1]);
                }
            }
        }
        int ans=0;
        for(int i=0;i<m;i++) {
            ans=Math.max(ans,dp[0][i]);
        }
        
        return ans;
    }
    
}
