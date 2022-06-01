/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package October_Lunchtime_2020_Division_1;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class COPAR {
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
        sieve(500);
        int test=input.scanInt();
        for(int tt=1;tt<=test;tt++) {
            int n=input.scanInt();
            int arr[]=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt();
//                arr[i]=(int)(Math.random()*100000);
            }
            ans.append(solve(n,arr)+"\n");
        }
        System.out.println(ans);
    }
    public static int solve(int n,int arr[]) {
        int fst[]=new int[primes.length];
        int lst[]=new int[primes.length];
        
        HashMap<Integer,Integer> ff=new HashMap<>();
        HashMap<Integer,Integer> ll=new HashMap<>();
        
        int brr[]=new int[n];
        for(int i=0;i<n;i++) {
            brr[i]=arr[i];
            for(int j=0;j<primes.length;j++) {
                while(brr[i]%primes[j]==0) {
                    brr[i]/=primes[j];
                }
            }
            if(brr[i]==1) {
                continue;
            }
            if(!ff.containsKey(brr[i])) {
                ff.put(brr[i], i);
            }
            if(!ll.containsKey(brr[i])) {
                ll.put(brr[i], i);
            }
            else {
                ll.replace(brr[i], i);
            }
        }
        
        boolean is_pos[]=new boolean[n];
        Arrays.fill(is_pos, true);
        for(int i=0;i<n;i++) {
            if(brr[i]==1) {
                continue;
            }
            int indx=ll.get(brr[i]);
            for(int j=indx-1;j>=i;j--) {
                if(!is_pos[j]) {
                    break;
                }
                is_pos[j]=false;
            }
        }
        
        for(int i=0;i<primes.length;i++) {
            fst[i]=lst[i]=-1;
            for(int j=0;j<n;j++) {
                if(arr[j]%primes[i]==0) {
                    fst[i]=j;
                    break;
                }
            }
            for(int j=n-1;j>=0;j--) {
                if(arr[j]%primes[i]==0) {
                    lst[i]=j;
                    break;
                }
            }
        }
        for(int i=0;i<n-1;i++) {
            if(!is_pos[i]) {
                continue;
            }
            boolean done=true;
            for(int j=0;j<primes.length;j++) {
                if(fst[j]<=i && lst[j]>i) {
                    done=false;
                    break;
                }
            }
            if(done) {
                return i+1;
            }
        }
        return -1;
    }
    public static void sieve(int n) {
        boolean sieve[]=new boolean[n+1];
        int indx=0;
        primes=new int[95];
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
