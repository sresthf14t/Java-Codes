/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_657_Div_2;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
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
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            long l=input.nextInt();
            long r=input.nextInt();
            long m=input.nextLong();
            solve(l,r,m);
        }
        System.out.println(ans);
    }
    public static void solve(long l,long r,long m) {
        long lft=(l-r+m);
        long rgt=(r-l+m);
        long a=-1,n=-1;
        for(long i=l;i<=r;i++) {
            long tmp=(lft)/i;
//            System.out.println(i+" "+tmp);
            if(tmp!=0 && tmp*i>=lft && tmp*i<=rgt) {
                n=tmp;
                a=i;
                break;
            }
            tmp++;
            if(tmp!=0 && tmp*i>=lft && tmp*i<=rgt) {
                n=tmp;
                a=i;
                break;
            }
        }
        long b=-1,c=-1;
        for(long i=l;i<=r;i++) {
            long tmp=m-(n*a)+i;
            if(tmp>=l && tmp<=r) {
                b=tmp;
                c=i;
                break;
            }
        }
        System.out.println(a+" "+b+" "+c);
    }
}
