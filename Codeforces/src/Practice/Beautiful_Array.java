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
public class Beautiful_Array {
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
    static int n;
    static long x,arr[];
    static HashMap<Long,Long> dp[][];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n=input.scanInt();
        x=input.scanInt();
        arr=new long[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
        }
        dp=new HashMap[n][3];
        for(int i=0;i<n;i++) {
            for(int j=0;j<3;j++) {
                dp[i][j]=new HashMap<>();
            }
        }
        if(x>=0) {
            System.out.println(pos(n,x,arr));
        }
        else {
            System.out.println(neg(0,0));
        }
    }
    public static long pos(int n,long x,long arr[]) {
        long sum=0,max=Integer.MIN_VALUE;
        for(int i=0;i<n;i++) {
            sum+=arr[i];
            max=Math.max(max,sum);
            if(sum<0) {
                sum=0;
            }
        }
        if(sum<0) {
            return 0;
        }
        return Math.max(0,max*x);
    }
    public static long neg(int indx,int state_mul) {
        if(indx==n) {
            return 0;
        }
        long max=0;
        if(state_mul==0) {
            max=Math.max(max,arr[indx]+neg(indx+1,0));
            arr[indx]*=x;
            max=Math.max(max,arr[indx]+neg(indx+1,1));
            arr[indx]/=x;
        }
        if(state_mul==1) {
            arr[indx]*=x;
            max=Math.max(max,arr[indx]+neg(indx+1,1));
            arr[indx]/=x;
            max=Math.max(max,arr[indx]+neg(indx+1,2));
        }
        if(state_mul==2) {
            max=Math.max(max,arr[indx]+neg(indx+1,2));
        }
        return max;
    }
}
