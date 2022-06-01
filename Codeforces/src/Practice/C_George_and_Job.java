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
public class C_George_and_Job {
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
    static long prefix[],dp[][];
    static int n,m,k;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n=input.scanInt();
        m=input.scanInt();
        k=input.scanInt();
        long arr[]=new long[n];
        dp=new long[5001][5001];
        prefix=new long[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
        }
        prefix[0]=arr[0];
        for(int i=1;i<n;i++) {
            prefix[i]=prefix[i-1]+arr[i];
        }
        for(int i=0;i<5001;i++) {
            for(int j=0;j<5001;j++) {
                dp[i][j]=-1L;
            }
        }
        System.out.println(solve(0,0));
    }
    public static long solve(int indx,int strt) {
        if(indx==k) {
            return 0;
        }
        if(strt+m-1>=n) {
            return Integer.MIN_VALUE;
        }
        if(dp[indx][strt]!=-1) {
            return dp[indx][strt];
        }
        long max=0;
        max=Math.max(max,solve(indx,strt+1));
        max=Math.max(max,solve(indx+1,strt+m)+range_sum(strt,strt+m-1));
        dp[indx][strt]=max;
        return max;
    }
    public static long range_sum(int l,int r) {
        if(r==0 || l==0) {
            return prefix[r];
        }
        return prefix[r]-prefix[l-1];
    }
}
