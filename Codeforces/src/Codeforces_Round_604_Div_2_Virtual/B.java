/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_604_Div_2_Virtual;

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
    static int seg_tree[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int test=input.scanInt();
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            int arr[]=new int[n];
            for(int i=0;i<n;i++) {
                arr[input.scanInt()-1]=i;
            }
            System.out.println(solve(n,arr));
        }
    }
    public static String solve(int n,int arr[]) {
        int max=Integer.MIN_VALUE,min=Integer.MAX_VALUE;
        StringBuilder ans=new StringBuilder("");
        seg_tree=new int[4*n];
        for(int i=0;i<n;i++) {
            update(0,0,n-1,arr[i],1);
            max=Math.max(max,arr[i]);
            min=Math.min(min,arr[i]);
            int sum=sum(0,0,n-1,min,max);
            if(sum==max-min+1) {
                ans.append(1);
            }
            else {
                ans.append(0);
            }
        }
        return ""+ans;
    }
    
    public static int sum(int vertex,int l,int r,int ql,int qr) {  //ql->query left , qr-> query right l->curr Segmrnt left    r->curr segment right
        if(ql>qr) {
            return 0;
        }
        
        if(ql==l && qr==r) {
            return seg_tree[vertex];
        }
        int mid=(l+r)/2;
        
        int total=0;
        
        //Left Child
        total+=sum((2*vertex)+1,l,mid,ql,Math.min(qr, mid));
        
        //Right Child
        total+=sum((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr);
        
        return total;
    }
    
    public static void update(int vertex,int l,int r,int pos,int value) {   //pos->Position of the update   value->updates value
        if(l==r) {
            seg_tree[vertex]=value;
            return;
        }
        int mid=(l+r)/2;
        //Left Child
        if(pos<=mid) {
            update((2*vertex)+1,l,mid,pos,value);
        }
        //Right Child
        else {
            update((2*vertex)+2,mid+1,r,pos,value);
        }
        seg_tree[vertex]=seg_tree[(2*vertex)+1]+seg_tree[(2*vertex)+2];
    }
}
