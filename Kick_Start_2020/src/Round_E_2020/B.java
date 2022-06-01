/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Round_E_2020;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class B {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            int n=input.nextInt();
            int a=input.nextInt();
            int b=input.nextInt();
            int c=input.nextInt();
            if(n<3) {
                fin=null;
                bf(n,0,new int[n],a,b,c);
                ans.append("Case #"+t+": ");
                if(fin!=null) {
                    for(int i=0;i<n;i++) {
                        ans.append(fin[i]+" ");
                    }
                    ans.append("\n");
                }
                else {
                    ans.append("IMPOSSIBLE\n");
                }
                continue;
            }
            int arr[]=solve(n,a,b,c);
            ans.append("Case #"+t+": ");
            if(check(n,arr,a,b,c)) {
                for(int i=0;i<n;i++) {
                    ans.append(arr[i]+" ");
                }
                ans.append("\n");
            }
            else {
                ans.append("IMPOSSIBLE\n");
            }
        }
        System.out.print(ans);
    }
    public static int[] solve(int n,int a,int b,int c) {
        int arr[]=new int[n];
        Arrays.fill(arr, n);
        for(int i=0;i<a-c;i++) {
            arr[i]=2;
        }
        for(int i=n-1,cnt=0;cnt<b-c;i--,cnt++) {
            arr[i]=2;
        }
        int cnt=0;
        for(int i=0;i<n;i++) {
            if(arr[i]==n) {
                cnt++;
            }
        }
        int red=cnt-c;
        for(int i=1;i<n-1 && red>0;i++) {
            if(arr[i]==n) {
                arr[i]=1;
                red--;
            }
        }
        return arr;
    }
    static int fin[];
    public static boolean check(int n,int arr[],int a,int b,int c) {
        int vis[]=new int[n];
        int max=0;
        int cnt1=0,cnt2=0;
        for(int i=0;i<n;i++) {
            if(arr[i]>=max) {
                max=arr[i];
                cnt1++;
                vis[i]++;
            }
        }
        max=0;
        for(int i=n-1;i>=0;i--) {
            if(arr[i]>=max) {
                max=arr[i];
                cnt2++;
                vis[i]++;
            }
        }
        int cnt=0;
        for(int i=0;i<n;i++) {
            if(vis[i]>1) {
                cnt++;
            }
        }
//        System.out.println(cnt1+" "+cnt2+" "+cnt);
        if(cnt1==a && cnt2==b && cnt==c) {
            return true;
        }
        return false;
    }
    public static int cnt_a(int n,int arr[]) {
        int max=0,cnt=0;
        for(int i=0;i<n;i++) {
            if(arr[i]>=max) {
                max=arr[i];
                cnt++;
            }
        }
        return cnt;
    }
    public static int cnt_b(int n,int arr[]) {
        int max=0,cnt=0;
        for(int i=n-1;i>=0;i--) {
            if(arr[i]>=max) {
                max=arr[i];
                cnt++;
            }
        }
        return cnt;
    }
    public static void bf(int n,int indx,int arr[],int a,int b,int c) {
        if(fin!=null) {
            return;
        }
        if(indx==n) {
            if(check(n,arr,a,b,c)) {
                copy(arr);
            }
            return;
        }
        for(int i=1;i<=n;i++) {
            arr[indx]=i;
            bf(n,indx+1,arr,a,b,c);
        }
    }
    public static void copy(int arr[]) {
        fin=new int[arr.length];
        for(int i=0;i<arr.length;i++) {
            fin[i]=arr[i];
        }
    }
}