/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Educational_Codeforces_Round_89;

/**
 *
 * @author User
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
    static ArrayList<Integer> adj_lst[];
    static boolean vis[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int test=input.scanInt();
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            int x=input.scanInt();
            int m=input.scanInt();
            int l[]=new int[m];
            int r[]=new int[m];
            for(int i=0;i<m;i++) {
                l[i]=input.scanInt();
                r[i]=input.scanInt();
            }
            int lft=Integer.MAX_VALUE,rgt=Integer.MIN_VALUE;
            ArrayList<Integer> a=new ArrayList<>();
            for(int i=0;i<m;i++) {
                if(x>=l[i] && x<=r[i]);
                else {
                    continue;
                }
                a.add(i);
                for(int j=i;j<m;j++) {
                    for(int k=0;k<a.size();k++) {
                       if(!(r[a.get(k)]<l[j] || l[a.get(k)]>r[j])) {
                            lft=Math.min(lft,l[j]);
                            rgt=Math.max(rgt,r[j]);
                            a.add(j);
                            break;
                        } 
                    }
                }
                break;
            }
            if(lft==Integer.MAX_VALUE) {
                System.out.println(1);
                continue;
            }
            System.out.println(rgt-lft+1);
        }
    }
}
