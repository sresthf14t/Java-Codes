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
public class B_Labyrinth {
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
    static int n,m,x,y,r,c,dist[][];
    static char arr[][];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n=input.scanInt();
        m=input.scanInt();
        r=input.scanInt()-1;
        c=input.scanInt()-1;
        x=input.scanInt();
        y=input.scanInt();
        arr=new char[n][m];
        for(int i=0;i<n;i++) {
            String str=input.scanString();
            for(int j=0;j<m;j++) {
                arr[i][j]=str.charAt(j);
            }
        }
        BFS(r,c);
        int distr[][]=new int[n][m];
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                distr[i][j]=(j-c)+dist[i][j];
            }
        }
        int cnt=0;
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(dist[i][j]<=x && distr[i][j]<=y) {
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }
    static void BFS(int i,int j) {
        dist=new int[n][m];
        for(int i1=0;i1<n;i1++) {
            for(int j1=0;j1<m;j1++) {
                dist[i1][j1]=Integer.MAX_VALUE/2;
            }
        }
        dist[i][j]=0;
        Deque<Integer> quex = new ArrayDeque<Integer>();
        Deque<Integer> quey = new ArrayDeque<Integer>();
        quex.add(i);
        quey.add(j);
        while(!quex.isEmpty()) {
            i=quex.poll();
            j=quey.poll();
            if(i>0 && arr[i-1][j]!='*' && dist[i-1][j]>dist[i][j]) {
                dist[i-1][j]=dist[i][j];
                quex.addFirst(i-1);
                quey.addFirst(j);
            }
            if(i<n-1 &&  arr[i+1][j]!='*' && dist[i+1][j]>dist[i][j]) {
                dist[i+1][j]=dist[i][j];
                quex.addFirst(i+1);
                quey.addFirst(j);
            }
            if(j>0 &&  arr[i][j-1]!='*' && dist[i][j-1]>dist[i][j]+1) {
                dist[i][j-1]=dist[i][j]+1;
                quex.addLast(i);
                quey.addLast(j-1);
            }
            if(j<m-1 &&  arr[i][j+1]!='*' && dist[i][j+1]>dist[i][j]) {
                dist[i][j+1]=dist[i][j];
                quex.addFirst(i);
                quey.addFirst(j+1);
            }
        }
    }
}
