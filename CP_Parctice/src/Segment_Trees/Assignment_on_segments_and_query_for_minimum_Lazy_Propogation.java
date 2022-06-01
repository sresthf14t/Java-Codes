/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Segment_Trees;

/**
 *
 * @author Srest
 */

//Stress Tested

public class Assignment_on_segments_and_query_for_minimum_Lazy_Propogation {
    public static class seg_tree {
        int seg_tree[],lazy[];
        boolean val_in_lazy[];
        public seg_tree(int n,int arr[]) {
            seg_tree=new int[4*n];
            lazy=new int[4*n];
            val_in_lazy=new boolean[4*n];
            built(arr,0,0,n-1);
        }

        public void built(int arr[], int vertex, int l,int r) {
            if(l==r) {
                seg_tree[vertex]=arr[r];
                return;
            }
            int mid=(l+r)/2;

            //Left Child
            built(arr,(2*vertex)+1, l, mid);

            //Right Child
            built(arr,(2*vertex)+2, mid+1, r);

            seg_tree[vertex]=Math.min(seg_tree[(2*vertex)+1],seg_tree[(2*vertex)+2]);
        }

        public void push(int vertex) {
            
            if(!val_in_lazy[vertex]) {
                return;
            }
            
            seg_tree[(2*vertex)+1]=lazy[vertex];
            lazy[(2*vertex)+1]=lazy[vertex];

            seg_tree[(2*vertex)+2]=lazy[vertex];
            lazy[(2*vertex)+2]=lazy[vertex];
            
            lazy[vertex]=0;
            
            val_in_lazy[vertex]=false;
            val_in_lazy[(2*vertex)+1]=true;
            val_in_lazy[(2*vertex)+2]=true;
        }

        public void update(int vertex,int l,int r,int ql,int qr,int add) {

            if(ql>qr) {
                return;
            }
            if(l==ql && r==qr) {
                seg_tree[vertex]=add;
                lazy[vertex]=add;
                val_in_lazy[vertex]=true;
                return;
            }
            push(vertex);

            int mid=(l+r)/2;
            //Left Child
            update((2*vertex)+1,l,mid,ql,Math.min(mid,qr),add);
            //Right Child
            update((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr,add);

            seg_tree[vertex]=Math.min(seg_tree[(2*vertex)+1],seg_tree[(2*vertex)+2]);
        }

        public int find_min(int vertex,int l,int r,int ql,int qr) {
    //        System.out.println(vertex+" "+l+" "+r+" "+ql+" "+qr);
            if(ql>qr) {
                return Integer.MAX_VALUE;
            }
            if(ql<=l && r<=qr) {
                return seg_tree[vertex];
            }
            push(vertex);
            int mid=(l+r)/2;
            return Math.min(find_min((2*vertex)+1,l,mid,ql,Math.min(qr,mid)),find_min((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr));
        }
        
        public void print() {
            for(int i=0;i<seg_tree.length;i++) {
                System.out.print(seg_tree[i]+" ");
            }
            System.out.println();
            for(int i=0;i<lazy.length;i++) {
                System.out.print(lazy[i]+" ");
            }
            System.out.println();
        }
    }
}
