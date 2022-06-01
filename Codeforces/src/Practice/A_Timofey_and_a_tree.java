/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

/**
 *
 * @author SRESTH
 */
import java.util.*;
import java.io.*;
public class A_Timofey_and_a_tree {
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
    static HashMap<Integer,Integer> map[];
    static int clr[],prev[],fin;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        
        int n=input.scanInt();
        adj_lst=new ArrayList[n];
        map=new HashMap[n];
        clr=new int[n];
        prev=new int[n];
        prev[0]=-1;
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<>();
            map[i]=new HashMap<>();
        }
        for(int i=0;i<n-1;i++) {
            int u=input.scanInt()-1;
            int v=input.scanInt()-1;
            adj_lst[u].add(v);
            adj_lst[v].add(u);
        }
        for(int i=0;i<n;i++) {
            clr[i]=input.scanInt();
        }
        
        fin=-1;
        DFS(0,-1);
        
        if(fin!=-1) {
            if(check(n)) {
                ans.append("YES\n"+(fin+1));
            }
            else {
                ans.append("NO");
            }
        }
        else {
            solve(0,-1);
            if(fin==-1) {
                ans.append("NO");
            }
            else {
                ans.append("YES\n"+(fin+1));
            }
        } 
        
        System.out.println(ans);
    }
    
    static boolean DFS(int root,int p) {
        prev[root]=p;
        add(root,clr[root]);
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==p) {
                continue;
            }
            if(DFS(adj_lst[root].get(i),root)) {
                return true;
            }
            merge(root, adj_lst[root].get(i));
        }
        if(map[root].size()>=2) {
            fin=root;
            return true;
        }
        return false;
    }
    
    static boolean DFS_1(int root,int p) {
        prev[root]=p;
        add(root,clr[root]);
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==p) {
                continue;
            }
            if(DFS(adj_lst[root].get(i),root)) {
                return true;
            }
            merge(root, adj_lst[root].get(i));
        }
        if(root!=fin && map[root].size()>=2) {
            return true;
        }
        return false;
    }
    
    
    public static void solve(int root,int prev) {
        if(prev!=-1) {
            rem(prev,root);
        }
        
        boolean is_pos=true;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(map[adj_lst[root].get(i)].size()!=1) {
                is_pos=false;
            }
        }
        if(is_pos) {
//            System.out.println(root);
            fin=root;
        }
        
        if(prev!=-1) {
            merge(root,prev);
        }
        
        
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==prev) {
                continue;
            }
            solve(adj_lst[root].get(i),root);
        }
        
        if(prev!=-1) {
            rem(root,prev);
        }
        if(prev!=-1) {
            merge(prev,root);
        }
        
    }
    
    public static void merge(int indx1,int indx2) {
        for(int i:map[indx2].keySet()) {
            add(indx1,i);
        }
    }
    public static void rem(int indx1,int indx2) {
        for(int i:map[indx2].keySet()) {
            del(indx1,i);
        }
    }
    
    public static void add(int indx,int val) {
        if(!map[indx].containsKey(val)) {
            map[indx].put(val, 0);
        }
        map[indx].replace(val, map[indx].get(val)+1);
    }
    
    public static void del(int indx,int val) {
        map[indx].replace(val, map[indx].get(val)-1);
        if(map[indx].get(val)==0) {
            map[indx].remove(val);
        }
    }
    
    public static boolean check(int n) {
        map=new HashMap[n];
        for(int i=0;i<n;i++) {
            map[i]=new HashMap<>();
        }
        if(DFS_1(fin,-1)) {
            return false;
        }
        boolean is_pos=true;
        for(int i=0;i<adj_lst[fin].size();i++) {
            if(map[adj_lst[fin].get(i)].size()!=1) {
                is_pos=false;
            }
        }
        return is_pos;
    }
}
