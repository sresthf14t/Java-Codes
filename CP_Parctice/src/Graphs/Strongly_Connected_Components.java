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
//Directed Graphs
import java.util.*;
import java.io.*;
public class Strongly_Connected_Components {
    static ArrayList<Integer> adj_lst[],adjT_lst[];
    static boolean vis[];
    static Stack<Integer> stack;
    public static void main(String args[]){
        Scanner input=new Scanner(System.in);
        //No of nodes
        adj_lst=new ArrayList[input.nextInt()];
        adjT_lst=new ArrayList[adj_lst.length];
        vis=new boolean[adj_lst.length];
        stack=new Stack<>();
        for(int i=0;i<adj_lst.length;i++) {
            adj_lst[i]=new ArrayList<Integer>();
            adjT_lst[i]=new ArrayList<Integer>();
        }
        //No of edges
        for(int i=input.nextInt();i>0;i--) {
            // input u & v
            int u=input.nextInt();
            int v=input.nextInt();
            adj_lst[u].add(v);
            adjT_lst[v].add(u);
        }
//        for(int i=0;i<adj_lst.length;i++) {
//            System.out.print(i+"->");
//            for(int j=0;j<adj_lst[i].size();j++) {
//                System.out.print(adj_lst[i].get(j)+" ");
//            }
//            System.out.println();
//        }
        
        
        //DFS
        for(int i=0;i<adj_lst.length;i++) {
            if(!vis[i]) {
                mod_DFS(i);
            }
        }
        
        //DFS of Transpose of graph
        int cnncted_cmp[]=new int[adj_lst.length];
        vis=new boolean[adj_lst.length];
        int clr=1;
        while(!stack.isEmpty()) {
            int i=stack.pop();
            if(!vis[i]) {
                modT_DFS(i);
                for(int j=0;j<adj_lst.length;j++) {
                    if(vis[j] && cnncted_cmp[j]==0) {
                        cnncted_cmp[j]=clr;
                    }
                }
                clr++;
            }
        }
        
        //All the Strongly Connected Components each clolor/number represents a Strongly Connected Component 
        for(int i=0;i<cnncted_cmp.length;i++) {
            System.out.println(i+".)"+cnncted_cmp[i]);
        }
        System.out.println();
    }
    
    public static void mod_DFS(int root) {
//        System.out.println(root);
        vis[root]=true;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(!vis[adj_lst[root].get(i)]) {
                mod_DFS(adj_lst[root].get(i));
            }
        }
        stack.push(root);
    }
    
    public static void modT_DFS(int root) {
//        System.out.println(root);
        vis[root]=true;
        for(int i=0;i<adjT_lst[root].size();i++) {
            if(!vis[adjT_lst[root].get(i)]) {
                modT_DFS(adjT_lst[root].get(i));
            }
        }
    }
}
