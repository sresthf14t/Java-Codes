/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_671_Div_2;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class E {
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
    static int primes[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        sieve();
        StringBuilder ans=new StringBuilder("");
        int test=input.scanInt();
        for(int tt=1;tt<=test;tt++) {
            int n=input.scanInt();
            solve(n);
        }
        System.out.println(ans);
    }
    public static void solve(int n) {
        ArrayList<Integer> div=new ArrayList<>();
        int lim=(int)Math.sqrt(n);
        for (int i=1; i<=lim; i++) { 
            if (n%i==0) { 
                if (n/i == i) {
                    div.add(i);
                }
                else {
                    div.add(i);
                    div.add(n/i);
                }
            } 
        }
        div.remove(new Integer(1));
        ArrayList<Integer> prime_div=new ArrayList<>();
        for(int i=0;i<primes.length;i++) {
            int cnt=0;
            while(n%primes[i]==0) {
                n/=primes[i];
                cnt++;
            }
            if(cnt>0) {
                prime_div.add(primes[i]);
            }
        }
        if(n!=1) {
            prime_div.add(n);
        }
        StringBuilder ans=new StringBuilder("");
        if(prime_div.size()==1) {
            for(int i=0;i<div.size();i++) {
                ans.append(div.get(i)+" ");
            }
            System.out.println(ans+"\n"+0);
            return;
        }
        boolean taken[]=new boolean[div.size()];
        ArrayList<Integer> arrli1=new ArrayList<>();
        for(int i=0;i<prime_div.size();i++) {
            arrli1.add(prime_div.get(i));
            for(int j=0;j<div.size();j++) {
                if(taken[j]) {
                    continue;
                }
                if(div.get(j)%prime_div.get(i)==0 && div.get(j)%prime_div.get((i+1)%prime_div.size())==0) {
                    arrli1.add(div.get(j));
                    taken[j]=true;
                    break;
                }
            }
        }
        
        ArrayList<Integer> tmp=new ArrayList<>();
        for(int i=0;i<taken.length;i++) {
            if(arrli1.contains(div.get(i))) {
                taken[i]=true;
            }
        }
        for(int i=0;i<arrli1.size();i++) {
            tmp.add(arrli1.get(i));
            if(!prime_div.contains(arrli1.get(i))) {
                continue;
            }
            for(int j=0;j<div.size();j++) {
                if(taken[j]) {
                    continue;
                }
                if(div.get(j)%arrli1.get(i)==0) {
                    tmp.add(div.get(j));
                    taken[j]=true;
                }
            }
        }
        int cnt=0;
        for(int i=0;i<tmp.size();i++) {
            ans.append(tmp.get(i)+" ");
            if(gcd(tmp.get(i),tmp.get((i+1)%tmp.size()))==1) {
                cnt++;
            }
//            System.out.println(gcd(tmp.get(i),tmp.get((i+1)%tmp.size())));
        }
        System.out.println(ans+"\n"+cnt);
    }
    public static void sieve() {
        boolean sieve[]=new boolean[(int)Math.sqrt(1000000000)];
        primes=new int[3401];
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
    
    
    public static int gcd(int a,int n) {
        int q,r1=n,r2=a,r,t1=0,t2=1,t;
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
