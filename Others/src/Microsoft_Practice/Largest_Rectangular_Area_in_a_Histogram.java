/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Microsoft_Practice;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class Largest_Rectangular_Area_in_a_Histogram {
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
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            int arr[]=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt();
            }
            System.out.println(solve(n,arr));
        }
    }
    public static int solve(int n,int arr[]) {
        int lft[]=new int[n];
        int rgt[]=new int[n];
        Stack<Integer> stck=new Stack<>();
        lft[0]=-1;
        stck.push(0);
        for(int i=1;i<n;i++) {
            while(!stck.isEmpty() && arr[stck.peek()]>=arr[i]) {
                stck.pop();
            }
            if(!stck.isEmpty() && arr[stck.peek()]<arr[i]) {
               lft[i]=stck.peek(); 
            }
            else {
                lft[i]=-1;
            }
            stck.push(i);
        }
        stck=new Stack<>();
        rgt[n-1]=-1;
        stck.push(n-1);
        for(int i=n-2;i>=0;i--) {
            while(!stck.isEmpty() && arr[stck.peek()]>=arr[i]) {
                stck.pop();
            }
            if(!stck.isEmpty() && arr[stck.peek()]<arr[i]) {
               rgt[i]=stck.peek(); 
            }
            else {
                rgt[i]=-1;
            }
            stck.push(i);
        }
//        for(int i=0;i<n;i++) {
//            System.out.print(lft[i]+" ");
//        }
//        System.out.println();
//        for(int i=0;i<n;i++) {
//            System.out.print(rgt[i]+" ");
//        }
//        System.out.println();
        int ans=0;
        for(int i=0;i<n;i++) {
            int l=lft[i];
            int r=rgt[i];
            if(l==-1 && r==-1) {
                ans=Math.max(ans,n*arr[i]);
            }
            else if(l==-1) {
                ans=Math.max(ans,arr[i]*r);
            }
            else if(r==-1) {
                ans=Math.max(ans, arr[i]*(n-l-1));
            }
            else {
                ans=Math.max(ans,arr[i]*(r-l-1));
            }
        }
        return ans;
    }
}
