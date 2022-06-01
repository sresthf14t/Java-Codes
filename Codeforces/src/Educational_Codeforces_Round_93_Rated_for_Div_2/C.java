/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Educational_Codeforces_Round_93_Rated_for_Div_2;

/**
 *
 * @author Srest
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
        StringBuilder ans=new StringBuilder("");
        int test=input.scanInt();
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            int arr[]=new int[n];
            String str=input.scanString();
            for(int i=0;i<n;i++) {
                arr[i]=str.charAt(i)-48;
            }
            ans.append(solve(n,arr)+"\n");
        }
        System.out.println(ans);
    }
    public static long solve(int n,int arr[]) {
        long ans=0;
        int prefix[]=new int[n];
        prefix[0]=arr[0];
        for(int i=1;i<n;i++) {
            prefix[i]=prefix[i-1]+arr[i];
        }
        int indx[]=new int[n];
        Arrays.fill(indx, -1);
        for(int i=0;i<n;i++) {
            int sum=0;
            for(int j=i;j<n;j++) {
                sum=sum(prefix,i,j);
                if(sum==(j-i+1)) {
                    indx[i]=j;
                    break;
                }
                if(sum>(j-i+1)) {
                    j=(i+sum-1)-1;
                }
                else {
//                    System.out.print(i+" "+j+" ");
                    j=next(prefix,(j-i+1),i);
//                    System.out.println(j);
                    if(j==-1) {
                        break;
                    }
                    j--;
                }
            }
        }
//        for(int i=0;i<n;i++) {
//            System.out.print(indx[i]+" ");
//        }
//        System.out.println();
        for(int i=0;i<n;i++) {
            if(indx[i]==-1) {
                continue;
            }
            int curr=i;
            long cnt=1;
            while(true) {
//                System.out.println(i+" "+curr);
                
                if(indx[curr]+1>=n || indx[indx[curr]+1]==-1) {
                    indx[curr]=-1;
                    break;
                }
                else {
                    cnt++;
                    int tmp=curr;
                    curr=indx[curr]+1; 
                    indx[tmp]=-1;
                }
                
            }
            ans+=(cnt*(cnt+1))/2;
        }
        return ans;
    }
    
    static int next(int[] arr, int target,int front)  {  
        int start = front, end = arr.length - 1;  
    
        int ans = -1;  
        while (start <= end) {  
            int mid = (start + end) / 2;  
    
            // Move to right side if target is  
            // greater.  
            if (sum(arr,front,mid) <= target) {  
                start = mid + 1;  
            }  
    
            // Move left side.  
            else {  
                ans = mid;  
                end = mid - 1;  
            }  
        }  
        return ans;  
    }
    
    public static int sum(int arr[],int l,int r) {
        return arr[r]-(l==0?0:arr[l-1]);
    }
}