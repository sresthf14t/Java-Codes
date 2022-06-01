/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Introductory_Problems;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class Two_Sets {
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
        if(n%2==0) {
            if(n%4==0) {
                StringBuilder ans1=new StringBuilder("");
                StringBuilder ans2=new StringBuilder("");
                ans1.append(n/2+"\n");
                ans2.append(n/2+"\n");
                for(int i=1,j=n;i<j;i++,j--) {
                    if(i%2==1) {
                        ans1.append(i+" "+j+" ");
                    }
                    else {
                        ans2.append(i+" "+j+" ");
                    }
                }
                System.out.println("YES\n"+ans1+"\n"+ans2);
            }
            else {
                System.out.println("NO");
            }
        }
        else {
            if(((n-1)/2)%2==1) {
                StringBuilder ans1=new StringBuilder("");
                StringBuilder ans2=new StringBuilder("");
                int tmp=(n-1)/2;
                tmp/=2;
                tmp++;
                ans1.append((2*tmp)+"\n");
                ans2.append(2*(tmp-1)+1+"\n");
                for(int i=1,j=n-1;i<j;i++,j--) {
                    if(i%2==1) {
                        ans1.append(i+" "+j+" ");
                    }
                    else {
                        ans2.append(i+" "+j+" ");
                    }
                }
                ans2.append(n);
                System.out.println("YES\n"+ans1+"\n"+ans2);
            }
            else {
                System.out.println("NO");
            }
        } 
    }
}
