/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_644_Div_3;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class H {
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
            long rem[]=new long[n];
            Set<Long> hashset=new HashSet<>();
            for(int i=0;i<n;i++) {
                rem[i]=bin_to_dec(input.scanString());
                hashset.add(rem[i]);
            }
            long total=(long)Math.pow(2, m);
            total-=n;
            long median=(total-1)/2;
            int cnt=0;
            for(int i=0;i<n;i++) {
                if(rem[i]<median) {
                    cnt++;
                }
            }
            for(int i=0;i<cnt;i++) {
                if(hashset.contains(median)) {
                    median++;
                    i--;
                }
                else {
                    median++;
                }
            }
            while(hashset.contains(median)) {
                median++;
            }
            System.out.println(dec_to_bin(median,m));
        }
    }
    public static StringBuilder dec_to_bin(long n,int m) {
        StringBuilder ans=new StringBuilder("");
        while(n!=0) {
            ans.append(n%2);
            n/=2;
        }
        while(ans.length()!=m) {
            ans.append('0');
        }
        ans.reverse();
        return ans;
    }
    public static long bin_to_dec(String str) {
        long ret=0;
        for(int i=0;i<str.length();i++) {
            if(str.charAt(i)=='0') {
                continue;
            }
            ret+=(long)Math.pow(2,str.length()-i-1);
        }
//        System.out.println(ret);
        return ret;
    }
}
