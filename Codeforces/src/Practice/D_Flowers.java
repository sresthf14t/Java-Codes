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
public class D_Flowers {
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
    static long dp[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int q=input.scanInt();
        int k=input.scanInt();
        int[][] query=new int[2][q];
        int max=-1;
        long mod=1000000007L;
        for(int i=0;i<q;i++) {
            int a=input.scanInt();
            int b=input.scanInt();
            query[0][i]=a;
            query[1][i]=b;
            max=Math.max(max,b);
        }
        dp=new long[max+1];
        for(int i=0;i<dp.length;i++) {
            dp[i]=-1;
        }
        ways(max,k,mod);
        long sum_arr[]=new long[max+1];
        sum_arr[0]=dp[0];
        for(int i=1;i<sum_arr.length;i++) {
            sum_arr[i]=(sum_arr[i-1]+dp[i])%mod;
        }
        StringBuilder ans=new StringBuilder("");
        for(int i=0;i<q;i++) {
            long sum=0;
            if(query[0][i]==0) {
                ans.append(sum_arr[query[1][i]]);
            }
            else {
                long tmp=sum_arr[query[1][i]]-sum_arr[query[0][i]-1];
                if(tmp<0) {
                    tmp+=mod;
                }
                ans.append(tmp+"\n");
            }
        }
        System.out.println(ans);
    }
    public static long ways(int n,int k,long mod) {
        if(n==0) {
            return 1;
        }
        if(n>=k) {
            if(dp[n-1]==-1) {
                dp[n-1]=ways(n-1,k,mod);
            }
            if(dp[n-k]==-1) {
                dp[n-k]=ways(n-k,k,mod);
            }
            dp[n]=(dp[n-1]+dp[n-k])%mod;
            return dp[n];
        }
        if(dp[n-1]==-1) {
            dp[n-1]=ways(n-1,k,mod);
        }
        dp[n]=dp[n-1]%mod;
        return dp[n];
    }
}
