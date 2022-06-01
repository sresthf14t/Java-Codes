/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Programming_Contest_1_Chapter_Ramayan;

/**
 *
 * @author User
 */
import java.io.*; 
import java.util.*;
public class HTREE {
    static ArrayList<Integer> adj_lst[];
    static boolean vis[];
    static int par[];
    public static void main(String args[]){
        Scanner input=new Scanner(System.in);
        //No of nodes
        int n=input.nextInt();
        int query=input.nextInt();
        adj_lst=new ArrayList[n];
        vis=new boolean[n];
        par=new int[n];
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<Integer>();
        }
        //No of edges
        for(int i=0;i<n-1;i++) {
            // input u & v
            int u=input.nextInt()-1;
            int v=input.nextInt()-1;
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
    }
    
    public static void DFS(int root,ArrayList<Integer> ansc) {
        ansc.add(root);
        System.out.println(root);
        vis[root]=true;
        if(an)
        for(int i=0;i<adj_lst[root].size();i++) {
            if(!vis[adj_lst[root].get(i)]) {
                DFS(adj_lst[root].get(i),ansc);
            }
        }
    }
}
