/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AIsing_Programming_Contest_2020;

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
        int n=input.scanInt();
        String str=input.scanString();
        int cnt=0;
        for(int i=0;i<n;i++) {
            if(str.charAt(i)=='1') {
                cnt++;
            }
        }
        int pow0[]=new int[n];
        if(cnt!=1) {
           pow0[0]=1;
            for(int i=1;i<n;i++) {
                pow0[i]=2*pow0[i-1];
                pow0[i]%=(cnt-1);
            } 
        }
        
        int pow1[]=new int[n];
        pow1[0]=1;
        for(int i=1;i<n;i++) {
            pow1[i]=2*pow1[i-1];
            pow1[i]%=(cnt+1);
        }
        int num0=0,num1=0;
        if(cnt!=1) {
            for(int i=0;i<n;i++) {
                if(str.charAt(i)=='1') {
                    num0+=pow0[n-i-1];
                    num0%=(cnt-1);
                }
            }
        }
        for(int i=0;i<n;i++) {
            if(str.charAt(i)=='1') {
                num1+=pow1[n-i-1];
                num1%=(cnt+1);
            }
        }
        StringBuilder ans=new StringBuilder("");
        for(int i=0;i<n;i++) {
            if(str.charAt(i)=='1') {
                if(cnt==1) {
                    ans.append("0\n");
                    continue;
                }
                num0-=pow0[n-i-1];
                if(num0<0) {
                    num0+=(cnt-1);
                }
                ans.append((calc(num0)+1)+"\n");
                num0+=pow0[n-i-1];
                num0%=(cnt-1);
            }
            else {
                num1+=pow1[n-i-1];
                num1%=(cnt+1);
                ans.append((calc(num1)+1)+"\n");
                num1-=pow1[n-i-1];
                if(num1<0) {
                    num1+=(cnt+1);
                }
            }
        }
        System.out.println(ans);
    }
    public static int calc(int n) {
        int cnt=0;
        while(n!=0) {
            int tmp=n;
            int ones=0;
            while(tmp!=0) {
               ones+=tmp%2;
               tmp/=2;
            }
            n%=ones;
            cnt++;
        }
        return cnt;
    }
}
