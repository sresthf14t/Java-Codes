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
        for(int t=1;t<=test;t++) {
            String str=input.scanString();
            StringBuilder ans=solve(str.length(),str);
//            System.out.println(ans);
            for(int i=0;i<ans.length()-1;i++) {
                if(ans.charAt(i)==ans.charAt(i+1)) {
                    ans=new StringBuilder("-1");
                    break;
                }
            }
            System.out.println(ans);
        }
    }
    public static StringBuilder solve(int n,String str) {
        StringBuilder ans=new StringBuilder("");
        for(int i=0;i<n;i++) {
            if(str.charAt(i)!='?') {
                ans.append(str.charAt(i));
                continue;
            }
            int bef=-1,aft=-1;
            int j=i;
            int cnt=0;
            while(j<n && str.charAt(j)=='?') {
                j++;
                cnt++;
            }
            if(i!=0) {
                bef=str.charAt(i-1);
            }
            if(j<n) {
                aft=str.charAt(j);
            }
            char chr[]={'a','b','c'};
            int strt=0,end=-1;
            for(int k=0;k<cnt;k++) {
                if(k==cnt-1) {
                    end=strt;
                }
                strt=(strt+1)%3;
            }
            if(chr[0]!=bef && chr[end]!=aft) {
                strt=0;
                for(int k=0;k<cnt;k++) {
                    ans.append(chr[strt]);
                    strt=(strt+1)%3;
                }
                i=j-1;
                continue;
            }
            strt=1;
            end=-1;
            for(int k=0;k<cnt;k++) {
                if(k==cnt-1) {
                    end=strt;
                }
                strt=(strt+1)%3;
            }
            if(chr[1]!=bef && chr[end]!=aft) {
                strt=1;
                for(int k=0;k<cnt;k++) {
                    ans.append(chr[strt]);
                    strt=(strt+1)%3;
                }
                i=j-1;
                continue;
            }
            
            strt=2;
            end=-1;
            for(int k=0;k<cnt;k++) {
                if(k==cnt-1) {
                    end=strt;
                }
                strt=(strt+1)%3;
            }
            if(chr[2]!=bef && chr[end]!=aft) {
                strt=2;
                for(int k=0;k<cnt;k++) {
                    ans.append(chr[strt]);
                    strt=(strt+1)%3;
                }
                i=j-1;
                continue;
            }
        }
        return ans;
    }
}
