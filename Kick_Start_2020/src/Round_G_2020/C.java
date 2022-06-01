/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Round_G_2020;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class C {
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
    
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        int test=input.scanInt();
        for(int tt=1;tt<=test;tt++) {
            int w=input.scanInt();
            int n=input.scanInt();
            long arr[]=new long[w];
            for(int i=0;i<w;i++) {
                arr[i]=input.scanInt();
            }
            sort(arr,0,w-1);
            long prefix[]=new long[w];
            prefix[0]=arr[0];
            for(int i=1;i<w;i++) {
                prefix[i]=prefix[i-1]+arr[i];
            }
            ans.append("Case #"+tt+": "+solve(w,n,arr,prefix)+"\n");;
        }
        System.out.println(ans);
    }
    public static long solve(int w,long n,long arr[],long prefix[]) {
        long ans=Long.MAX_VALUE;
        for(int i=0;i<w;i++) {
            long tmp=0;
            int lft_indx=i==0?-1:search_lft(0,i-1,arr[i],arr,n);
            int rgt_indx=i==w-1?-1:search_rgt(i+1,w-1,arr[i],arr,n);
            if(lft_indx==-1) {
                lft_indx=i;
            }
            if(rgt_indx==-1) {
                rgt_indx=i;
            }
            tmp+=((i-lft_indx+1)*arr[i])-sum(prefix,lft_indx,i);
            tmp+=sum(prefix,i,rgt_indx)-((rgt_indx-i+1)*arr[i]);
            if(lft_indx-1>=0) {
                tmp+=sum(prefix,0,lft_indx-1)-(lft_indx*arr[i])+(lft_indx*n);
            }
            if(rgt_indx+1<w) {
                tmp+=(((w-1)-(rgt_indx+1)+1)*arr[i])-sum(prefix,rgt_indx+1,w-1)+(((w-1)-(rgt_indx+1)+1)*n);
            }
//            System.out.println(i+" "+lft_indx+" "+rgt_indx+" "+tmp);
            ans=Math.min(ans,tmp);
        }
        return ans;
    }
    public static int search_lft(int l,int r,long ele,long arr[],long n) {
        int ans=-1;
        while(l<=r) {
            int mid=(l+r)/2;
            long tmp1=ele-arr[mid];
            long tmp2=arr[mid]-ele+n;
            if(tmp1<=tmp2) {
                ans=mid;
                r=mid-1;
            }
            else {
                l=mid+1;
            }
        }
        return ans;
    }
    public static int search_rgt(int l,int r,long ele,long arr[],long n) {
        int ans=-1;
        while(l<=r) {
            int mid=(l+r)/2;
            long tmp1=arr[mid]-ele;
            long tmp2=ele-arr[mid]+n;
//            System.out.println("\t"+" "+l+" "+r+" "+mid+" "+tmp1+" "+tmp2);
            if(tmp1<=tmp2) {
                ans=mid;
                l=mid+1;
            }
            else {
                r=mid-1;
            }
        }
        return ans;
    }
    public static long sum(long prefix[],int l,int r) {
        return prefix[r]-(l==0?0:prefix[l-1]);
    }
}
