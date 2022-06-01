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
public class Topological_Sorting {
    static ArrayList<Integer> adj_lst[];
    static boolean vis[];
    public static void main(String args[]){
        Scanner input=new Scanner(System.in);
        //No of nodes
        int n=input.nextInt();
        adj_lst=new ArrayList[n];
        vis=new boolean[n];
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
        }
//        for(int i=0;i<adj_lst.length;i++) {
//            System.out.print(i+"->");
//            for(int j=0;j<adj_lst[i].size();j++) {
//                System.out.print(adj_lst[i].get(j)+" ");
//            }
//            System.out.println();
//        }
        Stack<Integer> stck=new Stack<>();
        for(int i=0;i<n;i++) {
            if(!vis[i]) {
                top_sort(i,stck);
            }
        }
        while(!stck.isEmpty()) {
            System.out.print(stck.pop()+" ");
        }
        System.out.println();
    }
    public static void top_sort(int root,Stack<Integer> stck) {
//        System.out.println(root);
        vis[root]=true;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(!vis[adj_lst[root].get(i)]) {
                top_sort(adj_lst[root].get(i),stck);
            }
        }
        stck.push(root);
    }
}
