/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_651_Div_2;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class C {
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
        int test=input.scanInt();
        primes=new int[9592];
        sieve(100000);
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            System.out.println(solve(n));
        }
    }
    public static String solve(int n) {
        if(n==1) {
            return "FastestFinger";
        }
        if(n%2==1) {
            return "Ashishgup";
        }
        int even=0,odd=0;
        for(int i=0;i<primes.length;i++) {
            while(n%primes[i]==0) {
                n/=primes[i];
                if(i==0) {
                    even++;
                }
                else {
                    odd++;
                }
            }
        }
        if(n!=1) {
            odd++;
        }
        if(odd==0) {
            if(even==1) {
                return "Ashishgup";
            }
            return "FastestFinger";
        }
        if(odd==1) {
            if(even==1) {
                return "FastestFinger";
            }
            return "Ashishgup";
        }
        if(even==1) {
            return "Ashishgup";
        }
        return "Ashishgup";
    }
    static boolean sieve[];
    //false for prime number and true for composite number
    public static void sieve(int n) {
        sieve=new boolean[n+1];
        int indx=0;
        for(int i=2;i<n;i++) {
            if(!sieve[i]) {
                primes[indx]=i;
                indx++;
                for(int j=2*i;j<n;j=j+i) {
                    sieve[j]=true;
                }
            }
        }
    }
}
