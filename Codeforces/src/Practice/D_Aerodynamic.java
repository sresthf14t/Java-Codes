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
import java.math.BigDecimal;
import java.math.MathContext;
public class D_Aerodynamic {
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
        int x[]=new int[n];
        int y[]=new int[n];
        for(int i=0;i<n;i++) {
            x[i]=input.scanInt();
            y[i]=input.scanInt();
        }
        if(n%2!=0) {
            System.out.println("NO");
            return;
        }
        HashSet<String> pos=new HashSet<>();
        HashSet<String> neg=new HashSet<>();
        for(int i=0;i<n;i++) {
            int num=x[i]-x[(i+1)%n];
            int den=y[i]-y[(i+1)%n];
            System.out.println(num+" "+den);
            if(den==0) {
                if(pos.contains("-1")) {
                    pos.remove("-1");
                }
                else {
                    pos.add("-1");
                }
                continue;
            }
            if(num==0) {
                if(pos.contains("0")) {
                    pos.remove("0");
                }
                else {
                    pos.add("0");
                }
                continue;
            }
            boolean is_pos;
            if(num<0 && den<0) {
                is_pos=true;
            }
            else if(num<0 || den<0) {
                is_pos=false;
            }
            else {
                is_pos=true;
            }
            num=Math.abs(num);
            den=Math.abs(den);
            while(true) {
                int gcd=gcd(num,den);
                if(gcd==1) {
                    break;
                }
                else {
                    num/=gcd;
                    den/=gcd;
                }
            }
            String str=num+" "+den;
            if(is_pos) {
                if(pos.contains(str)) {
                    pos.remove(str);
                }
                else {
                    pos.add(str);
                }
            }
            else {
                if(neg.contains(str)) {
                    neg.remove(str);
                }
                else {
                    neg.add(str);
                }
            }
        }
        if(pos.size()==0 && neg.size()==0) {
            System.out.println("YES");
        }
        else {
            System.out.println("NO");
        }
    }
    
    public static int gcd(int a,int n) {
        int q,r1=n,r2=a,r;
        while(true) {
            q=r1/r2;
            r=r1%r2;
            r1=r2;
            r2=r;
            if(r2==0) {
                break;
            }
        }
        return r1;
    }
}
