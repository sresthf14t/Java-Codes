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
public class D_Two_Divisors {
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
    static int primes[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int n=input.scanInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
        }
        sieve();
        StringBuilder ans1=new StringBuilder("");
        StringBuilder ans2=new StringBuilder("");
        for(int i=0;i<n;i++) {
            ArrayList<Integer> div=new ArrayList<>();
            int tmp=arr[i];
            for(int j=0;j<primes.length;j++) {
                int cnt=0;
                while(tmp%primes[j]==0) {
                    tmp/=primes[j];
                    cnt++;
                }
                if(cnt>=1) {
                    div.add(primes[j]);
                }
            }
            if(tmp!=1) {
                div.add(tmp);
            }
            if(div.size()==1) {
                ans1.append(-1+" ");
                ans2.append(-1+" ");
            }
            else {
                int mul=1;
                while(arr[i]%div.get(0)==0) {
                    arr[i]/=div.get(0);
                    mul*=div.get(0);
                }
                ans1.append(mul+" ");
                ans2.append(arr[i]+" ");
            }
        }
        System.out.println(ans1);
        System.out.println(ans2);
    }
    public static void sieve() {
        int indx=0;
        primes=new int[452];
        boolean sieve[]=new boolean[3200];
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
