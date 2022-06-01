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
public class A_Maze {
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
    static boolean vis[];
    static int subtree[],depth[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int n=input.scanInt();
        int m=input.scanInt();
        int k=input.scanInt();
        char arr[][]=new char[n][m];
        int indx[][]=new int[n][m];
        int count=0;
        for(int i=0;i<n;i++) {
            String str=input.scanString();
            for(int j=0;j<m;j++) {
                arr[i][j]=str.charAt(j);
                indx[i][j]=count;
                count++;
            }
        }
        solve(n,m,k,arr,indx);
    }
    public static void create_graph(int n,int m,char arr[][],int indx[][]) {
        adj_lst=new ArrayList[n*m];
        vis=new boolean[adj_lst.length];
        subtree=new int[adj_lst.length];
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(arr[i][j]=='.') {
                    adj_lst[indx[i][j]]=new ArrayList<>();
                }
            }
        }
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(arr[i][j]=='#') {
                    continue;
                }
                if(i>0) {
                    if(arr[i-1][j]=='.') {
                        adj_lst[indx[i][j]].add(indx[i-1][j]);
                    }
                }
                if(i<n-1) {
                    if(arr[i+1][j]=='.') {
                        adj_lst[indx[i][j]].add(indx[i+1][j]);
                    }
                }
                if(j>0) {
                    if(arr[i][j-1]=='.') {
                        adj_lst[indx[i][j]].add(indx[i][j-1]);
                    }
                }
                if(j<m-1) {
                    if(arr[i][j+1]=='.') {
                        adj_lst[indx[i][j]].add(indx[i][j+1]);
                    }
                }
            }
        }
    }
    
//    public static int DFS(int root) {
////        System.out.println(root);
//        vis[root]=true;
//        boolean all_vis=true;
//        int cnt=1;
//        for(int i=0;i<adj_lst[root].size();i++) {
//            all_vis&=vis[adj_lst[root].get(i)];
//            if(!vis[adj_lst[root].get(i)]) {
//                cnt+=DFS(adj_lst[root].get(i));
//            }
//        }
//        if(all_vis) {
//            subtree[root]=1;
//            return subtree[root];
//        }
//        subtree[root]=cnt;
//        return subtree[root];
//    }
   static void BFS(int root) {
        depth=new int[adj_lst.length];
        Queue<Integer> que = new LinkedList<>();
        que.add(root);
        depth[root]=0;
        vis[root]=true;
        while(!que.isEmpty()) {
            for(int i=0;i<adj_lst[que.peek()].size();i++) {
                if(!vis[adj_lst[que.peek()].get(i)]) {
                    que.add(adj_lst[que.peek()].get(i));
                    vis[adj_lst[que.peek()].get(i)]=true;
                    depth[adj_lst[que.peek()].get(i)]=depth[que.peek()]+1;
                }
            }
            que.poll();
        }
    } 

    public static void solve(int n,int m,int k,char arr[][],int indx[][]) {
        create_graph(n,m,arr,indx);
        for(int i=0;i<n*m;i++) {
            if(adj_lst[i]!=null && !vis[i]) {
                BFS(i);
                break;
            }
        }
        int indx_arr[]=new int[n*m];
        for(int i=0;i<n*m;i++) {
            indx_arr[i]=i;
        }
//        for(int i=0;i<n*m;i++) {
//            System.out.print(depth[i]+" ");
//        }
//        System.out.println();
        sort(depth,indx_arr);
        for(int i=n*m-1,cnt=0;cnt<k;i--) {
            if(adj_lst[indx_arr[i]]==null) {
                continue;
            }
            depth[i]=-1;
            cnt++;
        }
        sort(indx_arr,depth);
        StringBuilder ans=new StringBuilder("");
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(depth[indx[i][j]]==-1) {
                    ans.append("X");
                }
                else {
                    ans.append(arr[i][j]);
                }
            }
            ans.append("\n");
        }
        System.out.println(ans);
    }
    
    public static void sort(int arr[], int[] brr) 
    { 
        int n = arr.length; 
  
        // Build heap (rearrange array) 
        for (int i = n / 2 - 1; i >= 0; i--) 
            heapify(arr,brr, n, i); 
  
        // One by one extract an element from heap 
        for (int i=n-1; i>0; i--) 
        { 
            // Move current root to end 
            int temp = arr[0]; 
            arr[0] = arr[i]; 
            arr[i] = temp; 
            
            temp = brr[0]; 
            brr[0] = brr[i]; 
            brr[i] = temp; 
  
            // call max heapify on the reduced heap 
            heapify(arr,brr, i, 0); 
        } 
    } 
  
    // To heapify a subtree rooted with node i which is 
    // an index in arr[]. n is size of heap 
    static void heapify(int arr[], int brr[],int n, int i) 
    { 
        int largest = i; // Initialize largest as root 
        int l = 2*i + 1; // left = 2*i + 1 
        int r = 2*i + 2; // right = 2*i + 2 
  
        // If left child is larger than root 
        if (l < n && arr[l] > arr[largest]) 
            largest = l; 
  
        // If right child is larger than largest so far 
        if (r < n && arr[r] > arr[largest]) 
            largest = r; 
  
        // If largest is not root 
        if (largest != i) 
        { 
            int swap = arr[i]; 
            arr[i] = arr[largest]; 
            arr[largest] = swap; 
            
            swap = brr[i]; 
            brr[i] = brr[largest]; 
            brr[largest] = swap; 
  
            // Recursively heapify the affected sub-tree 
            heapify(arr, brr,n, largest); 
        } 
    }  
}
