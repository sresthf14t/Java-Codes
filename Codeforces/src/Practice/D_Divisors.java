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
public class D_Divisors {
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
    
    public static void main(String args[]) throws IOException {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        long arr[]=new long[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.nextLong();
            
//            arr[i]=(long)(Math.random()*100);
//            if(check(1,new long[]{arr[i]})<3 || check(1,new long[]{arr[i]})>5) {
//                i--;
//                continue;
//            }
//            System.out.print(arr[i]+" ");
        }
//        System.out.println();
        
//        long check=check(n,arr);
        Set<Long> hashset=new HashSet<>();
        for(int i=0;i<n;i++) {
            long s4=search4(arr[i]);
            if(s4!=-1) {
                if(!hashset.contains(s4)) {
                    hashset.add(s4);
                }
                continue;
            }
            
            
            s4=search3(arr[i]);
            if(s4!=-1) {
                if(!hashset.contains(s4)) {
                    hashset.add(s4);
                }
                continue;
            }
            
            
            s4=search2(arr[i]);
            if(s4!=-1) {
                if(!hashset.contains(s4)) {
                    hashset.add(s4);
                }
                continue;
            }
            
            for(int j=i+1;j<n;j++) {
                long gcd=gcd(arr[i],arr[j]);
                if(gcd==arr[i] || gcd==1 || hashset.contains(gcd)) {
                    continue;
                }
                hashset.add(gcd);
            }
        }
        
        long div[]=new long[hashset.size()];
        int indx=0;
        Iterator<Long> itr = hashset.iterator(); 
        while (itr.hasNext()) { 
            div[indx]=itr.next();
            indx++;
        }
        long freq[]=new long[div.length];
        long times[]=new long[n];
        for(int i=0;i<n;i++) {
            for(int j=0;j<div.length;j++) {
                boolean done=false;
                while(arr[i]%div[j]==0) {
                    freq[j]++;
                    done=true;
                    arr[i]/=div[j];
                }
                if(done) {
                    times[i]++;
                }
            }
        }
        
        long ans=1,mod=998244353;
        
        for(int i=0;i<n;i++) {
            if(arr[i]==1) {
                continue;
            }
            long cnt=1;
            for(int j=i+1;j<n;j++) {
                if(arr[j]==arr[i]) {
                    cnt++;
                }
            }
            if(cnt==1) {
                continue;
            }
            if(times[i]==0) {
                ans*=(cnt+1)*(cnt+1);
            }
            else {
                ans*=(cnt+1);
            }
            for(int j=i+1;j<n;j++) {
                if(arr[j]==arr[i]) {
                    arr[j]=1;
                }
            }
            arr[i]=1;
            ans%=mod;
        }
        
        for(int i=0;i<n;i++) {
            if(arr[i]==1) {
                continue;
            }
            if(times[i]==0) {
                ans*=2*2;
            }
            else {
                ans*=2;
            }
            ans%=mod;
        }
        for(int i=0;i<freq.length;i++) {
            ans*=(freq[i]+1);
            ans%=mod;
        }
        
//        for(int i=0;i<n;i++) {
//            System.out.print(arr[i]+" ");
//        }
//        System.out.println();
//        for(int i=0;i<div.length;i++) {
//            System.out.println(div[i]+" "+freq[i]);
//        }
//        System.out.println();
        System.out.println(ans);
        System.out.flush();
    }
    
    public static long check(int n,long arr[]) {
        long mul=1;
        for(int i=0;i<n;i++) {
            mul*=arr[i];
        }
        long ans=0;
        for(long i=1;i<=(long)Math.sqrt(mul);i++) {
            if(mul%i!=0) {
                continue;
            }
            if(mul/i==i) {
                ans++;
            }
            else {
                ans+=2;
            }
        }
        return ans;
    }
    
    public static long gcd(long a,long n) {
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
        return r1;
    }
    
    public static long search2(long n) {
//        System.out.println(2222);
        long l=1,r=2000000000;
        while(l<=r) {
            long mid=(l+r)/2;
            long val=mid*mid;
            if(val==n) {
                return mid;
            }
            if(val<n) {
                l=mid+1;
            }
            else {
                r=mid-1;
            }
        }
        return -1;
    }
    
    public static long search3(long n) {
//        System.out.println(3333);
        long l=1,r=1300000;
        while(l<=r) {
            long mid=(l+r)/2;
            long val=mid*mid*mid;
            if(val==n) {
                return mid;
            }
            if(val<n) {
                l=mid+1;
            }
            else {
                r=mid-1;
            }
        }
        return -1;
    }
    
    public static long search4(long n) {
//        System.out.println(4444);
        long l=1,r=40000;
        while(l<=r) {
            long mid=(l+r)/2;
            long val=mid*mid;
            val*=val;
//            System.out.println(val+" "+n);
            if(val==n) {
                return mid;
            }
            if(val<n) {
                l=mid+1;
            }
            else {
                r=mid-1;
            }
        }
        return -1;
    }
}
