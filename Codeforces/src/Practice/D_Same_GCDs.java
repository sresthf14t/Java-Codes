/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class D_Same_GCDs {
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
    static long primes[],factors[],a,m,gcd;
    static int freq[];
    public static void main(String args[]) throws IOException {
        Scanner input=new Scanner(System.in);
        sieve();
        int test=input.nextInt();
        for(int t=1;t<=test;t++) {
            a=input.nextLong();
            m=input.nextLong();
            long tmp_m=m;
            gcd=gcd(a,m);
            int tmp_freq[]=new int[primes.length+1];
            int cnt=0;
            for(int i=0;i<primes.length;i++) {
                while(tmp_m%primes[i]==0) {
                    tmp_m/=primes[i];
                    tmp_freq[i]++;
                }
                if(tmp_freq[i]>0) {
                    cnt++;
                }
            }
            if(tmp_m!=1) {
                cnt++;
            }
            factors=new long[cnt];
            freq=new int[cnt];
            for(int i=0,indx=0;i<primes.length;i++) {
                if(tmp_freq[i]>0) {
                    factors[indx]=primes[i];
                    freq[indx]=tmp_freq[i];
                    indx++;
                }
            }
            if(tmp_m!=1) {
                factors[factors.length-1]=tmp_m;
                freq[freq.length-1]=1;
            }
            long tmp_gcd=gcd;
            for(int i=0;i<factors.length;i++) {
                while(tmp_gcd%factors[i]==0) {
                    tmp_gcd/=factors[i];
                    freq[i]--;
                }
            }
//            for(int i=0;i<factors.length;i++) {
//                System.out.println(factors[i]+" "+freq[i]);
//            }
//            System.out.println(solve(0,gcd));
            System.out.println(((a+m-1)/gcd)-((a-1)/gcd)-solve(0,gcd,0));
        }
    }
    public static long solve(int indx,long value,int cnt) {
//        System.out.println(indx+" "+value);
        if(indx==freq.length) {
            if(value==gcd) {
                return 0;
            }
            if(cnt%2==1) {
                return ((a+m-1)/value)-((a-1)/value);
            }
            else {
                return (-1)*(((a+m-1)/value)-((a-1)/value));
            }
        }
        long ans=0;
        for(int i=0;i<=Math.min(1,freq[indx]);i++) {
            if(i!=0) {
                value*=factors[indx];
                cnt++;
            }
            ans+=solve(indx+1,value,cnt);
        }
        return ans;
    }
    public static void sieve() {
        boolean sieve[]=new boolean[100001];
        int indx=0;
        primes=new long[9592];
        for(int i=2;i<sieve.length;i++) {
            if(!sieve[i]) {
                primes[indx]=i;
                indx++;
                for(int j=2;i*j<sieve.length;j++) {
                    sieve[i*j]=true;
                }
            }
        }
//        System.out.println(indx);
    }
    
    
    public static long gcd(long a,long n) {
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
        return r1;
    }
}
