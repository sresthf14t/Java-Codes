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
import java.math.*;
public class Beauty_of_tree {
    //then left_son=(2*p)+1;
    //and right_son=(2*p)+2;
    //if index(child) = N, index(parent) = (N-1)/2
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            int n=input.nextInt();
            int a=input.nextInt();
            int b=input.nextInt();
            int arr[]=new int[n];
            arr[0]=-1;
            for(int i=1;i<n;i++) {
                arr[i]=input.nextInt()-1;
            }
            long total=0,cnt=0;
            for(int i=0;i<n;i++) {
                for(int j=0;j<n;j++) {
                    total+=solve(n,arr,a,b,i,j);
                    cnt++;
                }
            }
            BigDecimal num=new BigDecimal(""+total);
            BigDecimal den=new BigDecimal(""+cnt);
            BigDecimal tmp=num.divide(den,10, RoundingMode.HALF_UP);
            ans.append("Case #"+t+": "+tmp);
            ans.append("\n");
        }
        System.out.println(ans);
    }
    public static int solve(int n,int arr[],int a,int b,int indx1,int indx2) {
        boolean vis[]=new boolean[n];
        paint(arr,indx1,a,vis);
        paint(arr,indx2,b,vis);
        int cnt=0;
        for(int i=0;i<n;i++) {
            if(vis[i]) {
                cnt++;
            }
        }
        return cnt;
    }
    public static void paint(int arr[],int indx,int dist,boolean vis[]) {
        int cnt=0;
        while(indx!=-1) {
//            System.out.println(indx);
            if(cnt%dist==0) {
                vis[indx]=true;
            }
            indx=arr[indx];
            cnt++;
        }
    }
}
