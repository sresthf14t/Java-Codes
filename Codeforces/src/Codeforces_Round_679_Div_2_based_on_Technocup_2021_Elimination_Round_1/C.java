/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_679_Div_2_based_on_Technocup_2021_Elimination_Round_1;

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
    
    static class Node { 

        int ele; 
  

        int i; 
  

        int j; 
  
        Node(int a, int b, int c) 
        { 
            this.ele = a; 
            this.i = b; 
            this.j = c; 
        } 
    } 
  

    static class MinHeap { 
        Node[] harr; // array of elements in heap 
        int size; // size of min heap 
  

        MinHeap(Node[] arr, int size) 
        { 
            this.harr = arr; 
            this.size = size; 
            int i = (size - 1) / 2; 
            while (i >= 0) { 
                MinHeapify(i); 
                i--; 
            } 
        } 
  

        int left(int i) 
        { 
            return 2 * i + 1; 
        } 
  

        int right(int i) 
        { 
            return 2 * i + 2; 
        } 
  

        void MinHeapify(int i) 
        { 
            int l = left(i); 
            int r = right(i); 
            int small = i; 
            if (l < size && harr[l].ele < harr[i].ele) 
                small = l; 
            if (r < size && harr[r].ele < harr[small].ele) 
                small = r; 
            if (small != i) { 
                swap(small, i); 
                MinHeapify(small); 
            } 
        } 
  
        void swap(int i, int j) 
        { 
            Node temp = harr[i]; 
            harr[i] = harr[j]; 
            harr[j] = temp; 
        } 
  

        Node getMin() 
        { 
            return harr[0]; 
        } 
  

        void replaceMin(Node x) 
        { 
            harr[0] = x; 
            MinHeapify(0); 
        } 
    } 
  

    static void solve(int[][] arr, int k) 
    { 
        int range = Integer.MAX_VALUE; 
        int min = Integer.MAX_VALUE; 
        int max = Integer.MIN_VALUE; 
        int start = -1, end = -1; 
  
        int n = arr[0].length; 
  

        Node[] arr1 = new Node[k]; 
        for (int i = 0; i < k; i++) { 
            Node node = new Node(arr[i][0], i, 1); 
            arr1[i] = node; 
  

            max = Math.max(max, node.ele); 
        } 
  

        MinHeap mh = new MinHeap(arr1, k); 
  

        while (true) { 

            Node root = mh.getMin(); 
  

            min = root.ele; 
  

            if (range > max - min + 1) { 
                range = max - min + 1; 
                start = min; 
                end = max; 
            } 
  

            if (root.j < n) { 
                root.ele = arr[root.i][root.j]; 
                root.j++; 
  

                if (root.ele > max) 
                    max = root.ele; 
            } 

            else
                break; 
  

            mh.replaceMin(root); 
        } 
        System.out.println(end-start); 
    } 
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int arr[]=new int[6];
        for(int i=0;i<6;i++) {
            arr[i]=input.scanInt();
        }
        sort(arr,0,arr.length-1);
        int n=input.scanInt();
        int brr[]=new int[n];
        for(int i=0;i<n;i++) {
            brr[i]=input.scanInt();
        }
        sort(brr,0,n-1);
        int crr[][]=new int[n][6];
        for(int i=0;i<n;i++) {
            for(int j=0;j<6;j++) {
                crr[i][j]=brr[i]-arr[j];
            }
            sort(crr[i],0,6-1);
        }
//        solve(crr,n);
        System.out.println(solve1(n,6,crr));
    }
    public static int solve1(int n,int m,int crr[][]) {
        TreeMap<Integer,Stack<Integer>> map=new TreeMap<>();
        int ans=Integer.MAX_VALUE;
        int indx[]=new int[n];
        for(int i=0;i<n;i++) {
            if(!map.containsKey(crr[i][0])) {
                Stack<Integer> stck=new Stack<>();
                stck.add(i);
                map.put(crr[i][0], stck);
            }
            else {
                Stack<Integer> stck=map.get(crr[i][0]);
                stck.add(i);
            }
        }
        while(true) {
            ans=Math.min(ans,map.lastKey()-map.firstKey());
//            System.out.println(ans);
            Stack<Integer> stck=map.get(map.firstKey());
            int tmp=stck.pop();
            
            indx[tmp]++;
            if(indx[tmp]==crr[0].length) {
                break;
            }
            if(stck.isEmpty()) {
                map.remove(map.firstKey());
            }
            if(!map.containsKey(crr[tmp][indx[tmp]])) {
                Stack<Integer> stck1=new Stack<>();
                stck1.add(tmp);
                map.put(crr[tmp][indx[tmp]], stck1);
            }
            else {
                Stack<Integer> stck1=map.get(crr[tmp][indx[tmp]]);
                stck1.add(tmp);
            }
        }
        ans=Math.min(ans,map.lastKey()-map.firstKey());
        return ans;
    }
}
