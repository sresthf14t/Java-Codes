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

/*We begin by considering problems of the simplest form: the modification 
query should add a number x to all numbers in the segment a[l…r]. 
The second query, that we are supposed to answer, asked simply for the
value of a[i].*/

import java.util.*;
import java.io.*;
public class Addition_on_segments_Lazy_Propogation {
    static int seg_tree[];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int arr[]=new int[n];
        seg_tree=new int[4*n];
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
    
    public static void update(int vertex, int l, int r, int ql, int qr , int add) {
        if(ql>qr) {
            return;
        }
        if(l==ql && r==qr) {
            seg_tree[vertex]+=add;
            return;
        }
        int mid=(l+r)/2;
        //Left Child
        update((2*vertex)+1,l,mid,ql,Math.min(mid,qr),add);
        //Right Child
        update((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr,add);
    }
    public static int get(int vertex, int l, int r, int pos) {
        if(l==r) {
            return seg_tree[vertex];
        }
        int mid=(l+r)/2;
        //Left Child
        if(pos<=mid) {
           return seg_tree[vertex]+get((2*vertex)+1,l,mid,pos); 
        }
        //Right Child
        else {
            return seg_tree[vertex]+get((2*vertex)+2,mid+1,r,pos);
        }
    }
}
