/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Global_Round_10;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class E {
    static int n;
    static long arr[][];
    static ArrayList<Integer> primes;
    static ArrayList<Integer> path;
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        n=input.nextInt();
        arr=new long[n][n];
        int val=1;
        for(int tmp=0;tmp<n;tmp++) {
            int i=0,j=tmp;
            while(i<n && j>=0) {
                arr[i][j]=val;
                val++;
                i++;
                j--;
            }
        }
        for(int tmp=1;tmp<n;tmp++) {
            int i=tmp,j=n-1;
            while(i<n && j>=0) {
                arr[i][j]=val;
                val++;
                i++;
                j--;
            }
        }
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
        System.out.flush();
        int q=input.nextInt();
        for(int i=0;i<q;i++) {
            StringBuilder ans=new StringBuilder("");
            long k=input.nextInt();
            path=null;
            DFS(0,0,0,k,new ArrayList<>());
            for(int j=0;j<path.size();j++) {
                ans.append(path.get(j)+" ");
                if(j%2==1) {
                    ans.append("\n");
                }
            }
            System.out.println(ans);
            System.out.flush();
        }
    }
    public static void DFS(int i,int j,long sum,long target,ArrayList<Integer> tmp) {
        if(path!=null) {
            return;
        }
        tmp.add(i+1);
        tmp.add(j+1);
        sum+=arr[i][j];
        if(i==n-1 && j==n-1 && sum==target) {
            path=new ArrayList<>(tmp);
        }
        if(sum>target) {
            tmp.remove(tmp.size()-1);
            tmp.remove(tmp.size()-1);
            return;
        }
        if(i!=n-1) {
            DFS(i+1,j,sum,target,tmp);
        }
        if(j!=n-1) {
            DFS(i,j+1,sum,target,tmp);
        }
        tmp.remove(tmp.size()-1);
        tmp.remove(tmp.size()-1);
    }
}