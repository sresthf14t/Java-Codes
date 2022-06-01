/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_636_Div_3;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class D_1 {
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
            ans.append(solve(n,k,arr));
            ans.append("\n");
        }
        System.out.println(ans);
    }
    public static long solve(int n,int k,int arr[]) {
        int arr1[]=new int[n/2];
        int arr2[]=new int[n/2];
        int sum[]=new int[n/2];
        int max_arr[]=new int[n/2];
        int min_arr[]=new int[n/2];
        for(int i=0;i<n/2;i++) {
            arr1[i]=arr[i];
            arr2[i]=arr[n-i-1];
        }
        for(int i=0;i<n/2;i++) {
            sum[i]=arr1[i]+arr2[i];
            max_arr[i]=Math.max(arr1[i], arr2[i])+k;
            min_arr[i]=Math.min(arr1[i], arr2[i])+1;
        }
//        for(int i=0;i<n/2;i++) {
//            System.out.print(sum[i]+" ");
//        }
//        System.out.println();
//        for(int i=0;i<n/2;i++) {
//            System.out.print(max_arr[i]+" ");
//        }
//        System.out.println();
//        for(int i=0;i<n/2;i++) {
//            System.out.print(min_arr[i]+" ");
//        }
//        System.out.println();
        int sum_cnt[]=new int[2*k+4];
        int max_sum_cnt[]=new int[2*k+2];
        int min_sum_cnt[]=new int[2*k+2];
        for(int i=0;i<n/2;i++) {
            sum_cnt[sum[i]]++;
            max_sum_cnt[max_arr[i]]++;
            min_sum_cnt[min_arr[i]]++;
        }
        for(int i=1;i<max_sum_cnt.length;i++) {
            max_sum_cnt[i]+=max_sum_cnt[i-1];
            min_sum_cnt[i]+=min_sum_cnt[i-1];
        }
        //max_arr<sum[i] && min_arr>sum[i]
        int ans=(n/2);
        for(int i=2;i<=2*k;i++) {
            int one,two,zero;
            two=max_sum_cnt[i-1]+(min_sum_cnt[2*k]-min_sum_cnt[i]);
            one=(n/2)-two-sum_cnt[i];
            ans=Math.min(ans, (2*two)+one);
        }
        return ans;
    }
}
