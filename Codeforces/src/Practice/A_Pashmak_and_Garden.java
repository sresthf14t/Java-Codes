/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class A_Pashmak_and_Garden {
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
        int x1=input.scanInt();
        int y1=input.scanInt();
        int x2=input.scanInt();
        int y2=input.scanInt();
        int len=Math.max(Math.abs(x1-x2),Math.abs(y1-y2));
        if(x1==x2) {
            System.out.println((x1+len)+" "+y1+" "+(x2+len)+" "+y2);
        }
        else if(y1==y2) {
            System.out.println((x1)+" "+(y1+len)+" "+x2+" "+(y2+len));
        }
        else if(Math.abs(x1-x2)!=Math.abs(y1-y2)) {
            System.out.println(-1);
        }
        else {
            if(x1<x2 && y1>y2) {
                System.out.println((x1+len)+" "+y1+" "+(x2-len)+" "+y2);
            }
            else if(x1>x2 && y1<y2) {
                System.out.println((x1-len)+" "+y1+" "+(x2+len)+" "+y2);
            }
            else if(x1>x2 && y1>y2) {
                System.out.println((x1-len)+" "+y1+" "+(x2+len)+" "+y2);
            }
            else {
                System.out.println((x1+len)+" "+y1+" "+(x2-len)+" "+y2);
            }
        }
    }
}
