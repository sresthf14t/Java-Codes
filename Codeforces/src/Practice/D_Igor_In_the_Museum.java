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
public class D_Igor_In_the_Museum {
    
    
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
    
    
    static ArrayList<Integer> adj_lst[],nodes_trav;
    static boolean vis[];
    static int sum,ans[];
    public static void main(String args[]) throws IOException {
//        Scanner input=new Scanner(System.in);
        Scan input=new Scan();
        //No of nodes
        int n=input.scanInt();
        int m=input.scanInt();
        int k=input.scanInt();
        char arr[][]=new char[n][m];
        ans=new int[n*m];
        adj_lst=new ArrayList[n*m];
        vis=new boolean[adj_lst.length];
        nodes_trav=new ArrayList<>();
        StringBuilder ans_str=new StringBuilder("");
        for(int i=0;i<adj_lst.length;i++) {
            adj_lst[i]=new ArrayList<Integer>();
            ans[i]=-1;
        }
        //No of edges
        for(int i=0;i<n;i++) {
            String str=input.scanString();
            for(int j=0;j<m;j++) {
                arr[i][j]=str.charAt(j);
            }
         }
        
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(arr[i][j]=='.') {
                    if(i-1>=0 && arr[i-1][j]=='.') {
                        adj_lst[(i*m)+j].add(((i-1)*m)+j);
                    }
                    if(i+1<n && arr[i+1][j]=='.') {
                        adj_lst[(i*m)+j].add(((i+1)*m)+j);
                    }
                    if(j-1>=0 && arr[i][j-1]=='.') {
                        adj_lst[(i*m)+j].add((i*m)+(j-1));
                    }
                    if(j+1<m && arr[i][j+1]=='.') {
                        adj_lst[(i*m)+j].add((i*m)+(j+1));
                    }
                }
            }
         }
//        for(int i=0;i<adj_lst.length;i++) {
//            System.out.print(i+"->");
//            for(int j=0;j<adj_lst[i].size();j++) {
//                System.out.print(adj_lst[i].get(j)+" ");
//            }
//            System.out.println();
//        }
//        for(int i=0;i<adj_lst.length;i++) {
//            if(vis[i] || adj_lst[i].size()==0) {
//                continue;
//            }
//            if(ans[i]==-1) {
//                sum=0;
//                mod_DFS(i);
//                for()
//            }
//        }
        for(int i=0;i<k;i++) {
            int a=input.scanInt();
            int b=input.scanInt();
            a--;
            b--;
            int root=(a*m)+b;
            if(ans[root]!=-1) {
                ans_str.append(ans[root]+"\n");
                continue;
            }
            sum=0;
            mod_BFS(root);
            while(nodes_trav.size()!=0) {
                ans[nodes_trav.get(0)]=sum;
                nodes_trav.remove(0);
            }
            ans_str.append(sum+"\n");
        }
        System.out.println(ans_str);
    }
    
    public static void mod_DFS(int root) {
        nodes_trav.add(root);
        vis[root]=true;
        sum+=4-adj_lst[root].size();
        for(int i=0;i<adj_lst[root].size();i++) {
            if(!vis[adj_lst[root].get(i)]) {
                mod_DFS(adj_lst[root].get(i));
            }
        }
    }
    
    static void mod_BFS(int root) {
        Queue<Integer> que = new LinkedList<>();
        que.add(root);
        nodes_trav.add(root);
        vis[root]=true;
        sum+=4-adj_lst[root].size();
        while(!que.isEmpty()) {
            for(int i=0;i<adj_lst[que.peek()].size();i++) {
                if(!vis[adj_lst[que.peek()].get(i)]) {
                    que.add(adj_lst[que.peek()].get(i));
                    vis[adj_lst[que.peek()].get(i)]=true;
                    sum+=4-adj_lst[adj_lst[que.peek()].get(i)].size();
                    nodes_trav.add(adj_lst[que.peek()].get(i));
                }
            }
            que.poll();
        }
    }
}
