/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Global_Round_10;

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
    static int n,arr[],dp[][][][][];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        int test=input.scanInt();
        for(int t=1;t<=test;t++) {
            n=input.scanInt();
            String str=input.scanString();
            arr=new int[n];
            for(int i=0;i<n;i++) {
                if(str.charAt(i)=='L') {
                    arr[i]=0;
                }
                else {
                    arr[i]=1;
                }
            }
            dp=new int[n][2][2][2][2];
            int min=1000000;
            fill();
            min=Math.min(min,solve(1,arr[0],arr[n-1],arr[0],arr[n-1]));
            toggle(0);
            min=Math.min(min,1+solve(1,arr[0],arr[n-1],arr[0],arr[n-1]));
            toggle(n-1);
            min=Math.min(min,2+solve(1,arr[0],arr[n-1],arr[0],arr[n-1]));
            toggle(0);
            min=Math.min(min,1+solve(1,arr[0],arr[n-1],arr[0],arr[n-1]));
            ans.append(min+"\n");
        }
        System.out.println(ans);
    }
    public static int solve(int indx,int fst,int lst,int prev,int prev_prev) {
        int curr=arr[indx];
        if(indx==n-1) {
            if(check(prev,prev_prev,curr) && check(curr,prev,fst)) {
                return 0;
            }
            return 1000000;
        }
        if(dp[indx][fst][lst][prev][prev_prev]!=-1) {
            return dp[indx][fst][lst][prev][prev_prev];
        }
        int min=1000000;
        if(check(prev,prev_prev,curr)) {
            min=Math.min(min,solve(indx+1,fst,lst,curr,prev));
        }
        
        if(curr==1) {
            curr=0;
        }
        else {
            curr=1;
        }
        
        if(check(prev,prev_prev,curr)) {
            min=Math.min(min,1+solve(indx+1,fst,lst,curr,prev));
        }
        
        if(curr==1) {
            curr=0;
        }
        else {
            curr=1;
        }
        dp[indx][fst][lst][prev][prev_prev]=min;
        return min;
    }
    public static boolean check(int curr,int prev,int nxt) {
        if(prev==1 && nxt==1) {
            if(curr==0) {
                return true;
            }
            return false;
        }
        if(prev==0 && nxt==0) {
            if(curr==1) {
                return true;
            }
            return false;
        }
        return true;
    }
    public static void toggle(int indx) {
        if(arr[indx]==1) {
            arr[indx]=0;
        }
        else {
            arr[indx]=1;
        }
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
