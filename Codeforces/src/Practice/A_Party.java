/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

/**
 *
 * @author User
 */
import java.io.*; 
import java.util.*;
public class A_Party {
    static ArrayList<Integer> adj_lst[];
    public static void main(String args[]){
        Scanner input=new Scanner(System.in);
        //No of nodes
        adj_lst=new ArrayList[input.nextInt()];
        for(int i=0;i<adj_lst.length;i++) {
            adj_lst[i]=new ArrayList<Integer>();
        }
        //No of edges
        for(int i=0;i<adj_lst.length;i++) {
            // input u & v
            int u=input.nextInt();
            if(u!=-1) {
               adj_lst[u-1].add(i); 
            }
        }
//        for(int i=0;i<adj_lst.length;i++) {
//            System.out.print(i+"->");
//            for(int j=0;j<adj_lst[i].size();j++) {
//                System.out.print(adj_lst[i].get(j)+" ");
//            }
//            System.out.println();
//        }
        int depth=-1;
        for(int i=0;i<adj_lst.length;i++) {
//            System.out.println("---------"+i+"---------");
            int tmp_depth=BFS_tree_depth(i);
            if(depth==-1) {
                depth=tmp_depth;
            }
            else if(tmp_depth>depth) {
                depth=tmp_depth;
            }
        }
        System.out.println(depth+1);
    }
    
    static int BFS_tree_depth(int strt) {
        boolean vis[]=new boolean[adj_lst.length];
        int depth[]=new int[adj_lst.length];
        Queue<Integer> que = new LinkedList<>();
        que.add(strt);
        depth[0]=0;
        vis[strt]=true;
        while(!que.isEmpty()) {
            for(int i=0;i<adj_lst[que.peek()].size();i++) {
                if(!vis[adj_lst[que.peek()].get(i)]) {
                    que.add(adj_lst[que.peek()].get(i));
                    vis[adj_lst[que.peek()].get(i)]=true;
                    depth[adj_lst[que.peek()].get(i)]=depth[que.peek()]+1;
                }
            }
//            System.out.println(depth[que.peek()]+" "+que.poll());
            que.poll();
        }
        int max_depth=0;
        for(int i=0;i<depth.length;i++) {
//            System.out.print(depth[i]+" ");
            if(depth[i]>max_depth) {
                max_depth=depth[i];
            }
        }
//        System.out.println();
        return max_depth;
    }
}
