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
public class C_To_Add_or_Not_to_Add {
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
    static long arr[],prefix[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int n=input.scanInt();
        int k=input.scanInt();
        arr=new long[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
        }
        solve(n,k);
    }
    public static void solve(int n,int k) {
        Arrays.sort(arr);
        prefix=new long[n];
        prefix[0]=arr[0];
        for(int i=1;i<n;i++) {
            prefix[i]=prefix[i-1]+arr[i];
        }
        int total=0;
        long max=0,ele=-1;
        for(int i=0;i<n;i++) {
            int indx=bin_search(i,k);
//            System.out.println(i+" "+indx);
            if(i-indx+1>max) {
                max=i-indx+1;
                ele=arr[i];
            }
        }
        System.out.println(max+" "+ele);
    }
    private static int bin_search(int r,long k)  {  
        int start = 0, end = r;  
    
        int ans = -1;  
        while (start <= end) {  
            int mid = (start + end) / 2;  
    
            // Move to the left side if the target is smaller  
            if (get_val(mid,r) > k) {  
                start = mid + 1;  
            }  
    
            // Move right side  
            else {  
                ans = mid;  
                end = mid - 1;  
                
            }  
        }  
        return ans;  
    }  
    public static long get_val(int indx,int r) {
        long sum=prefix[r]-(indx>0? prefix[indx-1]:0);
        long total=(r-indx+1)*arr[r];
        return total-sum;
    }
}
