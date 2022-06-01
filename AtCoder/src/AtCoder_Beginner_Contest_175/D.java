/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AtCoder_Beginner_Contest_175;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class D {
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
        int n=input.scanInt();
        int k=input.scanInt();
        int p[]=new int[n];
        boolean in[]=new boolean[n];
        for(int i=0;i<n;i++) {
            p[i]=input.scanInt()-1;
            in[p[i]]=true;
        }
        int c[]=new int[n];
        long max=Long.MIN_VALUE;
        for(int i=0;i<n;i++) {
            c[i]=input.scanInt();
            if(in[i]) {
                max=Math.max(max,c[i]);
            }
        }
        if(max<=0) {
            System.out.println(max);
            return;
        }
        long ans=Long.MIN_VALUE;
        for(int i=0;i<n;i++) {
            ans=Math.max(ans,solve(n,k+1,p,c,i));
        }
        System.out.println(ans);
    }
    public static long solve(int n,int k,int p[],int c[],int strt) {
        long ans=Long.MIN_VALUE,curr=0;
        int vis[]=new int[n];
        Arrays.fill(vis, -1);
        int indx=strt,cnt=0;
        for(int i=0;i<k;i++) {
            if(vis[indx]!=-1) {
                break;
            }
            vis[indx]=cnt;
            curr+=c[indx];
            if(i!=0) {
                ans=Math.max(ans,curr);
            }
            cnt++;
            indx=p[indx];
        }
//        System.out.println(ans);
//        if(vis[indx]==-1) {
//            return ans-c[strt];
//        }
        long curr1=0;
        int indx1=strt;
        while(indx1!=indx) {
            curr1+=c[indx1];
            indx1=p[indx1];
            k--;
        }
        long curr2=0;
        int indx2=indx;
        boolean done=false;
        int len=0;
        while(!done || indx2!=indx) {
            done=true;
            curr2+=c[indx2];
            indx2=p[indx2];
            len++;
        }
        
        long times=k/len;
        curr2*=times;
        k%=len;
        curr=curr1+curr2;
//        System.out.println(indx+" "+curr1+" "+curr2+" "+k);
        ans=Math.max(ans,curr);
        while(k!=0) {
            curr+=c[indx];
            indx=p[indx];
            k--;
            ans=Math.max(ans,curr);
        }
//        System.out.println(ans);
        ans-=c[strt];
//        System.out.println(ans);
        return ans;
    }
}