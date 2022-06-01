/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_614_Div2;

/**
 *
 * @author User
 */
import java.util.Arrays;
import java.util.Scanner;
public class A {
    static Scanner input=new Scanner(System.in);
    public static void main(String args[]) {
        int test=input.nextInt();
        for(int t=0;t<test;t++) {
            int n=input.nextInt();
            int s=input.nextInt();
            int k=input.nextInt();
            int arr[]=new int[k];
            for(int i=0;i<k;i++) {
                arr[i]=input.nextInt();
            }
            System.out.println(count(n,s,k,arr));
        }
    }
    public static int count(int n,int s,int k,int arr[]) {
        Arrays.sort(arr);
        int indx=-1;
        for(int i=0;i<k;i++) {
            if(arr[i]==s) {
                indx=i;
                break;
            }
        }
        if(indx==-1) {
            //System.out.println(0);
            return 0;
        }
        int count_l=0,count_r=0;
        boolean left=false,right=false;
        for(int i=indx;i>0;i--) {
            if(arr[i-1]==arr[i]-1) {
                count_l++;
            }
            else {
                count_l++;
                left=true;
                break;
            }
        }
        if(!left && arr[0]!=1) {
            left=true;
            count_l++;
        }
        for(int i=indx;i<arr.length-1;i++) {
            if(arr[i+1]==arr[i]+1) {
                count_r++;
            }
            else {
                count_r++;
                right=true;
                break;
            }
        }
        if(!right && arr[arr.length-1]!=n) {
            right=true;
            count_r++;
        }
        //System.out.println(count_l+" "+count_r+" "+left+" "+right);
        if(left && right) {
            if(count_l<count_r) {
                return count_l;
            }
            else {
                return count_r;
            }
        }
        else if(left) {
            return count_l;
        }
        else {
            return count_r;
        }
    }
}