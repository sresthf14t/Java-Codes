/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_660_Div_2;

/**
 *
 * @author Srest
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
    static int primes[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int test=input.scanInt();
        StringBuilder ans=new StringBuilder("");
//        sieve=new boolean[200001];
//        primes=new int[17984];
//        sieve(sieve.length);
//        al_primes();
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            int tmp=n;
            tmp-=(6+10+14);
            if(tmp>0 && tmp!=6 && tmp!=10 && tmp!=14) {
                ans.append("YES\n");
                ans.append(6+" "+10+" "+14+" "+tmp+"\n");
                continue;
            }
            n-=(6+10+15);
            if(n>0 && n!=6 && n!=10 && n!=15){
                ans.append("YES\n");
                ans.append(6+" "+10+" "+15+" "+n+"\n");
                continue;
            }
            else {
                ans.append("NO\n");
            }
        }
        System.out.println(ans);
    }
    static boolean sieve[];
    //false for prime number and true for composite number
    public static void sieve(int n) {
        int indx=0;
        for(int i=2;i<n;i++) {
            if(!sieve[i]) {
                primes[indx]=i;
                indx++;
                for(int j=2*i;j<n;j=j+i) {
                    sieve[j]=true;
                }
            }        
        }
//        System.out.println(indx);
    }
    public static void al_primes() {
        int indx=0;
        for(int i=2;i<sieve.length;i++) {
            int cnt=0;
            int num=i;
            boolean is_pos=true;
            for(int j=0;j<primes.length;j++) {
                int tmp=0;
                while(num%primes[j]==0) {
                    num/=primes[j];
                    cnt++;
                    tmp++;
                }
                if(tmp>1) {
                    is_pos=false;
                    break;
                }
                if(num==1) {
                    break;
                }
            }
            if(is_pos && cnt==2) {
                System.out.print(i+",");
            }
        }
        System.out.println(indx);
    }
}
