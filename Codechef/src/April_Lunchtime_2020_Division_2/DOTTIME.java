/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package April_Lunchtime_2020_Division_2;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class DOTTIME {
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
    
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int test=input.scanInt();
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            int m=input.scanInt();
            int q=input.scanInt();
            long arr[]=new long[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt();
            }
            int pos=input.scanInt()-1;
            long val=input.scanInt();
            arr[pos]=val;
            System.out.println();
            System.out.println(solve(n,m,arr));
        }
    }
    public static long solve(int n,int m,long arr[]) {
        long ans=0,mod=998244353L;
        for(int i=0;i+m<=n;i++) {
            for(int j=i;j+m<=n;j++) {
                for(int k=0;k<m;k++) {
//                    System.out.println((i+k)+" "+(j+k));
                    if(j==i) {
                        ans+=arr[i+k]*arr[j+k];
                    }
                    else {
                        ans+=2*(arr[i+k]*arr[j+k]);
                    }
                    ans%=mod;
                }
//                System.out.println();
            }
//            System.out.println();
        }
        return ans;
    }
}
