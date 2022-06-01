/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unacademy_Teaching_Assistant_Hiring_Contest;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class NOCYCLE {
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
    static int mark[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int n=input.scanInt();
        int m=input.scanInt();
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
        mark=new int[n];
        int cnt=0;
        for(int i=0;i<n;i++) {
            if(!vis[i]) {
               DFS(i,cnt);
               cnt++;
            }
        }
        int freq[]=new int[cnt];
        for(int i=0;i<n;i++) {
            freq[mark[i]]++;
        }
        long total=0;
        for(int i=0;i<freq.length;i++) {
            if(freq[i]==0) {
                continue;
            }
            total+=freq[i]-1;
        }
        System.out.println(Math.max(0,m-total));
    }
    public static void DFS(int root,int cnt) {
        mark[root]=cnt;
        vis[root]=true;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(!vis[adj_lst[root].get(i)]) {
                DFS(adj_lst[root].get(i),cnt);
            }
        }
    }
}
