/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_659_Div_2;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class B1 {
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
    static int n,k,l,arr[],dep[],dp[][][];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int test=input.scanInt();
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            n=input.scanInt();
            k=input.scanInt();
            l=input.scanInt();
            arr=new int[n+2];
            for(int i=1;i<=n;i++) {
                arr[i]=input.scanInt();
            }
            arr[0]=Integer.MIN_VALUE;
            arr[n+1]=Integer.MIN_VALUE;
            dep=new int[2*k];
            for(int i=0;i<=k;i++) {
                dep[i]=i;
            }
            for(int i=k+1;i<2*k;i++) {
                dep[i]=dep[i-1]-1;
            }
            dp=new int[n+2][2*k+1][2*k+1];
            for(int i=0;i<dp.length;i++) {
                for(int j=0;j<dp[0].length;j++) {
                    for(int k=0;k<dp[0][0].length;k++) {
                        dp[i][j][k]=-1;
                    }
                }
            }
            if(solve(0,0,0)) {
                ans.append("Yes\n");
            }
            else {
                ans.append("No\n");
            }
        }
        System.out.println(ans);
    }
    public static boolean solve(int indx,int time,int wait) {
//        System.out.println(wait);
        if(indx==n+1) {
            return true;
        }
        if(dp[indx][time][wait]!=-1) {
            return dp[indx][time][wait]==0?false:true;
        }
        if(wait<2*k && arr[indx]+dep[(time+1)%(2*k)]<=l) {
            if(solve(indx,(time+1)%(2*k),wait+1)) {
                dp[indx][time][wait]=1;
                return true;
            }
        }
        if(arr[indx+1]+dep[(time+1)%(2*k)]<=l) {
            if(solve(indx+1,(time+1)%(2*k),0)) {
                dp[indx][time][wait]=1;
                return true;
            }
        }
        dp[indx][time][wait]=0;
        return false;
            
    }
}
