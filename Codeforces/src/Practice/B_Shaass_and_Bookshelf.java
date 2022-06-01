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
public class B_Shaass_and_Bookshelf {
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
        int x[]=new int[n];
        int y[]=new int[n];
        for(int i=0;i<n;i++) {
            x[i]=input.scanInt();
            y[i]=input.scanInt();
        }
        System.out.println(solve(n,x,y));
    }
    public static int solve(int n,int x[],int y[]) {
        sort(n,y,x);
        for(int i=0;i<n;i++) {
            int j=i;
            while(j<n && x[j]==x[i]) {
                j++;
            }
            Arrays.sort(x,i,j);
            j--;
            i=j;
        }
        int prefix[]=new int[n];
        prefix[n-1]=x[n-1];
        for(int i=n-2;i>=0;i--) {
            prefix[i]=prefix[i+1]+x[i];
        }
//        for(int i=0;i<n;i++) {
//            System.out.println(prefix[i]+" "+y[i]);
//        }
        int sum=0;
        for(int i=0;i<n-1;i++) {
            sum+=y[i];
            if(sum>prefix[i+1]) {
                return prefix[i];
            }
        }
        return prefix[n-1];
    }
    public static void sort(int n,int arr[],int brr[]) {
        for(int i=0;i<n;i++) {
            for(int j=0;j<n-1;j++) {
                if(arr[j]>arr[j+1]) {
                    swap(arr,j,j+1);
                    swap(brr,j,j+1);
                }
            }
        }
    }
    public static void swap(int arr[],int l,int r) {
        int tmp=arr[l];
        arr[l]=arr[r];
        arr[r]=tmp;
    }
}
