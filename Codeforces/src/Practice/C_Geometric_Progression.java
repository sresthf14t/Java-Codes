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
public class C_Geometric_Progression {
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
        int n=input.scanInt();
        long k=input.scanInt();
        long arr[]=new long[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
        }
        System.out.println(solve(n,k,arr));
    }
    public static long solve(int n,long k,long arr[]) {
        HashMap<Long,Long> lft=new HashMap<>();
        HashMap<Long,Long> rgt=new HashMap<>();
        for(int i=0;i<n;i++) {
            if(!rgt.containsKey(arr[i])) {
                rgt.put(arr[i], 0L);
            }
            if(!lft.containsKey(arr[i])) {
                lft.put(arr[i], 0L);
            }
            rgt.replace(arr[i], rgt.get(arr[i])+1L);
        }
        long cnt=0;
        for(int i=0;i<n;i++) {
            rgt.replace(arr[i], rgt.get(arr[i])-1);
            if(arr[i]%k!=0) {
                lft.replace(arr[i], lft.get(arr[i])+1L);
                continue;
            }
            long l=arr[i]/k,r=arr[i]*k;
            if(lft.containsKey(l) && rgt.containsKey(r)) {
//                System.out.println(i+" "+lft.get(l)+" "+rgt.get(r));
                cnt+=(lft.get(l)*rgt.get(r));
            }
            lft.replace(arr[i], lft.get(arr[i])+1L);
        }
        return cnt;
    }
}
