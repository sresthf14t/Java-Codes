/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_667_Div_3;

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
        for(int t=0;t<test;t++) {
            int n=input.scanInt();
            int x=input.scanInt();
            int y=input.scanInt();
            ans.append(solve(n,x,y)+"\n");
        }
        System.out.println(ans);
    }
    public static StringBuilder solve(int n,int x,int y) {
        int diff=y-x;
        int ele_diff=-1,max=0;
        for(int i=1;i<=diff;i++) {
            if(diff%i!=0) {
                continue;
            }
            int ele=(diff/i)-1;
            if(ele+2<=n) {
                if(ele+2>max) {
                    max=ele+2;
                    ele_diff=i;
                }
            }
        }
        ArrayList<Integer> arrli=new ArrayList<>();
        int ele=x;
        while(ele<=y) {
            arrli.add(ele);
            ele+=ele_diff;
        }
        ele=x-ele_diff;
        while(arrli.size()<n && ele>0) {
            arrli.add(ele);
            ele-=ele_diff;
        }
        ele=y+ele_diff;
        while(arrli.size()<n) {
            arrli.add(ele);
            ele+=ele_diff;
        }
        StringBuilder ans=new StringBuilder("");
        for(int i=0;i<arrli.size();i++) {
            ans.append(arrli.get(i)+" ");
        }
        return ans;
    }
}
