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



//To detect that if a graph is a tree or no
/*Conditions
1.) It should not contain any cycle
2.)It should be fullt connected
*/
import java.util.*;
import java.io.*;
public class If_a_graph_is_a_tree {
    static ArrayList<Integer> adj_lst[];
    static boolean vis[];
    //-1->Black 0->White 1->Grey
    static int color[];
    public static void main(String args[]){
        Scanner input=new Scanner(System.in);
        //No of nodes
        adj_lst=new ArrayList[input.nextInt()];
        vis=new boolean[adj_lst.length];
        color=new int[adj_lst.length];
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
//        for(int i=0;i<adj_lst.length;i++) {
//            System.out.print(i+"->");
//            for(int j=0;j<adj_lst[i].size();j++) {
//                System.out.print(adj_lst[i].get(j)+" ");
//            }
//            System.out.println();
//        }
        boolean is_connected=false;
        DFS(0);
        for(int i=0;i<adj_lst.length;i++) {
            if(!vis[i])  {
                break;
            }
            if(i==adj_lst.length-1) {
                is_connected=true;
            }
        }
        vis=new boolean[adj_lst.length];
        System.out.println(is_connected & !find_cycle_DFS(0,-1));
    }
    
    public static boolean find_cycle_DFS(int root,int parent) {
        vis[root]=true;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(!vis[adj_lst[root].get(i)]) {
               if(find_cycle_DFS(adj_lst[root].get(i),root)) {
                   return true;
               }
            }
            else if(adj_lst[root].get(i)!=parent){
                return true;
            }
        }
        return false;
    }
    
    public static void DFS(int root) {
//        System.out.println(root);
        vis[root]=true;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(!vis[adj_lst[root].get(i)]) {
                DFS(adj_lst[root].get(i));
            }
        }
    }
}
