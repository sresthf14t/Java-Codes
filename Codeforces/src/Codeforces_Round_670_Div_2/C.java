/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_670_Div_2;

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
    static ArrayList<Integer> adj_lst[];
    static int n,parent[],subtree[],node1,node1_par;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int test=input.scanInt();
        StringBuilder ans=new StringBuilder("");
        for(int tt=1;tt<=test;tt++) {
            n=input.scanInt();
            adj_lst=new ArrayList[n];
            parent=new int[n];
            subtree=new int[n];
            for(int i=0;i<n;i++) {
                adj_lst[i]=new ArrayList<>();
            }
            int u=-1,v=-1;
            for(int i=0;i<n-1;i++) {
                u=input.scanInt()-1;
                v=input.scanInt()-1;
                adj_lst[u].add(v);
                adj_lst[v].add(u);
            }
            DFS(0,-1);
            int min=Integer.MAX_VALUE,min_cnt=0;
            for(int i=0;i<n;i++) {
//                System.out.println((i+1)+" "+subtree[i]);
                if(subtree[i]<min) {
                    min=subtree[i];
                    min_cnt=1;
                }
                else if(subtree[i]==min) {
                    min_cnt++;
                }
            }
            
            if(min_cnt==1) {
                ans.append((u+1)+" "+(v+1)+"\n");
                ans.append((u+1)+" "+(v+1)+"\n");
                continue;
            }
            int indx1=-1,indx2=-1;
            for(int i=0;i<n;i++) {
                if(subtree[i]==min && indx1==-1) {
                    indx1=i;
                }
                else if(subtree[i]==min) {
                    indx2=i;
                    break;
                }
            }
//            System.out.println(min_cnt+" "+(indx1+1)+" "+(indx2+1));
            node1=-1;
            node1_par=-1;
            find(indx1,indx2);
            ans.append((node1+1)+" "+(node1_par+1)+"\n");
            ans.append((indx2+1)+" "+(node1+1)+"\n");
        }
        System.out.println(ans);
    }
    public static int DFS(int root,int par) {
        parent[root]=par;
        int cnt=0,max=0;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==par) {
                continue;
            }
            int tmp=DFS(adj_lst[root].get(i),root);
            cnt+=tmp;
            max=Math.max(max,tmp);
        }
        max=Math.max(max, n-cnt-1);
        subtree[root]=max;
        return cnt+1;
    }
    public static int find(int root,int par) {
        int cnt=0;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==par) {
                continue;
            }
            int tmp=find(adj_lst[root].get(i),root);
            cnt+=tmp;
        }
        if(cnt==0) {
            node1=root;
            node1_par=par;
        }
        return cnt+1;
    }
}
