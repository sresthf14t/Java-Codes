/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package May_Lunchtime_2020_Division_2;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class TREDIFF {
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
    static ArrayList<Integer> adj_lst[],path;
    static boolean vis[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int test=input.scanInt();
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            int q=input.scanInt();
            int arr[]=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt();
            }
            adj_lst=new ArrayList[n];
            for(int i=0;i<n;i++) {
                adj_lst[i]=new ArrayList<Integer>();
            }
            for(int i=0;i<n-1;i++) {
                // input u & v
                int u=input.scanInt()-1;
                int v=input.scanInt()-1;
                adj_lst[u].add(v);
                adj_lst[v].add(u);
            }
            for(int query=1;query<=q;query++) {
                int u=input.scanInt()-1;
                int v=input.scanInt()-1;
                vis=new boolean[n];
                DFS(u,v,new ArrayList<Integer>());
                ans.append(get_min(arr));
                ans.append("\n");
            }
        }
        System.out.println(ans);
    }
    public static int get_min(int arr[]) {
        int freq[]=new int[101];
        for(int i=0;i<path.size();i++) {
//            System.out.print(path.get(i)+" ");
            freq[arr[path.get(i)]]++;
        }
//        System.out.println();
        for(int i=0;i<101;i++) {
            if(freq[i]>1) {
                return 0;
            }
        }
        int min=Integer.MAX_VALUE;
        for(int i=0;i<101;i++) {
            if(freq[i]>0) {
                int cnt=1;
                i++;
                while(i<101 && freq[i]==0) {
                    i++;
                    cnt++;
                }
                if(i==101) {
                    break;
                }
                min=Math.min(min,cnt);
                i--;
            }
        }
        return min;
    }
    public static void DFS(int root,int target,ArrayList<Integer> arrli) {
//        System.out.println(root);
        vis[root]=true;
        arrli.add(root);
        if(root==target) {
            path=new ArrayList<>(arrli);
        }
        for(int i=0;i<adj_lst[root].size();i++) {
            if(!vis[adj_lst[root].get(i)]) {
                DFS(adj_lst[root].get(i),target,arrli);
            }
        }
        arrli.remove(arrli.size()-1);
    }
}
