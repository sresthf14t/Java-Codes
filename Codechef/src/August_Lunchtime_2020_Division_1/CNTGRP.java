/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package August_Lunchtime_2020_Division_1;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class CNTGRP {
    static class Scan {
        private byte[] buf = new byte[1024];
        private int index;
        private InputStream in;
        private int total;

        public Scan() {
            in = System.in;
        }

        public int scan() throws IOException {
            if (total < 0) {
                throw new InputMismatchException();
            }
            if (index >= total) {
                index = 0;
                total = in.read(buf);
                if (total <= 0) {
                    return -1;
                }
            }
            return buf[index++];
        }

        public int scanInt() throws IOException {
            int integer = 0;
            int n = scan();
            while (isWhiteSpace(n)) {
                n = scan();
            }
            int neg = 1;
            if (n == '-') {
                neg = -1;
                n = scan();
            }
            while (!isWhiteSpace(n)) {
                if (n >= '0' && n <= '9') {
                    integer *= 10;
                    integer += n - '0';
                    n = scan();
                } else {
                    throw new InputMismatchException();
                }
            }
            return neg * integer;
        }

        public double scanDouble() throws IOException {
            double doub = 0;
            int n = scan();
            while (isWhiteSpace(n)) {
                n = scan();
            }
            int neg = 1;
            if (n == '-') {
                neg = -1;
                n = scan();
            }
            while (!isWhiteSpace(n) && n != '.') {
                if (n >= '0' && n <= '9') {
                    doub *= 10;
                    doub += n - '0';
                    n = scan();
                } else {
                    throw new InputMismatchException();
                }
            }
            if (n == '.') {
                n = scan();
                double temp = 1;
                while (!isWhiteSpace(n)) {
                    if (n >= '0' && n <= '9') {
                        temp /= 10;
                        doub += (n - '0') * temp;
                        n = scan();
                    } else {
                        throw new InputMismatchException();
                    }
                }
            }
            return doub * neg;
        }

        public String scanString() throws IOException {
            StringBuilder sb = new StringBuilder();
            int n = scan();
            while (isWhiteSpace(n)) {
                n = scan();
            }
            while (!isWhiteSpace(n)) {
                sb.append((char) n);
                n = scan();
            }
            return sb.toString();
        }

        private boolean isWhiteSpace(int n) {
            if (n == ' ' || n == '\n' || n == '\r' || n == '\t' || n == -1) {
                return true;
            }
            return false;
        }
    }

    public static void main(String args[]) throws IOException {
        Scan input = new Scan();
        StringBuilder ans=new StringBuilder("");
        int test=input.scanInt();
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            int m=input.scanInt();
            int arr[]=new int[n+1];
            arr[0]++;
            for(int i=0;i<n-1;i++) {
                arr[input.scanInt()]++;
            }
//            System.out.println();
//            for(int i=0;i<n+1;i++) {
//                System.out.print(arr[i]+" ");
//            }
//            System.out.println();
            long cnt=1,mod=1000000007L;
            for(int i=1;i<n+1;i++) {
                if(arr[i]==0) {
                    continue;
                }
                if(arr[i-1]==0) {
                    cnt=0;
                }
                for(int j=0;j<arr[i];j++) {
                    cnt*=arr[i-1];
                    cnt%=mod;
                }
            }
            m-=(n-1);
            long total=0;
            for(int i=1;i<n;i++) {
                total+=((long)arr[i]*(long)(arr[i]-1))/2L;
            }
            if(total<m) {
                cnt=0;
            }
            else {
                cnt*=ncr(total,m,mod);
                cnt%=mod;
            }
            ans.append(cnt+"\n");
        }
        System.out.println(ans);
    }
    public static long ncr(long n,long r,long mod) {
        long ans=1;
        if(r<n-r) {
           for(long i=(n-r+1);i<=n;i++) {
                ans*=i;
                ans%=mod;
            }
            for(int i=1;i<=r;i++) {
                ans*=Inverse(i,mod);
                ans%=mod;
            } 
        }
        else {
            for(long i=(r+1);i<=n;i++) {
                ans*=i;
                ans%=mod;
            }
            for(int i=1;i<=n-r;i++) {
                ans*=Inverse(i,mod);
                ans%=mod;
            }
        }
        return ans;
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
