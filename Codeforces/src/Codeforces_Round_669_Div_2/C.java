/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_669_Div_2;

/**
 *
 * @author Srest
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
        int curr=0;
        int arr[]=new int[n];
        for(int i=1;i<n;i++) {
            System.out.println("? "+(curr+1)+" "+(i+1));
            System.out.flush();
            int x=input.scanInt();
            System.out.println("? "+(i+1)+" "+(curr+1));
            System.out.flush();
            int y=input.scanInt();
            if(x>y) {
                arr[curr]=x;
                curr=i;
            }
            else {
                arr[i]=y;
            }
        }
        Set<Integer> hashset=new HashSet<>();
        for(int i=0;i<n;i++) {
            hashset.add(arr[i]);
        }
        int ele=1;
        while(hashset.contains(ele)) {
            ele++;
        }
        for(int i=0;i<n;i++) {
            if(arr[i]==0) {
                arr[i]=ele;
                break;
            }
        }
        StringBuilder ans=new StringBuilder("");
        for(int i=0;i<n;i++) {
            ans.append(arr[i]+" ");
        }
        System.out.println("! "+ans);
        System.out.flush();
    }
}
