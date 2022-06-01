/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_660_Div_2;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class C {
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
    static int n,m,people[],mood[],subtree[];
    static ArrayList<Integer> adj_lst[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int test=input.scanInt();
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            int m=input.scanInt();
            people=new int[n];
            mood=new int[n];
            for(int i=0;i<n;i++) {
                people[i]=input.scanInt();
            }
            for(int i=0;i<n;i++) {
                mood[i]=input.scanInt();
            }
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
            subtree=new int[n];
            DFS(0,-1);
//            for(int i=0;i<n;i++) {
//                System.out.println((i+1)+" "+subtree[i]);
//            }
            if(BFS()) {
                ans.append("YES\n");
            }
            else {
                ans.append("NO\n");
            }
        }
        System.out.println(ans);
    }
    public static int DFS(int root,int par) {
        int cnt=people[root];
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==par) {
                continue;
            }
            cnt+=DFS(adj_lst[root].get(i),root);
        }
        subtree[root]=cnt;
        return cnt;
    }
    static boolean BFS() {
        boolean vis[]=new boolean[adj_lst.length];
        Queue<Integer> que = new LinkedList<>();
        Queue<Integer> good = new LinkedList<>();
        Queue<Integer> bad = new LinkedList<>();
        que.add(0);
        vis[0]=true;
        int total=subtree[0];
        int req_g=(mood[0]+total)/2;
        int req_b=(total-mood[0])/2;
        if((mood[0]+total)%2==1 || (total-mood[0])%2==1) {
            return false;
        }
        if(req_g+req_b!=total) {
            return false;
        }
        if(req_g<0 || req_b<0) {
            return false;
        }
        good.add(req_g);
        bad.add(req_b);
        while(!que.isEmpty()) {
            int g=good.peek();
            int b=bad.peek();
            if((g-b)!=mood[que.peek()]) {
                return false;
            }
            for(int i=0;i<adj_lst[que.peek()].size();i++) {
                if(!vis[adj_lst[que.peek()].get(i)]) {
                    que.add(adj_lst[que.peek()].get(i));
                    vis[adj_lst[que.peek()].get(i)]=true;
                    int child=adj_lst[que.peek()].get(i);
                    total=subtree[child];
                    req_g=(mood[child]+total)/2;
                    req_b=(total-mood[child])/2;
//                    System.out.println((child+1)+" "+total+" "+mood[child]+" "+req_g+" "+req_b);
                    if((mood[child]+total)%2==1 || (total-mood[child])%2==1) {
                        return false;
                    }
                    if(req_g+req_b!=total) {
                        return false;
                    }
                    if(req_g<0 || req_b<0) {
                        return false;
                    }
                    good.add(req_g);
                    bad.add(req_b);
                    if(req_g>g) {
                        return false;
                    }
                    g-=req_g;
                    if(req_b>b) {
                        req_b-=b;
                        b=0;
                        if(g>=req_b) {
                            g-=req_b;
                        }
                        else {
                            return false;
                        }
                    }
                    else {
                        b-=req_b;
                    }
                }
            }
            if(g+b!=people[que.peek()]) {
                return false;
            }
            que.poll();
            good.poll();
            bad.poll();
        }
        return true;
    }
}
