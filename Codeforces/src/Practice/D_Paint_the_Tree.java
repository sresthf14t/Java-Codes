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
public class D_Paint_the_Tree {
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
    static int n,depth[];
    static long arr[][];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n=input.scanInt();
        adj_lst=new ArrayList[n];
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<>();
        }
        arr=new long[3][n];
        depth=new int[n];
        for(int i=0;i<3;i++) {
            for(int j=0;j<n;j++) {
                arr[i][j]=input.scanInt();
            }
        }
        for(int i=0;i<n-1;i++) {
            int u=input.scanInt()-1;
            int v=input.scanInt()-1;
            adj_lst[u].add(v);
            adj_lst[v].add(u);
        }
        int root=-1,max=0;
        for(int i=0;i<n;i++) {
            if(adj_lst[i].size()==1) {
                root=i;
            }
            max=Math.max(max,adj_lst[i].size());
        }
        if(max>2) {
            System.out.println(-1);
            return;
        }
        DFS(root,0,-1);
        long ans=Long.MAX_VALUE;
        int indx1=-1,indx2=-1,indx3=-1;
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                if(i==j) {
                    continue;
                }
                for(int k=0;k<3;k++) {
                    if(j==k) {
                        continue;
                    }
                    if(i==k) {
                        continue;
                    }
                    long tmp=calc(i,j,k);
//                    System.out.println(i+" "+j+" "+k+" "+ans);
                    if(tmp<ans) {
                        ans=tmp;
                        indx1=i;
                        indx2=j;
                        indx3=k;
                    }
                }
            }
        }
        StringBuilder fin=new StringBuilder("");
        for(int i=0;i<n;i++) {
            if(depth[i]%3==indx1) {
                fin.append(1+" ");
            }
            if(depth[i]%3==indx2) {
                fin.append(2+" ");
            }
            if(depth[i]%3==indx3) {
                fin.append(3+" ");
            }
        }
        System.out.println(ans);
        System.out.println(fin);
    }
    public static void DFS(int root,int dep,int par) {
        depth[root]=dep;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==par) {
                continue;
            }
            DFS(adj_lst[root].get(i),dep+1,root);
        }
    }
    public static long calc(int indx1,int indx2,int indx3) {
        long ans=0;
        for(int i=0;i<n;i++) {
            if(depth[i]%3==indx1) {
                ans+=arr[0][i];
            }
            if(depth[i]%3==indx2) {
                ans+=arr[1][i];
            }
            if(depth[i]%3==indx3) {
                ans+=arr[2][i];
            }
        }
        return ans;
    }
}
