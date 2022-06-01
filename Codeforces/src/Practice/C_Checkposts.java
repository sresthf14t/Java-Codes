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
public class C_Checkposts {
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
    
    
    static ArrayList<Integer> adj_lst[],adjT_lst[],curr_vis;
    static boolean vis[];
    static Stack<Integer> stack;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        //No of nodes
        adj_lst=new ArrayList[input.scanInt()];
        adjT_lst=new ArrayList[adj_lst.length];
        vis=new boolean[adj_lst.length];
        long cost[]=new long[adj_lst.length];
        for(int i=0;i<cost.length;i++) {
            cost[i]=input.scanInt();
        }
        stack=new Stack<>();
        for(int i=0;i<adj_lst.length;i++) {
            adj_lst[i]=new ArrayList<Integer>();
            adjT_lst[i]=new ArrayList<Integer>();
        }
        //No of edges
        for(int i=input.scanInt();i>0;i--) {
            // input u & v
            int u=input.scanInt()-1;
            int v=input.scanInt()-1;
            adj_lst[u].add(v);
            adjT_lst[v].add(u);
        }
//        for(int i=0;i<adj_lst.length;i++) {
//            System.out.print(i+"->");
//            for(int j=0;j<adj_lst[i].size();j++) {
//                System.out.print(adj_lst[i].get(j)+" ");
//            }
//            System.out.println();
//        }
        
        
        //DFS
        for(int i=0;i<adj_lst.length;i++) {
            if(!vis[i]) {
                mod_DFS(i);
            }
        }
        
        //DFS of Transpose of graph
        int cnncted_cmp[]=new int[adj_lst.length];
        vis=new boolean[adj_lst.length];
        int clr=1;
        while(!stack.isEmpty()) {
            int i=stack.pop();
            if(!vis[i]) {
                curr_vis=new ArrayList<>();
                modT_DFS(i);
                for(int j=0;j<curr_vis.size();j++) {
                    cnncted_cmp[curr_vis.get(j)]=clr;
                }
                clr++;
            }
        }
        
        //All the Strongly Connected Components each clolor/number represents a Strongly Connected Component 
        ArrayList<Integer> color[]=new ArrayList[clr];
        for(int i=0;i<clr;i++) {
            color[i]=new ArrayList<>();
        }
        for(int i=0;i<cnncted_cmp.length;i++) {
            color[cnncted_cmp[i]].add(i);
        }
        long comb=1,cst=0,mod=1000000007L;
        for(int i=1;i<clr;i++) {
            long min=Integer.MAX_VALUE,min_cnt=0;
            for(int j=0;j<color[i].size();j++) {
                if(cost[color[i].get(j)]<min) {
                    min=cost[color[i].get(j)];
                    min_cnt=1;
                }
                else if(cost[color[i].get(j)]==min) {
                    min_cnt++;
                }
            }
            comb*=min_cnt;
            comb%=mod;
            cst+=min;
        }
        System.out.println(cst+" "+comb);
    }
    
    public static void mod_DFS(int root) {
//        System.out.println(root);
        vis[root]=true;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(!vis[adj_lst[root].get(i)]) {
                mod_DFS(adj_lst[root].get(i));
            }
        }
        stack.push(root);
    }
    
    public static void modT_DFS(int root) {
//        System.out.println(root);
        curr_vis.add(root);
        vis[root]=true;
        for(int i=0;i<adjT_lst[root].size();i++) {
            if(!vis[adjT_lst[root].get(i)]) {
                modT_DFS(adjT_lst[root].get(i));
            }
        }
    }
}
