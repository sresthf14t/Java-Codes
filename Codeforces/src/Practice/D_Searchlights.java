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
public class D_Searchlights {
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
        int n=input.scanInt();
        int m=input.scanInt();
        int rx[]=new int[n];
        int ry[]=new int[n];
        int sx[]=new int[m];
        int sy[]=new int[m];
        int arr[]=new int[1000005];
        Arrays.fill(arr, -1);
        for(int i=0;i<n;i++) {
            rx[i]=input.scanInt();
            ry[i]=input.scanInt();
        }
        for(int i=0;i<m;i++) {
            sx[i]=input.scanInt();
            sy[i]=input.scanInt();
            arr[sx[i]]=Math.max(arr[sx[i]],sy[i]);
        }
        int curr=-1;
        for(int i=arr.length-1;i>=0;i--) {
            curr=Math.max(curr,arr[i]);
            arr[i]=curr;
        }
        sort(sx,0,sx.length-1);
        int ans=Integer.MAX_VALUE;
        for(int i=0;i<arr.length;i++) {
//            System.out.println(i);
            int tmp=0,indx=Integer.MAX_VALUE;
            for(int j=0;j<n;j++) {
                if(rx[j]+i>=arr.length) {
                    continue;
                }
                if(ry[j]<=arr[rx[j]+i]) {
                    tmp=Math.max(tmp,Math.abs(ry[j]-arr[rx[j]+i])+1);
                }
                int tmp_1=jgt(sx,rx[j]+i);
                if(tmp_1==-1) {
                    continue;
                }
                indx=Math.min(indx,Math.abs(rx[j]+i-sx[tmp_1]));
            }
            ans=Math.min(ans,i+tmp);
            for(int k=-2;k<2;k++) {
                if(i+k<0) {
                    continue;
                }
                tmp=0;
                for(int j=0;j<n;j++) {
                    if(rx[j]+i+k>=arr.length) {
                        continue;
                    }
                    if(ry[j]<=arr[rx[j]+i+k]) {
                        tmp=Math.max(tmp,Math.abs(ry[j]-arr[rx[j]+i+k])+1);
                    }
                }
    //            System.out.println(i+" "+tmp);
                ans=Math.min(ans,i+k+tmp);
            }
            if(indx==Integer.MAX_VALUE) {
                break;
            }
//            System.out.println(i+" "+indx);
            if(indx>0) {
                i+=indx-1;
            }
            
        }
        System.out.println(ans);
    }
    
    public static int jgt(int arr[],int val) {
        int l=0,r=arr.length-1,ans=-1;
        while(l<=r) {
            int mid=(l+r)/2;
            if(arr[mid]>=val) {
                ans=mid;
                r=mid-1;
            }
            else {
                l=mid+1;
            }
        }
        return ans;
    }
}
