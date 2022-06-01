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
public class B_Modulo_Sum {
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
    static int n,m,arr[];
    static int dp[][][];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n=input.scanInt();
        m=input.scanInt();
        arr=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
        }
        if(n>=m) {
            System.out.println("YES");
            System.exit(0);
        }
        dp=new int[n][m][2];
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                for(int k=0;k<2;k++) {
                    dp[i][j][k]=-1;
                }
            }
        }
        if(solve(n-1,0,false)) {
            System.out.println("YES");
        }
        else {
            System.out.println("NO");
        }
    }
    public static boolean solve(int indx,int sum,boolean sel) {
//        System.out.println(indx+" "+sum);
        if(indx==-1) {
            if(sel && sum%m==0) {
                return true;
            }
            return false;
        }
        if(dp[indx][sum][sel?1:0]!=-1) {
            return dp[indx][sum][sel?1:0]==1?true:false;
        }
        if(sum!=0 && sum%m==0) {
            dp[indx][sum][sel?1:0]=1;
            return true;
        }
        if(solve(indx-1,sum%m,sel)) {
            dp[indx][sum][sel?1:0]=1;
            return true;
        }
        if(solve(indx-1,(sum+arr[indx])%m,true)) {
            dp[indx][sum][sel?1:0]=1;
            return true;
        }
        dp[indx][sum][sel?1:0]=0;
        return false;
    }
}
