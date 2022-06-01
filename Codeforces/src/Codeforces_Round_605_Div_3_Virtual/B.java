/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_605_Div_3_Virtual;

/**
 *
 * @author User
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
    
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int test=input.scanInt();
        StringBuilder fin_ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            String str=input.scanString();
            int n=str.length();
            int l=0,r=0,u=0,d=0;
            for(int i=0;i<n;i++) {
                if(str.charAt(i)=='L') {
                    l++;
                }
                if(str.charAt(i)=='R') {
                    r++;
                }
                if(str.charAt(i)=='U') {
                    u++;
                }
                if(str.charAt(i)=='D') {
                    d++;
                }
            }
            StringBuilder ans=new StringBuilder("");
            int cnt_x=Math.min(l,r);
            int cnt_y=Math.min(u,d);
            if(cnt_x==0) {
                if(cnt_y>=1) {
                    ans.append("UD");
                    fin_ans.append(2);
                    fin_ans.append("\n");
                    fin_ans.append(ans);
                    fin_ans.append("\n");
                }
                else {
                    fin_ans.append(0);
                    fin_ans.append("\n");
                    fin_ans.append(ans);
                    fin_ans.append("\n");
                }
                continue;
            }
            if(cnt_y==0) {
                if(cnt_x>=1) {
                    ans.append("LR");
                    fin_ans.append(2);
                    fin_ans.append("\n");
                    fin_ans.append(ans);
                    fin_ans.append("\n");
                }
                else {
                    fin_ans.append(0);
                    fin_ans.append("\n");
                    fin_ans.append(ans);
                    fin_ans.append("\n");
                }
                continue;
            }
            for(int i=0;i<cnt_x;i++) {
                ans.append('L');
            }
            for(int i=0;i<cnt_y;i++) {
                ans.append('U');
            }
            for(int i=0;i<cnt_x;i++) {
                ans.append('R');
            }
            for(int i=0;i<cnt_y;i++) {
                ans.append('D');
            }
            cnt_x*=2;
            cnt_y*=2;
            fin_ans.append(cnt_x+cnt_y);
            fin_ans.append("\n");
            fin_ans.append(ans);
            fin_ans.append("\n");
        }
        System.out.println(fin_ans);
    }
}
