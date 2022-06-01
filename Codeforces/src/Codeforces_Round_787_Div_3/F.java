/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_787_Div_3;

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
    static boolean work[],has_work[],has_y[];
    static int n,k,x,y,last_indx,depth[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        int test=input.scanInt();
        for(int tt=1;tt<=test;tt++) {
            n=input.scanInt();
            k=input.scanInt();
            x=input.scanInt()-1;
            y=input.scanInt()-1;
            work=new boolean[n];
            has_work=new boolean[n];
            has_y=new boolean[n];
            depth=new int[n];
            last_indx=-1;
            for(int i=0;i<k;i++) {
                work[input.scanInt()-1]=true;
            }
            adj_lst=new ArrayList[n];
            for(int i=0;i<n;i++) {
                adj_lst[i]=new ArrayList<>();
            }
            for(int i=0;i<n-1;i++) {
                int u=input.scanInt()-1;
                int v=input.scanInt()-1;
                adj_lst[u].add(v);
                adj_lst[v].add(u);
            }
            work_dfs(x,-1);
            y_dfs(x,-1);
            find_last(x,-1);
            depth(x,-1,0);
//            System.out.println(last_indx+1);
//            System.out.println(dfs(x,-1)-1-(depth[last_indx]));
            int cnt=dfs(x,-1)-1-(depth[last_indx]);
            depth(y,-1,0);
            cnt+=depth[last_indx];
            ans.append(cnt+"\n");
        }
        System.out.print(ans);
    }
    
    public static void depth(int root,int par,int dep) {
        depth[root]=dep;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==par) {
                continue;
            }
            depth(adj_lst[root].get(i),root,dep+1);
        }
    }
    
    public static boolean work_dfs(int root,int par) {
        if(work[root]) {
            has_work[root]=true;
        }
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==par) {
                continue;
            }
            has_work[root]|=work_dfs(adj_lst[root].get(i),root);
        }
        return has_work[root];
    }
    
    public static boolean y_dfs(int root,int par) {
        if(root==y) {
            has_y[root]=true;
        }
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==par) {
                continue;
            }
            has_y[root]|=y_dfs(adj_lst[root].get(i),root);
        }
        return has_y[root];
    }
    
    public static void find_last(int root,int par) {
        last_indx=root;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==par) {
                continue;
            }
            if(!has_work[adj_lst[root].get(i)]) {
                continue;
            }
            if(!has_y[adj_lst[root].get(i)]) {
                find_last(adj_lst[root].get(i),root);
            }
        }
        
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==par) {
                continue;
            }
            if(!has_work[adj_lst[root].get(i)]) {
                continue;
            }
            if(has_y[adj_lst[root].get(i)]) {
                find_last(adj_lst[root].get(i),root);
            }
        }
    }
    
    public static int dfs(int root,int par) {
        int cnt=0;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==par) {
                continue;
            }
            if(!has_work[adj_lst[root].get(i)]) {
                continue;
            }
            if(!has_y[adj_lst[root].get(i)]) {
                cnt+=1+dfs(adj_lst[root].get(i),root);
            }
        }
        
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==par) {
                continue;
            }
            if(!has_work[adj_lst[root].get(i)]) {
                continue;
            }
            if(has_y[adj_lst[root].get(i)]) {
                cnt+=1+dfs(adj_lst[root].get(i),root);
            }
        }
        return cnt+1;
    }
}
