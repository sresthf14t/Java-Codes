/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_653_Div_3;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class F {
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
            int arr[]=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt();
            }
            solve(n,arr);
        }
    }
    static StringBuilder tmp;
    public static void solve(int n,int arr[]) {
        int cpy[]=new int[n];
        for(int i=0;i<n;i++) {
            cpy[i]=arr[i];
        }
        Arrays.sort(cpy);
        StringBuilder ans=new StringBuilder("");
        int cnt=0;
        for(int i=n-1;i>=3;i--) {
            if(arr[i]==cpy[i]) {
                continue;
            }
            tmp=new StringBuilder("");
            cnt+=sort(cpy[i],n,arr,i);
            ans.append(tmp);
        }
        boolean is_pos=true;
        for(int i=0;i<=3;i++) {
            is_pos=true;
            for(int j=0;j<3;j++) {
                if(arr[j]==cpy[j]) {
                    continue;
                }
                is_pos=false;
                break;
            }
            if(is_pos) {
                break;
            }
            cnt++;
            ans.append(1+" ");
            r_shft(0,arr);
        }
//        for(int i=0;i<n;i++) {
//            System.out.print(arr[i]+" ");
//        }
//        System.out.println();
        if(cnt<=n*n && is_pos) {
            System.out.println(cnt+"\n"+ans);
        }
        else {
            System.out.println(-1);
        }
    }
    public static int sort(int val,int n,int arr[],int tar_indx) {
        int indx=-1;
        for(int i=0;i<n;i++) {
            if(arr[i]==val) {
                indx=i;
                break;
            }
        }
        int cnt=0;
        for(int i=indx;i<=n;i++) {
            if(arr[tar_indx]==val) {
                break;
            }
            
            cnt++;
            if(i<=tar_indx-2) {
                r_shft(i,arr);
                tmp.append((i+1)+" ");
            }
            else {
                r_shft(tar_indx-2,arr);
                tmp.append((tar_indx-2+1)+" ");
            }
        }
        return cnt;
    }
    public static void r_shft(int indx,int arr[]) {
        int tmp=arr[indx+2];
        arr[indx+2]=arr[indx+1];
        arr[indx+1]=arr[indx];
        arr[indx]=tmp;
    }
}
