/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_581_Div_2_Virtual;

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
    static ArrayList<Integer> adj_lst[];
    static int dist[][];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        int n=input.scanInt();
        adj_lst=new ArrayList[n];
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<>();
        }
        for(int i=0;i<n;i++) {
            String str=input.scanString();
            for(int j=0;j<n;j++) {
                if(str.charAt(j)=='1') {
                    adj_lst[i].add(j);
                }
            }
        }
        int m=input.scanInt();
        int path[]=new int[m];
        for(int i=0;i<m;i++) {
            path[i]=input.scanInt()-1;
        }
        dist=new int[n][n];
        for(int i=0;i<n;i++) {
            BFS(i);
        }
        int prev[]=new int[n];
        Arrays.fill(prev, -1);
        int dp[]=new int[m];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0]=1;
        prev[path[0]]=0;
        for(int i=1;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(j==path[i]) {
                    continue;
                }
                int indx=prev[j];
                if(indx==-1) {
                    continue;
                }
                if(dist[j][path[i]]==(i-indx)) {
                    dp[i]=Math.min(dp[i], dp[indx]+1);
                }
            }
            prev[path[i]]=i;
        }
//        for(int i=0;i<m;i++) {
//            System.out.print(dp[i]+" ");
//        }
//        System.out.println();
        ArrayList<Integer> arrli=new ArrayList<>();
        int indx=m-1;
        while(indx>=0) {
//            System.out.println(indx);
            arrli.add((path[indx]+1));
            if(indx==0) {
                break;
            }
            for(int j=indx-1;j>=0;j--) {
                if(dp[j]==dp[indx]-1 && dist[path[j]][path[indx]]==indx-j) {
                    indx=j;
                    break;
                }
            }
        }
        ans.append(dp[m-1]+"\n");
        for(int i=arrli.size()-1;i>=0;i--) {
            ans.append(arrli.get(i)+" ");
        }
        System.out.println(ans);
    }
    
    static void BFS(int src) {
        boolean vis[]=new boolean[adj_lst.length];
        int depth[]=new int[adj_lst.length];
        Arrays.fill(depth, Integer.MAX_VALUE);
        Queue<Integer> que = new LinkedList<>();
        que.add(src);
        depth[src]=0;
        vis[src]=true;
        while(!que.isEmpty()) {
            for(int i=0;i<adj_lst[que.peek()].size();i++) {
                if(!vis[adj_lst[que.peek()].get(i)]) {
                    que.add(adj_lst[que.peek()].get(i));
                    vis[adj_lst[que.peek()].get(i)]=true;
                    depth[adj_lst[que.peek()].get(i)]=depth[que.peek()]+1;
                }
            }
            que.poll();
        }
        dist[src]=depth;
    }
}
