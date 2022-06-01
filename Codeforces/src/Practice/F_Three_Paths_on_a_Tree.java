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
public class F_Three_Paths_on_a_Tree {
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
    static int node1,node2,max_dist;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int n=input.scanInt();
        adj_lst=new ArrayList[n];
        vis=new boolean[n];
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<>();
        }
        for(int i=0;i<n-1;i++) {
            int u=input.scanInt()-1;
            int v=input.scanInt()-1;
            adj_lst[u].add(v);
            adj_lst[v].add(u);
        }
        node1=node2=-1;
        int root=-1;
        for(int i=0;i<n;i++) {
            if(adj_lst[i].size()>1) {
                root=i;
                break;
            }
        }
        DFS(root,-1);
        mark(node1,node2);
        int max=-1,max_indx=-1;
        for(int i=0;i<n;i++) {
            if(vis[i]) {
                int tmp[]=dist(i,-1);
                if(tmp[0]>max) {
                    max=tmp[0];
                    max_indx=tmp[1];
                }
            }
        }
        if(max_indx==node1 || max_indx==node2) {
            for(int i=0;i<n;i++) {
                if(i!=node1 && i!=node2) {
                    max_indx=i;
                    break;
                }
            }
        }
        node1++;
        node2++;
        max_indx++;
        System.out.println(max_dist+max-1);
//        System.out.println(max_dist+" "+max);
        System.out.println(node1+" "+node2+" "+max_indx);
    }
    public static int[] DFS(int root,int par) {
        if(adj_lst[root].size()==1) {
            return new int[]{root,1};
        }
        int dist[][];
        if(par==-1) {
            dist=new int[adj_lst[root].size()][2];
        }
        else {
            dist=new int[adj_lst[root].size()-1][2];
        }
        for(int i=0,indx=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==par) {
                continue;
            }
            dist[indx]=DFS(adj_lst[root].get(i),root);
            indx++;
        }
        if(dist.length==1 && dist[0][1]>max_dist) {
            max_dist=dist[0][1];
            node1=root;
            node2=dist[0][0];
            return new int[]{dist[0][0],dist[0][1]+1};
        }
        int max=-1,max_indx=-1,sec_max=-1,sec_max_indx=-1;
        for(int i=0;i<dist.length;i++) {
            if(dist[i][1]>max) {
                max=dist[i][1];
                max_indx=i;
            }
        }
        for(int i=0;i<dist.length;i++) {
            if(i==max_indx) {
                continue;
            }
            if(dist[i][1]>sec_max) {
                sec_max=dist[i][1];
                sec_max_indx=i;
            }
        }
        if(max+sec_max>max_dist) {
            max_dist=max+sec_max;
            node1=dist[max_indx][0];
            node2=dist[sec_max_indx][0];
        }
        return new int[]{dist[max_indx][0],dist[max_indx][1]+1};
    }
    public static boolean mark(int root,int target) {
        vis[root]=true;
        if(root==target) {
            return true;
        }
        for(int i=0;i<adj_lst[root].size();i++) {
            if(vis[adj_lst[root].get(i)]) {
                continue;
            }
            if(mark(adj_lst[root].get(i),target)) {
                return true;
            }
        }
        vis[root]=false;
        return false;
    }
    public static int[] dist(int root,int par) {
        int max=0,max_indx=root;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==par || vis[adj_lst[root].get(i)]) {
                continue;
            }
            int tmp[]=dist(adj_lst[root].get(i),root);
            if(tmp[0]>max) {
                max=tmp[0];
                max_indx=tmp[1];
            }
        }
        return new int[]{max+1,max_indx};
    }
}
