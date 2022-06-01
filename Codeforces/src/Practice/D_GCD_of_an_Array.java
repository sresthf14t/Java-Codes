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
public class D_GCD_of_an_Array {
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
    static TreeMap<Integer,Integer> map[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        
        sieve(200001);
        
        HashMap<Integer,Integer> prime_indx=new HashMap<>();
        for(int i=0;i<primes.length;i++) {
            prime_indx.put(primes[i], i);
        }
        
        long mod=1000000007L;
        int n=input.scanInt();
        int q=input.scanInt();
        int arr[]=new int[n];
        
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
        }
        
        map=new TreeMap[n];
        
        for(int i=0;i<n;i++) {
            map[i]=new TreeMap<>();
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
                boolean done=false;
                while(val%primes[j]==0) {
                    val/=primes[j];
                    add(i,primes[j]);
                    done=true;
                }
                if(done) {
                    cnt[j]++;
                }
            }
            
            if(val==1) {
                continue;
            }
            
            int idx=prime_indx.get(val);
            
            add(i,val);
            cnt[idx]++;
        }
        
        
        for(int qq=0;qq<q;qq++) {
            int indx=input.scanInt()-1;
            int val=input.scanInt();
            
            for(int i=0;i<primes.length && primes[i]<500;i++) {
                while(val%primes[i]==0) {
                    val/=primes[i];
                    if(cnt[i]==n-1 && !map[indx].containsKey(primes[i])) {
                        add(indx,primes[i]);
                        remove(primes[i]);
                        remake(i,cnt);
                        gcd*=primes[i];
                        gcd%=mod;
                    }
                    else {
                        if(!map[indx].containsKey(primes[i])) {
                            cnt[i]++;
                        }
                        add(indx,primes[i]);
                    }
                }
            }
            
            if(val!=1) {
                int idx=prime_indx.get(val);
                
                if(cnt[idx]==n-1 && !map[indx].containsKey(primes[idx])) {
                    add(indx,primes[idx]);
                    remove(primes[idx]);
                    remake(idx,cnt);
                    gcd*=primes[idx];
                    gcd%=mod;
                }
                else {
                    if(!map[indx].containsKey(primes[idx])) {
                        cnt[idx]++;
                    }
                    add(indx,primes[idx]);
                }
            }
            
            ans.append(gcd+"\n");
        }
        
        System.out.println(ans);
    }
    
    public static void remove(int val) {
        for(int i=0;i<map.length;i++) {
            rem(i,val);
        }
    }
    
    public static void remake(int indx,int cnt[]) {
        cnt[indx]=0;
        for(int i=0;i<map.length;i++) {
            if(map[i].containsKey(primes[indx])) {
                cnt[indx]++;
            }
        }
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
    
    public static void add(int indx,int val) {
        if(!map[indx].containsKey(val)) {
            map[indx].put(val, 0);
        }
        map[indx].replace(val, map[indx].get(val)+1);
    }
    
    public static void rem(int indx,int val) {
        if(!map[indx].containsKey(val)) {
            return;
        }
        map[indx].replace(val, map[indx].get(val)-1);
        if(map[indx].get(val)==0) {
            map[indx].remove(val);
        }
    }
    
    public static void sieve(int n) {
        boolean sieve[]=new boolean[n];
        primes=new int[17984];
        int indx=0;
        for(int i=2;i<n;i++) {
            if(sieve[i]) {
                continue;
            }
            primes[indx]=i;
            indx++;
            for(int j=2;i*j<n;j++) {
                sieve[i*j]=true;
            }
        }
//        System.out.println(indx);
    }
}
