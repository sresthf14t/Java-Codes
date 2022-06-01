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
public class B_Resort {
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
    static int arr[],dp[];
    static boolean obj[];
    static ArrayList<Integer> adj_lst[];
    static boolean vis[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int n=input.scanInt();
        obj=new boolean[n];
        arr=new int[n];
        for(int i=0;i<n;i++) {
            obj[i]=(input.scanInt()==1);
        }
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt()-1;
        }
        adj_lst=new ArrayList[n];
        vis=new boolean[n];
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<Integer>();
        }
        dp=new int[n];
        for(int i=0;i<n;i++) {
            dp[i]=-1;
            if(arr[i]==-1) {
                continue;
            }
            adj_lst[arr[i]].add(i);
        }
        int max=0;
        for(int i=0;i<n;i++) {
            if(dp[i]==-1) {
                DFS(i);
            }
        }
        int indx=-1;
        for(int i=0;i<n;i++) {
            if(dp[i]>max) {
                max=dp[i];
                indx=i;
            }
        }
        if(indx==-1) {
            boolean is_pos=false;
            for(int i=0;i<n;i++) {
                if(obj[i]) {
                    is_pos=true;
                    indx=i;
                    break;
                }
            }
            if(is_pos) {
                System.out.println(1+"\n"+(indx+1));
            }
            else {
                System.out.println(0);
            }
            System.exit(0);
        }
        ArrayList<Integer> path=new ArrayList<>();
        DFS_path(indx,path);
        System.out.println(max);
        for(int i=0;i<path.size();i++) {
            System.out.print((path.get(i)+1)+" ");
        }
        System.out.println();
    }
   public static int DFS(int root) {
//       System.out.println(root);
       
       if(dp[root]!=-1) {
           return dp[root];
       }
       if(obj[root]) {
           return 1;
       }
       if(adj_lst[root].size()!=1) {
           return Integer.MIN_VALUE;
       }
       if(vis[root]) {
           return Integer.MIN_VALUE;
       }
        vis[root]=true;
        int cnt=1+DFS(adj_lst[root].get(0));
        dp[root]=cnt;
        return cnt;
    }
    public static void DFS_path(int root,ArrayList<Integer> path) {
       if(obj[root]) {
           path.add(root);
           return;
       }
       path.add(root);
       DFS_path(adj_lst[root].get(0),path);
    }
}
