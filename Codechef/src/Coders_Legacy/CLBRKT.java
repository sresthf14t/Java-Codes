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
public class CLBRKT {
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
            String str=input.scanString();
            int n=str.length();
            int arr[]=solve(n,str);
            int query=input.scanInt();
            for(int i=0;i<query;i++) {
                int indx=input.scanInt()-1;
                if(arr[indx]==-1) {
                    ans.append(arr[indx]+"\n");
                }
                else {
                    ans.append((arr[indx]+1)+"\n");
                }
            }
        } 
        System.out.println(ans);
    }
    public static int[] solve(int n,String str) {
        int arr[]=new int[n];
        Arrays.fill(arr, -1);
        Stack<Integer> stck=new Stack<>();
        for(int i=0;i<n;i++) {
            if(str.charAt(i)=='(') {
                stck.push(i);
                continue;
            }
            if(stck.isEmpty()) {
                continue;
            }
            int indx=stck.pop();
            arr[indx]=i;
        }
        int curr=-1;
        for(int i=n-1;i>=0;i--) {
            if(arr[i]!=-1) {
                curr=arr[i];
                continue;
            }
            if(str.charAt(i)=='(') {
                curr=-1;
                continue;
            }
            arr[i]=curr;
        }
//        for(int i=0;i<n;i++) {
//            System.out.print(arr[i]+" ");
//        }
//        System.out.println();
        return arr;
    }
}
