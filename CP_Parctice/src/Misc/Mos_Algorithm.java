/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Misc;

/**
 *
 * @author Srest
 */
/*
sum range offline Queries in O(QlogQ) + O((N+Q)*√N)  
*/
import java.util.*;
import java.io.*;
public class Mos_Algorithm {
    public static void main(String args[]) throws IOException {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.nextInt();
        }
        int prefix[]=new int[n];
        prefix[0]=arr[0];
        for(int i=1;i<n;i++) {
            prefix[i]=prefix[i-1]+arr[i];
        }
        int q=input.nextInt();
        int lft[]=new int[q];
        int rgt[]=new int[q];
        int indx[]=new int[q];
        for(int i=0;i<q;i++) {
            lft[i]=input.nextInt();
            rgt[i]=input.nextInt();
            indx[i]=i;
        }
        int ans[]=Mos(n,arr,q,lft,rgt,indx);
        for(int i=0;i<q;i++) {
            System.out.println(ans[i]);
        }
    }
    public static int[] Mos(int n,int arr[],int q,int lft[],int rgt[],int indx[]) {
        sort(lft,rgt,indx,0,q-1);
        int len=(int)Math.ceil(Math.sqrt(n));
        len=Math.max(len,1);
        int strt=0,end=len-1;
        for(int i=0;i<q;i++) {
            int j=i;
            while(j<q && lft[j]<=end) {
                j++;
            }
            j--;
            if(j>=i) {
                sort(rgt,lft,indx,i,j);
            }
            i=j;
            strt+=len;
            end+=len;
        }
        int ans[]=new int[q];
        int sum=0,lptr=lft[0],rptr=rgt[0];
        for(int i=lft[0];i<=rgt[0];i++) {
            sum+=arr[i];
        }
        ans[0]=sum;
        for(int i=1;i<q;i++) {
            sum=update(arr,lptr,rptr,lft[i],rgt[i],sum);
            lptr=lft[i];
            rptr=rgt[i];
            ans[i]=sum;
        }
        sort(indx,ans,new int[q],0,q-1);
        return ans;
    }
    
    public static int update(int arr[],int lptr,int rptr,int l,int r,int sum) {
        if(l>lptr) {
            for(int i=lptr;i<l;i++) {
                sum-=arr[i];
            }
        }
        if(l<lptr) {
            for(int i=l;i<lptr;i++) {
                sum+=arr[i];
            }
        }
        if(r>rptr) {
            for(int i=rptr+1;i<=r;i++) {
                sum+=arr[i];
            }
        }
        if(r<rptr) {
            for(int i=rptr;i>r;i--) {
                sum-=arr[i];
            }
        }
        return sum;
    }
    static int partition(int arr[],int brr[],int crr[], int low, int high) 
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
                
                temp = crr[i]; 
                crr[i] = crr[j]; 
                crr[j] = temp; 
            } 
        } 
  
        // swap arr[i+1] and arr[high] (or pivot) 
        int temp = arr[i + 1]; 
        arr[i + 1] = arr[high]; 
        arr[high] = temp; 
        
        temp = brr[i + 1]; 
        brr[i + 1] = brr[high]; 
        brr[high] = temp;
        
        temp = crr[i + 1]; 
        crr[i + 1] = crr[high]; 
        crr[high] = temp;
  
        return i + 1; 
    } 
  
    /* A[] --> Array to be sorted,  
   l  --> Starting index,  
   h  --> Ending index */
    static void sort(int arr[],int brr[],int crr[], int l, int h) 
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
            int p = partition(arr,brr,crr, l, h); 
  
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
