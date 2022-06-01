/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Educational_DP_Contest;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class P {
    static ArrayList<Integer> adj_lst[];
    static long dp[][],mod=1000000007L;
    public static void main(String args[]){
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        adj_lst=new ArrayList[n];
        for(int i=0;i<adj_lst.length;i++) {
            adj_lst[i]=new ArrayList<Integer>();
        }
        for(int i=0;i<n-1;i++) {
            int u=input.nextInt()-1;
            int v=input.nextInt()-1;
            adj_lst[u].add(v);
            adj_lst[v].add(u);
        }
//        for(int i=0;i<adj_lst.length;i++) {
//            System.out.print(i+"->");
//            for(int j=0;j<adj_lst[i].size();j++) {
//                System.out.print(adj_lst[i].get(j)+" ");
//            }
//            System.out.println();
//        }
        dp=new long[n][2];
        for(int i=0;i<n;i++) {
            for(int j=0;j<2;j++) {
                dp[i][j]=-1L;
            }
        }
        System.out.println(BFS(0,-1,1));
    }
    //color     0->black    1->white
    static long BFS(int root, int parent,int parent_clr) {
//        System.out.println(source+" "+parent+" "+parent_clr);
        if(adj_lst[root].size()==1 && adj_lst[root].get(0)==parent) {
            if(parent_clr==0) {
                return 1;
            }
            return 2;
        }
        if(dp[root][parent_clr]!=-1) {
            return dp[root][parent_clr];
        } 
        long ways=1,ways1=1,ways2=1;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==parent) {
                continue;
            }
            if(parent_clr==0) {
                ways*=BFS(adj_lst[root].get(i),root,1);
                ways%=mod;
            }
            else {
                ways1*=BFS(adj_lst[root].get(i),root,0);
                ways2*=BFS(adj_lst[root].get(i),root,1);
                ways1%=mod;
                ways2%=mod;
            }
        }
        if(parent_clr==0) {
            dp[root][parent_clr]=ways;
            return dp[root][parent_clr];
        }
        dp[root][parent_clr]=(ways1+ways2)%mod;
        return dp[root][parent_clr];
    }
}
