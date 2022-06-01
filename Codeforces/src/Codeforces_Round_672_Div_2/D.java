/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_672_Div_2;

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
    
    static class seg_tree {
        int seg_tree[];
        public seg_tree(int n,int arr[]) {
            seg_tree=new int[4*n];
            built(arr,0,0,n-1);
        }
        public void built(int arr[], int vertex, int l,int r) {
            if(l==r) {
                seg_tree[vertex]=arr[r];
                return;
            }
            int mid=(l+r)/2;

            //Left Child
            built(arr,(2*vertex)+1, l, mid);

            //Right Child
            built(arr,(2*vertex)+2, mid+1, r);

            seg_tree[vertex]=0;
        }

        public void update(int vertex, int l, int r, int ql, int qr , int add) {
            if(ql>qr) {
                return;
            }
            if(l==ql && r==qr) {
                seg_tree[vertex]+=add;
                return;
            }
            int mid=(l+r)/2;
            //Left Child
            update((2*vertex)+1,l,mid,ql,Math.min(mid,qr),add);
            //Right Child
            update((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr,add);
        }
        public int get(int vertex, int l, int r, int pos) {
            if(l==r) {
                return seg_tree[vertex];
            }
            int mid=(l+r)/2;
            //Left Child
            if(pos<=mid) {
               return seg_tree[vertex]+get((2*vertex)+1,l,mid,pos); 
            }
            //Right Child
            else {
                return seg_tree[vertex]+get((2*vertex)+2,mid+1,r,pos);
            }
        }
    }

    
    static long mod,fact[];
    static int n,l[],r[],count=0;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n=input.scanInt();
        int k=input.scanInt();
        l=new int[n];
        r=new int[n];
        fact=new long[n+1];
        long ans=0;
        mod=998244353;
        fact[0]=1;
        for(int i=1;i<fact.length;i++) {
            fact[i]=i*fact[i-1];
            fact[i]%=mod;
        }
        for(int i=0;i<n;i++) {
            l[i]=input.scanInt();
            r[i]=input.scanInt();
        }
        heapSort(l,r,new int[n],n);
        solve(0,0,Integer.MAX_VALUE,k,new ArrayList<>());
        System.out.println(count);
//        System.exit(0);
        for(int i=0;i<n;i++) {
            System.out.println(i+" "+l[i]+" "+r[i]);
        }
        int sub[]=new int[n];
        int rem=n,cnt=0;
        for(int i=0;i<n;i++) {
            rem--;
            cnt-=sub[i];
            int indx=jgt(l,r[i]);
            if(indx==-1) {
                cnt++;
                continue;
            }
            if(rem+1+cnt<k) {
                continue;
            }
            sub[indx]++;
            int set1=n-1-indx+1;
            int set2=rem-set1+cnt;
            long tmp=ncr(set1+set2,k-1);
            if(set2>=k-1) {
                tmp-=ncr(set2,k-1);
            }
            System.out.println(l[i]+" "+r[i]+" "+indx+" "+set1+" "+set2+" "+tmp+" "+cnt);
            ans+=tmp;
            ans%=mod;
            cnt++;
        }
        System.out.println(ans);
//        if(k>2) {
//            ans*=ncr(n-2,k-2);
//        }
        System.out.println(ncr(n,k));
        ans%=mod;
        ans=ncr(n,k)-ans;
        System.out.println(ans);
        ans%=mod;
        if(ans<0) {
            ans+=mod;
        }
        System.out.println(ans);
    }
    
    static int jlt(int[] arr, int target)  
    {  
        int start = 0, end = arr.length-1;  
    
        int ans = -1;  
        while (start <= end) {  
            int mid = (start + end) / 2;  
    
            // Move to the left side if the target is smaller  
            if (arr[mid] >= target) {  
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
    static int jgt(int[] arr, int target)  
    {  
        int start = 0, end = arr.length - 1;  
    
        int ans = -1;  
        while (start <= end) {  
            int mid = (start + end) / 2;  
    
            // Move to right side if target is  
            // greater.  
            if (arr[mid] <= target) {  
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
    
    
    public static long ncr(int n,int r) {
        long ans=fact[n];
        ans*=Inverse(fact[n-r],mod);
        ans%=mod;
        ans*=Inverse(fact[r],mod);
        ans%=mod;
        return ans;
    }
    
    
    public static long Inverse(long a,long n) {
        long q,r1=n,r2=a,r,t1=0,t2=1,t;
        while(true) {
            q=r1/r2;
            r=r1%r2;
            t=t1-(q*t2);
            r1=r2;
            r2=r;
            t1=t2;
            t2=t;
            if(r2==0) {
                    break;
            }
        }
        if(r1!=1) {
                return -1;
        }
        t1%=n;
        if(t1<0) {
                t1+=n;
        }
        return t1;
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
  
  public static void solve(int indx,int lft,int rgt,int rem,ArrayList<Integer> tmp) {
      if(rem==0) {
          if(lft<=rgt) {
              return;
          }
          for(int i=0;i<tmp.size();i++) {
              System.out.print(tmp.get(i)+" ");
          }
          System.out.println();
          count++;
          return;
      }
      if(indx==n) {
          return;
      }
      solve(indx+1,lft,rgt,rem,tmp);
      tmp.add(indx+1);
      solve(indx+1,Math.max(lft,l[indx]),Math.min(rgt,r[indx]),rem-1,tmp);
      tmp.remove(tmp.size()-1);
  }
}
