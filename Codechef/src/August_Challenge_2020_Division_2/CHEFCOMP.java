/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package August_Challenge_2020_Division_2;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class CHEFCOMP {
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
    static int arr[],people[],apple[],days[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        int test=input.scanInt();
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            adj_lst=new ArrayList[n];
            vis=new boolean[n];
            for(int i=0;i<n;i++) {
                adj_lst[i]=new ArrayList<Integer>();
            }
            //No of edges
            for(int i=0;i<n-1;i++) {
                // input u & v
                int u=input.scanInt()-1;
                int v=input.scanInt()-1;
                adj_lst[u].add(v);
                adj_lst[v].add(u);
            }
            arr=new int[n];
            people=new int[n];
            apple=new int[n];
            days=new int[n];
            Arrays.fill(days, -1);
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt()-1;
            }
            for(int i=0;i<n;i++) {
                people[i]=input.scanInt();
            }
            for(int i=0;i<n;i++) {
                apple[i]=input.scanInt();
            }
            for(int i=0;i<n;i++) {
                vis=new boolean[n];
                DFS(arr[i],people[arr[i]],i+1);
                for(int j=0;j<adj_lst[arr[i]].size();j++) {
                    adj_lst[adj_lst[arr[i]].get(j)].remove(new Integer(arr[i]));
                }
                adj_lst[arr[i]]=new ArrayList<>();
            }
            for(int i=0;i<n;i++) {
                ans.append(days[i]+" ");
            }
            ans.append("\n");
        }
        System.out.println(ans);
    }
    public static void DFS(int root,int sub,int day) {
//        System.out.println(root);
        if(apple[root]>0) {
            apple[root]-=sub;
            apple[root]=Math.max(0,apple[root]);
            if(apple[root]==0) {
                days[root]=day;
            }
        }
        
        vis[root]=true;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(!vis[adj_lst[root].get(i)]) {
                DFS(adj_lst[root].get(i),sub,day);
            }
        }
    }
}
