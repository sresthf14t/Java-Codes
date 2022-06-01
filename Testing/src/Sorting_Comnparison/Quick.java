/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sorting_Comnparison;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class Quick {
    public static void main(String args[]) {
        int arr[]=new int[100];
        PrintWriter outputStream=null;
        try {
            outputStream=new PrintWriter("Test_Log.txt");
        }
        catch(FileNotFoundException e) {
            System.exit(0);
        }
        long startTime,endTime,timeElapsed;
        float quick,merge,difference,avg_diff=0,max_diff=0;
        int test=1000000;
        StringBuilder out=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            System.out.println("Test Case#"+t);
            for(int i=0;i<arr.length;i++) {
//            arr[i]=(int)(Math.random()*arr.length);
                arr[i]=i;
            }
            startTime = System.nanoTime();
//            quickSortIterative(arr,0,arr.length-1);
            Arrays.sort(arr);
            endTime = System.nanoTime();
            timeElapsed = endTime - startTime;
            quick=timeElapsed;
            out.append("QuickSort="+timeElapsed+"\t");

            startTime = System.nanoTime();
            mergeSort(arr,arr.length);
            endTime = System.nanoTime();
            timeElapsed = endTime - startTime;
            merge=timeElapsed;
            out.append("MergeSort="+timeElapsed+"\t");
            difference=(quick/merge)*100;
            avg_diff+=difference;
            max_diff=Math.max(max_diff,difference);
            out.append("Difference="+difference);
            out.append("\n");
//            System.out.println(quick+"\t"+merge);
        }
        avg_diff/=test;
        out.append("\n\n\n\n");
        out.append("Comparison=(Quick/Merge)*100\n");
        out.append("AVG Comparison="+avg_diff+"\n");
        out.append("MAX Comparison="+max_diff+"\n");
        outputStream.println(out);
        outputStream.close();
    }
    
    
    static int partition(int arr[], int low, int high) 
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
            } 
        } 
  
        // swap arr[i+1] and arr[high] (or pivot) 
        int temp = arr[i + 1]; 
        arr[i + 1] = arr[high]; 
        arr[high] = temp; 
  
        return i + 1; 
    } 
  
    /* A[] --> Array to be sorted,  
   l  --> Starting index,  
   h  --> Ending index */
    static void quickSortIterative(int arr[], int l, int h) 
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
            int p = partition(arr, l, h); 
  
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
    
    
    
    
    static void mergeSort(int arr[], int n) 
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
