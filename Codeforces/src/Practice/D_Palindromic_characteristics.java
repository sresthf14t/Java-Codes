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
public class D_Palindromic_characteristics {
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
    static long p,m,pow[];
    static int hash[][],rev_hash[][];
    static int dp[][];
    static boolean is_pal[][];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        
        p=131;
        m=1000000007;
        
        //Calculating power (p^n%)m
        pow=new long[10000];
        pow[0]=1;
        for(int i=1;i<pow.length;i++) {
            pow[i]=pow[i-1]*p;
            pow[i]%=m;
        }
        
        String str=input.scanString();
        hash=new int[str.length()][str.length()];
        rev_hash=new int[str.length()][str.length()];
        for(int i=0;i<str.length();i++) {
            long h=0;
            for(int j=i;j<str.length();j++) {
                h+=(str.charAt(j)*pow[j-i])%m;
                h%=m;
                hash[i][j]=(int)h;
            }
        }
        for(int i=0;i<str.length();i++) {
            long h=0;
            int cnt=0;
            for(int j=i;j>=0;j--,cnt++) {
                h+=(str.charAt(j)*pow[cnt])%m;
                h%=m;
                rev_hash[j][i]=(int)h;
            }
        }
//        for(int i=0;i<str.length();i++) {
//            for(int j=i;j<str.length();j++) {
//                System.out.println(str.subSequence(i, j+1)+" "+hash[i][j]+" "+rev_hash[i][j]);
//            }
//        }
        is_pal=new boolean[str.length()][str.length()];
        for(int i=0;i<str.length();i++) {
            for(int j=0;j<str.length();j++) {
                if(hash[i][j]==rev_hash[i][j]) {
                    is_pal[i][j]=true;
                }
            }
        }
        rev_hash=null;
        solve(str.length(),str);
    }
    public static void solve(int n, String str) {
        dp=new int[n][n];
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                dp[i][j]=-1;
            }
        }
        int cnt[]=new int[n+1];
        for(int i=0;i<n;i++) {
            for(int j=i;j<n;j++) {
                if(i==j) {
                    cnt[1]++;
                    continue;
                }
                else if(is_pal[i][j]) {
//                    System.out.println(i+" "+j+" "+check(str,i,j));
                    cnt[check(str,i,j)+1]++;
                }
                
            }
        }
        StringBuilder ans=new StringBuilder("");
        int total=0;
        for(int i=n-1;i>=1;i--) {
            cnt[i]+=cnt[i+1];
        }
        for(int i=1;i<=n;i++) {
            ans.append(cnt[i]+" ");
        }
        System.out.println(ans);
    }
    public static int check(String str,int l,int r) {
//        System.out.println(l+" "+r);
        if(dp[l][r]!=-1) {
            return dp[l][r];
        }
        if(l==r) {
            dp[l][r]=0;
            return 0;
        }
        int len=(r-l+1)/2;
        int l1=l;
        int r1=l+len-1;
        int l2;
        if((r-l+1)%2==0) {
            l2=r1+1;
        }
        else {
            l2=r1+2;
        }
        int r2=r;
        if(hash[l1][r1]==hash[l2][r2]) {
            dp[l][r]=1+Math.min(check(str,l1,r1),check(str,l2,r2));
            return dp[l][r];
        }
        else {
            dp[l][r]=0;
            return 0;
        }
    }
    public static boolean is_pal(String str,int l,int r) {
        for(int i=l,j=r;i<j;i++,j--) {
            if(str.charAt(i)!=str.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}
