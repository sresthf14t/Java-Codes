/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Round_D_2020;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class Locked_Doors {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            int n=input.nextInt();
            int query=input.nextInt();
            int arr[]=new int[n-1];
            for(int i=0;i<n-1;i++) {
                arr[i]=input.nextInt();
            }
            for(int q=1;q<=query;q++) {
                int strt=input.nextInt()-1;
                int num=input.nextInt();
                System.out.print((solve(n,strt,num,arr))+" ");
            }
            System.out.println();
        }
        System.out.println(ans);
    }
    public static int solve(int n,int strt,int num,int arr[]) {
        int lft_indx=strt-1;
        int rgt_indx=strt+1;
        for(int i=0;i<n;i++) {

        }
    }
}
