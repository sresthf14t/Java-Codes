/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_670_Div_2;

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
    
    
    static class st_max {
        long seg_tree[],lazy[];
        public st_max(int n,long arr[]) {
            seg_tree=new long[4*n];
            lazy=new long[4*n];
            built(arr,0,0,n-1);
        }

        public void built(long arr[], int vertex, int l,int r) {
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

        public void push(int vertex) {
            seg_tree[(2*vertex)+1]+=lazy[vertex];
            lazy[(2*vertex)+1]+=lazy[vertex];

            seg_tree[(2*vertex)+2]+=lazy[vertex];
            lazy[(2*vertex)+2]+=lazy[vertex];

            lazy[vertex]=0;
        }

        public void update(int vertex,int l,int r,int ql,int qr,long add) {

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

        public long find_max(int vertex,int l,int r,int ql,int qr) {
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

    
    static class st_min {
        long seg_tree[],lazy[];
        public st_min(int n,long arr[]) {
            seg_tree=new long[4*n];
            lazy=new long[4*n];
            built(arr,0,0,n-1);
        }

        public void built(long arr[], int vertex, int l,int r) {
            if(l==r) {
                seg_tree[vertex]=arr[r];
                return;
            }
            int mid=(l+r)/2;

            //Left Child
            built(arr,(2*vertex)+1, l, mid);

            //Right Child
            built(arr,(2*vertex)+2, mid+1, r);

            seg_tree[vertex]=Math.min(seg_tree[(2*vertex)+1],seg_tree[(2*vertex)+2]);
        }

        public void push(int vertex) {
            seg_tree[(2*vertex)+1]+=lazy[vertex];
            lazy[(2*vertex)+1]+=lazy[vertex];

            seg_tree[(2*vertex)+2]+=lazy[vertex];
            lazy[(2*vertex)+2]+=lazy[vertex];

            lazy[vertex]=0;
        }

        public void update(int vertex,int l,int r,int ql,int qr,long add) {

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

            seg_tree[vertex]=Math.min(seg_tree[(2*vertex)+1],seg_tree[(2*vertex)+2]);
        }

        public long find_min(int vertex,int l,int r,int ql,int qr) {
    //        System.out.println(vertex+" "+l+" "+r+" "+ql+" "+qr);
            if(ql>qr) {
                return Integer.MAX_VALUE;
            }
            if(ql<=l && r<=qr) {
                return seg_tree[vertex];
            }
            push(vertex);
            int mid=(l+r)/2;
            return Math.min(find_min((2*vertex)+1,l,mid,ql,Math.min(qr,mid)),find_min((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr));
        }

    }
    
    
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int n=input.scanInt();
        StringBuilder ans=new StringBuilder("");
        long arr[]=new long[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
        }
        if(n==1) {
            if(arr[0]<0) {
                ans.append(arr[0]/2+"\n");
            }
            else {
                ans.append((arr[0]/2+(arr[0]%2==0?0:1))+"\n");
            }
            int q=input.scanInt();
            for(int qq=1;qq<=q;qq++) {
                int l=input.scanInt()-1;
                int r=input.scanInt()-1;
                long tmp=input.scanInt();
                arr[0]+=tmp;
                if(arr[0]<0) {
                    ans.append(arr[0]/2+"\n");
                }
                else {
                    ans.append((arr[0]/2+(arr[0]%2==0?0:1))+"\n");
                }
            }
            System.out.println(ans);
            return;
        }
        long dec[]=new long[n];
        long inc[]=new long[n];
        long min=Long.MAX_VALUE;
        for(int i=0;i<n;i++) {
            min=Math.min(min,arr[i]);
        }
        long max=Long.MIN_VALUE;
        min=Long.MAX_VALUE;
        for(int i=0;i<n;i++) {
            if(dec[i]>min) {
                dec[i]=min;
            }
            inc[i]=arr[i]-dec[i];
            if(inc[i]>=max) {
                max=inc[i];
                min=Math.min(min,dec[i]);
                continue;
            }
            inc[i]=max;
            dec[i]=arr[i]-inc[i];
            min=Math.min(min,dec[i]);
        }
        long tmp2=(Math.abs(inc[n-1]+dec[0])/2);
        if(Math.abs(inc[n-1]+dec[0])%2!=0) {
            tmp2++;
        }
        for(int i=0;i<n;i++) {
            System.out.print(inc[i]+" ");
        }
        System.out.println();
        for(int i=0;i<n;i++) {
            System.out.print(dec[i]+" ");
        }
        System.out.println();
        ans.append(tmp2+"\n");
        st_max smax=new st_max(n,inc);
        st_max smin=new st_max(n,dec);
        int q=input.scanInt();
        for(int qq=1;qq<=q;qq++) {
            int l=input.scanInt()-1;
            int r=input.scanInt()-1;
            long add=input.scanInt();
            smax.update(0, 0, n-1, l, r, add);
            max=smax.find_max(0, 0, n-1, l, r);
            if(max>inc[n-1]) {
                smax.update(0, 0, n-1, n-1, n-1, max-inc[n-1]);
            }
            if(smax.find_max(0, 0, n-1, l, l)<smax.find_max(0, 0, n-1, 0, 0)) {
                dec[0]=smax.find_max(0, 0, n-1, 0, 0)+dec[0]-smax.find_max(0, 0, n-1, l, l);
                smax.update(0, 0, n-1, 0, 0, smax.find_max(0, 0, n-1, l, l)-smax.find_max(0, 0, n-1, 0, 0));
            }
            System.out.println("---------------------------------------------------------------");
            for(int i=0;i<n;i++) {
                System.out.print(smax.find_max(0, 0, n-1, i, i)+" ");
            }
            System.out.println();
            for(int i=0;i<n;i++) {
                System.out.print(dec[i]+" ");
            }
            System.out.println();
            tmp2=(Math.abs(smax.find_max(0, 0, n-1, n-1, n-1)+dec[0])/2);
            if(Math.abs(smax.find_max(0, 0, n-1, n-1, n-1)+dec[0])%2!=0) {
                tmp2++;
            }
            ans.append(tmp2+"\n");
        }
        System.out.println(ans);
    }
}
