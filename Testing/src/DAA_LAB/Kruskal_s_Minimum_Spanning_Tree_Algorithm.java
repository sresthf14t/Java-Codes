/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAA_LAB;

/**
 *
 * @author Srest
 */

//O(nlog(n))
//NOT TESTED

import java.util.*;
import java.io.*;
public class Kruskal_s_Minimum_Spanning_Tree_Algorithm {
    
    
    
    static class DSU {
        int parent[],size[];
        public DSU (){
            parent=new int[n];
            size=new int[n];
            make_set(n);
        }
        public void make_set(int n) {
            for(int i=0;i<n;i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public void union(int a, int b) {
            a = find(a);
            b = find(b);
            if (a != b) {
                if (size[a] < size[b])
                    swap(a, b);
                parent[b] = a;
                size[a] += size[b];
            }
        }
        public int find(int v) {
            if (v == parent[v]) {
                return v;
            }
            parent[v] = find(parent[v]);
            return parent[v];
        }
        public void swap(int i,int j) {
            int tmp=size[i];
            size[i]=size[j];
            size[j]=tmp;
        }
    }
    
    
    
    
    
    
    
    
    
    static int n,m,u[],v[],edge[];
    static ArrayList<Integer> mst[];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        n=input.nextInt();
        m=input.nextInt();
        u=new int[m];
        v=new int[m];
        edge=new int[m];
        for(int i=0;i<m;i++) {
            u[i]=input.nextInt();
            v[i]=input.nextInt();
            edge[i]=input.nextInt();
        }
        mst=new ArrayList[n];
        for(int i=0;i<n;i++) {
            mst[i]=new ArrayList<>();
        }
        MST();
        for(int i=0;i<mst.length;i++) {
//            System.out.print(i+"->");
            for(int j=0;j<mst[i].size();j++) {
                if(mst[i].get(j)<i) {
                    continue;
                }
                System.out.println(i+" "+mst[i].get(j));
            }
        }
    }
    public static void MST() {
        DSU dsu=new DSU();
        //sort
        sort(edge,u,v,m);
        for(int i=0;i<m;i++) {
//            System.out.println(u[i]+" "+v[i]+" "+edge[i]);
            if(dsu.find(u[i])==dsu.find(v[i])) {
                continue;
            }
            mst[u[i]].add(v[i]);
            mst[v[i]].add(u[i]);
            dsu.union(u[i], v[i]);
        }
    }

    static void buildMaxHeap(int arr[],int brr[],int crr[], int n) { 
    for (int i = 1; i < n; i++) 
    { 
      // if child is bigger than parent 
      if (arr[i] > arr[(i - 1) / 2]) 
      { 
        int j = i; 
  
        // swap child and parent until 
        // parent is smaller 
        while (arr[j] > arr[(j - 1) / 2]) 
        { 
          swap(arr, j, (j - 1) / 2); 
          swap(brr, j, (j - 1) / 2); 
          swap(crr, j, (j - 1) / 2); 
          j = (j - 1) / 2; 
        } 
      } 
    } 
  } 
  
  static void sort(int arr[],int brr[],int crr[], int n) 
  { 
    buildMaxHeap(arr,brr,crr, n); 
  
    for (int i = n - 1; i > 0; i--) 
    { 
      // swap value of first indexed 
      // with last indexed 
      swap(arr, 0, i); 
      swap(brr, 0, i); 
      swap(crr, 0, i); 
      
  
      // maintaining heap property 
      // after each swapping 
      int j = 0, index; 
  
      do
      { 
        index = (2 * j + 1); 
  
        // if left child is smaller than 
        // right child point index variable 
        // to right child 
        if (index < (i - 1) && arr[index] < arr[index + 1]) 
          index++; 
  
        // if parent is smaller than child 
        // then swapping parent with child 
        // having higher value 
        if (index < i && arr[j] < arr[index]) {
          swap(arr, j, index); 
          swap(brr, j, index);
          swap(crr, j, index);
        }
  
        j = index; 
  
      } while (index < i); 
    } 
  } 
  
  public static void swap(int[] a, int i, int j) { 
    int temp = a[i]; 
    a[i]=a[j]; 
    a[j] = temp; 
  } 
}
