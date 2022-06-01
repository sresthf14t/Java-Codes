/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Educational_Codeforces_Round_89;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class D {
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
    static int primes[],a,b;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        sieve(3500);
        int n=input.scanInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
        }
        StringBuilder ans1=new StringBuilder("");
        StringBuilder ans2=new StringBuilder("");
        for(int i=0;i<n;i++) {
            solve(arr[i]);
            ans1.append(a+" ");
            ans2.append(b+" ");
        }
        System.out.println(ans1+"\n"+ans2);
    }
    public static void solve(int n) {
        int cpy=n;
        ArrayList<Integer> div=new ArrayList<>();
        for(int i=0;i<primes.length;i++) {
            if(n%primes[i]==0) {
                div.add(primes[i]);
            }
            while(n%primes[i]==0) {
                n/=primes[i];
            }
        }
        if(n!=1) {
            div.add(n);
        }
        a=-1;
        b=-1;
        for(int i=0;i<div.size();i++) {
            for(int j=i+1;j<div.size();j++) {
                if(gcd(div.get(i)+div.get(j),cpy)) {
                    a=div.get(i);
                    b=div.get(j);
                    break;
                }
            }
            if(a!=-1) {
                break;
            }
        }
    }
    //false for prime number and true for composite number
    public static void sieve(int n) {
        boolean sieve[]=new boolean[n];
        primes=new int[489];
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
//        System.out.println(indx);
    }
    
    public static boolean gcd(int a,int n) {
        int q,r1=n,r2=a,r;
        while(true) {
            q=r1/r2;
            r=r1%r2;
            r1=r2;
            r2=r;
            if(r2==0) {
                    break;
            }
        }
        if(r1==1) {
            return true;
        }
        return false;
    }
}
