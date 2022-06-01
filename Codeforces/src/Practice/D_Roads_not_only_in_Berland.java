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
public class D_Roads_not_only_in_Berland {
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
    static int n,cnt;
    static ArrayList<Integer> adj_lst[];
    static boolean vis[];
    static ArrayList<Integer> del;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n=input.scanInt();
        adj_lst=new ArrayList[n];
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<>();
        }
        cnt=0;
        for(int i=0;i<n-1;i++) {
            int u=input.scanInt()-1;
            int v=input.scanInt()-1;
            adj_lst[u].add(v);
            adj_lst[v].add(u);
        }
        del=new ArrayList<>();
        for(int i=0;i<n;i++) {
            vis=new boolean[n];
            if(DFS(i,-1)) {
                i--;
            }
        }
        StringBuilder ans=new StringBuilder("");
        ArrayList<Integer> add=new ArrayList<>();
        vis=new boolean[n];
        for(int i=0;i<n;i++) {
            if(vis[i]) {
                continue;
            }
            add.add(i+1);
            DFS(i,-1);
        }
        int indx=0;
        for(int i=0;i<del.size();i+=2) {
            ans.append(del.get(i)+" "+del.get(i+1)+" "+add.get(indx)+" "+add.get(indx+1)+"\n");
            indx++;
        }
        System.out.println(cnt+"\n"+ans);
    }
    public static boolean DFS(int root,int prev) {
        vis[root]=true;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==prev) {
                continue;
            }
            if(!vis[adj_lst[root].get(i)]) {
                if(DFS(adj_lst[root].get(i),root)) {
                    return true;
                }
            }
            else {
                del.add(root+1);
                del.add(adj_lst[root].get(i)+1);
                adj_lst[adj_lst[root].get(i)].remove(new Integer(root));
                adj_lst[root].remove(i);
                cnt++;
                return true;
            }
        }
        return false;
    }
}
