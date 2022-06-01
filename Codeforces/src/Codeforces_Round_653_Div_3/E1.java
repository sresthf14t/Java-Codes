/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_653_Div_3;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class E1 {
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
        int test=1;
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            int k=input.scanInt();
            int arr[]=new int[n];
            boolean alice[]=new boolean[n];
            boolean bob[]=new boolean[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt();
                if(input.scanInt()==1) {
                    alice[i]=true;
                }
                if(input.scanInt()==1) {
                    bob[i]=true;
                }
            }
            ans.append(solve(n,k,arr,alice,bob));
        }
        System.out.println(ans);
    }
    public static long solve(int n,int k,int arr[],boolean alice[],boolean bob[]) {
        int both=0,al1=0,bo1=0;
        for(int i=0;i<n;i++) {
            if(alice[i] && bob[i]) {
                both++;
            }
            else if(alice[i]) {
                al1++;
            }
            else if(bob[i]) {
                bo1++;
            }
        }
        int bot[]=new int[both];
        int al[]=new int[al1];
        int bo[]=new int[bo1];
        int indx1=0,indx2=0,indx3=0;
        for(int i=0;i<n;i++) {
            if(alice[i] && bob[i]) {
                bot[indx1]=arr[i];
                indx1++;
            }
            else if(alice[i]) {
                al[indx2]=arr[i];
                indx2++;
            }
            else if(bob[i]) {
                bo[indx3]=arr[i];
                indx3++;
            }
        }
        Arrays.sort(bot);
        Arrays.sort(al);
        Arrays.sort(bo);
        indx1=indx2=indx3=0;
        long ans=0;
        for(int i=0;i<k;i++) {
            if(indx1==bot.length && (indx2==al.length || indx2==bo.length)) {
                return -1;
            }
            if(indx1==bot.length) {
                ans+=al[indx2]+bo[indx2];
                indx2++;
                continue;
            }
            if(indx2==al.length || indx2==bo.length) {
                ans+=bot[indx1];
                indx1++;
                continue;
            }
            if(bot[indx1]<=al[indx2]+bo[indx2]) {
                ans+=bot[indx1];
                indx1++;
            }
            else {
                ans+=al[indx2]+bo[indx2];
                indx2++;
            }
        }
        return ans;
    }
}
