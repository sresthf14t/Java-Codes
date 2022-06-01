/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Educational_Codeforces_Round_94_Rated_for_Div_2;

/**
 *
 * @author Srest
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
        Scan input=new Scan();
        int test=input.scanInt();
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            long p=input.scanInt();
            long f=input.scanInt();
            long cnts=input.scanInt();
            long cntw=input.scanInt();
            long s=input.scanInt();
            long w=input.scanInt();
            long P=p;
            long F=f;
            
            if(s>w) {
                long tmp=s;
                s=w;
                w=tmp;
                tmp=cnts;
                cnts=cntw;
                cntw=tmp;
            }
            
            long tmps=cnts;
            long tmpw=cntw;
            
            
            long fin=0;
            long takes=p/s;
            if(takes>cnts) {
                takes=cnts;
            }
            fin+=takes;
            p-=(takes*s);
            cnts-=takes;
            long takew=p/w;
            takew=Math.min(takew,cntw);
            cntw-=takew;
            fin+=takew;

            takes=f/s;
            if(takes>cnts) {
                takes=cnts;
            }
            fin+=takes;
            f-=(takes*s);
            cnts-=takes;
            takew=f/w;
            takew=Math.min(takew,cntw);
            cntw-=takew;
            fin+=takew;
            
            
            long fin1=0;
            fin1=solve(tmps,tmpw,s,w,P,F);
            ans.append(Math.max(fin,fin1)+"\n");
        }
        
        
        
        System.out.println(ans);
    }
    public static long solve(long cnts,long cntw,long s,long w,long p,long f) {
        long max=0;
        for(int i=0;i<cnts;i++) {
            long rem1=(p-(i*s));
            long rem2=(f-((cnts-i)*s));
            if(rem1<0 || rem2<0) {
                continue;
            }
            long w1=rem1/w;
            w1=Math.min(w1,cntw);
            long remw=cntw-w1;
            long w2=rem2/w;
            w2=Math.min(w2,remw);
            max=Math.max(max,(cnts+w1+w2));
        }
        return max;
    }
}
