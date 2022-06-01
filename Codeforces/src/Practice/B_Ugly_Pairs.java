/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class B_Ugly_Pairs {
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
        for(int tt=0;tt<test;tt++) {
            String str=input.scanString();
            solve(str);
        }
    }
    public static void solve(String str) {
        int chr[]=new int[26];
        for(int i=0;i<str.length();i++) {
            chr[str.charAt(i)-97]++;
        }
        StringBuilder str1=new StringBuilder("");
        StringBuilder str2=new StringBuilder("");
        for(int i=0;i<chr.length;i++) {
            for(int j=0;j<chr[i];j++) {
                if(i%2==0) {
                    str1.append((char)(97+i));
                }
                else {
                    str2.append((char)(97+i));
                }
            }
        }
        if(check(""+str1+str2)) {
            System.out.println(""+str1+str2);
            return;
        }
        str1=str1.reverse();
        if(check(""+str1+str2)) {
            System.out.println(""+str1+str2);
            return;
        }
        str1=str1.reverse();
        str2=str2.reverse();
        if(check(""+str1+str2)) {
            System.out.println(""+str1+str2);
            return;
        }
        str1=str1.reverse();
        if(check(""+str1+str2)) {
            System.out.println(""+str1+str2);
            return;
        }
        System.out.println("No answer");
    }
    public static boolean check(String str) {
        for(int i=0;i<str.length()-1;i++) {
            if(Math.abs(str.charAt(i)-str.charAt(i+1))==1) {
                return false;
            }
        }
        return true;
    }
}
