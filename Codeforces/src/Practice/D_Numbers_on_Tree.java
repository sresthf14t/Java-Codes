/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class D_Numbers_on_Tree {
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
    static int n,parent[],sub[],num[];
    static ArrayList<Integer> adj_lst[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n=input.scanInt();
        parent=new int[n];
        sub=new int[n];
        num=new int[n];
        adj_lst=new ArrayList[n];
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<>();
        }
        int root=-1;
        for(int i=0;i<n;i++) {
            parent[i]=input.scanInt()-1;
            sub[i]=input.scanInt();
            if(parent[i]!=-1) {
                adj_lst[i].add(parent[i]);
                adj_lst[parent[i]].add(i);
            }
            else {
                root=i;
            }
        }
        if(n==1) {
            if(sub[0]==0) {
                System.out.println("YES\n"+1);
            }
            else {
                System.out.println("NO");
            }
            return;
        }
        Arrays.fill(num, -1);
        int cnt=2;
        for(int i=0;i<n;i++) {
            if(parent[i]==-1) {
                continue;
            }
            if(adj_lst[i].size()==1) {
                cnt++;
            }
        }
        int inc=1000000000/cnt,curr=inc;
        for(int i=0;i<n;i++) {
            if(parent[i]==-1) {
                continue;
            }
            if(adj_lst[i].size()==1) {
                num[i]=curr;
                curr+=inc;
            }
        }
        DFS(root);
        boolean is_pos=true;
        for(int i=0;i<n;i++) {
            if(num[i]<=0) {
                is_pos=false;
                break;
            }
            ArrayList<Integer> arrli=new ArrayList<>();
            subtree(i,arrli,new ArrayList<>());
            cnt=0;
            for(int j=0;j<arrli.size();j++) {
                if(arrli.get(j)<num[i]) {
                    cnt++;
                }
            }
            if(cnt!=sub[i]) {
                is_pos=false;
                break;
            }
        }
        if(!is_pos) {
            System.out.println("NO");
            return;
        }
        StringBuilder ans=new StringBuilder("");
        for(int i=0;i<n;i++) {
            ans.append(num[i]+" ");
        }
        System.out.println("YES\n"+ans);
    }
    public static void DFS(int root) {
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==parent[root]) {
                continue;
            }
            DFS(adj_lst[root].get(i));
        }
        if(num[root]==-1) {
            solve(root);
        }
    }
    public static void solve(int root) {
        ArrayList<Integer> arrli=new ArrayList<>();
        ArrayList<Integer> indx=new ArrayList<>();
        subtree(root,arrli,indx);
        int arr[]=new int[arrli.size()-1];
        int arr_indx[]=new int[arrli.size()-1];
        for(int i=1;i<arrli.size();i++) {
            arr[i-1]=arrli.get(i);
            arr_indx[i-1]=indx.get(i);
        }
        heapSort(arr,arr_indx,arr.length);
        int curr=10;
        for(int i=0;i<arr.length;i++) {
            arr[i]=curr;
            num[arr_indx[i]]=curr;
            curr+=10;
        }
        if(arr.length<sub[root]) {
            return;
        }
        if(sub[root]==0) {
            num[root]=(arr[0])/2;
            return;
        }
        if(arr.length==sub[root]) {
            num[root]=(arr[sub[root]-1]+1000000000)/2;
            return;
        }
        num[root]=(arr[sub[root]-1]+arr[sub[root]])/2;
    }
    public static void correct(int arr[],int arr_indx[]) {
        
    }
    public static void subtree(int root,ArrayList<Integer> arrli,ArrayList<Integer> indx) {
        arrli.add(num[root]);
        indx.add(root);
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==parent[root]) {
                continue;
            }
            subtree(adj_lst[root].get(i),arrli,indx);
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
  
  static void heapSort(int arr[],int brr[], int n) 
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
