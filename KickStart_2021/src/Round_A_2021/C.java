/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Round_A_2021;

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
    
    
    static class seg_tree {
        int seg_tree[],seg_indx[];
        public seg_tree(int n,int arr[]) {
            seg_tree=new int[4*n];
            seg_indx=new int[4*n];
            create_seg_tree(arr,0,0,n-1);
        }

        //0 index-Left child-(2*i+1) Right Child-(2*i+2)

        public void create_seg_tree(int arr[],int vertex,int l,int r) {
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
            
            if(seg_tree[(2*vertex)+1]>seg_tree[(2*vertex)+2]) {
                seg_tree[vertex]=seg_tree[(2*vertex)+1];
                seg_indx[vertex]=seg_indx[(2*vertex)+1];
            }
            else {
                seg_tree[vertex]=seg_tree[(2*vertex)+2];
                seg_indx[vertex]=seg_indx[(2*vertex)+2];
            }
        }

        public int[] max(int vertex,int l,int r,int ql,int qr) {  //ql->query left , qr-> query right l->curr Segmrnt left    r->curr segment right
            if(ql>qr) {
                return new int[]{-1,Integer.MIN_VALUE};
            }

            if(ql==l && qr==r) {
                return new int[]{seg_indx[vertex],seg_tree[vertex]};
            }
            int mid=(l+r)/2;

            //Left Child
            int max1[]=max((2*vertex)+1,l,mid,ql,Math.min(qr, mid));

            //Right Child
            int max2[]=max((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr);
            
            if(max1[1]>max2[1]) {
                return max1;
            }

            return max2;
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
            if(seg_tree[(2*vertex)+1]>seg_tree[(2*vertex)+2]) {
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
            int m=input.scanInt();
            int arr[][]=new int[n][m];
            for(int i=0;i<n;i++) {
                for(int j=0;j<m;j++) {
                    arr[i][j]=input.scanInt();
                }
            }
            ans.append("Case #"+tt+": "+solve(n,m,arr)+"\n");
        }
        System.out.println(ans);
    }
    public static long solve(int n,int m,int arr[][]) {
        
        boolean vis[][]=new boolean[n][m];
        long ans=0;
        
        int brr[]=new int[n*m];
        int indx[][]=new int[n][m];
        int indxx[]=new int[n*m];
        int indxy[]=new int[n*m];
        int idx=0;
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                brr[idx]=arr[i][j];
                indx[i][j]=idx;
                indxx[idx]=i;
                indxy[idx]=j;
                idx++;
            }
        }
        
        seg_tree st=new seg_tree(brr.length,brr);
        
        for(int tt=0;tt<n*m;tt++) {
            
            int indx1=st.max(0, 0, brr.length-1, 0, brr.length-1)[0];
            st.update(0, 0, brr.length-1, indx1, Integer.MIN_VALUE);
            
//            System.out.println(indx1);
            
            int i=indxx[indx1],j=indxy[indx1];
            if(i==-1 || j==-1) {
                continue;
            }
            if(i!=0) {
                int cnt=Math.abs(arr[i-1][j]-arr[i][j]);
                if(cnt>1) {
                    ans+=cnt-1;
                    arr[i-1][j]+=cnt-1;
                    st.update(0, 0, brr.length-1, indx[i-1][j], arr[i-1][j]);
                }
                
            }
            
            if(i!=n-1) {
                int cnt=Math.abs(arr[i+1][j]-arr[i][j]);
                if(cnt>1) {
                    ans+=cnt-1;
                    arr[i+1][j]+=cnt-1;
                    st.update(0, 0, brr.length-1, indx[i+1][j], arr[i+1][j]);
                }
                
            }
            
            if(j!=0) {
                int cnt=Math.abs(arr[i][j-1]-arr[i][j]);
                if(cnt>1) {
                    ans+=cnt-1;
                    arr[i][j-1]+=cnt-1;
                    st.update(0, 0, brr.length-1, indx[i][j-1], arr[i][j-1]);
                }
                
            }
            
            if(j!=m-1) {
                int cnt=Math.abs(arr[i][j+1]-arr[i][j]);
                if(cnt>1) {
                    ans+=cnt-1;
                    arr[i][j+1]+=cnt-1;
                    st.update(0, 0, brr.length-1, indx[i][j+1], arr[i][j+1]);
                }
                
            }
        }
        
        return ans;
    }
    
    public static int[] max(int n,int m,int arr[][],boolean vis[][]) {
        int indx[]=new int[2];
        indx[0]=indx[1]=-1;
        int max=-1;
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(vis[i][j]) {
                    continue;
                }
                if(arr[i][j]>max) {
                    max=arr[i][j];
                    indx[0]=i;
                    indx[1]=j;
                }
            }
        }
        vis[indx[0]][indx[1]]=true;
        return indx;
    }
}
