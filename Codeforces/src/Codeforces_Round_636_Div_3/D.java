/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_636_Div_3;

/**
 *
 * @author User
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
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int test=input.scanInt();
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            int k=input.scanInt();
            int arr[]=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt();
            }
            ans.append(solve(n,k,arr));
            ans.append("\n");
        }
        System.out.println(ans);
    }
    public static long solve(int n,int k,int arr[]) {
        int arr1[]=new int[n/2];
        int arr2[]=new int[n/2];
        int sum[]=new int[n/2];
        int max_arr[]=new int[n/2];
        int min_arr[]=new int[n/2];
        for(int i=0;i<n/2;i++) {
            arr1[i]=arr[i];
            arr2[i]=arr[n-i-1];
        }
        for(int i=0;i<n/2;i++) {
            sum[i]=arr1[i]+arr2[i];
            max_arr[i]=Math.max(arr1[i], arr2[i])+k;
            min_arr[i]=Math.min(arr1[i], arr2[i])+1;
        }
//        for(int i=0;i<n/2;i++) {
//            System.out.print(sum[i]+" ");
//        }
//        System.out.println();
//        for(int i=0;i<n/2;i++) {
//            System.out.print(max_arr[i]+" ");
//        }
//        System.out.println();
//        for(int i=0;i<n/2;i++) {
//            System.out.print(min_arr[i]+" ");
//        }
//        System.out.println();
        int sum_cnt[]=new int[2*k+4];
        for(int i=0;i<n/2;i++) {
            sum_cnt[sum[i]]++;
        }
        Arrays.sort(max_arr);
        Arrays.sort(min_arr);
        //max_arr<sum[i] && min_arr>sum[i]
        int ans=(n/2);
        for(int i=2;i<=2*k+2;i++) {
            int count1,count2;
            if(max_arr[(n/2)-1]<i) {
//                System.out.println(11111);
                count1=n/2;
            }
            else if(max_arr[0]>=i) {
//                System.out.println(22222);
                count1=0;
            }
            else {
//                System.out.println(333333);
                count1=bin_search1(0,(n/2)-1,max_arr,i)+1;
            }
            
            if(min_arr[0]>i) {
//                System.out.println(4444444);
                count2=n/2;
            }
            else if(min_arr[(n/2)-1]<=i) {
//                System.out.println(555555);
                count2=0;
            }
            else {
//                System.out.println(6666666);
                count2=(n/2)-bin_search2(0,(n/2)-1,min_arr,i)+1;
            }
            int count=(n/2)-count1-count2-sum_cnt[i];
            int fin=count+2*(count1+count2);
//            System.out.println("fin->"+i+" "+count1+" "+count2+" "+sum_cnt[i]);
            ans=Math.min(ans,fin);
        }
        return ans;
    }
    public static int bin_search1(int l,int r,int arr[],int target) {
//        System.out.println(l+" "+r+" "+target);
        int pivot=(l+r)/2;
        if(arr[pivot]<target && arr[pivot+1]>=target) {
            return pivot;
        }
        if(arr[pivot]<target) {
            return bin_search1(pivot+1,r,arr,target);
        }
        return bin_search1(l,pivot-1,arr,target);
    }
    
    public static int bin_search2(int l,int r,int arr[],int target) {
//        System.out.println(l+" "+r+" "+target);
        int pivot=(l+r)/2;
        if(arr[pivot]>target && arr[pivot-1]<=target) {
            return pivot;
        }
        if(arr[pivot]<=target) {
            return bin_search2(pivot+1,r,arr,target);
        }
        return bin_search2(l,pivot-1,arr,target);
    }
}
