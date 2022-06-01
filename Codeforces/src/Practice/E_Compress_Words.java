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
public class E_Compress_Words {
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
        int n=input.scanInt();
        String str[]=new String[n];
        for(int i=0;i<n;i++) {
            str[i]=input.scanString();
        }
        System.out.println(solve(n,str));
    }
    public static String solve(int n,String str[]) {
        long p1=131;
        long m1=951231973;
        
        long p2=167;
        long m2=1000000007;
        //Calculating power (p^n%)m
        long pow1[]=new long[1000000];
        pow1[0]=1;
        
        long pow2[]=new long[1000000];
        pow2[0]=1;
        for(int i=1;i<pow1.length;i++) {
            pow1[i]=pow1[i-1]*p1;
            pow1[i]%=m1;
            
            pow2[i]=pow2[i-1]*p2;
            pow2[i]%=m2;
        }
        
        StringBuilder ans=new StringBuilder(str[0]);
        for(int i=0;i<n-1;i++) {
            int len=-1;
            long hash1=0,hash2=0,hash3=0,hash4=0;
            for(int j1=ans.length()-1,j2=0;j1>=0 && j2<str[i+1].length();j1--,j2++) {
                hash1*=p1;
                hash1%=m1;
                hash1+=ans.charAt(j1);
                
                hash2+=(str[i+1].charAt(j2)*pow1[j2])%m1;
                hash2%=m1;
                
                
                hash3*=p2;
                hash3%=m2;
                hash3+=ans.charAt(j1);
                
                hash4+=(str[i+1].charAt(j2)*pow2[j2])%m2;
                hash4%=m2;
                
                if(hash1==hash2 && hash3==hash4) {
                    len=j2;
                }
            }
            for(int j=len+1;j<str[i+1].length();j++) {
                ans.append(str[i+1].charAt(j));
            }
        }
        return ""+ans;
    }
}
