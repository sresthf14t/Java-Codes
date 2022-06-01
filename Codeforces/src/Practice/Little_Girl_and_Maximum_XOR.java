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
public class Little_Girl_and_Maximum_XOR {
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
        long l=input.nextLong();
        long r=input.nextLong();
        System.out.println(solve(l,r));
    }
    public static long solve(long l,long r) {
        StringBuilder min=dec_to_bin(l);
        StringBuilder max=dec_to_bin(r);
        StringBuilder ans=new StringBuilder("");
        while(min.length()!=max.length()) {
            min.insert(0, '0');
        }
//        System.out.println(min+" "+max);
        int min_up=Integer.MAX_VALUE;
        for(int i=0;i<min.length();i++) {
            if(max.charAt(i)=='1') {
                min_up=i;
                break;
            }
        }
        int min_down=Integer.MAX_VALUE;
        for(int i=min.length()-1;i>=0;i--) {
            if(max.charAt(i)=='1') {
                min_down=i;
                break;
            }
        }
        
        int max_up=Integer.MAX_VALUE;
        for(int i=0;i<min.length();i++) {
            if(max.charAt(i)=='0') {
                max_up=i;
                break;
            }
        }
        int max_down=Integer.MAX_VALUE;
        for(int i=0;i<max.length();i++) {
            if(min.charAt(i)=='1') {
                max_down=i;
                break;
            }
        }
        
        for(int i=0;i<max.length();i++) {
            if(min.charAt(i)=='1' && max.charAt(i)=='1') {
                if(i<min_down || i>max_down) {
                    ans.append('1');
                }
                else {
                    ans.append('0');
                }
            }
            else if(min.charAt(i)=='1' || max.charAt(i)=='1') {
                ans.append('1');
            }
            else {
                if(i>min_up || i>max_up) {
                    ans.append('1');
                }
                else {
                    ans.append('0');
                }
            }
        }
//        System.out.println(ans);
        return bin_to_dec(ans);
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
                ans+=(long)Math.pow(2,str.length()-i-1);
            }
        }
        return ans;
    }
}
