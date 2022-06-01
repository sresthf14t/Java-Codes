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
public class D_Santa_Claus_and_a_Palindrome {
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
    static long p,m,pow[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        
        p=131;
        m=1000000007;
        
        //Calculating power (p^n%)m
        pow=new long[1000001];
        pow[0]=1;
        for(int i=1;i<pow.length;i++) {
            pow[i]=pow[i-1]*p;
            pow[i]%=m;
        }
        
        
        int n=input.scanInt();
        int k=input.scanInt();
        int hash[]=new int[n];
        int rev_hash[]=new int[n];
        int arr[]=new int[n];
        for(int i=0;i<n;i++) {
            String str=input.scanString();
            arr[i]=input.scanInt();
            hash[i]=(int)hash(str);
            rev_hash[i]=(int)rev_hash(str);
        }
        System.out.println(solve(n,hash,rev_hash,arr));
    }
    public static long solve(int n, int hash[], int rev_hash[], int arr[]) {
        int indx[]=new int[n];
        for(int i=0;i<n;i++) {
            indx[i]=i;
        }
        
        //Sort
        heapSort(arr, hash , rev_hash, n);
        
        HashMap<Integer,Integer> map=new HashMap<>();
        int cnt=0;
        for(int i=n-1;i>=0;i--) {
            if(map.containsKey(hash[i])) {
                hash[i]=map.get(hash[i]);
            }
            else {
                map.put(hash[i], cnt);
                hash[i]=cnt;
                cnt++;
            }
        }
        for(int i=0;i<n;i++) {
            if(map.containsKey(rev_hash[i])) {
                rev_hash[i]=map.get(rev_hash[i]);
            }
            else {
                rev_hash[i]=-1;
            }
        }
        
        ArrayList<Integer> arrli[]=new ArrayList[n];
        for(int i=0;i<n;i++) {
            arrli[i]=new ArrayList<>();
        }
        for(int i=n-1;i>=0;i--) {
            arrli[hash[i]].add(arr[i]);
        }
        long total=0;
        long min=0;
        for(int i=0;i<n;i++) {
            if(rev_hash[i]==-1) {
                continue;
            }
            if(hash[i]==rev_hash[i]) {
                if(arrli[hash[i]].size()>1 && arrli[hash[i]].get(0)+arrli[hash[i]].get(1)>0) {
                    total+=arrli[hash[i]].get(0)+arrli[hash[i]].get(1);
                    min=Math.min(min,arrli[hash[i]].get(0));
                    min=Math.min(min,arrli[hash[i]].get(1));
                    arrli[hash[i]].remove(0);
                    arrli[hash[i]].remove(0);
                }
            }
            else {
                if(arrli[hash[i]].size()>0 && arrli[rev_hash[i]].size()>0 && arrli[hash[i]].get(0)+arrli[rev_hash[i]].get(0)>0) {
                    total+=arrli[hash[i]].get(0)+arrli[rev_hash[i]].get(0);
                    arrli[hash[i]].remove(0);
                    arrli[rev_hash[i]].remove(0);
                }
            }
        }
        int max=0;
        for(int i=0;i<n;i++) {
            if(hash[i]==rev_hash[i]) {
                if(arrli[hash[i]].size()>0 && arrli[hash[i]].get(0)>0) {
                    max=Math.max(max,arrli[hash[i]].get(0));
                }
            }
        }
        if(min>=0) {
            return total+max;
        }
        if(Math.abs(min)>max) {
            return total-min;
        }
        return total+max;
    }
    public static long hash(String str) {
        long hash=0;
        for(int i=0;i<str.length();i++) {
            hash+=(str.charAt(i)*pow[i])%m;
        }
        return hash;
    }
    public static long rev_hash(String str) {
        long hash=0;
        for(int i=0;i<str.length();i++) {
            hash+=(str.charAt(str.length()-i-1)*pow[i])%m;
        }
        return hash;
    }
    
    
    static void buildMaxHeap(int arr[],int brr[],int crr[], int n) 
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
          j = (j - 1) / 2; 
        } 
      } 
    } 
  } 
  
  static void heapSort(int arr[],int brr[],int crr[], int n) 
  { 
    buildMaxHeap(arr,brr,crr, n); 
  
    for (int i = n - 1; i > 0; i--) 
    { 
      // swap value of first indexed 
      // with last indexed 
      swap(arr, 0, i); 
      swap(brr, 0, i); 
      swap(crr, 0, i); 
  
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
        }
  
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
