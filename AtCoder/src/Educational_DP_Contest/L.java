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
public class L {
    static long arr[];
    static long dp[][];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        arr=new long[n];
        dp=new long[n][n];
        long total=0;
        for(int i=0;i<n;i++) {
            arr[i]=input.nextInt();
            total+=arr[i];
        }
        long x=solve(0,n-1);
        long y=total-x;
        System.out.println(x-y);
    }
    public static long solve(int i,int j) {
        if(i==j) {
            return arr[i];
        }
        if(i+1==j) {
            return Math.max(i,j);
        }
        return Math.max(arr[i]+Math.min(solve(i+2,j),solve(i+1,j-1)),arr[j]+Math.min(solve(i+1,j-1),solve(i,j-2)));
    }
}
