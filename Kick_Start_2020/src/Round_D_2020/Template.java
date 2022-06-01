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
public class Template {
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
        int cnt=0,max=Integer.MIN_VALUE;
        for(int i=0;i<n;i++) {
            if(i==n-1) {
                if(arr[i]>max) {
                    cnt++;
                }
                break;
            }
            if(arr[i]>max && arr[i]>arr[i+1]) {
                cnt++;
            }
            max=Math.max(max,arr[i]);
        }
        return cnt;
    }
}
