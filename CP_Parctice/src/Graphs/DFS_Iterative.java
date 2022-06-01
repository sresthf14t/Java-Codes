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
public class DFS_Iterative {
    static ArrayList<Integer> adj_lst[];
    static boolean vis[];
    public static void main(String args[]){
        Scanner input=new Scanner(System.in);
        //No of nodes
        adj_lst=new ArrayList[input.nextInt()];
        vis=new boolean[adj_lst.length];
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
        for(int i=0;i<adj_lst.length;i++) {
            System.out.print(i+"->");
            for(int j=0;j<adj_lst[i].size();j++) {
                System.out.print(adj_lst[i].get(j)+" ");
            }
            System.out.println();
        }
        System.out.println();
        Iterative_DFS(0);
        System.out.println();
    }
    public static void Iterative_DFS(int root) {
        Stack<Integer> stck=new Stack<>();
        stck.push(root);
        while(!stck.isEmpty()) {
            root=stck.pop();
            if(!vis[root]) {
                System.out.println(root);
                vis[root]=true;
            }
            for(int i=0;i<adj_lst[root].size();i++) {
                if(!vis[adj_lst[root].get(i)]) {
                    stck.push(adj_lst[root].get(i));
                }
            }
        }
    }
}
