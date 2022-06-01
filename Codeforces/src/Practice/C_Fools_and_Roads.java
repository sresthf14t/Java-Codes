/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

/**
 *
 * @author SRESTH
 */
import java.util.*;
import java.io.*;
public class C_Fools_and_Roads {
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
    
    public static void sort(int arr[],int l,int r) {    //sort(arr,0,n-1);
        if(l==r) {
            return;
        }
        int mid=(l+r)/2;
        sort(arr,l,mid);
        sort(arr,mid+1,r);
        merge(arr,l,mid,mid+1,r);
    }
    public static void merge(int arr[],int l1,int r1,int l2,int r2) {
        int tmp[]=new int[r2-l1+1];
        int indx1=l1,indx2=l2;
        //sorting the two halves using a tmp array
        for(int i=0;i<tmp.length;i++) {
            if(indx1>r1) {
                tmp[i]=arr[indx2];
                indx2++;
                continue;
            }
            if(indx2>r2) {
                tmp[i]=arr[indx1];
                indx1++;
                continue;
            }
            if(arr[indx1]<arr[indx2]) {
                tmp[i]=arr[indx1];
                indx1++;
                continue;
            }
            tmp[i]=arr[indx2];
            indx2++;
        }
        //Copying the elements of tmp into the main array
        for(int i=0,j=l1;i<tmp.length;i++,j++) {
            arr[j]=tmp[i];
        }
    }
    
    public static void sort(long arr[],int l,int r) {    //sort(arr,0,n-1);
        if(l==r) {
            return;
        }
        int mid=(l+r)/2;
        sort(arr,l,mid);
        sort(arr,mid+1,r);
        merge(arr,l,mid,mid+1,r);
    }
    public static void merge(long arr[],int l1,int r1,int l2,int r2) {
        long tmp[]=new long[r2-l1+1];
        int indx1=l1,indx2=l2;
        //sorting the two halves using a tmp array
        for(int i=0;i<tmp.length;i++) {
            if(indx1>r1) {
                tmp[i]=arr[indx2];
                indx2++;
                continue;
            }
            if(indx2>r2) {
                tmp[i]=arr[indx1];
                indx1++;
                continue;
            }
            if(arr[indx1]<arr[indx2]) {
                tmp[i]=arr[indx1];
                indx1++;
                continue;
            }
            tmp[i]=arr[indx2];
            indx2++;
        }
        //Copying the elements of tmp into the main array
        for(int i=0,j=l1;i<tmp.length;i++,j++) {
            arr[j]=tmp[i];
        }
    }
    
    
    static  class seg_tree {
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
    
    
    static ArrayList<Integer> adj_lst[],et,etd;
    static int parent[],euler_tour_depth[],euler_tour[],freq[],cnt[];
    static HashMap<String,Integer> map;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        
        int n=input.scanInt();
        adj_lst=new ArrayList[n];
        parent=new int[n];
        et=new ArrayList<>();
        etd=new ArrayList<>();
        freq=new int[n];
        cnt=new int[n];
        map=new HashMap<>();
        int edges[][]=new int[n-1][2];
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<>();
        }
        for(int i=0;i<n-1;i++) {
            int u=input.scanInt()-1;
            int v=input.scanInt()-1;
            map.put(Math.min(u,v)+" "+Math.max(u,v), 0);
            edges[i][0]=Math.min(u,v);
            edges[i][1]=Math.max(u,v);
            adj_lst[u].add(v);
            adj_lst[v].add(u);
        }
        DFS(0,-1,0);
        euler_tour_depth=new int[et.size()];
        euler_tour=new int[etd.size()];
        for(int i=0;i<et.size();i++) {
            euler_tour[i]=et.get(i);
            euler_tour_depth[i]=etd.get(i);
        }
        seg_tree st=new seg_tree(euler_tour_depth.length,euler_tour_depth);
        int first[]=new int[n];
        Arrays.fill(first, -1);
        for(int i=0;i<euler_tour.length;i++) {
            if(first[euler_tour[i]]==-1) {
                first[euler_tour[i]]=i;
            }
        }
        int query=input.scanInt();
        for(int q=0;q<query;q++) {
            int u=input.scanInt()-1;
            int v=input.scanInt()-1;
            int l=Math.min(first[u],first[v]);
            int r=Math.max(first[u],first[v]);
            int indx=st.min(0, 0, euler_tour_depth.length-1, l, r)[1];
//            System.out.println(euler_tour[indx]+1);
            freq[u]++;
            freq[v]++;
            freq[euler_tour[indx]]-=2;
        }
        mod_DFS(0,-1);
        for(int i=0;i<n-1;i++) {
            ans.append(map.get(edges[i][0]+" "+edges[i][1])+" ");
        }
        ans.append("\n");
        System.out.print(ans);
    }
    
    public static int mod_DFS(int root,int par) {
        int total=freq[root];
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==par) {
                continue;
            }
            total+=mod_DFS(adj_lst[root].get(i),root);
        }
        map.replace(Math.min(root,par)+" "+Math.max(root,par), total);
        return total;
    }
    
    public static void DFS(int root,int par,int dep) {
        parent[root]=par;
        et.add(root);
        etd.add(dep);
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==par) {
                continue;
            }
            DFS(adj_lst[root].get(i),root,dep+1);
            et.add(root);
            etd.add(dep);
        }
    }
}
