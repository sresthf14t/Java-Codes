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
public class C_Barcode {
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
    static int n,m,x,y,black[],white[];
    static int dp[][];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n=input.scanInt();
        m=input.scanInt();
        x=input.scanInt();
        y=input.scanInt();
        char arr[][]=new char[n][m];
        for(int i=0;i<n;i++) {
            String str=input.scanString();
            for(int j=0;j<m;j++) {
                arr[i][j]=str.charAt(j);
            }
        }
        black=new int[m];
        white=new int[m];
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(arr[j][i]=='#') {
                    black[i]++;
                }
                else {
                    white[i]++;
                }
            }
        }
        for(int i=1;i<m;i++) {
            black[i]+=black[i-1];
            white[i]+=white[i-1];
        }
        dp=new int[m][2];
        for(int i=0;i<m;i++) {
            for(int j=0;j<2;j++) {
                dp[i][j]=-1;
            }
        }
        System.out.println(solve(0,-1));
    }
    public static int solve(int indx,int prev_clr) {
        //prev_clr 0=black 1=white
        if(indx==m) {
            return 0;
        }
        if(indx+x>m) {
            return 10000000;
        }
        int min=10000000;
        if(prev_clr!=-1 && dp[indx][prev_clr]!=-1) {
            return dp[indx][prev_clr];
        }
        for(int i=x;i<=y;i++) {
            if(indx+i>m) {
                break;
            }
            if(prev_clr==-1) {
                min=Math.min(min,solve(indx+i,0)+range_sum(black,indx,indx+i-1));
                min=Math.min(min,solve(indx+i,1)+range_sum(white,indx,indx+i-1));
            }
            else if(prev_clr==1) {
                min=Math.min(min,solve(indx+i,0)+range_sum(black,indx,indx+i-1));
            }
            else {
                min=Math.min(min,solve(indx+i,1)+range_sum(white,indx,indx+i-1));
            }
        }
        if(prev_clr!=-1) {
            dp[indx][prev_clr]=min;
        }
        return min;
    }
    public static int range_sum(int arr[], int l, int r) {
        if(l==0) {
            return arr[r];
        }
        return arr[r]-arr[l-1];
    }
}
