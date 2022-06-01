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
public class E_Breaking_Good {
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
    
    static int n,m,depth0[],cost0[],depthn[],costn[],parent0[],parentn[];
    static ArrayList<Integer> adj_lst[],state[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        
        n=input.scanInt();
        m=input.scanInt();
        adj_lst=new ArrayList[n];
        state=new ArrayList[n];
        
        HashMap<String,Integer> map=new HashMap<>();
        
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<>();
            state[i]=new ArrayList<>();
        }
        
        int good=0,bad=0;
        for(int i=0;i<m;i++) {
            int u=input.scanInt()-1;
            int v=input.scanInt()-1;
            int tmp=input.scanInt();
            tmp^=1;
            if(tmp==0) {
                good++;
            }
            else {
                bad++;
            }
            adj_lst[u].add(v);
            adj_lst[v].add(u);
            state[u].add(tmp);
            state[v].add(tmp);
            
            map.put((u+1)+" "+(v+1), tmp);
        }
        
        BFS0(0);
        BFSn(n-1);
        
        int dist=depth0[n-1],indx=-1,cost=Integer.MAX_VALUE;
        for(int i=0;i<n;i++) {
            int dd=depth0[i]+depthn[i];
            if(dd!=dist) {
                continue;
            }
            int cc=cost0[i]+costn[i];
            if(cc<=cost) {
                cost=cc;
                indx=i;
            }
        }
        
        
        int root=indx,cnt=0;
        while(parent0[root]!=-1) {
            String str1=(root+1)+" "+(parent0[root]+1),str2=(parent0[root]+1)+" "+(root+1);
//            System.out.println(str1+" | "+str2);
            if(map.containsKey(str1)) {
                if(map.get(str1)==1) {
                    cnt++;
                    ans.append(str1+" 1\n");
                }
                map.remove(str1);
            }
            else {
                if(map.get(str2)==1) {
                    cnt++;
                    ans.append(str2+" 1\n");
                }
                map.remove(str2);
            }
            root=parent0[root];
        }
        root=indx;
        while(parentn[root]!=-1) {
            String str1=(root+1)+" "+(parentn[root]+1),str2=(parentn[root]+1)+" "+(root+1);
//            System.out.println(str1+" | "+str2);
            if(map.containsKey(str1)) {
                if(map.get(str1)==1) {
                    cnt++;
                    ans.append(str1+" 1\n");
                }
                map.remove(str1);
            }
            else {
                if(map.get(str2)==1) {
                    cnt++;
                    ans.append(str2+" 1\n");
                }
                map.remove(str2);
            }
            root=parentn[root];
        }
        
        for(String str:map.keySet()) {
            if(map.get(str)==0) {
                ans.append(str+" 0\n");
                cnt++;
            }
        }
        System.out.println(cnt+"\n"+ans);
    }
    
    public static void BFS0(int src) {
        depth0=new int[n];
        cost0=new int[n];
        parent0=new int[n];
        Arrays.fill(parent0, -1);
        Queue<Integer> que=new LinkedList<>();
        boolean vis[]=new boolean[n];
        que.add(src);
        vis[src]=true;
        while(!que.isEmpty()) {
            int root=que.poll();
            for(int i=0;i<adj_lst[root].size();i++) {
                if(vis[adj_lst[root].get(i)]) {
                    continue;
                }
                que.add(adj_lst[root].get(i));
                vis[adj_lst[root].get(i)]=true;
                depth0[adj_lst[root].get(i)]=depth0[root]+1;
                cost0[adj_lst[root].get(i)]=cost0[root]+state[root].get(i);
                parent0[adj_lst[root].get(i)]=root;
            }
        }
    }
    
    public static void BFSn(int src) {
        depthn=new int[n];
        costn=new int[n];
        parentn=new int[n];
        Arrays.fill(parentn, -1);
        Queue<Integer> que=new LinkedList<>();
        boolean vis[]=new boolean[n];
        que.add(src);
        vis[src]=true;
        while(!que.isEmpty()) {
            int root=que.poll();
            for(int i=0;i<adj_lst[root].size();i++) {
                if(vis[adj_lst[root].get(i)]) {
                    continue;
                }
                que.add(adj_lst[root].get(i));
                vis[adj_lst[root].get(i)]=true;
                depthn[adj_lst[root].get(i)]=depthn[root]+1;
                costn[adj_lst[root].get(i)]=costn[root]+state[root].get(i);
                parentn[adj_lst[root].get(i)]=root;
            }
        }
    }
}
