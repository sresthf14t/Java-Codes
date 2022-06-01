/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DP;

/**
 *
 * @author User
 */
/*
Given a value N, if we want to make change for N cents, and we have infinite
supply of each of S = { S1, S2, .. , Sm} valued coins, how many ways can we
make the change? The order of coins doesn’t matter.
*/
import java.util.*;
import java.io.*;
public class Coin_Change {
    static int dp[];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int arr[]=new int[n];
        int max=-1;
        for(int i=0;i<n;i++) {
            arr[i]=input.nextInt();
            max=Math.max(max,arr[i]);
        }
        int target=input.nextInt();
        System.out.println(ways(arr,target));
    }
    public static int ways(int[] arr,int target) {
        dp=new int[target+1];
        dp[0]=1;
        for(int i=0;i<arr.length;i++) {
            for(int j=arr[i];j<=target;j++) {
                dp[j]+=dp[j-arr[i]];
            }
        }
        return dp[target];
    }
}
