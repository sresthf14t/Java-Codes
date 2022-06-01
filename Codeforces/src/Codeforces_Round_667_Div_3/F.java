/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_667_Div_3;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class F {
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
    static int n,k,prefix[];
    static String str,t;
    static int dp[][][];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int test=1;
        StringBuilder ans=new StringBuilder("");
        for(int t1=0;t1<test;t1++) {
            n=input.scanInt();
            k=input.scanInt();
            str=input.scanString();
            t=input.scanString();
            if(t.charAt(0)==t.charAt(1)) {
                System.out.println(solve1());
                return;
            }
            prefix=new int[n];
            int cnt=0;
            for(int i=0;i<n;i++) {
                if(str.charAt(i)==t.charAt(1)) {
                    cnt++;
                }
                prefix[i]=cnt;
            }
            dp=new int[n+1][n+1][n+1];
            for(int i=0;i<dp.length;i++) {
                for(int j=0;j<dp[0].length;j++) {
                    for(int k=0;k<dp[0][0].length;k++) {
                        dp[i][j][k]=-1;
                    }
                }
            }
            ans.append(solve(0,0,k)+"\n");
        }
        System.out.println(ans);
    }
    public static int solve(int indx,int prev,int rem) {
        if(indx==n) {
            return 0;
        }
        if(dp[indx][prev][rem]!=-1) {
            return dp[indx][prev][rem];
        }
        int max=0;
        if(str.charAt(indx)==t.charAt(0)) {
            max=Math.max(max,get(indx+1,n-1)+solve(indx+1,prev+1,rem));
        }
        if(str.charAt(indx)==t.charAt(1)) {
            max=Math.max(max,solve(indx+1,prev,rem));
        }
        if(str.charAt(indx)!=t.charAt(0) && rem>0) {
            max=Math.max(max,get(indx+1,n-1)+solve(indx+1,prev+1,rem-1)-(str.charAt(indx)==t.charAt(1)?prev:0));
        }
        if(str.charAt(indx)!=t.charAt(1) && rem>0) {
            max=Math.max(max,prev+solve(indx+1,prev,rem-1));
        }
        if(str.charAt(indx)!=t.charAt(0) && str.charAt(indx)!=t.charAt(1)) {
            max=Math.max(max,solve(indx+1,prev,rem));
        }
        dp[indx][prev][rem]=max;
//        System.out.println(indx+" "+max);
        return max;
    }
    public static int get(int l,int r) {
        if(l>r) {
            return 0;
        }
        return prefix[r]-(l==0?0:prefix[l-1]);
    }
    public static int solve1() {
        int cnt=0;
        for(int i=0;i<str.length();i++) {
            if(str.charAt(i)==t.charAt(0)) {
                cnt++;
            }
        }
        cnt+=k;
        cnt=Math.min(cnt, n);
        return (cnt*(cnt-1))/2;
    }
}
