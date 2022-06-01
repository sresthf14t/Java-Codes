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
public class B_Infinite_Prefixes {
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
            int n=input.scanInt();
            int x=input.scanInt();
            StringBuilder str=new StringBuilder(input.scanString());
//            if(x<0) {
//                for(int i=0;i<n;i++) {
//                    if(str.charAt(i)=='0'){
//                        str.setCharAt(i, '1');
//                    }
//                    else {
//                        str.setCharAt(i, '0');
//                    }
//                }
//                x*=-1;
//            }
            System.out.println(solve(n,x,str));
        }
    }
    public static int solve(int n,int x,StringBuilder str) {
        HashMap<Integer,Integer> map=new HashMap<>();
        int curr=0;
        for(int i=0;i<n;i++) {
            if(str.charAt(i)=='0') {
                curr++;
            }
            else {
                curr--;
            }
            if(!map.containsKey(curr)) {
                map.put(curr, 0);
            }
            map.replace(curr, map.get(curr)+1);
        }
        int ans=0;
        if(x==0 && curr==0) {
            return -1;
        }
        if(curr==0 && map.containsKey(x)) {
            return -1;
        }
        if(curr==0) {
            if(map.containsKey(x)) {
                ans=map.get(x);
            }
            if(x==0) {
                ans++;
            }
            return ans;
        }
        int l=(x-n)/curr;
        l=Math.max(0,l);
        l*=curr;
        int r=(x+n)/curr;
        r*=curr;
//        System.out.println(l+" "+r+" "+curr);
        if(curr>0) {
            if(l<0) {
                l=0;
            }
            if(r<0) {
                r=0;
            }
            for(int i=l;i<=r;i+=curr) {
                if(map.containsKey(x-i)) {
                    ans+=map.get(x-i);
                }
            }
        }
        else if(curr<0) {
            if(l>0) {
                l=0;
            }
            if(r>0) {
                r=0;
            }
            for(int i=r;i>=l;i+=curr) {
                if(map.containsKey(x-i)) {
//                    System.out.println(x+" "+i+" "+map.get(x-i));
                    ans+=map.get(x-i);
                }
            }
        }
        if(x==0) {
            ans++;
        }
        return ans;
    }
}
