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
public class Adj_List {
    static ArrayList<Integer> adj_lst[];
    public static void main(String args[]){
        Scanner input=new Scanner(System.in);
        //No of nodes
        int n=input.nextInt();
        adj_lst=new ArrayList[n];
        for(int i=0;i<adj_lst.length;i++) {
            adj_lst[i]=new ArrayList<Integer>();
        }
        //No of edges
        int m=input.nextInt();
        for(int i=0;i<m;i++) {
            // input u & v
            int u=input.nextInt();
            int v=input.nextInt();
            adj_lst[u].add(v);
            adj_lst[v].add(u);
        }
        for(int i=0;i<adj_lst.length;i++) {
            System.out.print(i+"->");
            for(int j=0;j<adj_lst[i].size();j++) {
                System.out.print(adj_lst[i].get(j)+" ");
            }
            System.out.println();
        }
    }
}
