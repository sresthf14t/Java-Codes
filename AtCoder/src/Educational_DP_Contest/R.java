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
import java.util.*;
import java.io.*;
public class R {
    static long mod=1000000007L;
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        long k=input.nextLong();
        long arr[][]=new long[n][n];
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                arr[i][j]=input.nextLong();
            }
        }
        long res[][]=new long[n][n];
        power(arr, res, k);
        long sum=0;
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                sum+=res[i][j];
                sum%=mod;
            }
        }
        System.out.println(sum);
    }
    public static void power(long arr[][], long res[][], long n) {
        if(n==1) {
            for(int i=0;i<arr.length;i++) {
                for(int j=0;j<arr.length;j++) {
                    res[i][j]=arr[i][j];
                }
            }
            return;
        }
        power(arr,res,n/2);
        multiply(res,res,res);
        if(n%2==1) {
            multiply(res,arr,res);
        }
    }
    
    static void multiply(long a[][], long b[][], long res[][])  { 
        long mul[][] = new long[a.length][a.length]; 
        for (int i = 0; i < a.length; i++) { 
            for (int j = 0; j < a.length; j++)  { 
                mul[i][j] = 0; 
                for (int k = 0; k < a.length; k++) {
                    mul[i][j] += a[i][k] * b[k][j]; 
                    mul[i][j]%=mod;
                }
            } 
        }
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                res[i][j] = mul[i][j]; 
            }
        }
    } 
}
