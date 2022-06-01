/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_649_Div_2;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class D {
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
    static ArrayList<Integer> adj_lst[],path;
    static boolean vis[];
    static int color[],strt,end;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int n=input.scanInt();
        int m=input.scanInt();
        int k=input.scanInt();
        adj_lst=new ArrayList[n];
        vis=new boolean[n];
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<Integer>();
        }
        //No of edges
        for(int i=0;i<m;i++) {
            // input u & v
            int u=input.scanInt()-1;
            int v=input.scanInt()-1;
            adj_lst[u].add(v);
            adj_lst[v].add(u);
        }
        if(m<=n-1) {
            color=new int[n];
            Arrays.fill(color, -1);
            for(int i=0;i<n;i++) {
                if(vis[i]) {
                    continue;
                }
                DFS_color(i);
            }
            int zero=0,one=0;
            for(int i=0;i<n;i++) {
                if(color[i]==0) {
                    zero++;
                }
                else {
                    one++;
                }
            }
            int len=(int)Math.ceil((float)k/2);
            StringBuilder ans=new StringBuilder("");
            if(zero>one) {
                for(int i=0;i<n && len>0;i++) {
                    if(color[i]==0) {
                        ans.append((i+1)+" ");
                        len--;
                    }
                }
            }
            else {
                for(int i=0;i<n && len>0;i++) {
                    if(color[i]==1) {
                        ans.append((i+1)+" ");
                        len--;
                    }
                }
            }
            System.out.println(1);
            System.out.println(ans);
            return;
        }
        strt=end=-1;
        path=new ArrayList<>();
        for(int i=0;i<n;i++) {
            if(!vis[i]) {
                DFS(i,-1);
            }
            if(strt!=-1) {
                break;
            }
        }
        vis=new boolean[n];
        DFS_path(strt,new ArrayList<Integer>());
        if(path.size()<=k) {
            StringBuilder ans=new StringBuilder("");
            for(int i=0;i<path.size();i++) {
                ans.append((path.get(i)+1)+" ");
            }
            System.out.println(2);
            System.out.println(path.size());
            System.out.println(ans);
            return;
        }
        int len=(int)Math.ceil((float)k/2);
        StringBuilder ans=new StringBuilder("");
        for(int i=1;i<path.size() && len>0;i+=2) {
            ans.append((path.get(i)+1)+" ");
            len--;
        }
        System.out.println(1);
        System.out.println(ans);
        
    }
    public static void DFS(int root,int par) {
        if(strt!=-1) {
            return;
        }
        vis[root]=true;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==par) {
                continue;
            }
            if(!vis[adj_lst[root].get(i)]) {
                DFS(adj_lst[root].get(i),root);
            }
            else {
                strt=adj_lst[root].get(i);
                end=root;
                return;
            }
        }
    }
    
    public static void DFS_path(int root,ArrayList<Integer> arrli) {
        arrli.add(root);
        if(root==end) {
            path=new ArrayList<>(arrli);
        }
        vis[root]=true;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(!vis[adj_lst[root].get(i)]) {
                DFS_path(adj_lst[root].get(i),arrli);
            }
        }
        arrli.remove(arrli.size()-1);
    }
    
    public static void DFS_color(int root) {
//        System.out.println(root);
        vis[root]=true;
        boolean zero=false,one=false;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(color[adj_lst[root].get(i)]==0) {
                zero=true;
            }
            if(color[adj_lst[root].get(i)]==1) {
                one=true;
            }
        }
        if(!one) {
            color[root]=0;
        }
        else {
            color[root]=1;
        }
        for(int i=0;i<adj_lst[root].size();i++) {
            if(!vis[adj_lst[root].get(i)]) {
                DFS_color(adj_lst[root].get(i));
            }
        }
    }
}
