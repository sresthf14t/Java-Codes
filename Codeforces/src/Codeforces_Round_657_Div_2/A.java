/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_657_Div_2;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class A {
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
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            StringBuilder str=new StringBuilder(input.scanString());
            StringBuilder tmp=new StringBuilder(""+str);
            if(solve(n,tmp)) {
                ans.append("YES\n");
                ans.append(tmp+"\n");
                continue;
            }
            boolean is_pos=false;
            for(int i=0;i<n;i++) {
                if(str.charAt(i)=='?') {
                    str.setCharAt(i, 'z');
                }
                tmp=new StringBuilder(""+str);
                if(solve(n,tmp)) {
                    is_pos=true;
                    ans.append("YES\n");
                    ans.append(tmp+"\n");
                    break;
                }
            }
            if(!is_pos) {
                ans.append("NO\n");
            }
        }
        System.out.println(ans);
    }
    public static boolean solve(int n,StringBuilder str) {
        String tmp="abacaba";
        int cnt=0;
        for(int i=0;i<str.length();i++) {
            if(str.charAt(i)!='a') {
                continue;
            }
            if(i+tmp.length()>n) {
                break;
            }
            if(str.substring(i, i+tmp.length()).equals(tmp)) {
                cnt++;
            }
        }
        if(cnt>1) {
            return false;
        }
        if(cnt==1) {
            fill(str);
            cnt=0;
            for(int i=0;i<str.length();i++) {
                if(str.charAt(i)!='a') {
                    continue;
                }
                if(i+tmp.length()>n) {
                    break;
                }
                if(str.substring(i, i+tmp.length()).equals(tmp)) {
                    cnt++;
                }
            }
            if(cnt==1) {
                return true;
            }
            return false;
        }
        
        int indx=0;
        for(int i=0;i<n;i++) {
            if(indx==tmp.length()) {
                break;
            }
            if(str.charAt(i)=='?') {
                str.setCharAt(i, tmp.charAt(indx));
                indx++;
                continue;
            }
            if(str.charAt(i)==tmp.charAt(indx)) {
                indx++;
                continue;
            }
            else {
                indx=0;
            }
        }
        if((""+str).contains("abacaba")) {
            fill(str);
            cnt=0;
            for(int i=0;i<str.length();i++) {
                if(str.charAt(i)!='a') {
                    continue;
                }
                if(i+tmp.length()>n) {
                    break;
                }
                if(str.substring(i, i+tmp.length()).equals(tmp)) {
                    cnt++;
                }
            }
            if(cnt==1) {
                return true;
            }
        }
        return false;
    }
    public static void fill(StringBuilder str) {
        for(int i=0;i<str.length();i++) {
            if(str.charAt(i)=='?') {
                str.setCharAt(i, 'z');
            }
        }
    }
}
