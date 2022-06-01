/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

/**
 *
 * @author SRESTH
 */
import java.util.*;
import java.io.*;
public class D_Devu_and_his_Brother {
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
        
        int n=input.scanInt();
        int m=input.scanInt();
        long arr[]=new long[n];
        long brr[]=new long[m];
        long prefix_a[]=new long[n];
        long prefix_b[]=new long[m];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
        }
        for(int i=0;i<m;i++) {
            brr[i]=input.scanInt();
        }
        sort(arr,0,n-1);
        sort(brr,0,m-1);
        prefix_a[0]=arr[0];
        for(int i=1;i<n;i++) {
            prefix_a[i]=prefix_a[i-1]+arr[i];
        }
        prefix_b[0]=brr[0];
        for(int i=1;i<m;i++) {
            prefix_b[i]=prefix_b[i-1]+brr[i];
        }
        long min=Long.MAX_VALUE;
        for(int i=0;i<n;i++) {
            min=Math.min(min,cnt(n,m,arr,brr,prefix_a,prefix_b,arr[i]));
        }
        for(int i=0;i<m;i++) {
            min=Math.min(min,cnt(n,m,arr,brr,prefix_a,prefix_b,brr[i]));
        }
//        if(solve(n,m,arr,brr,prefix_a,prefix_b)!=min) {
//            System.out.print(solve(n,m,arr,brr,prefix_a,prefix_b)+" "+min);
//        }
        ans.append(min+"\n");
        System.out.print(ans);
    }
    
    public static long cnt(int n,int m,long arr[],long brr[],long prefix_a[],long prefix_b[],long val) {
        long sum=0; 
        int indx_a=jlt(n,arr,val);
        if(indx_a!=-1) {
           sum+=Math.abs(((indx_a-0+1)*val)-get(prefix_a,0,indx_a)); 
        }
        int indx_b=jgt(m,brr,val);
        if(indx_b!=-1) {
            sum+=Math.abs((((m-1)-indx_b+1)*val)-get(prefix_b,indx_b,m-1));
        }
//        System.out.println(val+" "+indx_a+" "+indx_b+" "+sum);
        return sum;
    }
    
    public static long solve(int n,int m,long arr[],long brr[],long prefix_a[],long prefix_b[]) {
        long min=Long.MAX_VALUE;
        for(long i=1;i<=100;i++) {
            long val=i;
            long sum=0; 
            int indx_a=jlt(n,arr,val);
            if(indx_a!=-1) {
               sum+=Math.abs(((indx_a-0+1)*val)-get(prefix_a,0,indx_a)); 
            }
            int indx_b=jgt(m,brr,val);
            if(indx_b!=-1) {
                sum+=Math.abs((((m-1)-indx_b+1)*val)-get(prefix_b,indx_b,m-1));
            }
            min=Math.min(min,sum);
        }
        return min;
    }
    
    public static long get(long arr[],int l,int r) {
        return arr[r]-(l==0?0:arr[l-1]);
    }
    public static int jgt(int n,long arr[],long val) {
        int l=0,r=n-1,ans=-1;
        while(l<=r) {
            int mid=(l+r)/2;
            if(arr[mid]>val) {
                ans=mid;
                r=mid-1;
            }
            else {
                l=mid+1;
            }
        }
        return ans;
    }
    public static int jlt(int n,long arr[],long val) {
        int l=0,r=n-1,ans=-1;
        while(l<=r) {
            int mid=(l+r)/2;
            if(arr[mid]<val) {
                ans=mid;
                l=mid+1;
            }
            else {
                r=mid-1;
            }
        }
        return ans;
    }
}
