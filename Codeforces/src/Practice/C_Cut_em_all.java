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
public class C_Cut_em_all {
    static ArrayList<Integer> adj_lst[];
    static boolean vis[];
    static int children[];
    public static void main(String args[]){
        Scanner input=new Scanner(System.in);
        //No of nodes
        int n=input.nextInt();
        adj_lst=new ArrayList[n];
        vis=new boolean[n];
        children=new int[n];
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<Integer>();
        }
        //No of edges
        for(int i=0;i<n-1;i++) {
            // input u & v
            int u=input.nextInt();
            int v=input.nextInt();
            u--;
            v--;
            adj_lst[u].add(v);
            adj_lst[v].add(u);
        }
        for(int i=0;i<n;i++) {
            children[i]=-1;
        }
        if(n%2==1) {
            System.out.println(-1);
            System.exit(0);
        }
        mod_DFS(0,-1);
//        for(int i=0;i<adj_lst.length;i++) {
//            System.out.print(i+"->");
//            for(int j=0;j<adj_lst[i].size();j++) {
//                System.out.print(adj_lst[i].get(j)+" ");
//            }
//            System.out.println();
//        }
        
        int count=0;
        for(int i=1;i<n;i++) {
//            System.out.println(i+".)"+children[i]);
            if(children[i]%2==1) {
                count++;
            }
        }
        System.out.println(count);
    }
    
    public static int mod_DFS(int root,int parent) {
        vis[root]=true;
        if(children[root]!=-1) {
            return children[root];
        }
        if(adj_lst[root].size()==1 && adj_lst[root].get(0)==parent) {
            children[root]=0;
            return 0;
        }
        children[root]=0;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(!vis[adj_lst[root].get(i)]) {
                if(children[adj_lst[root].get(i)]==-1) {
                    children[adj_lst[root].get(i)]=mod_DFS(adj_lst[root].get(i),root);
                }
                children[root]+=1+children[adj_lst[root].get(i)];
            }
        }
        return children[root];
    }
}
