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

/*Suppose now that the modification query asks to assign each element of a 
certain segment a[l…r] to some value p. As a second query we will again
consider reading the value of the array a[i].*/

import java.util.*;
import java.io.*;
public class Assignment_on_segments_Lazy_Propogation {
    static int seg_tree[];
    static boolean marked[];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int arr[]=new int[n];
        seg_tree=new int[4*n];
        marked=new boolean[4*n];
        for(int i=0;i<n;i++) {
            arr[i]=input.nextInt();
        }
        built(arr,0,0,n-1);
        int query=input.nextInt();
        for(int q=1;q<=query;q++) {
            System.out.println("UPDATE");
            update(0,0,n-1,input.nextInt(),input.nextInt(),input.nextInt());
            while(input.nextInt()!=-1) {
                System.out.println("get");
                System.out.println(get(0,0,n-1,input.nextInt()));
                System.out.println("Exit");
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
        
        seg_tree[vertex]=0;
    }
    
    public static void push(int vertex) {
        if(marked[vertex]) {
            seg_tree[(2*vertex)+1]=seg_tree[(2*vertex)+2]=seg_tree[vertex];
            marked[(2*vertex)+1]=marked[(2*vertex)+2]=true;
            marked[vertex]=false;
        }
        
    }
    
    public static void update(int vertex,int l,int r,int ql,int qr,int value) {
        if(ql>qr) {
            return;
        }
        if(l==ql && r==qr) {
            seg_tree[vertex]=value;
            marked[vertex]=true;
            return;
        }
        push(vertex);
        int mid=(l+r)/2;
        //Left Child
        update((2*vertex)+1,l,mid,ql,Math.min(mid,qr),value);
        //Right Child
        update((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr,value);
    }
    
    public static int get(int vertex, int l,int r ,int pos) {
        if(l==r) {
            return seg_tree[vertex];
        }
        push(vertex);
        int mid=(l+r)/2;
        if(pos<=mid) {
            return get((2*vertex)+1,l,mid,pos);
        }
        else {
            return get((2*vertex)+2,mid+1,r,pos);
        }
    }
}
