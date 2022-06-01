/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_670_Div_2;

/**
 *
 * @author Srest
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
        StringBuilder ans=new StringBuilder("");
        for(int tt=1;tt<=test;tt++) {
            int n=input.scanInt();
            ArrayList<Integer> pos=new ArrayList<>();
            ArrayList<Integer> neg=new ArrayList<>();
            for(int i=0;i<n;i++) {
                int tmp=input.scanInt();
                if(tmp<0) {
                    neg.add(tmp);
                }
                else {
                    pos.add(tmp);
                }
            }
            pos.sort(null);
            neg.sort(null);
            ArrayList<Integer> arrli=new ArrayList<>();
            int cnt=0;
            for(int i=pos.size()-1;i>=0;i--) {
                arrli.add(pos.get(i));
                cnt++;
                if(cnt==5) {
                    break;
                }
            }
            cnt=0;
            for(int i=0,j=neg.size()-1;i<=j;i++,j--) {
                if(i==j) {
                    arrli.add(neg.get(i));
                    break;
                }
                arrli.add(neg.get(i));
                arrli.add(neg.get(j));
                cnt+=2;
                if(cnt==10) {
                    break;
                }
            }
            int tmp[]=new int[arrli.size()];
            for(int i=0;i<arrli.size();i++) {
                tmp[i]=arrli.get(i);
            }
            ans.append(solve(0,tmp,5,1)+"\n");
        }
        System.out.println(ans);
    }
    public static long solve(int indx,int tmp[],int rem,long ans) {
        if(indx==tmp.length) {
            if(rem==0) {
                return ans;
            }
            return Long.MIN_VALUE;
        }
        long max=Long.MIN_VALUE;
        max=Math.max(max,solve(indx+1,tmp,rem,ans));
        max=Math.max(max,solve(indx+1,tmp,rem-1,ans*tmp[indx]));
        return max;
    }
}
