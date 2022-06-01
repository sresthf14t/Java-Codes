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
public class Cycle_Detection_Directed_Graph {
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
        System.out.println(mod_DFS(0));
    }
    
    public static boolean mod_DFS(int root) {
        color[root]=1;
        vis[root]=true;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(color[adj_lst[root].get(i)]==1) {
                return true;
            }
            if(!vis[adj_lst[root].get(i)]) {
                if(mod_DFS(adj_lst[root].get(i))) {
                    return true;
                }
            }
        }
        color[root]=-1;
        return false;
    }
}
