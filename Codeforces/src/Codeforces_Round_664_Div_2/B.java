/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_664_Div_2;

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
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        int n=input.scanInt();
        int m=input.scanInt();
        int x=input.scanInt();
        int y=input.scanInt();
        ans.append(x+" "+y);
        ans.append("\n");
        for(int i=y+1;i<=m;i++) {
            ans.append(x+" "+i);
            ans.append("\n");
        }
        for(int i=y-1;i>=1;i--) {
            ans.append(x+" "+i);
            ans.append("\n");
        }
        if(y==1) {
            y=m;
        }
        else {
            y=1;
        }
        for(int i=x-1;i>=1;i--) {
            if(y==m) {
                for(int j=m;j>=1;j--) {
                    ans.append(i+" "+j);
                    ans.append("\n");
                    y=j;
                }
            }
            else {
                for(int j=1;j<=m;j++) {
                    ans.append(i+" "+j);
                    ans.append("\n");
                    y=j;
                }
            }
        }
        
        for(int i=x+1;i<=n;i++) {
            if(y==m) {
                for(int j=m;j>=1;j--) {
                    ans.append(i+" "+j);
                    ans.append("\n");
                    y=j;
                }
            }
            else {
                for(int j=1;j<=m;j++) {
                    ans.append(i+" "+j);
                    ans.append("\n");
                    y=j;
                }
            }
        }
        System.out.println(ans);
    }
}
