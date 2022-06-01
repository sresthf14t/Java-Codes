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
public class B_Equivalent_Strings {
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
    static String str1,str2;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        str1=input.scanString();
        str2=input.scanString();
        int n=str1.length();
        if(solve(n,0,n-1,0,n-1)) {
            System.out.println("YES");
        }
        else {
            System.out.println("NO");
        }
    }
    public static boolean solve(int len,int l1,int r1,int l2,int r2) {
//        System.out.println(len+" "+l1+" "+r1+" "+l2+" "+r2);
        if(equal(l1,r1,l2,r2)) {
            return true;
        }
        if(len%2==0) {
            if(solve(len/2,l1,(l1+r1)/2,((l2+r2)/2)+1,r2) && solve(len/2,((l1+r1)/2)+1,r1,l2,(l2+r2)/2)) {
                return true;
            }
            if(solve(len/2,l1,(l1+r1)/2,l2,(l2+r2)/2) && solve(len/2,(l1+r1)/2+1,r1,(l2+r2)/2+1,r2)) {
                return true;
            }
        }
        return false;
    }
    public static boolean equal(int l1,int r1,int l2,int r2) {
        for(int i1=l1,i2=l2;i1<=r1 && i2<=r2;i1++,i2++) {
            if(str1.charAt(i1)!=str2.charAt(i2)) {
                return false;
            }
        }
        return true;
    }
}
