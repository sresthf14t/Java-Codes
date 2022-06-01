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
public class B_Hometask {
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
        int freq[]=new int[10];
        for(int i=0;i<n;i++) {
            freq[input.scanInt()]++;
        }
        System.out.println(solve(n,freq));
    }
    public static StringBuilder solve(int n,int freq[]) {
        if(freq[0]==0) {
            return new StringBuilder("-1");
        }
        int sum=0;
        for(int i=0;i<freq.length;i++) {
            sum+=(i*freq[i]);
        }
        if(sum%3==1) {
            if(freq[1]>0) {
                sum-=1;
                freq[1]--;
            }
            else if(freq[4]>0) {
                sum-=4;
                freq[4]--;
            }
            else if(freq[7]>0) {
                sum-=7;
                freq[7]--;
            }
        }
        else if(sum%3==2) {
            if(freq[2]>0) {
                sum-=2;
                freq[2]--;
            }
            else if(freq[5]>0) {
                sum-=5;
                freq[5]--;
            }
            else if(freq[8]>0) {
                sum-=8;
                freq[8]--;
            }
            else if(freq[1]>1) {
                sum-=2;
                freq[1]-=2;
            }
            else if(freq[4]>1) {
                sum-=8;
                freq[4]-=2;
            }
            else if(freq[7]>1) {
                sum-=14;
                freq[7]-=2;
            }
        }
        while(sum%3!=0) {
            boolean sub=false;
            for(int i=1;i<freq.length;i++) {
                if(i%3==0) {
                    continue;
                }
                if(freq[i]>0) {
                    sum-=i;
                    freq[i]--;
                    sub=true;
                    break;
                }
            }
            if(!sub) {
                break;
            }
        }
        if(sum%3!=0) {
            return new StringBuilder("-1");
        }
        StringBuilder ans=new StringBuilder("");
        for(int i=freq.length-1;i>=0;i--) {
            for(int j=0;j<freq[i];j++) {
                ans.append(i);
            }
        }
        if(ans.charAt(0)=='0') {
            ans=new StringBuilder("0");
        }
        return ans;
    }
}
