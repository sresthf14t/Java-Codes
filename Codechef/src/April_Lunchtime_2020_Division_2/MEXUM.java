/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package April_Lunchtime_2020_Division_2;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class MEXUM {
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
    static long total,fact[],mod=998244353;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int test=input.scanInt();
        StringBuilder ans=new StringBuilder("");
        fact=new long[10000];
        fact();
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            int arr[]=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt();
            }
            total=0;
            solve(n,arr);
            System.out.println(total);
        }
    }
    public static void solve(int n,int arr[]) {
        Arrays.sort(arr);
        HashMap<Integer,Integer> map = new HashMap<>();
        long mex=Integer.MAX_VALUE;
        for(int i=0;i<n;i++) {
            if(map.containsKey(arr[i])) {
                map.replace(arr[i], map.get(arr[i])+1);
            }
            else {
                map.put(arr[i], 1);
            }
        }
        for(int i=1;i<100000000;i++) {
            if(!map.containsKey(i)) {
                mex=i;
                break;
            }
        }
        int ele[]=new int[map.size()];
        int freq[]=new int[map.size()];
        for(int i=0,indx=0;i<n;i++) {
            if(map.containsKey(arr[i])) {
                ele[indx]=arr[i];
                freq[indx]=map.get(arr[i]);
                map.remove(arr[i]);
                indx++;
            }
        }
        Set<Integer> hashset=new HashSet<>();
        rec(freq.length-1,freq,ele,hashset,1);
    }
    public static void rec(int n,int freq[],int ele[],Set<Integer> hashset,long choice) {
//        System.out.println(n+" "+choice);
        if(n==-1) {
            long mex=find_mex(hashset);
//            System.out.println("mex="+mex);
            total+=(choice*mex)%mod;
            total%=mod;
            return;
        }
        for(int i=0;i<=freq[n];i++) {
            long tmp_choice=choice*ncr(freq[n],i);
            tmp_choice%=mod;
            if(i==1) {
                hashset.add(ele[n]);
            }
            rec(n-1,freq,ele,hashset,tmp_choice);
        }
        hashset.remove(ele[n]);
    }
    public static long find_mex(Set<Integer> hashset) {
        for(int i=1;i<10000000L;i++) {
            if(!hashset.contains(i)) {
                return i;
            }
        }
        return -1;
    }
    public static long ncr(int n,int r) {
        long ncr=(((fact[n]*Inverse(fact[r],mod))%mod)*Inverse(fact[n-r],mod))%mod;
        return ncr;
    }
    public static void fact() {
        fact[0]=1;
        for(int i=1;i<10000;i++) {
            fact[i]=i*fact[i-1];
            fact[i]%=mod;
        }
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
