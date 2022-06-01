/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package July_Lunchtime_2020_Division_1;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class BINFUN {
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
    static long pow[];
    public static void main(String args[]) throws IOException {
        pow=new long[62];
        pow[0]=1;
        for(int i=1;i<pow.length;i++) {
            pow[i]=2*pow[i-1];
        }
        Scan input=new Scan();
        int test=input.scanInt();
        StringBuilder ans=new StringBuilder("");
        int sum=0;
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            sum+=n;
            long arr[]=new long[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt();
            }
            if(sum<=1000) {
                ans.append(solve_2(n,arr)+"\n");
            }
            else {
                ans.append(solve_1(n,arr)+"\n");
            }
        }
        System.out.println(ans);
    }
    public static long solve_1(int n,long arr[]) {
        Arrays.sort(arr);
        StringBuilder max=dec_to_bin(arr[n-1]);
        long ans=0;
        for(int i=0;i<n-1;i++) {
            StringBuilder tmp_x=new StringBuilder(max);
            tmp_x.append(dec_to_bin(arr[i]));
            StringBuilder tmp_y=dec_to_bin(arr[i]);
            tmp_y.append(max);
            ans=Math.max(ans, Math.abs(bin_to_dec(tmp_x)-bin_to_dec(tmp_y)));
        }
        return ans;
    }
    public static long solve_2(int n,long arr[]) {
        long ans=0;
        for(int i=0;i<n;i++) {
            StringBuilder max=dec_to_bin(arr[i]);
            for(int j=i+1;j<n;j++) {
                StringBuilder tmp_x=new StringBuilder(max);
                tmp_x.append(dec_to_bin(arr[j]));
                StringBuilder tmp_y=dec_to_bin(arr[j]);
                tmp_y.append(max);
                ans=Math.max(ans, Math.abs(bin_to_dec(tmp_x)-bin_to_dec(tmp_y)));
            }
        }
        return ans;
    }
    public static StringBuilder dec_to_bin(long n) {
        StringBuilder ans=new StringBuilder("");
        while(n!=0) {
            ans.append(n%2);
            n/=2;
        }
        ans=ans.reverse();
        return ans;
    }
    public static long bin_to_dec(StringBuilder tmp) {
        long ans=0;
        for(int i=0;i<tmp.length();i++) {
            if(tmp.charAt(i)=='0') {
                continue;
            }
            ans+=pow[tmp.length()-i-1];
        }
        return ans;
    }
}
