/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_634_Div_3;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class E2_1 {
    static int arr[];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=1;t<=test;t++) {
            int n=input.nextInt();
            arr=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.nextInt();
            }
            System.out.println(lps(0,n-1,false,-1));
        }
        
    }
    public static int lps(int l,int r,boolean tran,int prev) {
        if(l==r) {
           if(tran) {
                if(arr[l]==prev) {
                    return 1;
                }
                else {
                    return 0;
                }
            }
            else {
                return 1;
            } 
        }
        if(arr[l]==arr[r]) {
            if(l+1==r) {
                if(tran) {
                    if(arr[l]==prev) {
                        return 2;
                    }
                    else {
                        return 0;
                    }
                }
                else {
                    return 2;
                }
            }
            if(arr[l]==prev || prev==-1) {
                return 2+lps(l+1,r-1,tran,arr[l]);
            }
            if(arr[l]!=prev && !tran) {
                return 2+lps(l+1,r-1,!tran,arr[l]);
            }
        }
        return Math.max(lps(l+1,r,tran,prev),lps(l,r-1,tran,prev));
    }
}
