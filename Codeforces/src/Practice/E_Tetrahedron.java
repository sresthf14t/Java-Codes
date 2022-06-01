/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class E_Tetrahedron {
    static int dp[][];
    static long mod;
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        if(n==1) {
            System.out.println(0);
            System.exit(0);
        }
        mod=1000000007L;
        dp=new int[4][n+1];
//        for(int i=0;i<dp.length;i++) {
//            for(int j=0;j<dp[i].length;j++) {
//                dp[i][j]=-1;
//            }
//        }
        System.out.println(ways(n));
    }
    
    public static int ways(int n) {
        dp[3][0]=1;
        for(int i=1;i<=n;i++) {
            long tmp=((long)dp[1][i-1]+(long)dp[2][i-1]+(long)dp[3][i-1])%mod;
            dp[0][i]=(int)tmp;
            tmp=((long)dp[0][i-1]+(long)dp[2][i-1]+(long)dp[3][i-1])%mod;
            dp[1][i]=(int)tmp;
            tmp=((long)dp[0][i-1]+(long)dp[1][i-1]+(long)dp[3][i-1])%mod;
            dp[2][i]=(int)tmp;
            tmp=((long)dp[0][i-1]+(long)dp[1][i-1]+(long)dp[2][i-1])%mod;
            dp[3][i]=(int)tmp;
        }
        return dp[3][n];
    }
}
