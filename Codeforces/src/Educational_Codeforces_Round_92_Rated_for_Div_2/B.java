/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Educational_Codeforces_Round_92_Rated_for_Div_2;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class B {
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
    static int n,k,z,arr[];
    static int prefix[];
    static int dp[][][];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int test=input.scanInt();
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
           n=input.scanInt();
           k=input.scanInt();
           z=input.scanInt();
           arr=new int[n];
           for(int i=0;i<n;i++) {
               arr[i]=input.scanInt();
           }
           prefix=new int[n];
           prefix[0]=arr[0];
           for(int i=1;i<n;i++) {
               prefix[i]=prefix[i-1]+arr[i];
           }
           dp=new int[n+1][z+1][2];
           for(int i=0;i<n+1;i++) {
               for(int j=0;j<z+1;j++) {
                   for(int k=0;k<2;k++) {
                       dp[i][j][k]=-1;
                   }
               }
           }
           ans.append(solve(0,z,1));
           ans.append("\n");
        }
        System.out.println(ans);
    }
    public static int solve(int indx,int z_rem,int prev) {
        int rem=k-indx-2*(z-z_rem);
        if(rem==0) {
            dp[indx][z_rem][prev]=arr[indx];
            return arr[indx];
        }
        if(z_rem==0) {
            dp[indx][z_rem][prev]=sum(indx,indx+rem);
            return sum(indx,indx+rem);
        }
        if(dp[indx][z_rem][prev]!=-1) {
            return dp[indx][z_rem][prev];
        }
        int ans=Integer.MIN_VALUE;
        if(indx!=n-1) {
            ans=Math.max(ans,arr[indx]+solve(indx+1,z_rem,1));
        }
        if(indx!=0 && z_rem>0 && prev!=0) {
            ans=Math.max(ans,arr[indx]+solve(indx-1,z_rem-1,0));
        }
        if(indx+rem<n) {
            ans=Math.max(ans,sum(indx,indx+rem));
        }
        dp[indx][z_rem][prev]=ans;
        return ans;
    }
    public static int sum(int l,int r) {
        return prefix[r]-(l!=0?prefix[l-1]:0);
    }
}
