/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Microsoft_Practice;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class Trapping_Rain_Water {
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
        int test=input.scanInt();
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            long arr[]=new long[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt();
            }
            System.out.println(solve(n,arr));
        }
    }
    public static long solve(int n,long arr[]) {
        int max_lft[]=new int[n];
        int max_rgt[]=new int[n];
        long max=-1;
        int max_indx=-1;
        for(int i=0;i<n;i++) {
            if(arr[i]>=max) {
                max=arr[i];
                max_indx=i;
            }
            max_lft[i]=max_indx;
        }
        max=-1;
        max_indx=-1;
        for(int i=n-1;i>=0;i--) {
            if(arr[i]>=max) {
                max=arr[i];
                max_indx=i;
            }
            max_rgt[i]=max_indx;
        }
        max=-1;
        max_indx=-1;
        for(int i=0;i<n;i++) {
            if(arr[i]>=max) {
                max=arr[i];
                max_indx=i;
            }
        }
        long ans=0;
        //left
        int indx=max_indx;
//        System.out.println(max_indx);
        while(indx!=0) {
            int sec_max_indx=max_lft[indx-1];
//            System.out.println(sec_max_indx);
            ans+=calc(arr,indx,sec_max_indx);
            indx=sec_max_indx;
        }
        //Right
        indx=max_indx;
        while(indx!=n-1) {
            int sec_max_indx=max_rgt[indx+1];
            ans+=calc(arr,sec_max_indx,indx);
            indx=sec_max_indx;
        }
        return ans;
    }
    public static long calc(long arr[],int indx1,int indx2) {
        long ans=Math.min(arr[indx1], arr[indx2])*(indx1-indx2-1);
        for(int i=indx2+1;i<indx1;i++) {
            ans-=arr[i];
        }
        return ans;
    }
}
