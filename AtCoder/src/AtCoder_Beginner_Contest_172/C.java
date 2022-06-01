/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AtCoder_Beginner_Contest_172;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class C {
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
        int m=input.scanInt();
        long k=input.scanInt();
        long arr[]=new long[n];
        long brr[]=new long[m];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
        }
        for(int i=0;i<m;i++) {
            brr[i]=input.scanInt();
        }
        long prefix1[]=new long[n];
        long prefix2[]=new long[m];
        prefix1[0]=arr[0];
        prefix2[0]=brr[0];
        for(int i=1;i<n;i++) {
            prefix1[i]=prefix1[i-1]+arr[i];
        }
        for(int i=1;i<m;i++) {
            prefix2[i]=prefix2[i-1]+brr[i];
        }
        int ans=0;
        for(int i=0;i<n;i++) {
            if(k<prefix1[i]) {
                break;
            }
            long tmp=k-prefix1[i];
            int indx=next(prefix2,tmp);
            ans=Math.max(ans,i+indx+2);
        }
        for(int i=0;i<m;i++) {
            if(k<prefix2[i]) {
                break;
            }
            long tmp=k-prefix2[i];
            int indx=next(prefix1,tmp);
            ans=Math.max(ans,i+indx+2);
        }
        System.out.println(ans);
    }
    
    private static int next(long[] arr, long target)  
    {  
        int start = 0, end = arr.length-1;  
    
        int ans = -1;  
        while (start <= end) {  
            int mid = (start + end) / 2;  
    
            // Move to the left side if the target is smaller  
            if (arr[mid] > target) {  
                end = mid - 1;  
            }  
    
            // Move right side  
            else {  
                ans = mid;  
                start = mid + 1;  
            }  
        }  
        return ans;  
    }  
}
