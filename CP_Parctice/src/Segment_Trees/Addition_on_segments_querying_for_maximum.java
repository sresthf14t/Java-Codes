/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Segment_Trees;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class Addition_on_segments_querying_for_maximum {
    static int seg_tree[],lazy[];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int arr[]=new int[n];
        seg_tree=new int[4*n];
        lazy=new int[4*n];
        for(int i=0;i<n;i++) {
            arr[i]=input.nextInt();
        }
        built(arr,0,0,n-1);
        int query=input.nextInt();
        for(int q=1;q<=query;q++) {
            int type=input.nextInt();
            if(type==1) {
                System.out.println(11111);
                System.out.println(find_max(0,0,n-1,input.nextInt()-1,input.nextInt()-1));
            }
            else {
                update(0,0,n-1,input.nextInt()-1,input.nextInt()-1,input.nextInt());
            }
        }
    }
    
    public static void built(int arr[], int vertex, int l,int r) {
        if(l==r) {
            seg_tree[vertex]=arr[r];
            return;
        }
        int mid=(l+r)/2;
        
        //Left Child
        built(arr,(2*vertex)+1, l, mid);
        
        //Right Child
        built(arr,(2*vertex)+2, mid+1, r);
        
        seg_tree[vertex]=Math.max(seg_tree[(2*vertex)+1],seg_tree[(2*vertex)+2]);
    }
    
    public static void push(int vertex) {
        seg_tree[(2*vertex)+1]+=lazy[vertex];
        lazy[(2*vertex)+1]+=lazy[vertex];
        
        seg_tree[(2*vertex)+2]+=lazy[vertex];
        lazy[(2*vertex)+2]+=lazy[vertex];
        
        lazy[vertex]=0;
    }
    
    public static void update(int vertex,int l,int r,int ql,int qr,int add) {
        
        if(ql>qr) {
            return;
        }
        if(l==ql && r==qr) {
            seg_tree[vertex]+=add;
            lazy[vertex]+=add;
            return;
        }
        push(vertex);
        
        int mid=(l+r)/2;
        //Left Child
        update((2*vertex)+1,l,mid,ql,Math.min(mid,qr),add);
        //Right Child
        update((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr,add);
        
        seg_tree[vertex]=Math.max(seg_tree[(2*vertex)+1],seg_tree[(2*vertex)+2]);
    }
    
    public static int find_max(int vertex,int l,int r,int ql,int qr) {
//        System.out.println(vertex+" "+l+" "+r+" "+ql+" "+qr);
        if(ql>qr) {
            return Integer.MIN_VALUE;
        }
        if(ql<=l && r<=qr) {
            return seg_tree[vertex];
        }
        push(vertex);
        int mid=(l+r)/2;
        return Math.max(find_max((2*vertex)+1,l,mid,ql,Math.min(qr,mid)),find_max((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr));
    }
    
}
