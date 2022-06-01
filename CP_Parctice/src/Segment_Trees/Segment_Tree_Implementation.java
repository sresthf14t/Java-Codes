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
public class Segment_Tree_Implementation {
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
        seg_tree[vertex]=seg_tree[(2*vertex)+1]+seg_tree[(2*vertex)+2];
    }
}
