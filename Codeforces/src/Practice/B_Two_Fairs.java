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
public class B_Two_Fairs {
    static ArrayList<Integer> adj_lst[];
    static boolean vis[];
    static int intime[],outtime[];
    static int time;
    public static void main(String args[]){
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int T=0;T<test;T++) {
            int n=input.nextInt();
            int m=input.nextInt();
            int a=input.nextInt();
            int b=input.nextInt();
            a--;
            b--;
            //No of nodes
            adj_lst=new ArrayList[n];
            vis=new boolean[adj_lst.length];
            intime=new int[adj_lst.length];
            outtime=new int[adj_lst.length];
            time=0;
            for(int i=0;i<adj_lst.length;i++) {
                adj_lst[i]=new ArrayList<Integer>();
            }
            //No of edges
            for(int i=m;i>0;i--) {
                // input u & v
                int u=input.nextInt();
                int v=input.nextInt();
                u--;
                v--;
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
            for(int i=0;i<n;i++) {
                if(vis[i]) {
                    continue;
                }
                
                mod_DFS(i);
                if(vis[a] && vis[b]) {
                    break;
                }
                else {
                    intime=new int[adj_lst.length];
                    outtime=new int[adj_lst.length];
                    time=0;
                }
            }
            int count_1=0,count_2=0;
            for(int i=0;i<n;i++) {
                
            }
        }
        
    }
    
    public static void mod_DFS(int root) {
//        System.out.println(root);
        vis[root]=true;
        time++;
        intime[root]=time;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(!vis[adj_lst[root].get(i)]) {
                mod_DFS(adj_lst[root].get(i));
            }
        }
        time++;
        outtime[root]=time;
    }
}
