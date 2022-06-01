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
public class C_Xenia_and_Weights {
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
    static boolean weights[];
    static int m;
    static ArrayList<Integer> arr;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        String str=input.scanString();
        m=input.scanInt();
        weights=new boolean[11];
        arr=new ArrayList<>();
        for(int i=0;i<str.length();i++) {
            if(str.charAt(i)=='1') {
                weights[i+1]=true;
            }
        }
        if(!solve(0,0,0)) {
            System.out.println("NO");
        }
    }
    public static boolean solve(int indx,int balance,int prev) {
        if(indx==m) {
            print();
            return true;
        }
        for(int i=Math.abs(balance)+1;i<=10;i++) {
            if(weights[i] && i!=prev) {
                arr.add(i);
                if(balance<0) {
                    if(solve(indx+1,balance+i,i)) {
                        return true;
                    }
                }
                else {
                    if(solve(indx+1,balance-i,i)) {
                        return true;
                    }
                }
                arr.remove(arr.size()-1);
            }
        }
        return false;
    }
    public static void print() {
        System.out.println("YES");
        for(int i=0;i<arr.size();i++) {
            System.out.print(arr.get(i)+" ");
        }
        System.out.println();
    }
}
