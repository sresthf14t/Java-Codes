/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package June_Cook_Off_2020_Division_2;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
import java.math.BigDecimal;
public class PERCAPTA {
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
    static int color[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int test=input.scanInt();
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            int m=input.scanInt();
            int arr[]=new int[n];
            int brr[]=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt();
            }
            for(int i=0;i<n;i++) {
                brr[i]=input.scanInt();
            }
            adj_lst=new ArrayList[n];
            vis=new boolean[adj_lst.length];
            for(int i=0;i<adj_lst.length;i++) {
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
            
            float div[]=new float[n];
            for(int i=0;i<n;i++) {
                div[i]=(float)arr[i]/(float)brr[i];
            }
            float max=0;
            for(int i=0;i<n;i++) {
                max=Math.max(max,div[i]);
            }
            for(int i=0;i<n;i++) {
                if(div[i]!=max) {
                    vis[i]=true;
                }
            }
            color=new int[n];
            int clr=1;
            for(int i=0;i<n;i++) {
                if(!vis[i]) {
                    DFS(i,clr);
                    clr++;
                }
            }
            int freq[]=new int[n+1];
            for(int i=0;i<n;i++) {
                freq[color[i]]++;
            }
            int max_cnt=0,max_clr=0;
            for(int i=1;i<freq.length;i++) {
                if(freq[i]>max_cnt) {
                    max_cnt=freq[i];
                    max_clr=i;
                }
            }
            StringBuilder ans=new StringBuilder("");
            for(int i=0;i<n;i++) {
                if(color[i]==max_clr) {
                    ans.append((i+1)+" ");
                }
            }
            System.out.println(max_cnt+"\n"+ans);
        }
    }
    public static void DFS(int root,int clr) {
//        System.out.println(root);
        color[root]=clr;
        vis[root]=true;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(!vis[adj_lst[root].get(i)]) {
                DFS(adj_lst[root].get(i),clr);
            }
        }
    }
}
