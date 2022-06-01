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
import java.io.*; 
import java.util.*; 
public class H {
    static long dp[][],mod=1000000007L;
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int m=input.nextInt();
        dp=new long[n][m];
        for(int i=0;i<n;i++) {
            String str=input.next();
            for(int j=0;j<m;j++) {
                if(str.charAt(j)=='#') {
                    dp[i][j]=-1;
                }
            }
        }
        System.out.println(solve(n,m));
    }
    public static long solve(int n, int m) {
        for(int i=1;i<n;i++) {
            if(dp[i][0]==-1) {
                break;
            }
            else {
                dp[i][0]=1;
            }
        }
        for(int i=1;i<m;i++) {
            if(dp[0][i]==-1) {
                break;
            }
            else {
                dp[0][i]=1;
            }
        }
        for(int i=1;i<n;i++) {
            for(int j=1;j<m;j++) {
                if(dp[i][j]==-1) {
                    continue;
                }
                if(dp[i-1][j]==-1 && dp[i][j-1]==-1) {
                    dp[i][j]=0;
                }
                else if(dp[i-1][j]==-1) {
                    dp[i][j]=dp[i][j-1];
                }
                else if(dp[i][j-1]==-1) {
                    dp[i][j]=dp[i-1][j];
                }
                else {
                    dp[i][j]=(dp[i-1][j]+dp[i][j-1])%mod;
                }
            }
        }
        return dp[n-1][m-1];
    }
}
