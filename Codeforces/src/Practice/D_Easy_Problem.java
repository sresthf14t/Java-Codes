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
public class D_Easy_Problem {
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
    static int n;
    static String str;
    static long arr[],dp[][][][][];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n=input.scanInt();
        str=input.scanString();
        arr=new long[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
        }
        dp=new long[n][2][2][2][2];
        fill();
        System.out.println(solve(0,false,false,false,false));
    }
    public static long solve(int indx,boolean h,boolean a,boolean r,boolean d) {
        if(indx==n) {
            if(h && a && r && d) {
                return 10000000000000000L;
            }
            return 0;
        }
        if(dp[indx][h?1:0][a?1:0][r?1:0][d?1:0]!=-1) {
            return dp[indx][h?1:0][a?1:0][r?1:0][d?1:0];
        }
        long min=Long.MAX_VALUE;
        if(str.charAt(indx)=='h') {
            min=Math.min(min,solve(indx+1,true,a,r,d));
        }
        else if(str.charAt(indx)=='a') {
            if(h) {
                min=Math.min(min,solve(indx+1,h,true,r,d));
            }
            else {
                min=Math.min(min,solve(indx+1,h,a,r,d));
            }
        }
        else if(str.charAt(indx)=='r') {
            if(a) {
                min=Math.min(min,solve(indx+1,h,a,true,d));
            }
            else {
                min=Math.min(min,solve(indx+1,h,a,r,d));
            }
        }
        else if(str.charAt(indx)=='d') {
            if(r) {
                min=Math.min(min,solve(indx+1,h,a,r,true));
            }
            else {
                min=Math.min(min,solve(indx+1,h,a,r,d));
            }
        }
        else {
            min=Math.min(min,solve(indx+1,h,a,r,d));
        }
        min=Math.min(min,arr[indx]+solve(indx+1,h,a,r,d));
        dp[indx][h?1:0][a?1:0][r?1:0][d?1:0]=min;
        return min;
    }
    public static void fill() {
        for(int i=0;i<n;i++) {
            for(int j=0;j<2;j++) {
                for(int k=0;k<2;k++) {
                    for(int l=0;l<2;l++) {
                        for(int m=0;m<2;m++) {
                            dp[i][j][k][l][m]=-1;
                        }
                    }
                }
            }
        }
    }
}
