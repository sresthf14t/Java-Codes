/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_673_Div_2_Virtual;

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
        long seg_tree[];
        long seg_indx[];
        public seg_tree(int n,long arr[]) {
            seg_tree=new long[4*n];
            seg_indx=new long[4*n];
            create_seg_tree(arr,0,0,n-1);
        }

        //0 index-Left child-(2*i+1) Right Child-(2*i+2)

        public void create_seg_tree(long arr[],int vertex,int l,int r) {
            if(l==r) {
                seg_tree[vertex]=arr[r];
                seg_indx[vertex]=r;
                return;
            }
            int mid=(l+r)/2;
            //Left Child
            create_seg_tree(arr,(2*vertex)+1,l,mid);
            //Right Child
            create_seg_tree(arr,(2*vertex)+2,mid+1,r);
            //Filling this node
            if(seg_tree[(2*vertex)+1]<seg_tree[(2*vertex)+2]) {
                seg_tree[vertex]=seg_tree[(2*vertex)+1];
                seg_indx[vertex]=seg_indx[(2*vertex)+1];
            }
            else {
                seg_tree[vertex]=seg_tree[(2*vertex)+2];
                seg_indx[vertex]=seg_indx[(2*vertex)+2];
            }
        }

        public long[] min(int vertex,int l,int r,int ql,int qr) {  //ql->query left , qr-> query right l->curr Segmrnt left    r->curr segment right
            if(ql>qr) {
                return new long[]{Long.MAX_VALUE,Long.MAX_VALUE};
            }

            if(ql==l && qr==r) {
                return new long[]{seg_tree[vertex],seg_indx[vertex]};
            }
            int mid=(l+r)/2;

            long tmp1[]=min((2*vertex)+1,l,mid,ql,Math.min(qr, mid));
            long tmp2[]=min((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr);
            
            if(tmp1[0]<tmp2[0]) {
                return tmp1;
            }
            else {
                return tmp2;
            }
        }

        public void update(int vertex,int l,int r,int pos,long value) {   //pos->Position of the update   value->updates value
            if(l==r) {
                seg_tree[vertex]=value;
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
            
            if(seg_tree[(2*vertex)+1]<seg_tree[(2*vertex)+2]) {
                seg_tree[vertex]=seg_tree[(2*vertex)+1];
                seg_indx[vertex]=seg_indx[(2*vertex)+1];
            }
            else {
                seg_tree[vertex]=seg_tree[(2*vertex)+2];
                seg_indx[vertex]=seg_indx[(2*vertex)+2];
            }
        }
    }

    
    
    
    
    
    
    
    
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        int test=input.scanInt();
        for(int tt=1;tt<=test;tt++) {
            int n=input.scanInt();
            long arr[]=new long[n];
            long sum=0;
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt();
                sum+=arr[i];
            }
            if(sum%n!=0) {
                ans.append(-1+"\n");
                continue;
            }
            if(n==1) {
                
            }
            ans.append(solve(n,arr,sum));
        }
        System.out.println(ans);
    }
    public static StringBuilder solve(int n,long arr[],long sum) {
        StringBuilder ans=new StringBuilder("");
        int cnt=0;
        long ele=sum/n;
        for(int i=1;i<n;i++) {
            if(arr[i]/(i+1)==0) {
                continue;
            }
            ans.append((i+1)+" "+(0+1)+" "+(arr[i]/(i+1))+"\n");
            long tmp=arr[i]%(i+1);
            arr[0]+=arr[i]-tmp;
            arr[i]=tmp;
            cnt++;
        }
        long diff[]=new long[n];
        diff[0]=Long.MAX_VALUE;
        long idx[]=new long[n];
        for(int i=1;i<n;i++) {
            idx[i]=i;
            if(arr[i]==0) {
                diff[i]=Long.MAX_VALUE;
                continue;
            }
            diff[i]=(i+1)-arr[i];
        }
        
        sort(diff,idx,n);
        for(int i=0;i<n;i++) {
            if(diff[i]==Long.MAX_VALUE) {
                continue;
            }
            if(arr[(int)idx[i]]<=ele) {
                continue;
            }
//            System.out.println(i+" "+arr[0]+" "+diff[i]);
            if(arr[0]>=diff[i]) {
                ans.append((0+1)+" "+(idx[i]+1)+" "+(diff[i])+"\n");
                arr[(int)idx[i]]+=diff[i];
                arr[0]-=diff[i];
                ans.append((idx[i]+1)+" "+(0+1)+" "+(arr[(int)idx[i]]/(idx[i]+1))+"\n");
                arr[0]+=arr[(int)idx[i]];
                arr[(int)idx[i]]=arr[(int)idx[i]]%(idx[i]+1);
                diff[i]=0;
                cnt+=2;
            }
            else  {
                boolean done=false;
                for(int j=i-1;j>=0;j--) {
                    if(diff[j]==Long.MAX_VALUE) {
                        continue;
                    }
                    if(diff[j]!=0 && arr[0]>=diff[j]) {
                        ans.append((0+1)+" "+(idx[j]+1)+" "+(diff[j])+"\n");
                        arr[(int)idx[j]]+=diff[j];
                        arr[0]-=diff[j];
                        ans.append((idx[j]+1)+" "+(0+1)+" "+(arr[(int)idx[j]]/(idx[j]+1))+"\n");
                        arr[0]+=arr[(int)idx[j]];
                        arr[(int)idx[j]]=arr[(int)idx[j]]%(idx[j]+1);
                        diff[j]=0;
                        cnt+=2;
                        done=true;
                        break;
                    }
                }
                if(done) {
                    i--;
                    continue;
                }
            }
        }
        sort(idx,diff,n);
        for(int i=1;i<n;i++) {
            if(arr[i]>ele) {
                return new StringBuilder("-1\n");
            }
            if(ele-arr[i]==0) {
                continue;
            }
            if(arr[0]<ele-arr[i]) {
                break;
            }
            ans.append((0+1)+" "+(i+1)+" "+(ele-arr[i])+"\n");
            long tmp=ele-arr[i];
            arr[0]-=tmp;
            arr[i]+=tmp;
            cnt++;
        }
//        for(int i=0;i<n;i++) {
//            System.out.println(i+" "+arr[i]);
//        }
        boolean is_pos=true;
        for(int i=0;i<n;i++) {
            if(arr[i]!=arr[0]) {
                is_pos=false;
                break;
            }
        }
        if(!is_pos || cnt>3*n) {
            return new StringBuilder("-1\n");
        }
        StringBuilder tmp=new StringBuilder(""+cnt+"\n");
        tmp.append(ans);
        return tmp;
    }
    
     static void buildMaxHeap(long arr[],long brr[], int n) 
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
  
  static void sort(long arr[],long brr[], int n) 
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
  
  public static void swap(long[] a, int i, int j) { 
    long temp = a[i]; 
    a[i]=a[j]; 
    a[j] = temp; 
  } 
}
