/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class B_Password {
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
    static long pow[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        String str=input.scanString();
        System.out.println(solve(str));
    }
    public static String solve(String str) {
        long p=131;
        long m=1000000007;
        
        //Calculating power (p^n%)m
        pow=new long[1000000];
        pow[0]=1;
        for(int i=1;i<pow.length;i++) {
            pow[i]=pow[i-1]*p;
            pow[i]%=m;
        }
        int indx=-1;
        long prefix_hash=0,suffix_hash=0;
        for(int i=0;i<str.length();i++) {
            prefix_hash+=str.charAt(i)*pow[i];
            prefix_hash%=m;
            
            suffix_hash*=p;
            suffix_hash%=m;
            suffix_hash+=str.charAt(str.length()-i-1);
            
            if(prefix_hash==suffix_hash && (true || compare(str,0,str.length()-i-1,i+1))) {
                if(Rabin_Karp(str,str,i+1,prefix_hash)) {
                    indx=i;
                }
            }
        }
        if(indx==-1) {
            return "Just a legend";
        }
        return str.substring(0, indx+1);
    }
    public static boolean compare(String str,int strt1,int strt2, int len) {
        for(int i=0;i<len;i++) {
            if(str.charAt(i+strt1)!=str.charAt(i+strt2)) {
                return false;
            }
        }
        return true;
    }
    
    
    
    
    public static boolean Rabin_Karp(String s,String t,int len,long t_hash) {   //|t|>=|s|
        long p=131;
        long m=1000000007;
        long s_hash=t_hash;
        long p_inv=Inverse(p,m);
        
//        long t_hash=0;
//        for(int i=0;i<len;i++) {
//            t_hash+=(t.charAt(i)*pow[i])%m;
//            t_hash%=m;
//        }
        for(int i=len;i<t.length()-1;i++) {
            t_hash-=t.charAt(i-len);
            t_hash*=p_inv;
            t_hash%=m;
            t_hash+=(t.charAt(i)*pow[len-1])%m;
            t_hash%=m;
//            System.out.println(i+" "+s_hash+" "+t_hash);
            if(t_hash==s_hash) {
                if(true || check(s,t,i,len)) {
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean check(String s,String t,int end,int len) {
        int strt=end-len+1;
        for(int i=0;i<len;i++) {
            if(s.charAt(i)!=t.charAt(i+strt)) {
                return false;
            }
        }
        return true;
    }
    
    
    public static long Inverse(long a,long n) {
        long q,r1=n,r2=a,r,t1=0,t2=1,t;
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
        if(r1!=1) {
                return -1;
        }
        t1%=n;
        if(t1<0) {
                t1+=n;
        }
        return t1;
    }
}
