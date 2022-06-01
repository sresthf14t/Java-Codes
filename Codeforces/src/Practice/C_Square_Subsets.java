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
public class C_Square_Subsets {
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
    
    public static void sort(int arr[],int l,int r) {    //sort(arr,0,n-1);
        if(l==r) {
            return;
        }
        int mid=(l+r)/2;
        sort(arr,l,mid);
        sort(arr,mid+1,r);
        merge(arr,l,mid,mid+1,r);
    }
    public static void merge(int arr[],int l1,int r1,int l2,int r2) {
        int tmp[]=new int[r2-l1+1];
        int indx1=l1,indx2=l2;
        //sorting the two halves using a tmp array
        for(int i=0;i<tmp.length;i++) {
            if(indx1>r1) {
                tmp[i]=arr[indx2];
                indx2++;
                continue;
            }
            if(indx2>r2) {
                tmp[i]=arr[indx1];
                indx1++;
                continue;
            }
            if(arr[indx1]<arr[indx2]) {
                tmp[i]=arr[indx1];
                indx1++;
                continue;
            }
            tmp[i]=arr[indx2];
            indx2++;
        }
        //Copying the elements of tmp into the main array
        for(int i=0,j=l1;i<tmp.length;i++,j++) {
            arr[j]=tmp[i];
        }
    }
    
    public static void sort(long arr[],int l,int r) {    //sort(arr,0,n-1);
        if(l==r) {
            return;
        }
        int mid=(l+r)/2;
        sort(arr,l,mid);
        sort(arr,mid+1,r);
        merge(arr,l,mid,mid+1,r);
    }
    public static void merge(long arr[],int l1,int r1,int l2,int r2) {
        long tmp[]=new long[r2-l1+1];
        int indx1=l1,indx2=l2;
        //sorting the two halves using a tmp array
        for(int i=0;i<tmp.length;i++) {
            if(indx1>r1) {
                tmp[i]=arr[indx2];
                indx2++;
                continue;
            }
            if(indx2>r2) {
                tmp[i]=arr[indx1];
                indx1++;
                continue;
            }
            if(arr[indx1]<arr[indx2]) {
                tmp[i]=arr[indx1];
                indx1++;
                continue;
            }
            tmp[i]=arr[indx2];
            indx2++;
        }
        //Copying the elements of tmp into the main array
        for(int i=0,j=l1;i<tmp.length;i++,j++) {
            arr[j]=tmp[i];
        }
    }
    static int n,arr[],primes[],pow[];
    static long dp[][],fact[],even_ncr[],odd_ncr[],mod;
    static boolean is_prime[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n=input.scanInt();
        arr=new int[71];
        for(int i=0;i<n;i++) {
            arr[input.scanInt()]++;
        }
        primes=new int[]{2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67};
        is_prime=new boolean[100];
        for(int i=0;i<primes.length;i++) {
            is_prime[primes[i]]=true;
        }
        primes=new int[]{2,3,5,7,11,13,17,19,23,29,31};
        pow=new int[20];
        pow[0]=1;
        for(int i=1;i<pow.length;i++) {
            pow[i]=2*pow[i-1];
        }
        mod=1000000007L;
        dp=new long[80][10000];
        for(int i=0;i<dp.length;i++) {
            for(int j=0;j<dp[0].length;j++) {
                dp[i][j]=-1;
            }
        }
        fact=new long[100001];
        fact[0]=1;
        for(int i=1;i<fact.length;i++) {
            fact[i]=i*fact[i-1];
            fact[i]%=mod;
        }
        even_ncr=new long[80];
        odd_ncr=new long[80];
        for(int i=1;i<arr.length;i++) {
            long even=1+(arr[i]/2),odd=(arr[i]+1)-even;
            for(int j=0;j<=arr[i];j++) {
                if(j%2==0) {
                    even_ncr[i]+=ncr(arr[i],j);
                    even_ncr[i]%=mod;
                }
                else {
                    odd_ncr[i]+=ncr(arr[i],j);
                    odd_ncr[i]%=mod;
                }
            }
        }
        System.out.println(solve(1,0)-1);
    }
    public static long solve(int indx,int state) {
//        System.out.println(indx+" "+state);
        if(indx==arr.length) {
            if(state==0) {
                return 1;
            }
            return 0;
        }
        if(dp[indx][state]!=-1) {
            return dp[indx][state];
        }
        int s=0,tmp=indx;
        for(int i=0;i<primes.length;i++) {
            int cnt=0;
            while(tmp%primes[i]==0) {
                tmp/=primes[i];
                cnt++;
            }
            if(cnt%2==1) {
                s+=pow[i];
            }
        }
        
        long ans=0;
        //even
        ans+=even_ncr[indx]*solve(indx+1,state);
        ans%=mod;
        //odd
        if(indx<=35 || !is_prime[indx]) {
            ans+=odd_ncr[indx]*solve(indx+1,state^s);
            ans%=mod;
        }
        dp[indx][state]=ans;
        return ans;
    }
    public static long ncr(int n,int r) {
        long ans=fact[n];
        ans*=inv(fact[r],mod);
        ans%=mod;
        ans*=inv(fact[n-r],mod);
        ans%=mod;
        return ans;
    }
    
    public static long inv(long a,long n) {
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
