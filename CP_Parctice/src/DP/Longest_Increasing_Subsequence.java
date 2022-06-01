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
import java.util.*;
import java.io.*;
public class Longest_Increasing_Subsequence {
    public static void main(String arsg[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.nextInt();
        }
        System.out.println(find_lis(arr,n));
    }
    public static int find_lis(int arr[],int n) {
        int lis[]=new int[n];
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
        int max_lis=-1;
        for(int i=0;i<n;i++) {
            max_lis=Math.max(max_lis,lis[i]);
        }
        return max_lis;
    }
}
