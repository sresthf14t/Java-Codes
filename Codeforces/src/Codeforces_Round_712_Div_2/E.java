/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_712_Div_2;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class E {
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
        
        long arr[]=new long[n-1];
        long crr[]=new long[n-1];
        
        int a=input.scanInt(),c=input.scanInt();
        
        for(int i=0;i<n-1;i++) {
            arr[i]=input.scanInt();
            crr[i]=input.scanInt();
        }
        
        sort(arr,crr,arr.length);
        
        for(int i=0,j=arr.length-1;i<j;i++,j--) {
            swap(arr,i,j);
            swap(crr,i,j);
        }
        
        long fin=0;
        
        fin+=Math.max(c, arr[0]-a);
        
        for(int i=0;i<n-2;i++) {
            fin+=Math.max(crr[i],arr[i+1]-arr[i]);
        }
        
        fin+=Math.max(crr[n-2],a-arr[n-2]);
        
        ans.append(fin);
        
        System.out.println(ans);
    }
    
    
    static void buildMaxHeap(long arr[], long brr[], int n)
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
          j = (j - 1) / 2;
        }
      }
    }
  }
  
  static void sort(long arr[], long brr[], int n)
  {
    buildMaxHeap(arr,brr, n);
  
    for (int i = n - 1; i > 0; i--)
    {
      // swap value of first indexed
      // with last indexed
      swap(arr, 0, i);
      swap(brr, 0, i);
  
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
