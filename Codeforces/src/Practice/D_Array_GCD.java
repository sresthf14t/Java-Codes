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
public class D_Array_GCD {
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
    static int n,arr[],val,primes[];
    static long a,b;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        sieve();
        n=input.scanInt();
        a=input.scanInt();
        b=input.scanInt();
        arr=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
        }
        ArrayList<Integer> div=new ArrayList<>();
        Set<Integer> hashset=new HashSet<>();
        factor(arr[0]-1,div,hashset);
        factor(arr[0],div,hashset);
        factor(arr[0]+1,div,hashset);
        factor(arr[n-1]-1,div,hashset);
        factor(arr[n-1],div,hashset);
        factor(arr[n-1]+1,div,hashset);
        long ans=Long.MAX_VALUE;
        for(int i=0;i<div.size();i++) {
            val=div.get(i);
            ans=Math.min(ans,solve1());
            ans=Math.min(ans,solve2());
        }
        System.out.println(ans);
    }
    public static long solve1() {
        long dp0[]=new long[n];
        long dp1[]=new long[n];
        long dp2[]=new long[n];
        Arrays.fill(dp0, Long.MAX_VALUE/10);
        Arrays.fill(dp1, Long.MAX_VALUE/10);
        Arrays.fill(dp2, Long.MAX_VALUE/10);
        if(arr[0]%val==0) {
            dp0[0]=0;
        }
        else if((arr[0]-1)%val==0 || (arr[0]+1)%val==0) {
            dp0[0]=b;
        }
        dp1[0]=Long.MAX_VALUE/10;
        dp2[0]=dp0[0];
        for(int i=1;i<n;i++) {
            if(arr[i]%val==0) {
                dp0[i]=Math.min(dp0[i],dp0[i-1]);
            }
            else if((arr[i]-1)%val==0 || (arr[i]+1)%val==0) {
                dp0[i]=Math.min(dp0[i],b+dp0[i-1]);
            }
            
            dp1[i]=Math.min(dp1[i], a+dp0[i-1]);
            dp1[i]=Math.min(dp1[i],a+dp1[i-1]);
            
            if(arr[i]%val==0) {
                dp2[i]=Math.min(dp2[i],dp0[i-1]);
                dp2[i]=Math.min(dp2[i],dp1[i-1]);
                dp2[i]=Math.min(dp2[i],dp2[i-1]);
            }
            else if((arr[i]-1)%val==0 || (arr[i]+1)%val==0) {
                dp2[i]=Math.min(dp2[i],b+dp0[i-1]);
                dp2[i]=Math.min(dp2[i],b+dp1[i-1]);
                dp2[i]=Math.min(dp2[i],b+dp2[i-1]);
            }
        }
        return Math.min(dp0[n-1], Math.min(dp1[n-1],dp2[n-1]));
    }
    
    public static long solve2() {
        long dp0[]=new long[n];
        long dp1[]=new long[n];
        long dp2[]=new long[n];
        Arrays.fill(dp0, Long.MAX_VALUE/10);
        Arrays.fill(dp1, Long.MAX_VALUE/10);
        Arrays.fill(dp2, Long.MAX_VALUE/10);
        if(arr[0]%val==0) {
            dp0[0]=0;
        }
        else if((arr[0]-1)%val==0 || (arr[0]+1)%val==0) {
            dp0[0]=b;
        }
        dp1[0]=a;
        dp2[0]=dp0[0];
        for(int i=1;i<n;i++) {
            if(arr[i]%val==0) {
                dp0[i]=Math.min(dp0[i],dp0[i-1]);
            }
            else if((arr[i]-1)%val==0 || (arr[i]+1)%val==0) {
                dp0[i]=Math.min(dp0[i],b+dp0[i-1]);
            }
            
            dp1[i]=Math.min(dp1[i], a+dp0[i-1]);
            dp1[i]=Math.min(dp1[i],a+dp1[i-1]);
            
            if(arr[i]%val==0) {
                dp2[i]=Math.min(dp2[i],dp0[i-1]);
                dp2[i]=Math.min(dp2[i],dp1[i-1]);
                dp2[i]=Math.min(dp2[i],dp2[i-1]);
            }
            else if((arr[i]-1)%val==0 || (arr[i]+1)%val==0) {
                dp2[i]=Math.min(dp2[i],b+dp0[i-1]);
                dp2[i]=Math.min(dp2[i],b+dp1[i-1]);
                dp2[i]=Math.min(dp2[i],b+dp2[i-1]);
            }
        }
        return Math.min(dp0[n-1], dp2[n-1]);
    }
    
    public static void sieve() {
        boolean sieve[]=new boolean[35000];
        primes=new int[3732];
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
    public static void factor(int val,ArrayList<Integer> div,Set<Integer> hashset) {
        for(int i=0;i<primes.length;i++) {
            int cnt=0;
            while(val%primes[i]==0) {
                val/=primes[i];
                cnt++;
            }
            if(cnt>0 && !hashset.contains(primes[i])) {
                hashset.add(primes[i]);
                div.add(primes[i]);
            }
        }
        if(val!=1 && !hashset.contains(val)) {
            hashset.add(val);
            div.add(val);
        }
    }
}
