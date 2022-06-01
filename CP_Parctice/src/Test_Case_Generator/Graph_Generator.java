/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Case_Generator;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class Graph_Generator {
    static ArrayList<Integer> adj_lst[],art_points;
    static boolean vis[];
    static int time,timer[],low[];
    static Set<Integer> art_hashset,adj_lst_check[];
    public static void main(String args[]) {
        int cnt=0;
        while(true) {
            cnt++;
            if(true || cnt%1000000==0) {
                System.out.println(cnt);
            }
            int n=1000;
            ArrayList<Integer> arr1=new ArrayList<>();
            ArrayList<Integer> arr2=new ArrayList<>();
            for(int i=0;i<n;i++) {
                for(int j=i+1;j<n;j++) {
                    arr1.add(i);
                    arr2.add(j);
                }
            }
            int m=(int)(Math.random()*(n*(n-1))/2);
            adj_lst=new ArrayList[n];
            vis=new boolean[n];
            timer=new int[n];
            low=new int[n];
            time=0;
            art_hashset=new HashSet<>();
            art_points=new ArrayList<>();
            adj_lst_check=new HashSet[n];
            for(int i=0;i<n;i++) {
                adj_lst[i]=new ArrayList<>();
                adj_lst_check[i]=new HashSet<>();
            }
            for(int i=0;i<m;i++) {
                int indx=(int)(Math.random()*(arr1.size()-1));
                int u=arr1.get(indx);
                int v=arr2.get(indx);
                arr1.remove(indx);
                arr2.remove(indx);
                adj_lst[u].add(v);
                adj_lst[v].add(u);
                adj_lst_check[u].add(v);
                adj_lst_check[v].add(u);
            }
            art_points(n);
            Set<Integer> hashset=BF_Check(n);
            if(hashset.size()!=art_points.size()) {
                System.out.println("NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
                System.out.println(1+" "+hashset.size()+" "+art_points.size()+" "+n+" "+m);
                for(int i=0;i<n;i++) {
                    System.out.print(i+"-->");
                    for(int j=0;j<adj_lst[i].size();j++) {
                        System.out.print(adj_lst[i].get(j)+" ");
                    }
                    System.out.println();
                }
                break;
            }
            for(int i=0;i<art_points.size();i++) {
                if(!hashset.contains(art_points.get(i))) {
                    System.out.println("NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
                    System.out.println(2+" "+hashset.size()+" "+art_points.size()+" "+n+" "+m);
                    break;
                }
            }
        }
        
    }
    public static void art_points(int n) {
        for(int i=0;i<n;i++) {
            if(!vis[i]) {
                DFS(i,-1);
            }
        }
    }
    public static void DFS(int root,int par) {
        timer[root]=low[root]=time;
        time++;
        vis[root]=true;
        int children=0;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==par) {
                continue;
            }
            if(vis[adj_lst[root].get(i)]) {
                low[root]=Math.min(low[root],timer[adj_lst[root].get(i)]);
            }
            else {
                DFS(adj_lst[root].get(i),root);
                low[root]=Math.min(low[root],low[adj_lst[root].get(i)]);
                if(low[adj_lst[root].get(i)]>=timer[root] && par!=-1) {
                    if(!art_hashset.contains(root)) {
                        art_hashset.add(root);
                        art_points.add(root);
                    }
                }
                children++;
            }
        }
        if(children>1 && par==-1) {
            if(!art_hashset.contains(root)) {
                art_hashset.add(root);
                art_points.add(root);
            }
        }
    }
    
    public static Set<Integer> BF_Check(int n) {
        Set<Integer> hashset=new HashSet<>();
        for(int i=0;i<n;i++) {
            if(adj_lst[i].size()==0) {
                continue;
            }
            vis=new boolean[n];
            boolean vis1[]=new boolean[n];
            BF_DFS(adj_lst[i].get(0),i);
            BF_DFS1(adj_lst[i].get(0),vis1);
            for(int j=0;j<n;j++) {
                if(j==i) {
                    continue;
                }
                if(vis[j]!=vis1[j]) {
                    hashset.add(i);
                    break;
                }
            }
        }
        return hashset;
    }
    public static void BF_DFS(int root,int exc) {
        vis[root]=true;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(vis[adj_lst[root].get(i)]) {
                continue;
            }
            if(adj_lst[root].get(i)==exc) {
                continue;
            }
            BF_DFS(adj_lst[root].get(i),exc);
        }
    }
    public static void BF_DFS1(int root,boolean vis1[]) {
        vis1[root]=true;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(vis1[adj_lst[root].get(i)]) {
                continue;
            }
            BF_DFS1(adj_lst[root].get(i),vis1);
        }
    }
}
