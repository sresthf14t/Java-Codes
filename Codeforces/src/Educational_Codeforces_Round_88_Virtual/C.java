/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Educational_Codeforces_Round_88_Virtual;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
import java.math.*;
public class C {
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
            int hot=input.scanInt();
            int cold=input.scanInt();
            int target=input.scanInt();
            ans.append(solve(hot,cold,target)+"\n");
        }
        System.out.println(ans);
    }
    public static int solve(int  hot,int  cold,int target) {
        BigDecimal h=new BigDecimal(""+hot);
        BigDecimal c=new BigDecimal(""+cold);
        BigDecimal t=new BigDecimal(""+target);
//        System.out.println(h+" "+c+" "+t);
        BigDecimal min=(h.add(c)).divide(new BigDecimal("2"),MathContext.DECIMAL128);
        min=min.subtract(t);
        min=min.abs();
        int times=2;
        if(min.compareTo(new BigDecimal("0"))==0) {
            return times;
        }
        int k=((c.subtract(t)).divide((h.add(c)).subtract(t.multiply(new BigDecimal("2"))),MathContext.DECIMAL128)).intValue();
        k--;
        BigDecimal tmp=((new BigDecimal(""+k).multiply(h)).add(new BigDecimal(""+(k-1)).multiply(c))).divide(new BigDecimal(""+(k+k-1)),MathContext.DECIMAL128);
        tmp=tmp.subtract(t);
        tmp=tmp.abs();
        if(min.compareTo(tmp)==1 && (k+k-1)>0) {
            min=new BigDecimal(""+tmp);
            times=k+k-1;
        }
        
        k++;
        tmp=((new BigDecimal(""+k).multiply(h)).add(new BigDecimal(""+(k-1)).multiply(c))).divide(new BigDecimal(""+(k+k-1)),MathContext.DECIMAL128);
        tmp=tmp.subtract(t);
        tmp=tmp.abs();
        if(min.compareTo(tmp)==1 && (k+k-1)>0) {
            min=new BigDecimal(""+tmp);
            times=k+k-1;
        }
        
        k++;
        tmp=((new BigDecimal(""+k).multiply(h)).add(new BigDecimal(""+(k-1)).multiply(c))).divide(new BigDecimal(""+(k+k-1)),MathContext.DECIMAL128);
        tmp=tmp.subtract(t);
        tmp=tmp.abs();
//        System.out.println(min+" "+tmp1);
        if(min.compareTo(tmp)==1 && (k+k-1)>0) {
            times=k+k-1;
        }
        return times;
    }
}
