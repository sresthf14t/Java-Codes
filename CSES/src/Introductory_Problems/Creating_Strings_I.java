/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Introductory_Problems;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class Creating_Strings_I {
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
    static StringBuilder ans;
    static String str;
    static int cnt;
    static Set<String> hashset;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        str=input.scanString();
        ans=new StringBuilder("");
        cnt=0;
        hashset=new HashSet<>();
        int chr[]=new int[26];
        for(int i=0;i<str.length();i++) {
            chr[str.charAt(i)-97]++;
        }
        str="";
        for(int i=0;i<chr.length;i++) {
            char app=(char)(i+97);
            for(int j=0;j<chr[i];j++) {
                str+=app;
            }
        }
        solve(0,new StringBuilder(""),new boolean[str.length()]);
        System.out.println(cnt+"\n"+ans);
    }
    public static void solve(int indx,StringBuilder tmp,boolean taken[]) {
        if(indx==str.length()) {
            if(!hashset.contains(""+tmp)) {
                ans.append(tmp+"\n");
                cnt++;
                hashset.add(""+tmp);
            }
            return;
        }
        for(int i=0;i<str.length();i++) {
            if(taken[i]) {
                continue;
            }
            tmp.append(str.charAt(i));
            taken[i]=true;
            solve(indx+1,tmp,taken);
            tmp.deleteCharAt(tmp.length()-1);
            taken[i]=false;
        }
    }
}
