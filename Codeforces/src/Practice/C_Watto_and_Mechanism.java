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
public class C_Watto_and_Mechanism {
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
    static long p1,m1,pow1[];
    static long p2,m2,pow2[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        
        p1=131;
        m1=1000000007;
        
        p2=167;
        m2=190000043;
        
        //Calculating power (p^n%)m
        pow1=new long[1000000];
        pow1[0]=1;
        
        pow2=new long[1000000];
        pow2[0]=1;
        for(int i=1;i<pow1.length;i++) {
            pow1[i]=pow1[i-1]*p1;
            pow1[i]%=m1;
            
            pow2[i]=pow2[i-1]*p2;
            pow2[i]%=m2;
        }
        
        int n=input.scanInt();
        int m=input.scanInt();
        Set<Long> hashset1=new HashSet<>();
        Set<Long> hashset2=new HashSet<>();
        for(int i=0;i<n;i++) {
            String tmp=input.scanString();
            hashset1.add(hash1(tmp));
            hashset2.add(hash2(tmp));
        }
        StringBuilder ans=new StringBuilder("");
        for(int i=0;i<m;i++) {
            if(solve(input.scanString(),hashset1,hashset2)) {
                ans.append("YES");
            }
            else {
                ans.append("NO");
            }
            ans.append("\n");
        }
        System.out.println(ans);
    }
    public static boolean solve(String str,Set<Long> hashset1, Set<Long> hashset2) {
        long h1=hash1(str);
        long h2=hash2(str);
        for(int i=0;i<str.length();i++) {
            h1-=(str.charAt(i)*pow1[i])%m1;
            if(h1<0) {
                h1+=m1;
            }
            h1%=m1;
            
            h2-=(str.charAt(i)*pow2[i])%m2;
            if(h2<0) {
                h2+=m2;
            }
            h2%=m2;
            
            
            for(int j='a';j<'z';j++) {
                if(str.charAt(i)==j) {
                    continue;
                }
                h1+=(j*pow1[i])%m1;
                h1%=m1;
                
                h2+=(j*pow2[i])%m2;
                h2%=m2;
                if(hashset1.contains(h1) && hashset2.contains(h2)) {
                    return true;
                }
                h1-=(j*pow1[i])%m1;
                if(h1<0) {
                    h1+=m1;
                }
                h1%=m1;
                
                h2-=(j*pow2[i])%m2;
                if(h2<0) {
                    h2+=m2;
                }
                h2%=m2;
            }
            h1+=(str.charAt(i)*pow1[i])%m1;
            h1%=m1;
            
            h2+=(str.charAt(i)*pow2[i])%m2;
            h2%=m2;
        }
        return false;
    }
    public static long hash1(String str) {
        long hash=0;
        for(int i=0;i<str.length();i++) {
            hash+=(str.charAt(i)*pow1[i])%m1;
            hash%=m1;
        }
        return hash;
    }
    
    public static long hash2(String str) {
        long hash=0;
        for(int i=0;i<str.length();i++) {
            hash+=(str.charAt(i)*pow2[i])%m2;
            hash%=m2;
        }
        return hash;
    }
}
