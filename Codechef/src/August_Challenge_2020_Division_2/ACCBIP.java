/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package August_Challenge_2020_Division_2;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class ACCBIP {
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
    static int n,c,k,arr[],brr[],crr[],v[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        int test=input.scanInt();
        for(int t=1;t<=test;t++) {
            n=input.scanInt();
            c=input.scanInt();
            k=input.scanInt();
            arr=new int[n];
            brr=new int[n];
            crr=new int[n];
            v=new int[c];
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt();
                brr[i]=input.scanInt();
                crr[i]=input.scanInt()-1;
            }
            for(int i=0;i<c;i++) {
                v[i]=input.scanInt();
            }
            ans.append(solve(0,k,new boolean[n])+"\n");
        }
        System.out.println(ans);
    }
    public static int solve(int indx,int k,boolean del[]) {
        if(indx==n) {
            int cnt=0;
            for(int i=0;i<n;i++) {
//                System.out.print((del[i]?1:0)+" ");
                if(del[i]) {
                    continue;
                }
                for(int j=i+1;j<n;j++) {
                    if(del[j]) {
                        continue;
                    }
                    for(int k1=j+1;k1<n;k1++) {
                        if(del[k1]) {
                            continue;
                        }
                        if(crr[i]==crr[k1] && crr[j]==crr[k1]) {
                            if(arr[i]!=arr[j] && arr[k1]!=arr[i] && arr[k1]!=arr[j]) {
                                cnt++;
                            }
                        }
                    }
                }
            }
//            System.out.println();
            return cnt;
        }
        int min=Integer.MAX_VALUE;
        if(k>=v[crr[indx]]) {
            del[indx]=true;
            min=Math.min(min,solve(indx+1,k-v[crr[indx]],del));
            del[indx]=false;
        }
        min=Math.min(min,solve(indx+1,k,del));
        return min;
    }
}
