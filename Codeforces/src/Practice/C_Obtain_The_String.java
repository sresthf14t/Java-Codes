/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class C_Obtain_The_String {
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
        for(int tt=1;tt<=test;tt++) {
            String s=input.scanString();
            String t=input.scanString();
            System.out.println(solve(s,t));
        }
    }
    public static int solve(String s,String t) {
        int indx[][]=new int[26][s.length()];
        for(int i=0;i<indx.length;i++) {
            int curr=-1;
            for(int j=s.length()-1;j>=0;j--) {
                indx[i][j]=curr;
                if(s.charAt(j)==(i+97)) {
                    curr=j;
                }
            }
        }
        int cnt=0,curr=-1;
        boolean strt=false;
        for(int i=0;i<t.length();i++) {
            if(!strt) {
                if(s.charAt(0)==t.charAt(i)) {
                    curr=0;
                    strt=true;
                    continue;
                }
                else {
                    curr=indx[t.charAt(i)-'a'][0];
                    if(curr==-1) {
                        return -1;
                    }
                    strt=true;
                }
            }
            else {
               curr=indx[t.charAt(i)-'a'][curr];
               if(curr==-1) {
                   if(!strt) {
                       return -1;
                   }
                   else {
                       curr=-1;
                       strt=false;
                       cnt++;
                       i--;
                       continue;
                   }
               }
            }
        }
        if(strt) {
            cnt++;
        }
        return cnt;
    }
}
