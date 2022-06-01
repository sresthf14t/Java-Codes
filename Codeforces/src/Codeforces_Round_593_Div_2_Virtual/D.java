/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_593_Div_2_Virtual;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class D {
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
    static int n,m,k,x1[],y1[],x2[],y2[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n=input.scanInt();
        m=input.scanInt();
        k=input.scanInt();
        x1=new int[k];
        y1=new int[k];
        x2=new int[k];
        y2=new int[k];
        for(int i=0;i<k;i++) {
            x1[i]=input.scanInt();
            y1[i]=input.scanInt();
            x2[i]=x1[i];
            y2[i]=y1[i];
        }
        
        sort(x1,y1,k);
        sort(y2,x2,k);
        
        for(int i=0;i<k;i++) {
            int j=i;
            while(j<k && x1[i]==x1[j]) {
                j++;
            }
            j--;
            sort(y1,i,j);
            i=j;
        }
        
        for(int i=0;i<k;i++) {
            int j=i;
            while(j<k && y2[i]==y2[j]) {
                j++;
            }
            j--;
            sort(x2,i,j);
            i=j;
        }
        
        
        if(solve(1) || solve(2)) {
            System.out.println("YES");
        }
        else {
            System.out.println("NO");
        }
    }
    
    public static boolean solve(int dir) {
        
        long total=1;
        int i=1,j=1,up=0,down=n+1,left=0,right=m+1;
        while(true) {
            if(dir==1) {
                up=Math.max(up, i);
                int indx=search(i,j,dir);
                indx=Math.min(indx, right);
                indx--;
                if(indx==j) {
                    break;
                }
                total+=Math.abs(indx-j);
                j=indx;
                dir=2;
            }
            else if(dir==2) {
                right=Math.min(right,j);
                int indx=search(i,j,dir);
                indx=Math.min(indx, down);
                indx--;
                if(indx==i) {
                    break;
                }
                total+=Math.abs(indx-i);
                i=indx;
                dir=3;
            }
            else if(dir==3) {
                down=Math.min(down, i);
                int indx=search(i,j,dir);
//                System.out.println(indx+" "+left);
                indx=Math.max(indx,left);
                indx++;
                if(indx==j) {
                    break;
                }
                total+=Math.abs(indx-j);
                j=indx;
                dir=4;
            }
            else if(dir==4) {
                left=Math.max(left,j);
                int indx=search(i,j,dir);
//                System.out.println(indx);
                indx=Math.max(indx,up);
                indx++;
                if(indx==i) {
                    break;
                }
                total+=Math.abs(indx-i);
                i=indx;
                dir=1;
            }
            
//            System.out.println(i+" "+j+" "+total);
        }
        
        if(total+k==(long)n*(long)m) {
            return true;
        }
        return false;
    }
    
    public static int search(int i,int j,int dir) {
        if(dir==1) {
            int x_indx1=s_fst_indx(x1,i);
            int x_indx2=s_lst_indx(x1,i);
            if(x_indx1==-1) {
                return m+1;
            }
            int indx=jgt(y1,j,x_indx1,x_indx2);
            if(indx==-1) {
                return m+1;
            }
            return y1[indx];
        }
        if(dir==2) {
            int y_indx1=s_fst_indx(y2,j);
            int y_indx2=s_lst_indx(y2,j);
            if(y_indx1==-1) {
                return n+1;
            }
            int indx=jgt(x2,i,y_indx1,y_indx2);
            if(indx==-1) {
                return n+1;
            }
            return x2[indx];
        }
        if(dir==3) {
            int x_indx1=s_fst_indx(x1,i);
            int x_indx2=s_lst_indx(x1,i);
            if(x_indx1==-1) {
                return 0;
            }
            int indx=jlt(y1,j,x_indx1,x_indx2);
//            System.out.println(x_indx1+" "+x_indx2+" "+indx);
            if(indx==-1) {
                return 0;
            }
            return y1[indx];
        }
        if(dir==4) {
            int y_indx1=s_fst_indx(y2,j);
            int y_indx2=s_lst_indx(y2,j);
//            System.out.println(y_indx1+" "+y_indx2);
            if(y_indx1==-1) {
                return 0;
            }
            int indx=jlt(x2,i,y_indx1,y_indx2);
            if(indx==-1) {
                return 0;
            }
            return x2[indx];
        }
        return -1;
    }
    
    
    public static int s_fst_indx(int arr[],int val) {
        int l=0,r=arr.length-1,ans=-1;
        while(l<=r) {
            int mid=(l+r)/2;
            if(arr[mid]==val) {
                ans=mid;
                r=mid-1;
            }
            if(arr[mid]<val) {
                l=mid+1;
            }
            if(arr[mid]>val) {
                r=mid-1;
            }
        }
        return ans;
    }
    public static int s_lst_indx(int arr[],int val) {
        int l=0,r=arr.length-1,ans=-1;
        while(l<=r) {
            int mid=(l+r)/2;
            if(arr[mid]==val) {
                ans=mid;
                l=mid+1;
            }
            if(arr[mid]<val) {
                l=mid+1;
            }
            if(arr[mid]>val) {
                r=mid-1;
            }
        }
        return ans;
    }
    
    
    public static int jgt(int arr[],int target,int start,int end) {
    
        int ans = -1;  
        while (start <= end) {  
            int mid = (start + end) / 2;  
    
            // Move to right side if target is  
            // greater.  
            if (arr[mid] < target) {  
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
    
    
    public static int jlt(int arr[],int target,int start,int end) { 
    
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

