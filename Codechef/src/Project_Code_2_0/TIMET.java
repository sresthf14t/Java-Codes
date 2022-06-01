/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_Code_2_0;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class TIMET {
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
    static int n,arr[][];
    static boolean vis[];
    static ArrayList<Integer> strt,end;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int test=input.scanInt();
        for(int t=1;t<=test;t++) {
            n=input.scanInt();
            arr=new int[n][n];
            vis=new boolean[n];
            for(int i=0;i<n;i++) {
                for(int j=0;j<n;j++) {
                    arr[i][j]=input.scanInt();
                }
            }
            boolean is_pos=false;
            for(int i=0;i<n;i++) {
                vis=new boolean[n];
                if(DFS(i,0,i)) {
                    is_pos=true;
                    break;
                }
            }
            if(is_pos) {
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
            }
        }
    }
    public static boolean DFS(int root,int sum,int target) {
        vis[root]=true;
        for(int i=0;i<n;i++) {
            if(!vis[i]) {
                if(sum+arr[root][i]<0 && DFS(i,sum+arr[root][i],target)) {
                    return true;
                }
            }
            if(i==target && sum+arr[root][i]<0) {
                return true;
            }
        }
        vis[root]=false;
        return false;
    }
}
