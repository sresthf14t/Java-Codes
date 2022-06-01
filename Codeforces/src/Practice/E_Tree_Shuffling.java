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
public class E_Tree_Shuffling {
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
    static ArrayList<Integer> adj_lst[];
    static int n,a[],b[],c[],parent[],cnt[][],cnt_old[][];
    static boolean vis[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n=input.scanInt();
        a=new int[n];
        b=new int[n];
        c=new int[n];
        adj_lst=new ArrayList[n];
        parent=new int[n];
        cnt=new int[n][2];
        cnt_old=new int[n][2];
        int indx[]=new int[n];
        int brr[]=new int[2];
        int crr[]=new int[2];
        for(int i=0;i<n;i++) {
            indx[i]=i;
            a[i]=input.scanInt();
            b[i]=input.scanInt();
            c[i]=input.scanInt();
            brr[b[i]]++;
            crr[c[i]]++;
            adj_lst[i]=new ArrayList<>();
        }
        for(int i=0;i<n-1;i++) {
            int u=input.scanInt()-1;
            int v=input.scanInt()-1;
            adj_lst[u].add(v);
            adj_lst[v].add(u);
        }
        if(brr[0]!=crr[0] || brr[1]!=crr[1]) {
            System.out.println(-1);
            return;
        }
        vis=new boolean[n];
        Arrays.fill(vis, true);
        DFS(0,-1,Integer.MAX_VALUE);
        copy();
//        for(int i=0;i<n;i++) {
//            System.out.println((i+1)+" "+vis[i]);
//        }
        long ans=0;
        sort(a,indx,n);
        for(int j=0;j<n;j++) {
            int i=indx[j];
//            System.out.println(i+" "+ans);
            if(vis[i]) {
                continue;
            }
            int min=Math.min(cnt[i][0],cnt[i][1]);
//            System.out.println((i+1)+" "+cnt[i][0]+" "+cnt[i][1]);
            update_sub(i);
            cnt[i][0]-=min;
            cnt[i][1]-=min;
            update_add(i);
            ans+=(long)a[j]*(long)min*2L;
//            System.out.println((i+1)+" "+ans+" "+cnt[i][0]+" "+cnt[i][1]);
//            mark(i);
        }
        System.out.println(ans);
    }
    public static void mark(int root) {
        vis[root]=true;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==parent[root]) {
                continue;
            }
            if(!vis[adj_lst[root].get(i)]) {
                mark(adj_lst[root].get(i));
            }
        }
    }
    public static void update_sub(int root) {
        int zero=cnt_old[root][0],one=cnt_old[root][1];
        while(parent[root]!=-1) {
            cnt[parent[root]][0]-=zero;
            cnt[parent[root]][1]-=one;
            if(!vis[parent[root]]) {
                break;
            }
            root=parent[root];
        }
    }
    public static void update_add(int root) {
        int zero=cnt[root][0],one=cnt[root][1];
        while(parent[root]!=-1) {
            cnt[parent[root]][0]+=zero;
            cnt[parent[root]][1]+=one;
            if(!vis[parent[root]]) {
                break;
            }
            root=parent[root]; 
        }
    }
    public static int[] DFS(int root,int par,int min) {
        if(a[root]<min) {
            min=a[root];
            vis[root]=false;
        }
        parent[root]=par;
        int zero=0,one=0;
        if(b[root]==0 && c[root]==1) {
            zero++;
        }
        else if(b[root]==1 && c[root]==0) {
            one++;
        }
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==par) {
                continue;
            }
            int tmp[]=DFS(adj_lst[root].get(i),root,min);
            zero+=tmp[0];
            one+=tmp[1];
        }
        cnt[root][0]=zero;
        cnt[root][1]=one;
        return new int[]{zero,one};
    }
    
    public static void copy() {
        for(int i=0;i<n;i++) {
            for(int j=0;j<2;j++) {
                cnt_old[i][j]=cnt[i][j];
            }
        }
    }
    
    static void buildMaxHeap(int arr[],int brr[], int n) 
  { 
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
          j = (j - 1) / 2; 
        } 
      } 
    } 
  } 
  
  static void sort(int arr[],int brr[], int n) 
  { 
    buildMaxHeap(arr,brr, n); 
  
    for (int i = n - 1; i > 0; i--) 
    { 
      // swap value of first indexed 
      // with last indexed 
      swap(arr, 0, i);
      swap(brr, 0, i);
  
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
}
