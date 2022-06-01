/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_791_Div_2;

/**
 *
 * @author SRESTH
 */
import java.util.*;
import java.io.*;
public class D {
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
    
    public static long cvt(String str) {
        long ans=0;
        for(int i=0;i<str.length();i++) {
            ans*=10;
            ans+=str.charAt(i)-'0';
        }
        return ans;
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

            seg_tree[vertex]=Math.max(seg_tree[(2*vertex)+1],seg_tree[(2*vertex)+2]);
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

            seg_tree[vertex]=Math.max(seg_tree[(2*vertex)+1],seg_tree[(2*vertex)+2]);
        }

        public int find_max(int vertex,int l,int r,int ql,int qr) {
    //        System.out.println(vertex+" "+l+" "+r+" "+ql+" "+qr);
            if(ql>qr) {
                return Integer.MIN_VALUE;
            }
            if(ql<=l && r<=qr) {
                return seg_tree[vertex];
            }
            push(vertex);
            int mid=(l+r)/2;
            return Math.max(find_max((2*vertex)+1,l,mid,ql,Math.min(qr,mid)),find_max((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr));
        }
    }
    
    
    
    
    static ArrayList<Integer> adj_lst[];
    static boolean vis[];
    static HashMap<Integer,Integer> map;
    static ArrayList<Integer> arrli;
    static int n,m,arr[],min;
    static long k;
    static seg_tree st;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        
        n=input.scanInt();
        m=input.scanInt();
        k=cvt(input.scanString());
        st=new seg_tree(n,new int[n]);
        arr=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
        }
        vis=new boolean[n];
        adj_lst=new ArrayList[n];
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<>();
        }
        for(int i=0;i<m;i++) {
            int u=input.scanInt()-1;
            int v=input.scanInt()-1;
            adj_lst[u].add(v);
        }
        map=new HashMap<>();
        arrli=new ArrayList<>();
        min=Integer.MAX_VALUE;
        for(int i=0;i<n;i++) {
            if(vis[i]) {
                continue;
            }
            dfs(i);
        }
        System.out.println(min);
        System.out.print(ans);
    }
    
    public static void dfs(int root) {
        vis[root]=true;
        arrli.add(root);
        map.put(root, arrli.size()-1);
        st.update(0, 0, n-1, arrli.size()-1, arrli.size()-1, arr[root]);
        for(int i=0;i<adj_lst[root].size();i++) {
            if(map.containsKey(adj_lst[root].get(i))) {
//                System.out.println("cycle "+root+" "+adj_lst[root].get(i));
                min=Math.min(min,st.find_max(0, 0, n-1, map.get(adj_lst[root].get(i)), arrli.size()-1));
            }
            else if(!vis[adj_lst[root].get(i)]) {
                dfs(adj_lst[root].get(i));
            }
        }
        arrli.remove(arrli.size()-1);
        map.remove(root);
    }
}

