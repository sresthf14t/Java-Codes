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
public class C_Bear_and_Prime_Numbers {
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
    static long primes[],prefix[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        primes=new long[664579];
        prefix=new long[primes.length];
        sieve(10000001);
        int n=input.scanInt();
        long max=0;
        int freq[]=new int[10000001];
        for(int i=0;i<n;i++) {
            int tmp=input.scanInt();
            freq[tmp]++;
            max=Math.max(max,tmp);
        }
        prefix(n,freq,max);
        int query=input.scanInt();
        StringBuilder ans=new StringBuilder("");
        for(int q=1;q<=query;q++) {
            int l=input.scanInt();
            int r=input.scanInt();
            ans.append(solve(l,r));
            ans.append("\n");
        }
        System.out.println(ans);
    }
    public static long solve(long l,long r) {
        if(l>primes[primes.length-1]) {
            return 0;
        }
        int lft=bin_search_jgt(primes,0,primes.length-1,l),rght;
        if(r>=primes[primes.length-1]) {
            rght=primes.length-1;
        }
        else {
            rght=bin_search_jlt(primes,0,primes.length-1,r);
        }
//        System.out.println(primes[lft]+" "+primes[rght]);
        if(primes[lft]>=l && primes[lft]<=r && primes[rght]>=l && primes[rght]<=r) {
        }
        else {
            return 0;
        }
        if(lft==0 || rght==0) {
            return prefix[rght];
        }
        return prefix[rght]-prefix[lft-1];
    }
    public static void prefix(int n,int freq[],long max) {
        long count=0;
        for(int i=0;i<primes.length;i++) {
            for(int j=0;primes[i]*j<=max;j++) {
                count+=freq[(int)primes[i]*j];
            }
            prefix[i]=count;
        }
    }
    public static int bin_search_jgt(long arr[],int l,int r,long target) {
        while(true) {
            int pivot=(l+r)/2;
            if(pivot==0) {
                return pivot;
            }
            if(arr[pivot]>=target && arr[pivot-1]<target) {
                return pivot;
            }
            if(arr[pivot]>target) {
                r=pivot-1;
                continue;
//                return bin_search_jgt(arr,l,pivot-1,target);
            }
            l=pivot+1;
//            return bin_search_jgt(arr,pivot+1,r,target);
        }
    }
    
    public static int bin_search_jlt(long arr[],int l,int r,long target) {
        while(true) {
            int pivot=(l+r)/2;
            if(pivot==arr.length-1) {
                return pivot;
            }
            if(arr[pivot]<=target && arr[pivot+1]>target) {
                return pivot;
            }
            if(arr[pivot]>target) {
                r=pivot-1;
                continue;
//                return bin_search_jlt(arr,l,pivot-1,target);
            }
            l=pivot+1;
//            return bin_search_jlt(arr,pivot+1,r,target);
        }
        
    }
    //false for prime number and true for composite number
    public static void sieve(int n) {
        boolean sieve[]=new boolean[n];
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