/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_655_Div_2;

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
    
    
    static class seg_tree {
        static long seg_tree[];
        static int indx[];
        public seg_tree(int n,long arr[]) {
            seg_tree=new long[4*n];
            indx=new int[4*n];
            create_seg_tree(arr,0,0,n-1);
        }

        //0 index-Left child-(2*i+1) Right Child-(2*i+2)

        public static void create_seg_tree(long arr[],int vertex,int l,int r) {
            if(l==r) {
                seg_tree[vertex]=arr[r];
                indx[vertex]=r;
                return;
            }
            int mid=(l+r)/2;
            //Left Child
            create_seg_tree(arr,(2*vertex)+1,l,mid);
            //Right Child
            create_seg_tree(arr,(2*vertex)+2,mid+1,r);
            //Filling this node
            if(seg_tree[(2*vertex)+1]<seg_tree[(2*vertex)+2]) {
                seg_tree[vertex]=seg_tree[(2*vertex)+1];
                indx[vertex]=indx[(2*vertex)+1];
            }
            else {
                seg_tree[vertex]=seg_tree[(2*vertex)+2];
                indx[vertex]=indx[(2*vertex)+2];
            }
        }

        public static long[] min(int vertex,int l,int r,int ql,int qr) {  //ql->query left , qr-> query right l->curr Segmrnt left    r->curr segment right
            if(ql>qr) {
                return new long[]{Long.MAX_VALUE,-1};
            }

            if(ql==l && qr==r) {
                return new long[]{seg_tree[vertex],indx[vertex]};
            }
            int mid=(l+r)/2;
            
            long lft[]=min((2*vertex)+1,l,mid,ql,Math.min(qr, mid));
            long rgt[]=min((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr);
            if(lft[0]<rgt[0]) {
                return lft;
            }
            else {
                return rgt;
            }
        }

        public static void update(int vertex,int l,int r,int pos,long value) {   //pos->Position of the update   value->updates value
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
            
            if(seg_tree[(2*vertex)+1]<seg_tree[(2*vertex)+2]) {
                seg_tree[vertex]=seg_tree[(2*vertex)+1];
                indx[vertex]=indx[(2*vertex)+1];
            }
            else {
                seg_tree[vertex]=seg_tree[(2*vertex)+2];
                indx[vertex]=indx[(2*vertex)+2];
            }
        }
    }
    
    
    static int n,lft[],rgt[];
    static long arr[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n=input.scanInt();
        arr=new long[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
        }
        
        lft=new int[n];
        rgt=new int[n];
        for(int i=0;i<n;i++) {
            if(i==0) {
                lft[i]=n-1;
            }
            else {
                lft[i]=i-1;
            }
        }
        for(int i=0;i<n;i++) {
            if(i==n-1) {
                rgt[i]=0;
            }
            else {
                rgt[i]=i+1;
            }
        }
        System.out.println(solve());
    }
    public static long solve() {
        if(n==1) {
            return arr[0];
        }
        seg_tree tree=new seg_tree(n,arr);
        long prev=-1;
        while(true) {
            long min[]=seg_tree.min(0, 0, n-1, 0, n-1);
            if(min[0]==Long.MAX_VALUE) {
                break;
            }
            int indx=(int)min[1];
            if(arr[lft[indx]]==Long.MAX_VALUE || arr[rgt[indx]]==Long.MAX_VALUE) {
                break;
            }
            if(lft[indx]==indx || rgt[indx]==indx) {
                break;
            }
            arr[indx]=arr[lft[indx]]+arr[rgt[indx]];
            arr[lft[indx]]=Long.MAX_VALUE;
            arr[rgt[indx]]=Long.MAX_VALUE;
            prev=arr[indx];
            tree.update(0,0,n-1,indx,arr[indx]);
            tree.update(0,0,n-1,lft[indx],Long.MAX_VALUE);
            tree.update(0,0,n-1,rgt[indx],Long.MAX_VALUE);
            lft[rgt[rgt[indx]]]=indx;
            rgt[lft[lft[indx]]]=indx;
            lft[indx]=lft[lft[indx]];
            rgt[indx]=rgt[rgt[indx]];
        }
//        for(int i=0;i<n;i++) {
//            System.out.print(arr[i]+" ");
//        }
//        System.out.println();
//        for(int i=0;i<n;i++) {
//            System.out.print(lft[i]+" ");
//        }
//        System.out.println();
//        for(int i=0;i<n;i++) {
//            System.out.print(rgt[i]+" ");
//        }
//        System.out.println();
        return prev;
    }
}
