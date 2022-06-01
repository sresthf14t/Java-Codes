/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package May_Lunchtime_2020_Division_2;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class CHEFZRON {
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
    static int n,arr[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int test=input.scanInt();
        for(int t=1;t<=test;t++) {
            n=input.scanInt();
            arr=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt();
            }
            System.out.println(solve(0,false,false));
        }
    }
    public static int solve(int indx,boolean lft,boolean rgt) {
        boolean no_ones=true;
        for(int i=0;i<n;i++) {
            System.out.print(arr[i]+" ");
            if(arr[i]==1) {
                no_ones=false;
            }
        }
        System.out.println();
        if(no_ones) {
            return 0;
        }
        if(arr[indx]!=1) {
            return solve((indx+1)%n,false,false);
        }
        int min=Integer.MAX_VALUE;
        if(arr[indx]==1) {
            if(!rgt) {
                arr[(indx+1)%n]++;
                arr[indx]=0;
                min=Math.min(min,1+solve((indx+1)%n,false,true));
                arr[(indx+1)%n]--;
                arr[indx]=1;
            }
            if(!lft) {
                int n_indx=(indx-1)%n;
                if(n_indx<0) {
                    n_indx+=n;
                }
                arr[indx]=0;
                arr[n_indx]++;
                min=Math.min(min,1+solve((indx+1)%n,true,false));
                arr[n_indx]--;
                arr[indx]=1;
            }
        }
        return min;
    }
}
