/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class D_Aroma_s_Search {
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
        Scanner input=new Scanner(System.in);
        long x0=input.nextLong();
        long y0=input.nextLong();
        long ax=input.nextLong();
        long ay=input.nextLong();
        long bx=input.nextLong();
        long by=input.nextLong();
        long xs=input.nextLong();
        long ys=input.nextLong();
        long time=input.nextLong();
        int cnt=1;
        long x=x0,y=y0;
        while(true) {
            x*=ax;
            x+=bx;
            y*=ay;
            y+=by;
            if(x<(long)3*Math.pow(10, 16) && y<3*(long)Math.pow(10, 17)) {
                cnt++;
            }
            else {
                break;
            }
        }
        long arrx[]=new long[cnt];
        long arry[]=new long[cnt];
        x=x0;
        y=y0;
        for(int i=0;i<cnt;i++) {
            arrx[i]=x;
            arry[i]=y;
            x*=ax;
            x+=bx;
            y*=ay;
            y+=by;
        }
//        for(int i=0;i<cnt;i++) {
//            System.out.println(arrx[i]+" "+arry[i]);
//        }
        int ans=0;
        for(int i=0;i<cnt;i++) {
            ans=Math.max(ans, solve(arrx,arry,i,xs,ys,time));
//            System.out.println(i+" "+ans);
        }
        System.out.println(ans);
    }
    public static int solve(long arrx[],long arry[],int strt,long x0,long y0,long time) {
        int cnt=0;
        long rem=time;
        long x=x0,y=y0;
        for(int i=strt;i>=0;i--) {
            long diff=Math.abs(arrx[i]-x)+Math.abs(arry[i]-y);
            if(diff<=rem) {
                rem-=diff;
                cnt++;
                x=arrx[i];
                y=arry[i];
            }
            else {
                break;
            }
        }
        for(int i=strt+1;i<arrx.length;i++) {
            long diff=Math.abs(arrx[i]-x)+Math.abs(arry[i]-y);
            if(diff<=rem) {
                rem-=diff;
                cnt++;
                x=arrx[i];
                y=arry[i];
            }
            else {
                break;
            }
        }
        int max=cnt;
        cnt=0;
        x=x0;
        y=y0;
        rem=time;
        for(int i=strt;i<arrx.length;i++) {
            long diff=Math.abs(arrx[i]-x)+Math.abs(arry[i]-y);
            if(diff<=rem) {
                rem-=diff;
                cnt++;
                x=arrx[i];
                y=arry[i];
            }
            else {
                break;
            }
        }
        for(int i=strt-1;i>=0;i--) {
            long diff=Math.abs(arrx[i]-x)+Math.abs(arry[i]-y);
            if(diff<=rem) {
                rem-=diff;
                cnt++;
                x=arrx[i];
                y=arry[i];
            }
            else {
                break;
            }
        }
        max=Math.max(max, cnt);
        return max;
    }
}
