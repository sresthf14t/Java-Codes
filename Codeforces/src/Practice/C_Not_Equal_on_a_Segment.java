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
public class C_Not_Equal_on_a_Segment {
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
    
    public static void sort(int arr[],int brr[],int crr[],int drr[],int l,int r) {    //sort(arr,0,n-1);
        if(l==r) {
            return;
        }
        int mid=(l+r)/2;
        sort(arr,brr,crr,drr,l,mid);
        sort(arr,brr,crr,drr,mid+1,r);
        merge(arr,brr,crr,drr,l,mid,mid+1,r);
    }
    public static void merge(int arr[],int brr[],int crr[],int drr[],int l1,int r1,int l2,int r2) {
        int tmpa[]=new int[r2-l1+1];
        int tmpb[]=new int[r2-l1+1];
        int tmpc[]=new int[r2-l1+1];
        int tmpd[]=new int[r2-l1+1];
        int indx1=l1,indx2=l2;
        //sorting the two halves using a tmp array
        for(int i=0;i<tmpa.length;i++) {
            if(indx1>r1) {
                tmpa[i]=arr[indx2];
                tmpb[i]=brr[indx2];
                tmpc[i]=crr[indx2];
                tmpd[i]=drr[indx2];
                indx2++;
                continue;
            }
            if(indx2>r2) {
                tmpa[i]=arr[indx1];
                tmpb[i]=brr[indx1];
                tmpc[i]=crr[indx1];
                tmpd[i]=drr[indx1];
                indx1++;
                continue;
            }
            if(arr[indx1]<arr[indx2]) {
                tmpa[i]=arr[indx1];
                tmpb[i]=brr[indx1];
                tmpc[i]=crr[indx1];
                tmpd[i]=drr[indx1];
                indx1++;
                continue;
            }
            tmpa[i]=arr[indx2];
            tmpb[i]=brr[indx2];
            tmpc[i]=crr[indx2];
            tmpd[i]=drr[indx2];
            indx2++;
        }
        //Copying the elements of tmp into the main array
        for(int i=0,j=l1;i<tmpa.length;i++,j++) {
            arr[j]=tmpa[i];
            brr[j]=tmpb[i];
            crr[j]=tmpc[i];
            drr[j]=tmpd[i];
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
    
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        
        int n=input.scanInt();
        int q=input.scanInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
        }
        int lft[]=new int[q];
        int rgt[]=new int[q];
        int val[]=new int[q];
        int indx[]=new int[q];
        for(int i=0;i<q;i++) {
            lft[i]=input.scanInt()-1;
            rgt[i]=input.scanInt()-1;
            val[i]=input.scanInt();
            indx[i]=i;
        }
        
        int fin[]=Mos(n,arr,q,lft,rgt,indx,val);
        
        for(int i=0;i<q;i++) {
            if(fin[i]>-1) {
                fin[i]++;
            }
            ans.append(fin[i]+"\n");
        }
        
        System.out.println(ans);
    }
    
    public static int[] Mos(int n,int arr[],int q,int lft[],int rgt[],int indx[],int val[]) {
        sort(lft,rgt,indx,val,0,q-1);
        int len=(int)Math.ceil(Math.sqrt(n));
        len=Math.max(len,1);
        int strt=0,end=len-1;
        for(int i=0;i<q;i++) {
            int j=i;
            while(j<q && lft[j]<=end) {
                j++;
            }
            j--;
            if(j>=i) {
                sort(rgt,lft,indx,val,i,j);
            }
            i=j;
            strt+=len;
            end+=len;
        }
        int ans[]=new int[q];
        Arrays.fill(ans, -1);
        int lptr=lft[0],rptr=rgt[0];
        TreeMap<Integer,TreeSet<Integer>> tm=new TreeMap<>();
        for(int i=lft[0];i<=rgt[0];i++) {
            if(!tm.containsKey(arr[i])) {
                TreeSet<Integer> ts=new TreeSet<>();
                tm.put(arr[i], ts);
            }
            TreeSet<Integer> ts=tm.get(arr[i]);
            ts.add(i);
        }
        if(tm.lowerKey(val[0])!=null) {
            ans[0]=tm.get(tm.lowerKey(val[0])).first();
        }
        else if(tm.higherKey(val[0])!=null) {
            ans[0]=tm.get(tm.higherKey(val[0])).first();
        }
        for(int i=1;i<q;i++) {
            update(arr,lptr,rptr,lft[i],rgt[i],tm);
            lptr=lft[i];
            rptr=rgt[i];
            if(tm.lowerKey(val[i])!=null) {
                ans[i]=tm.get(tm.lowerKey(val[i])).first();
            }
            else if(tm.higherKey(val[i])!=null) {
                ans[i]=tm.get(tm.higherKey(val[i])).first();
            }
        }
        sort(indx,ans,new int[q],new int[q],0,q-1);
        return ans;
    }
    
    public static void update(int arr[],int lptr,int rptr,int l,int r,TreeMap<Integer,TreeSet<Integer>> tm) {
//        System.out.println(l+" "+r);
        
        if(l<lptr) {
            for(int i=l;i<lptr;i++) {
                if(!tm.containsKey(arr[i])) {
                    TreeSet<Integer> ts=new TreeSet<>();
                    tm.put(arr[i], ts);
                }
                TreeSet<Integer> ts=tm.get(arr[i]);
                ts.add(i);
            }
        }
        
        
        if(r>rptr) {
            for(int i=rptr+1;i<=r;i++) {
                if(!tm.containsKey(arr[i])) {
                    TreeSet<Integer> ts=new TreeSet<>();
                    tm.put(arr[i], ts);
                }
                TreeSet<Integer> ts=tm.get(arr[i]);
                ts.add(i);
            }
        }
        
        if(l>lptr) {
            for(int i=lptr;i<l;i++) {
                tm.get(arr[i]).remove(i);
                if(tm.get(arr[i]).isEmpty()) {
                    tm.remove(arr[i]);
                }
            }
        }
        
        
        if(r<rptr) {
            for(int i=rptr;i>r;i--) {
                tm.get(arr[i]).remove(i);
                if(tm.get(arr[i]).isEmpty()) {
                    tm.remove(arr[i]);
                }
            }
        }
    }
}
