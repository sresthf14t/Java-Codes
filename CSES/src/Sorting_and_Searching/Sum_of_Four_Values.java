/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sorting_and_Searching;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class Sum_of_Four_Values {
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
        long x=input.scanInt();
        long arr[]=new long[n];
        long index[]=new long[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
            index[i]=i;
        }
        long sum[]=new long[(n*(n-1))/2];
        long indx1[]=new long[sum.length];
        long indx2[]=new long[sum.length];
        int cnt=0;
        for(int i=0;i<n;i++) {
            for(int j=i+1;j<n;j++) {
                sum[cnt]=arr[i]+arr[j];
                indx1[cnt]=i;
                indx2[cnt]=j;
                cnt++;
            }
        }
        sort(sum,indx1,indx2,sum.length);
//        for(int i=0;i<sum.length;i++) {
//            System.out.print(sum[i]+" ");
//        }
//        System.out.println();
        for(int i=1;i<sum.length;i++) {
            int indx=search(0,i-1,sum,x-sum[i]);
//            System.out.println(indx);
            if(indx==-1) {
                continue;
            }
            int j=indx;
            while(j>0 && sum[j]==x-sum[i]) {
                if(indx1[i]!=indx1[j] && indx2[i]!=indx2[j] && indx1[i]!=indx2[j] && indx1[j]!=indx2[i]) {
                    indx1[i]++;
                    indx2[i]++;
                    indx1[j]++;
                    indx2[j]++;
                    System.out.println(indx1[i]+" "+indx2[i]+" "+indx1[j]+" "+indx2[j]);
                    return;
                }
                j--;
            }
            
            j=indx;
            while(j<i && sum[j]==x-sum[i]) {
                if(indx1[i]!=indx1[j] && indx2[i]!=indx2[j] && indx1[i]!=indx2[j] && indx1[j]!=indx2[i]) {
                    indx1[i]++;
                    indx2[i]++;
                    indx1[j]++;
                    indx2[j]++;
                    System.out.println(indx1[i]+" "+indx2[i]+" "+indx1[j]+" "+indx2[j]);
                    return;
                }
                j++;
            }
        }
        System.out.println("IMPOSSIBLE");
    }
   
    
    public static int search(int l,int r,long arr[],long val) {
        int ans=-1;
        while(l<=r) {
            int mid=(l+r)/2;
            if(arr[mid]==val) {
                return mid;
            }
            if(arr[mid]<val) {
                l=mid+1;
            }
            else {
                r=mid-1;
            }
        }
        return -1;
    }
    
    static void buildMaxHeap(long arr[],long brr[],long crr[], int n) 
  { 
    for (int i = 1; i < n; i++) 
    { 
      // if child is bigger than parent 
      if (arr[i] > arr[(i - 1) / 2]) 
      { 
        int j = i; 
  
        // swap child and parent until 
        // parent is smaller 
        while (arr[j] > arr[(j - 1) / 2]) 
        { 
          swap(arr, j, (j - 1) / 2); 
          swap(brr, j, (j - 1) / 2); 
          swap(crr, j, (j - 1) / 2); 
          j = (j - 1) / 2; 
        } 
      } 
    } 
  } 
  
  static void sort(long arr[],long brr[],long crr[], int n) 
  { 
    buildMaxHeap(arr,brr,crr, n); 
  
    for (int i = n - 1; i > 0; i--) 
    { 
      // swap value of first indexed 
      // with last indexed 
      swap(arr, 0, i); 
      swap(brr, 0, i); 
      swap(crr, 0, i); 
  
      // maintaining heap property 
      // after each swapping 
      int j = 0, index; 
  
      do
      { 
        index = (2 * j + 1); 
  
        // if left child is smaller than 
        // right child point index variable 
        // to right child 
        if (index < (i - 1) && arr[index] < arr[index + 1]) 
          index++; 
  
        // if parent is smaller than child 
        // then swapping parent with child 
        // having higher value 
        if (index < i && arr[j] < arr[index]) {
          swap(arr, j, index); 
          swap(brr, j, index); 
          swap(crr, j, index); 
        }
  
        j = index; 
  
      } while (index < i); 
    } 
  } 
  
  public static void swap(long[] a, int i, int j) { 
    long temp = a[i]; 
    a[i]=a[j]; 
    a[j] = temp; 
  }
}
