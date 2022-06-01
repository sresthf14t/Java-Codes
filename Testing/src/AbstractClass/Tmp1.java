/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AbstractClass;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class Tmp1 {
    static ArrayList<Integer> adj_lst[],strt,end;
    static boolean vis[];
    static int max;
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=1;t<=test;t++) {
            max=-1;
            int n=input.nextInt();
            //No of nodes
            adj_lst=new ArrayList[n];
            strt=new ArrayList<>();
            end=new ArrayList<>();
            vis=new boolean[n];
            for(int i=0;i<n;i++) {
                adj_lst[i]=new ArrayList<Integer>();
            }
            int arr[]=new int[n];
            for(int i=0;i<n;i++) {
                int tmp=input.nextInt();
                if(tmp==-1) {
                    continue;
                }
                adj_lst[i].add(tmp);
            }
            for(int i=0;i<n;i++) {
                if(vis[i] ){
                    continue;
                }
                DFS(i);
            }
            for(int i=0;i<strt.size();i++) {
                DFS_sum(strt.get(i),end.get(i),0);
            }
            System.out.println(max);
        }
    }
    public static void DFS(int root) {
//        System.out.println(root);
        vis[root]=true;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(!vis[adj_lst[root].get(i)]) {
                DFS(adj_lst[root].get(i));
            }
            else {
                strt.add(adj_lst[root].get(i));
                end.add(root);
            }
        }
        vis[root]=false;
    }
    public static void DFS_sum(int root,int target,int sum) {
//        System.out.println(root);
        sum+=root;
        if(root==target) {
            max=Math.max(max,sum);
            return;
        }
        vis[root]=true;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(!vis[adj_lst[root].get(i)]) {
                DFS_sum(adj_lst[root].get(i),target,sum);
            }
        }
    }
}
