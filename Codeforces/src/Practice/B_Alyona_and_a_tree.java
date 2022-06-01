/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

/**
 *
 * @author SRESTH
 */
import java.util.*;
import java.io.*;
public class B_Alyona_and_a_tree {
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

    
    
    
    
    static ArrayList<Integer> adj_lst[],wei[];
    static int n,arr[],fin[];
    static seg_tree st;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        
        n=input.scanInt();
        arr=new int[n];
        adj_lst=new ArrayList[n];
        wei=new ArrayList[n];
        st=new seg_tree(n,new int[n]);
        fin=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
            adj_lst[i]=new ArrayList<>();
            wei[i]=new ArrayList<>();
        }
        for(int i=1;i<n;i++) {
            int u=input.scanInt()-1;
            int v=i;
            int val=input.scanInt();
//            System.out.println("uv="+u+" "+v);
            adj_lst[u].add(v);
            adj_lst[v].add(u);
            wei[u].add(val);
            wei[v].add(val);
        }
        dfs(0,-1,new ArrayList<Long>(),0);
        for(int i=0;i<n;i++) {
            ans.append(fin[i]+" ");
        }
        ans.append("\n");
        System.out.print(ans);
    }
    
    public static void dfs(int root,int par,ArrayList<Long> arrli,int dep) {  
        int up=find_up(arr[root],arrli);
//        System.out.println("DFS="+(root+1)+" "+up+" "+(dep-1)+" "+(dep-1-up+1));
        if(up!=-1) {
            st.update(0, 0, n-1, dep-1-up+1, dep-1, 1);
        }
        
        for(int i=0;i<adj_lst[root].size();i++) {
//            System.out.println("adj="+root+" "+adj_lst[root].get(i));
            if(adj_lst[root].get(i)==par) {
                continue;
            }
            arrli.add((long)wei[root].get(i));
            if(arrli.size()>1) {
                arrli.set(arrli.size()-1, arrli.get(arrli.size()-1-1)+arrli.get(arrli.size()-1));
            }
            dfs(adj_lst[root].get(i),root,arrli,dep+1);
            arrli.remove(arrli.size()-1);
        }
        
        fin[root]=st.get(0, 0, n-1, dep);
        st.update(0, 0, n-1, dep, dep, -1*fin[root]);
    }
    
    public static int find_up(int val,ArrayList<Long> arrli) {
        int l=0,r=arrli.size()-1;
        int end=arrli.size()-1;
        int ans=-1;
        while(l<=r) {
            int mid=(l+r)/2;
            long num=get(arrli,mid,end);
            if(num<=val) {
                ans=mid;
                r=mid-1;
            }
            else {
                l=mid+1;
            }
        }
        if(ans!=-1) {
            ans=arrli.size()-1-ans+1;
        }
        return ans;
    }
    
    public static long get(ArrayList<Long> arrli,int l,int r) {
        return arrli.get(r)-(l==0?0:arrli.get(l-1));
    }
}
