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
public class Maximum_Range_Update_Qureris {
    static int seg_tree[];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int arr[]=new int[n];
        seg_tree=new int[4*n];
        for(int i=0;i<n;i++) {
            arr[i]=input.nextInt();
        }
        create_seg_tree(arr,0,0,n-1);
        
        int query=input.nextInt();
        for(int q=1;q<=query;q++) {
            System.out.println("UPDATE");
            update(0, 0, n-1,input.nextInt(),input.nextInt());
            System.out.println("Query");
            int ql=input.nextInt();
            int qr=input.nextInt();
            System.out.println(max(0,0,n-1,ql,qr));
        }
    }
    
    //0 index-Left child-(2*i+1) Right Child-(2*i+2)
    
    public static void create_seg_tree(int arr[],int vertex,int l,int r) {
        if(l==r) {
            seg_tree[vertex]=arr[r];
            return;
        }
        int mid=(l+r)/2;
        //Left Child
        create_seg_tree(arr,(2*vertex)+1,l,mid);
        //Right Child
        create_seg_tree(arr,(2*vertex)+2,mid+1,r);
        //Filling this node
        seg_tree[vertex]=Math.max(seg_tree[(2*vertex)+1],seg_tree[(2*vertex)+2]);
    }
    
    public static int max(int vertex,int l,int r,int ql,int qr) {  //ql->query left , qr-> query right l->curr Segmrnt left    r->curr segment right
        if(ql>qr) {
            return Integer.MIN_VALUE;
        }
        
        if(ql==l && qr==r) {
            return seg_tree[vertex];
        }
        int mid=(l+r)/2;
        
        int max=Integer.MIN_VALUE;
        
        //Left Child
        max=Math.max(max,max((2*vertex)+1,l,mid,ql,Math.min(qr, mid)));
        
        //Right Child
        max=Math.max(max,max((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr));
        
        return max;
    }
    
    public static void update(int vertex,int l,int r,int pos,int value) {   //pos->Position of the update   value->updates value
        if(l==r) {
            seg_tree[vertex]=value;
            return;
        }
        int mid=(l+r)/2;
        //Left Child
        if(pos<=mid) {
            update((2*vertex)+1,l,mid,pos,value);
        }
        //Right Child
        else {
            update((2*vertex)+2,mid+1,r,pos,value);
        }
        seg_tree[vertex]=Math.max(seg_tree[(2*vertex)+1],seg_tree[(2*vertex)+2]);
    }
}
