/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_607_Div_2_Virtual;

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
        for(int t=0;t<test;t++) {
            String str1=input.scanString().toLowerCase();
            String str2=input.scanString().toLowerCase();
            System.out.println(solve(str1,str2).toUpperCase());
        }
    }
    public static String solve(String str1,String str2) {
        String tmp[]={str1,str2};
        Arrays.sort(tmp);
        if(tmp[0]==str1 && !str1.equals(str2)) {
            return str1;
        }
        StringBuilder ans=new StringBuilder("");
        int freq[]=new int[26];
        for(int i=0;i<str1.length();i++) {
            freq[str1.charAt(i)-97]++;
        }
        int swap=-1;
        for(int i=0;i<str1.length() && i<str2.length();i++) {
            
            int indx=str2.charAt(i)-97;
//            System.out.println(i+" "+indx);
            for(int j=0;j<=indx;j++) {
                if((j+97)==str1.charAt(i)) {
                    break;
                }
                if(freq[j]>0) {
                    freq[j]--;
                    ans.append((char)(j+97));
                    swap=str1.charAt(i)-97;
                    break;
                }
            }
            if(swap!=-1) {
                break;
            }
            if(str1.charAt(i)==str2.charAt(i)) {
                ans.append(str1.charAt(i));
                freq[str1.charAt(i)-97]--;
            }
            else {
                return "---";
            }
        }
        
        if(swap==-1) {
            return "---";
        }
        for(int i=ans.length();i<str1.length();i++) {
            if(freq[str1.charAt(i)-97]==0) {
                ans.append((char)(swap+97));
                continue;
            }
            else {
                ans.append(str1.charAt(i));
            }
            freq[str1.charAt(i)-97]--;
        }
        String ret=""+ans;
        tmp=new String[]{ret,str2};
        Arrays.sort(tmp);
        if(tmp[0].equals(ret) && !str2.equals(ret)) {
            return ret;
        }
        return "---";
    }
}
