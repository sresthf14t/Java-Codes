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
public class D_Caesar_s_Legions {
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
    static int n1,n2,k1,k2;
    static long dp[][][][],mod=100000000L;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n1=input.scanInt();
        n2=input.scanInt();
        k1=input.scanInt();
        k2=input.scanInt();
        dp=new long[101][101][11][11];
        for(int i=0;i<101;i++) {
            for(int j=0;j<101;j++) {
                for(int k=0;k<11;k++) {
                    for(int l=0;l<11;l++) {
                        dp[i][j][k][l]=-1L;
                    }
                }
            }
        }
        System.out.println(solve(n1,n2,0,0));
    }
    public static long solve(int indx1,int indx2,int cnt1,int cnt2) {
//        System.out.println(indx1+" "+indx2);
        if(indx1==0 && indx2==0) {
            return 1;
        }
        if(dp[indx1][indx2][cnt1][cnt2]!=-1) {
            return dp[indx1][indx2][cnt1][cnt2];
        }
        long ways=0;
        if(indx1>0 && cnt1<k1) {
            ways+=solve(indx1-1,indx2,cnt1+1,0);
        }
        if(indx2>0 && cnt2<k2) {
            ways+=solve(indx1,indx2-1,0,cnt2+1);
        }
        ways%=mod;
        dp[indx1][indx2][cnt1][cnt2]=ways;
        return ways;
    }
}
