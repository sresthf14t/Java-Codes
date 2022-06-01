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
public class Prim_s_Minimum_Spanning_Tree {
    
    
    public static class seg_tree {
        int seg_tree[],seg_indx[];
        public seg_tree(int n,int arr[]) {
            seg_tree=new int[4*n];
            seg_indx=new int[4*n];
            create_seg_tree(arr,0,0,n-1);
        }

        //0 index-Left child-(2*i+1) Right Child-(2*i+2)

        public void create_seg_tree(int arr[],int vertex,int l,int r) {
            if(l==r) {
                seg_tree[vertex]=arr[r];
                seg_indx[vertex]=r;
                return;
            }
            int mid=(l+r)/2;
            //Left Child
            create_seg_tree(arr,(2*vertex)+1,l,mid);
            //Right Child
            create_seg_tree(arr,(2*vertex)+2,mid+1,r);
            //Filling this node
            if(seg_tree[(2*vertex)+1]<seg_tree[(2*vertex)+2]) {
                seg_tree[vertex]=seg_tree[(2*vertex)+1];
                seg_indx[vertex]=seg_indx[(2*vertex)+1];
            }
            else {
                seg_tree[vertex]=seg_tree[(2*vertex)+2];
                seg_indx[vertex]=seg_indx[(2*vertex)+2];
            }
        }

        public int[] min(int vertex,int l,int r,int ql,int qr) {  //ql->query left , qr-> query right l->curr Segmrnt left    r->curr segment right
            if(ql>qr) {
                return new int[]{Integer.MAX_VALUE,0};
            }

            if(ql==l && qr==r) {
                return new int[]{seg_tree[vertex],seg_indx[vertex]};
            }
            int mid=(l+r)/2;

            int min1[]=min((2*vertex)+1,l,mid,ql,Math.min(qr, mid));
            int min2[]=min((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr);
            if(min1[0]<min2[0]) {
                return min1;
            }
            return min2;
        }

        public void update(int vertex,int l,int r,int pos,int value) {   //pos->Position of the update   value->updates value
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
            if(seg_tree[(2*vertex)+1]<seg_tree[(2*vertex)+2]) {
                seg_tree[vertex]=seg_tree[(2*vertex)+1];
                seg_indx[vertex]=seg_indx[(2*vertex)+1];
            }
            else {
                seg_tree[vertex]=seg_tree[(2*vertex)+2];
                seg_indx[vertex]=seg_indx[(2*vertex)+2];
            }
        }
    }

    
    
    static int n,m;
    static ArrayList<Integer> adj_lst[],weight[],mst[];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        n=input.nextInt();
        m=input.nextInt();
        adj_lst=new ArrayList[n];
        weight=new ArrayList[n];
        mst=new ArrayList[n];
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<>();
            weight[i]=new ArrayList<>();
            mst[i]=new ArrayList<>();
        }
        for(int i=0;i<m;i++) {
            int u=input.nextInt();
            int v=input.nextInt();
            int wei=input.nextInt();
            adj_lst[u].add(v);
            adj_lst[v].add(u);
            weight[u].add(wei);
            weight[v].add(wei);
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
        boolean taken[]=new boolean[n];
        int tmp[]=new int[n];
        int parent[]=new int[n];
        Arrays.fill(parent, -1);
        Arrays.fill(tmp, Integer.MAX_VALUE-1);
        seg_tree st=new seg_tree(n,tmp);
        while(true) {
            if(st.min(0, 0, n-1, 0, n-1)[0]==Integer.MAX_VALUE) {
                break;
            }
            int indx=st.min(0, 0, n-1, 0, n-1)[1];
            for(int j=0;j<adj_lst[indx].size();j++) {
                if(taken[adj_lst[indx].get(j)]) {
                    continue;
                }
                if(st.min(0, 0, n-1, adj_lst[indx].get(j), adj_lst[indx].get(j))[0] > weight[indx].get(j)) {
                    st.update(0, 0, n-1,adj_lst[indx].get(j), weight[indx].get(j));
                    parent[adj_lst[indx].get(j)]=indx;
                }
            }
            taken[indx]=true;
            if(parent[indx]!=-1) {
                mst[indx].add(parent[indx]);
                mst[parent[indx]].add(indx);
            }
            st.update(0, 0, n-1, indx, Integer.MAX_VALUE);
        }
    }
}
