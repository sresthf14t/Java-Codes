/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sorting_and_Searching;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class Concert_Tickets {
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
    
    
    
    static  class seg_tree {
        static int seg_tree[][];    //[i][0]->max   [i][1]->max_count
        public seg_tree(int n,int arr[]) {
            seg_tree=new int[4*n][2];
            create_seg_tree(arr,0,0,n-1);
        }


        public void combine(int vertex) {
            if(seg_tree[(2*vertex)+1][0]>seg_tree[(2*vertex)+2][0]) {
                seg_tree[vertex][0]=seg_tree[(2*vertex)+1][0];
                seg_tree[vertex][1]=seg_tree[(2*vertex)+1][1];
            }
            else {
                seg_tree[vertex][0]=seg_tree[(2*vertex)+2][0];
                seg_tree[vertex][1]=seg_tree[(2*vertex)+2][1];
            }
        }

        //0 index-Left child-(2*i+1) Right Child-(2*i+2)

        public void create_seg_tree(int arr[],int vertex,int l,int r) {
            if(l==r) {
                seg_tree[vertex][0]=arr[r];
                seg_tree[vertex][1]=r;
                return;
            }
            int mid=(l+r)/2;
            //Left Child
            create_seg_tree(arr,(2*vertex)+1,l,mid);
            //Right Child
            create_seg_tree(arr,(2*vertex)+2,mid+1,r);
            //Filling this node

            combine(vertex);
        }

        public int[] max(int vertex,int l,int r,int ql,int qr) {  //ql->query left , qr-> query right l->curr Segmrnt left    r->curr segment right
            if(ql>qr) {
                return new int[]{-1,-1};
            }

            if(ql==l && qr==r) {
                return new int[]{seg_tree[vertex][0],seg_tree[vertex][1]};
            }
            int mid=(l+r)/2;


            //Left Child
            int max1[]=max((2*vertex)+1,l,mid,ql,Math.min(qr, mid));

            //Right Child
            int max2[]=max((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr);

            if(max1[0]>max2[0]) {
                return max1;
            }
            else {
                return max2;
            }
        }

        public void update(int vertex,int l,int r,int pos,int value) {   //pos->Position of the update   value->updates value
            if(l==r) {
                seg_tree[vertex][0]=value;
                seg_tree[vertex][1]=1;
                return;
            }
            int mid=(l+r)/2;
            //Left Child
            if(pos<=mid) {
                update((2*vertex)+1,l,mid,pos,value);
            }
            //Right Child
            else {
                update((2*vertex)+2,mid+1,r,pos,value);
            }

            combine(vertex);
        }
}

    
    
    
    
    
    
    
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int n=input.scanInt();
        int m=input.scanInt();
        int arr[]=new int[n];
        int brr[]=new int[m];
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
        }
        for(int i=0;i<m;i++) {
            brr[i]=input.scanInt();
        }
        sort(arr,n);
        StringBuilder ans=new StringBuilder("");
        seg_tree tree=new seg_tree(n,arr);
        for(int i=0;i<m;i++) {
            int indx=next(arr,brr[i]);
            if(indx==-1) {
                ans.append(-1+"\n");
                continue;
            }
            int max[]=tree.max(0, 0, n-1, 0, indx);
            if(max[0]==-1) {
                ans.append(-1+"\n");
            }
            else {
                ans.append(arr[max[1]]+"\n");
                tree.update(0, 0, n-1, max[1], -1);
            }
        }
        System.out.println(ans);
    }
    
    private static int next(int[] arr, int target)  
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
    
    
    
    static void buildMaxHeap(int arr[], int n) 
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
        if (index < (i - 1) && arr[index] < arr[index + 1]) 
          index++; 
  
        // if parent is smaller than child 
        // then swapping parent with child 
        // having higher value 
        if (index < i && arr[j] < arr[index]) 
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
