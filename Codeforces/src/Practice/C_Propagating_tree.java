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
public class C_Propagating_tree {
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
    
    
    
    class seg_tree {
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

    
    
    
    
    ArrayList<Integer> adj_lst[],et_even,et_odd;
    int depth[];
    public static void main(String args[]) throws IOException {
        C_Propagating_tree obj=new C_Propagating_tree();
        obj.solve();
    }
    
    public void solve() throws IOException {
        
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        
        int n=input.scanInt();
        int q=input.scanInt();
        int arr[]=new int[n];
        depth=new int[n];
        adj_lst=new ArrayList[n];
        et_even=new ArrayList<>();
        et_odd=new ArrayList<>();
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<>();
            arr[i]=input.scanInt();
        }
        for(int i=0;i<n-1;i++) {
            int u=input.scanInt()-1;
            int v=input.scanInt()-1;
            adj_lst[u].add(v);
            adj_lst[v].add(u);
        }
        euler_tour(0,-1,0);
        int first[]=new int[n];
        int last[]=new int[n];
        Arrays.fill(first, -1);
        for(int i=0;i<et_even.size();i++) {
            if(first[et_even.get(i)]==-1) {
                first[et_even.get(i)]=i;
            }
            last[et_even.get(i)]=i;
        }
//        System.out.println(et);
        seg_tree st_even=new seg_tree(et_even.size(),new int[et_even.size()]);
        seg_tree st_odd=new seg_tree(et_even.size(),new int[et_even.size()]);
        for(int qq=1;qq<=q;qq++) {
            int type=input.scanInt();
            if(type==1) {
                int indx=input.scanInt()-1;
                int val=input.scanInt();
                if(depth[indx]%2==0) {
                    st_even.update(0, 0, et_even.size()-1, first[indx], first[indx], val);
                    st_odd.update(0, 0, et_even.size()-1, first[indx]+1, last[indx]-1, -1*val);
                    st_even.update(0, 0, et_even.size()-1, first[indx]+1, last[indx]-1, val);
                }
                else {
                    st_odd.update(0, 0, et_even.size()-1, first[indx], first[indx], val);
                    st_even.update(0, 0, et_even.size()-1, first[indx]+1, last[indx]-1, -1*val);
                    st_odd.update(0, 0, et_even.size()-1, first[indx]+1, last[indx]-1, val);
                }
                
            }
            if(type==2) {
                int indx=input.scanInt()-1;
                if(depth[indx]%2==0) {
                    ans.append((st_even.get(0, 0, et_even.size()-1, first[indx])+arr[indx])+"\n");
                }
                else {
                    ans.append((st_odd.get(0, 0, et_even.size()-1, first[indx])+arr[indx])+"\n");
                }
            }
        }
        System.out.print(ans);
        
    }
    
    public void euler_tour(int root,int par,int dep) {
        depth[root]=dep;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==par) {
                continue;
            }
            et_even.add(root);
            et_odd.add(root);
            euler_tour(adj_lst[root].get(i),root,dep+1);
        }
        et_even.add(root);
        et_odd.add(root);
    }
}
