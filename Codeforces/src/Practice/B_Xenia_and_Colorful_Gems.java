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
public class B_Xenia_and_Colorful_Gems {
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
        for(int t=1;t<=test;t++) {
            int n1=input.scanInt();
            int n2=input.scanInt();
            int n3=input.scanInt();
            long a[]=new long[n1];
            long b[]=new long[n2];
            long c[]=new long[n3];
            for(int i=0;i<n1;i++) {
                a[i]=input.scanInt();
            }
            for(int i=0;i<n2;i++) {
                b[i]=input.scanInt();
            }
            for(int i=0;i<n3;i++) {
                c[i]=input.scanInt();
            }
            System.out.println(solve(n1,n2,n3,a,b,c));
        }
    }
    public static long solve(int n1,int n2,int n3, long a[],long b[],long c[]) {
        Arrays.sort(a);
        Arrays.sort(b);
        Arrays.sort(c);
        long min=Long.MAX_VALUE;
        for(int i=0;i<n1;i++) {
            long num1=findClosest(b,a[i]);
            long num2=findClosest(c,a[i]);
            min=Math.min(min,get_pow(a[i],num1,num2));
        }
        
        for(int i=0;i<n2;i++) {
            long num1=findClosest(a,b[i]);
            long num2=findClosest(c,b[i]);
            min=Math.min(min,get_pow(b[i],num1,num2));
        }
        
        for(int i=0;i<n3;i++) {
            long num1=findClosest(a,c[i]);
            long num2=findClosest(b,c[i]);
            min=Math.min(min,get_pow(c[i],num1,num2));
        }
        return min;
    }
    public static long get_pow(long a,long b ,long c) {
        long num1=(a-b)*(a-b);
        long num2=(a-c)*(a-c);
        long num3=(b-c)*(b-c);
//        System.out.println(num1+" "+num2+" "+num3);
        return num1+num2+num3;
    }
    
    public static long findClosest(long arr[], long target) 
    { 
        int n = arr.length; 
  
        // Corner cases 
        if (target <= arr[0]) 
            return arr[0]; 
        if (target >= arr[n - 1]) 
            return arr[n - 1]; 
  
        // Doing binary search  
        int i = 0, j = n, mid = 0; 
        while (i < j) { 
            mid = (i + j) / 2; 
  
            if (arr[mid] == target) 
                return arr[mid]; 
  
            /* If target is less than array element, 
               then search in left */
            if (target < arr[mid]) { 
         
                // If target is greater than previous 
                // to mid, return closest of two 
                if (mid > 0 && target > arr[mid - 1])  
                    return getClosest(arr[mid - 1],  
                                  arr[mid], target); 
                  
                /* Repeat for left half */
                j = mid;               
            } 
  
            // If target is greater than mid 
            else { 
                if (mid < n-1 && target < arr[mid + 1])  
                    return getClosest(arr[mid],  
                          arr[mid + 1], target);                 
                i = mid + 1; // update i 
            } 
        } 
  
        // Only single element left after search 
        return arr[mid]; 
    } 
  
    // Method to compare which one is the more close 
    // We find the closest by taking the difference 
    //  between the target and both values. It assumes 
    // that val2 is greater than val1 and target lies 
    // between these two. 
    public static long getClosest(long val1, long val2,  long target) 
    { 
        if (target - val1 >= val2 - target)  
            return val2;         
        else 
            return val1;         
    } 
}
