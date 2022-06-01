/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dynamic_Programming;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class Array_Description {
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
    static int n,m,arr[];
    static long dp[][],mod;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n=input.scanInt();
        m=input.scanInt();
        arr=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
        }
        dp=new long[n][m+1];
        mod=1000000007L;
        for(int i=0;i<n;i++) {
            for(int j=0;j<=m;j++) {
                dp[i][j]=-1;
            }
        }
        for(int i=0;i<n-1;i++) {
            if(arr[i]==0 || arr[i+1]==0) {
                continue;
            }
            if(Math.abs(arr[i]-arr[i+1])>1) {
                System.out.println(0);
                return;
            }
        }
        System.out.println(solve(0,0));
    }
    public static long solve(int indx,int prev) {
        if(indx==n) {
            return 1;
        }
        if(dp[indx][prev]!=-1) {
            return dp[indx][prev];
        }
        if(arr[indx]!=0) {
            dp[indx][prev]=solve(indx+1,arr[indx])%mod;
            return dp[indx][prev];
        }
        long cnt=0;
        if(prev==0) {
            for(int i=1;i<=m;i++) {
                cnt+=solve(indx+1,i);
                cnt%=mod;
            }
        }
        else {
            if(prev-1>=1) {
                if(indx==n-1 || arr[indx+1]==0) {
                    cnt+=solve(indx+1,prev-1);
                }
                else if(Math.abs(prev-1-arr[indx+1])<=1) {
                    cnt+=solve(indx+1,prev-1);
                }
            }
            if(indx==n-1 || arr[indx+1]==0) {
                cnt+=solve(indx+1,prev);
            }
            else if(Math.abs(prev-arr[indx+1])<=1) {
                cnt+=solve(indx+1,prev);
            }
            if(prev+1<=m) {
                if(indx==n-1 || arr[indx+1]==0) {
                    cnt+=solve(indx+1,prev+1);
                }
                else if(Math.abs(prev+1-arr[indx+1])<=1) {
                    cnt+=solve(indx+1,prev+1);
                }
            }
        }
        cnt%=mod;
        dp[indx][prev]=cnt;
        return cnt;
    }
}
