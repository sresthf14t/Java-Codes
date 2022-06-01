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
public class Largest_Sum_Contiguous_Subarray {
    static long arr[];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        arr=new long[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.nextLong();
        }
        System.out.println(solve());
    }
    public static long solve() {
        long max_sum=Integer.MIN_VALUE,sum=0;
        for(int i=0;i<arr.length;i++) {
            sum+=arr[i];
            max_sum=Math.max(max_sum,sum);
            if(sum<0) {
                sum=0;
            }
        }
        return max_sum;
    }
}
