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
public class C_Hacking_Cypher {
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
        String str=input.scanString();
        long a=input.scanInt();
        long b=input.scanInt();
        System.out.println(solve(str,a,b));
    }
    public static StringBuilder solve(String str,long a,long b) {
        long pow[]=new long[str.length()+1];
        long tmp=1;
        for(int i=0;i<pow.length;i++) {
            pow[i]=tmp;
            tmp*=10;
            tmp%=b;
        }
        long lft=0,rgt=0;
        for(int i=0;i<str.length();i++) {
            rgt=(rgt*10)+(str.charAt(i)-48);
            rgt%=b;
        }
        int indx=-1;
        for(int i=0;i<str.length()-1;i++) {
            lft=(lft*10)+(str.charAt(i)-48);
            lft%=a;
            rgt-=pow[str.length()-i-1]*(str.charAt(i)-48);
            rgt%=b;
            while(rgt<0) {
                rgt+=b;
            }
//            System.out.println(lft+" "+rgt);
            if(lft==0 && rgt==0) {
                indx=i;
                if(str.charAt(i+1)!='0') {
                    break;
                }
            }
        }
        if(indx==-1 || str.charAt(indx+1)=='0') {
            return new StringBuilder("NO");
        }
        StringBuilder ans=new StringBuilder("YES\n");
        for(int i=0;i<str.length();i++) {
            ans.append(str.charAt(i));
            if(i==indx) {
                ans.append("\n");
            }
        }
        return ans;
    }
}
