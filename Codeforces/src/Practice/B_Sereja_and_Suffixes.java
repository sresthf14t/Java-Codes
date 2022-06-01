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
public class B_Sereja_and_Suffixes {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int m=input.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.nextInt();
        }
        int[] dp=solve(n,arr);
        StringBuilder ans=new StringBuilder("");
        for(int i=0;i<m;i++) {
            int q=input.nextInt();
            ans.append(dp[q-1]+"\n");
        }
        System.out.println(ans);
    }
    
    public static int[] solve(int n,int[] arr) {
        int dp[]=new int[n];
        Set<Integer> hash_set=new HashSet<>();
        dp[n-1]=1;
        hash_set.add(arr[n-1]);
        for(int i=n-2;i>=0;i--) {
            if(!hash_set.contains(arr[i])) {
                dp[i]=dp[i+1]+1;
                hash_set.add(arr[i]);
            }
            else {
                dp[i]=dp[i+1];
            }
        }
        return dp;
    }
}
