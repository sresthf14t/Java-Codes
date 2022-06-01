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
public class Maximum_and_number_of_times_it_appears {
    static int seg_tree[][];    //[i][0]->max   [i][1]->max_count
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int arr[]=new int[n];
        seg_tree=new int[4*n][2];
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
            int max[]=(max(0,0,n-1,ql,qr));
            System.out.println("Max="+max[0]+" Max_cnt="+max[1]);
        }
    }
    
    
    public static void combine(int vertex) {
        if(seg_tree[(2*vertex)+1][0]>seg_tree[(2*vertex)+2][0]) {
            seg_tree[vertex][0]=seg_tree[(2*vertex)+1][0];
            seg_tree[vertex][1]=seg_tree[(2*vertex)+1][1];
        }
        else if(seg_tree[(2*vertex)+1][0]<seg_tree[(2*vertex)+2][0]) {
            seg_tree[vertex][0]=seg_tree[(2*vertex)+2][0];
            seg_tree[vertex][1]=seg_tree[(2*vertex)+2][1];
        }
        else {
            seg_tree[vertex][0]=seg_tree[(2*vertex)+2][0];
            seg_tree[vertex][1]=seg_tree[(2*vertex)+1][1]+seg_tree[(2*vertex)+2][1];
        }
    }
    
    //0 index-Left child-(2*i+1) Right Child-(2*i+2)
    
    public static void create_seg_tree(int arr[],int vertex,int l,int r) {
        if(l==r) {
            seg_tree[vertex][0]=arr[r];
            seg_tree[vertex][1]=1;
            return;
        }
        int mid=(l+r)/2;
        //Left Child
        create_seg_tree(arr,(2*vertex)+1,l,mid);
        //Right Child
        create_seg_tree(arr,(2*vertex)+2,mid+1,r);
        //Filling this node
        
        combine(vertex);
    }
    
    public static int[] max(int vertex,int l,int r,int ql,int qr) {  //ql->query left , qr-> query right l->curr Segmrnt left    r->curr segment right
        if(ql>qr) {
            return new int[]{-1,-1};
        }
        
        if(ql==l && qr==r) {
            return new int[]{seg_tree[vertex][0],seg_tree[vertex][1]};
        }
        int mid=(l+r)/2;
        
        
        //Left Child
        int max1[]=max((2*vertex)+1,l,mid,ql,Math.min(qr, mid));
        
        //Right Child
        int max2[]=max((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr);
        
        if(max1[0]>max2[0]) {
            return max1;
        }
        if(max1[0]<max2[0]) {
            return max2;
        }
        return new int[]{max1[0],max1[1]+max2[1]};
    }
    
    public static void update(int vertex,int l,int r,int pos,int value) {   //pos->Position of the update   value->updates value
        if(l==r) {
            seg_tree[vertex][0]=value;
            seg_tree[vertex][1]=1;
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
        
        combine(vertex);
    }
}
