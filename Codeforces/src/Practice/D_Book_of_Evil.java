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
public class D_Book_of_Evil {
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
    static ArrayList<Integer> adj_lst[],count[];
    static boolean vis[],is_affected[];
    static int n,m,d;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n=input.scanInt();
        m=input.scanInt();
        d=input.scanInt();
        adj_lst=new ArrayList[n];
        count=new ArrayList[n];
        vis=new boolean[n];
        is_affected=new boolean[n];
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<Integer>();
            count[i]=new ArrayList<Integer>();
        }
        for(int i=0;i<m;i++) {
            is_affected[input.scanInt()-1]=true;
        }
        for(int i=0;i<n-1;i++) {
            int u=input.scanInt()-1;
            int v=input.scanInt()-1;
            adj_lst[u].add(v);
            adj_lst[v].add(u);
        }
        DFS(0,-1);
        System.out.println(solve(0,-1,-1));
    }
    
    public static int solve(int root,int par,int prev) {
        int ans=0;
        int max=-1,sec_max=-1,max_indx=-1,sec_max_indx=-1;
        for(int i=0;i<count[root].size();i++) {
            if(count[root].get(i)>max) {
                max=count[root].get(i);
                max_indx=i;
            }
        }
        for(int i=0;i<count[root].size();i++) {
            if(i==max_indx) {
                continue;
            }
            if(count[root].get(i)>sec_max) {
                sec_max=count[root].get(i);
                sec_max_indx=i;
            }
        }
        if(prev>=max) {
            sec_max=max;
            sec_max_indx=max_indx;
            max=prev;
            max_indx=count[root].size()+1;
        }
        else if(prev>=sec_max) {
            sec_max=prev;
            sec_max_indx=count[root].size()+1;
        }
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==par) {
                continue;
            }
            int nxt;
            if(i!=max_indx) {
                nxt=max;
            }
            else {
                nxt=sec_max;
            }
            ans+=solve(adj_lst[root].get(i),root,nxt==-1?-1:nxt+1);
        }
        if(max<=d) {
            ans++;
        }
//        System.out.println((root+1)+" "+max+" "+sec_max+" "+prev+" "+ans);
        return ans;
    }
    
    
    public static int DFS(int root,int par) {
        int max=-1;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==par) {
                count[root].add(-1);
                continue;
            }
            int tmp=DFS(adj_lst[root].get(i),root);
            count[root].add(tmp);
            max=Math.max(max,tmp);
        }
        if(is_affected[root]) {
            count[root].add(0);
            max=Math.max(max,0);
        }
        if(max==-1) {
            return -1;
        }
        return max+1;
    }
}
