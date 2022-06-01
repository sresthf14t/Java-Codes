/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphs;

/**
 *
 * @author Srest
 */


//works for unweighted ad weighted graphs
//Time Complexity-O(E log V )
/*
Suppose there are n towns connected by m bidirectional roads. 
There are s towns among them with a police station. 
We want to find out the distance of each town from the nearest police station.
If the town itself has one the distance is 0.
*/
/*
Just add a zero cost edge from any one of the source vertex to all other source
vertices Then do normal Disktras from the chosen source vertex
*/
/*
----------------------------------------------------------------------------
---------------------------------NOT TESTED---------------------------------
----------------------------------------------------------------------------
*/
import java.util.*;
import java.io.*;
public class Multi_Source_Shortest_Path_weighted_Graph {
    
    
    static class seg_tree {
        int seg_tree[],seg_indx[];
        public seg_tree(int n,int arr[]) {
            seg_tree=new int[4*n];
            seg_indx=new int[4*n];
            create_seg_tree(arr,0,0,n-1);
        }

        //0 index-Left child-(2*i+1) Right Child-(2*i+2)

        public void create_seg_tree(int arr[],int vertex,int l,int r) {
            if(l==r) {
                seg_indx[vertex]=r;
                seg_tree[vertex]=arr[r];
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
                return new int[]{Integer.MAX_VALUE,-1};
            }

            if(ql==l && qr==r) {
                return new int[]{seg_tree[vertex],seg_indx[vertex]};
            }
            int mid=(l+r)/2;

            //Left Child
            int min1[]=min((2*vertex)+1,l,mid,ql,Math.min(qr, mid));

            //Right Child
            int min2[]=min((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr);

            if(min1[0]<min2[0]) {
                return min1;
            }
            else {
                return min2;
            }
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
    
    
    
    
    
    
    
    static ArrayList<Integer> adj_lst[],weight[];
    static int n,dist[];
    static boolean vis[];
    static seg_tree s_tree;
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        n=input.nextInt();
        int m=input.nextInt();
        adj_lst=new ArrayList[n];
        weight=new ArrayList[n];
        dist=new int[n];
        vis=new boolean[n];
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<Integer>();
            weight[i]=new ArrayList<Integer>();
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
        int src_cnt=input.nextInt();
        ArrayList<Integer> source=new ArrayList<>();
        for(int i=0;i<src_cnt;i++) {
            source.add(input.nextInt());
        }
        
        //Adding 0 cost edge from first soruce to rest sources
        
        for(int i=1;i<source.size();i++) {
            int u=source.get(0);
            int v=source.get(i);
            int wei=0;
            adj_lst[u].add(v);
            adj_lst[v].add(u);
            weight[u].add(wei);
            weight[v].add(wei);
        }
        
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source.get(0)]=0;
        s_tree=new seg_tree(n,dist);
        Dijkstras(source.get(0));
        System.out.println();
        for(int i=0;i<n;i++) {
            System.out.println(i+" "+(dist[i]));
        }
        
    }
    
    
    public static void Dijkstras(int root) {
        vis[root]=true;
        dist[root]=0;
        while(true) {
//            System.out.println(root);
            vis[root]=true;
            for(int i=0;i<adj_lst[root].size();i++) {
                if(vis[adj_lst[root].get(i)]) {
                    continue;
                }
                if(dist[root]+weight[root].get(i)<dist[adj_lst[root].get(i)]) {
                    dist[adj_lst[root].get(i)]=dist[root]+weight[root].get(i);
                    s_tree.update(0, 0, n-1, adj_lst[root].get(i), dist[adj_lst[root].get(i)]);
                }
            }
            s_tree.update(0, 0, n-1, root, Integer.MAX_VALUE);
            int tmp[]=s_tree.min(0, 0, n-1, 0, n-1);
            if(tmp[0]==Integer.MAX_VALUE) {
                break;
            }
            root=tmp[1];
        }
    }
}
