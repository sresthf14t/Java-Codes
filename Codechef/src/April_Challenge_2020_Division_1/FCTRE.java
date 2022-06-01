/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package April_Challenge_2020_Division_1;

/**
 *
 * @author User
 */
import java.io.*; 
import java.util.*; 
public class FCTRE {
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
    static int cost[],pro_cost[];
    static boolean sieve[];
    static int primes[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        sieve=new boolean[1000001];
        primes=new int[78498];
        sieve(1000001);
        int test=input.scanInt();
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
             //No of nodes
            adj_lst=new ArrayList[n];
            vis=new boolean[n];
            cost=new int[n];
            pro_cost=new int[n];
            for(int i=0;i<n;i++) {
                adj_lst[i]=new ArrayList<Integer>();
            }
            //No of edges
            for(int i=0;i<n-1;i++) {
                // input u & v
                int u=input.scanInt();
                int v=input.scanInt();
                u--;
                v--;
                adj_lst[u].add(v);
                adj_lst[v].add(u);
            }
            for(int i=0;i<n;i++) {
                cost[i]=input.scanInt();
            }
//            for(int i=0;i<adj_lst.length;i++) {
//                System.out.print(i+"->");
//                for(int j=0;j<adj_lst[i].size();j++) {
//                    System.out.print(adj_lst[i].get(j)+" ");
//                }
//                System.out.println();
//            }
            int q=input.scanInt();
            for(int i=1;i<=q;i++) {
                vis=new boolean[n];
                int strt=input.scanInt();
                int end=input.scanInt();
                strt--;
                end--;
                mod_DFS(strt,end,new ArrayList<Integer>());
                System.out.println(find_n_factors());
            }
        }
    }
    public static void mod_DFS(int root,int target,ArrayList<Integer> path_cost) {
//        System.out.println(root);
        vis[root]=true;
        path_cost.add(cost[root]);
        if(root==target) {
            path=new ArrayList<>(path_cost);
        }
        for(int i=0;i<adj_lst[root].size();i++) {
            if(!vis[adj_lst[root].get(i)]) {
                mod_DFS(adj_lst[root].get(i),target,path_cost);
            }
        }
        path_cost.remove(path_cost.size()-1);
    }
    
    //false for prime number and true for composite number
    public static void sieve(int n) {
        int k=-1;
        for(int i=2;i<n;i++) {
            if(!sieve[i]) {
                k++;
                primes[k]=i;
                for(int j=2*i;j<n;j=j+i) {
                    sieve[j]=true;
                }
            }
        }
    }
    
    public static long find_n_factors() {
        long mod=1000000007L,product=1,count=0;
        int n_count[]=new int[primes.length];
        for(int i=0;i<path.size();i++) {
            int tmp=path.get(i);
            for(int j=0;j<primes.length;j++) {
                count=0;
                while(tmp%primes[j]==0) {
                    tmp/=primes[j];
                    count++;
                }
                n_count[j]+=count;
            }
        }
        for(int i=0;i<primes.length;i++) {
            product*=(n_count[i]+1);
            product%=mod;
        }
        return product;
    }
}
