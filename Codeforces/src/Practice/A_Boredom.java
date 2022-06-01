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
public class A_Boredom {
    static long dp[][];
    public static void main(String rags[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        long arr[]=new long[100001];
        for(int i=0;i<n;i++) {
            arr[input.nextInt()]++;
        }
        System.out.println(max_sum(arr, 100001 ,false));
    }
    public static long max_sum(long[] arr,int n,boolean nxt_sel) {
        dp=new long[2][n];   //o->Dont take
        for(int i=1;i<n;i++) {
            dp[0][i]=Math.max(dp[0][i-1],dp[1][i-1]);
            dp[1][i]=dp[0][i-1]+(((long)i)*arr[i]);
        }
        return Math.max(dp[0][n-1],dp[1][n-1]);
    }
}
