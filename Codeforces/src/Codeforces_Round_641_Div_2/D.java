/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_641_Div_2;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class D {
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
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            int k=input.scanInt();
            int arr[]=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt();
            }
            if(solve(n,k,arr)) {
                ans.append("yes\n");
            }
            else {
                ans.append("no\n");
            }
        }
        System.out.println(ans);
    }
    public static boolean solve(int n,int k,int arr[]) {
        if(n==1 && arr[0]==k) {
            return true;
        }
        for(int i=0;i<n-1;i++) {
            if(arr[i]==k && arr[i+1]==k) {
                return true;
            }
        }
        int cnt=0,cnt1=0;
        ArrayList<Integer> arrli=new ArrayList<>();
        int prefix[]=new int[n];
        int pre_k[]=new int[n];
        for(int i=0;i<n;i++) {
            if(arr[i]==k) {
                arrli.add(i);
                cnt1++;
            }
            if(arr[i]<k) {
                cnt++;
            }
            prefix[i]=cnt;
            pre_k[i]=cnt1;
        }
//        for(int i=0;i<n;i++) {
//            System.out.print(prefix[i]+" ");
//        }
//        System.out.println();
//        for(int i=0;i<n;i++) {
//            System.out.print(pre_k[i]+" ");
//        }
//        System.out.println();
        if(arrli.size()==0) {
            return false;
        }
        int J=0;
        for(int i=0;i<n;i++) {
            int indx=-1;
            for(;J<arrli.size();J++) {
                if(arrli.get(J)>i) {
                    indx=arrli.get(J);
                    break;
                }
            }
            if(arr[i]==k) {
                indx=i+1;
            }
            if(indx==-1) {
                break;
            }
            for(int j=indx;j<n;j++) {
                cnt=prefix[j]-(i>0?prefix[i-1]:0);
                cnt1=pre_k[j]-(i>0?pre_k[i-1]:0);
                if((j-i+2)/2>cnt && (j-i+2)/2<=cnt1)  {
                    return true;
                }
                if((cnt+cnt1)<(j-i+2)/2 && (j-i+2)/2-(cnt+cnt1)<(n-j)) {
                    break;
                } 
            }
        }
        return false;
    }
} 
