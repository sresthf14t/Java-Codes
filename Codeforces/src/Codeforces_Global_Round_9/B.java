/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Global_Round_9;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class B {
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
            int m=input.scanInt();
            int arr[][]=new int[n][m];
            for(int i=0;i<n;i++) {
                for(int j=0;j<m;j++) {
                    arr[i][j]=input.scanInt();
                }
            }
            solve(n,m,arr);
        }
    }
    public static void solve(int n,int m,int arr[][]) {
        int tmp[][]=new int[n][m];
        tmp[0][0]=tmp[n-1][0]=tmp[0][m-1]=tmp[n-1][m-1]=2;
        for(int i=1;i<n-1;i++) {
            tmp[i][0]=3;
            tmp[i][m-1]=3;
        }
        for(int i=1;i<m-1;i++) {
            tmp[0][i]=3;
            tmp[n-1][i]=3;
        }
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(tmp[i][j]==0) {
                    tmp[i][j]=4;
                }
            }
        }
        StringBuilder ans=new StringBuilder("");
        boolean is_pos=true;
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                ans.append(tmp[i][j]+" ");
                if(arr[i][j]>tmp[i][j]) {
                    is_pos=false;
                    break;
                }
            }
            ans.append("\n");
            if(!is_pos) {
                break;
            }
        }
        if(!is_pos) {
            System.out.println("NO");
        }
        else {
            System.out.println("YES\n"+ans);
        }
    }
}
