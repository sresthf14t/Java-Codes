/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Educational_Codeforces_Round_129_Rated_for_Div_2;

/**
 *
 * @author SRESTH
 */
import java.util.*;
import java.io.*;
public class F {
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
    
    static ArrayList<Integer> adj_lst[];
    static ArrayList<Integer> wei[];
    static long sub;
    
    
    // Number of vertices
    static int n;
    
    
    static int sz;

    

    // Adjacency list representation
    // of the tree
    static ArrayList []tree;

    // Array that stores the subtree size
    static int []subtree_size;

    // Array to mark all the
    // vertices which are visited
    static int []vis;

    
    
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        
        n=input.scanInt();
        sub=0;
        sz=n;
        tree = new ArrayList[sz];
        subtree_size = new int[sz];
        vis = new int[sz];
        
        for(int i = 0; i < sz; i++) {
            tree[i] = new ArrayList<>();
        }
        
        adj_lst=new ArrayList[n];
        wei=new ArrayList[n];
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<>();
            wei[i]=new ArrayList<>();
        }
        for(int i=0;i<n-1;i++) {
            int u=input.scanInt()-1;
            int v=input.scanInt()-1;
            int val=input.scanInt();
            AddEdge(u, v);
            adj_lst[u].add(v);
            adj_lst[v].add(u);
            wei[u].add(val);
            wei[v].add(val);
        }
        System.out.println(getSum());
        solve(0,-1);
        System.out.println(sub);
        
        System.out.print(ans);
    }

    
    public static TreeMap<Integer,Long> solve(int root,int par) {
        TreeMap<Integer,Long> map=new TreeMap<>();
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==par) {
                continue;
            }
            TreeMap<Integer,Long> tmp=solve(adj_lst[root].get(i),root);
            if(tmp.containsKey(wei[root].get(i))) {
                tmp.replace(wei[root].get(i), (long)subtree_size[adj_lst[root].get(i)]);
            }
            else {
                tmp.put(wei[root].get(i), (long)subtree_size[adj_lst[root].get(i)]);
            }
            for(int key:tmp.keySet()) {
                
                if(map.containsKey(key)) {
                    System.out.println(root+" "+key);
                    sub+=tmp.get(key)*map.get(key);
                    map.replace(key, tmp.get(key)+map.get(key));
                }
                else {
                    map.put(key, tmp.get(key));
                }
            }
        }
        return map;
    }
    
    
    
    
    
    
    // Utility function to create an
    // edge between two vertices
    static void AddEdge(int a, int b)
    {

        // Add a to b's list
        tree[a].add(b);

        // Add b to a's list
        tree[b].add(a);
    }

    // Function to calculate the subtree size
    static int dfs(int node)
    {

        // Mark visited
        vis[node] = 1;
        subtree_size[node] = 1;

        // For every adjacent node
        for(int child : (ArrayList<Integer>)tree[node])
        {

            // If not already visited
            if (vis[child] == 0)
            {

                // Recursive call for the child
                subtree_size[node] += dfs(child);
            }
        }
        return subtree_size[node];
    }

    // Function to calculate the
    // contribution of each edge
    static int contribution(int node, int ans)
    {

        // Mark current node as visited
        vis[node] = 1;

        // For every adjacent node
        for(int child : (ArrayList<Integer>)tree[node])
        {

            // If it is not already visited
            if (vis[child] == 0)
            {
                ans += (subtree_size[child] *
                   (n - subtree_size[child]));
                ans = contribution(child, ans);
            }
        }
        return ans;
    }

    // Function to return the required sum
    static int getSum()
    {

        // First pass of the dfs
        Arrays.fill(vis, 0);
        dfs(0);

        // Second pass
        int ans = 0;
        Arrays.fill(vis, 0);
        ans = contribution(0, ans);

        return ans;
    }
}
