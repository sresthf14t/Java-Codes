/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hack_the_Interview_V_Asia_Pacific;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class Rerouting {
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
    static int color[],curr_clr;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int n=input.scanInt();
        int arr[]=new int[n];
        color=new int[n];
        adj_lst=new ArrayList[n];
        vis=new boolean[n];
        boolean has_in[]=new boolean[n];
        Arrays.fill(color, -1);
        boolean all_cycles=true;
        for(int i=0;i<n;i++) {
            int tmp=input.scanInt()-1;
            adj_lst[i]=new ArrayList<Integer>();
            if(i!=tmp) {
               adj_lst[i].add(tmp); 
               has_in[tmp]=true;
            }
            else {
                all_cycles=false;
            }
        }
        int clr=1;
        for(int i=0;i<n;i++) {
            if(!has_in[i]) {
                ArrayList<Integer> arrli=new ArrayList<>();
                curr_clr=-1;
                DFS(i,clr,arrli);
                if(curr_clr!=-1) {
                    for(int j=0;j<arrli.size();j++) {
                        color[arrli.get(j)]=curr_clr;
                    }
                }
                clr++;
            }
        }
        for(int i=0;i<n;i++) {
            if(!vis[i]) {
                ArrayList<Integer> arrli=new ArrayList<>();
                curr_clr=-1;
                DFS(i,clr,arrli);
                if(curr_clr!=-1) {
                    for(int j=0;j<arrli.size();j++) {
                        color[arrli.get(j)]=curr_clr;
                    }
                }
            }
        }
        Set<Integer> hashset=new HashSet<>();
        for(int i=0;i<n;i++) {
            if(!hashset.contains(color[i])) {
                hashset.add(color[i]);
            }
        }
        if(all_cycles) {
            System.out.println(hashset.size());
        }
        else {
            System.out.println(hashset.size()-1);
        }
        
    }
    public static void DFS(int root,int clr,ArrayList<Integer> arrli) {
//        System.out.println(root);
        arrli.add(root);
        vis[root]=true;
        color[root]=clr;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(!vis[adj_lst[root].get(i)]) {
                DFS(adj_lst[root].get(i),clr,arrli);
            }
            else {
                curr_clr=color[adj_lst[root].get(i)];
            }
        }
    }
}
