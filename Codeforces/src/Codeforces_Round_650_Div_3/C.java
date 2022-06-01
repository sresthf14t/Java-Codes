/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_650_Div_3;

/**
 *
 * @author User
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
    public static void main(String arsg[]) throws IOException {
        Scan input=new Scan();
        int test=input.scanInt();
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            int k=input.scanInt();
            String str=input.scanString();
            ans.append(solve(n,k,str));
            ans.append("\n");
        }
        System.out.println(ans);
    }
    public static int solve(int n,int k, String str) {
        
        int cnt=0,first_1=-1,last_1=-1;
        for(int i=0;i<n;i++) {
            if(str.charAt(i)!='1') {
                continue;
            }
            if(first_1==-1) {
                first_1=i;
            }
            last_1=i;
            int j=i+1,chairs=0;
            while(j<n && str.charAt(j)!='1') {
                j++;
                chairs++;
            }
            if(j==n) {
                break;
            }
            i=j-1;
//            System.out.println(chairs);
            cnt+=count(chairs,k);
        }
        int diff=0;
        
        if(first_1==-1) {
            for(int i=1;i<n;i++) {
                diff++;
                if(diff>k) {
                    cnt++;
                    diff=0;
                }
            }
            return cnt+1;
        }
        
        for(int i=first_1-1;i>=0;i--) {
            diff++;
            if(diff>k) {
                cnt++;
                diff=0;
            }
        }
        diff=0;
        for(int i=last_1+1;i<n;i++) {
            diff++;
            if(diff>k) {
                cnt++;
                diff=0;
            }
        }
        return cnt;
    }
    public static int count(long chairs,long k) {
        long cnt=0;
        for(int i=1;;i++) {
            if(i+((i+1)*k)<=chairs) {
                cnt=i;
            }
            else {
                break;
            }
        }
        return (int)cnt;
    }
}
