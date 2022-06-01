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
public class Alien_Piano_1 {
    static int n,arr[],min;
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            n=input.nextInt();
            arr=new int[n];
            min=Integer.MAX_VALUE;
            for(int i=0;i<n;i++) {
                arr[i]=input.nextInt();
            }
            solve(0,new int[n]);
            ans.append("Case #"+t+": "+min);
            ans.append("\n");
        }
        System.out.println(ans);
    }
    public static void solve(int indx,int tmp[]) {
        if(indx==n) {
            min=Math.min(min,check(tmp));
            return;
        }
        tmp[indx]=1;
        solve(indx+1,tmp);
        tmp[indx]=2;
        solve(indx+1,tmp);
        tmp[indx]=3;
        solve(indx+1,tmp);
        tmp[indx]=4;
        solve(indx+1,tmp);
        tmp[indx]=0;
    }
    public static int check(int ans[]) {
        int cnt=0;
        for(int i=1;i<n;i++) {
            if(arr[i]>arr[i-1]) {
                if(ans[i]<=ans[i-1]) {
                    cnt++;
                }
            }
            if(arr[i]<arr[i-1]) {
                if(ans[i]>=ans[i-1]) {
                    cnt++;
                }
            }
            if(arr[i]==arr[i-1]) {
                if(ans[i]!=ans[i-1]) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
