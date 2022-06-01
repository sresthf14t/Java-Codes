/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AtCoder_Beginner_Contest_172;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class F {
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
    static long arr[],pow[],min;
    public static void main(String args[]) throws IOException {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        arr=new long[n];
        pow=new long[63];
        pow[0]=1;
        for(int i=1;i<pow.length;i++) {
            pow[i]=2*pow[i-1];
        }
        long ans=0;
        for(int i=0;i<n;i++) {
            arr[i]=input.nextLong();
        }
        for(int i=2;i<n;i++) {
            ans^=arr[i];
        }
        StringBuilder tmp=dec_to_bin(ans);
        min=-1;
        solve(0,tmp,0,0);
        System.out.println(min);
    }
    public static void solve(int indx,StringBuilder tmp,long a,long b) {
        if(a>arr[0]) {
            return;
        }
        System.out.println(indx);
        if(indx==tmp.length()) {
            long first=a;
            long sec=b;
            if(first==0 || sec==0) {
                return;
            }
            if(first<=arr[0] && sec>=arr[1]) {
                long diff=arr[0]-first;
                if(arr[1]+diff==sec) {
//                    System.out.println(first+" "+sec);
                    if(min==-1) {
                        min=diff;
                    }
                    else {
                        min=Math.min(min,diff);
                    }
                }
            }
            return;
        }
        if(tmp.charAt(indx)=='0') {
            solve(indx+1,tmp,a,b);
            
            a+=pow[tmp.length()-indx-1];
            b+=pow[tmp.length()-indx-1];
            solve(indx+1,tmp,a,b);
            a-=pow[tmp.length()-indx-1];
            b-=pow[tmp.length()-indx-1];
        }
        else {
            a+=pow[tmp.length()-indx-1];
            solve(indx+1,tmp,a,b);
            a-=pow[tmp.length()-indx-1];
            
            b+=pow[tmp.length()-indx-1];
            solve(indx+1,tmp,a,b);
            b-=pow[tmp.length()-indx-1];
        }
    }
    public static StringBuilder dec_to_bin(long n) {
        StringBuilder ans=new StringBuilder("");
        while(n!=0) {
            ans.append(n%2);
            n/=2;
        }
        while(ans.length()!=40) {
            ans.append(0);
        }
        ans=ans.reverse();
        return ans;
    }
}
