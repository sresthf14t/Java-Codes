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
public class Dijkstras_Shortest_Path_Algorithm {
    static ArrayList<Integer> adj_lst[],weight[];
    static boolean vis[];
    static int dist[],prev[];
    public static void main(String args[]){
        Scanner input=new Scanner(System.in);
        //No of nodes
        adj_lst=new ArrayList[input.nextInt()];
        weight=new ArrayList[adj_lst.length];
        vis=new boolean[adj_lst.length];
        dist=new int[adj_lst.length];
        prev=new int[adj_lst.length];
        for(int i=0;i<adj_lst.length;i++) {
            adj_lst[i]=new ArrayList<Integer>();
            weight[i]=new ArrayList<Integer>();
        }
        //No of edges
        for(int i=input.nextInt();i>0;i--) {
            // input u & v
            int u=input.nextInt();
            int v=input.nextInt();
            int w=input.nextInt();
            adj_lst[u].add(v);
            adj_lst[v].add(u);
            weight[u].add(w);
            weight[v].add(w);
        }
//        for(int i=0;i<adj_lst.length;i++) {
//            System.out.print(i+"->");
//            for(int j=0;j<adj_lst[i].size();j++) {
//                System.out.print(adj_lst[i].get(j)+" ");
//            }
//            System.out.println();
//        }
        System.out.println("\n");
        Dijkstras(0);
        for(int i=0;i<adj_lst.length;i++) {
            System.out.println(i+"->"+dist[i]+" "+prev[i]);
        }
    }
    
    public static void Dijkstras(int source) {
        int curr_indx=source;
        while(true) {
            vis[curr_indx]=true;
            for(int i=0;i<adj_lst[curr_indx].size();i++) {
                if(adj_lst[curr_indx].get(i).equals(new Integer(source))) {
                    continue;
                }
                int tmp_dist=dist[curr_indx]+weight[curr_indx].get(i);
                if(dist[adj_lst[curr_indx].get(i)]==0) {
                    dist[adj_lst[curr_indx].get(i)]=tmp_dist;
                    prev[adj_lst[curr_indx].get(i)]=curr_indx;
                }
                else if(tmp_dist<dist[adj_lst[curr_indx].get(i)]) {
                    dist[adj_lst[curr_indx].get(i)]=tmp_dist;
                    prev[adj_lst[curr_indx].get(i)]=curr_indx;
                }
            }
            int min_dist=1999999999,nxt_indx=-1;
            for(int i=0;i<adj_lst.length;i++) {
                if(vis[i]) {
                    continue;
                }
                if(dist[i]!=0 && dist[i]<min_dist) {
                    min_dist=dist[i];
                    nxt_indx=i;
                }
            }
            if(nxt_indx==-1) {
                break;
            }
            curr_indx=nxt_indx;
        }
    }    
    
}
