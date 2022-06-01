/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_658_Div_2;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class C2 {
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
            StringBuilder str=new StringBuilder(input.scanString());
            StringBuilder fin=new StringBuilder(input.scanString());
            solve(n,str,fin);
        }
        System.out.println(ans);
    }
    public static void solve(int n,StringBuilder str,StringBuilder fin) {
        int cnt=0,flip=0;
        StringBuilder ans=new StringBuilder("");
        int l=0,r=n-1;
        for(int i=n-1;i>0;i--) {
            if(flip%2==0 && str.charAt(r)==fin.charAt(i)) {
                if(flip%2==1) {
                    r++;
                }
                else {
                    r--;
                }
                continue;
            }
            if(flip%2==1 && str.charAt(r)!=fin.charAt(i)) {
                if(flip%2==1) {
                    r++;
                }
                else {
                    r--;
                }
                continue;
            }
            if(str.charAt(l)!=str.charAt(r)) {
                ans.append(1+" ");
                cnt++;
                if(str.charAt(l)=='0') {
                    str.setCharAt(l, '1');
                }
                else {
                    str.setCharAt(l, '0');
                }
            }
            flip++;
            cnt++;
            ans.append((i+1)+" ");
            int tmp=l;
            l=r;
            r=tmp;
            if(flip%2==1) {
                r++;
            }
            else {
                r--;
            }
        }
        if(flip%2==0 && str.charAt(r)!=fin.charAt(0)) {
            cnt++;
            ans.append(1+" ");
        }
        if(flip%2==1 && str.charAt(r)==fin.charAt(0)) {
            cnt++;
            ans.append(1+" ");
        }
        System.out.println(cnt+" "+ans);
    }
}
