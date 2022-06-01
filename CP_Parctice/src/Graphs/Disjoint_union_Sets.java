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
public class Disjoint_union_Sets {
    static int parent[];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        //no of nodes
        int n=input.nextInt();
        parent=new int[n];
        for(int i=0;i<n;i++) {
            parent[i]=i;
        }
        while(true) {
            int in=input.nextInt();
            if(in==1) {
                union(input.nextInt(),input.nextInt());
            }
            else if(in==2){
                System.out.println(find(input.nextInt()));
            }
            else {
                break;
            }
        }
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
