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
public class E_Paths_and_Trees {
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
        long seg_tree[];
        int seg_indx[];
        public seg_tree(int n,long arr[]) {
            seg_tree=new long[4*n];
            seg_indx=new int[4*n];
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
    static boolean vis[];
    static long dist[],prev_dist[];
    static int prev[],n;
    static seg_tree s_tree;
    
    
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        
        n=input.scanInt();
        int m=input.scanInt();
        adj_lst=new ArrayList[n];
        weight=new ArrayList[n];
        for(int i=0;i<adj_lst.length;i++) {
            adj_lst[i]=new ArrayList<Integer>();
            weight[i]=new ArrayList<Integer>();
        }
        vis=new boolean[n];
        dist=new long[n];
        prev=new int[n];
        prev_dist=new long[n];
        Arrays.fill(prev, -1);
        //No of edges
        
        
        boolean taken[]=new boolean[m];
        long weii[]=new long[m];
        long indx1[]=new long[n];
        for(int i=0;i<n;i++) {
            indx1[i]=i;
        }
        HashMap<Long,Integer> map=new HashMap<>();
        for(int i=0;i<m;i++) {
            // input u & v
            int u=input.scanInt()-1;
            int v=input.scanInt()-1;
            map.put(hash(u,v), i);
            int wei=input.scanInt();
            adj_lst[u].add(v);
            adj_lst[v].add(u);
            weight[u].add(wei);
            weight[v].add(wei);
            weii[i]=wei;
        }
        int source=input.scanInt()-1;
        dist[source]=0;
        for(int i=0;i<n;i++) {
            if(i==source) {
                continue;
            }
            dist[i]=Long.MAX_VALUE;
        }
        s_tree=new seg_tree(n,dist);
        Dijkstras(source);
        sort(dist,indx1,n);
        for(int i=0;i<n;i++) {
            int tmp=(int)indx1[i];
            while(prev[tmp]!=-1) {
//                System.out.println((tmp+1)+" "+(prev[tmp]+1));
                long hash=hash(tmp,prev[tmp]);
                if(map.containsKey(hash)) {
                    int indx=map.get(hash);
                    if(taken[indx]) {
                        break;
                    }
                    taken[indx]=true;
                }
                hash=hash(prev[tmp],tmp);
                if(map.containsKey(hash)) {
                    int indx=map.get(hash);
                    if(taken[indx]) {
                        break;
                    }
                    taken[indx]=true;
                }
                tmp=prev[tmp];
            }
        }
        StringBuilder ans=new StringBuilder("");
        long fin=0;
        for(int i=0;i<m;i++) {
            if(taken[i]) {
                fin+=weii[i];
                ans.append((i+1)+" ");
            }
        }
        System.out.println(fin+"\n"+ans);
    }
    
    public static void Dijkstras(int root) {
        vis[root]=true;
        dist[root]=0;
        prev[root]=-1;
        while(true) {
//            System.out.println(root);
            vis[root]=true;
            for(int i=0;i<adj_lst[root].size();i++) {
                if(vis[adj_lst[root].get(i)]) {
                    continue;
                }
                if(dist[root]+weight[root].get(i)<dist[adj_lst[root].get(i)]) {
                    dist[adj_lst[root].get(i)]=dist[root]+weight[root].get(i);
                    prev[adj_lst[root].get(i)]=root;
                    prev_dist[adj_lst[root].get(i)]=weight[root].get(i);
                    s_tree.update(0, 0, n-1, adj_lst[root].get(i), dist[adj_lst[root].get(i)]);
                }
                else if(dist[root]+weight[root].get(i)==dist[adj_lst[root].get(i)] && weight[root].get(i)<prev_dist[adj_lst[root].get(i)]) {
                    dist[adj_lst[root].get(i)]=dist[root]+weight[root].get(i);
                    prev[adj_lst[root].get(i)]=root;
                    prev_dist[adj_lst[root].get(i)]=weight[root].get(i);
                    s_tree.update(0, 0, n-1, adj_lst[root].get(i), dist[adj_lst[root].get(i)]);
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
    public static long hash(long a,long b) {
        return a*1000000 + b;
    }
    
    
    
    static void buildMaxHeap(long arr[],long brr[], int n) 
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
  
  static void sort(long arr[],long brr[], int n) 
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
  
  public static void swap(long[] a, int i, int j) { 
    long temp = a[i]; 
    a[i]=a[j]; 
    a[j] = temp; 
  } 
  
}
