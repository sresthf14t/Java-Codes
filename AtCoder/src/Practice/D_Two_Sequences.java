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
public class D_Two_Sequences {
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
    static int pow[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int n=input.scanInt();
        int arr[]=new int[n];
        int brr[]=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
        }
        for(int i=0;i<n;i++) {
            brr[i]=input.scanInt();
        }
        pow=new int[30];
        pow[0]=1;
        for(int i=1;i<pow.length;i++) {
            pow[i]=2*pow[i-1];
        }
        int cpy1[]=new int[n];
        int cpy2[]=new int[n];
        int ans=0;
        for(int i=0;i<pow.length;i++) {
            for(int j=0;j<n;j++) {
                if((pow[i]&arr[j])!=0) {
                    cpy1[j]+=pow[i];
                }
                if((pow[i]&brr[j])!=0) {
                    cpy2[j]+=pow[i];
                }
            }
            if(solve(n,cpy1,cpy2,i)) {
                ans+=pow[i];
            }
        }
        System.out.println(ans);
    }
    public static boolean solve(int n,int cpy1[],int cpy2[],int indx) {
        int arr[]=new int[n];
        int brr[]=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=cpy1[i];
            brr[i]=cpy2[i];
        }
//        System.out.println("-------------------"+indx+"-------------------");
        sort(brr,0,n-1);
        sort(arr,0,n-1);
//        for(int i=0;i<n;i++) {
//            System.out.print(arr[i]+" ");
//        }
//        System.out.println();
//        for(int i=0;i<n;i++) {
//            System.out.print(brr[i]+" ");
//        }
//        System.out.println();
        int mid=-1;
        for(int i=0;i<n;i++) {
            if((brr[i]&pow[indx])!=0) {
                mid=i;
                break;
            }
        }
        if(mid==-1) {
            mid=n;
        }
//        System.out.println(mid);
        long cnt=0;
        for(int i=0;i<n;i++) {
            if((arr[i]&pow[indx])==0) {
                int indx1=search_0_0(brr,0,mid-1,arr[i],indx);
                int indx2=search_0_1(brr,mid,n-1,arr[i],indx);
                if(indx1==-1 && indx2==-1) {
                    continue;
                }
                if(indx1==-1) {
                    indx1=mid;
                }
                if(indx2==-1) {
                    indx2=mid-1;
                }
//                System.out.println("0 "+i+" "+indx1+" "+indx2);
                cnt+=indx2-indx1+1;
            }
            else {
                int indx1=search_0_1(brr,0,mid-1,arr[i],indx);
                int indx2=search_0_0(brr,mid,n-1,arr[i],indx);
                if(indx1==-1 && indx2==-1) {
                    continue;
                }
                if(indx1==-1) {
                    indx1=-1;
                }
                if(indx2==-1) {
                    indx2=n;
                }
//                System.out.println("1 "+i+" "+indx1+" "+indx2);
                cnt+=indx1+1+(n-1-indx2+1);
            }
        }
//        System.out.println(cnt);
        if(cnt%2==0) {
            return false;
        }
        return true;
    }
    public static int search_0_0(int arr[],int l,int r,int val,int indx) {
        if(l<0 || r<0) {
            return -1;
        }
        int ans=-1;
        while(l<=r) {
            int mid=(l+r)/2;
            int tmp=arr[mid]+val;
            if((tmp&pow[indx])!=0) {
                ans=mid;
                r=mid-1;
            }
            else {
                l=mid+1;
            }
        }
        return ans;
    }
    public static int search_0_1(int arr[],int l,int r,int val,int indx) {
        if(l<0 || r<0) {
            return -1;
        }
        int ans=-1;
        while(l<=r) {
            int mid=(l+r)/2;
            int tmp=arr[mid]+val;
            if((tmp&pow[indx])!=0) {
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
