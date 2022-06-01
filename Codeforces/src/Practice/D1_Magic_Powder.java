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
public class D1_Magic_Powder {
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
        long k=input.scanInt();
        long a[]=new long[n];
        long b[]=new long[n];
        for(int i=0;i<n;i++) {
            a[i]=input.scanInt();
        }
        for(int i=0;i<n;i++) {
            b[i]=input.scanInt();
        }
//        for(int i=0;i<n/10;i++) {
//            int l=(int)Math.random()*(n-1);
//            int r=(int)Math.random()*(n-1);
//            swap(a,l,r);
//            swap(b,l,r);
//        }
        System.out.println(solve(n,k,a,b));
    }
//    public static void swap(long arr[],int l,int r) {
//        long tmp=arr[l];
//        arr[l]=arr[r];
//        arr[r]=tmp;
//    }
    public static long solve(int n,long k,long a[],long b[]) {
        long pos[]=new long[n];
        long rem[]=new long[n];
        for(int i=0;i<n;i++) {
            pos[i]=b[i]/a[i];
            rem[i]=b[i]%a[i];
        }
//        quickSortIterative(pos,rem,a,b,0,n-1);
//        for(int i=0;i<n;i++) {
//            if(a[i]-rem[i]<=k) {
//                k-=(a[i]-rem[i]);
//                pos[i]++;
//            }
//            else {
//                break;
//            }
//        }
        sort(pos,rem,a,b,n);
        long total=0,cnt=0;
        for(int i=0;i<n;i++) {
            int j=i;
            j=i;
            long tot_rem=0;
            while(j<n && pos[j]==pos[i]) {
                total+=a[j];
                tot_rem+=rem[j];
                j++;
            }
            if(j==n) {
                cnt+=pos[i];
                break;
            }
            long diff=pos[j]-pos[i];
            if(k>=diff*total-tot_rem) {
                k-=diff*total-tot_rem;
            }
            else {
                long inc=0;
                if(total-tot_rem<=k) {
                    k-=(total-tot_rem);
                    inc++;
                }
                inc+=k/total;
                cnt+=pos[i]+inc;
                k=0;
                break;
            }
            i=j-1;
        }
        total=0;
        for(int i=0;i<n;i++) {
            total+=a[i];
        }
        cnt+=k/total;
        return cnt;
    }
    
    
    static int partition(long arr[],long brr[],long crr[],long drr[], int low, int high) 
    { 
        long pivot = arr[high]; 
  
        // index of smaller element 
        int i = (low - 1); 
        for (int j = low; j <= high - 1; j++) { 
            // If current element is smaller than or 
            // equal to pivot 
            if (arr[j] <= pivot) { 
                i++; 
  
                // swap arr[i] and arr[j] 
                long temp = arr[i]; 
                arr[i] = arr[j]; 
                arr[j] = temp; 
                
                temp = brr[i]; 
                brr[i] = brr[j]; 
                brr[j] = temp; 
                
                temp = crr[i]; 
                crr[i] = crr[j]; 
                crr[j] = temp; 
                
                temp = drr[i]; 
                drr[i] = drr[j]; 
                drr[j] = temp; 
            } 
        } 
  
        // swap arr[i+1] and arr[high] (or pivot) 
        long temp = arr[i + 1]; 
        arr[i + 1] = arr[high]; 
        arr[high] = temp; 
        
        temp = brr[i + 1]; 
        brr[i + 1] = brr[high]; 
        brr[high] = temp; 
        
        temp = crr[i + 1]; 
        crr[i + 1] = crr[high]; 
        crr[high] = temp; 
        
        temp = drr[i + 1]; 
        drr[i + 1] = drr[high]; 
        drr[high] = temp; 
  
        return i + 1; 
    } 
  
    /* A[] --> Array to be sorted,  
   l  --> Starting index,  
   h  --> Ending index */
    static void quickSortIterative(long arr[], long brr[],long crr[],long drr[],int l, int h) 
    { 
        if(l==h) {
            return;
        }
        // Create an auxiliary stack 
        int[] stack = new int[h - l + 1]; 
  
        // initialize top of stack 
        int top = -1; 
  
        // push initial values of l and h to stack 
        stack[++top] = l; 
        stack[++top] = h; 
  
        // Keep popping from stack while is not empty 
        while (top >= 0) { 
            // Pop h and l 
            h = stack[top--]; 
            l = stack[top--]; 
  
            // Set pivot element at its correct position 
            // in sorted array 
            int p = partition(arr,brr,crr,drr, l, h); 
  
            // If there are elements on left side of pivot, 
            // then push left side to stack 
            if (p - 1 > l) { 
                stack[++top] = l; 
                stack[++top] = p - 1; 
            } 
  
            // If there are elements on right side of pivot, 
            // then push right side to stack 
            if (p + 1 < h) { 
                stack[++top] = p + 1; 
                stack[++top] = h; 
            } 
        } 
    } 
    
    
    
    static void buildMaxHeap(long arr[],long brr[],long crr[],long drr[], int n) 
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
          swap(drr, j, (j - 1) / 2); 
          
          j = (j - 1) / 2; 
        } 
      } 
    } 
  } 
  
  static void sort(long arr[],long brr[],long crr[],long drr[], int n) 
  { 
    buildMaxHeap(arr,brr,crr,drr, n); 
  
    for (int i = n - 1; i > 0; i--) 
    { 
      // swap value of first indexed 
      // with last indexed 
      swap(arr, 0, i); 
      swap(brr, 0, i);
      swap(crr, 0, i);
      swap(drr, 0, i);
      
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
          swap(drr, j, index); 
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
