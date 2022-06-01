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
public class E_Segments_Removal {
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
        int seg_tree[],seg_indx[];
        public seg_tree (int n,int arr[]) {
            seg_tree=new int[4*n];
            seg_indx=new int[4*n];
            create_seg_tree(arr,0,0,n-1);
        }

        //0 index-Left child-(2*i+1) Right Child-(2*i+2)

        public void create_seg_tree(int arr[],int vertex,int l,int r) {
            if(l==r) {
                seg_tree[vertex]=arr[r];
                seg_indx[vertex]=r;
                return;
            }
            int mid=(l+r)/2;
            //Left Child
            create_seg_tree(arr,(2*vertex)+1,l,mid);
            //Right Child
            create_seg_tree(arr,(2*vertex)+2,mid+1,r);
            //Filling this node
            seg_tree[vertex]=Math.max(seg_tree[(2*vertex)+1],seg_tree[(2*vertex)+2]);
            if(seg_tree[(2*vertex)+1]>=seg_tree[(2*vertex)+2]) {
                seg_indx[vertex]=seg_indx[(2*vertex)+1];
            }
            else {
                seg_indx[vertex]=seg_indx[(2*vertex)+2];
            }
        }

        public int[] max(int vertex,int l,int r,int ql,int qr) {  //ql->query left , qr-> query right l->curr Segmrnt left    r->curr segment right
            if(ql>qr) {
                return new int[]{-1,-1};
            }

            if(ql==l && qr==r) {
                return new int[]{seg_tree[vertex],seg_indx[vertex]};
            }
            int mid=(l+r)/2;

            int max=Integer.MIN_VALUE;

            //Left Child
            int tmp_l[]=max((2*vertex)+1,l,mid,ql,Math.min(qr, mid));
            int tmp_r[]=max((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr);
            if(tmp_l[0]>=tmp_r[0]) {
                return tmp_l;
            }
            return tmp_r;
        }

        public void update(int vertex,int l,int r,int pos,int value) {   //pos->Position of the update   value->updates value
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
            seg_tree[vertex]=Math.max(seg_tree[(2*vertex)+1],seg_tree[(2*vertex)+2]);
            if(seg_tree[(2*vertex)+1]>=seg_tree[(2*vertex)+2]) {
                seg_indx[vertex]=seg_indx[(2*vertex)+1];
            }
            else {
                seg_indx[vertex]=seg_indx[(2*vertex)+2];
            }
        }
    }



    
    
    
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int n=input.scanInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
        }
        int indx=0;
        for(int i=0;i<n;i++) {
            int j=i;
            while(j<n && arr[j]==arr[i]) {
                j++;
            }
            indx++;
            i=j-1;
        }
        int freq[]=new int[indx];
        int ele[]=new int[indx];
        int lft[]=new int[indx];
        int rgt[]=new int[indx];
        indx=0;
        for(int i=0;i<n;i++) {
            int j=i,cnt=0;
            while(j<n && arr[j]==arr[i]) {
                j++;
                cnt++;
            }
            freq[indx]=cnt;
            ele[indx]=arr[i];
            lft[indx]=indx-1;
            rgt[indx]=indx+1;
            indx++;
            i=j-1;
        }
        seg_tree st=new seg_tree(freq.length,freq);
        int ans=0;
//        for(int i=0;i<freq.length;i++) {
//            System.out.print(freq[i]+" ");
//        }
//        System.out.println();
        while(true) {
            int tmp[]=st.max(0, 0, freq.length-1, 0, freq.length-1);
            if(tmp[0]==-1) {
                break;
            }
            ans++;
            indx=tmp[1];
//            for(int i=0;i<freq.length;i++) {
//                System.out.print(freq[i]+" ");
//            }
//            System.out.println();
//            System.out.println(indx);
            st.update(0, 0, freq.length-1, indx, -1);
            freq[indx]=-1;
            if(lft[indx]!=-1 && rgt[indx]!=freq.length && ele[lft[indx]]==ele[rgt[indx]]) {
                rgt[lft[indx]]=rgt[rgt[indx]];
                if(rgt[rgt[indx]]!=freq.length) {
                    lft[rgt[rgt[indx]]]=lft[indx];
                }
                freq[lft[indx]]+=freq[rgt[indx]];
                st.update(0, 0, freq.length-1, lft[indx], freq[lft[indx]]);
                freq[rgt[indx]]=-1;
                st.update(0, 0, freq.length-1, rgt[indx], -1);
            }
            else {
                if(rgt[indx]!=freq.length) {
                    lft[rgt[indx]]=lft[indx];
                }
                if(lft[indx]!=-1) {
                    rgt[lft[indx]]=rgt[indx];
                }
            }
        }
        System.out.println(ans);
    }
}
