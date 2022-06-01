/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package August_Challenge_2020_Division_2;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class SUBSFREQ {
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
    static long fact[],mod;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        int test=input.scanInt();
        mod=1000000007L;
        fact=new long[5001];
        fact[0]=1;
        for(int i=1;i<fact.length;i++) {
            fact[i]=i*fact[i-1];
            fact[i]%=mod;
        }
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            int arr[]=new int[n];
            for(int i=0;i<n;i++) {
                arr[input.scanInt()-1]++;
            }
            if(n>5000) {
                solve1(n,arr);
            }
            else {
                solve2(n,arr);
            }
        }
        System.out.println(ans);
    }
    public static void solve1(int n,int arr[]) {
        long pow[]=new long[500001];
        pow[0]=1;
        for(int i=1;i<pow.length;i++) {
            pow[i]=2*pow[i-1];
            pow[i]%=mod;
        }
        StringBuilder ans=new StringBuilder("");
        for(int i=1;i<=n;i++) {
            ans.append(pow[n-i]+" ");
        }
        System.out.println(ans);
    }
    public static void solve2(int n,int arr[]) {
//        for(int i=0;i<n;i++) {
//            System.out.print(arr[i]+" ");
//        }
//        System.out.println();
        for(int i=0;i<n;i++) {
            long ans=0;
            for(int cnt=1;cnt<=arr[i];cnt++) {
                long tmp=ncr(arr[i],cnt);
//                System.out.println(i+" "+cnt+" "+tmp);
                for(int j=i-1;j>=0;j--) {
                    long tmp1=0;
                    for(int k=0;k<cnt && k<=arr[j];k++) {
                        tmp1+=ncr(arr[j],k);
                        tmp1%=mod;
                    }
//                    System.out.println(i+" "+cnt+" "+j+" "+tmp1);
                    if(tmp1>0) {
                        tmp*=tmp1;
                        tmp%=mod;
                    }
                }
                for(int j=i+1;j<n;j++) {
                    long tmp1=0;
                    for(int k=0;k<=cnt && k<=arr[j];k++) {
                        tmp1+=ncr(arr[j],k);
                        tmp1%=mod;
                    }
//                    System.out.println(i+" "+cnt+" "+j+" "+tmp1);
                    if(tmp1>0) {
                        tmp*=tmp1;
                        tmp%=mod;
                    }
                }
                ans+=tmp;
                ans%=mod;
            }
            System.out.print(ans+" ");
        }
        System.out.println();
    }
    public static long ncr(int n,int r) {
        long ans=fact[n]*Inverse(fact[n-r],mod);
        ans%=mod;
        ans*=Inverse(fact[r],mod);
        ans%=mod;
        return ans;
    }
    
    
    public static long Inverse(long a,long n) {
        long q,r1=n,r2=a,r,t1=0,t2=1,t;
        while(true) {
            q=r1/r2;
            r=r1%r2;
            t=t1-(q*t2);
            r1=r2;
            r2=r;
            t1=t2;
            t2=t;
            if(r2==0) {
                    break;
            }
        }
        if(r1!=1) {
                return -1;
        }
        t1%=n;
        if(t1<0) {
                t1+=n;
        }
        return t1;
    }
}
