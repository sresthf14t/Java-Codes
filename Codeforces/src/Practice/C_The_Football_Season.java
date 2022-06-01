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
import java.math.*;
public class C_The_Football_Season {
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
    static long primes[];
    public static void main(String args[]) throws IOException {
        Scanner input=new Scanner(System.in);
        long n=input.nextLong();
        long p=input.nextLong();
        long w=input.nextLong();
        long d=input.nextLong();
        sieve();
        for(int i=0;i<primes.length;i++) {
            while(w%primes[i]==0 && d%primes[i]==0 && p%primes[i]==0) {
                w/=primes[i];
                d/=primes[i];
                p/=primes[i];
            }
        }
        long w_inv=Inverse(w,d);
        if(w_inv==-1) {
            solve1(n,p,w,d);
            return;
        }
        if(!solve(n,p,w,d,w_inv)) {
            System.out.println(-1);
        }
    }
    public static boolean solve(long n,long p,long w,long d,long w_inv) {
//        System.out.println(w_inv);
        long val=get(n,p,w,d,w_inv);
        
        long l=0,r=(n-val)/d;
        while(l<=r) {
            long mid=(l+r)/2;
            long x=(val+(mid*d));
//            System.out.println((p-(w*x))%d);
            long y=(p-(w*x))/d;
//            System.out.println(x+" "+y+" "+(x*w+y*d)+" "+p);
            if(y>=0 && x+y<=n) {
                System.out.println(x+" "+y+" "+(n-x-y));
                return true;
            }
            if(y<0) {
                r=mid-1;
            }
            else {
                l=mid+1;
            }
        }
        return false;
    }
    public static long get(long n,long p,long w,long d,long w_inv) {
        BigInteger val=(new BigInteger(""+p)).multiply(new BigInteger((""+w_inv)));
        val=val.mod(new BigInteger(""+d));
        return val.longValue();
    }
    public static void solve1(long n,long p,long w,long d) {
        long x=p/w;
        long rem=p-(x*w);
        if(rem%d!=0) {
            System.out.println(-1);
            return;
        }
        long y=rem/d;
        if(x+y>n) {
            System.out.println(-1);
            return;
        }
        System.out.println(x+" "+y+" "+(n-x-y));
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
    public static void sieve() {
        boolean sieve[]=new boolean[100001];
        primes=new long[9592];
        int indx=0;
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
}
