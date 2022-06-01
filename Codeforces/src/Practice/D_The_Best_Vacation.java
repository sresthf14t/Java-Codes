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
public class D_The_Best_Vacation {
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
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        long x=input.nextLong();
        long arr[]=new long[2*n-1];
        for(int i=0;i<n;i++) {
            arr[i]=input.nextInt();
        }
        for(int j=n;j<arr.length;j++) {
            arr[j]=arr[j-n];
        }
        long ans=solve(n,arr,x);
        ans=Math.max(ans,solve1(n,arr,x));
        System.out.println(ans);
    }
    public static long solve(int n,long arr[],long x) {
        long cnt_prefix[]=new long[arr.length];
        cnt_prefix[0]=arr[0];
        long prefix[]=new long[arr.length];
        prefix[0]=(arr[0]*(arr[0]+1))/2;
        for(int i=1;i<arr.length;i++) {
            cnt_prefix[i]=arr[i]+cnt_prefix[i-1];
            prefix[i]=(prefix[i-1])+(arr[i]*(arr[i]+1))/2;
        }
        long ans=0;
        for(int i=0;i<arr.length;i++) {
            int indx=jgt(x,i,cnt_prefix);
            if(indx==-1) {
                continue;
            }
            long total=get(cnt_prefix,i,indx-1);
            total-=x;
            total=Math.abs(total);
            ans=Math.max(ans,get(prefix,i,total==0?indx:indx-1)+(total*(total+1))/2);
        }
//        System.out.println(ans);
        return ans;
    }
    public static long solve1(int n,long arr[],long x) {
        long cnt_prefix[]=new long[arr.length];
        cnt_prefix[0]=arr[0];
        long prefix[]=new long[arr.length];
        prefix[0]=(arr[0]*(arr[0]+1))/2;
        for(int i=1;i<arr.length;i++) {
            cnt_prefix[i]=arr[i]+cnt_prefix[i-1];
            prefix[i]=(prefix[i-1])+(arr[i]*(arr[i]+1))/2;
        }
        long ans=0;
        for(int i=0;i<arr.length;i++) {
            int indx=jgt1(x,i,cnt_prefix);
            if(indx==-1) {
                continue;
            }
            long total=get(cnt_prefix,indx+1,i);
            total-=x;
            total=Math.abs(total);
            long a0=arr[indx]-total+1;
//            System.out.println(i+" "+indx+" "+total);
            ans=Math.max(ans,get(prefix,total==0?indx:indx+1,i)+(total*(2*a0+(total-1)))/2);
//            System.out.println(i+" "+indx+" "+total+" "+ans);
        }
//        System.out.println(ans);
        return ans;
    }
    public static long get(long arr[],int l,int r) {
        if(l>r) {
            return 0;
        }
        return arr[r]-(l==0?0:arr[l-1]);
    }
    public static int jgt(long x,int strt,long arr[]) {
        int ans=-1,l=strt,r=arr.length-1;
        while(l<=r) {
            int mid=(l+r)/2;
            long total=get(arr,strt,mid);
            if(total<x) {
                l=mid+1;
            }
            else {
                ans=mid;
                r=mid-1;
            }
        }
        return ans;
    }
    public static int jgt1(long x,int strt,long arr[]) {
        int ans=-1,l=0,r=strt;
        while(l<=r) {
            int mid=(l+r)/2;
            long total=get(arr,mid,strt);
            if(total<x) {
                r=mid-1;
            }
            else {
                ans=mid;
                l=mid+1;
            }
        }
        return ans;
    }
}
