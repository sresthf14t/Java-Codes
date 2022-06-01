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
public class B_Zuma {
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
    static int n,arr[],dp[][];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n=input.scanInt();
        arr=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
        }
        dp=new int[n][n];
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                dp[i][j]=-1;
            }
        }
        System.out.println(solve(0,n-1));
    }
    public static int solve(int strt,int end) {
        if(strt>end) {
            return 0;
        }
        if(strt==end) {
            return 1;
        }
        if(dp[strt][end]!=-1) {
            return dp[strt][end];
        }
        int min=Integer.MAX_VALUE;
        min=Math.min(min,1+solve(strt+1,end));
        if(arr[strt]==arr[strt+1]) {
            min=Math.min(min,1+solve(strt+2,end));
        }
        for(int i=strt+2;i<=end;i++) {
            if(arr[strt]==arr[i]) {
                min=Math.min(min, solve(strt+1,i-1)+solve(i+1,end));
            }
        }
        dp[strt][end]=min;
        return min;
    }
    public static boolean is_pal(int strt,int end) {
        for(int i=strt,j=end;i<j;i++,j--) {
            if(arr[i]!=arr[j]) {
                return false;
            }
        }
        return true;
    }
}
