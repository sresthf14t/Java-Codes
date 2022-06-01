/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Programming_Contest_1_Chapter_Ramayan;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class BHARAT {
    static int n,k;
    static long mod=1000000007L;
    static long dp[][];
    public static void main(String args[]) throws IOException {
        Scanner input=new Scanner(System.in);
        n=input.nextInt();
        k=input.nextInt();
        dp=new long[2001][2001];
        for(int i=0;i<2001;i++) {
            for(int j=0;j<2001;j++) {
                dp[i][j]=-1L;
            }
        }
        System.out.println(solve(0,n));
    }
    public static long solve(int indx,int prev) {
        if(indx==k) {
            return 1;
        }
        if(dp[indx][prev]!=-1) {
            return dp[indx][prev];
        }
        long ways=0;
        if(prev>1) {
            ways+=solve(indx,prev-1);
        }
        ways%=mod;
        ways+=solve(indx+1,prev);
        ways%=mod;
        dp[indx][prev]=ways;
        return ways;
    }
}
