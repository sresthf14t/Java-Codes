/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Code_Ensemble_Rated_for_Division_2;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class CENS20F {
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
    static int n;
    static long arr[],sum[];
    static ArrayList<Integer> adj_lst[];
    static boolean vis[];
    static int parent[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int test=input.scanInt();
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            n=input.scanInt();
            int q=input.scanInt();
            arr=new long[n];
            adj_lst=new ArrayList[n];
            vis=new boolean[n];
            sum=new long[n];
            parent=new int[n];
            for(int i=0;i<n;i++) {
                adj_lst[i]=new ArrayList<>();
                arr[i]=input.scanInt();
            }
            for(int i=0;i<n-1;i++) {
                int u=input.scanInt()-1;
                int v=input.scanInt()-1;
                adj_lst[u].add(v);
                adj_lst[v].add(u);
            }
            DFS(0,-1,0,0);
            DFS(0,-1,0,1);
            for(int i=0;i<q;i++) {
                int root=input.scanInt()-1;
                if(vis[root]) {
                    continue;
                }
                arr[root]=sum[root];
                mark(root,parent[root],0);
                vis[root]=true;
            }
            for(int i=0;i<n;i++) {
                ans.append(arr[i]+" ");
            }
            ans.append("\n");
        }
        System.out.println(ans);
    }
    public static void mark(int root,int par,int dep) {
//        System.out.println(dep+" "+(root+1));
        if(dep!=0 && dep%2==0) {
            sum[root]=0;
            arr[root]=0;
            if(vis[root]) {
                return;
            }
            vis[root]=true;
        }
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==par) {
                continue;
            }
            mark(adj_lst[root].get(i),root,dep+1);
        }
    }
    public static long DFS(int root,int par,int dep,int parity) {
        parent[root]=par;
        long tmp=0;
        if(dep%2==parity%2) {
            tmp+=arr[root];
        }
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==par) {
                continue;
            }
            tmp+=DFS(adj_lst[root].get(i),root,dep+1,parity);
        }
        if(dep%2==parity%2) {
            sum[root]=tmp;
        }
        return tmp;
    }
}
