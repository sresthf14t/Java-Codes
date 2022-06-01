/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package June_Lunchtime_2020_Division_2;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class MAXAND18 {
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
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int test=input.scanInt();
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            int k=input.scanInt();
            int arr[]=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt();
            }
            ans.append(solve(n,arr,k)+"\n");
        }
        System.out.println(ans);
    }
    public static long solve(int n,int arr[],int k) {
        int pow[]=new int[32];
        pow[0]=1;
        for(int i=1;i<pow.length;i++) {
            pow[i]=2*pow[i-1];
        }
        long cnt[]=new long[pow.length];
        for(int i=0;i<n;i++) {
            for(int j=0;j<pow.length;j++) {
                if((arr[i]&pow[j])!=0) {
                    cnt[j]++;
                }
            }
        }
        for(int i=0;i<pow.length;i++) {
            cnt[i]*=pow[i];
        }
        boolean taken[]=new boolean[pow.length];
        for(int i=0;i<k;i++) {
            long max=-1;
            int max_indx=-1;
            for(int j=0;j<pow.length;j++) {
                if(taken[j]) {
                    continue;
                }
                if(cnt[j]>max) {
                    max=cnt[j];
                    max_indx=j;
                }
            }
            taken[max_indx]=true;
        }
        long ans=0;
        for(int i=0;i<pow.length;i++) {
            if(taken[i]) {
                ans+=pow[i];
            }
        }
        return ans;
    }
}
