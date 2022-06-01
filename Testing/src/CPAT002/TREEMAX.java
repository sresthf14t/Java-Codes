/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CPAT002;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class TREEMAX {
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
    
    
    static class seg_tree {
        int seg_tree[];
        boolean marked[];
        seg_tree(int n,int arr[]) {
            seg_tree=new int[4*n];
            marked=new boolean[4*n];
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

            seg_tree[vertex]=0;
        }

        public void push(int vertex) {
            if(marked[vertex]) {
                seg_tree[(2*vertex)+1]=Math.max(seg_tree[(2*vertex)+1],seg_tree[vertex]);
                seg_tree[(2*vertex)+2]=Math.max(seg_tree[(2*vertex)+2],seg_tree[vertex]);
                marked[(2*vertex)+1]=marked[(2*vertex)+2]=true;
                marked[vertex]=false;
            }

        }

        public void update(int vertex,int l,int r,int ql,int qr,int value) {
            if(ql>qr) {
                return;
            }
            if(l==ql && r==qr) {
                seg_tree[vertex]=Math.max(seg_tree[vertex],value);
                marked[vertex]=true;
                return;
            }
            push(vertex);
            int mid=(l+r)/2;
            //Left Child
            update((2*vertex)+1,l,mid,ql,Math.min(mid,qr),value);
            //Right Child
            update((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr,value);
        }

        public int get(int vertex, int l,int r ,int pos) {
            if(l==r) {
                return seg_tree[vertex];
            }
            push(vertex);
            int mid=(l+r)/2;
            if(pos<=mid) {
                return get((2*vertex)+1,l,mid,pos);
            }
            else {
                return get((2*vertex)+2,mid+1,r,pos);
            }
        }
    }

    
    static ArrayList<Integer> adj_lst[],et,etd;
    static int parent[],euler_tour_depth[],euler_tour[],arr[],max[];
    public static void main(String args[]) throws IOException {
//        int arr[]=new int[20];
//        seg_tree st=new seg_tree(arr.length,arr);
//        for(int i=0;i<10000;i++) {
//            int l=(int)(Math.random()*(arr.length-1));
//            int r=(int)(Math.random()*(arr.length-1));
//            int val=(int)(Math.random()*10000);
//            
//            if(l>r) {
//                int tmp=l;
//                l=r;
//                r=tmp;
//            }
////            System.out.println(l+" "+r+" "+val);
//            update(l,r,val,arr);
//            st.update(0, 0, arr.length-1, l, r, val);
//            
////            for(int j=0;j<arr.length;j++) {
////                System.out.print(arr[j]+" ");
////            }
////            System.out.println();
////            for(int j=0;j<arr.length;j++) {
////                System.out.print(st.get(0, 0, arr.length-1, j)+" ");
////            }
////            System.out.println();
//            
//            for(int j=0;j<arr.length;j++) {
//                if(arr[j]!=st.get(0, 0, arr.length-1, j)) {
//                    System.out.println(-1);
//                }
//            }
//        }
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        int test=input.scanInt();
        for(int tt=1;tt<=test;tt++) {
            int n=input.scanInt();
            int q=input.scanInt();
            arr=new int[n];
            max=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt();
            }
            adj_lst=new ArrayList[n];
            parent=new int[n];
            et=new ArrayList<>();
            etd=new ArrayList<>();
            for(int i=0;i<n;i++) {
                adj_lst[i]=new ArrayList<>();
            }
            for(int i=0;i<n-1;i++) {
                int u=input.scanInt()-1;
                int v=input.scanInt()-1;
                adj_lst[u].add(v);
                adj_lst[v].add(u);
            }
            DFS(0,-1,0);
            
            int first[]=new int[n];
            int lst[]=new int[n];
            Arrays.fill(first, -1);
            Arrays.fill(lst, -1);
            for(int i=0;i<et.size();i++) {
                if(first[et.get(i)]==-1) {
                    first[et.get(i)]=i;
                }
                lst[et.get(i)]=i;
            }
            
            fmax(0,-1,0);
            
            for(int i=0;i<n;i++) {
                System.out.print(max[i]+" ");
            }
            System.out.println();
            int brr[]=new int[et.size()];
            for(int i=0;i<et.size();i++) {
                brr[i]=max[et.get(i)];
            }
            
            seg_tree st=new seg_tree(brr.length,brr);
            
            for(int i=0;i<q;i++) {
                int type=input.scanInt();
                if(type==1) {
                    int u=input.scanInt()-1;
                    int val=input.scanInt();
                    st.update(0, 0, brr.length-1, first[u], lst[u], val);
                }
                else {
                    int u=input.scanInt()-1;
                    int val=st.get(0, 0, brr.length-1, u);
                    ans.append(val+"\n");
                }
            }
            
        }
        System.out.println(ans);
    }
    
    
    public static void fmax(int root,int par,int max1) {
        max1=Math.max(max1,arr[root]);
        max[root]=max1;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==par) {
                continue;
            }
            fmax(adj_lst[root].get(i),root,max1);
        }
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
    
    
    public static void update(int l,int r,int val,int arr[]) {
        for(int i=l;i<=r;i++) {
            arr[i]=Math.max(val,arr[i]);
        }
    }
}
