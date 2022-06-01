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
public class D_Good_Substrings {
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
    static class node {
        node next[];
        public node() {
            next=new node[26];
        }
    }
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        String str=input.scanString();
        boolean good[]=new boolean[26];
        String tmp=input.scanString();
        for(int i=0;i<tmp.length();i++) {
            if(tmp.charAt(i)=='1') {
                good[i]=true;
            }
        }
        int k=input.scanInt();
        System.out.println(solve(str,good,k));
    }
    public static int solve(String str,boolean good[],int k) {
        int ans=0;
        node root=new node();
        for(int i=0;i<str.length();i++) {
            int cnt=0;
            node curr=root;
            for(int j=i;j<str.length();j++) {
                if(!good[str.charAt(j)-97]) {
                    cnt++;
                }
                if(cnt<=k) {
                    if(curr.next[str.charAt(j)-97]==null) {
                        node tmp=new node();
                        curr.next[str.charAt(j)-97]=tmp;
                        curr=tmp;
                        ans++;
                    }
                    else {
                        curr=curr.next[str.charAt(j)-97];
                    }
                }
                else {
                    break;
                }
            }
        }
        return ans;
    }
}
