/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_668_Div_2;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class C {
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
        for(int tt=1;tt<=test;tt++) {
            int n=input.scanInt();
            int k=input.scanInt();
            StringBuilder str=new StringBuilder(input.scanString());
            if(solve(n,k,str)) {
                ans.append("YES\n");
            }
            else {
                ans.append("NO\n");
            }
        }
        System.out.println(ans);
    }
    public static boolean solve(int n,int k,StringBuilder str) {
        int zero[]=new int[n];
        int one[]=new int[n];
        int ques[]=new int[n];
        for(int i=0;i<k;i++) {
            char tmp='?';
            for(int j=i;j<n;j+=k) {
                if(str.charAt(j)=='?') {
                    continue;
                }
                tmp=str.charAt(j);
                break;
            }
            if(tmp=='?') {
                continue;
            }
            for(int j=i;j<n;j+=k) {
                if(str.charAt(j)=='?') {
                    continue;
                }
                if(str.charAt(j)!=tmp) {
                    return false;
                }
            }
            for(int j=i;j<n;j+=k) {
                str.setCharAt(j, tmp);
            }
        }
        int cnt=0;
        for(int i=0;i<n;i++) {
            if(str.charAt(i)=='0') {
                cnt++;
            }
            zero[i]=cnt;
        }
        cnt=0;
        for(int i=0;i<n;i++) {
            if(str.charAt(i)=='1') {
                cnt++;
            }
            one[i]=cnt;
        }
        cnt=0;
        for(int i=0;i<n;i++) {
            if(str.charAt(i)=='?') {
                cnt++;
            }
            ques[i]=cnt;
        }
        for(int i=0;i<n;i++) {
            if(i+k-1<n) {
                if(get(zero,i,i+k-1)<=k/2 && get(one,i,i+k-1)<=k/2);
                else {
                    return false;
                }
            }
            else {
                break;
            }
        }
        return true;
    }
    public static int get(int arr[],int l,int r) {
        return arr[r]-(l==0?0:arr[l-1]);
    }
}
