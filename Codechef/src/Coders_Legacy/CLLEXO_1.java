/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Coders_Legacy;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class CLLEXO_1 {
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
    static int n,m;
    static String str[];
    static StringBuilder dp[][];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int test=input.scanInt();
        StringBuilder ans=new StringBuilder("\n");
        for(int t=1;t<=test;t++) {
            n=input.scanInt();
            m=input.scanInt();
            str=new String[n];
            for(int i=0;i<n;i++) {
                str[i]=input.scanString();
            }
            dp=new StringBuilder[n][m];
            ans.append(solve(0,0));
            ans.append("\n");
        } 
        System.out.println(ans);
    }
    public static StringBuilder solve(int indx1,int indx2) {
        if(indx1==n-1) {
            StringBuilder tmp=new StringBuilder(str[indx1].substring(indx2, m));
            dp[indx1][indx2]=tmp;
            return tmp;
        }
        if(dp[indx1][indx2]!=null) {
            return dp[indx1][indx2];
        }
        if(str[indx1].charAt(indx2)=='#') {
            dp[indx1][indx2]=new StringBuilder("");
            return dp[indx1][indx2];
        }
        if(indx2==m-1) {
            StringBuilder tmp=solve(indx1+1,indx2);
            if(tmp.length()==0) {
                dp[indx1][indx2]=tmp;
                return dp[indx1][indx2];
            }
            dp[indx1][indx2]=new StringBuilder("");
            dp[indx1][indx2].append(str[indx1].charAt(indx2));
            dp[indx1][indx2].append(tmp);
            return dp[indx1][indx2];
        }
        StringBuilder tmp[]=new StringBuilder[2];
        tmp[0]=solve(indx1,indx2+1);
        tmp[1]=solve(indx1+1,indx2);
        if(tmp[0].length()==0 && tmp[1].length()==0) {
            dp[indx1][indx2]=tmp[0];
            return dp[indx1][indx2];
        }
        if(tmp[0].length()==0) {
            dp[indx1][indx2]=new StringBuilder("");
            dp[indx1][indx2].append(str[indx1].charAt(indx2));
            dp[indx1][indx2].append(tmp[1]);
            return dp[indx1][indx2];
        }
        if(tmp[1].length()==0) {
            dp[indx1][indx2]=new StringBuilder("");
            dp[indx1][indx2].append(str[indx1].charAt(indx2));
            dp[indx1][indx2].append(tmp[0]);
            return dp[indx1][indx2];
        }
        int min=compare(tmp);
        dp[indx1][indx2]=new StringBuilder("");
        dp[indx1][indx2].append(str[indx1].charAt(indx2));
        dp[indx1][indx2].append(tmp[min]);
        return dp[indx1][indx2];
    }
    public static int compare(StringBuilder tmp[]) {
        int lim=Math.min(tmp[0].length(), tmp[1].length());
        for(int i=0;i<lim;i++) {
            if(tmp[0].charAt(i)==tmp[1].charAt(i)) {
                continue;
            }
            if(tmp[0].charAt(i)<tmp[1].charAt(i)) {
                return 0;
            }
            return 1;
        }
        if(tmp[0].length()<tmp[1].length()) {
            return 0;
        }
        return 1;
    }
}
