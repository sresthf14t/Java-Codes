/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_783_Div_2;

/**
 *
 * @author SRESTH
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
        static long seg_tree[],lazy[],seg_indx[],lazy_indx[];
        public seg_tree(int n,long arr[]) {
            seg_tree=new long[4*n];
            lazy=new long[4*n];
            seg_indx=new long[4*n];
            lazy_indx=new long[4*n];
            built(arr,0,0,n-1);
        }

        public void built(long arr[], int vertex, int l,int r) {
            if(l==r) {
                seg_tree[vertex]=arr[r];
                seg_indx[vertex]=r;
                return;
            }
            int mid=(l+r)/2;

            //Left Child
            built(arr,(2*vertex)+1, l, mid);

            //Right Child
            built(arr,(2*vertex)+2, mid+1, r);
            
            if(seg_tree[(2*vertex)+2]>0) {
                seg_tree[vertex]=seg_tree[(2*vertex)+2];
                seg_indx[vertex]=seg_indx[(2*vertex)+2];
            }
            else if(seg_tree[(2*vertex)+1]>0){
                seg_tree[vertex]=seg_tree[(2*vertex)+1];
                seg_indx[vertex]=seg_indx[(2*vertex)+1];
            }
            else {
                seg_tree[vertex]=-1;
                seg_indx[vertex]=-1;
            }
        }

        public void push(int vertex) {
            seg_tree[(2*vertex)+1]+=lazy[vertex];
            lazy[(2*vertex)+1]+=lazy[vertex];

            seg_tree[(2*vertex)+2]+=lazy[vertex];
            lazy[(2*vertex)+2]+=lazy[vertex];

            lazy[vertex]=0;
            
            
            seg_indx[(2*vertex)+1]=lazy_indx[vertex];
            lazy_indx[(2*vertex)+1]=lazy_indx[vertex];

            seg_indx[(2*vertex)+2]=lazy_indx[vertex];
            lazy_indx[(2*vertex)+2]=lazy_indx[vertex];

            lazy_indx[vertex]=-1;
        }

        public void update(int vertex,int l,int r,int ql,int qr,long add) {

            if(ql>qr) {
                return;
            }
            if(l==ql && r==qr) {
                seg_tree[vertex]+=add;
                lazy[vertex]+=add;
                
                seg_indx[vertex]=r;
                lazy_indx[vertex]=r;
                
                return;
            }
            push(vertex);

            int mid=(l+r)/2;
            //Left Child
            update((2*vertex)+1,l,mid,ql,Math.min(mid,qr),add);
            //Right Child
            update((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr,add);

            if(seg_tree[(2*vertex)+2]>0) {
                seg_tree[vertex]=seg_tree[(2*vertex)+2];
                seg_indx[vertex]=seg_indx[(2*vertex)+2];
            }
            else if(seg_tree[(2*vertex)+1]>0){
                seg_tree[vertex]=seg_tree[(2*vertex)+1];
                seg_indx[vertex]=seg_indx[(2*vertex)+1];
            }
            else {
                seg_tree[vertex]=-1;
                seg_indx[vertex]=-1;
            }
        }

        public long[] find(int vertex,int l,int r,int ql,int qr) {
    //        System.out.println(vertex+" "+l+" "+r+" "+ql+" "+qr);
            if(ql>qr) {
                return new long[]{Long.MIN_VALUE,-1};
            }
            if(ql<=l && r<=qr) {
                return new long[]{seg_tree[vertex],seg_indx[vertex]};
            }
            push(vertex);
            int mid=(l+r)/2;
            
            long ret_l[]=find((2*vertex)+1,l,mid,ql,Math.min(qr,mid));
            long ret_r[]=find((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr);
            
            if(ret_l[0]>0) {
                return ret_l;
            }
            return ret_r;
        }

    }

    
    static int n;
    static long arr[];
    static long prefix[];
    static seg_tree st;
    static int dp[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        int test=input.scanInt();
        for(int tt=1;tt<=test;tt++) {
            n=input.scanInt();
            arr=new long[n];
            prefix=new long[n];
            dp=new int[n];
            Arrays.fill(dp, Integer.MIN_VALUE);
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt();
//                arr[i]=(int)(Math.random()*1000);
//                if(((int)(Math.random()*10))%2==0) {
//                    arr[i]*=-1;
//                }
            }
//            for(int i=0;i<n;i++) {
//                System.out.print(arr[i]+" ");
//            }
//            System.out.println();
            prefix[0]=arr[0];
            for(int i=1;i<n;i++) {
                prefix[i]=prefix[i-1]+arr[i];
            }
            st=new seg_tree(n,prefix);
            ans.append(solve(0)+"\n");
        }
        System.out.println(ans);
    }
    
    public static int solve(int indx) {
       if(indx==n) {
           return 0;
       }
       
       if(dp[indx]!=Integer.MIN_VALUE) {
           return dp[indx];
       }
       
       int ans=Integer.MIN_VALUE;
       if(arr[indx]<0) {
           ans=Math.max(ans,-1+solve(indx+1));
       }
       else if(arr[indx]==0) {
           ans=Math.max(ans,0+solve(indx+1));
       }
       else {
           ans=Math.max(ans,1+solve(indx+1));
       }
       
       if(indx==0) {
           long tmp[]=st.find(0, 0, n-1, indx, n-1);
           System.out.println(indx+" "+tmp[1]);
           if(tmp[1]==-1) {
               ans=Math.max(ans, n);
           }
           else {
               ans=Math.max(ans,(int)tmp[1]-indx+1+solve((int)tmp[1]+1));
           }
       }
       else {
           st.update(0, 0, n-1, indx, n-1, -prefix[indx-1]);
           long tmp[]=st.find(0, 0, n-1, indx, n-1);
           System.out.println(indx+" "+tmp[1]);
           if(tmp[1]==-1) {
               ans=Math.max(ans, n);
           }
           else {
               ans=Math.max(ans,(int)tmp[1]-indx+1+solve((int)tmp[1]+1));
           }
           st.update(0, 0, n-1, indx, n-1, prefix[indx-1]);
       }
       dp[indx]=ans;
       return ans;
    }
    
    public static long get(int l,int r) {
        return prefix[r]-(l==0?0:prefix[l-1]);
    }
}
