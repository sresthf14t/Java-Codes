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
public class E_Military_Problem {
    static ArrayList<Integer> adj_lst[],dfs;
    static boolean vis[];
    static int subtree[];
    public static void main(String args[]){
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int q=input.nextInt();
        adj_lst=new ArrayList[n];
        vis=new boolean[n];
        dfs=new ArrayList<>();
        subtree=new int[n];
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<Integer>();
        }
        //No of edges
        for(int i=1;i<n;i++) {
            int tmp=input.nextInt()-1;
            if(tmp!=i) {
                adj_lst[tmp].add(i);
            }
        }
//        for(int i=0;i<adj_lst.length;i++) {
//            System.out.print(i+"->");
//            for(int j=0;j<adj_lst[i].size();j++) {
//                System.out.print(adj_lst[i].get(j)+" ");
//            }
//            System.out.println();
//        }
        DFS(0);
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<dfs.size();i++) {
            map.put(dfs.get(i), i);
        }
        for(int i=0;i<q;i++) {
            int root=input.nextInt()-1;
            int indx=input.nextInt();
            if(indx>subtree[root]) {
                System.out.println(-1);
                continue;
            }
            int map_indx=map.get(root);
            System.out.println(dfs.get(map_indx+indx-1)+1);
        }
    }
    
    public static void DFS(int root) {
        dfs.add(root);
        if(adj_lst[root].size()==0) {
            subtree[root]=1;
            return;
        }
        int sub=0;
        for(int i=0;i<adj_lst[root].size();i++) {
            DFS(adj_lst[root].get(i));
            sub+=subtree[adj_lst[root].get(i)];
        }
        subtree[root]=sub+1;
    }
}
