/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AtCoder_Beginner_Contest_171;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class C {
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
    static StringBuilder ans;
    static long pow[];
    static char chr[]={' ','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    static HashMap<Long,Integer> map[];
    public static void main(String args[]) throws IOException {
        Scanner input=new Scanner(System.in);
        long n=input.nextLong();
        pow=new long[12];
        pow[0]=1;
        map=new HashMap[n];
        for(int i=0;i<n;i++) {
            map[i]=new HashMap<>();
        }
        for(int i=1;i<pow.length;i++) {
            pow[i]=26*pow[i-1];
        }
        ans=new StringBuilder("");
        solve(10,n,new StringBuilder(""));
        System.out.println(ans);
    }
    public static boolean solve(int indx,long n,StringBuilder tmp) {
//        System.out.println(indx+" "+n);
        if(n<0) {
            return false;
        }
        if(indx==-1) {
            if(n!=0) {
                return false;
            }
            ans=new StringBuilder(""+tmp);
            return true;
        }
        if(map[indx].containsKey(n)) {
            map[indx].put(n, indx);
            return false;
        }
        if(tmp.length()==0) {
            if(solve(indx-1,n,tmp)) {
                return true;
            }
        }
        if(pow[indx]>n) {
            return false;
        }
        for(int i=26;i>=1;i--) {
            if(i*pow[indx]<=n) {
                tmp.append(chr[i]);
                if(solve(indx-1,n-(i*pow[indx]),tmp)) {
                    return true;
                }
                tmp.deleteCharAt(tmp.length()-1);
            }
        }
        map[indx].put(n, indx);
        return false;
    }
}
