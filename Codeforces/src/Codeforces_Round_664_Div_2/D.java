/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_664_Div_2;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class D {
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
    static int n,d,m,arr[],less[],more[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n=input.scanInt();
        d=input.scanInt();
        m=input.scanInt();
        arr=new int[n];
        int cnt1=0,cnt2=0;
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
            if(arr[i]<=m) {
                cnt1++;
            }
            else {
                cnt2++;
            }
        }
        less=new int[cnt1];
        more=new int[cnt2];
        for(int i=0,indx1=0,indx2=0;i<n;i++) {
            if(arr[i]<=m) {
                less[indx1]=arr[i];
                indx1++;
            }
            else {
                more[indx2]=arr[i];
                indx2++;
            }
        }
        System.out.println(solve());
    }
    public static long solve() {
        Arrays.sort(less);
        Arrays.sort(more);
        reverse(more);
        reverse(less);
        long ans=0;
        if(more.length>0) {
            ans=more[0];
        }
        more=chng(more);
        int arr1[]=new int[(d+1)*(more.length/(d+1) + (more.length%(d+1)==0?0:1))];
        int arr2[]=new int[more.length+less.length-arr1.length];
        Arrays.fill(arr1, -1);
        Arrays.fill(arr2, -1);
        int indx=0;
        for(int i=0;i<arr1.length;i++) {
            if(i%(d+1)==0) {
                arr1[i]=more[indx];
                indx++;
            }
        }
        for(int i=0;i<arr1.length && indx<more.length;i++) {
            if(i%(d+1)!=0) {
                arr1[i]=more[indx];
                indx++;
            }
        }
        indx=less.length-1;
        for(int i=0;i<arr1.length;i++) {
            if(i%(d+1)!=0 && arr1[i]==-1) {
                arr1[i]=less[indx];
                indx--;
            }
        }
        indx=0;
        for(int i=0;i<arr2.length;i++) {
            arr2[i]=less[indx];
            indx++;
        }
        
//        for(int i=0;i<arr1.length;i++) {
//            System.out.print(arr1[i]+" ");
//        }
//        System.out.println();
//        for(int i=0;i<arr2.length;i++) {
//            System.out.print(arr2[i]+" ");
//        }
//        System.out.println();
        indx=arr2.length-1;
        long prefix[]=new long[arr2.length];
        prefix[0]=arr2[0];
        for(int i=1;i<prefix.length;i++) {
            prefix[i]=arr2[i]+prefix[i-1];
        }
        for(int i=0;i<arr1.length;i++) {
            if(arr1[i]<=m) {
                continue;
            }
            if(i%(d+1)==0) {
                continue;
            }
//            System.out.println(arr1[i]+" "+indx+" "+get(prefix,indx-d,indx));
            if(get(prefix,indx-d,indx)<arr1[i]) {
                int tmp=arr2[indx-d];
                arr2[indx-d]=arr1[i];
                arr1[i]=tmp;
                indx-=d+1;
            }
        }
        
        for(int i=0;i<arr1.length;i++) {
            ans+=arr1[i];
            if(arr1[i]>m) {
                i+=d;
            }
        }
        for(int i=0;i<arr2.length;i++) {
            ans+=arr2[i];
            if(arr2[i]>m) {
                i+=d;
            }
        }
//        for(int i=0;i<arr1.length;i++) {
//            System.out.print(arr1[i]+" ");
//        }
//        System.out.println();
//        for(int i=0;i<arr2.length;i++) {
//            System.out.print(arr2[i]+" ");
//        }
//        System.out.println();
        return ans;
    }
    public static void reverse(int arr[]) {
        for(int i=0,j=arr.length-1;i<j;i++,j--) {
            int tmp=arr[i];
            arr[i]=arr[j];
            arr[j]=tmp;
        }
    }
    public static long get(long prefix[],int l,int r) {
        return prefix[r]-(l==0?0:prefix[l-1]);
    }
    public static int[] chng(int arr[]) {
        if(arr.length==0) {
            return arr;
        }
        int brr[]=new int[arr.length-1];
        for(int i=1;i<arr.length;i++) {
            brr[i-1]=arr[i];
        }
        return brr;
    }
}
