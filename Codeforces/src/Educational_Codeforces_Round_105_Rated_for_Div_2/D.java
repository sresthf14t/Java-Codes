/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Educational_Codeforces_Round_105_Rated_for_Div_2;

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
    
    
    static class DSU {
        int parent[],size[];
        DSU(int n){
            parent=new int[n];
            size=new int[n];
            make_set(n);
        }
        public void make_set(int n) {
            for(int i=0;i<n;i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public void union(int a, int b) {
            a = find(a);
            b = find(b);
            if (a != b) {
                if (size[a] < size[b])
                    swap(a, b);
                parent[b] = a;
                size[a] += size[b];
            }
        }
        public int find(int v) {
            if (v == parent[v]) {
                return v;
            }
            parent[v] = find(parent[v]);
            return parent[v];
        }
        public void swap(int i,int j) {
            int tmp=size[i];
            size[i]=size[j];
            size[j]=tmp;
        }
    }

    
    static ArrayList<Integer> adj_lst[];
    static int fin_sal[],parent[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        
        int n=input.scanInt();
        int arr[][]=new int[n][n];
        
        ArrayList<Integer> sal[]=new ArrayList[5001];
        for(int i=0;i<sal.length;i++) {
            sal[i]=new ArrayList<>();
        }
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                arr[i][j]=input.scanInt();
                if(i>j) {
                    sal[arr[i][j]].add(i);
                    sal[arr[i][j]].add(j);
                }
            }
        }
        
        DSU dsu=new DSU(10000);
        adj_lst=new ArrayList[10000];
        parent=new int[10000];
        Arrays.fill(parent, -1);
        fin_sal=new int[10000];
        Arrays.fill(fin_sal, -1);
        for(int i=0;i<n;i++) {
            fin_sal[i]=arr[i][i];
        }
        int indx=n;
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<>();
        }
        for(int i=1;i<sal.length;i++) {
            for(int j=0;j<sal[i].size();j+=2) {
                int u=sal[i].get(j);
                int v=sal[i].get(j+1);
                int fin1=DFS(u,indx-1,n,dsu);
                int fin2=DFS(v,indx-1,n,dsu);
                if(fin1==fin2) {
                    continue;
                }
                else if(fin_sal[fin1]==i) {
                    adj_lst[fin1].add(fin2);
                    adj_lst[fin2].add(fin1);
                    dsu.union(fin1, fin2);
                }
                else if(fin_sal[fin2]==i) {
                    adj_lst[fin2].add(fin1);
                    adj_lst[fin1].add(fin2);
                    dsu.union(fin1, fin2);
                }
                else {
                    adj_lst[indx]=new ArrayList<>();
                    adj_lst[indx].add(fin1);
                    adj_lst[fin1].add(indx);
                    adj_lst[indx].add(fin2);
                    adj_lst[fin2].add(indx);
                    fin_sal[indx]=i;
                    dsu.union(indx, fin1);
                    dsu.union(indx, fin2);
                    indx++;
                }
            }
        }
        ans.append(indx+"\n");
        for(int i=0;i<indx;i++) {
            ans.append(fin_sal[i]+" ");
        }
        ans.append("\n");
        ans.append(indx+"\n");
        for(int i=0;i<indx;i++) {
            for(int j=0;j<adj_lst[i].size();j++) {
                int tmp=adj_lst[i].get(j);
                if(i<tmp) {
                    continue;
                }
                if(fin_sal[i]<fin_sal[tmp]) {
                    ans.append((i+1)+" "+(tmp+1)+"\n");
                }
                else {
                    ans.append((tmp+1)+" "+(i+1)+"\n");
                }
            }
        }
        System.out.println(ans);
    }
    public static int DFS(int root,int indx,int n,DSU dsu) {
        for(int i=indx;i>=n;i--) {
            if(dsu.find(root)==dsu.find(i)) {
                return i;
            }
        }
        return root;
    }
}
