/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class B_Maximum_Absurdity {
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
        int k=input.scanInt();
        long arr[]=new long[n];
        long prefix[]=new long[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
        }
        solve(n,k,arr);
    }
    public static void solve(int n,int k,long arr[]) {
        long prefix[]=new long[n-k+1];
        long sum=0;
        for(int i=0;i<k;i++) {
            sum+=arr[i];
        }
        prefix[0]=sum;
        for(int i=1;i<n-k+1;i++) {
            sum+=arr[i+k-1];
            sum-=arr[i-1];
            prefix[i]=sum;
        }
        long max_sum[]=new long[n-k+1];
        long max=0;
        for(int i=n-k;i>=0;i--) {
            max=Math.max(max,prefix[i]);
            max_sum[i]=max;
        }
        long ans=0;
        int l=-1,r=-1;
        for(int i=0;i+k<n-k+1;i++) {
//            System.out.println(i+" "+(i+k));
            if(prefix[i]+max_sum[i+k]>ans) {
                ans=prefix[i]+max_sum[i+k];
                l=i;
            }
        }
        for(int i=l+k;i<n-k+1;i++) {
            if(prefix[i]==max_sum[l+k]) {
                r=i;
                break;
            }
        }
//        for(int i=0;i<n-k+1;i++) {
//            System.out.print(prefix[i]+" ");
//        }
//        System.out.println();
//        for(int i=0;i<n-k+1;i++) {
//            System.out.print(max_sum[i]+" ");
//        }
//        System.out.println();
        l++;
        r++;
        System.out.println(l+" "+r);
    }
}
