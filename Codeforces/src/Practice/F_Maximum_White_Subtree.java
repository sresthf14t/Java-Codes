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
public class F_Maximum_White_Subtree {
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
    static int color[],diff[],ans[];
    static ArrayList<Integer> adj_lst[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int n=input.scanInt();
        color=new int[n];
        for(int i=0;i<n;i++) {
            color[i]=input.scanInt();
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
        diff=new int[n];
        ans=new int[n];
        calc_diff(0,-1);
        ans[0]=diff[0];
        solve(0,-1);
        for(int i=0;i<n;i++) {
            System.out.print(ans[i]+" ");
        }
        System.out.println();
    }
    public static void solve(int root,int par) {  
        int tmp=diff[root];
        if(root!=0) {
            if(diff[root]>0) {
                if(color[par]==0) {
                    diff[par]=Math.max(diff[par]-diff[root],-1);
                }
                else {
                    diff[par]=Math.max(diff[par]-diff[root],1);
                }
            }
//            System.out.println((root+1)+" "+(par+1)+" "+diff[root]+" "+diff[par]);
            if(diff[par]>0) {
                diff[root]+=diff[par];
            }
            ans[root]=diff[root];
        }
        
//        System.out.println("----------------------"+(root+1)+"----------------------");
//        for(int i=0;i<diff.length;i++) {
//            System.out.print(diff[i]+" ");
//        }
//        System.out.println("\n--------------------------------------------");
        int tmp1=diff[root];
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==par) {
                continue;
            }
            solve(adj_lst[root].get(i),root);
            diff[root]=tmp1;
        }
        diff[root]=tmp;
    }
    public static void calc_diff(int root,int par) {
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==par) {
                continue;
            }
            calc_diff(adj_lst[root].get(i),root);
        }
        if(color[root]==0) {
            diff[root]=-1;
        }
        else {
            diff[root]=1;
        }
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==par) {
                continue;
            }
            if(diff[adj_lst[root].get(i)]>0) {
                diff[root]+=diff[adj_lst[root].get(i)];
            }
        }
    }
}
