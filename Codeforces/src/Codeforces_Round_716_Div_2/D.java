/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_716_Div_2;

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
        seg_tree(int n,int arr[]) {
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
            seg_tree[vertex]=Math.max(seg_tree[(2*vertex)+1],seg_tree[(2*vertex)+2]);
        }

        public int max(int vertex,int l,int r,int ql,int qr) {  //ql->query left , qr-> query right l->curr Segmrnt left    r->curr segment right
            if(ql>qr) {
                return Integer.MIN_VALUE;
            }

            if(ql==l && qr==r) {
                return seg_tree[vertex];
            }
            int mid=(l+r)/2;

            int max=Integer.MIN_VALUE;

            //Left Child
            max=Math.max(max,max((2*vertex)+1,l,mid,ql,Math.min(qr, mid)));

            //Right Child
            max=Math.max(max,max((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr));

            return max;
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
            seg_tree[vertex]=Math.max(seg_tree[(2*vertex)+1],seg_tree[(2*vertex)+2]);
        }
    }

    
    
    
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        
        int n=input.scanInt();
        int q=input.scanInt();
        
        int arr[]=new int[n];
        int l[]=new int[q];
        int r[]=new int[q];
        int indx[]=new int[q];
        int fin[]=new int[q];
        
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt()-1;
        }
        
        for(int i=0;i<q;i++) {
            l[i]=input.scanInt()-1;
            r[i]=input.scanInt()-1;
            indx[i]=i;
        }
        
        //sort
        
        sort(l,r,indx,0,q-1);
        int len=(int)Math.ceil(Math.sqrt(n));
        len=Math.max(len,1);
        int strt=0,end=len-1;
        for(int i=0;i<q;i++) {
            int j=i;
            while(j<q && l[j]<=end) {
                j++;
            }
            j--;
            if(j>=i) {
                sort(r,l,indx,i,j);
                i=j;
            }
            
            strt+=len;
            end+=len;
        }
        
        
        seg_tree st=new seg_tree(n,new int[n]);
        
        
        int cl=l[0],cr=r[0];
        for(int i=l[0];i<=r[0];i++) {
            st.update(0, 0, n-1, arr[i], st.max(0, 0, n-1, arr[i], arr[i])+1);
        }
        
        int max=st.max(0, 0, n-1, 0, n-1);
        len=r[0]-l[0]+1;
        int sm=len-max;
        if(max<=(len/2)+(len%2)) {
            fin[0]=1;
        }
        else {
//            System.out.println(l[0]+" "+r[0]+" "+len+" "+max);
            fin[0]=(len-max)+Math.max(0,(len-3*(len-max)));
        }
        
        
        for(int i=1;i<q;i++) {
            if(l[i]>cl) {
                for(int j=cl+1;j<=l[i];j++) {
                    st.update(0, 0, n-1, arr[j], st.max(0, 0, n-1, arr[j], arr[j])+1);
                }
            }
            else {
                for(int j=cl;j>=l[i];j--) {
                    st.update(0, 0, n-1, arr[j], st.max(0, 0, n-1, arr[j], arr[j])-1);
                }
            }
            
            if(r[i]>cr) {
                for(int j=cr+1;j<=r[i];j++) {
                    st.update(0, 0, n-1, arr[j], st.max(0, 0, n-1, arr[j], arr[j])+1);
                }
            }
            else {
                for(int j=cr;j>=r[i];j--) {
                    st.update(0, 0, q, arr[j], st.max(0, 0, q, arr[j], arr[j])-1);
                }
            }
            
            cl=l[i];
            cr=r[i];
            
            max=st.max(0, 0, n-1, 0, n-1);
            len=r[i]-l[i]+1;
            sm=len-max;
//            System.out.println(l[i]+" "+r[i]+" "+max);
            if(max<=(len/2)+(len%2)) {
                fin[i]=1;
                continue;
            }
            
            fin[i]=(len-max)+Math.max(0,(len-3*(len-max)));
            
        }
        
        sort(indx,fin,l,0,q-1);
        
        for(int i=0;i<q;i++) {
            ans.append(fin[i]+"\n");
        }
        
        System.out.println(ans);
    }
    
    
    
    static int partition(int arr[],int brr[],int crr[], int low, int high) 
    { 
        int pivot = arr[high]; 
  
        // index of smaller element 
        int i = (low - 1); 
        for (int j = low; j <= high - 1; j++) { 
            // If current element is smaller than or 
            // equal to pivot 
            if (arr[j] <= pivot) { 
                i++; 
  
                // swap arr[i] and arr[j] 
                int temp = arr[i]; 
                arr[i] = arr[j]; 
                arr[j] = temp; 
                
                temp = brr[i]; 
                brr[i] = brr[j]; 
                brr[j] = temp; 
                
                temp = crr[i]; 
                crr[i] = crr[j]; 
                crr[j] = temp; 
            } 
        } 
  
        // swap arr[i+1] and arr[high] (or pivot) 
        int temp = arr[i + 1]; 
        arr[i + 1] = arr[high]; 
        arr[high] = temp; 
        
        temp = brr[i + 1]; 
        brr[i + 1] = brr[high]; 
        brr[high] = temp;
        
        temp = crr[i + 1]; 
        crr[i + 1] = crr[high]; 
        crr[high] = temp;
  
        return i + 1; 
    } 
  
    /* A[] --> Array to be sorted,  
   l  --> Starting index,  
   h  --> Ending index */
    static void sort(int arr[],int brr[],int crr[], int l, int h) 
    { 
        if(l==h) {
            return;
        }
        // Create an auxiliary stack 
        int[] stack = new int[h - l + 1]; 
  
        // initialize top of stack 
        int top = -1; 
  
        // push initial values of l and h to stack 
        stack[++top] = l; 
        stack[++top] = h; 
  
        // Keep popping from stack while is not empty 
        while (top >= 0) { 
            // Pop h and l 
            h = stack[top--]; 
            l = stack[top--]; 
  
            // Set pivot element at its correct position 
            // in sorted array 
            int p = partition(arr,brr,crr, l, h); 
  
            // If there are elements on left side of pivot, 
            // then push left side to stack 
            if (p - 1 > l) { 
                stack[++top] = l; 
                stack[++top] = p - 1; 
            } 
  
            // If there are elements on right side of pivot, 
            // then push right side to stack 
            if (p + 1 < h) { 
                stack[++top] = p + 1; 
                stack[++top] = h; 
            } 
        } 
    }
}
