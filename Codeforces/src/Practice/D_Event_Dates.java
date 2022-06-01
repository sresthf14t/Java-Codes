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
public class D_Event_Dates {
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
        static int seg_tree[],lazy[],seg_indx[];
        public seg_tree(int n) {
            seg_tree=new int[4*n];
            lazy=new int[4*n];
            seg_indx=new int[4*n];
            built(0,0,n-1);
        }

        public void built(int vertex, int l,int r) {
            if(l==r) {
                seg_tree[vertex]=0;
                seg_indx[vertex]=r;
                return;
            }
            int mid=(l+r)/2;

            //Left Child
            built((2*vertex)+1, l, mid);

            //Right Child
            built((2*vertex)+2, mid+1, r);

            if(seg_tree[(2*vertex)+1]<=seg_tree[(2*vertex)+2]) {
                seg_tree[vertex]=seg_tree[(2*vertex)+1];
                seg_indx[vertex]=seg_indx[(2*vertex)+1];
            }
            else {
                seg_tree[vertex]=seg_tree[(2*vertex)+2];
                seg_indx[vertex]=seg_indx[(2*vertex)+2];
            }
        }

        public static void push(int vertex) {
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

        public int[] min(int vertex,int l,int r,int ql,int qr) {
    //        System.out.println(vertex+" "+l+" "+r+" "+ql+" "+qr);
            if(ql>qr) {
                return new int[]{Integer.MAX_VALUE,-1};
            }
            if(ql<=l && r<=qr) {
                return new int[]{seg_tree[vertex],seg_indx[vertex]};
            }
            push(vertex);
            int mid=(l+r)/2;
            int tmp1[]=min((2*vertex)+1,l,mid,ql,Math.min(qr,mid));
            int tmp2[]=min((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr);
            if(tmp1[0]<=tmp2[0]) {
                return tmp1;
            }
            return tmp2;
        }
    }
    
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        int n=input.scanInt();
        int l[]=new int[n];
        int r[]=new int[n];
        int indx[]=new int[n];
        int shift[]=new int[n];
        int len=1000005;
        seg_tree st=new seg_tree(len);
        for(int i=0;i<n;i++) {
            l[i]=input.scanInt();
            r[i]=input.scanInt();
            if(r[i]-l[i]>1100) {
                r[i]=l[i]+1000;
                if(l[i]>1000) {
                    shift[i]=l[i]-1;
                    l[i]=1;
                    r[i]=1000;
                }
            }
            indx[i]=i;
            st.update(0, 0, len-1, l[i], r[i], 1);
        }
        sort(n,l,r,indx);
        int fin[]=new int[n];
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(fin[i]!=0) {
                    continue;
                }
                int tmp[]=st.min(0, 0, len-1, l[j], r[j]);
                if(tmp[0]!=1) {
                    continue;
                }
                fin[j]=tmp[1];
                st.update(0, 0, len-1, tmp[1], tmp[1], 1001);
                st.update(0, 0, len-1, l[j], r[j], -1);
            }
        }
        for(int i=0;i<n;i++) {
            if(fin[i]!=0) {
                continue;
            }
            int tmp[]=st.min(0, 0, len-1, l[i], r[i]);
            fin[i]=tmp[1];
            st.update(0, 0, len-1, tmp[1], tmp[1], 1001);
            st.update(0, 0, len-1, l[i], r[i], -1);
        }
        sort1(0,n-1,indx,fin,r);
        for(int i=0;i<n;i++) {
            fin[i]+=shift[i];
            ans.append(fin[i]+" ");
        }
        System.out.println(ans);
    }
    public static void sort(int n,int l[],int r[],int indx[]) {
        for(int i=0;i<n;i++) {
            for(int j=0;j<n-1;j++) {
                if(r[j]-l[j]>r[j+1]-l[j+1]) {
                    swap(l,j,j+1);
                    swap(r,j,j+1);
                    swap(indx,j,j+1);
                }
            }
        }
    }
    public static void sort1(int strt,int end,int l[],int r[],int indx[]) {
        for(int i=strt;i<end;i++) {
            for(int j=strt;j<end;j++) {
                if(l[j]>l[j+1]) {
                    swap(l,j,j+1);
                    swap(r,j,j+1);
                    swap(indx,j,j+1);
                }
            }
        }
    }
    public static void swap(int arr[], int i,int j) {
        int tmp=arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }
}
