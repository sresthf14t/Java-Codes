/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Code_Ensemble_Rated_for_Division_2;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class CENS20G {
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
        StringBuilder ans=new StringBuilder("");
        int test=input.scanInt();
        for(int t=1;t<=test;t++) {
            String str=input.scanString();
            int l=0,r=0,d=0,u=0;
            for(int i=0;i<str.length();i++) {
                if(str.charAt(i)=='L') {
                    l++;
                }
                if(str.charAt(i)=='R') {
                    r++;
                }
                if(str.charAt(i)=='D') {
                    d++;
                }
                if(str.charAt(i)=='U') {
                    u++;
                }
            }
            int x1=input.scanInt();
            int y1=input.scanInt();
            int q=input.scanInt();
            for(int Q=0;Q<q;Q++) {
                int x2=input.scanInt();
                int y2=input.scanInt();
                int x=x2-x1;
                int y=y2-y1;
                if(x>0) {
                    x=Math.abs(x);
                    if(r<x) {
                        ans.append("NO\n");
                        continue;
                    }
                }
                else {
                    x=Math.abs(x);
                    if(l<x) {
                        ans.append("NO\n");
                        continue;
                    }
                }
                
                if(y>0) {
                    y=Math.abs(y);
                    if(u<y) {
                        ans.append("NO\n");
                        continue;
                    }
                }
                else {
                    y=Math.abs(y);
                    if(d<y) {
                        ans.append("NO\n");
                        continue;
                    }
                }
                ans.append("YES ");
                ans.append((Math.abs(x) + Math.abs(y)));
                ans.append("\n");
            }
        }
        System.out.println(ans);
    }
}
