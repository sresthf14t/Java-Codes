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
public class D_Minimum_Euler_Cycle {
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
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=1;t<=test;t++) {
            long n=input.nextLong();
            long l=input.nextLong();
            long r=input.nextLong();
            long val1=1,val2=2,strt=1;
            for(int i=1;;i++) {
                strt+=2*(n-i);
                val1++;
                val2++;
                if(strt==l) {
                    break;
                }
                if(strt>l) {
                    strt-=2*(n-i);
                    val1--;
                    val2--;
                    break;
                }
            }
//            System.out.println(strt);
            ArrayList<Long> arrli=new ArrayList<>();
            while(strt<=r) {
                if(val1==n) {
                    arrli.add(1L);
                    strt++;
                }
                long tmp1=val1,tmp2=val2;
                while(tmp2<=n) {
                    if(strt>=l && strt<=r) {
                        arrli.add(tmp1);
                    }
                    strt++;
                    if(strt>=l && strt<=r) {
                        arrli.add(tmp2);
                    }
                    tmp2++;
                    strt++;
                }
                val1++;
                val2++;
            }
//            System.out.println(arrli.size());
            StringBuilder ans=new StringBuilder("");
            for(int i=0;i<arrli.size();i++) {
                ans.append(arrli.get(i)+" ");
            }
            System.out.println(ans);
        }
        
    }
}
