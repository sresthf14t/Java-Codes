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
public class Alien_Piano {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            int n=input.nextInt();
            int arr[]=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.nextInt();
            }
            ans.append("Case #"+t+": "+solve(n,arr));
            ans.append("\n");
        }
        System.out.println(ans);
    }
    public static int solve(int n,int arr[]) {
        int ans=0,inc=1,dec=1;
        for(int i=0;i<n-1;i++) {
            if(arr[i]<arr[i+1]) {
                inc++;
                dec=1;
            }
            if(arr[i]>arr[i+1]) {
                dec++;
                inc=1;
            }
            if(inc>4) {
                ans++;
                inc=1;
            }
            if(dec>4) {
                ans++;
                dec=1;
            }
        }
        return ans;
    }
}
