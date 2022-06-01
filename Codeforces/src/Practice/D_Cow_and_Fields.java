/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class D_Cow_and_Fields {
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
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int n=input.scanInt();
        int m=input.scanInt();
        int k=input.scanInt();
        boolean special[]=new boolean[n];
        for(int i=0;i<k;i++) {
            special[input.scanInt()-1]=true;
        }
        adj_lst=new ArrayList[n];
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<Integer>();
        }
        for(int i=0;i<m;i++) {
            // input u & v
            int u=input.scanInt();
            int v=input.scanInt();
            u--;
            v--;
            adj_lst[u].add(v);
            adj_lst[v].add(u);
        }
        int depth0[]=new int[n];
        int depth1[]=new int[n];
        BFS(0,depth0);
        BFS(n-1,depth1);
        for(int i=0;i<n;i++) {
            if(!special[i]) {
                continue;
            }
            for(int j=0;j<adj_lst[i].size();j++) {
                if(special[adj_lst[i].get(j)]) {
                    System.out.println(depth0[n-1]);
                    return;
                }
            }
        }
        int depth[]=new int[n];
        int indx[]=new int[n];
        for(int i=0;i<n;i++) {
            depth[i]=depth0[i]-depth1[i];
            indx[i]=i;
        }
        sort(depth,indx,n);
        TreeMap<Integer,Integer> map=new TreeMap<>();
        for(int i=0;i<n;i++) {
            if(!special[i]) {
                continue;
            }
            if(!map.containsKey(depth1[i])) {
                map.put(depth1[i], 0);
            }
            map.replace(depth1[i], map.get(depth1[i])+1);
        }
        int max=Integer.MIN_VALUE;
        for(int i=0;i<n-1;i++) {
            if(!special[indx[i]]) {
                continue;
            }
            map.replace(depth1[indx[i]], map.get(depth1[indx[i]])-1);
            if(map.get(depth1[indx[i]])==0) {
                map.remove(depth1[indx[i]]);
            }
            if(map.size()==0) {
                break;
            }
//            System.out.println(indx[i]+" "+depth0[indx[i]]+" "+map.lastKey());
            max=Math.max(max,depth0[indx[i]]+map.lastKey()+1);
        }
        System.out.println(Math.min(depth0[n-1],max));
    }
    static void BFS(int source,int depth[]) {
        Arrays.fill(depth, Integer.MAX_VALUE);
        boolean vis[]=new boolean[adj_lst.length];
        Queue<Integer> que = new LinkedList<>();
        que.add(source);
        depth[source]=0;
        vis[source]=true;
        while(!que.isEmpty()) {
            for(int i=0;i<adj_lst[que.peek()].size();i++) {
                if(!vis[adj_lst[que.peek()].get(i)]) {
                    que.add(adj_lst[que.peek()].get(i));
                    vis[adj_lst[que.peek()].get(i)]=true;
                    depth[adj_lst[que.peek()].get(i)]=Math.min(depth[adj_lst[que.peek()].get(i)], depth[que.peek()]+1);
                }
            }
            que.poll();
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
