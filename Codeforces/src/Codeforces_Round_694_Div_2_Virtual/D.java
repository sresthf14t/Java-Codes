/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_694_Div_2_Virtual;

/**
 *
 * @author Srest
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
    
    public static long stl(String str) {
        long ans=0;
        for(int i=0;i<str.length();i++) {
            ans=(ans*10)+(str.charAt(i)-'0');
        }
        return ans;
    }
    
    static int primes[],set[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        sieve();
        set=new int[1000001];
        int cnt=0;
        int test=input.scanInt();
        for(int tt=1;tt<=test;tt++) {
            int n=input.scanInt();
            int arr[]=new int[n];
            int brr[]=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt();
                brr[i]=arr[i];
//                arr[i]=(int)(Math.random()*(1000000-1))+1;
                arr[i]=cvt(arr[i]);
//                System.out.print(arr[i]+" ");
            }
            System.out.println();
            
            
            int fin[]=solve(n,arr);
            int q=input.scanInt();
            for(int i=0;i<q;i++) {
                cnt++;
//                if(cnt==105859) {
//                    print(n,brr);
//                    System.exit(0);
//                }
                long w=stl(input.scanString());
//                long w=(long)(Math.random()*1000000000000000000L);
                w=Math.min(w,1);
                ans.append(fin[(int)w]+"\n");
            }
        }
        System.out.println(ans);
    }
    public static int[] solve(int n,int arr[]) {
        int fin[]=new int[2];
        int cnt=0;
        for(int i=0;i<n;i++) {
            set[arr[i]]++;
            cnt=Math.max(cnt,set[arr[i]]);
        }
        for(int i=0;i<n;i++) {
            if(arr[i]==1) {
                continue;
            }
            if(set[arr[i]]%2==0) {
                set[1]+=set[arr[i]];
                set[arr[i]]=0;
            }
        }
        fin[0]=cnt;
        cnt=set[1];
        for(int i=0;i<n;i++) {
            cnt=Math.max(cnt,set[arr[i]]);
        }
        set[1]=0;
        for(int i=0;i<n;i++) {
            set[arr[i]]=0;
        }
        fin[1]=cnt;
        return fin;
    }
    
    public static int cvt(int n) {
        int ans=1;
        for(int i=0;i<primes.length;i++) {
            int cnt=0;
            while(n%primes[i]==0) {
                cnt++;
                n/=primes[i];
            }
            if(cnt%2==1) {
                ans*=primes[i];
            }
        }
        if(n!=1) {
            ans*=n;
        }
        return ans;
    }
    
    public static void sieve() {
        boolean sieve[]=new boolean[1001];
        primes=new int[168];
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
    public static void print(int n,int arr[]) {
        for(int i=0;i<n;i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
    
}
