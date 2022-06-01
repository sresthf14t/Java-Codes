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
public class C_Vasya_And_Array {
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
        int m=input.scanInt();
        int arr[]=new int[n];
        boolean inc[]=new boolean[n];
        boolean dec[]=new boolean[n];
        int type[]=new int[m];
        int l[]=new int[m];
        int r[]=new int[m];
        for(int i=0;i<m;i++) {
            type[i]=input.scanInt();
            l[i]=input.scanInt()-1;
            r[i]=input.scanInt()-1;
            if(type[i]==0) {
                continue;
            }
            for(int j=l[i]+1;j<=r[i];j++) {
                inc[j]=true;
            }
        }
        for(int i=0;i<m;i++) {
            if(type[i]==1) {
                continue;
            }
            boolean done=false;
            for(int j=l[i]+1;j<=r[i];j++) {
                if(!inc[j]) {
                    dec[j]=true;
                    done=true;
                }
            }
            if(!done) {
                System.out.println("NO");
                return;
            }
        }
        Arrays.fill(arr, 10000);
        for(int i=1;i<n;i++) {
            if(inc[i]) {
                arr[i]=arr[i-1]+1;
            }
            if(dec[i]) {
                arr[i]=arr[i-1]-1;
            }
        }
        System.out.println("YES");
        for(int i=0;i<n;i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
    public static boolean check(int arr[],int op[],int lft[],int rgt[]) {
        for(int i=0;i<op.length;i++) {
            if(op[i]==0) {
                if(inc(arr,lft[i],rgt[i])) {
                    return false;
                }
            }
            else {
                if(!inc(arr,lft[i],rgt[i])) {
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean inc(int arr[],int l,int r) {
        for(int i=l+1;i<=r;i++) {
            if(arr[i]<arr[i-1]) {
                return false;
            }
        }
        return true;
    }
}
