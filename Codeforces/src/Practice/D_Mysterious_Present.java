/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class D_Mysterious_Present {
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
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int n=input.scanInt();
        int h=input.scanInt();
        int w=input.scanInt();
        int height1[]=new int[n];
        int width1[]=new int[n];
        int height2[]=new int[n];
        int width2[]=new int[n];
        int indx1[]=new int[n];
        int indx2[]=new int[n];
        for(int i=0;i<n;i++) {
            height1[i]=input.scanInt();
            width1[i]=input.scanInt();
            height2[i]=height1[i];
            width2[i]=width1[i];
            indx1[i]=i;
            indx2[i]=i;
        }
        sort(n,height1,width1,indx1);
        ArrayList<Integer> ret1=solve(h,w,height1,width1,n);
        sort(n,width2,height2,indx2);
        ArrayList<Integer> ret2=solve(h,w,height2,width2,n);
        ArrayList<Integer> ret;
        int ans[];
        if(ret1.size()>ret2.size()) {
            ret=new ArrayList<>(ret1);
            ans=new int[ret.size()];
            for(int i=0;i<ans.length;i++) {
                ans[i]=indx1[ret.get(i)]+1;
            }
        }
        else {
            ret=new ArrayList<>(ret2);
            ans=new int[ret.size()];
            for(int i=0;i<ans.length;i++) {
                ans[i]=indx2[ret.get(i)]+1;
            }
        }
        System.out.println(ans.length);
        for(int i=0;i<ans.length;i++) {
            System.out.print(ans[i]+" ");
        }
        System.out.println();
    }
    public static ArrayList<Integer> solve(int h,int w,int a[],int b[],int n) {
        int lis[]=new int[n];
        int max=1;
        int prev[]=new int[n];
        for(int i=0;i<n;i++) {
            lis[i]=1;
            prev[i]=-1;
        }
        for(int i=1;i<n;i++) {
            if(a[i]<=h || b[i]<=w) {
                continue;
            }
            for(int j=i-1;j>=0;j--) {
                if(a[j]<=h || b[j]<=w) {
                    continue;
                }
                if(a[i]>a[j] && b[i]>b[j] && 1+lis[j]>=lis[i]) {
                    lis[i]=1+lis[j];
                    max=Math.max(max,lis[i]);
                    prev[i]=j;
                }
            }
        }
        int indx=-1;
        for(int i=0;i<n;i++) {
            if(a[i]<=h || b[i]<=w) {
                continue;
            }
            if(lis[i]==max) {
                indx=i;
                break;
            }
        }
//        for(int i=0;i<n;i++) {
//            System.out.println(a[i]+" "+b[i]);
//        }
//        for(int i=0;i<n;i++) {
//            System.out.print(lis[i]+" ");
//        }
//        System.out.println();
        ArrayList<Integer> arr=new ArrayList<>();
        while(indx!=-1) {
            arr.add(indx);
            indx=prev[indx];
        }
        arr.sort(null);
        return arr;
    }
    public static void sort(int n,int a[],int b[],int c[]) {
        for(int i=0;i<n;i++) {
            for(int j=0;j<n-1;j++) {
                if(a[j]>a[j+1]) {
                    swap(a,j,j+1);
                    swap(b,j,j+1);
                    swap(c,j,j+1);
                }
            }
        }
    }
    public static void swap(int arr[],int l,int r) {
        int tmp=arr[l];
        arr[l]=arr[r];
        arr[r]=tmp;
    }
}
