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
public class D_Connected_Components {
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
    
    
    
    
    
    static ArrayList<Integer> adj_lst[],indx[];
    static boolean vis[];
    static int comp[];
    static int parent[],rank[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        int n=input.scanInt();
        int m=input.scanInt();
        adj_lst=new ArrayList[n];
        indx=new ArrayList[n];
        vis=new boolean[n];
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<>();
            indx[i]=new ArrayList<>();
        }
        int u[]=new int[m];
        int v[]=new int[m];
        for(int i=0;i<m;i++) {
            u[i]=input.scanInt()-1;
            v[i]=input.scanInt()-1;
            adj_lst[u[i]].add(v[i]);
            adj_lst[v[i]].add(u[i]);
            indx[u[i]].add(i);
            indx[v[i]].add(i);
        }
        int k=input.scanInt();
        int ll=0,rr=0;
        int lft[]=new int[k];
        int rgt[]=new int[k];
        int t_indx[]=new int[k];
        for(int i=0;i<k;i++) {
            int l=input.scanInt()-1;
            int r=input.scanInt()-1;
            lft[i]=l;
            rgt[i]=r;
            rr+=10;
            if(rr>=m) {
                ll++;
                rr=0;
            }
            t_indx[i]=i;
        }
        //sort
        sort(lft,rgt,t_indx,k);
        for(int i=0;i<k;i++) {
            int j=i;
            while(j<k-1 && lft[j]==lft[j+1]) {
                j++;
            }
            if(j>i) {
                merge_sort(rgt,t_indx,i,j);
//                quickSortIterative(rgt,t_indx,i,j);
                i=j;
            }
            
        }
//        System.out.println();
//        for(int i=k-1;i>=0;i--) {
//            System.out.println(lft[i]+" "+rgt[i]+" "+t_indx[i]);
//        }
//        System.out.println();
        int t_ans[]=new int[k];
        int curr=-1;
        for(int i=k-1;i>=0;i--) {
//            System.out.println(lft[i]+" "+rgt[i]);
            if(i==k-1 || lft[i]!=lft[i+1]) {
                curr=find_cnt(n,lft[i],rgt[i]);
//                System.out.println(curr);
                t_ans[i]=curr;
                continue;
            }
            for(int j=rgt[i+1];j>rgt[i];j--) {
                if(curr==1) {
                    break;
                }
//                System.out.println("j"+j+" "+comp[u[j]]+" "+comp[v[j]]);
                if(find(u[j])!=find(v[j])) {
                    union(u[j],v[j]);
                    curr--;
                }
            }
            t_ans[i]=curr;
        }
        merge_sort(t_indx,t_ans,0,k-1);
//        quickSortIterative(t_indx,t_ans,0,k-1);
        for(int i=0;i<k;i++) {
//            System.out.println(lft[i]+" "+rgt[i]+" "+t_indx[i]+" "+t_ans[i]);
            ans.append(t_ans[i]+"\n");
        }
        System.out.println(ans);
    }
    public static void make_set(int n) {
        for(int i=0;i<n;i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            if (rank[a] < rank[b]) {
                swap(a, b);
            }
            parent[b] = a;
            if (rank[a] == rank[b]) {
                rank[a]++;
            }
        }
    }
    public static int find(int v) {
        if (v == parent[v]) {
            return v;
        }
        parent[v] = find(parent[v]);
        return parent[v];
    }
    public static void swap(int i,int j) {
        int tmp=rank[i];
        rank[i]=rank[j];
        rank[j]=tmp;
    }
    
    
    public static int find_cnt(int n,int l,int r) {
        vis=new boolean[n];
        int cnt=0;
        parent=new int[n];
        rank=new int[n];
        make_set(n);
        for(int i=0;i<n;i++) {
            if(vis[i]) {
                continue;
            }
            DFS(i,l,r,i);
            cnt++;
        }
//        for(int i=0;i<n;i++) {
//            System.out.print(find(i)+" ");
//        }
//        System.out.println();
        return cnt;
    }
    public static void DFS(int root,int l,int r,int tmp) {
        vis[root]=true;
        union(tmp,root);
        for(int i=0;i<adj_lst[root].size();i++) {
            if(indx[root].get(i)>=l && indx[root].get(i)<=r) {
                continue;
            }
            if(vis[adj_lst[root].get(i)]) {
                continue;
            }
            DFS(adj_lst[root].get(i),l,r,tmp);
        }
    }
    
    
    
    static void buildMaxHeap(int arr[],int brr[],int crr[], int n) 
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
          swap(crr, j, (j - 1) / 2); 
          j = (j - 1) / 2; 
        } 
      } 
    } 
  } 
  
  static void sort(int arr[],int brr[],int crr[], int n) 
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
  
  
  
  
  
  
  
  
  public static void merge(int arr[],int brr[], int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;
 
        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];
        
        int L1[] = new int[n1];
        int R1[] = new int[n2];
 
        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i) {
            L[i] = arr[l + i];
            L1[i] = brr[l + i];
        }
        for (int j = 0; j < n2; ++j) {
            R[j] = arr[m + 1 + j];
            R1[j] = brr[m + 1 + j];
        }
 
        /* Merge the temp arrays */
 
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;
 
        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                brr[k] = L1[i];
                i++;
            }
            else {
                arr[k] = R[j];
                brr[k] = R1[j];
                j++;
            }
            k++;
        }
 
        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            brr[k] = L1[i];
            i++;
            k++;
        }
 
        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            brr[k] = R1[j];
            j++;
            k++;
        }
    }
 
    // Main function that sorts arr[l..r] using
    // merge()
    public static void merge_sort(int arr[],int brr[], int l, int r)
    {
        if (l < r) {
            // Find the middle point
            int m = (l + r) / 2;
 
            // Sort first and second halves
            merge_sort(arr,brr, l, m);
            merge_sort(arr,brr, m + 1, r);
 
            // Merge the sorted halves
            merge(arr,brr, l, m, r);
        }
    }
}
