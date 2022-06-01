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
import java.util.*;
import java.io.*;
public class E_Reachability_from_the_Capital {
    static ArrayList<Integer> adj_lst[],longest_path;
    static boolean vis[];
    public static void main(String args[]){
        Scanner input=new Scanner(System.in);
        //No of nodes
        int n=input.nextInt();
        int m=input.nextInt();
        int source=input.nextInt()-1;
        adj_lst=new ArrayList[n];
        vis=new boolean[n];
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<Integer>();
        }
        //No of edges
        for(int i=0;i<m;i++) {
            // input u & v
            int u=input.nextInt();
            int v=input.nextInt();
            u--;
            v--;
            adj_lst[u].add(v);
        }
        DFS(source);
        int count=0;
        ArrayList<Integer> nodes=new ArrayList<>();
        for(int i=0;i<n;i++) {
            if(!vis[i]) {
                nodes.add(i);
            }
        }
        if(nodes.isEmpty()) {
            System.out.println(0);
            System.exit(0);
        }
        
        longest_path=new ArrayList<>();
        ArrayList<Integer> path=new ArrayList<>();
        while(true) {
            count++;
            longest_path=new ArrayList<>();
            for(int i=0;i<nodes.size();i++) {
                path=new ArrayList<>();
                path_DFS(nodes.get(i), path);
                if(path.size()>longest_path.size()) {
                    longest_path=new ArrayList<>(path);
                }
            }
            for(int i=0;i<longest_path.size();i++) {
                vis[longest_path.get(i)]=true;
                nodes.remove(longest_path.get(i));
            }
            if(nodes.isEmpty()) {
                break;
            }
        }
        System.out.println(count);
    }
    
    public static void DFS(int root) {
        vis[root]=true;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(!vis[adj_lst[root].get(i)]) {
                DFS(adj_lst[root].get(i));
            }
        }
    }
    
    public static void path_DFS(int root,ArrayList<Integer> path) {
        vis[root]=true;
        path.add(root);
        for(int i=0;i<adj_lst[root].size();i++) {
            if(!vis[adj_lst[root].get(i)]) {
                path_DFS(adj_lst[root].get(i),path);
            }
        }
        vis[root]=false;
    }
}
