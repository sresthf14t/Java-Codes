/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_640_Div_4;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class G {
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
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int test=input.scanInt();
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            solve(n);
        }
    }
    static ArrayList<Integer> adj_lst[],path;
    static boolean vis[];
    public static void solve(int n) {
        path=null;
        adj_lst=new ArrayList[n];
        vis=new boolean[n];
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<Integer>();
        }
        //No of edges
        for(int i=0;i<n;i++) {
            for(int j=i-2;j>=0 && j>=i-4;j--) {
                adj_lst[i].add(j);
            }
        }
        for(int i=0;i<n;i++) {
            for(int j=i+2;j<n && j<=i+4;j++) {
                adj_lst[i].add(j);
            }
        }
        for(int i=0;i<n;i++) {
            vis=new boolean[n];
            DFS(i,new ArrayList<Integer>(),n);
            if(path!=null) {
                break;
            }
        }
        if(path==null) {
            System.out.println(-1);
            return;
        }
        for(int i=0;i<path.size();i++) {
            System.out.print((path.get(i)+1)+" ");
        }
        System.out.println();
    }
    public static void DFS(int root,ArrayList<Integer> tmp,int n) {
        tmp.add(root);
//        print(tmp);
        if(tmp.size()==n) {
            path=new ArrayList<>(tmp);
        }
        vis[root]=true;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(!vis[adj_lst[root].get(i)]) {
                DFS(adj_lst[root].get(i),tmp,n);
            }
        }
        tmp.remove(tmp.size()-1);
    }
    public static void print(ArrayList<Integer> tmp) {
        for(int i=0;i<tmp.size();i++) {
            System.out.print(tmp.get(i)+" ");
        }
        System.out.println();
    }
}
