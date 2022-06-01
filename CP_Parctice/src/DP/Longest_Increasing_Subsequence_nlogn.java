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
O(n logn) algorithm using patience sort. It only gives length of lis
to get lis add pointers to the top of last pile from the current element and
backtrack the pointer to get lis
*/
import java.util.*;
import java.io.*;
public class Longest_Increasing_Subsequence_nlogn {
    static int arr[];
    public static void main(String ars[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        arr=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.nextInt();
        }
        System.out.println(lis(n));
    }
    public static int lis(int n) {
        ArrayList<Integer> top=new ArrayList<>();
        top.add(arr[0]);
        for(int i=1;i<n;i++) {
            if(top.get(top.size()-1)<arr[i]) {
                top.add(arr[i]);
            }
            else {
                int indx=binary_search(top,0,top.size(),arr[i]);
                top.set(indx, arr[i]);
            }
        }
        return top.size();
    }
    public static int binary_search(ArrayList<Integer> arr,int l,int r,int target) {
        int pivot=(l+r)/2;
        if(pivot==0 && arr.get(pivot)>=target) {
            return pivot;
        }
        if(arr.get(pivot)>=target && arr.get(pivot-1)<target) {
            return pivot;
        }
        if(arr.get(pivot)<target) {
            return binary_search(arr,pivot+1,r,target);
        }
        else {
            return binary_search(arr,l,pivot-1,target);
        }
    }
}
