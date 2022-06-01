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
public class B_Edge_Weight_Assignment {
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
    static int ans_min,ans_max;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int n=input.scanInt();
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
        ans_min=1;
        ans_max=adj_lst.length-1;
        int root=-1;
        for(int i=0;i<n;i++) {
            if(adj_lst[i].size()>1) {
                root=i;
                break;
            }
        }
        DFS(root,-1);
        count(root,-1);
        System.out.println(ans_min+" "+ans_max);
    }
    public static int[] DFS(int root,int par) { //{odd,even}
        if(adj_lst[root].size()==1) {
            return new int[]{0,1};
        }
        int even=0,odd=0;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)!=par) {
                int tmp[]=DFS(adj_lst[root].get(i),root);
                even+=tmp[0];
                odd+=tmp[1];
            }
        }
        if(even>=1 && odd>=1) {
            ans_min=Math.max(ans_min,3);
        }
        return new int[]{odd,even};
    } 
    public static int count(int root,int par) {
        if(adj_lst[root].size()==1) {
            return 1;
        }
        int cnt=0;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)!=par) {
                cnt+=count(adj_lst[root].get(i),root);
            }
        }
        if(cnt>1) {
            ans_max-=(cnt-1);
        }
        return 0;
    }
}
