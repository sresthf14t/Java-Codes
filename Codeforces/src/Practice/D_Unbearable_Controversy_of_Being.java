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
public class D_Unbearable_Controversy_of_Being {
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
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int n=input.scanInt();
        int m=input.scanInt();
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
//            adj_lst[v].add(u);
        }
        long ans=0;
        for(int i=0;i<n;i++) {
            ArrayList<Integer> arr=new ArrayList<>();
            DFS(i,0,arr,-1);
            ans+=solve(arr);
        }
        System.out.println(ans);
    }
    public static long solve(ArrayList<Integer> arr) {
        long ans=0;
        arr.sort(null);
        for(int i=0;i<arr.size();i++) {
            int j=i;
            long cnt=0;
            while(j<arr.size() && arr.get(j)==arr.get(i)) {
                j++;
                cnt++;
            }
            if(cnt>1) {
//                System.out.println(cnt);
                ans+=(cnt*(cnt-1))/2;
            }
            i=j-1;
        }
        return ans;
    }
    public static void DFS(int root,int dep,ArrayList<Integer> arr,int par) {
        if(dep==2) {
            arr.add(root);
            return;
        }
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)!=par) {
                DFS(adj_lst[root].get(i),dep+1,arr,root);
            }
        }
    }
}
