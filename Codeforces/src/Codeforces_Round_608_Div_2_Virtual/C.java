/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_608_Div_2_Virtual;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class C {
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
        int x=input.scanInt();
        int y=input.scanInt();
        int arr_x[]=new int[n];
        int arr_y[]=new int[n];
        for(int i=0;i<n;i++) {
            arr_x[i]=input.scanInt();
            arr_y[i]=input.scanInt();
        }
        int lft=0,rgt=0,up=0,dwn=0;
        for(int i=0;i<n;i++) {
            if(arr_y[i]==y) {
                if(arr_x[i]<x) {
                    lft++;
                }
                else {
                    rgt++;
                }
            } 
            else if(arr_x[i]==x) {
                if(arr_y[i]<y) {
                    dwn++;
                }
                else {
                    up++;
                }
            }
            else if(arr_x[i]>=x && arr_y[i]>=y) {
                rgt++;
                up++;
            }
            else if(arr_x[i]<=x && arr_y[i]>=y) {
                lft++;
                up++;
            }
            else if(arr_x[i]<=x && arr_y[i]<=y) {
                lft++;
                dwn++;
            }
            else if(arr_x[i]>=x && arr_y[i]<=y) {
                rgt++;
                dwn++;
            }
//            System.out.println(lft+" "+rgt+" "+up+" "+dwn);
        }
//        System.out.println(lft+" "+rgt+" "+up+" "+dwn);
        int max=Math.max(Math.max(lft, rgt),Math.max(up,dwn));
        System.out.println(max);
        if(rgt==max) {
            System.out.println((x+1)+" "+y);
        }
        else if(lft==max) {
            System.out.println((x-1)+" "+y);
        }
        else if(up==max) {
            System.out.println(x+" "+(y+1));
        }
        else if(dwn==max) {
            System.out.println(x+" "+(y-1));
        }
    }
}
