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
import java.io.*; 
import java.util.*;
public class D_Coloring_Edges {
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
    static boolean vis[],vis1[];
    static int arr[][],max;
    public static void main(String args[]) throws IOException{
        Scan input=new Scan();
        int n=input.scanInt();
        arr=new int[n][n];
        //No of nodes
        adj_lst=new ArrayList[n];
        vis=new boolean[n];
        vis1=new boolean[n];
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<Integer>();
        }
        //No of edges
        int m=input.scanInt();
        max=1;
        int edges[][]=new int[2][m];
        for(int i=0;i<m;i++) {
            // input u & v
            int u=input.scanInt()-1;
            int v=input.scanInt()-1;
            adj_lst[u].add(v);
            edges[0][i]=u;
            edges[1][i]=v;
            arr[u][v]=1;
        }
//        for(int i=0;i<adj_lst.length;i++) {
//            System.out.print(i+"->");
//            for(int j=0;j<adj_lst[i].size();j++) {
//                System.out.print(adj_lst[i].get(j)+" ");
//            }
//            System.out.println();
//        }
        for(int i=0;i<n;i++) {
            if(!vis[i]) {
                DFS(i);
            }
        }
        StringBuilder ans=new StringBuilder("");
        ans.append(max+"\n");
        for(int i=0;i<m;i++) {
            ans.append(arr[edges[0][i]][edges[1][i]]+" ");
        }
        System.out.println(ans);
    }
    
    public static void DFS(int root) {
//        System.out.println(root);
        vis[root]=true;
        vis1[root]=true;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(vis1[(int)adj_lst[root].get(i)]) {
                arr[root][(int)adj_lst[root].get(i)]=2;
                max=2;
            }
            else {
                DFS((int)adj_lst[root].get(i));
            }
        }
        vis1[root]=false;
    }

}
