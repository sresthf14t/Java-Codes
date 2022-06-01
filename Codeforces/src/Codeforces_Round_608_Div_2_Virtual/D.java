/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_608_Div_2_Virtual;

/**
 *
 * @author User
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
    static ArrayList<Integer> adj_lst[];
    static int n,m,k,a[],b[],c[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n=input.scanInt();
        m=input.scanInt();
        k=input.scanInt();
        a=new int[n];
        b=new int[n];
        c=new int[n];
        adj_lst=new ArrayList[n];
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<Integer>();
            adj_lst[i].add(i);
        }
        for(int i=0;i<n;i++) {
            a[i]=input.scanInt();
            b[i]=input.scanInt();
            c[i]=input.scanInt();
        }
        for(int i=0;i<m;i++) {
            int u=input.scanInt()-1;
            int v=input.scanInt()-1;
            adj_lst[u].add(v);
        }
        int extra[]=new int[n];
        int curr=k;
        for(int i=0;i<n;i++) {
            if(curr<a[i]) {
                System.out.println(-1);
                return;
            }
            curr+=b[i];
            if(i!=n-1) {
               extra[i]=curr-a[i+1]; 
            }
            else {
                extra[i]=curr;
            }
        }
        System.out.println(solve(extra));
    }
    public static int solve(int extra[]) {
        for(int i=n-2;i>=0;i--) {
            extra[i]=Math.min(extra[i],extra[i+1]);
        }
        int indx[]=new int[n];
        for(int i=0;i<n;i++) {
            indx[i]=i;
        }
        sort(n,c,indx);
        int total=0;
        for(int i=n-1;i>=0;i--) {
            int index=-1;
            for(int j=0;j<n;j++) {
                if(extra[j]==0) {
                    continue;
                }
                if(adj_lst[j].contains(indx[i])) {
                    index=j;
                }
            }
            if(index==-1) {
                continue;
            }
            total+=c[i];
            update(n,index,extra);
        }
        return total;
    }
    public static void sort(int n,int arr[],int brr[]) {
        for(int i=0;i<n;i++) {
            for(int j=0;j<n-1;j++) {
                if(arr[j]>arr[j+1]) {
                    swap(j,j+1,arr);
                    swap(j,j+1,brr);
                }
            }
        }
    }
    public static void swap(int i,int j,int arr[]) {
        int tmp=arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }
    public static void update(int n,int strt,int arr[]) {
        for(int i=strt;i<n;i++) {
            arr[i]=Math.max(0, arr[i]-1);
        }
        for(int i=n-2;i>=0;i--) {
            arr[i]=Math.min(arr[i],arr[i+1]);
        }
    }
}
