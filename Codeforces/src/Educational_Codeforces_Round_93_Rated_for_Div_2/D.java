/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Educational_Codeforces_Round_93_Rated_for_Div_2;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class D {
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
    static int r,g,b,red[],green[],blue[];
    static int dp[][][];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        r=input.scanInt();
        g=input.scanInt();
        b=input.scanInt();
        red=new int[r];
        green=new int[g];
        blue=new int[b];
        for(int i=0;i<r;i++) {
            red[i]=input.scanInt();
        }
        for(int i=0;i<g;i++) {
            green[i]=input.scanInt();
        }
        for(int i=0;i<b;i++) {
            blue[i]=input.scanInt();
        }
        sort(r,red);
        sort(g,green);
        sort(b,blue);
        dp=new int[r+1][g+1][b+1];
        for(int i=0;i<r+1;i++) {
            for(int j=0;j<g+1;j++) {
                for(int k=0;k<b+1;k++) {
                    dp[i][j][k]=-1;
                }
            }
        }
        System.out.println(solve(0,0,0));
    }
    public static int solve(int indx1,int indx2,int indx3) {
        if(indx1==r && indx2==g && indx3==b) {
            return 0;
        }
        if(dp[indx1][indx2][indx3]!=-1) {
            return dp[indx1][indx2][indx3];
        }
        int max=0;
        if(indx1<r) {
            max=Math.max(max,solve(indx1+1,indx2,indx3));
        }
        if(indx2<g) {
            max=Math.max(max,solve(indx1,indx2+1,indx3));
        }
        if(indx3<b) {
            max=Math.max(max,solve(indx1,indx2,indx3+1));
        }
        if(indx1<r && indx2<g) {
            max=Math.max(max,(red[indx1]*green[indx2]+solve(indx1+1,indx2+1,indx3)));
        }
        if(indx1<r && indx3<b) {
            max=Math.max(max,(red[indx1]*blue[indx3]+solve(indx1+1,indx2,indx3+1)));
        }
        if(indx2<g && indx3<b) {
            max=Math.max(max,(green[indx2]*blue[indx3]+solve(indx1,indx2+1,indx3+1)));
        }
        dp[indx1][indx2][indx3]=max;
        return max;
    }
    public static void sort(int n,int arr[]) {
        for(int i=0;i<n;i++) {
            for(int j=0;j<n-1;j++) {
                if(arr[j+1]>arr[j]) {
                    swap(arr,j,j+1);
                }
            }
        }
    }
    public static void swap(int arr[],int l,int r) {
        int tmp=arr[l];
        arr[l]=arr[r];
        arr[r]=tmp;
    }
}