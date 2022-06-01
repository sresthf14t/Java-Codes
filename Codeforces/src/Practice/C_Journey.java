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
import java.io.*;
import java.util.*;
import java.io.DataInputStream; 
import java.io.FileInputStream; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.util.Scanner; 
import java.util.StringTokenizer;
public class C_Journey {
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
    static ArrayList<Integer> adj_lst[],tym[],vis_place;
    static boolean vis[];
    static int next[];
    static long dp[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        //No of nodes
        int n=input.scanInt();
        int m=input.scanInt();
        int time=input.scanInt();
        adj_lst=new ArrayList[n];
        tym=new ArrayList[n];
        vis=new boolean[n];
        next=new int[n];
        dp=new long[n];
        vis_place=new ArrayList<>();
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<Integer>();
            tym[i]=new ArrayList<Integer>();
            next[i]=-1;
            dp[i]=-19999999999999999L;
        }
        //No of edges
        for(int i=0;i<m;i++) {
            // input u & v
            int u=input.scanInt();
            int v=input.scanInt();
            int t=input.scanInt();
            u--;
            v--;
            adj_lst[u].add(v);
            tym[u].add(t);
        }

        longest_path_DAG(0,n-1,0,time);
        System.out.println(dp[0]);
        int root=0;
        while(root!=-1) {
            System.out.print((root+1)+" ");
            root=next[root];
        }
        System.out.println();
        for(int i=0;i<n;i++) {
            System.out.println((i+1)+".)"+dp[i]);
        }
//        System.out.print(vis_place.size()+"\n");
//        for(int i=0;i<vis_place.size();i++) {
//            System.out.print((vis_place.get(i)+1)+" ");
//        }
        System.out.println();
    }
    
//    public static void mod_DFS(int root,int time_taken,int time_lim,ArrayList<Integer> visited_places) {
//        if(time_taken>time_lim) {
//            return;
//        }
////        System.out.println(root+" "+time_taken);
//        visited_places.add(root);
//        if(root==adj_lst.length-1) {
//            if(time_taken<=time_lim && visited_places.size()>vis_place.size()) {
//                vis_place=new ArrayList<>(visited_places);
//            }
//            visited_places.remove(visited_places.size()-1);
//            return;
//        }
//        for(int i=0;i<adj_lst[root].size();i++) {
//            mod_DFS(adj_lst[root].get(i),time_taken+tym[root].get(i),time_lim,visited_places);
//        }
//        visited_places.remove(visited_places.size()-1);
//    }
    
    public static long longest_path_DAG(int root,int target,long curr_time,long time_lim) {
        if(dp[root]>0) {
            return dp[root];
        }
        if(root==target) {
//            System.out.println("time="+curr_time);
            return 1;
        }
        for(int i=0;i<adj_lst[root].size();i++) {
            if(curr_time+tym[root].get(i)>time_lim) {
                continue;
            }
            if(dp[adj_lst[root].get(i)]<0) {
                dp[adj_lst[root].get(i)]=longest_path_DAG(adj_lst[root].get(i),target,curr_time+tym[root].get(i),time_lim);
            }
            if(1+dp[adj_lst[root].get(i)]>dp[root]) {
                dp[root]=1+dp[adj_lst[root].get(i)];
                next[root]=adj_lst[root].get(i);
            }
        }
        return dp[root];
    }
}
