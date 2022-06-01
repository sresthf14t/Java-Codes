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
import java.io.*; 
import java.util.*; 
public class G {
    static ArrayList<Integer> adj_lst[];
    static boolean vis[];
    static int dp[];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int m=input.nextInt();
        adj_lst=new ArrayList[n];
        vis=new boolean[n];
        dp=new int[n];
        for(int i=0;i<adj_lst.length;i++) {
            adj_lst[i]=new ArrayList<Integer>();
            dp[i]=-1;
        }
        for(int i=0;i<m;i++) {
            int u=input.nextInt()-1;
            int v=input.nextInt()-1;
            adj_lst[u].add(v);
        }
//        for(int i=0;i<adj_lst.length;i++) {
//            System.out.print(i+"->");
//            for(int j=0;j<adj_lst[i].size();j++) {
//                System.out.print(adj_lst[i].get(j)+" ");
//            }
//            System.out.println();
//        }
        for(int i=0;i<n;i++) {
            if(!vis[i]) {
                solve(i);
            }
        }
        int max=Integer.MIN_VALUE;
        for(int i=0;i<n;i++) {
            max=Math.max(max,dp[i]);
        }
        System.out.println(max);
    }
    public static void solve(int root) {
        vis[root]=true;
        if(adj_lst[root].size()==0) {
            dp[root]=0;
            return;
        }
        dp[root]=Integer.MIN_VALUE;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(dp[adj_lst[root].get(i)]==-1) {
                solve(adj_lst[root].get(i));
            }
            dp[root]=Math.max(dp[root],1+dp[adj_lst[root].get(i)]);
        }
    }
}
