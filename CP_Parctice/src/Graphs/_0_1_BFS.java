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
//Get shortest distance from any vertex to all other vertexes when all edge distances are only 0 or 1.
//Time Complexity O(V+E)
import java.util.*;
import java.io.*;
public class _0_1_BFS {
    static ArrayList<Integer> adj_lst[],weight[];
    static int dist[];
    public static void main(String args[]){
        Scanner input=new Scanner(System.in);
        //No of nodes
        int n=input.nextInt();
        adj_lst=new ArrayList[n];
        weight=new ArrayList[n];
        for(int i=0;i<adj_lst.length;i++) {
            adj_lst[i]=new ArrayList<Integer>();
            weight[i]=new ArrayList<Integer>();
        }
        //No of edges
        int m=input.nextInt();
        for(int i=0;i<m;i++) {
            // input u & v
            int u=input.nextInt();
            int v=input.nextInt();
            int wei=input.nextInt();
            adj_lst[u].add(v);
            adj_lst[v].add(u);
            weight[u].add(wei);
            weight[v].add(wei);
        }
        int root=input.nextInt();
        dist=new int[n];
        _01_BFS(root);
        for(int i=0;i<n;i++) {
            System.out.print(dist[i]+" ");
        }
        System.out.println();
    }
    public static void _01_BFS(int root) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        Deque<Integer> que = new ArrayDeque<Integer>();
        que.addFirst(root);
        dist[root]=0;
        while(!que.isEmpty()) {
            root=que.poll();
            for(int i=0;i<adj_lst[root].size();i++) {
                if(dist[adj_lst[root].get(i)]>dist[root]+weight[root].get(i)) {
                    dist[adj_lst[root].get(i)]=dist[root]+weight[root].get(i);
                    if(weight[root].get(i)==0) {
                        que.addFirst(adj_lst[root].get(i));
                    }
                    else {
                        que.addLast(adj_lst[root].get(i));
                    }
                }
            }
        }
    }
}
