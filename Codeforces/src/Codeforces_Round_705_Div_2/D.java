/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_705_Div_2;

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
    
    static int primes[],p_indx[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        
        long mod=1000000007L;
        
        sieve(200001);
        
        int n=input.scanInt();
        int q=input.scanInt();
        
        int arr[]=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
//            arr[i]=(int)(Math.random()*(200000-1))+1;
        }
        
        ArrayList<Integer> add[]=new ArrayList[n];
        for(int i=0;i<n;i++) {
            add[i]=new ArrayList<>();
        }
        
        long gcd=arr[0];
        for(int i=1;i<n;i++) {
            gcd=gcd((int)gcd,arr[i]);
        }
        
        for(int i=0;i<n;i++) {
            arr[i]/=(int)gcd;
        }
        
        int cnt[]=new int[primes.length];
        for(int i=0;i<n;i++) {
            int val=arr[i];
            for(int j=0;j<primes.length && primes[j]<500;j++) {
                int ctt=0;
                while(val%primes[j]==0) {
                    val/=primes[j];
                    ctt=1;
                }
                cnt[j]+=ctt;
            }
            if(val!=1) {
                int indx=p_indx[val];
                cnt[indx]++;
            }
        }
        
//        for(int i=0;i<10;i++) {
//            System.out.println(i+" "+primes[i]+" "+cnt[i]);
//        }
        
        for(int qq=0;qq<q;qq++) {
            int indx=input.scanInt()-1;
            int val=input.scanInt();
            
//            System.out.println("111 "+indx+" "+val);
            
//            int indx=(int)(Math.random()*(n-1));
//            int val=(int)(Math.random()*(200000-1))+1;
            
            int tmp=val;
            for(int i=0;i<primes.length && primes[i]<500;i++) {
                while(val%primes[i]==0) {
                    
//                    System.out.println(qq+" "+primes[i]+" "+arr[indx]+" "+check_missing(arr,indx,add[indx],primes[i]));
                    
                    val/=primes[i];
                    
                    if(check_missing(arr,indx,add[indx],primes[i])) {
                        if(cnt[i]!=n-1) {
                            cnt[i]++;
                            continue;
                        }
//                        System.out.println("000 "+qq+" "+primes[i]+" "+val+" "+tmp);
                        rem(add,arr,primes[i]);
                        remake_cnt(i,arr,add,cnt);
                        tmp/=primes[i];
                        gcd*=primes[i];
                        gcd%=mod;
//                        System.out.println("000 "+qq+" "+primes[i]+" "+val+" "+tmp);
                    }
                }
            }
            
            if(val==1) {
                if(tmp!=1) {
                    add[indx].add(tmp);
                }
                ans.append(gcd+"\n");
                continue;
            }

            int idx=p_indx[val];
            if(check_missing(arr,indx,add[indx],primes[idx])) {
                if(cnt[idx]!=n-1) {
                    cnt[idx]++;
                }
                else {
                    rem(add,arr,primes[idx]);
                    remake_cnt(idx,arr,add,cnt);
                    tmp/=primes[idx];
                    gcd*=primes[idx];
                    gcd%=mod;
                }
            }

            if(tmp!=1) {
                add[indx].add(tmp);
            }
            
            ans.append(gcd+"\n");
        }
        
        System.out.println(ans);
    }
    
    public static void remake_cnt(int indx,int arr[],ArrayList<Integer> arrli[],int cnt[]) {
        cnt[indx]=0;
        for(int i=0;i<arr.length;i++) {
            if(arr[i]%primes[indx]==0) {
                cnt[indx]++;
                continue;
            }
            for(int j=0;j<arrli[i].size();j++) {
                if(arrli[i].get(j)%primes[indx]!=0) {
                    continue;
                }
                cnt[indx]++;
                break;
            }
        }
    }
    
    public static void rem(ArrayList<Integer> arrli[],int arr[],int val) {
        for(int i=0;i<arr.length;i++) {
            if(arr[i]%val==0) {
                arr[i]/=val;
                continue;
            }
            for(int j=0;j<arrli[i].size();j++) {
                if(arrli[i].get(j)%val!=0) {
                    continue;
                }
                arrli[i].set(j, arrli[i].get(j)/val);
                break;
            }
        }
    }
    
    public static boolean check_missing(int arr[],int indx,ArrayList<Integer> arrli,int val) {
        if(arr[indx]%val==0) {
            return false;
        }
        for(int i=0;i<arrli.size();i++) {
            if(arrli.get(i)%val==0) {
                return false;
            }
        }
        return true;
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
    
    public static void sieve(int n) {
        boolean sieve[]=new boolean[n];
        primes=new int[17984];
        p_indx=new int[n];
        int indx=0;
        for(int i=2;i<sieve.length;i++) {
            if(sieve[i]) {
                continue;
            }
            primes[indx]=i;
            p_indx[i]=indx;
            indx++;
            for(int j=2;i*j<sieve.length;j++) {
                sieve[i*j]=true;
            }
        }
//        System.out.println(indx);
    }
}
