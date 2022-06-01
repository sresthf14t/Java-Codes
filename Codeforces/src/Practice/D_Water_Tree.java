/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class D_Water_Tree {
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
 
    
    
    
    
    
    
    
    static ArrayList<Integer> adj_lst[],et;
    static int parent[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        int n=input.scanInt();
        adj_lst=new ArrayList[n];
        parent=new int[n];
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<>();
        }
        et=new ArrayList<>();
        for(int i=0;i<n-1;i++) {
            int u=input.scanInt()-1;
            int v=input.scanInt()-1;
            adj_lst[u].add(v);
            adj_lst[v].add(u);
        }
        DFS(0,-1);
        
        int fst_indx[]=new int[n];
        int lst_indx[]=new int[n];
        Arrays.fill(fst_indx, -1);
        for(int i=0;i<et.size();i++) {
            if(fst_indx[et.get(i)]==-1) {
                fst_indx[et.get(i)]=i;
            }
            lst_indx[et.get(i)]=i;
        }
        
        seg_tree st=new seg_tree(et.size(),new int[et.size()]);
        
        int status[]=new int[n];
        int q=input.scanInt();
        for(int i=0;i<q;i++) {
            int type=input.scanInt();
            int u=input.scanInt()-1;
            if(type==1) {
                int min=st.find_min(0, 0, et.size()-1, fst_indx[u], lst_indx[u]);
                if(min==0 && parent[u]!=-1) {
                    st.update(0, 0, et.size()-1, fst_indx[parent[u]], fst_indx[parent[u]], 0);
                }
                st.update(0, 0, et.size()-1, fst_indx[u], lst_indx[u], 1);
            }
            if(type==2) {
                st.update(0, 0, et.size()-1, fst_indx[u], fst_indx[u], 0);
            }
            if(type==3) {
                int min=st.find_min(0, 0, et.size()-1, fst_indx[u], lst_indx[u]);
                ans.append(min+"\n");
            }
        }
        
        System.out.println(ans);
    }
    
    public static void DFS(int root,int par) {
        parent[root]=par;
        et.add(root);
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==par) {
                continue;
            }
            DFS(adj_lst[root].get(i),root);
            et.add(root);
        }
    }
}
