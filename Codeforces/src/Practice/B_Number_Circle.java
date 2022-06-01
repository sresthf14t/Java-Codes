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
import java.io.*; 
import java.util.*; 
import java.io.PrintWriter;
public class B_Number_Circle {
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
        int arr[]=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
        }
        sort(arr,n);
        int tmp=arr[n-1];
        arr[n-1]=arr[n-2];
        arr[n-2]=tmp;
        if(arr[n-1]>=arr[n-2]+arr[0]) {
            System.out.println("NO");
            return;
        }
        for(int i=1;i<n-1;i++) {
            if(arr[i]>=arr[i-1]+arr[i+1]) {
                System.out.println("NO");
                return;
            }
        }
        StringBuilder ans=new StringBuilder("YES\n");
        for(int i=0;i<n;i++) {
            ans.append(arr[i]+" ");
        }
        System.out.println(ans);
        
    }
    
    
    static void sort(int arr[], int n) 
    { 
          
        // For current size of subarrays to 
        // be merged curr_size varies from  
        // 1 to n/2 
        int curr_size;  
                      
        // For picking starting index of  
        // left subarray to be merged 
        int left_start; 
                          
          
        // Merge subarrays in bottom up  
        // manner. First merge subarrays  
        // of size 1 to create sorted  
        // subarrays of size 2, then merge 
        // subarrays of size 2 to create  
        // sorted subarrays of size 4, and 
        // so on. 
        for (curr_size = 1; curr_size <= n-1;  
                      curr_size = 2*curr_size) 
        { 
              
            // Pick starting point of different 
            // subarrays of current size 
            for (left_start = 0; left_start < n-1; 
                        left_start += 2*curr_size) 
            { 
                // Find ending point of left  
                // subarray. mid+1 is starting  
                // point of right 
                int mid = Math.min(left_start + curr_size - 1, n-1); 
          
                int right_end = Math.min(left_start  
                             + 2*curr_size - 1, n-1); 
          
                // Merge Subarrays arr[left_start...mid] 
                // & arr[mid+1...right_end] 
                merge(arr, left_start, mid, right_end); 
            } 
        } 
    } 
      
    /* Function to merge the two haves arr[l..m] and 
    arr[m+1..r] of array arr[] */
    static void merge(int arr[], int l, int m, int r) 
    { 
        int i, j, k; 
        int n1 = m - l + 1; 
        int n2 = r - m; 
      
        /* create temp arrays */
        int L[] = new int[n1]; 
        int R[] = new int[n2]; 
      
        /* Copy data to temp arrays L[] 
        and R[] */
        for (i = 0; i < n1; i++) 
            L[i] = arr[l + i]; 
        for (j = 0; j < n2; j++) 
            R[j] = arr[m + 1+ j]; 
      
        /* Merge the temp arrays back into 
        arr[l..r]*/
        i = 0; 
        j = 0; 
        k = l; 
        while (i < n1 && j < n2) 
        { 
            if (L[i] <= R[j]) 
            { 
                arr[k] = L[i]; 
                i++; 
            } 
            else
            { 
                arr[k] = R[j]; 
                j++; 
            } 
            k++; 
        } 
      
        /* Copy the remaining elements of  
        L[], if there are any */
        while (i < n1) 
        { 
            arr[k] = L[i]; 
            i++; 
            k++; 
        } 
      
        /* Copy the remaining elements of 
        R[], if there are any */
        while (j < n2) 
        { 
            arr[k] = R[j]; 
            j++; 
            k++; 
        } 
    } 
}
