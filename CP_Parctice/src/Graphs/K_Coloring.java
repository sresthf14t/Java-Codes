/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphs;

/**
 *
 * @author sf14t
 */
import java.util.*;
import java.io.*;
public class K_Coloring {
    static ArrayList<Integer> adj_lst[];    //To store the edges for each verted of the grapg
    static boolean vis[];   //To maintain the list of currently already visited vetices while DFS to avoid cycles
    static int color[]; //To store the color of each vertex
    public static void main(String args[]){
        Scanner input=new Scanner(System.in);
        //Strat andEnd time of rides
        System.out.println("Enter the number of rides to be scheduled");
        int n=input.nextInt();  //No. of rides
        float strt[]=new float[n];  //Start time if ride
        float end[]=new float[n];   //End time of ride
        color=new int[n];
        vis=new boolean[n];
        System.out.println("Enter the start and end time of each ride in the format\nHH MM");
        for(int i=0;i<n;i++) {
            float start_hh=input.nextInt();
            float strt_mm=input.nextInt();
            strt[i]=start_hh+(strt_mm/60);
            float end_hh=input.nextInt();
            float end_mm=input.nextInt();
            end[i]=end_hh+(end_mm/60);
        }
        create_graph(n,strt,end);   //To create the graph using the ride times
        //To perform DFS for all vertices[in case of disconnected graph]
        for(int i=0;i<n;i++) {
            if(!vis[i]) {
                DFS(i); 
            }
        }
        System.out.println();
        int total_taxi_used=Integer.MIN_VALUE;  //To fing the total number of taxi's used[1 indexed]
        for(int i=0;i<n;i++) {
            System.out.println("Ride "+(i+1)+" assigned to : Taxi "+color[i]);
             total_taxi_used=Math.max( total_taxi_used,color[i]);
        }
        System.out.println("\nTotal number of Taxi's used : "+ total_taxi_used);
    }
    
    //Depth First Search
    public static void DFS(int root) {
//        System.out.println(root);
        vis[root]=true;
        boolean used_color[]=new boolean[color.length+1];   //To find the color used in the adjecent vertices of the current vertex
        for(int i=0;i<adj_lst[root].size();i++) {
            used_color[color[adj_lst[root].get(i)]]=true;
        }
        int least_number_unused_color=-1;   //To find the least number color we can use[currently unsued]
        for(int i=1;i<used_color.length;i++) {
            if(!used_color[i]) {
                least_number_unused_color=i;
                break;
            }
        }
        color[root]=least_number_unused_color;  //Assigning tht color the the currnt vertex
        for(int i=0;i<adj_lst[root].size();i++) {
            if(!vis[adj_lst[root].get(i)]) {
                DFS(adj_lst[root].get(i));
            }
        }
    }
    public static void create_graph(int n,float strt[],float end[]) {
        //Initializing each ArrayList[representing each vertex]
        adj_lst=new ArrayList[n];
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<>();
        }
        //To find overlapping rides and creating a edge between those vertices
         for(int i=0;i<n;i++) {
            for(int j=i+1;j<n;j++) {
                if(!(end[i]<=strt[j] || strt[i]>=end[j])) {
                    adj_lst[i].add(j);
                    adj_lst[j].add(i);
                }
            }
        }
    }
}
