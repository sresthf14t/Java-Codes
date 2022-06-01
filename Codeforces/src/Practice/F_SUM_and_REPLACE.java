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
public class F_SUM_and_REPLACE {
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
        public seg_tree(int n,int arr[]) {
            seg_tree=new long[4*n];
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
            seg_tree[vertex]=seg_tree[(2*vertex)+1]+seg_tree[(2*vertex)+2];
        }

        public long sum(int vertex,int l,int r,int ql,int qr) {  //ql->query left , qr-> query right l->curr Segmrnt left    r->curr segment right
            if(ql>qr) {
                return 0;
            }

            if(ql==l && qr==r) {
                return seg_tree[vertex];
            }
            int mid=(l+r)/2;

            int total=0;

            //Left Child
            total+=sum((2*vertex)+1,l,mid,ql,Math.min(qr, mid));

            //Right Child
            total+=sum((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr);

            return total;
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
            seg_tree[vertex]=seg_tree[(2*vertex)+1]+seg_tree[(2*vertex)+2];
        }
    }

    
    
    static int primes[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        sieve();
        int n=input.scanInt();
        int q=input.scanInt();
        int arr[]=new int[n];
        TreeSet<Integer> ts=new TreeSet<>();
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
            if(arr[i]>2) {
                ts.add(i);
            }
        }
        int fx[]=new int[1000001];
        int max=-1,max_indx=-1;
        for(int i=1;i<fx.length;i++) {
            int tmp=i,cnt=1;
            for(int j=0;j<primes.length;j++) {
                int tc=1;
                while(tmp%primes[j]==0) {
                    tmp/=primes[j];
                    tc++;
                }
                cnt*=tc;
            }
            if(tmp!=1) {
                cnt*=2;
            }
            fx[i]=cnt;
//            if(fx[i]>max) {
//                max=fx[i];
//                max_indx=i;
//            }
        }
//        System.out.println(max_indx);
//        for(int i=0;i<fx.length;i++) {
//            System.out.println(i+" "+fx[i]);
//        }
        seg_tree st=new seg_tree(n,arr);
        
        Stack<Integer> stck=new Stack<>();
        for(int qq=1;qq<=q;qq++) {
            int type=input.scanInt();
            int l=input.scanInt()-1;
            int r=input.scanInt()-1;
            if(type==1) {
                
                while(ts.higher(l-1)!=null && ts.higher(l-1)<=r) {
                    int indx=ts.higher(l-1);
                    ts.remove(indx);
                    stck.add(indx);
                    arr[indx]=fx[arr[indx]];
                    st.update(0, 0, n-1, indx, arr[indx]);
                }
                while(!stck.isEmpty()) {
                    int indx=stck.pop();
                    if(arr[indx]>2) {
                        ts.add(indx);
                    }
                }
            }
            else {
                ans.append(st.sum(0, 0, n-1, l, r)+"\n");
            }
        }
        System.out.println(ans);
    }
    public static void sieve() {
        int indx=0;
        primes=new int[168];
        boolean sieve[]=new boolean[1001];
        for(int i=2;i<sieve.length;i++) {
            if(sieve[i]) {
                continue;
            }
            primes[indx]=i;
            indx++;
            for(int j=2;i*j<sieve.length;j++) {
                sieve[i*j]=true;
            }
        }
//        System.out.println(indx);
    }
}
