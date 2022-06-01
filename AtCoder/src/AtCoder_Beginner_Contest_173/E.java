/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AtCoder_Beginner_Contest_173;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class E {
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
        int k=input.scanInt();
        long mod=1000000007L;
        int arr[]=new int[n];
        boolean pos=false;
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
            if(arr[i]>0) {
                pos=true;
            }
        }
        sort(arr,n);
        if(!pos) {
            long ans=1;
            int i,j;
            for(i=n-1,j=0;j<k;i--,j++) {
                if(arr[i]==0) {
                    j--;
                    continue;
                }
                ans*=arr[i];
                ans%=mod;
                if(ans<0) {
                    ans+=mod;
                }
            }
            if(j!=k) {
                System.out.println(0);
                return;
            }
            System.out.println(ans);
            return;
        }
        int neg=0,j=0,i=n-1;
        for(i=n-1;i>=0 && j<k;i--,j++) {
//            System.out.println(arr[i]);
            if(arr[i]==0) {
                j--;
                continue;
            }
            if(arr[i]<0) {
                neg++;
            }
        }
        if(k==n) {
            long ans=1;
            for(i=0;i<n;i++) {
                ans*=arr[i];
                ans%=mod;
                if(ans<0) {
                    ans+=mod;
                }
            }
            System.out.println(ans);
            return;
        }
        if(j!=k) {
            System.out.println(0);
            return;
        }
        if(neg%2==1) {
            long neg_val=-1,pos_val=-1;
            for(;i>=0;i--) {
                if(arr[i]==0) {
                    continue;
                }
                if(neg_val==-1 && arr[i]<0) {
                    neg_val=arr[i];
                }
                if(pos_val==-1 && arr[i]>0) {
                    pos_val=arr[i];
                }
            }
            double neg_ratio=-1,pos_ratio=-1;
            boolean neg_got=false,pos_got=false;
            for(i=n-1;i>=0;i--) {
                if(!neg_got && arr[i]>0) {
                    neg_ratio=(double)neg_val/(double)arr[i];
                    neg_ratio=Math.abs(neg_ratio);
                    neg_got=true;
                } 
                if(!pos_got && arr[i]<0) {
                    pos_ratio=(double)pos_val/(double)arr[i];
                    pos_ratio=Math.abs(pos_ratio);
                    pos_got=true;
                } 
            }
            if(!neg_got && !pos_got) {
                long ans=1;
                for(i=n-1,j=0;i>=0 && j<k;i--,j++) {
                    if(arr[i]==0) {
                        continue;
                    }
                    ans*=arr[i];
                    ans%=mod;
                    if(ans<0) {
                        ans+=mod;
                    }
                }
                if(j!=k) {
                    System.out.println(0);
                    return;
                } 
                System.out.println(ans);
                return;
            }
            if(!pos_got) {
                long ans=neg_val;
                ans+=mod;
                boolean mis=false;
                for(i=n-1,j=0;i>=0 && j<k;i--,j++) {
                    if(!mis && arr[i]>0) {
                        j--;
                        mis=true;
                        continue;
                    }
                    ans*=arr[i];
                    ans%=mod;
                    if(ans<0) {
                        ans+=mod;
                    }
                }
                if(j!=k) {
                    ans=1;
                    for(i=n-1,j=0;j<k;i--,j++) {
                        if(arr[i]==0) {
                            j--;
                            continue;
                        }
                        ans*=arr[i];
                        ans%=mod;
                        if(ans<0) {
                            ans+=mod;
                        }
                    }
                    System.out.println(ans);
                    return;
                } 
                System.out.println(ans);
                return;
            }
            else if(!neg_got) {
                long ans=pos_val;
                boolean mis=false;
                for(i=n-1,j=0;i>=0 && j<k;i--,j++) {
                    if(!mis && arr[i]<0) {
                        j--;
                        mis=true;
                        continue;
                    }
                    ans*=arr[i];
                    ans%=mod;
                    if(ans<0) {
                        ans+=mod;
                    }
                }
                if(j!=k) {
                    ans=1;
                    for(i=n-1,j=0;j<k;i--,j++) {
                        if(arr[i]==0) {
                            j--;
                            continue;
                        }
                        ans*=arr[i];
                        ans%=mod;
                        if(ans<0) {
                            ans+=mod;
                        }
                    }
                    System.out.println(ans);
                    return;
                } 
                System.out.println(ans);
                return;
            }
            else if(neg_ratio>pos_ratio) {
                long ans=neg_val;
                ans+=mod;
                boolean mis=false;
                for(i=n-1,j=0;i>=0 && j<k;i--,j++) {
                    if(!mis && arr[i]>0) {
                        j--;
                        mis=true;
                        continue;
                    }
                    ans*=arr[i];
                    ans%=mod;
                    if(ans<0) {
                        ans+=mod;
                    }
                }
                if(j!=k) {
                    ans=1;
                    for(i=n-1,j=0;j<k;i--,j++) {
                        if(arr[i]==0) {
                            j--;
                            continue;
                        }
                        ans*=arr[i];
                        ans%=mod;
                        if(ans<0) {
                            ans+=mod;
                        }
                    }
                    System.out.println(ans);
                    return;
                } 
                System.out.println(ans);
                return;
            }
            else {
                long ans=pos_val;
                boolean mis=false;
                for(i=n-1,j=0;j<k;i--,j++) {
                    if(!mis && arr[i]<0) {
                        if(arr[i]==0) {
                            j--;
                            continue;
                        }
                        j--;
                        mis=true;
                        continue;
                    }
                    ans*=arr[i];
                    ans%=mod;
                    if(ans<0) {
                        ans+=mod;
                    }
                }
                if(j!=k) {
                    ans=1;
                    for(i=n-1,j=0;j<k;i--,j++) {
                        if(arr[i]==0) {
                            j--;
                            continue;
                        }
                        ans*=arr[i];
                        ans%=mod;
                        if(ans<0) {
                            ans+=mod;
                        }
                    }
                    System.out.println(ans);
                    return;
                } 
                System.out.println(ans);
                return;
            }
        }
        
        else {
            long ans=1;
            for(i=n-1,j=0;j<k;i--,j++) {
                ans*=arr[i];
                ans%=mod;
                if(ans<0) {
                    ans+=mod;
                }
            }
            System.out.println(ans);
            return;
        }
    }
    
    
    static void buildMaxHeap(int arr[], int n) 
  { 
    for (int i = 1; i < n; i++) 
    { 
      // if child is bigger than parent 
      if (Math.abs(arr[i]) >Math.abs(arr[(i - 1) / 2])) 
      { 
        int j = i; 
  
        // swap child and parent until 
        // parent is smaller 
        while (Math.abs(arr[j]) > Math.abs(arr[(j - 1) / 2])) 
        { 
          swap(arr, j, (j - 1) / 2); 
          j = (j - 1) / 2; 
        } 
      } 
    } 
  } 
  
  static void sort(int arr[], int n) 
  { 
    buildMaxHeap(arr, n); 
  
    for (int i = n - 1; i > 0; i--) 
    { 
      // swap value of first indexed 
      // with last indexed 
      swap(arr, 0, i); 
  
      // maintaining heap property 
      // after each swapping 
      int j = 0, index; 
  
      do
      { 
        index = (2 * j + 1); 
  
        // if left child is smaller than 
        // right child point index variable 
        // to right child 
        if (index < (i - 1) && Math.abs(arr[index]) < Math.abs(arr[index + 1])) 
          index++; 
  
        // if parent is smaller than child 
        // then swapping parent with child 
        // having higher value 
        if (index < i && Math.abs(arr[j]) < Math.abs(arr[index])) 
          swap(arr, j, index); 
  
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
