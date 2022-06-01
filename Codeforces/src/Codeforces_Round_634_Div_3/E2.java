/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_634_Div_3;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class E2 {
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
        StringBuilder ans=new StringBuilder("");
        int test=input.scanInt();
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            int arr[]=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt();
            }
            if(n==1) {
                ans.append(1+"\n");
                continue;
            }
            
            ArrayList<Integer> arrli[]=new ArrayList[201];
            for(int i=0;i<201;i++) {
                arrli[i]=new ArrayList<>();
            }
            for(int i=0;i<n;i++) {
                arrli[arr[i]].add(i);
            }
            ans.append(solve(n,arrli)+"\n");
        }
        System.out.println(ans);
    }
    
    public static int solve(int n,ArrayList<Integer> arrli[]) {
        int max_len=0;
        for(int i=1;i<201;i++) {
            for(int j=1;j<201;j++) {
                for(int k1=0,k2=arrli[i].size()-1;k1<=k2;k1++,k2--) {
                    int count=2*(k1+1);
                    if(k1==k2) {
                        count--;
                    }
                    int  lft=arrli[i].get(k1)+1;
                    int right=arrli[i].get(k2)-1;
                    for(int m=0;m<arrli[j].size();m++) {
                        if(arrli[j].get(m)>=lft && arrli[j].get(m)<=right) {
                            count++;
                        }
                    }
                    max_len=Math.max(max_len,count);
                }
            }
        }
        return max_len;
    }
}
