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
public class C_Yet_Another_Walking_Robot {
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
        for(int t=0;t<test;t++) {
            int n=input.scanInt();
            String str=input.scanString();
            solve(n,str);
        }
    }
    public static void solve(int n,String str) {
//        for(int i=0;i<n-1;i++) {
//            if(str.charAt(i)=='L' && str.charAt(i+1)=='R') {
//                System.out.println((i+1)+" "+(i+2));
//                return;
//            }
//            if(str.charAt(i)=='R' && str.charAt(i+1)=='L') {
//                System.out.println((i+1)+" "+(i+2));
//                return;
//            }
//            if(str.charAt(i)=='U' && str.charAt(i+1)=='D') {
//                System.out.println((i+1)+" "+(i+2));
//                return;
//            }
//            if(str.charAt(i)=='D' && str.charAt(i+1)=='U') {
//                System.out.println((i+1)+" "+(i+2));
//                return;
//            }
//        }
        int x=0,y=0;
        HashMap<String,Integer> map=new HashMap<>();
        int min=Integer.MAX_VALUE;
        int l=-1,r=-1;
        map.put(x+" "+y, 0);
        for(int i=1;i<=n;i++) {
            if(str.charAt(i-1)=='L') {
                x--;
            }
            if(str.charAt(i-1)=='R') {
                x++;
            }
            if(str.charAt(i-1)=='D') {
                y--;
            }
            if(str.charAt(i-1)=='U') {
                y++;
            }
            if(map.containsKey(x+" "+y)) {
                int indx=map.get(x+" "+y);
                if(i-indx<min) {
                    min=i-indx;
                    l=indx+1;
                    r=i;
                }
                map.replace(x+" "+y, i);
            }
            else {
                map.put(x+" "+y, i);
            }
        }
        if(l==-1) {
            System.out.println(-1);
        }
        else {
            System.out.println(l+" "+r);
        }
    }
}
