/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package November_Challenge_2020_Division_2;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class UNSQUERS {
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
        int seg_tree[],lazy[];
        public seg_tree(int n,int arr[]) {
            seg_tree=new int[4*n];
            lazy=new int[4*n];
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

            seg_tree[vertex]=Math.max(seg_tree[(2*vertex)+1],seg_tree[(2*vertex)+2]);
        }

        public void push(int vertex) {
            seg_tree[(2*vertex)+1]+=lazy[vertex];
            lazy[(2*vertex)+1]+=lazy[vertex];

            seg_tree[(2*vertex)+2]+=lazy[vertex];
            lazy[(2*vertex)+2]+=lazy[vertex];

            lazy[vertex]=0;
        }

        public void update(int vertex,int l,int r,int ql,int qr,int add) {

            if(ql>qr) {
                return;
            }
            if(l==ql && r==qr) {
                seg_tree[vertex]+=add;
                lazy[vertex]+=add;
                return;
            }
            push(vertex);

            int mid=(l+r)/2;
            //Left Child
            update((2*vertex)+1,l,mid,ql,Math.min(mid,qr),add);
            //Right Child
            update((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr,add);

            seg_tree[vertex]=Math.max(seg_tree[(2*vertex)+1],seg_tree[(2*vertex)+2]);
        }

        public int find_max(int vertex,int l,int r,int ql,int qr) {
    //        System.out.println(vertex+" "+l+" "+r+" "+ql+" "+qr);
            if(ql>qr) {
                return Integer.MIN_VALUE;
            }
            if(ql<=l && r<=qr) {
                return seg_tree[vertex];
            }
            push(vertex);
            int mid=(l+r)/2;
            return Math.max(find_max((2*vertex)+1,l,mid,ql,Math.min(qr,mid)),find_max((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr));
        }

    }

    
    
    
    
    
    static Scan input;
    static int n,q,s,arr[],next[],back[],dp[][];
    public static void main(String args[]) throws IOException {
        input=new Scan();
        StringBuilder ans=new StringBuilder("");
        n=input.scanInt();
        q=input.scanInt();
        s=input.scanInt();
        arr=new int[n];
        back=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
//            arr[i]=(int)(Math.random()*1000);
//            System.out.print(arr[i]+" ");
        }
        System.out.println();
        next(arr,n);
        back(arr,n);
//        for(int i=0;i<n;i++) {
//            System.out.print(back[i]+" ");
//        }
//        System.out.println();
        if(s==1) {
            ans.append(solve_online());
        }
        else {
            ans.append(solve_offline());
        }
        System.out.println(ans);
    }
    public static StringBuilder solve_online() throws IOException {
        StringBuilder ans=new StringBuilder("");
        int prev=0;
        for(int qq=0;qq<q;qq++) {
            int l=input.scanInt();
            int r=input.scanInt();
            l=(l+s*prev-1)%(n);
            r=(r+s*prev-1)%(n);
            if(l>r) {
                int tmp=l;
                l=r;
                r=tmp;
            }
            init();
            int tmp=on_solve(l,false,r);
            prev=tmp;
            ans.append(tmp+"\n");
        }
        return ans;
    }
    public static int on_solve(int indx,boolean strt,int end) {
        if(indx==-1 || indx>end) {
            return 0;
        }
        if(dp[indx][strt?1:0]!=-1) {
            return dp[indx][strt?1:0];
        }
        int ans=0;
        if(!strt) {
            ans=Math.max(ans,on_solve(indx+1,strt,end));
            ans=Math.max(ans,1+on_solve(next[indx],true,end));
        }
        else {
            ans=Math.max(ans,1+on_solve(next[indx],strt,end));
        }
        dp[indx][strt?1:0]=ans;
        return ans;
    }
    public static void init() {
        dp=new int[n][2];
        for(int i=0;i<dp.length;i++) {
            for(int j=0;j<dp[0].length;j++) {
                dp[i][j]=-1;
            }
        }
    }
    static void next(int arr[], int n) { 

        Stack<Integer> S = new Stack<>(); 
  
        next=new int[n];

        for (int i = n-1; i >= 0; i--) { 

            while (!S.empty() && arr[S.peek()] <= arr[i]) { 
                S.pop(); 
            } 
            if (S.empty()) { 
                next[i]=-1;
            } 
            else 
            { 
                next[i]=S.peek(); 
            } 
            S.push(i); 
        } 
    }
    
    static void back(int arr[], int n) { 

        Stack<Integer> S = new Stack<>(); 
  
        back=new int[n];

        for (int i = 0; i < n; i++) { 

            while (!S.empty() && arr[S.peek()] < arr[i]) { 
                S.pop(); 
            } 
            if (S.empty()) { 
                back[i]=-1;
            } 
            else 
            { 
                back[i]=S.peek(); 
            } 
            S.push(i); 
        } 
    }
    
    public static StringBuilder solve_offline() throws IOException {
        int prev=0;
        int lft[]=new int[q];
        int rgt[]=new int[q];
        int fin[]=new int[q];
        int indx[]=new int[q];
        int ll[]=new int[q];
        int rr[]=new int[q];
        for(int i=0;i<q;i++) {
            lft[i]=(input.scanInt()+s*prev-1)%(n);
            rgt[i]=(input.scanInt()+s*prev-1)%(n);
            
//            lft[i]=(int)(Math.random()*n);
//            rgt[i]=(int)(Math.random()*n);
            
            
            
            if(lft[i]>rgt[i]) {
                int tmp=lft[i];
                lft[i]=rgt[i];
                rgt[i]=tmp;
            }
            
            ll[i]=lft[i];
            rr[i]=rgt[i];
            indx[i]=i;
        }
        //sort
        sort(rgt,lft,indx,q);
        seg_tree st=new seg_tree(n,new int[n]);
        int r=-1;
        for(int i=0;i<q;i++) {
//            System.out.println(indx[i]+" "+lft[i]+" "+rgt[i]+" "+r);
            while(r<rgt[i]) {
                r++;
                int tmp=back[r];
                if(tmp==-1) {
                    tmp=0;
                }
                else {
                    tmp++;
                }
//                System.out.println(r+" "+tmp);
                st.update(0, 0, n-1, tmp, r, 1);
            }
//            for(int j=0;j<n;j++) {
//                System.out.print(st.find_max(0, 0, n-1, j, j)+" ");
//            }
//            System.out.println();
            fin[i]=st.find_max(0, 0, n-1, lft[i], rgt[i]);
//            if(fin[i]!=cnt(lft[i],rgt[i])) {
//                System.out.println(i+" "+lft[i]+" "+rgt[i]+" "+fin[i]+" "+cnt(lft[i],rgt[i]));
//            }
        }
        sort(indx,fin,new int[q],q);
        StringBuilder ans=new StringBuilder("");
        for(int i=0;i<q;i++) {
            ans.append(fin[i]+"\n");
//            if(fin[i]!=cnt(ll[i],rr[i])) {
//                System.out.println(i+" "+ll[i]+" "+rr[i]+" "+fin[i]+" "+cnt(ll[i],rr[i]));
//            }
        }
        return ans;
    }
    
    public static int cnt(int l,int r) {
        int ans=0;
        for(int i=l;i<=r;i++) {
            int indx=i,cnt=0,curr=-1;
            for(int j=i;j<=r;j++) {
                if(arr[j]>curr) {
                    cnt++;
                    curr=arr[j];
                }
            }
            ans=Math.max(ans,cnt);
        }
        return ans;
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
  
  static void sort(int arr[],int brr[],int crr[], int n) 
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
