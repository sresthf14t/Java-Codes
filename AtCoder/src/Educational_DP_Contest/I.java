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
public class I {
    static double arr[],dp[][];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        arr=new double[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.nextFloat();
        }
        dp=new double[n+1][n+1];
        for(int i=0;i<=n;i++) {
            for(int j=0;j<=n;j++) {
                dp[i][j]=-1;
            }
        }
        System.out.println(String.format("%.10f", solve(n-1,0,0)));
    }
    public static double solve(int n,int heads,int tails) {
        if(n==-1) {
            if(heads>tails) {
                return 1;
            }
            return 0;
        }
        if(dp[n][heads]!=-1) {
            return dp[n][heads];
        }
        dp[n][heads] = (solve(n-1,heads+1,tails)*arr[n])+(solve(n-1,heads,tails+1)*(1.0-arr[n]));
        return dp[n][heads];
    }
}
