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
public class D_Vasya_and_Triangle {
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
    static int primes[],freq[];
    static long n,m,k,c,x,y;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        sieve();
        n=input.scanInt();
        m=input.scanInt();
        k=input.scanInt();
        c=2*(n*m)/k;
        if(2*n*m%k!=0) {
            System.out.println("NO");
            return;
        }
        int indx=9592;
        freq=new int[primes.length];
        long tmpn=n,tmpm=m,tmpk=k;
        for(int i=0;i<primes.length-3;i++) {
            while(tmpn%primes[i]==0) {
                tmpn/=primes[i];
                freq[i]++;
            }
        }
        if(tmpn!=1) {
            primes[indx]=(int)tmpn;
            freq[indx]++;
            indx++;
        }
        
        
        for(int i=0;i<primes.length-3;i++) {
            while(tmpm%primes[i]==0) {
                tmpm/=primes[i];
                freq[i]++;
            }
        }
        if(tmpm!=1) {
            primes[indx]=(int)tmpm;
            freq[indx]++;
            indx++;
        }
        
        
        for(int i=0;i<primes.length-3;i++) {
            while(tmpk%primes[i]==0) {
                tmpk/=primes[i];
                freq[i]--;
            }
        }
        if(tmpk!=1) {
            if(tmpk==primes[primes.length-2]) {
                freq[primes.length-2]--;
            }
            else if(tmpk==primes[primes.length-1]) {
                freq[primes.length-1]--;
            }
        }
        freq[0]++;
        x=-1;
        y=-1;
        
        int cnt=0;
        for(int i=0;i<primes.length;i++) {
            if(freq[i]>0) {
                cnt++;
            }
        }
        int tmp1[]=new int[cnt];
        int tmp2[]=new int[cnt];
        indx=0;
        for(int i=0;i<primes.length;i++) {
            if(freq[i]>0) {
                tmp1[indx]=primes[i];
                tmp2[indx]=freq[i];
                indx++;
            }
        }
        primes=tmp1;
        freq=tmp2;
        solve(0,1);
        if(x!=-1 && y!=-1) {
            System.out.println("YES");
            System.out.println(0+" "+0);
            System.out.println(x+" "+0);
            System.out.println(0+" "+y);
        }
        else {
            System.out.println("NO");
        }
    }
    
    
    public static void solve(int indx,long a) {
//        System.out.println(indx);
        if(x!=-1 && y!=-1) {
            return;
        }
        if(indx==primes.length) {
            long b=c/a;
            if(a<=n && b<=m) {
                x=a;
                y=b;
            }
            return;
        }
        for(int i=0;i<=freq[indx];i++) {
            if(i>0) {
                a*=primes[indx];
            }
            solve(indx+1,a);
        }
    }
    
    public static void sieve() {
        boolean sieve[]=new boolean[100000];
        primes=new int[9592+2];
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
