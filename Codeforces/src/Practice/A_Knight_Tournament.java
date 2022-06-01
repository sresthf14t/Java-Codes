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
public class A_Knight_Tournament {
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
    static boolean marked[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int n=input.scanInt();
        int m=input.scanInt();
        seg_tree=new int[4*n];
        marked=new boolean[4*n];
        int l[]=new int[m];
        int r[]=new int[m];
        int value[]=new int[m];
        built(0, 0, n-1);
        for(int i=0;i<m;i++) {
            l[i]=input.scanInt()-1;
            r[i]=input.scanInt()-1;
            value[i]=input.scanInt()-1;
        }
        for(int i=m-1;i>=0;i--) {
            update(0, 0, n-1, l[i], value[i]-1, value[i]);
            update(0, 0, n-1, value[i]+1, r[i], value[i]);
        }
        StringBuilder ans=new StringBuilder("");
        for(int i=0;i<n;i++) {
            int indx=get(0, 0, n-1, i);
            if(indx==i) {
                ans.append("0 ");
            }
            else {
                ans.append((indx+1)+" ");
            }
        }
        System.out.println(ans);
    }
    
    public static void built(int vertex, int l,int r) {
        if(l==r) {
            seg_tree[vertex]=r;
            return;
        }
        int mid=(l+r)/2;
        
        //Left Child
        built((2*vertex)+1, l, mid);
        
        //Right Child
        built((2*vertex)+2, mid+1, r);
        
        seg_tree[vertex]=0;
    }
    
    public static void push(int vertex) {
        if(marked[vertex]) {
            seg_tree[(2*vertex)+1]=seg_tree[(2*vertex)+2]=seg_tree[vertex];
            marked[(2*vertex)+1]=marked[(2*vertex)+2]=true;
            marked[vertex]=false;
        }
        
    }
    
    public static void update(int vertex,int l,int r,int ql,int qr,int value) {
        if(ql>qr) {
            return;
        }
        if(l==ql && r==qr) {
            seg_tree[vertex]=value;
            marked[vertex]=true;
            return;
        }
        push(vertex);
        int mid=(l+r)/2;
        //Left Child
        update((2*vertex)+1,l,mid,ql,Math.min(mid,qr),value);
        //Right Child
        update((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr,value);
    }
    
    public static int get(int vertex, int l,int r ,int pos) {
        if(l==r) {
            return seg_tree[vertex];
        }
        push(vertex);
        int mid=(l+r)/2;
        if(pos<=mid) {
            return get((2*vertex)+1,l,mid,pos);
        }
        else {
            return get((2*vertex)+2,mid+1,r,pos);
        }
    }
}
