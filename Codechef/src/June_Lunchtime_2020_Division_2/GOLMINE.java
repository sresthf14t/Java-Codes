/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package June_Lunchtime_2020_Division_2;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class GOLMINE {
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
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            int mine[]=new int[n];
            int time_a[]=new int[n];
            int time_b[]=new int[n];
        }
        System.out.println(ans);
    }
    public static float solve(int n,int mine[],int time_a[],int time_b[]) {
        int indx_a[]=new int[n];
        int indx_b[]=new int[n];
        for(int i=0;i<n;i++) {
            indx_a[i]=i;
            indx_b[i]=i;
        }
        double got_1=0,got_2=0;
        for(int i=0;i<n;i++) {
            if(time_a[i]==time_b[i]) {
                got_1+=(double)mine[i]/(double)2;
                got_2+=(double)mine[i]/(double)2;
                mine[i]=0;
            }
        }
        
        
        double time=0,strt1=0,end1=0,strt2=0,end2=0;
        int indx1=0,indx2=0;
        
        while(true) {
            for(int i=indx1;i<n;i++) {
                if(mine[indx_a[i]]!=0) {
                    indx1=i;
                    break;
                }
            }
            for(int i=indx2;i<n;i++) {
                if(mine[indx_b[i]]!=0) {
                    indx2=i;
                    break;
                }
            }
            if(indx_a[indx1]==indx_b[indx2]) {
                got_1+=(double)mine[indx_a[indx1]]*(((double)(time_b[indx2])/(double)(time_a[indx1]+time_b[indx2])));
                got_2+=(double)mine[indx_a[indx1]]*(((double)(time_a[indx2])/(double)(time_a[indx1]+time_b[indx2])));
                continue;
            }
            double time_a_req=(double)mine[indx_a[indx1]]/(double)time_a[indx1];
            double time_b_req=(double)mine[indx_b[indx2]]/(double)time_b[indx2];
            if(time_a_req==time_b_req) {
                
            }
        }
        
    }
}
