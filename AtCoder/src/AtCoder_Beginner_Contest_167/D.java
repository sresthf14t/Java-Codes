/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AtCoder_Beginner_Contest_167;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class D {
    public static void main(String args[]) throws IOException {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        long k=input.nextLong();
        int arr[]=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.nextInt()-1;
        }
        solve(n,arr,k);
    }
    public static void solve(int n,int arr[],long k) {
        int vis[]=new int[n];
        for(int i=0;i<n;i++) {
            vis[i]=-1;
        }
        long loop=-1;
        int i=0;
        for(int time=0;k>0;time++,k--) {
            if(vis[i]!=-1) {
                loop=time-vis[i];
                break;
            }
            vis[i]=time;
            i=arr[i];
        }
        if(k==0) {
            System.out.println(i+1);
            return;
        }
        k%=loop;
        while(k!=0) {
            i=arr[i];
            k--;
        }
        System.out.println(i+1);
    }
}
