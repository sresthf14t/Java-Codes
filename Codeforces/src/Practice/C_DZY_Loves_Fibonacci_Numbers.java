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
public class C_DZY_Loves_Fibonacci_Numbers {
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
    static long seg_tree[],lazy[],fib[],mod;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int n=input.scanInt();
        int m=input.scanInt();
        long arr[]=new long[n];
        seg_tree=new long[4*n];
        lazy=new long[4*n];
        fib=new long[n+1];
        mod=1000000009;
        fib[0]=fib[1]=1;
        for(int i=2;i<fib.length;i++) {
            fib[i]=fib[i-1]+fib[i-2];
            fib[i]%=mod;
        }
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
        }
        built(arr,0,0,n-1);
        for(int i=0;i<m;i++) {
            int type=input.scanInt();
            int l=input.scanInt()-1;
            int r=input.scanInt()-1;
            if(type==1) {
                update(0,0,n-1,l,r,)
            }
        }
    }
    
    public static void built(long arr[], int vertex, int l,int r) {
        if(l==r) {
            seg_tree[vertex]=arr[r];
            return;
        }
        int mid=(l+r)/2;
        
        //Left Child
        built(arr,(2*vertex)+1, l, mid);
        
        //Right Child
        built(arr,(2*vertex)+2, mid+1, r);
        
        seg_tree[vertex]=Math.max(seg_tree[(2*vertex)+1],seg_tree[(2*vertex)+2]);
    }
    
    public static void push(int vertex) {
        seg_tree[(2*vertex)+1]+=lazy[vertex];
        lazy[(2*vertex)+1]+=lazy[vertex];
        
        seg_tree[(2*vertex)+2]+=lazy[vertex];
        lazy[(2*vertex)+2]+=lazy[vertex];
        
        lazy[vertex]=0;
    }
    
    public static void update(int vertex,int l,int r,int ql,int qr,int add) {
        
        if(ql>qr) {
            return;
        }
        if(l==ql && r==qr) {
            seg_tree[vertex]+=add;
            lazy[vertex]+=add;
            return;
        }
        push(vertex);
        
        int mid=(l+r)/2;
        //Left Child
        update((2*vertex)+1,l,mid,ql,Math.min(mid,qr),add);
        //Right Child
        update((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr,add);
        
        seg_tree[vertex]=Math.max(seg_tree[(2*vertex)+1],seg_tree[(2*vertex)+2]);
    }
    
    public static long find_max(int vertex,int l,int r,int ql,int qr) {
//        System.out.println(vertex+" "+l+" "+r+" "+ql+" "+qr);
        if(ql>qr) {
            return Integer.MIN_VALUE;
        }
        if(ql<=l && r<=qr) {
            return seg_tree[vertex];
        }
        push(vertex);
        int mid=(l+r)/2;
        return Math.max(find_max((2*vertex)+1,l,mid,ql,Math.min(qr,mid)),find_max((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr));
    }
}
