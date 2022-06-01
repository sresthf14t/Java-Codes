/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_597_Div_2_Virtual;

/**
 *
 * @author Srest
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
    
    
    static class DSU {
        int parent[],size[];
        public DSU(int n){
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

    static int n,m,u[],v[];
    static long edge[],cost[];
    static ArrayList<Integer> mst[];
    static ArrayList<Long> weight[];
    static boolean vis[],taken[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        n=input.scanInt();
        long x[]=new long[n];
        long y[]=new long[n];
        for(int i=0;i<n;i++) {
            x[i]=input.scanInt();
            y[i]=input.scanInt();
        }
        cost=new long[n];
        for(int i=0;i<n;i++) {
            cost[i]=input.scanInt();
        }
        long line[]=new long[n];
        for(int i=0;i<n;i++) {
            line[i]=input.scanInt();
        }
        
        u=new int[(n*(n-1))/2];
        v=new int[(n*(n-1))/2];
        edge=new long[(n*(n-1))/2];
        int indx=0;
        for(int i=0;i<n;i++) {
            for(int j=i+1;j<n;j++) {
                u[indx]=i;
                v[indx]=j;
                edge[indx]=(line[i]+line[j])*(Math.abs(x[i]-x[j])+Math.abs(y[i]-y[j]));
                indx++;
            }
        }
        
        mst=new ArrayList[n];
        weight=new ArrayList[n];
        for(int i=0;i<n;i++) {
            mst[i]=new ArrayList<>();
            weight[i]=new ArrayList<>();
        }
        
        MST();
        
        
//        for(int i=0;i<mst.length;i++) {
////            System.out.print(i+"->");
//            for(int j=0;j<mst[i].size();j++) {
//                if(mst[i].get(j)<i) {
//                    continue;
//                }
//                System.out.println(i+" "+mst[i].get(j));
//            }
//        }
        
        u=new int[n-1];
        v=new int[n-1];
        edge=new long[n-1];
        indx=0;
        for(int i=0;i<mst.length;i++) {
            for(int j=0;j<mst[i].size();j++) {
                if(i>=mst[i].get(j)) {
                    continue;
                }
                u[indx]=i;
                v[indx]=mst[i].get(j);
                edge[indx]=weight[i].get(j);
                indx++;
            }
        }
        sort(edge,u,v,u.length);
        for(int i=0,j=u.length-1;i<j;i++,j--) {
            swap(edge,i,j);
            swap(u,i,j);
            swap(v,i,j);
        }
        boolean edge_rem[]=new boolean[u.length];
        taken=new boolean[n];
        vis=new boolean[n];
        taken[min_indx(0,-1)]=true;
        for(int i=0;i<u.length;i++) {
            indx=-1;
            mst[u[i]].remove(new Integer(v[i]));
            mst[v[i]].remove(new Integer(u[i]));
            edge_rem[i]=true;
            if(!taken(u[i],-1)) {
                indx=min_indx(u[i],-1);
            }
            else {
                indx=min_indx(v[i],-1);
            }
            taken[indx]=true;
            if(cost[indx]>edge[i]) {
                mst[u[i]].add(v[i]);
                mst[v[i]].add(u[i]);
                taken[indx]=false;
                edge_rem[i]=false;
            }
        }
        int cnt=0,cnte=0;
        long total=0;
        for(int i=0;i<n;i++) {
            if(taken[i]) {
                cnt++;
                total+=cost[i];
            }
        }
        for(int i=0;i<edge.length;i++) {
            if(!edge_rem[i]) {
                cnte++;
                total+=edge[i];
            }
        }
        
        ans.append(total+"\n");
        ans.append(cnt+"\n");
        
        for(int i=0;i<n;i++) {
            if(taken[i]) {
                ans.append((i+1)+" ");
            }
        }
        ans.append("\n");
        
        ans.append(cnte+"\n");
        for(int i=0;i<u.length;i++) {
            if(!edge_rem[i]) {
                ans.append((u[i]+1)+" "+(v[i]+1)+"\n");
            }
        }
        
        
        
        System.out.println(ans);
    }
    
    public static boolean taken(int root,int par) {
        if(taken[root]) {
            return true;
        }
        for(int i=0;i<mst[root].size();i++) {
            if(mst[root].get(i)==par) {
                continue;
            }
            if(taken(mst[root].get(i),root)) {
                return true;
            }
        }
        return false;
    }
    
    
    public static int min_indx(int root,int par) {
        int indx=root;
        for(int i=0;i<mst[root].size();i++) {
            if(mst[root].get(i)==par) {
                continue;
            }
            int tmp=min_indx(mst[root].get(i),root);
            if(cost[tmp]<cost[indx]) {
                indx=tmp;
            }
        }
        return indx;
    }
    
     public static void MST() {
        DSU dsu=new DSU(n);
        //sort
        sort(edge,u,v,u.length);
        for(int i=0;i<u.length;i++) {
//            System.out.println(u[i]+" "+v[i]+" "+edge[i]);
            if(dsu.find(u[i])==dsu.find(v[i])) {
                continue;
            }
            mst[u[i]].add(v[i]);
            mst[v[i]].add(u[i]);
            weight[u[i]].add(edge[i]);
            weight[v[i]].add(edge[i]);
            dsu.union(u[i], v[i]);
        }
    }

    static void buildMaxHeap(long arr[],int brr[],int crr[], int n) { 
    for (int i = 1; i < n; i++) 
    { 
      // if child is bigger than parent 
      if (arr[i] > arr[(i - 1) / 2]) 
      { 
        int j = i; 
  
        // swap child and parent until 
        // parent is smaller 
        while (arr[j] > arr[(j - 1) / 2]) 
        { 
          swap(arr, j, (j - 1) / 2); 
          swap(brr, j, (j - 1) / 2); 
          swap(crr, j, (j - 1) / 2); 
          j = (j - 1) / 2; 
        } 
      } 
    } 
  } 
  
  static void sort(long arr[],int brr[],int crr[], int n) 
  { 
    buildMaxHeap(arr,brr,crr, n); 
  
    for (int i = n - 1; i > 0; i--) 
    { 
      // swap value of first indexed 
      // with last indexed 
      swap(arr, 0, i); 
      swap(brr, 0, i); 
      swap(crr, 0, i); 
      
  
      // maintaining heap property 
      // after each swapping 
      int j = 0, index; 
  
      do
      { 
        index = (2 * j + 1); 
  
        // if left child is smaller than 
        // right child point index variable 
        // to right child 
        if (index < (i - 1) && arr[index] < arr[index + 1]) 
          index++; 
  
        // if parent is smaller than child 
        // then swapping parent with child 
        // having higher value 
        if (index < i && arr[j] < arr[index]) {
          swap(arr, j, index); 
          swap(brr, j, index);
          swap(crr, j, index);
        }
  
        j = index; 
  
      } while (index < i); 
    } 
  } 
  
  public static void swap(int[] a, int i, int j) { 
    int temp = a[i]; 
    a[i]=a[j]; 
    a[j] = temp; 
  } 
  
  public static void swap(long[] a, int i, int j) { 
    long temp = a[i]; 
    a[i]=a[j]; 
    a[j] = temp; 
  } 
}
