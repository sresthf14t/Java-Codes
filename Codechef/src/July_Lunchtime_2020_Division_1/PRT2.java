/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package July_Lunchtime_2020_Division_1;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class PRT2 {
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
    static long arr[];
    static HashMap<Integer,Long> dp[][];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int test=input.scanInt();
        StringBuilder fin=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            int k=input.scanInt();
            adj_lst=new ArrayList[n];
            for(int i=0;i<n;i++) {
                adj_lst[i]=new ArrayList<>();
            }
            arr=new long[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt();
            }
            for(int i=0;i<n-1;i++) {
                int u=input.scanInt()-1;
                int v=input.scanInt()-1;
            }
            if(n==2) {
                adj_lst[0].add(1);
                adj_lst[1].add(0);
            }
            else {
                adj_lst[0].add(2);
                adj_lst[2].add(0);
                
                
                adj_lst[0].add(1);
                adj_lst[1].add(0);
            }
            Arrays.sort(arr);
            reverse();
            dp=new HashMap[n][k+1];
            for(int i=0;i<dp.length;i++) {
                for(int j=0;j<dp[0].length;j++) {
                    dp[i][j]=new HashMap<>();
                }
            }
            long ans=0;
            for(int i=0;i<Math.min(n, 3);i++) {
                ans=Math.max(ans,solve(i,k,-1));
            }
            fin.append(ans);
            fin.append("\n");
        }
        System.out.println(fin);
    }
    public static long solve(int root,int k,int prev) {
        if(k==0) {
            return 0;
        }
        if(dp[root][k].containsKey(prev)) {
            return dp[root][k].get(prev);
        }
        long ans=0;
        if(adj_lst[root].size()==1 && adj_lst[root].get(0)==prev) {
            ans=arr[root]+solve(root,k-1,-1);
            dp[root][k].put(prev, ans);
            return ans;
        } 
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==prev) {
                continue;
            }
            ans=Math.max(ans,arr[root]+solve(adj_lst[root].get(i),k-1,root));
        }
        dp[root][k].put(prev, ans);
        return ans;
    }
    public static void reverse() {
        long tmp[]=new long[arr.length];
        for(int i=arr.length-1,j=0;i>=0;i--,j++) {
            tmp[i]=arr[j];
        }
        arr=tmp;
    }
}
