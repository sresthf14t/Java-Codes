/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_666_Div_2;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class C {
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
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int n=input.scanInt();
        long arr[]=new long[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
        }
        if(n==1) {
            System.out.println(1+" "+1);
            System.out.println(-1*arr[0]);
            System.out.println(1+" "+1);
            System.out.println(0);
            System.out.println(1+" "+1);
            System.out.println(0);
            return;
        }
        int l=0;
        int r=n-2;
        int range=(r-l+1);
//        if(range%2==n%2) {
//            r++;
//            range++;
//        }
        System.out.println((l+1)+" "+(r+1));
        StringBuilder ans=new StringBuilder("");
        for(int i=l;i<=r;i++) {
            long tmp=get(arr[i],n,range);
            arr[i]+=tmp*range;
            ans.append((tmp*range)+" ");
        }
        System.out.println(ans);
        l=1;
        r=n-1;
        range=(r-l+1);
        System.out.println((l+1)+" "+(r+1));
        ans=new StringBuilder("");
        for(int i=l;i<=r;i++) {
            long tmp=get(arr[i],n,range);
            arr[i]+=tmp*range;
            ans.append((tmp*range)+" ");
        }
        System.out.println(ans);
        System.out.println(1+" "+n);
        ans=new StringBuilder("");
        for(int i=0;i<n;i++) {
            ans.append(-1*arr[i]);
            ans.append(" ");
        }
        System.out.println(ans);
        
    }
    public static long get(long ele,long n,int tmp) {
        if(ele<0) {
            ele=Math.abs(ele);
            ele%=n;
            ele=n-ele;
        }
        ele%=n;
        ele*=-1;
        if(ele<0) {
            ele+=n;
        }
        long inv=Inverse(tmp,n);
        ele*=inv;
        ele%=n;
        while(ele<0) {
            ele+=n;
        } 
        return ele;
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