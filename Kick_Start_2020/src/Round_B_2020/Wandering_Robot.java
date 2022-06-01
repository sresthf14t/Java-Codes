/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Round_B_2020;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class Wandering_Robot {
    static float dp[][];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=1;t<=test;t++) {
            int n=input.nextInt();
            int m=input.nextInt();
            dp=new float[n][m];
            int x1=input.nextInt()-1;
            int y1=input.nextInt()-1;
            int x2=input.nextInt()-1;
            int y2=input.nextInt()-1;
            System.out.println("Case #"+t+": "+solve(n,m,x1,y1,x2,y2));
        }
    }
    public static float solve(int n,int m,int x1, int y1,int x2,int y2) {
        dp[0][0]=1;
        for(int i=1;i<n;i++) {
            if(i>=x1 && 0>=y1 && i<=x2 && 0<=y2) {
                break;
            }
            dp[i][0]=dp[i-1][0]/2;
        }
        for(int i=1;i<m;i++) {
            if(0>=x1 && i>=y1 && 0<=x2 && i<=y2) {
                break;
            }
            dp[0][i]=dp[0][i-1]/2;
        }
        for(int i=1;i<n;i++) {
            for(int j=1;j<m;j++) {
                if(i>=x1 && j>=y1 && i<=x2 && j<=y2) {
                continue;
            }
                if(j==m-1) {
                    dp[i][j]+=dp[i-1][j];
                }
                else {
                    dp[i][j]+=(dp[i-1][j])/2;
                }
                if(i==n-1) {
                    dp[i][j]+=dp[i][j-1];
                }
                else {
                    dp[i][j]+=(dp[i][j-1])/2;
                }
            }
        }
//        for(int i=0;i<n;i++) {
//            for(int j=0;j<m;j++) {
//                System.out.print(dp[i][j]+"\t");
//            }
//            System.out.println();
//        }
        return dp[n-1][m-1];
    }
}
