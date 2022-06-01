/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tokio_Marine_Nichido_Fire_Insurance_Programming_Contest_2020;

/**
 *
 * @author User
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
        int k=input.scanInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
        }
        solve(n,arr,k);
        StringBuilder ans=new StringBuilder("");
        for(int i=0;i<n;i++) {
            ans.append(arr[i]+" ");
        }
        System.out.println(ans);
    }
    public static void solve(int n,int arr[],int k) {
        for(int i=0;i<k;i++) {
//            System.out.println(i);
            int brr[]=new int[n];
            int update[]=new int[n];
            int up=0;
            for(int j=0;j<n;j++) {
                if(j+arr[j]<n) {
                     update[j+arr[j]]+=-1;
                }
            }
            for(int j=0;j<n;j++) {
                up++;
                brr[j]+=up;
                up+=update[j];
                
            }
            
            up=0;
            update=new int[n];
            for(int j=n-1;j>=0;j--) {
                if(j-arr[j]>=0) {
                     update[j-arr[j]]+=-1;
                }
            }
            for(int j=n-1;j>=0;j--) {
                up++;
                brr[j]+=up; 
                up+=update[j];
            }
            
            for(int j=0;j<n;j++) {
                arr[j]=brr[j]-1;
            }
            boolean all_n=true;
            for(int j=0;j<n;j++) {
                if(arr[j]!=n) {
                    all_n=false;
                    break;
                }
            }
            if(all_n) {
                break;
            }
        }
    }
}
