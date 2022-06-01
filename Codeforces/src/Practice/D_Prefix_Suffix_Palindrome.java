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
public class D_Prefix_Suffix_Palindrome {
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
    static long p1,p_inv1,m1;
    static long p2,p_inv2,m2;
    static long pow1[], pow2[];
    public static void main(String args[]) throws IOException {
        Scanner input=new Scanner(System.in);
        
        p1=131;
        m1=951231973;
        p_inv1=Inverse(p1,m1);
        
        p2=167;
        m2=1000000007;
        p_inv2=Inverse(p2,m2);
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
        
        int test=input.nextInt();
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            String str=input.next();
            ans.append(solve(str.length(),str)+"\n");
        }
        System.out.println(ans);
    }
    public static String solve(int n,String str) {

        StringBuilder tmp=new StringBuilder(""+str);
        tmp=tmp.reverse();
        
        int len=0;
        for(int i=0,j=n-1;i<j;i++,j--) {
            if(str.charAt(i)!=str.charAt(j)) {
                break;
            }
            len=(i+1);
        }
        if(len==n) {
            return str;
        }
        int l1=longest_pal_prefix(str,len,n-len-1);
        int l2=longest_pal_prefix(""+tmp,len,n-len-1);
        StringBuilder ans=new StringBuilder("");
        for(int i=0;i<len;i++) {
            ans.append(str.charAt(i));
        }
        if(l1!=0 && l1>=l2) {
            for(int i=len;i<len+l1;i++) {
                ans.append(str.charAt(i));
            }
        }
        else if(l2!=0 && l2>l1){
            for(int i=n-len-l2;i<n-len;i++) {
                ans.append(str.charAt(i));
            }
        }
        for(int i=n-len;i<n;i++) {
            ans.append(str.charAt(i));
        }
//        System.out.println(ans);
        return ""+ans;
//        System.out.println(longest_pal_prefix+" "+longest_pal_suffix+" "+len);
    }
    public static int longest_pal_prefix(String str,int l,int r) {
        long hash1=0,rev_hash1=0;
        long hash2=0,rev_hash2=0;
        int max=0;
        for(int i=l,j=0;i<=r;i++,j++) {
            hash1+=(str.charAt(i)*pow1[j])%m1;
            hash1%=m1;
            rev_hash1*=p1;
            rev_hash1%=m1;
            rev_hash1+=str.charAt(i);
            rev_hash1%=m1;
            
            
            hash2+=(str.charAt(i)*pow2[j])%m2;
            hash2%=m2;
            rev_hash2*=p2;
            rev_hash2%=m2;
            rev_hash2+=str.charAt(i);
            rev_hash2%=m2;
            
            
            if(hash1==rev_hash1 && hash2==rev_hash2) {
                max=j+1;
            }
        }
//        System.out.println(l+" "+r+" "+max);
        return max;
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
