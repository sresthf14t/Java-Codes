/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sorting_and_Searching;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class Sum_of_Three_Values {
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
        long x=input.scanInt();
        long arr[]=new long[n];
        long index[]=new long[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
            index[i]=i;
        }
        sort(n,arr,index);
        for(int i=2;i<n;i++) {
            for(int j=i-1;j>0;j--) {
                int indx=search(0,j-1,arr,x-arr[i]-arr[j]);
                if(indx!=-1) {
                    i=(int)index[i]+1;
                    j=(int)index[j]+1;
                    indx=(int)index[indx]+1;
                    System.out.println(indx+" "+j+" "+i);
                    return;
                }
            }
        }
        System.out.println("IMPOSSIBLE");
    }
    public static void sort(int n,long arr[],long brr[]) {
        for(int i=0;i<n;i++) {
            for(int j=0;j<n-1;j++) {
                if(arr[j]>arr[j+1]) {
                    swap(j,j+1,arr);
                    swap(j,j+1,brr);
                }
            }
        }
    }
    public static void swap(int i,int j,long arr[]) {
        long tmp=arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }
    public static int search(int l,int r,long arr[],long val) {
        int ans=-1;
        while(l<=r) {
            int mid=(l+r)/2;
            if(arr[mid]==val) {
                return mid;
            }
            if(arr[mid]<val) {
                l=mid+1;
            }
            else {
                r=mid-1;
            }
        }
        return -1;
    }
}
