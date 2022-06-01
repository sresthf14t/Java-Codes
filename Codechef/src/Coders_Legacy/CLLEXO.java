/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Coders_Legacy;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class CLLEXO {
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
        StringBuilder ans=new StringBuilder("\n");
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            int m=input.scanInt();
            String str[]=new String[n];
            for(int i=0;i<n;i++) {
                str[i]=input.scanString();
            }
            ans.append(solve(n,m,str));
            ans.append("\n");
        } 
        System.out.println(ans);
    }
    public static StringBuilder solve(int n,int m,String str[]) {
        int arr[]=new int[n];
        Arrays.fill(arr, -1);
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(str[i].charAt(j)=='#') {
                    arr[i]=j;
                }
            }
        }
        boolean block[]=new boolean[n];
        for(int i=n-1;i>=0;i--) {
            if(!str[i].contains("#")) {
                break;
            }
            block[i]=true;
        }
        StringBuilder ans=new StringBuilder("");
        int indx=0;
        for(int i=0;i<n-1;i++) {
            ans.append(str[i].charAt(indx));
            if(block[i] && arr[i+1]!=-1 && arr[i+1]+1>indx) {
                while(indx!=arr[i+1]+1) {
                    indx++;
                    if(str[i].charAt(indx)=='#') {
                        indx--;
                        break;
                    }
                    ans.append(str[i].charAt(indx));
                }
            }
            while(indx<m-1) {
                if(str[i].charAt(indx+1)<str[i+1].charAt(indx)) {
                    indx++;
                    if(str[i].charAt(indx)=='#') {
                        indx--;
                        break;
                    }
                    ans.append(str[i].charAt(indx));
                }
                else {
                    break;
                }
            }
        }
        for(int i=indx;i<m;i++) {
            ans.append(str[n-1].charAt(i));
        }
        return ans;
    }
}
