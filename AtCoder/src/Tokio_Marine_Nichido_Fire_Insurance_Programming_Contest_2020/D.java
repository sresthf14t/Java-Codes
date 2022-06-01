/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tokio_Marine_Nichido_Fire_Insurance_Programming_Contest_2020;

/**
 *
 * @author User
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
    static int val[],wei[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int n=input.scanInt();
        val=new int[n];
        wei=new int[n];
        for(int i=0;i<n;i++) {
            val[i]=input.scanInt();
            wei[i]=input.scanInt();
        }
        StringBuilder ans=new StringBuilder("");
        int query=input.scanInt();
        for(int i=0;i<query;i++) {
            int root=input.scanInt();
            int cap=input.scanInt();
            ans.append(solve(root,cap));
            ans.append("\n");
        }
        System.out.println(ans);
    }
    public static int solve(int root,int cap) {
        int weight[]=new int[18];
        int value[]=new int[18];
        for(int i=0;i<18;i++) {
            weight[i]=wei[root-1];
            value[i]=val[root-1];
            root/=2;
            if(root==0) {
                break;
            }
        }
        return KnapSack(value , weight, 18, cap);
    }
    
    static int KnapSack(int val[], int wt[], int n, int W) 
{ 

    int []dp = new int[W+1]; 
      

    for(int i=0; i < n; i++)  
      

        for(int j = W; j >= wt[i]; j--) 
            dp[j] = Math.max(dp[j] , val[i] + dp[j - wt[i]]); 
              

    return dp[W]; 
} 
}
