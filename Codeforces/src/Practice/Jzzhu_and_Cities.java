/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class Jzzhu_and_Cities {
    static class Scan {
        private byte[] buf=new byte[1024];
        private int index;
        private InputStream in;
        private int total;
        public Scan()
        {
            in=System.in;
        }
        public int scan()throws IOException
        {
            if(total<0)
            throw new InputMismatchException();
            if(index>=total)
            {
                index=0;
                total=in.read(buf);
                if(total<=0)
                return -1;
            }
            return buf[index++];
        }
        public int scanInt()throws IOException
        {
            int integer=0;
            int n=scan();
            while(isWhiteSpace(n))
            n=scan();
            int neg=1;
            if(n=='-')
            {
                neg=-1;
                n=scan();
            }
            while(!isWhiteSpace(n))
            {
                if(n>='0'&&n<='9')
                {
                    integer*=10;
                    integer+=n-'0';
                    n=scan();
                }
                else throw new InputMismatchException();
            }
            return neg*integer;
        }
        public double scanDouble()throws IOException
        {
            double doub=0;
            int n=scan();
            while(isWhiteSpace(n))
            n=scan();
            int neg=1;
            if(n=='-')
            {
                neg=-1;
                n=scan();
            }
            while(!isWhiteSpace(n)&&n!='.')
            {
                if(n>='0'&&n<='9')
                {
                    doub*=10;
                    doub+=n-'0';
                    n=scan();
                }
                else throw new InputMismatchException();
            }
            if(n=='.')
            {
                n=scan();
                double temp=1;
                while(!isWhiteSpace(n))
                {
                    if(n>='0'&&n<='9')
                    {
                        temp/=10;
                        doub+=(n-'0')*temp;
                        n=scan();
                    }
                    else throw new InputMismatchException();
                }
            }
            return doub*neg;
        }
        public String scanString()throws IOException
        {
            StringBuilder sb=new StringBuilder();
            int n=scan();
            while(isWhiteSpace(n))
            n=scan();
            while(!isWhiteSpace(n))
            {
                sb.append((char)n);
                n=scan();
            }
            return sb.toString();
        }
        private boolean isWhiteSpace(int n)
        {
            if(n==' '||n=='\n'||n=='\r'||n=='\t'||n==-1)
            return true;
            return false;
        }
    }
    
    
    
    
    static class seg_tree {
        long seg_tree[],seg_indx[];
        public seg_tree(int n,long arr[]) {
            seg_tree=new long[4*n];
            seg_indx=new long[4*n];
            create_seg_tree(arr,0,0,n-1);
        }

        //0 index-Left child-(2*i+1) Right Child-(2*i+2)

        public void create_seg_tree(long arr[],int vertex,int l,int r) {
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

        public long[] min(int vertex,int l,int r,int ql,int qr) {  //ql->query left , qr-> query right l->curr Segmrnt left    r->curr segment right
            if(ql>qr) {
                return new long[]{Long.MAX_VALUE,-1};
            }

            if(ql==l && qr==r) {
                return new long[]{seg_tree[vertex],seg_indx[vertex]};
            }
            int mid=(l+r)/2;

            //Left Child
            long min1[]=min((2*vertex)+1,l,mid,ql,Math.min(qr, mid));

            //Right Child
            long min2[]=min((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr);

            if(min1[0]<min2[0]) {
                return min1;
            }
            else {
                return min2;
            }
        }

        public void update(int vertex,int l,int r,int pos,long value) {   //pos->Position of the update   value->updates value
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
    static HashMap<Integer,Integer> map[];
    static boolean vis[];
    static int n;
    static long dist[];
    static seg_tree s_tree;
    
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n=input.scanInt();
        int m=input.scanInt();
        int k=input.scanInt();
        adj_lst=new ArrayList[n];
        weight=new ArrayList[n];
        for(int i=0;i<adj_lst.length;i++) {
            adj_lst[i]=new ArrayList<Integer>();
            weight[i]=new ArrayList<Integer>();
        }
        map=new HashMap[n];
        vis=new boolean[n];
        dist=new long[n];
        int source=0;
        for(int i=0;i<m;i++) {
            // input u & v
            int u=input.scanInt()-1;
            int v=input.scanInt()-1;
            int wei=input.scanInt();
            adj_lst[u].add(v);
            adj_lst[v].add(u);
            weight[u].add(wei);
            weight[v].add(wei);
        }
        int cnt=0;
        int train[]=new int[n];
        for(int i=0;i<k;i++) {
            int dest=input.scanInt()-1;
            int dist=input.scanInt();
            if(train[dest]==0) {
                train[dest]=dist;
            }
            else if(train[dest]>dist) {
                train[dest]=dist;
                cnt++;
            }
            else {
                cnt++;
            }
        }
        for(int i=0;i<n;i++) {
            if(train[i]==0) {
                continue;
            }
            adj_lst[0].add(i);
            adj_lst[i].add(0);
            weight[0].add(train[i]);
            weight[i].add(train[i]);
        }
        dist[source]=0;
        for(int i=0;i<n;i++) {
            if(i==source) {
                continue;
            }
            dist[i]=Long.MAX_VALUE;
        }
        s_tree=new seg_tree(n,dist);
        Dijkstras(0);
        for(int i=0;i<n;i++) {
            if(train[i]==0) {
                continue;
            }
            if(map[i].size()>1) {
                cnt++;
            }
            else if(dist[i]<train[i] || map[i].get(0)>1) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }
    
    
    public static void Dijkstras(int root) {
        vis[root]=true;
        dist[root]=0;
        while(true) {
            vis[root]=true;
            for(int i=0;i<adj_lst[root].size();i++) {
                if(vis[adj_lst[root].get(i)]) {
                    continue;
                }
                if(dist[root]+weight[root].get(i)<dist[adj_lst[root].get(i)]) {
                    dist[adj_lst[root].get(i)]=dist[root]+weight[root].get(i);
                    map[adj_lst[root].get(i)]=new HashMap<>();
                    map[adj_lst[root].get(i)].put(root, 1);
                    s_tree.update(0, 0, n-1, adj_lst[root].get(i), dist[adj_lst[root].get(i)]);
                }
                else if(dist[root]+weight[root].get(i)==dist[adj_lst[root].get(i)]) {
                    if(map[adj_lst[root].get(i)].containsKey(root)) {
                        map[adj_lst[root].get(i)].replace(root, map[adj_lst[root].get(i)].get(root)+1);
                    }
                    else {
                        map[adj_lst[root].get(i)].put(root, 1);
                    }
                }
            }
            s_tree.update(0, 0, n-1, root, Long.MAX_VALUE);
            long tmp[]=s_tree.min(0, 0, n-1, 0, n-1);
            if(tmp[0]==Long.MAX_VALUE) {
                break;
            }
            root=(int)tmp[1];
        }
    }
}
