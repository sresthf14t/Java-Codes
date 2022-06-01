/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphs;

/**
 *
 * @author Srest
 */

//Only works for unweighted graphs
//Time Complexity-O(V + E)
/*
uppose there are n towns connected by m bidirectional roads. 
There are s towns among them with a police station. 
We want to find out the distance of each town from the nearest police station.
If the town itself has one the distance is 0.
*/
/*
Just add a all the source vertices to the queue initally and initialize their 
distances to 0 and rest distances to MAX_VALUE, then do normal depth finding 
BFS to gest closest source vertex to all other vertices
*/
import java.util.*;
import java.io.*;
public class Multi_Source_Shortest_Path_Unweighted_Graph {
    static ArrayList<Integer> adj_lst[];
    static int dist[];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int m=input.nextInt();
        adj_lst=new ArrayList[n];
        dist=new int[n];
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<Integer>();
        }
        for(int i=0;i<m;i++) {
            int u=input.nextInt();
            int v=input.nextInt();
            adj_lst[u].add(v);
            adj_lst[v].add(u);
        }
        int src_cnt=input.nextInt();
        ArrayList<Integer> source=new ArrayList<>();
        for(int i=0;i<src_cnt;i++) {
            source.add(input.nextInt());
        }
        Multi_src_BFS(n,source);
        for(int i=0;i<n;i++) {
            System.out.print(dist[i]+" ");
        }
        System.out.println();
    }
    public static void Multi_src_BFS(int n,ArrayList<Integer> source) {
        boolean vis[]=new boolean[n];
        Queue<Integer> que=new LinkedList<>();
        Arrays.fill(dist, Integer.MAX_VALUE/2);
        for(int i=0;i<source.size();i++) {
            que.add(source.get(i));
            vis[source.get(i)]=true;
            dist[source.get(i)]=0;
        }
        while(!que.isEmpty()) {
            int root=que.poll();
            for(int i=0;i<adj_lst[root].size();i++) {
                if(vis[adj_lst[root].get(i)]) {
                    continue;
                }
                que.add(adj_lst[root].get(i));
                vis[adj_lst[root].get(i)]=true;
                dist[adj_lst[root].get(i)]=Math.min(dist[adj_lst[root].get(i)],dist[root]+1);
            }
        }
    }
}
