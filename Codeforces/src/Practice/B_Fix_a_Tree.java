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
public class B_Fix_a_Tree {
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
    static int fin_root=-1;
    static int color[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int n=input.scanInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt()-1;
        }
        //No of nodes
        adj_lst=new ArrayList[n];
        vis=new boolean[n];
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<Integer>();
        }
        //No of edges
        for(int i=0;i<n;i++) {
            // input u & v
            if(arr[i]==i) {
                continue;
            }
            adj_lst[arr[i]].add(i);
        }
        color=new int[n];
        boolean par[]=new boolean[n];
        for(int i=0;i<n;i++) {
            for(int j=0;j<adj_lst[i].size();j++) {
                par[adj_lst[i].get(j)]=true;
            }
        }
        int root=-1,clr=1;
        for(int i=0;i<n;i++) {
            if(!par[i]) {
                root=i;
                DFS(i,clr);
                clr++;
            }
        }
        for(int i=0;i<n;i++) {
            if(!vis[i]) {
                DFS(i,clr);
                clr++;
            }
        }
        
//        for(int i=0;i<adj_lst.length;i++) {
//            System.out.print((i+1)+"->");
//            for(int j=0;j<adj_lst[i].size();j++) {
//                System.out.print((adj_lst[i].get(j)+1)+" ");
//            }
//            System.out.println();
//        }
        par=new boolean[n];
        for(int i=0;i<n;i++) {
            for(int j=0;j<adj_lst[i].size();j++) {
                par[adj_lst[i].get(j)]=true;
            }
        }
        if(root==-1) {
            for(int i=0;i<n;i++) {
                if(!par[i]) {
                    root=i;
                    break;
                }
            }
        }
//        System.out.println(root);
        for(int i=0;i<n;i++) {
            if(!par[i] && i!=root) {
//                System.out.println(first+" "+i);
                adj_lst[root].add(i);
            }
        }
        
        int ans[]=new int[n];
        for(int i=0;i<n;i++) {
            for(int j=0;j<adj_lst[i].size();j++) {
                ans[adj_lst[i].get(j)]=i;
            }
        }
        ans[root]=root;
        int cnt=0;
        for(int i=0;i<n;i++) {
            if(arr[i]!=ans[i]) {
                cnt++;
            }
        }
        StringBuilder fin=new StringBuilder("");
        for(int i=0;i<n;i++) {
            ans[i]++;
            fin.append(ans[i]+" ");
        }
        System.out.println(cnt+"\n"+fin);
    }
    public static void DFS(int root,int clr) {
//        System.out.println(root);
        color[root]=clr;
        vis[root]=true;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(!vis[adj_lst[root].get(i)]) {
                DFS(adj_lst[root].get(i),clr);
            }
            else if(color[adj_lst[root].get(i)]==clr){
                adj_lst[root].remove(i);
                i--;
            }
        }
    }
}
