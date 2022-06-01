/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Educational_Codeforces_Round_104_Rated_for_Div_2;

/**
 *
 * @author Srest
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
    
    public static void sort(int arr[],int l,int r) {    //sort(arr,0,n-1);
        if(l==r) {
            return;
        }
        int mid=(l+r)/2;
        sort(arr,l,mid);
        sort(arr,mid+1,r);
        merge(arr,l,mid,mid+1,r);
    }
    public static void merge(int arr[],int l1,int r1,int l2,int r2) {
        int tmp[]=new int[r2-l1+1];
        int indx1=l1,indx2=l2;
        //sorting the two halves using a tmp array
        for(int i=0;i<tmp.length;i++) {
            if(indx1>r1) {
                tmp[i]=arr[indx2];
                indx2++;
                continue;
            }
            if(indx2>r2) {
                tmp[i]=arr[indx1];
                indx1++;
                continue;
            }
            if(arr[indx1]<arr[indx2]) {
                tmp[i]=arr[indx1];
                indx1++;
                continue;
            }
            tmp[i]=arr[indx2];
            indx2++;
        }
        //Copying the elements of tmp into the main array
        for(int i=0,j=l1;i<tmp.length;i++,j++) {
            arr[j]=tmp[i];
        }
    }
    
    public static void sort(long arr[],int l,int r) {    //sort(arr,0,n-1);
        if(l==r) {
            return;
        }
        int mid=(l+r)/2;
        sort(arr,l,mid);
        sort(arr,mid+1,r);
        merge(arr,l,mid,mid+1,r);
    }
    public static void merge(long arr[],int l1,int r1,int l2,int r2) {
        long tmp[]=new long[r2-l1+1];
        int indx1=l1,indx2=l2;
        //sorting the two halves using a tmp array
        for(int i=0;i<tmp.length;i++) {
            if(indx1>r1) {
                tmp[i]=arr[indx2];
                indx2++;
                continue;
            }
            if(indx2>r2) {
                tmp[i]=arr[indx1];
                indx1++;
                continue;
            }
            if(arr[indx1]<arr[indx2]) {
                tmp[i]=arr[indx1];
                indx1++;
                continue;
            }
            tmp[i]=arr[indx2];
            indx2++;
        }
        //Copying the elements of tmp into the main array
        for(int i=0,j=l1;i<tmp.length;i++,j++) {
            arr[j]=tmp[i];
        }
    }
    
    static int maxx;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        
        maxx=500000000;
        
        int n1=input.scanInt();
        int n2=input.scanInt();
        int n3=input.scanInt();
        int n4=input.scanInt();
        
        int arr1[]=new int[n1];
        int arr2[]=new int[n2];
        int arr3[]=new int[n3];
        int arr4[]=new int[n4];
        
        for(int i=0;i<n1;i++) {
            arr1[i]=input.scanInt();
        }
        
        for(int i=0;i<n2;i++) {
            arr2[i]=input.scanInt();
        }
        
        for(int i=0;i<n3;i++) {
            arr3[i]=input.scanInt();
        }
        
        for(int i=0;i<n4;i++) {
            arr4[i]=input.scanInt();
        }
        
        int m1=input.scanInt();
        ArrayList<Integer> adj_lst1[]=new ArrayList[n1];
        for(int i=0;i<n1;i++) {
            adj_lst1[i]=new ArrayList<>();
        }
        for(int i=0;i<m1;i++) {
            int u=input.scanInt()-1;
            int v=input.scanInt()-1;
            adj_lst1[u].add(v);
//            adj_lst1[v].add(u);
        }
        
        
        
        int m2=input.scanInt();
        ArrayList<Integer> adj_lst2[]=new ArrayList[n2];
        for(int i=0;i<n2;i++) {
            adj_lst2[i]=new ArrayList<>();
        }
        for(int i=0;i<m2;i++) {
            int u=input.scanInt()-1;
            int v=input.scanInt()-1;
            adj_lst2[u].add(v);
//            adj_lst2[v].add(u);
        }
        
        
        int m3=input.scanInt();
        ArrayList<Integer> adj_lst3[]=new ArrayList[n3];
        for(int i=0;i<n3;i++) {
            adj_lst3[i]=new ArrayList<>();
        }
        for(int i=0;i<m3;i++) {
            int u=input.scanInt()-1;
            int v=input.scanInt()-1;
            adj_lst3[u].add(v);
//            adj_lst3[v].add(u);
        }
        
        
        solve(n3,n4,arr3,arr4,adj_lst3);
        
        solve(n2,n3,arr2,arr3,adj_lst2);
        
        solve(n1,n2,arr1,arr2,adj_lst1);
        
        sort(arr1,0,arr1.length-1);
        
        if(arr1[0]>=maxx) {
            System.out.println(-1);
            return;
        }
        System.out.println(arr1[0]);
        
//        print(arr1);
//        print(arr2);
//        print(arr3);
//        print(arr4);
    }
    
    public static void print(int arr[]) {
        System.out.println();
        for(int i=0;i<arr.length;i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
    
    public static void solve(int n1,int n2,int arr1[],int arr2[],ArrayList<Integer> adj_lst[]) {
        int indx[]=new int[n2];
        for(int i=0;i<n2;i++) {
            indx[i]=i;
        }
        
        sort(arr2,indx,arr2.length);
        
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<n2;i++) {
            map.put(indx[i], i);
        }
        
//        if(n1==4) {
//            for(int i=0;i<n1;i++) {
//                System.out.print(i+"->");
//                for(int j=0;j<adj_lst[i].size();j++) {
//                    System.out.print(adj_lst[i].get(j)+" ");
//                }
//                System.out.println();
//            }
//        }
        
        for(int i=0;i<n1;i++) {
            for(int j=0;j<adj_lst[i].size();j++) {
                adj_lst[i].set(j, map.get(adj_lst[i].get(j)));
            }
        }
        
        for(int i=0;i<n1;i++) {
            adj_lst[i].sort(null);
        }
        
//        if(n1==4) {
//            for(int i=0;i<n1;i++) {
//                System.out.print(i+"->");
//                for(int j=0;j<adj_lst[i].size();j++) {
//                    System.out.print(adj_lst[i].get(j)+" ");
//                }
//                System.out.println();
//            }
//        }
//        System.out.println();
        
        for(int i=0;i<n1;i++) {
            int idx=0;
            for(int j=0;j<adj_lst[i].size();j++,idx++) {
                if(adj_lst[i].get(j)!=idx) {
                    break;
                }
            }
            if(idx==n2) {
                arr1[i]+=maxx;
            }
            else {
                arr1[i]+=arr2[idx];
            }
        }
         
    }
    
    
    static void buildMaxHeap(int arr[],int brr[], int n) 
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
          j = (j - 1) / 2; 
        } 
      } 
    } 
  } 
  
  static void sort(int arr[],int brr[], int n) 
  { 
    buildMaxHeap(arr,brr, n); 
  
    for (int i = n - 1; i > 0; i--) 
    { 
      // swap value of first indexed 
      // with last indexed 
      swap(arr, 0, i);
      swap(brr, 0, i);
  
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
