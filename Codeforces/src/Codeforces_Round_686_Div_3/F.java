/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_686_Div_3;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class F {
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
        int seg_tree[];
        public seg_tree(int n,int arr[]) {
            seg_tree=new int[4*n];
            create_seg_tree(arr,0,0,n-1);
        }

        //0 index-Left child-(2*i+1) Right Child-(2*i+2)

        public void create_seg_tree(int arr[],int vertex,int l,int r) {
            if(l==r) {
                seg_tree[vertex]=arr[r];
                return;
            }
            int mid=(l+r)/2;
            //Left Child
            create_seg_tree(arr,(2*vertex)+1,l,mid);
            //Right Child
            create_seg_tree(arr,(2*vertex)+2,mid+1,r);
            //Filling this node
            seg_tree[vertex]=Math.min(seg_tree[(2*vertex)+1],seg_tree[(2*vertex)+2]);
        }

        public int min(int vertex,int l,int r,int ql,int qr) {  //ql->query left , qr-> query right l->curr Segmrnt left    r->curr segment right
            if(ql>qr) {
                return Integer.MAX_VALUE;
            }

            if(ql==l && qr==r) {
                return seg_tree[vertex];
            }
            int mid=(l+r)/2;

            int min=Integer.MAX_VALUE;

            //Left Child
            min=Math.min(min,min((2*vertex)+1,l,mid,ql,Math.min(qr, mid)));

            //Right Child
            min=Math.min(min,min((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr));

            return min;
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
            seg_tree[vertex]=Math.min(seg_tree[(2*vertex)+1],seg_tree[(2*vertex)+2]);
        }
    }

    
    
    
    
    
    
    
    
    
    static seg_tree st;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        int test=input.scanInt();
        for(int tt=1;tt<=test;tt++) {
            int n=input.scanInt();
            int arr[]=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt();
            }
            ans.append(solve1(n,arr));
        }
        System.out.println(ans);
    }
    
    
    public static StringBuilder solve1(int n,int arr[]) {
        StringBuilder ans=new StringBuilder("");
        st=new seg_tree(n,arr);
        int lft=0,rgt=n-1,maxl=arr[0],maxr=arr[n-1];
        while(lft<rgt && rgt-lft>1) {
            while(lft<rgt && rgt-lft>1 && maxl<maxr) {
                lft++;
                maxl=Math.max(maxl,arr[lft]);
            }
            while(lft<rgt && rgt-lft>1 && maxr<maxl) {
                rgt--;
                maxr=Math.max(maxr,arr[rgt]);
            }
            if(maxl==maxr && lft<rgt && rgt-lft>1) {
                int min=st.min(0, 0, n-1, lft+1, rgt-1);
                if(min==maxl) {
                    ans.append("YES\n");
                    ans.append((lft-0+1)+" "+((rgt-1)-(lft+1)+1)+" "+(n-1-(rgt)+1)+"\n");
                    return ans;
                }
                if(arr[lft+1]<maxl) {
                    lft++;
                    maxl=Math.max(maxl,arr[lft]);
                }
                else {
                    rgt--;
                    maxr=Math.max(maxr,arr[rgt]);
                }
            }
        }
        ans.append("NO\n");
        return ans;
    }
    
    public static StringBuilder solve(int n,int arr[]) {
        StringBuilder ans=new StringBuilder("");
        int mp[]=new int[n];
        int ms[]=new int[n];
        int max=Integer.MIN_VALUE;
        for(int i=0;i<n;i++) {
            max=Math.max(max,arr[i]);
            mp[i]=max;
        }
        max=Integer.MIN_VALUE;
        for(int i=n-1;i>=0;i--) {
            max=Math.max(max,arr[i]);
            ms[i]=max;
        }
//        for(int i=0;i<n;i++) {
//            System.out.print(mp[i]+" ");
//        }
//        System.out.println();
//        for(int i=0;i<n;i++) {
//            System.out.print(ms[i]+" ");
//        }
//        System.out.println();
        st=new seg_tree(n,arr);
        
        for(int i=0;i<n-2;i++) {
//            if(i!=5) {
//                continue;
//            }
            int indx=search(i+1,mp[i],ms);
            if(indx==-1) {
                continue;
            }
            ans.append("YES\n");
            ans.append((i+1)+" "+(indx-(i+1)+1)+" "+(n-1-(indx+1)+1)+"\n");
            return ans;
        }
        ans.append("NO\n");
        return ans;
    }
    public static int search(int l,int val,int ms[]) {
        int strt=l;
        int r=ms.length-2;
        while(l<=r) {
            int mid=(l+r)/2;
            int min=st.min(0, 0, ms.length-1, strt, mid);
            int max=ms[mid+1];
//            System.out.println(l+" "+r+" "+mid+" "+min+" "+max+" "+val);
            if(min<val) {
                r=mid-1;
            }
            if(min>val) {
                l=mid+1;
            }
            if(min==val && max<val) {
                r=mid-1;
            }
            if(min==val && max>val) {
                l=mid+1;
            }
            if(min==val && max==val) {
                return mid;
            }
        }
        return -1;
    }
}

