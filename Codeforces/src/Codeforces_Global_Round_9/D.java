/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Global_Round_9;

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
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            int arr[]=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt();
            }
            solve(n,arr);
        }
    }
    public static void solve(int n,int arr[] ) {
        ArrayList<Integer> arrli=new ArrayList<>();
        while(!check(n,arr)) {
            int mex=find_mex(n,arr);
            if(mex!=0) {
                arr[mex-1]=mex;
                arrli.add(mex-1+1);
            }
            else {
                for(int i=0;i<n;i++) {
                    if(arr[i]!=i+1) {
                        arr[i]=mex;
                        arrli.add(i+1);
                        break;
                    }
                }
            }
        }
        System.out.println(arrli.size());
        for(int i=0;i<arrli.size();i++) {
            System.out.print(arrli.get(i)+" ");
        }
        System.out.println();
    }
    public static int find_mex(int n,int arr[]) {
        Set<Integer> hashset=new HashSet<>();
        for(int i=0;i<n;i++) {
            hashset.add(arr[i]);
        }
        int mex=0;
        while(hashset.contains(mex)) {
            mex++;
        }
        return mex;
    }
    public static boolean check(int n,int arr[]) {
        for(int i=0;i<n;i++) {
            if(arr[i]!=i+1) {
                return false;
            }
        }
        return true;
    }
}
