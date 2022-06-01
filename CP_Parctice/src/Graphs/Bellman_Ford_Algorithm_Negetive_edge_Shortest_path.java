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


//Single Source shortest path for grapth with negetive edges

import java.util.*;
import java.io.*;
public class Bellman_Ford_Algorithm_Negetive_edge_Shortest_path {
    static ArrayList<Integer> adj_lst[],weight[];
    static int dist[],prev[];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int m=input.nextInt();
        adj_lst=new ArrayList[n];
        weight=new ArrayList[n];
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<Integer>();
            weight[i]=new ArrayList<Integer>();
        }
        for(int i=0;i<m;i++) {
            int u=input.nextInt();
            int v=input.nextInt();
            int wei=input.nextInt();
            adj_lst[u].add(v);
            weight[u].add(wei);
        }
        shortest_path(n,0);
        for(int i=0;i<n;i++) {
            System.out.print(dist[i]+" ");
        }
        System.out.println();
        
        //Path to a target node
        int target=input.nextInt();
        ArrayList<Integer> path=path(0,target);
        for(int i=0;i<path.size();i++) {
            System.out.print(path.get(i)+" ");
        }
        System.out.println();
    }
    public static void shortest_path(int n,int source) {
        dist=new int[n];
        prev=new int[n];
        Arrays.fill(prev, -1);
        Arrays.fill(dist, Integer.MAX_VALUE/10);
        dist[source]=0;
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                for(int k=0;k<adj_lst[j].size();k++) {
                    if(dist[adj_lst[j].get(k)]>dist[j]+weight[j].get(k)) {
                        dist[adj_lst[j].get(k)]=dist[j]+weight[j].get(k);
                        prev[adj_lst[j].get(k)]=j;
                    }
                }
            }
        }
        for(int j=0;j<n;j++) {
            boolean done=false;
            for(int k=0;k<adj_lst[j].size();k++) {
                if(dist[adj_lst[j].get(k)]>dist[j]+weight[j].get(k)) {
                    System.out.println("Negetive Weigth Cycle Present");
                    done=true;
                    break;
                }
            }
            if(done) {
                break;
            }
        }
    }
    public static ArrayList<Integer> path(int source,int target) {
        ArrayList<Integer> path=new ArrayList<>();
        int curr=target;
        boolean got_src=false;
        while(curr!=-1) {
            if(curr==source) {
                got_src=true;
            }
            path.add(curr);
            curr=prev[curr];
        }
        if(!got_src) {
            return new ArrayList<>();
        }
        for(int i=0,j=path.size()-1;i<j;i++,j--) {
            int tmp=path.get(i);
            path.set(i, path.get(j));
            path.set(j, tmp);
        }
        return path;
    }
}
