/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AtCoder_Beginner_Contest_178;

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
    static int n;
    static long dp[][][],mod;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n=input.scanInt();
        mod=1000000007L;
        long ans1=1,ans2=1,ans3=1;
        for(int i=0;i<n;i++) {
            ans1*=8;
            ans2*=10;
            ans3*=9;
            ans1%=mod;
            ans2%=mod;
            ans3%=mod;
        }
//        System.out.println(ans1+" "+ans2+" "+ans3);
        ans2-=ans1;
        ans3-=ans1;
        ans3*=2;
        ans2-=ans3;
        ans2%=mod;
        while(ans2<0) {
            ans2+=mod;
        }
        System.out.println(ans2);
//        dp=new long[n][2][2];
//        for(int i=0;i<n;i++) {
//            for(int j=0;j<2;j++) {
//                for(int k=0;k<2;k++) {
//                    dp[i][j][k]=-1;
//                }
//            }
//        }
//        System.out.println(solve(0,false,false));
    }
    public static long solve(int indx,boolean zero,boolean nine) {
        if(indx==n) {
            if(zero && nine) {
                return 1;
            }
            return 0;
        }
        if(dp[indx][zero?0:1][nine?0:1]!=-1) {
            return dp[indx][zero?0:1][nine?0:1];
        }
        long ans=0;
        ans+=solve(indx+1,true,nine);
        ans%=mod;
        ans+=solve(indx+1,zero,true);
        ans%=mod;
        ans+=8*solve(indx+1,zero,nine);
        ans%=mod;
        dp[indx][zero?0:1][nine?0:1]=ans;
        return ans;
    }
}
