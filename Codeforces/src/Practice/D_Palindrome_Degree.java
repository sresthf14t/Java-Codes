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
public class D_Palindrome_Degree {
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
    static int dp[];
    static long hash[],rev_hash[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        String str=input.scanString();
        
        long p=131;
        long m=1000000007;
        long p_inv=190839696;
        //Calculating power (p^n%)m
        long pow[]=new long[str.length()+1];
        hash=new long[str.length()+1];
        rev_hash=new long[str.length()+1];
        pow[0]=1;
        for(int i=1;i<pow.length;i++) {
            pow[i]=pow[i-1]*p;
            pow[i]%=m;
        }
        
        
        long h=0;
        for(int i=0;i<str.length();i++) {
            h+=(str.charAt(i)*pow[i])%m;
            h%=m;
            hash[i]=h;
        }
        
        h=0;
        for(int i=str.length()-1,j=0;i>=0;i--,j++) {
            h+=(str.charAt(i)*pow[j])%m;
            h%=m;
        }
        for(int i=str.length()-1;i>=0;i--) {
            rev_hash[i]=h;
            h-=str.charAt(i);
            h*=p_inv;
            h%=m;
        }
        System.out.println(solve(str.length(),str));
    }
    public static long solve(int n,String str) {
        dp=new int[n+1];
        dp[1]=1;
        int ans=1;
        for(int i=2;i<=n;i++) {
            if(hash[i-1]==rev_hash[i-1]) {
                dp[i]=1+dp[i/2];
            }
            ans+=dp[i];
        }
        return ans;
    }
    
}
