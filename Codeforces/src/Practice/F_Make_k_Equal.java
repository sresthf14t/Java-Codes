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
public class F_Make_k_Equal {
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
        int k=input.scanInt();
        long arr[]=new long[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
        }
        sort(arr,0,n-1);
        long prefix[]=new long[n];
        prefix[0]=arr[0];
        long ans=Long.MAX_VALUE;
        for(int i=1;i<n;i++) {
            prefix[i]=arr[i]+prefix[i-1];
        }
        for(int i=0;i<n;i++) {
            int j=i,cnt=0,tmp_k=k;
            while(j<n && arr[j]==arr[i]) {
                j++;
                cnt++;
            }
            j--;
            tmp_k-=cnt;
            if(tmp_k<=0) {
                ans=Math.min(ans,0);
                i=j;
                continue;
            }
            if(i!=0) {
                long sum=get(prefix,0,i-1);
                long diff=Math.abs((i*(arr[i]-1))-sum);
                if(tmp_k<=i) {
                    diff+=tmp_k;
                    ans=Math.min(ans, diff);
                }
                else {
                    diff+=i;
                    tmp_k-=i;
                    sum=get(prefix,j+1,n-1);
                    diff+=Math.abs((n-1-j)*(arr[j]+1)-sum);
                    diff+=tmp_k;
                    ans=Math.min(ans, diff);
                }
//                System.out.println(i+" "+j+" "+diff);
            }
            tmp_k=k-cnt;
            if(i!=n-1) {
                long sum=get(prefix,j+1,n-1);
                long diff=Math.abs((n-1-j)*(arr[j]+1)-sum);
                if(tmp_k<=(n-1-j)) {
                    diff+=tmp_k;
                    ans=Math.min(ans,diff);
                }
                else {
                    diff+=(n-1-j);
                    tmp_k-=(n-1-j);
                    sum=get(prefix,0,i-1);
                    diff+=Math.abs((i*(arr[i]-1))-sum);
                    diff+=tmp_k;
                    ans=Math.min(ans,diff);
                }
//                System.out.println(i+" "+j+" "+diff);
            }
//            System.out.println(i+" "+j+" "+ans);
            if(j>i) {
                i=j;
            }
        }
        System.out.println(ans);
    }
    public static long get(long arr[],int l,int r) {
        return arr[r]-(l==0?0:arr[l-1]);
    }
}
