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
import java.util.*;
import java.io.*;
public class Addition_on_segments_query_for_sum_lazy_propogation {
    static class seg_tree {
        int seg_tree[],lazy[];
        public seg_tree(int n,int arr[]) {
            seg_tree=new int[4*n];
            lazy=new int[4*n];
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

            seg_tree[vertex]=seg_tree[(2*vertex)+1]+seg_tree[(2*vertex)+2];
        }

        public void push(int vertex,int l,int r) {
            int mid=(l+r)/2;
            int ll=l;
            int lr=mid;
            int rl=mid+1;
            int rr=r;
            seg_tree[(2*vertex)+1]+=(lr-ll+1)*lazy[vertex];
            lazy[(2*vertex)+1]+=lazy[vertex];
            seg_tree[(2*vertex)+2]+=(rr-rl+1)*lazy[vertex];
            lazy[(2*vertex)+2]+=lazy[vertex];
            lazy[vertex]=0;
        }

        public void update(int vertex,int l,int r,int ql,int qr,int add) {
            if(ql>qr) {
                return;
            }
            if(l==ql && r==qr) {
                seg_tree[vertex]+=(qr-ql+1)*add;
                lazy[vertex]+=add;
                return;
            }
            push(vertex,l,r);

            int mid=(l+r)/2;
            //Left Child
            update((2*vertex)+1,l,mid,ql,Math.min(mid,qr),add);
            //Right Child
            update((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr,add);

            seg_tree[vertex]=seg_tree[(2*vertex)+1]+seg_tree[(2*vertex)+2];
        }

        public int sum(int vertex,int l,int r,int ql,int qr) {
            if(ql>qr) {
                return 0;
            }
            if(ql<=l && r<=qr) {
                return seg_tree[vertex];
            }
            push(vertex,l,r);
            int mid=(l+r)/2;
            return sum((2*vertex)+1,l,mid,ql,Math.min(qr,mid))+sum((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr);
        }
    }
}
