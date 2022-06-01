/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphs;

/**
 *
 * @author User
 */

//Longest path strating from any node to any node in a directed Acyclic Graph
import java.io.*;
import java.util.*;
public class Longest_Path_in_DAG {
    static ArrayList<Integer> adj_lst[];
    static boolean vis[];
    static int dp[];
    public static void main(String args[]){
        Scanner input=new Scanner(System.in);
        //No of nodes
        adj_lst=new ArrayList[input.nextInt()];
        dp=new int[adj_lst.length];
        vis=new boolean[adj_lst.length];
        for(int i=0;i<dp.length;i++) {
            dp[i]=-1;
        }
        for(int i=0;i<adj_lst.length;i++) {
            adj_lst[i]=new ArrayList<Integer>();
        }
        //No of edges
        for(int i=input.nextInt();i>0;i--) {
            // input u & v
            int u=input.nextInt();
            int v=input.nextInt();
            adj_lst[u].add(v);
        }
        for(int i=0;i<vis.length;i++) {
            if(!vis[i]) {
                max_path(i);
            }
        }
        int max_dist=-1;
        for(int i=0;i<dp.length;i++) {
            max_dist=Math.max(max_dist, dp[i]);
        }
        System.out.println(max_dist);
    }
    
    public static int max_path(int root) {
        vis[root]=true;
        if(dp[root]!=-1) {
            return dp[root];
        }
        if(adj_lst[root].size()==0) {
            dp[root]=0;
            return dp[root];
        }
        for(int i=0;i<adj_lst[root].size();i++) {
            if(dp[adj_lst[root].get(i)]==-1) {
                dp[adj_lst[root].get(i)]=max_path(adj_lst[root].get(i));
            }
            dp[root]=Math.max(dp[root], dp[adj_lst[root].get(i)]);
        }
        dp[root]++;
        return dp[root];
    }
}
