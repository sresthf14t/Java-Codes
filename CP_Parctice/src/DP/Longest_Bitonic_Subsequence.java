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
Given an array arr[0 … n-1] containing n positive integers, a subsequence 
of arr[] is called Bitonic if it is first increasing, then decreasing.
Write a function that takes an array as argument and returns the length
of the longest bitonic subsequence.
*/
import java.util.*;
import java.io.*;
public class Longest_Bitonic_Subsequence {
    static int lis[],lds[];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.nextInt();
        }
        find_lis(arr,n);
        find_lds(arr,n);
        System.out.println(Longest_Bitonic_Subseq(n));
    }
    public static void find_lis(int arr[],int n) {
        lis=new int[n];
        for(int i=0;i<n;i++) {
            lis[i]=1;
        }
        for(int i=1;i<n;i++) {
            for(int j=i-1;j>=0;j--) {
                if(arr[i]>arr[j]) {
                    lis[i]=Math.max(lis[i],lis[j]+1);
                }
            }
        }
    }
    
    public static void find_lds(int arr[],int n) {
        lds=new int[n];
        for(int i=0;i<n;i++) {
            lds[i]=1;
        }
        for(int i=n-1;i>=0;i--) {
            for(int j=i+1;j<n;j++) {
                if(arr[i]>arr[j]) {
                    lds[i]=Math.max(lds[i],lds[j]+1);
                }
            }
        }
    }
    public static int Longest_Bitonic_Subseq(int n) {
        int max=-1;
        for(int i=0;i<n;i++) {
            max=Math.max(max,lis[i]+lds[i]);
        }
        return max-1;
    }
}
