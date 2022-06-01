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
//To find if two nodes given a s input are on the same path in the graph

import java.io.*; 
import java.util.*; 
public class Two_nodes_at_same_path {
    static ArrayList<Integer> adj_lst[];
    static boolean vis[];
    static int intime[],outtime[];
    static int time;
    public static void main(String args[]){
        Scanner input=new Scanner(System.in);
        //No of nodes
        adj_lst=new ArrayList[input.nextInt()];
        vis=new boolean[adj_lst.length];
        intime=new int[adj_lst.length];
        outtime=new int[adj_lst.length];
        time=0;
        for(int i=0;i<adj_lst.length;i++) {
            adj_lst[i]=new ArrayList<Integer>();
        }
        //No of edges
        for(int i=input.nextInt();i>0;i--) {
            // input u & v
            int u=input.nextInt();
            int v=input.nextInt();
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
        mod_DFS(0);
//        for(int i=0;i<adj_lst.length;i++) {
//            System.out.println(intime[i]+"  "+outtime[i]);
//        }
        
        
        //QUERIES
        for(int i=input.nextInt();i>0;i--) {
            int u=input.nextInt();
            int v=input.nextInt();
            
            //if u is a parent of v
            if(intime[u]<intime[v] && outtime[u]>outtime[v]) {
                System.out.println("YES");
            }
            //if v is a parent of u
            else if(intime[v]<intime[u] && outtime[v]>outtime[u]) {
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
            }
        }
        
    }
    
    public static void mod_DFS(int root) {
//        System.out.println(root);
        vis[root]=true;
        time++;
        intime[root]=time;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(!vis[adj_lst[root].get(i)]) {
                mod_DFS(adj_lst[root].get(i));
            }
        }
        time++;
        outtime[root]=time;
    }
}
