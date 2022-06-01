/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_611_Div_3_Virtual;

/**
 *
 * @author User
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
    static boolean vis[];
    static int lst;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int test=1;
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            int arr[]=new int[n];
            adj_lst=new ArrayList[n];
            vis=new boolean[adj_lst.length];
            for(int i=0;i<adj_lst.length;i++) {
                adj_lst[i]=new ArrayList<Integer>();
            }
            boolean rec[]=new boolean[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt()-1;
                if(arr[i]!=-1) {
                    rec[arr[i]]=true;
                    adj_lst[i].add(arr[i]);
                }
            }
            ArrayList<Integer> strt=new ArrayList<>();
            ArrayList<Integer> end=new ArrayList<>();
            for(int i=0;i<n;i++) {
                if(!rec[i]) {
                    lst=-1;
                    DFS(i);
                    if(lst==-1) {
                        continue;
                    }
                    strt.add(i);
                    end.add(lst);
                }
            }
            for(int i=0;i<end.size()-1;i++) {
                adj_lst[end.get(i)].add(strt.get(i+1));
            }
            adj_lst[end.get(end.size()-1)].add(strt.get(0));
            StringBuilder ans=new StringBuilder("");
            for(int i=0;i<n;i++) {
                int indx=adj_lst[i].get(0);
                indx++;
                ans.append(indx+" ");
            }
            System.out.println(ans);
        }
    }
    public static void DFS(int root) {
//        System.out.println(root);
        lst=root;
        vis[root]=true;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(!vis[adj_lst[root].get(i)]) {
                DFS(adj_lst[root].get(i));
            }
        }
    }
}
