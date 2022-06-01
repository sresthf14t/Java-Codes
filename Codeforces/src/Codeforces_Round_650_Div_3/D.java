/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_650_Div_3;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class D {
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
    public static void main(String arsg[]) throws IOException {
        Scan input=new Scan();
        int test=input.scanInt();
        for(int t=1;t<=test;t++) {
            String str=input.scanString();
            int chr[]=new int[26];
            for(int i=0;i<str.length();i++) {
                chr[str.charAt(i)-97]++;
            }
            int n=input.scanInt();
            int arr[]=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt();
            }
            System.out.println(solve(n,arr,chr));
        }
    }
    public static String solve(int n,int arr[],int chr[]) {
        int ans[]=new int[n];
        int chr_indx=25;
        while(true) {
//            for(int i=0;i<n;i++) {
//                System.out.print(arr[i]+" ");
//            }
//            System.out.println();
            boolean done=false;
            int cnt=0;
            ArrayList<Integer> arrli=new ArrayList<>();
            for(int i=0;i<n;i++) {
                if(arr[i]==0) {
                    arrli.add(i);
                    done=true;
                    cnt++;
                }
            }
//            System.out.println(arrli.size());
            int indx=-1;
            for(int i=chr_indx;i>=0;i--) {
                if(chr[i]>=cnt) {
                    chr_indx=i-1;
                    indx=i;
                    chr[i]=-cnt;
                    break;
                }
            }
            for(int k=0;k<arrli.size();k++) {
                int i=arrli.get(k);
                arr[i]=-1;
                ans[i]=indx;
                for(int j=i-1,count=1;j>=0;j--,count++) {
                    if(arr[j]>0) {
                        arr[j]-=count;
                    }
                }
                for(int j=i+1,count=1;j<n;j++,count++) {
                    if(arr[j]>0) {
                        arr[j]-=count;
                    }
                }
            }
//            for(int i=0;i<n;i++) {
//                if(arr[i]==0) {
//                    arr[i]--;
//                }
//            }
            if(!done) {
                break;
            }
        }
        StringBuilder fin=new StringBuilder("");
        for(int i=0;i<n;i++) {
            fin.append((char)(ans[i]+97));
        }
        return ""+fin;
    }
}
