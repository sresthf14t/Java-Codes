/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_605_Div_3_Virtual;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class E {
    
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
    static int arr[],dist[],same_dist[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int n=input.scanInt();
        arr=new int[n];
        dist=new int[n];
        same_dist=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
        }
        //No of nodes
        adj_lst=new ArrayList[n];
        vis=new boolean[n];
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<Integer>();
        }
        //No of edges
        for(int i=0;i<n;i++) {
            if(i-arr[i]>=0) {
                adj_lst[i].add(i-arr[i]);
            }
            if(i+arr[i]<n) {
                adj_lst[i].add(i+arr[i]);
            }
        }
//        for(int i=0;i<adj_lst.length;i++) {
//            System.out.print(i+"->");
//            for(int j=0;j<adj_lst[i].size();j++) {
//                System.out.print(adj_lst[i].get(j)+" ");
//            }
//            System.out.println();
//        }
        StringBuilder ans=new StringBuilder("");
        for(int i=0;i<n;i++) {
            dist[i]=BFS(i);
            ans.append(dist[i]+" ");
        }
        ans.append("\n");
        System.out.println(ans);
    }
    
    static int BFS(int source) {
        int min=same_dist[source]=Integer.MAX_VALUE;
        boolean vis[]=new boolean[adj_lst.length];
        int depth[]=new int[adj_lst.length];
        Queue<Integer> que = new LinkedList<>();
        que.add(source);
        depth[source]=0;
        vis[source]=true;
        while(!que.isEmpty()) {
            for(int i=0;i<adj_lst[que.peek()].size();i++) {
                if(!vis[adj_lst[que.peek()].get(i)]) {
                    if(arr[adj_lst[que.peek()].get(i)]%2==arr[source]%2 && dist[adj_lst[que.peek()].get(i)]>0) {
                        min=Math.min(min, dist[adj_lst[que.peek()].get(i)]+depth[que.peek()]+1);
                        continue;
                    }
                    else if(arr[adj_lst[que.peek()].get(i)]%2!=arr[source]%2 && same_dist[adj_lst[que.peek()].get(i)]!=Integer.MAX_VALUE) {
                        min=Math.min(min, same_dist[adj_lst[que.peek()].get(i)]+depth[que.peek()]+1);
                        continue;
                    }
                    que.add(adj_lst[que.peek()].get(i));
                    vis[adj_lst[que.peek()].get(i)]=true;
                    depth[adj_lst[que.peek()].get(i)]=depth[que.peek()]+1;
                    if(arr[source]%2==arr[adj_lst[que.peek()].get(i)]%2) {
                        same_dist[source]=Math.min(same_dist[source], depth[adj_lst[que.peek()].get(i)]);
                    }
                    if(arr[source]%2!=arr[adj_lst[que.peek()].get(i)]%2) {
                        return Math.min(min,depth[adj_lst[que.peek()].get(i)]);
                    }
                }
            }
            que.poll();
        }
        if(min==Integer.MAX_VALUE) {
            return -1;
        }
        return min;
    }
}
