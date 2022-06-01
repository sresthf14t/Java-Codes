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
public class D_Distance_in_Tree {
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
    static int n,k,parent[],dist[][];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n=input.scanInt();
        k=input.scanInt();
        dist=new int[n][k+1];
        adj_lst=new ArrayList[n];
        parent=new int[n];
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<>();
        }
        for(int i=0;i<n-1;i++) {
            int u=input.scanInt()-1;
            int v=input.scanInt()-1;
            adj_lst[u].add(v);
            adj_lst[v].add(u);
        }
        DFS(0,-1);
//        for(int i=0;i<n;i++) {
//            for(int j=0;j<k+1;j++) {
//                System.out.print(dist[i][j]+" ");
//            }
//            System.out.println();
//        }
        long ans=0;
        for(int i=0;i<n;i++) {
            ans+=solve(i);
//            System.out.println((i+1)+" "+solve(i));
        }
        System.out.println(ans/2);
    }
        public static long solve(int root) {
        long ans=dist[root][k];
        int cnt=0;
        int tmp_root=root;
        while(parent[tmp_root]!=-1) {
            int tmp=tmp_root;
            tmp_root=parent[tmp_root];
            cnt++;
            if(cnt>k) {
                break;
            }
            if(k-cnt-1>=0) {
                dist[tmp_root][k-cnt]-=dist[tmp][k-cnt-1];
            }
            ans+=dist[tmp_root][k-cnt];
            if(k-cnt-1>=0) {
                dist[tmp_root][k-cnt]+=dist[tmp][k-cnt-1];
            }
//            if(k-(2*cnt)>=0) {
//                ans-=dist[root][k-(2*cnt)];
//            }
//            else {
//                ans--;
//            }
//            if(cnt==k) {
//                ans++;
//            }
//            System.out.println(tmp_root+" "+cnt);
        }
        return ans;
    }
    public static int[] DFS(int root,int par) {
        parent[root]=par;
        int cnt[]=new int[k+1];
        cnt[0]=1;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==par) {
                continue;
            }
            int tmp[]=DFS(adj_lst[root].get(i),root);
            for(int j=0;j<cnt.length-1;j++) {
                cnt[j+1]+=tmp[j];
            }
        }
        for(int i=0;i<cnt.length;i++) {
            dist[root][i]=cnt[i];
        }
        return cnt;
    }
}
