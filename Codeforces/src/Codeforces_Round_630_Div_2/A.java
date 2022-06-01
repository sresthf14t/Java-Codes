/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_630_Div_2;

/**
 *
 * @author User
 */
import java.io.*;
import java.util.*;
import java.io.DataInputStream; 
import java.io.FileInputStream; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.util.Scanner; 
import java.util.StringTokenizer;
public class A {
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
        int t=input.scanInt();
        for(int test=0;test<t;test++) {
            int a=input.scanInt();
            int b=input.scanInt();
            int c=input.scanInt();
            int d=input.scanInt();
            int x=input.scanInt();
            int y=input.scanInt();
            int x1=input.scanInt();
            int y1=input.scanInt();
            int x2=input.scanInt();
            int y2=input.scanInt();
            int x_axis_min=Math.min(a, b);
            int y_axis_min=Math.min(c, d);
            boolean is_pos=true;
            if(x1==x2 && (a!=0 || b!=0)) {
                is_pos=false;
            }
            if(y1==y2 && (c!=0 || d!=0)) {
                is_pos=false;
            } 
            a-=x_axis_min;
            b-=x_axis_min;
            c-=y_axis_min;
            d-=y_axis_min;
            if(a!=0) {
                x-=a;
                if(x<x1) {
                    is_pos=false;
                }
            }
            else {
                x+=b;
                if(x>x2) {
                    is_pos=false;
                }
            }
            if(c!=0) {
                y-=c;
                if(y<y1) {
                    is_pos=false;
                }
            }
            else {
                y+=d;
                if(y>y2) {
                    is_pos=false;
                }
            }
            if(is_pos) {
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
            }
        }
    }
}
