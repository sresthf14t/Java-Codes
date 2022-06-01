/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package November_Cook_Off_2020_Division_2;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class HIRINGWO {
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
        StringBuilder ans=new StringBuilder("");
        int test=input.scanInt();
        sieve();
        for(int tt=1;tt<=test;tt++) {
            int k=input.scanInt();
            int x=input.scanInt();
            ArrayList<Integer> div=new ArrayList<>();
            for(int i=0;i<primes.length;i++) {
                int tmp=1;
                while(x%primes[i]==0) {
                    x/=primes[i];
                    tmp*=primes[i];
                }
                if(tmp>1) {
                    div.add(tmp);
                }
            }
            
            if(k>=div.size()) {
                int fin=0;
                for(int i=0;i<div.size();i++) {
                    fin+=div.get(i);
                }
                fin+=k-div.size();
                ans.append(fin+"\n");
            }
            else {
                ans.append(solve(k,div,1)+"\n");
            }
        }
        System.out.println(ans);
    }
    
    public static int solve(int rem,ArrayList<Integer> div,int curr) {
        if(rem==0) {
            if(curr!=1) {
                return Integer.MAX_VALUE/2;
            }
            for(int i=0;i<div.size();i++) {
                if(div.get(i)!=1) {
                    return Integer.MAX_VALUE/2;
                }
            }
            return 0;
        }
        int min=Integer.MAX_VALUE/2;
        for(int i=0;i<div.size();i++) {
            if(div.get(i)==1) {
                continue;
            }
            int tmp=curr*div.get(i);
            int tt=div.get(i);
            div.set(i, 1);
            min=Math.min(min, solve(rem,div,tmp));
            min=Math.min(min, (curr*tt)+solve(rem-1,div,1));
            div.set(i, tt);
        }
        return min;
    }
    
    
    public static void sieve() {
        boolean sieve[]=new boolean[1000001];
        primes=new int[78498];
        int indx=0;
        for(int i=2;i<sieve.length;i++) {
            if(sieve[i]) {
                continue;
            }
            primes[indx]=i;
            indx++;
            for(int j=2;i*j<sieve.length;j++) {
                sieve[i*j]=true;
            }
        }
//        System.out.println(indx);
    }
}
