/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_689_Div_2_based_on_Zed_Code_Competition;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
import java.math.BigInteger;
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
    
    public static long cvt(String str) {
        long ans=0;
        for(int i=0;i<str.length();i++) {
            ans=(ans*10)+(str.charAt(i)-'0');
        }
        return ans;
    }
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        long k=cvt(input.scanString());
        long l=cvt(input.scanString());
        long r=cvt(input.scanString());
        long t=cvt(input.scanString());
        long x=cvt(input.scanString());
        long y=cvt(input.scanString());
        
        
        //Not fill
        
        long tmp=k-(t*x);
        if(tmp>=l) {
            System.out.println("YES");
            return;
        }
        
        //First fill
        
        long fst=(y+k-r)/x;
        
        if((k-x*fst)+y>r) {
            fst++;
        }
//        System.out.println(fst);
        if(k-(x*fst)<l) {
            System.out.println("NO");
            return;
        }
        if(fst>=t) {
            System.out.println("YES");
            return;
        }
        if(y>(r-l)) {
            System.out.println("NO");
            return;
        }
        if(y<x) {
            long def=x-y;
            if(k-(((t-fst)*def)+(fst*x))<l) {
                System.out.println("NO");
                return;
            }
            System.out.println("YES");
            return;
        }
//        System.out.println(lcm(x,y));
        if(y>=x && (lcm(x,y)==-1 || lcm(y,x)>(r-l+1)) ) {
            Set<Long> hashset=new HashSet<>();
            for(int i=0;i<t;i++) {
                if(i>500000) {
                    System.out.println("YES");
                    return;
                }
                if(hashset.contains(k)) {
                    System.out.println("YES");
                    return;
                }
                hashset.add(k);
                if(k<l) {
                    System.out.println("NO");
                    return;
                }
                if(k+y<=r) {
                    k+=y;
                }
                k-=x;
            }
            if(k<l) {
                System.out.println("NO");
                return;
            }
            System.out.println("YES");
            return;
        }
        System.out.println("YES");
    }
    public static long lcm(long a,long b) {
        long gcd=gcd(a,b);
        BigInteger A=new BigInteger(""+a);
        BigInteger B=new BigInteger(""+b);
        BigInteger GCD=new BigInteger(""+gcd);
        BigInteger LCM=A.multiply(B);
        LCM=LCM.divide(GCD);
        if(LCM.compareTo(new BigInteger(""+Long.MAX_VALUE))==1) {
            return -1;
        }
        return LCM.longValue();
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
}
