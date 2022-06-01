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
import java.util.Scanner;
public class Union_Find_Algorithm_Detect_Cycle_in_an_Undirected_Graph {
    static int parent[],src[],dest[];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        //no of nodes
        int n=input.nextInt();
        //No of edges
        int m=input.nextInt();
        parent=new int[n];
        src=new int[m];
        dest=new int[m];
        for(int i=0;i<n;i++) {
            parent[i]=i;
        }
        for(int i=0;i<m;i++) {
            src[i]=input.nextInt();
            dest[i]=input.nextInt();
        }
        System.out.println(cycle_detection());
    }
    public static boolean cycle_detection() {
        for(int i=0;i<src.length;i++) {
//            System.out.println(i);
            if(find(src[i])==find(dest[i])) {
                return true;
            }
            union(src[i],dest[i]);
        }
        return false;
    }
    public static void union(int x,int y) {
        int x_root=find(x);
        int y_root=find(y);
        if(x_root==y_root) {
            return;
        }
        parent[x_root]=y;
    }
    public static int find(int x) {
        if(parent[x]==x) {
            return x;
        }
        return find(parent[x]);
    }
}
