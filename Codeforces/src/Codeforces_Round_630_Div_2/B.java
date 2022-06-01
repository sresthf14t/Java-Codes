/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_630_Div_2;

/**
 *
 * @author User
 */
import java.io.*;
import java.util.*;
import java.io.DataInputStream; 
import java.io.FileInputStream; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.util.Scanner; 
import java.util.StringTokenizer;
public class B {
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
        int color=0;
        int clr[]=new int[10001];
        for(int i=2;i<1001;i++) {
            if(is_prime(i)) {
                color++;
                for(int j=0;i*j<1001;j++) {
                    clr[i*j]=color;
                }
            }
            if(color==11) {
                break;
            }
        }
        int test=input.scanInt();
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            int arr[]=new int[n];
            int clring[]=new int[n];
            boolean is_taken[]=new boolean[12];
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt();
                clring[i]=clr[arr[i]];
                is_taken[clring[i]]=true;
            }
            for(int i=1;i<12;i++) {
                if(is_taken[i]) {
                    continue;
                }
                int indx=-1;
                for(int j=i+1;j<12;j++) {
                    if(is_taken[j]) {
                        indx=j;
                        break;
                    }
                }
                if(indx==-1) {
                    break;
                }
                for(int j=0;j<n;j++) {
                    if(clring[j]==indx) {
                        clring[j]=i;
                    }
                }
                is_taken[indx]=false;
            }
            int max=-1;
            for(int i=0;i<n;i++) {
                max=Math.max(max, clring[i]);
            }
            System.out.println(max);
            for(int i=0;i<n;i++) {
                System.out.print(clring[i]+" ");
            }
            System.out.println();
        }
    }
    
    
    public static int GCD(int a,int n) {
        int q,r1=n,r2=a,r,t1=0,t2=1,t;
        while(true) {
            q=r1/r2;
            r=r1%r2;
            t=t1-(q*t2);
            r1=r2;
            r2=r;
            t1=t2;
            t2=t;
            if(r2==0) {
                    break;
            }
        }
            return r1;
    }
    public static boolean is_prime(int n) {
        for(int i=2;i<n;i++) {
            if(n%i==0) {
                return false;
            }
        }
        return true;
    }
}
