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
public class B_Shortest_Cycle {
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
    static long arr[];
    static int n;
    static ArrayList<Integer> adj_lst[];
    public static void main(String args[]) throws IOException {
        Scanner input=new Scanner(System.in);
        n=input.nextInt();
        arr=new long[n];
        long tmp[]=new long[n];
        int count=0;
        for(int i=0;i<n;i++) {
            tmp[i]=input.nextLong();
            if(tmp[i]!=0) {
                count++;
            }
        }
        arr=new long[count];
        for(int i=0,indx=0;i<n;i++) {
            if(tmp[i]==0) {
                continue;
            }
            arr[indx]=tmp[i];
            indx++;
        }
        n=count;
        long pow[]=new long[63];
        pow[0]=1;
        for(int i=1;i<pow.length;i++) {
            pow[i]=2*pow[i-1];
//            System.out.println(pow[i]);
        }
        int cnt[]=new int[pow.length];
        for(int i=0;i<n;i++) {
            for(int j=0;j<pow.length;j++) {
                if((arr[i]&pow[j])!=0) {
                    cnt[j]++;
                }
            }
        }
        for(int i=0;i<pow.length;i++) {
            if(cnt[i]>2) {
                System.out.println(3);
                return;
            }
        }
        adj_lst=new ArrayList[n];
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<>();
            if(arr[i]==0) {
                continue;
            }
            for(int j=0;j<n;j++) {
                if(arr[j]==0 || i==j) {
                    continue;
                }
                if((arr[i]&arr[j])!=0) {
                    adj_lst[i].add(j);
                }
            }
        }
        int min=Integer.MAX_VALUE;
        for(int i=0;i<n;i++) {
            min=Math.min(min,BFS(i));
        }
        if(min==Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(min);
    }
    static int BFS(int source) {
        int root=source;
        boolean vis[]=new boolean[n];
        int depth[]=new int[n];
        Queue<Integer> que = new LinkedList<>();
        int par[]=new int[n];
        Arrays.fill(par, -1);
        que.add(root);
        depth[root]=0;
        vis[root]=true;
        while(!que.isEmpty()) {
            for(int i=0;i<adj_lst[que.peek()].size();i++) {
                if(!vis[adj_lst[que.peek()].get(i)]) {
                    que.add(adj_lst[que.peek()].get(i));
                    par[adj_lst[que.peek()].get(i)]=que.peek();
                    vis[adj_lst[que.peek()].get(i)]=true;
                    depth[adj_lst[que.peek()].get(i)]=depth[que.peek()]+1;
                    continue;
                }
                if(par[que.peek()] != adj_lst[que.peek()].get(i) && par[adj_lst[que.peek()].get(i)] != que.peek()) {
                    return depth[que.peek()]+depth[adj_lst[que.peek()].get(i)]+1;
                }
            }
            que.poll();
        }
        return Integer.MAX_VALUE;
    }
}
