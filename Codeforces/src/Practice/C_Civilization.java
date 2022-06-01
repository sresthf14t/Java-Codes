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
public class C_Civilization {
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
    
    
    
    public static class DSU {
        int parent[],size[];
        DSU(int n){
            parent=new int[n];
            size=new int[n];
            make_set(n);
        }
        public void make_set(int n) {
            for(int i=0;i<n;i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public void union(int a, int b) {
            a = find(a);
            b = find(b);
            if (a != b) {
                if (size[a] < size[b])
                    swap(a, b);
                parent[b] = a;
                size[a] += size[b];
            }
        }
        public int find(int v) {
            if (v == parent[v]) {
                return v;
            }
            parent[v] = find(parent[v]);
            return parent[v];
        }
        public void swap(int i,int j) {
            int tmp=size[i];
            size[i]=size[j];
            size[j]=tmp;
        }
    }

    
    static int n,m;
    static ArrayList<Integer> adj_lst[];
    static DSU dsu;
    static boolean vis[];
    static int chain;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        int test=1;
        for(int tt=1;tt<=test;tt++) {
            n=input.scanInt();
            m=input.scanInt();
            int q=input.scanInt();
            adj_lst=new ArrayList[n];
            for(int i=0;i<n;i++) {
                adj_lst[i]=new ArrayList<>();
            }
            for(int i=0;i<m;i++) {
                int u=input.scanInt()-1;
                int v=input.scanInt()-1;
                adj_lst[u].add(v);
                adj_lst[v].add(u);
            }
            vis=new boolean[n];
            dsu=new DSU(n);
            int size[]=new int[n];
            for(int i=0;i<n;i++) {
                if(vis[i]) {
                    continue;
                }
                chain=0;
                DFS(i,-1);
                size[dsu.find(i)]=chain;
            }
            
//            System.out.println("ca");
            
            for(int i=0;i<q;i++) {
//                System.out.println(i);
                int type=input.scanInt();
                
                if(type==1) {
                    int x=input.scanInt()-1;
                    ans.append((size[dsu.find(x)]-1)+"\n");
                }
                else {
                    int x=input.scanInt()-1;
                    int y=input.scanInt()-1;
//                    System.out.println(i+" "+x+" "+y);
                    if(dsu.find(x)==dsu.find(y)) {
                        continue;
                    }
                    int s1=size[dsu.find(x)];
                    int s2=size[dsu.find(y)];
                    
                    int p[]=new int[4];
                    if(s1%2==0) {
                        p[0]=(s1/2)+1;
                        p[1]=(s1/2)-1;
                    }
                    else {
                        p[0]=(s1/2)+1;
                        p[1]=(s1/2);
                    }
                    
                    if(s2%2==0) {
                        p[2]=(s2/2)+1;
                        p[3]=(s2/2)-1;
                    }
                    else {
                        p[2]=(s2/2)+1;
                        p[3]=(s2/2);
                    }
                    
//                    System.out.println(i+" "+p[0]+" "+p[1]+" "+p[2]+" "+p[3]);
                    
                    sort(p,0,p.length-1);
                    dsu.union(x, y);
                    size[dsu.find(x)]=p[3]+p[2];
                    
                }
            }
        }
        System.out.println(ans);
    }
    public static int DFS(int root,int par) {
        vis[root]=true;
        if(par!=-1) {
            dsu.union(root, par);
        }
        int max=0,sec_max=0;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==par) {
                continue;
            }
            int tmp=DFS(adj_lst[root].get(i),root);
            if(tmp>max) {
                sec_max=max;
                max=tmp;
            }
            else if(tmp>sec_max) {
                sec_max=tmp;
            }
        }
        chain=Math.max(chain, max+sec_max+1);
        return max+1;
        
    }
}
