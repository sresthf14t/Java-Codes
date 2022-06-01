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
public class F_Clear_the_String {
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
    static int dp[][][];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int n=input.scanInt();
        StringBuilder str=new StringBuilder(input.scanString());
        dp=new int[26][n+1][n+1];
        for(int i=0;i<26;i++) {
            for(int j=0;j<=n;j++) {
                for(int k=0;k<=n;k++) {
                    dp[i][j][k]=-1;
                }
            }
        }
        boolean bool[]=new boolean[26];
        for(int i=0;i<str.length();i++) {
            bool[str.charAt(i)-97]=true;
        }
        for(int i=0;i<26;i++) {
            bool[i]=!bool[i];
        }
        System.out.println(solve(n,str));
    }
    public static int solve(int n,StringBuilder str) {
//        System.out.println(str);
        if(n==0) {
            return 0;
        }
        int moves=Integer.MAX_VALUE;
        for(int i=0;i<str.length();i++) {
            if(dp[str.charAt(i)-97][i][n]==-1) {
                StringBuilder tmp=new StringBuilder(str);
                del_steps(tmp,i);
                int tmp_moves=1+solve(tmp.length(),tmp);
                dp[str.charAt(i)-97][i][n]=tmp_moves;
            }
            moves=Math.min(moves,dp[str.charAt(i)-97][i][n]);
        }
        return moves;
    }
    public static void del_steps(StringBuilder str,int indx) {
        char chr=str.charAt(indx);
        while(indx<str.length() && str.charAt(indx)==chr) {
            str.deleteCharAt(indx);
        }
    }
}
