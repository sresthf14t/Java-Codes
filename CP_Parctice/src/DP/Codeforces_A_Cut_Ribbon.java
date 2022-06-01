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
import java.util.Arrays;
import java.util.Scanner;
public class Codeforces_A_Cut_Ribbon {
    static int table[];
    static int na=1,nb=1,nc=1;
    public static void main(String rags[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int arr[]={input.nextInt(),input.nextInt(),input.nextInt()};
        Arrays.sort(arr);
        int a=arr[0];
        int b=arr[1];
        int c=arr[2];
        table=new int[n+1];
        if(b%a==0) {
            nb=b/a;
        }
        for(int i=0;i<=c;i++) {
            for(int j=0;j<=c;j++) {
                if((i*a)+(j*b)==c) {
                    if(i+j>nc) {
                        nc=i+j;
                    }
                }
            }
        }
//        System.out.println(na+" "+nb+" "+nc);
        System.out.println(count(n,a,b,c));
    }
    
    static int count(int n,int a,int b,int c) {
        if(n<=0) {
            return -9999999;
        }
        if(n==a) {
            return na;
        }
        if(n==b) {
             return nb;
        }
        if(n==c) {
            return nc;
        }
        if(table[n]!=0) {
            return table[n];
        }
        table[n]=max(count(n-a,a,b,c),count(n-b,a,b,c),count(n-c,a,b,c))+1;
        return table[n];
    }
    static int max(int a,int b,int c) {
        if(a>=b && a>=c) {
            return a;
        }
        if(b>=a && b>=c) {
            return b;
        }
        return c;
    }
}
