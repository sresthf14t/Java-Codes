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
public class K {
    static int arr[];
    static boolean dp[];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int k=input.nextInt();
        arr=new int[n];
        dp=new boolean[k+1];
        for(int i=0;i<n;i++) {
            arr[i]=input.nextInt();
            dp[arr[i]]=true;
        }
        if(solve(n,k)) {
            System.out.println("First");
        }
        else {
            System.out.println("Second");
        }
    }
    public static boolean solve(int n,int k) {
        int strt=arr[0];
        for(int i=strt+1;i<=k;i++) {
            for(int j=0;j<n;j++) {
                if(arr[j]>i) {
                    break;
                }
                dp[i]|=!dp[i-arr[j]];
            }
        }
        return dp[k];
    }
}
