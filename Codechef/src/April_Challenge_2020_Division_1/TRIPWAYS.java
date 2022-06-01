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
import java.util.*;
import java.io.*;
public class TRIPWAYS {
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

    static ArrayList<Integer> adj_lst[],paths[],ways[];
    static boolean vis[];
    static int paths_indx,ways_indx;
    public static void main(String args[]) throws IOException{
        
//        ways=new ArrayList[1000000];
//        ways_indx=0;
//        n_as_sum_of_m_nos(0,10,0,2000,new ArrayList<Integer>());
//        for(int i=0;i<ways_indx;i++) {
//            for(int j=0;j<ways[i].size();j++) {
//                System.out.print(ways[i].get(j)+" ");
//            }
//            System.out.println();
//        }
//        System.exit(0);
        
        Scan input=new Scan();
        //No of nodes
        int n=input.scanInt();
        int m=input.scanInt();
        int query=input.scanInt();
        adj_lst=new ArrayList[n];
        paths=new ArrayList[n];
        paths_indx=0;
        vis=new boolean[n];
        int[] path_obj=new int[n];
        for(int i=0;i<n;i++) {
            path_obj[i]=input.scanInt();
            adj_lst[i]=new ArrayList<Integer>();
        }
        //No of edges
        for(int i=1;i<=m;i++) {
            // input u & v
            int u=input.scanInt();
            int v=input.scanInt();
            u--;
            v--;
            adj_lst[u].add(v);
            adj_lst[v].add(u);
        }
//        for(int i=0;i<adj_lst.length;i++) {
//            System.out.print(i+"->");
//            for(int j=0;j<adj_lst[i].size();j++) {
//                System.out.print(adj_lst[i].get(j)+" ");
//            }
//            System.out.println();
//        }
        path_DFS(0, new ArrayList<Integer>());
//        System.out.println();
//        for(int i=0;i<paths_indx;i++) {
//            for(int j=0;j<paths[i].size();j++) {
//                System.out.print((paths[i].get(j)+1)+" ");
//            }
//            System.out.println();
//        }
//        System.out.println();
        
        long mod=1000000007L;
        for(int q=1;q<=query;q++) {
            long days=input.scanInt();
            long sum=0;
            for(int i=0;i<paths_indx;i++) {
                sum+=calculate_ways(paths[i],days,path_obj);
                sum%=mod;
            }
            System.out.println(sum);
        }
    }
    
    public static void path_DFS(int root,ArrayList<Integer> path) {
//        System.out.println(root);
        path.add(root);
        if(root==adj_lst.length-1) {
            paths[paths_indx]=new ArrayList<>(path);
            path.remove(path.size()-1);
            paths_indx++;
            return;
        }
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)>root) {
                path_DFS(adj_lst[root].get(i),path);
            }
        }
        path.remove(path.size()-1);
    }
    public static void n_as_sum_of_m_nos(long sum,long n,long depth,long m,ArrayList<Integer> no_ways) {
        if(depth==m && sum==n) {
            ways[ways_indx]=new ArrayList<>(no_ways);
            ways_indx++;
            return;
        }
        else if(depth==m) {
            return;
        }
        for(int i=0;i<=n-sum;i++) {
            no_ways.add(i);
            sum+=i;
            n_as_sum_of_m_nos(sum,n,depth+1,m,no_ways);
            no_ways.remove(no_ways.size()-1);
            sum-=i;
        }
    }
    
    public static long calculate_ways(ArrayList<Integer> path,long days,int[] path_obj) {
        long mod=1000000007L;
        ways=new ArrayList[10000000];
        ways_indx=0;
        n_as_sum_of_m_nos(0,days-path.size()+1,0,path.size(),new ArrayList<Integer>());
        
//        System.out.println("--------------------WAYS---------------------");
//        for(int i=0;i<ways_indx;i++) {
//            for(int j=0;j<ways[i].size();j++) {
//                System.out.print(ways[i].get(j)+" ");
//            }
//            System.out.println();
//        }
        
        long sum=0;
        for(int i=0;i<ways_indx;i++) {
            long pro=1;
            for(int j=0;j<ways[i].size();j++) {
                pro*=(long)Math.pow(path_obj[path.get(j)],ways[i].get(j));
            }
            sum+=pro;
            sum%=mod;
        }
        return sum;
    }
}
