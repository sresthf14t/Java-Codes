/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_678_Div_2;

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
    
    
    
    
    static class seg_tree {
        int seg_tree[];
        public seg_tree(int n,int arr[]) {
            seg_tree=new int[4*n];
            create_seg_tree(arr,0,0,n-1);
        }

        //0 index-Left child-(2*i+1) Right Child-(2*i+2)

        public void create_seg_tree(int arr[],int vertex,int l,int r) {
            if(l==r) {
                seg_tree[vertex]=arr[r];
                return;
            }
            int mid=(l+r)/2;
            //Left Child
            create_seg_tree(arr,(2*vertex)+1,l,mid);
            //Right Child
            create_seg_tree(arr,(2*vertex)+2,mid+1,r);
            //Filling this node
            seg_tree[vertex]=Math.min(seg_tree[(2*vertex)+1],seg_tree[(2*vertex)+2]);
        }

        public int min(int vertex,int l,int r,int ql,int qr) {  //ql->query left , qr-> query right l->curr Segmrnt left    r->curr segment right
            if(ql>qr) {
                return Integer.MAX_VALUE;
            }

            if(ql==l && qr==r) {
                return seg_tree[vertex];
            }
            int mid=(l+r)/2;

            int min=Integer.MAX_VALUE;

            //Left Child
            min=Math.min(min,min((2*vertex)+1,l,mid,ql,Math.min(qr, mid)));

            //Right Child
            min=Math.min(min,min((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr));

            return min;
        }

        public void update(int vertex,int l,int r,int pos,int value) {   //pos->Position of the update   value->updates value
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
            seg_tree[vertex]=Math.min(seg_tree[(2*vertex)+1],seg_tree[(2*vertex)+2]);
        }
    }
    
    
    
    
    
    
    
    static int n,arr[];
    static ArrayList<Integer> arrli[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int n=input.scanInt();
//        int n=100000;
        int arr[]=new int[n];
        arrli=new ArrayList[n+2];
        boolean no=false;
        for(int i=0;i<n;i++) {
            arrli[i]=new ArrayList<>();
            arr[i]=input.scanInt();
//            arr[i]=i+1;
            if(arr[i]!=1) {
                no=true;
            }
        }
        if(!no) {
            System.out.println(1);
            return;
        }
        arrli[n]=new ArrayList<>();
        arrli[n+1]=new ArrayList<>();
        for(int i=0;i<n;i++) {
            arrli[arr[i]].add(i);
        }
        int queryl[]=new int[5*n];
        int queryr[]=new int[5*n];
        int queryval[]=new int[5*n];
        boolean fnd[]=new boolean[5*n];
        int cnt=0;
        int ans=2;
        for(int i=2;i<=n+1;i++) {
            for(int j=0;j<arrli[i].size();j++) {
                if(j==0 && arrli[i].get(j)!=0) {
                    queryl[cnt]=0;
                    queryr[cnt]=arrli[i].get(j)-1;
                    queryval[cnt]=i;
                    cnt++;
                }
                if(j==arrli[i].size()-1 && arrli[i].get(j)!=n-1) {
                    queryl[cnt]=arrli[i].get(j)+1;
                    queryr[cnt]=n-1;
                    queryval[cnt]=i;
                    cnt++;
                }
                if(j!=arrli[i].size()-1) {
                    queryl[cnt]=arrli[i].get(j)+1;
                    queryr[cnt]=arrli[i].get(j+1)-1;
                    queryval[cnt]=i;
                    cnt++;
                }
            }
            if(arrli[i].size()==0) {
                queryl[cnt]=0;
                queryr[cnt]=n-1;
                queryval[cnt]=i;
                cnt++;
            }
        }
        
        //Sort
        sort(queryr,queryl,queryval,cnt-1);
        
        
        TreeSet<Integer> ts=new TreeSet<>();
        for(int i=1;i<=n+2;i++) {
            ts.add(i);
        }
        
        int freq[]=new int[n];
        Arrays.fill(freq, Integer.MAX_VALUE);
        seg_tree st=new seg_tree(n,freq);
        int indx=-1;
        int prev[]=new int[n+2];
        Arrays.fill(prev, -1);
        for(int i=0;i<cnt;i++) {
            while(indx<queryr[i]) {
                indx++;
                if(prev[arr[indx]]!=-1) {
                    st.update(0, 0, n-1, prev[arr[indx]], Integer.MAX_VALUE);
                }
                st.update(0, 0, n-1, indx, arr[indx]);
                prev[arr[indx]]=indx;
                if(ts.contains(arr[indx])) {
                    ts.remove(arr[indx]);
                }
            }
            int mex=Math.min(ts.first(),queryl[i]==0?Integer.MAX_VALUE:st.min(0, 0, n-1, 0, queryl[i]-1));
            if(mex==queryval[i]) {
                fnd[queryval[i]]=true;
            }
//            System.out.println(queryl[i]+" "+queryr[i]+" "+queryval[i]+" "+mex+" "+(queryl[i]==0?Integer.MAX_VALUE:st.min(0, 0, n-1, 0, queryl[i]-1)));
        }
//        for(int i=0;i<n;i++) {
//            System.out.print(st.min(0, 0, n-1, i, i)+" ");
//        }
//        System.out.println();
        ans=Integer.MAX_VALUE;
        for(int i=2;i<fnd.length;i++) {
            if(!fnd[i]) {
                ans=i;
                break;
            }
        }
        System.out.println(ans);
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
