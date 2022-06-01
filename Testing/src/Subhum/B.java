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
public class B {
    static int n,total,iv[],pages[],dp[][];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        n=input.nextInt();
        iv=new int[n];
        pages=new int[n];
        total=input.nextInt();
        for(int i=0;i<n;i++) {
            iv[i]=input.nextInt();
        }
        for(int i=0;i<n;i++) {
            pages[i]=input.nextInt();
        }
        dp=new int[n][total+1];
        for(int i=0;i<dp.length;i++) {
            for(int j=0;j<dp[0].length;j++) {
                dp[i][j]=-1;
            }
        }
        System.out.println(solve(0,total));
    }
    public static int solve(int indx,int rem) {
        if(indx==n) {
            return 0;
        }
        if(dp[indx][rem]!=-1) {
            return dp[indx][rem];
        }
        int max=0;
        max=Math.max(max,solve(indx+1,rem));
        if(2*pages[indx]<=rem) {
            max=Math.max(max,iv[indx]+solve(indx+1,rem-2*pages[indx]));
        }
        dp[indx][rem]=max;
        return max;
    }
}
