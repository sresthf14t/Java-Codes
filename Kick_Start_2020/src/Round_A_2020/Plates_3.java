/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Round_A_2020;

/**
 *
 * @author User
 */
/*
1
3 3 6
3 2 1
3 2 1
3 2 1
*/
import java.util.*;
import java.io.*;
public class Plates_3 {
    static int dp[][],arr[][],sum[][],n,k,p;
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=1;t<=test;t++) {
            n=input.nextInt();
            k=input.nextInt();
            p=input.nextInt();
            arr=new int[n][k];
            for(int i=0;i<n;i++) {
                for(int j=0;j<k;j++) {
                    arr[i][j]=input.nextInt();
                }
            }
            sum=new int[n][k];
            for(int i=0;i<n;i++) {
                sum[i][0]=arr[i][0];
                for(int j=1;j<k;j++) {
                    sum[i][j]=sum[i][j-1]+arr[i][j];
                }
            }
            dp=new int[n][p+1];
            for(int i=0;i<n;i++) {
                for(int j=0;j<=p;j++) {
                    dp[i][j]=-1;
                }
            }
            System.out.println("Case #"+t+": "+solve(0,p));
        }
    }
    public static int solve(int indx,int rem) {
        if(indx==n) {
            return 0;
        }
        if(rem<=0) {
            return 0;
        }
        if(dp[indx][rem]!=-1) {
            return dp[indx][rem];
        }
        int max=solve(indx+1,rem);
        for(int i=0;i<k && i<rem;i++) {
            max=Math.max(max,sum[indx][i]+solve(indx+1,rem-i-1));
        }
        dp[indx][rem]=max;
        return dp[indx][rem];
    }
}
