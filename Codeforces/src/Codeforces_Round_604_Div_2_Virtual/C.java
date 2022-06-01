/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_604_Div_2_Virtual;

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
        int test=input.scanInt();
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            int arr[]=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt();
            }
            int med[]=solve(n,arr);
            System.out.println(med[0]+" "+med[1]+" "+med[2]);
        }
    }
    public static int[] solve(int n,int arr[]) {
        ArrayList<Integer> arrli=new ArrayList<>();
        for(int i=0;i<n;i++) {
            int cnt=0;
            int j=i;
            while(j<n && arr[j]==arr[i]) {
                cnt++;
                j++;
            }
            arrli.add(cnt);
            i=j-1;
        }
//        for(int i=0;i<arrli.size();i++) {
//            System.out.print(arrli.get(i)+" ");
//        }
//        System.out.println();
        int med[]=new int[3];
        if(arrli.size()<3) {
            return med;
        }
        int strt=1;
        med[0]=arrli.get(0);
        while(strt<arrli.size() && med[1]<=med[0]) {
            med[1]+=arrli.get(strt);
            strt++;
        }
//        System.out.println(med[0]+" "+med[1]+" "+med[2]+" "+strt);
        for(int i=strt;i<arrli.size();i++) {
            if(med[0]+med[1]+med[2]+arrli.get(i)<=n/2) {
                med[2]+=arrli.get(i);
            }
            else {
                break;
            }
//            System.out.println(med[2]);
        }
        if(med[0]>=med[1] || med[0]>=med[2] || (med[0]+med[1]+med[2]>n/2)) {
            return new int[3];
        }
        return med;
    }
}
