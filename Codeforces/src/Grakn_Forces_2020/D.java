/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grakn_Forces_2020;

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
    static HashMap<Integer,Integer> dpx[],dpy[];
    static int n,m,rx[],ry[],sx[],sy[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n=input.scanInt();
        m=input.scanInt();
        rx=new int[n];
        ry=new int[n];
        sx=new int[m];
        sy=new int[m];
        for(int i=0;i<n;i++) {
            rx[i]=input.scanInt();
            ry[i]=input.scanInt();
        }
        int max_x=0,max_y=0;
        for(int i=0;i<m;i++) {
            sx[i]=input.scanInt();
            sy[i]=input.scanInt();
            max_x=Math.max(max_x,sx[i]);
            max_y=Math.max(max_y,sy[i]);
        }
        int diff[]=new int[n];
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(rx[i]<=sx[j] && ry[i]<=sy[j]) {
                    diff[i]=Math.max(diff[i],sx[j]-rx[i]+sy[j]-ry[i]);
                }
            }
        }
        heapSort(diff,rx,ry,n);
        for(int i=0,j=n-1;i<j;i++,j--) {
            swap(rx,i,j);
            swap(ry,i,j);
        }
        dpx=new HashMap[1000011];
        dpy=new HashMap[1000011];
        for(int i=0;i<dpx.length;i++) {
            dpx[i]=new HashMap<>();
            dpy[i]=new HashMap<>();
        }
        int x=0,y=0;
        for(int i=0;i<n;i++) {
            if(rx[i]>max_x || ry[i]>max_y) {
                continue;
            }
            solve(rx[i],ry[i]);
            int tmp_x=dpx[rx[i]].get(ry[i]);
            int tmp_y=dpy[rx[i]].get(ry[i]);
//            System.out.println(tmp_x+" "+tmp_y);
            for(int j=i+1;j<n;j++) {
                rx[j]+=tmp_x;
            }
            for(int j=i+1;j<n;j++) {
                ry[j]+=tmp_y;
            }
            x+=tmp_x;
            y+=tmp_y;
        }
        System.out.println(x+y);
    }
    public static void solve(int x,int y) {
//        System.out.println(x+" "+y);
        int minx=10000000,miny=10000000;
        boolean done=false;
        for(int i=0;i<m;i++) {
            if(x<=sx[i] && y<=sy[i]) {
                done=true;
                solve(sx[i]+1,y);
                solve(x,sy[i]+1);
                if(dpx[sx[i]+1].get(y)+dpy[sx[i]+1].get(y)+sx[i]+1-x<minx+miny) {
                    minx=dpx[sx[i]+1].get(y)+sx[i]+1-x;
                    miny=dpy[sx[i]+1].get(y);
                }
                if(dpx[x].get(sy[i]+1)+dpy[x].get(sy[i]+1)+sy[i]+1-y<minx+miny) {
                    minx=dpx[x].get(sy[i]+1);
                    miny=dpy[x].get(sy[i]+1)+sy[i]+1-y;
                }
            }
        }
        if(!done) {
            minx=0;
            miny=0;
        }
        dpx[x].put(y, minx);
        dpy[x].put(y, miny);
    }
    
    
    static void buildMaxHeap(int arr[], int brr[],int crr[],int n) 
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
  
  static void heapSort(int arr[],int brr[],int crr[],int n) 
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
  
  public static void swap(int[] a, int i, int j) { 
    int temp = a[i]; 
    a[i]=a[j]; 
    a[j] = temp; 
  } 
}
