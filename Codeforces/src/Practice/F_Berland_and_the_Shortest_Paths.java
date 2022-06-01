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
public class F_Berland_and_the_Shortest_Paths {
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
    static ArrayList<Integer> adj_lst[],indx[],choice[];
    static int depth[],fin[][],curr;
    static boolean vis[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        int n=input.scanInt();
        int m=input.scanInt();
        int k=input.scanInt();
        adj_lst=new ArrayList[n];
        indx=new ArrayList[n];
        choice=new ArrayList[n];
        depth=new int[n];
        vis=new boolean[n];
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<>();
            choice[i]=new ArrayList<>();
            indx[i]=new ArrayList<>();
        }
        for(int i=0;i<m;i++) {
            int u=input.scanInt()-1;
            int v=input.scanInt()-1;
            adj_lst[u].add(v);
            adj_lst[v].add(u);
            indx[u].add(i);
            indx[v].add(i);
        }
        BFS();
//        for(int i=0;i<n;i++) {
//            System.out.print(depth[i]+" ");
//        }
//        System.out.println();
        fin=new int[k][m];
        for(int i=0;i<n;i++) {
            for(int j=0;j<adj_lst[i].size();j++) {
                if(depth[adj_lst[i].get(j)]==depth[i]-1) {
                    choice[i].add(indx[i].get(j));
                }
            }
        }
        curr=0;
        create(1,new int[m]);
        int cnt=k;
        for(int i=1;i<k;i++) {
            boolean same=true;
            for(int j=0;j<m;j++) {
                if(fin[i][j]!=0) {
                    same=false;
                    break;
                }
            }
            if(same) {
                cnt=i;
                break;
            }
        }
        ans.append(cnt+"\n");
        for(int i=0;i<cnt;i++) {
            for(int j=0;j<m;j++) {
                ans.append(fin[i][j]);
            }
            ans.append("\n");
        }
        System.out.println(ans);
    }
    public static void BFS() {
        Queue<Integer> que=new LinkedList<>();
        que.add(0);
        vis[0]=true;
        while(!que.isEmpty()) {
            int root=que.poll();
            for(int i=0;i<adj_lst[root].size();i++) {
                if(vis[adj_lst[root].get(i)]) {
                    continue;
                }
                que.add(adj_lst[root].get(i));
                vis[adj_lst[root].get(i)]=true;
                depth[adj_lst[root].get(i)]=depth[root]+1;
            }
        }
    }
    public static void create(int indx,int arr[]) {
        if(curr==fin.length) {
            return;
        }
        if(indx==adj_lst.length) {
            copy(arr);
            curr++;
            return;
        }
        for(int i=0;i<choice[indx].size();i++) {
            arr[choice[indx].get(i)]=1;
            create(indx+1,arr);
            arr[choice[indx].get(i)]=0;
        }
    }
    public static void copy(int arr[]) {
        for(int i=0;i<arr.length;i++) {
            fin[curr][i]=arr[i];
        }
    }
}
