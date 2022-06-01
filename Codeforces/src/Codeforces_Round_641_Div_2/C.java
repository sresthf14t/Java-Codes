/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_641_Div_2;

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
        sieve(200001);
        int n=input.scanInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
        }
//        fact(n,arr);
        System.out.println(solve(n,arr));
    }
    public static int solve(int n,int arr[]) {
        int ans=1;
        int cnt[]=new int[30];
        for(int i=0;i<primes.length;i++) {
            for(int j=0;j<n;j++) {
                int count=0;
                while(arr[j]%primes[i]==0) {
                    arr[j]/=primes[i];
                    count++;
                }
                cnt[count]++;
            }
            int pow=find_sec_min(n,cnt);
//            System.out.println(primes[i]+" "+pow);
            ans*=(int)Math.pow(primes[i], pow);
        }
        return ans;
    }
    public static int find_sec_min(int n,int arr[]) {
        int sum=0,ans=0;
        for(int i=0;i<arr.length;i++) {
            sum+=arr[i];
            if(sum>=2) {
                ans=i;
                break;
            }
        }
        for(int i=0;i<arr.length;i++) {
            arr[i]=0;
        }
        return ans;
    }
    //false for prime number and true for composite number
    public static void sieve(int n) {
        boolean sieve[]=new boolean[n];
        primes=new int[17984];
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
    public static void fact(int n,int arr[]) {
        System.out.println();
        for(int i=0;i<n;i++) {
            System.out.print(arr[i]+"->");
            for(int j=0;j<primes.length;j++) {
                if(arr[i]%primes[j]==0) {
                    System.out.print(primes[j]+" ");
                }
            }
            System.out.println();
        }
    }
}
