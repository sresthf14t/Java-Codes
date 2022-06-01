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
public class D_Prefixes_and_Suffixes {
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
    public static void main(String arsg[]) throws IOException {
        Scan input=new Scan();
        String str=input.scanString();
        count_prefixes(str.length(),str);
    }
    public static void count_prefixes(int n,String str) {
        int ans[]=new int[n+1];
        int kmp[]=create_KMP_Array(n,str);
        for(int i=0;i<n;i++) {
            ans[kmp[i]]++;
        }
        for(int i=n-1;i>0;i--) {
            ans[kmp[i-1]]+=ans[i];
        }
        for(int i=0;i<=n;i++) {
            ans[i]++;
        }
        StringBuilder fin=new StringBuilder("");
        long p=131;
        long m=1000000007;
        //Calculating power (p^n%)m
        long pow[]=new long[1000000];
        pow[0]=1;
        
        for(int i=1;i<pow.length;i++) {
            pow[i]=pow[i-1]*p;
            pow[i]%=m;
        }
         int cnt=0;
        long p_hash=0,s_hash=0;
        for(int i=0;i<n;i++) {
            p_hash+=(str.charAt(i)*pow[i])%m;
            p_hash%=m;
            s_hash*=p;
            s_hash%=m;
            s_hash+=str.charAt(str.length()-i-1);
            s_hash%=m;
            if(p_hash==s_hash) {
                cnt++;
                fin.append((i+1)+" "+ans[i+1]+"\n");
            }
        }
        System.out.println(cnt+"\n"+fin);
    }
    public static int[] create_KMP_Array(int n,String str) {
        int kmp[]=new int[n];
        kmp[0]=0;
        for(int i=1;i<n;i++) {
            int j=kmp[i-1];
            while(j>0 && str.charAt(i)!=str.charAt(j)) {
                j=kmp[j-1];
            }
            if(str.charAt(i)==str.charAt(j)) {
                j++;
            }
            kmp[i]=j;
        }
        return kmp;
    }
}
