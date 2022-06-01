/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_638_Div_2;

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
    static boolean weights[];
    static int m;
    static ArrayList<Integer> arr;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int test=input.scanInt();
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            int k=input.scanInt();
            String str=input.scanString();
            System.out.println(solve(n,k,str));
        }
    }
    public static String solve(int n,int k,String str) {
        int chr[]=new int[26];
        for(int i=0;i<n;i++) {
            chr[str.charAt(i)-97]++;
        }
        StringBuilder ans[]=new StringBuilder[k];
        for(int i=0;i<k;i++) {
            ans[i]=new StringBuilder("");
        }
        for(int i=0,j=0;i<k;i++) {
            while(chr[j]<=0) {
                j++;
            }
            ans[i].append((char)(97+j));
            chr[j]--;
        }
        int left=0,left_indx=-1;
        for(int i=0;i<26;i++) {
            if(chr[i]>0) {
                left++;
                left_indx=i;
            }
        }
        if(left==1) {
            char tmp=(char)(97+left_indx);
            for(int i=0,j=0;i<chr[left_indx];i++) {
                if(ans[j].charAt(0)==ans[0].charAt(0)) {
                    ans[j].append(tmp);
                    j++;
                }
                else {
                    j=0;
                    ans[j].append(tmp);
                    j++;
                }
                if(j==k) {
                    j=0;
                }
            }
            int indx=0;
            for(int i=0;i<k;i++) {
                if(ans[i].charAt(0)>ans[indx].charAt(0)) {
                    indx=i;
                }
            }
            return ""+ans[indx];
        }
        for(int i=0;i<26;i++) {
            char tmp=(char)(97+i);
            while(chr[i]!=0) {
                ans[0].append(tmp);
                chr[i]--;
            }
        }
        int indx=0;
        for(int i=0;i<k;i++) {
            if(ans[i].charAt(0)>ans[indx].charAt(0)) {
                indx=i;
            }
        }
        return ""+ans[indx];
    }
}
