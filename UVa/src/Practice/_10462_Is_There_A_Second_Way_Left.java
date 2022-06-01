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
public class _10462_Is_There_A_Second_Way_Left {
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
                return new long[]{Integer.MAX_VALUE,-1};
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
    
    
    
    
    
    
    
    static int n,m,strt[],end[];
    static long wei[];
    static ArrayList<Integer> adj_lst[],mst[],et,etd;
    static ArrayList<Long> weight[],et_edge;
    static boolean vis[],taken[];
    static int parent[],euler_tour[];
    static long sum,euler_tour_depth[];
    public static void main(String args[]) throws IOException {
        Scanner input=new Scanner(System.in);
        StringBuilder ans=new StringBuilder("");
        int test=input.nextInt();
        for(int tt=1;tt<=test;tt++) {
            sum=0;
            n=input.nextInt();
            m=input.nextInt();
            adj_lst=new ArrayList[n];
            mst=new ArrayList[n];
            weight=new ArrayList[n];
            strt=new int[m];
            end=new int[m];
            wei=new long[m];
            vis=new boolean[n];
            et_edge=new ArrayList<>();
            parent=new int[n];
            et=new ArrayList<>();
            etd=new ArrayList<>();
            for(int i=0;i<n;i++) {
                adj_lst[i]=new ArrayList<>();
                mst[i]=new ArrayList<>();
                weight[i]=new ArrayList<>();
            }
            for(int i=0;i<m;i++) {
                int u=input.nextInt()-1;
                int v=input.nextInt()-1;
                long w=input.nextLong();
                adj_lst[u].add(v);
                adj_lst[v].add(u);
                weight[u].add(w);
                weight[v].add(w);
                strt[i]=u;
                end[i]=v;
                wei[i]=w;
            }
            MST();
            vis=new boolean[n];
            DFS(0,-1,0);
            boolean is_pos=true;
            for(int i=0;i<n;i++) {
                if(!vis[i]) {
                    is_pos=false;
                    break;
                }
            }
            if(!is_pos) {
                ans.append("No way\n");
                continue;
            }
            if(m==n-1) {
                ans.append("No second way\n");
                continue;
            }
            
            euler_tour_depth=new long[et.size()];
            euler_tour=new int[etd.size()];
            for(int i=0;i<et.size();i++) {
                euler_tour[i]=et.get(i);
                euler_tour_depth[i]=etd.get(i);
            }
            long et_wei[]=new long[et_edge.size()];
            for(int i=0;i<et_edge.size();i++) {
                et_wei[i]=-1*et_edge.get(i);
            }
            seg_tree st=new seg_tree(euler_tour_depth.length,euler_tour_depth);
            seg_tree st1=new seg_tree(et_wei.length,et_wei); 
            int first[]=new int[n];
            Arrays.fill(first, -1);
            for(int i=0;i<euler_tour.length;i++) {
                if(first[euler_tour[i]]==-1) {
                    first[euler_tour[i]]=i;
                }
            }
//            int query=input.nextInt();
//            for(int q=0;q<query;q++) {
//                int u=input.nextInt()-1;
//                int v=input.nextInt()-1;
//                int l=Math.min(first[u],first[v]);
//                int r=Math.max(first[u],first[v]);
//                int indx=st.min(0, 0, euler_tour_depth.length-1, l, r)[1];
//                System.out.println(euler_tour[indx]+1);
//            }
            long min=Long.MAX_VALUE;
            for(int i=0;i<m;i++) {
                if(taken[i]) {
                    continue;
                }
                int u=strt[i];
                int v=end[i];
                int l=Math.min(first[u],first[v]);
                int r=Math.max(first[u],first[v]);
                long tmp=-1*st1.min(0, 0, et_wei.length-1, l, r-1)[0];
                min=Math.min(min,sum-tmp+wei[i]);
            }
            ans.append(min+"\n");
        }
        System.out.print(ans);
    }
    public static void MST() {
        heapSort(wei,strt,end,m);
        taken=new boolean[m];
        for(int i=0;i<m;i++) {
            if(vis[strt[i]] && vis[end[i]]) {
                continue;
            }
            taken[i]=true;
            vis[strt[i]]=vis[end[i]]=true;
            mst[strt[i]].add(end[i]);
            mst[end[i]].add(strt[i]);
            weight[strt[i]].add(wei[i]);
            weight[end[i]].add(wei[i]);
            sum+=wei[i];
        }
    }
    public static void DFS(int root,int par,int dep) {
        parent[root]=par;
        et.add(root);
        etd.add(dep);
        vis[root]=true;
        for(int i=0;i<mst[root].size();i++) {
            if(mst[root].get(i)==par) {
                continue;
            }
            et_edge.add(weight[root].get(i));
            DFS(mst[root].get(i),root,dep+1);
            et.add(root);
            etd.add(dep);
            et_edge.add(weight[root].get(i));
        }
    }
    
    
    
    static void buildMaxHeap(long arr[],int brr[],int crr[], int n) 
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
  
  static void heapSort(long arr[],int brr[],int crr[], int n) 
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
