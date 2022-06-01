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
public class D_Shortest_and_Longest_LIS {
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
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            String str=input.scanString();
            ans.append(min(n,str)+"\n");
            ans.append(max(n,str)+"\n");
        }
        System.out.println(ans);
    }
    public static StringBuilder min(int n,String str) {
        StringBuilder ans=new StringBuilder("");
        int l=1,r=n;
        int arr[]=new int[n];
        for(int i=0;i<n-1;i++) {
            if(str.charAt(i)=='<') {
                arr[i]=0;
                l++;
            }
            else {
                arr[i]=1;
                r--;
            }
        }
        if(str.charAt(n-2)=='<') {
            arr[n-1]=0;
            l++;
        }
        else {
            arr[n-1]=1;
            r--;
        }
//        System.out.println(l+" "+r);
        l--;
        r=n;
        for(int i=0;i<n-1;i++) {
            if(str.charAt(i)=='>') {
                ans.append(r+" ");
                r--;
                continue;
            }
            int j=i;
            int cnt=0;
            while(j<n-1 && str.charAt(j)==str.charAt(i)) {
                cnt++;
                j++;
            }
            if(j==n-1) {
                cnt++;
            }
            l-=cnt-1;
            j=i;
            for(int k=0;k<cnt;k++) {
                ans.append(l+" ");
                l++;
                j++;
            }
            l-=cnt+1;
            i=j-1;
        }
        if(str.charAt(n-2)=='>') {
            ans.append(r);
        }
        return ans;
    }
    public static StringBuilder max(int n,String str) {
        StringBuilder ans=new StringBuilder("");
        int l=1,r=n;
        for(int i=0;i<n-1;i++) {
            if(str.charAt(i)=='<') {
                ans.append(l+" ");
                l++;
            }
            else {
                ans.append(r+" ");
                r--;
            }
        }
        ans.append(l);
        return ans;
    }
}
