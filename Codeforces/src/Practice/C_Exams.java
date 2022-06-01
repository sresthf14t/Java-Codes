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
public class C_Exams {
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
        int a[]=new int[n];
        int b[]=new int[n];
        for(int i=0;i<n;i++) {
            a[i]=input.scanInt();
            b[i]=input.scanInt();
        }
        System.out.println(solve(n,a,b));
    }
    public static int solve(int n,int a[],int b[]) {
        if(n!=1) {
            quickSortIterative(a,b,0,n-1);
        }
        for(int i=0;i<n;i++) {
            int j=i;
            while(j<n && a[j]==a[i]) {
                j++;
            }
            j--;
            if(i==j) {
                continue;
            }
            Arrays.sort(b,i,j+1);
        }
        int ans=b[0];
        for(int i=1;i<n;i++) {
            if(b[i]>=ans) {
                ans=b[i];
            }
            else {
                ans=a[i];
            }
        }
        return ans;
    }
    
    static int partition(int arr[],int brr[] ,int low, int high) 
    { 
        int pivot = arr[high]; 
  
        // index of smaller element 
        int i = (low - 1); 
        for (int j = low; j <= high - 1; j++) { 
            // If current element is smaller than or 
            // equal to pivot 
            if (arr[j] <= pivot) { 
                i++; 
  
                // swap arr[i] and arr[j] 
                int temp = arr[i]; 
                arr[i] = arr[j]; 
                arr[j] = temp; 
                
                temp = brr[i]; 
                brr[i] = brr[j]; 
                brr[j] = temp; 
            } 
        } 
  
        // swap arr[i+1] and arr[high] (or pivot) 
        int temp = arr[i + 1]; 
        arr[i + 1] = arr[high]; 
        arr[high] = temp; 
        
        temp = brr[i + 1]; 
        brr[i + 1] = brr[high]; 
        brr[high] = temp; 
  
        return i + 1; 
    } 
  
    /* A[] --> Array to be sorted,  
   l  --> Starting index,  
   h  --> Ending index */
    static void quickSortIterative(int arr[], int brr[],int l, int h) 
    { 
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
            int p = partition(arr,brr ,l, h); 
  
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
}
