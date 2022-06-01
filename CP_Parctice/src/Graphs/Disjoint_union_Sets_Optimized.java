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
/*
Worst Case TC: O(log n)
Avrage Case TC: O(1)
*/

//Size can aslo be used instead of rank and size stores the size of the set code in CP Algorithms
import java.util.Scanner;
public class Disjoint_union_Sets_Optimized {
    static int parent[],size[];
    public static void main(String args[]){
        Scanner input=new Scanner(System.in);
        //no of nodes
        int n=input.nextInt();
        StringBuilder ans=new StringBuilder("");
        parent=new int[n];
        size=new int[n];
        make_set(n);
        int q=input.nextInt();
        for(int i=0;i<q;i++) {
            int t=input.nextInt();
            int u=input.nextInt();
            int v=input.nextInt();
            if(t==0) {
                union(u,v);
            }
            else {
                if(find(u)==find(v)) {
                    ans.append(1+"\n");
                }
                else {
                    ans.append(0+"\n");
                }
            }
        }
        System.out.println(ans);
    }
    public static void make_set(int n) {
        for(int i=0;i<n;i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            if (size[a] < size[b])
                swap(a, b);
            parent[b] = a;
            size[a] += size[b];
        }
    }
    public static int find(int v) {
        if (v == parent[v]) {
            return v;
        }
        parent[v] = find(parent[v]);
        return parent[v];
    }
    public static void swap(int i,int j) {
        int tmp=size[i];
        size[i]=size[j];
        size[j]=tmp;
    }
}
