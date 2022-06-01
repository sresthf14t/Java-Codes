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
public class C_Present {
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
    
    public static void sort(int arr[],int l,int r) {    //sort(arr,0,n-1);
        if(l==r) {
            return;
        }
        int mid=(l+r)/2;
        sort(arr,l,mid);
        sort(arr,mid+1,r);
        merge(arr,l,mid,mid+1,r);
    }
    public static void merge(int arr[],int l1,int r1,int l2,int r2) {
        int tmp[]=new int[r2-l1+1];
        int indx1=l1,indx2=l2;
        //sorting the two halves using a tmp array
        for(int i=0;i<tmp.length;i++) {
            if(indx1>r1) {
                tmp[i]=arr[indx2];
                indx2++;
                continue;
            }
            if(indx2>r2) {
                tmp[i]=arr[indx1];
                indx1++;
                continue;
            }
            if(arr[indx1]<arr[indx2]) {
                tmp[i]=arr[indx1];
                indx1++;
                continue;
            }
            tmp[i]=arr[indx2];
            indx2++;
        }
        //Copying the elements of tmp into the main array
        for(int i=0,j=l1;i<tmp.length;i++,j++) {
            arr[j]=tmp[i];
        }
    }
    
    public static void sort(long arr[],int l,int r) {    //sort(arr,0,n-1);
        if(l==r) {
            return;
        }
        int mid=(l+r)/2;
        sort(arr,l,mid);
        sort(arr,mid+1,r);
        merge(arr,l,mid,mid+1,r);
    }
    public static void merge(long arr[],int l1,int r1,int l2,int r2) {
        long tmp[]=new long[r2-l1+1];
        int indx1=l1,indx2=l2;
        //sorting the two halves using a tmp array
        for(int i=0;i<tmp.length;i++) {
            if(indx1>r1) {
                tmp[i]=arr[indx2];
                indx2++;
                continue;
            }
            if(indx2>r2) {
                tmp[i]=arr[indx1];
                indx1++;
                continue;
            }
            if(arr[indx1]<arr[indx2]) {
                tmp[i]=arr[indx1];
                indx1++;
                continue;
            }
            tmp[i]=arr[indx2];
            indx2++;
        }
        //Copying the elements of tmp into the main array
        for(int i=0,j=l1;i<tmp.length;i++,j++) {
            arr[j]=tmp[i];
        }
    }
    
    static class seg_tree {
        int seg_tree[],seg_indx[],lazy[];
        seg_tree(int n,int arr[]) {
            seg_tree=new int[4*n];
            seg_indx=new int[4*n];
            lazy=new int[4*n];
            built(arr,0,0,n-1);
        }

        public void built(int arr[], int vertex, int l,int r) {
            if(l==r) {
                seg_tree[vertex]=arr[r];
                seg_indx[vertex]=r;
                return;
            }
            int mid=(l+r)/2;

            //Left Child
            built(arr,(2*vertex)+1, l, mid);

            //Right Child
            built(arr,(2*vertex)+2, mid+1, r);
            
            if(seg_tree[(2*vertex)+1]<=seg_tree[(2*vertex)+2]) {
                seg_tree[vertex]=seg_tree[(2*vertex)+1];
                seg_indx[vertex]=seg_indx[(2*vertex)+1];
            }
            else {
                seg_tree[vertex]=seg_tree[(2*vertex)+2];
                seg_indx[vertex]=seg_indx[(2*vertex)+2];
            }
        }

        public void push(int vertex) {
            seg_tree[(2*vertex)+1]+=lazy[vertex];
            lazy[(2*vertex)+1]+=lazy[vertex];

            seg_tree[(2*vertex)+2]+=lazy[vertex];
            lazy[(2*vertex)+2]+=lazy[vertex];

            lazy[vertex]=0;
        }

        public void update(int vertex,int l,int r,int ql,int qr,int add) {

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
            
            if(seg_tree[(2*vertex)+1]<=seg_tree[(2*vertex)+2]) {
                seg_tree[vertex]=seg_tree[(2*vertex)+1];
                seg_indx[vertex]=seg_indx[(2*vertex)+1];
            }
            else {
                seg_tree[vertex]=seg_tree[(2*vertex)+2];
                seg_indx[vertex]=seg_indx[(2*vertex)+2];
            }
        }

        public int[] find_min(int vertex,int l,int r,int ql,int qr) {
            if(ql>qr) {
                return new int[]{Integer.MAX_VALUE,-1};
            }
            if(ql<=l && r<=qr) {
                return new int[]{seg_tree[vertex],seg_indx[vertex]};
            }
            push(vertex);
            int mid=(l+r)/2;
            
            int lft[]=find_min((2*vertex)+1,l,mid,ql,Math.min(qr,mid));
            int rgt[]=find_min((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr);
            
            if(lft[0]<=rgt[0]) {
                return lft;
            }
            else {
                return rgt;
            }
        }

    }

    
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int n=input.scanInt();
        int m=input.scanInt();
        int w=input.scanInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
        }
        
        seg_tree st=new seg_tree(n,arr);
        
        for(int i=0;i<m;i++) {
            int min[]=st.find_min(0, 0, n-1, 0, n-1);
            int indx=min[1];
            if(indx+w-1>n-1) {
                indx=n-1-w+1;
            }
            st.update(0, 0, n-1, indx, indx+w-1, 1);
        }
        
        System.out.println(st.find_min(0, 0, n-1, 0, n-1)[0]);
    }
}
