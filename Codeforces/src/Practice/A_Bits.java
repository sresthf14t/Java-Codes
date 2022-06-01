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
import java.io.*; 
import java.util.*;
public class A_Bits {
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
    static long pow[];
    public static void main(String args[]) throws IOException {
        Scanner input=new Scanner(System.in);
        pow=new long[64];
        long p=1;
        for(int i=0;i<pow.length;i++) {
            pow[i]=p-1;
            p*=2;
        }
        int test=input.nextInt();
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            long l=input.nextLong();
            long r=input.nextLong();
            ans.append(solve(l,r));
            ans.append("\n");
        }
        System.out.println(ans);
    }
    public static long solve(long l,long r) {
        for(int i=pow.length-1;i>=0;i--) {
            if(pow[i]>=l && pow[i]<=r) {
                return pow[i];
            }
        }
        StringBuilder min=dec_to_bin(l);
        StringBuilder max=dec_to_bin(r);
//        System.out.println(min+"\n"+max);
        boolean one=false;
        StringBuilder ans=new StringBuilder("1");
        for(int i=1;i<max.length();i++) {
            if(!one && max.charAt(i)=='1' && min.charAt(i)=='0' && n_zero(max,i)>0) {
                one=true;
                ans.append('0');
                continue;
            }
            if(one) {
                ans.append('1');
            }
            else {
                ans.append(max.charAt(i));
            }
        }
        return bin_to_dec(ans);
    }
    public static int n_zero(StringBuilder str,int indx) {
        int cnt=0;
        for(int i=indx+1;i<str.length();i++) {
            if(str.charAt(i)=='0') {
                cnt++;
            }
        }
//        System.out.println(indx+" "+cnt);
        return cnt;
    }
    public static StringBuilder dec_to_bin(long n) {
        StringBuilder ans=new StringBuilder("");
        while(n!=0) {
            ans.append(n%2);
            n/=2;
        }
        ans.reverse();
        return ans;
    }
    public static long bin_to_dec(StringBuilder str) {
        long ans=0;
        for(int i=0;i<str.length();i++) {
            if(str.charAt(i)=='1') {
                ans+=pow[str.length()-i-1]+1;
            }
        }
        return ans;
    }
}
