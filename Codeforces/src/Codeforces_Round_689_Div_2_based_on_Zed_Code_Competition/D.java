/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_689_Div_2_based_on_Zed_Code_Competition;

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
    
    
    
    
    static class max_st {
        int seg_tree[];
        public max_st(int n,int arr[]) {
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
                return 0;
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

    
    
    
    
    
    static class min_st {
        int seg_tree[];
        public min_st(int n,int arr[]) {
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
    static long prefix[];
    static HashSet<Long> hashset;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        int test=input.scanInt();
        for(int tt=1;tt<=test;tt++) {
            n=input.scanInt();
            int q=input.scanInt();
            arr=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt();
            }
            sort(arr,0,n-1);
            prefix=new long[n];
            prefix[0]=arr[0];
            for(int i=1;i<n;i++) {
                prefix[i]=arr[i]+prefix[i-1];
            }
            hashset=new HashSet<>();
            solve(0,n-1);
            for(int i=0;i<q;i++) {
                long val=input.scanInt();
                if(hashset.contains(val)) {
                    ans.append("YES\n");
                }
                else {
                    ans.append("NO\n");
                }
            }
        }
        System.out.println(ans);
    }
    public static void solve(int l,int r) {
        hashset.add(get(l,r));
        if(l==r) {
            return;
        }
        int mid=(arr[l]+arr[r])/2;
        int indx=jlt(arr,mid,l,r);
        if(indx==-1) {
            return;
        }
        if(indx==r) {
            return;
        }
        solve(l,indx);
        solve(indx+1,r);
    }
    
    static int jlt(int[] arr, int target,int start,int end)  
    {  
    
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
    public static long get(int l,int r) {
        return prefix[r]-(l==0?0:prefix[l-1]);
    }
}
