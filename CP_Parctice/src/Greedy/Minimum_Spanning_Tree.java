/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Greedy;

/**
 *
 * @author User
 */
import java.io.*; 
import java.util.*;

class Min_Heap {
    //Left child=2*parent_indx+1;
    //Right child=2*parent_indx+2;
    //Parent=(child_indx-1)/2;
    static ArrayList<Integer> min_heap;
    public static void insert(int data) {
        min_heap.add(data);
        ins_heapify();
    }
    public static void ins_heapify() {
        int indx=min_heap.size()-1;
        while(min_heap.get(indx)<min_heap.get((indx-1)/2)) {
            int tmp=min_heap.get(indx);
            min_heap.set(indx, min_heap.get((indx-1)/2));
            min_heap.set((indx-1)/2,tmp);
            indx=(indx-1)/2;
        }
    }

    public static void del_heapify() {
        int indx=0;
        while(true) {
            int tmp_indx=-1;
            if((2*indx+1)<min_heap.size() && (2*indx+2)<min_heap.size()) {
                if(min_heap.get(2*indx+1)<min_heap.get(2*indx+2) && min_heap.get(2*indx+1)<min_heap.get(indx)) {
                    tmp_indx=2*indx+1;
                }
                else if(min_heap.get(2*indx+2)<min_heap.get(indx)){
                    tmp_indx=2*indx+2;
                }
            }
            else if((2*indx+1)<min_heap.size() && min_heap.get(2*indx+1)<min_heap.get(indx)) {
                tmp_indx=2*indx+1;
            }
            else if((2*indx+2)<min_heap.size() && min_heap.get(2*indx+2)<min_heap.get(indx)) {
                tmp_indx=2*indx+2;
            }
            if(tmp_indx==-1) {
                break;
            }
            int tmp=min_heap.get(indx);
            min_heap.set(indx, min_heap.get(tmp_indx));
            min_heap.set(tmp_indx,tmp);
            indx=tmp_indx;
        }
    }
    
    public static int delete_min() {
        int min=-1;
        if(min_heap.size()>0) {
            min=min_heap.get(0);
            min_heap.set(0, min_heap.get(min_heap.size()-1));
            min_heap.remove(min_heap.size()-1);
            if(min_heap.size()>0) {
                del_heapify();
            }
        }
        return min;
    }
    
    public static void print() {
        for(int i=0;i<min_heap.size();i++) {
            System.out.print(min_heap.get(i)+" ");
        }
        System.out.println();
    }
}

public class Minimum_Spanning_Tree {
    static ArrayList<Integer> adj_lst[],weight[];
    static int parent[];
    static boolean mst_set[];
    public static void main(String args[]){
        Scanner input=new Scanner(System.in);
        //No of nodes
        adj_lst=new ArrayList[input.nextInt()];
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
    }
    static void MST(int source) {
        mst_set[source]=true;
        parent[source]=-1;
        for(int i=1;i<adj_lst.length;i++) {
            
        }
    }
}
