/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphs;

/**
 *
 * @author Srest
 */

/*
We are given an undirected graph. An articulation point (or cut vertex) is 
defined as a vertex which, when removed along with associated edges, makes 
the graph disconnected (or more precisely, increases the number of connected 
components in the graph).

Time Complexity-O(V+E)
*/
import java.util.*;
import java.io.*;
public class Articulation_points {
    static ArrayList<Integer> adj_lst[],art_points;
    static boolean vis[];
    static int time,timer[],low[];
    static Set<Integer> art_hashset;
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int m=input.nextInt();
        adj_lst=new ArrayList[n];
        vis=new boolean[n];
        timer=new int[n];
        low=new int[n];
        time=0;
        art_hashset=new HashSet<>();
        art_points=new ArrayList<>();
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<>();
        }
        for(int i=0;i<m;i++) {
            int u=input.nextInt();
            int v=input.nextInt();
            adj_lst[u].add(v);
            adj_lst[v].add(u);
        }
        art_points(n);
        System.out.println("Articulation Points");
        for(int i=0;i<art_points.size();i++) {
            System.out.print(art_points.get(i)+" ");
        }
        System.out.println();
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
}
