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
public class D_Long_Jumps {
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
        int l=input.scanInt();
        int x=input.scanInt();
        int y=input.scanInt();
        int arr[]=new int[n];
        Set<Integer> hashset=new HashSet<>();
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
            hashset.add(arr[i]);
        }
        solve(n,l,x,y,arr,hashset);
    }
    public static void solve(int n,int l,int x,int y,int arr[],Set<Integer> hashset) {
        boolean b1=false,b2=false;
        for(int i=0;i<n;i++) {
            if(hashset.contains(arr[i]+x)) {
                b1=true;
            }
            if(hashset.contains(arr[i]+y)) {
                b2=true;
            }
            if(b1 && b2) {
                System.out.println(0);
                return;
            } 
        }
        if(b1) {
            System.out.println(1+"\n"+y);
            return;
        }
        if(b2) {
            System.out.println(1+"\n"+x);
            return;
        }
        for(int i=0;i<n;i++) {
            if(hashset.contains(arr[i]+x) && arr[i]+y<=l) {
                System.out.println(1+"\n"+(arr[i]+y));
                return;
            }
            if(hashset.contains(arr[i]+y) && arr[i]+x<=l) {
                System.out.println(1+"\n"+(arr[i]+x));
                return;
            }
        }
        Set<Integer> hashset1=new HashSet<>();
        for(int i=0;i<n;i++) {
            if(arr[i]+x<=l) {
                hashset1.add(arr[i]+x);
            }
            if(arr[i]-x>0) {
                hashset1.add(arr[i]-x);
            }
        }
        for(int i=0;i<n;i++) {
            if(hashset1.contains(arr[i]+y)) {
                System.out.println(1+"\n"+(arr[i]+y));
                return;
            }
            if(hashset1.contains(arr[i]-y)) {
                System.out.println(1+"\n"+(arr[i]-y));
                return;
            }
        }

        hashset1=new HashSet<>();
        for(int i=0;i<n;i++) {
            if(arr[i]+y<=l) {
                hashset1.add(arr[i]+y);
            }
            if(arr[i]-y>0) {
                hashset1.add(arr[i]-y);
            }
        }
        for(int i=0;i<n;i++) {
            if(hashset1.contains(arr[i]+x)) {
                System.out.println(1+"\n"+(arr[i]+x));
                return;
            }
            if(hashset1.contains(arr[i]-x)) {
                System.out.println(1+"\n"+(arr[i]-x));
                return;
            }
        }
        System.out.println(2+"\n"+x+" "+y);
    }
}
