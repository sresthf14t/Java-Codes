/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Baltic_Olympiad_in_Informatics_2020_Day_1;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class A {
    static Scanner input=new Scanner(System.in);
    public static void main(String args[]) {
        int test=input.nextInt();
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            long n=input.nextLong();
            solve(n);
        }
        System.out.println(ans);
    }
    public static void solve(long n) {
        long curr=-1;
        long l=1,r=(n-1),ans=-1;
        int in;
        while(l<=r) {
            long mid=(l+r)/2;
            long lft=get_l(n,mid);
            long rgt=get_r(n,mid);
//            System.out.println(l+" "+r+" "+mid+" "+lft+" "+rgt);
            if(curr!=lft) {
                System.out.println("? "+lft);
                System.out.flush();
                in=input.nextInt();
                curr=lft;
            }
            System.out.println("? "+rgt);
            System.out.flush();
            in=input.nextInt();
            curr=rgt;
            if(in==1) {
                ans=mid;
                r=mid-1;
            }
            else {
                l=mid+1;
            }
        }
        System.out.println("= "+ans);
        System.out.flush();
    }
    public static long get_l(long n,long diff) {
        long mid=n/2;
        if(n%2==1) {
            mid++;
        }
        if(diff%2==1) {
            return mid-(diff/2);
        }
        return mid+(diff/2);
    }
    public static long get_r(long n,long diff) {
        long mid=n/2;
        if(n%2==1) {
            mid++;
        }
        if(diff%2==1) {
            return mid+(diff/2)+1;
        }
        return mid-(diff/2);
    }
}
