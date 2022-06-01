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
public class D_Complete_Tripartite {
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
    static int clr[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        //No of nodes
        int n=input.scanInt();
        int m=input.scanInt();
        adj_lst=new ArrayList[n];
        vis=new boolean[n];
        clr=new int[n];
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<Integer>();
        }
        //No of edges
        for(int i=0;i<m;i++) {
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
        int color=coloring();
        int one=0,two=0,three=0;
        boolean is_pos=true;
        for(int i=0;i<n;i++) {
            if(clr[i]==1) {
                one++;
            }
            if(clr[i]==2) {
                two++;
            }
            if(clr[i]==3) {
                three++;
            }
        }
        for(int i=0;i<n;i++) {
            if(clr[i]==1) {
                if(adj_lst[i].size()!=two+three) {
                    is_pos=false;
                    break;
                }
            }
            if(clr[i]==2) {
                if(adj_lst[i].size()!=one+three) {
                    is_pos=false;
                    break;
                }
            }
            if(clr[i]==3) {
                if(adj_lst[i].size()!=one+two) {
                    is_pos=false;
                    break;
                }
            }
        }
        if(color==-1 || (one==0 || two==0 || three==0) || (!is_pos)) {
            System.out.println(-1);
        }
        else {
            for(int i=0;i<n;i++) {
                System.out.print(clr[i]+" ");
            }
            System.out.println();
        }
    }
    
    public static int coloring() {
        boolean one,two,three;
        for(int i=0;i<adj_lst.length;i++) {
            one=two=three=false;
            for(int j=0;j<adj_lst[i].size();j++) {
                if(clr[adj_lst[i].get(j)]==1) {
                    one=true;
                }
                else if(clr[adj_lst[i].get(j)]==2) {
                    two=true;
                }
                else if(clr[adj_lst[i].get(j)]==3) {
                    three=true;
                }
            }
            if(!one) {
                clr[i]=1;
            }
            else if(!two) {
                clr[i]=2;
            }
            else if(!three) {
                clr[i]=3;
            }
            else {
                return -1;
            }
        }
        return 1;
    }
}
