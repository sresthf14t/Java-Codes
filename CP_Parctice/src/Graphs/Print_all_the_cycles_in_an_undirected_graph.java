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
import java.io.*; 
import java.util.*;
public class Print_all_the_cycles_in_an_undirected_graph {
    static ArrayList<Integer> adj_lst[],cycles[];
    static int parent[],color[],cycle_number;
    public static void main(String args[]){
        Scanner input=new Scanner(System.in);
        //No of nodes
        int n=input.nextInt();
        adj_lst=new ArrayList[n];
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<Integer>();
        }
        //No of edges
        int m=input.nextInt();
        for(int i=0;i<m;i++) {
            // input u & v
            int u=input.nextInt();
            int v=input.nextInt();
            adj_lst[u].add(v);
            adj_lst[v].add(u);
        }
        cycles=new ArrayList[2*n];
        cycle_number=0;
        color=new int[n];
        parent=new int[n];
        for(int i=0;i<n;i++) {
            parent[i]=-1;
        }
        
//        for(int i=0;i<adj_lst.length;i++) {
//            System.out.print(i+"->");
//            for(int j=0;j<adj_lst[i].size();j++) {
//                System.out.print(adj_lst[i].get(j)+" ");
//            }
//            System.out.println();
//        }
        for(int i=0;i<n;i++) {
            if(color[i]==0) {
                DFS(i,-1);
            }
        }
        for(int i=0;i<cycle_number;i++) {
            for(int j=0;j<cycles[i].size();j++) {
                System.out.print(cycles[i].get(j)+" ");
            }
            System.out.println();
        }
    }
    public static void DFS(int root,int par) {
        if(color[root]==2) {
            return;
        }
        if(color[root]==1) {
            cycles[cycle_number]=new ArrayList<>();
            int r=par;
            while(r!=root) {
                cycles[cycle_number].add(r);
                color[r]=2;
                r=parent[r];
            }
            cycles[cycle_number].add(root);
            cycle_number++;
            return;
        }
        color[root]=1;
        parent[root]=par;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==par) {
                continue;
            }
            DFS(adj_lst[root].get(i),root);
        }
        color[root]=2;
    }
}
