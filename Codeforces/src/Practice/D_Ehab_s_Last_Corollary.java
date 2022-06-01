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
public class D_Ehab_s_Last_Corollary {
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
    static int n,m,k,strt,end;
    static ArrayList<Integer> adj_lst[],path;
    static boolean vis[];
    static int depth[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n=input.scanInt();
        m=input.scanInt();
        k=input.scanInt();
        depth=new int[n];
        adj_lst=new ArrayList[n];
        vis=new boolean[n];
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<>();
        }
        for(int i=0;i<m;i++) {
            int u=input.scanInt()-1;
            int v=input.scanInt()-1;
            adj_lst[u].add(v);
            adj_lst[v].add(u);
        }
        strt=end=-1;
        DFS(0,-1,0);
        if(m==n-1) {
            System.out.println(1);
            int lim=k/2+k%2;
            int cnt=0;
            StringBuilder ans=new StringBuilder("");
            for(int i=0;i<n;i++) {
                if(depth[i]%2==0) {
                    ans.append((i+1)+" ");
                    cnt++;
                }
                if(cnt==lim) {
                    break;
                }
            }
            if(cnt>=lim) {
                System.out.println(ans);
                return;
            }
            cnt=0;
            ans=new StringBuilder("");
            for(int i=0;i<n;i++) {
                if(depth[i]%2==1) {
                    ans.append((i+1)+" ");
                    cnt++;
                }
                if(cnt==lim) {
                    break;
                }
            }
            System.out.println(ans);
            return;
        }
        path=null;
        vis=new boolean[n];
        find_path(strt,end,new ArrayList<>());
        while(true) {
            Set<Integer> hashset=new HashSet<>();
            for(int i=0;i<path.size();i++) {
                hashset.add(path.get(i));
            }
            for(int i=0;i<path.size();i++) {
                ArrayList<Integer> tmp=new ArrayList<>();
                for(int j=0;j<adj_lst[path.get(i)].size();j++) {
                    if(hashset.contains(adj_lst[path.get(i)].get(j))) {
                        tmp.add(adj_lst[path.get(i)].get(j));
                    }
                }
                adj_lst[path.get(i)]=tmp;
            }
            boolean done=true;
            for(int i=0;i<path.size();i++) {
                if(adj_lst[path.get(i)].size()>2) {
                    done=false;
                    strt=path.get(i);
                    int prev=i-1;
                    if(prev<0) {
                        prev+=path.size();
                    }
                    int nxt=(i+1)%path.size();
                    for(int j=0;j<adj_lst[path.get(i)].size();j++) {
                        if((int)adj_lst[path.get(i)].get(j)==(int)path.get(prev) || (int)adj_lst[path.get(i)].get(j)==(int)path.get(nxt)) {
                            continue;
                        }
                        end=adj_lst[path.get(i)].get(j);
                        break;
                    }
                    break;
                }
            }
            if(done) {
                break;
            }
            ArrayList<Integer> tmp1=new ArrayList<>();
            ArrayList<Integer> tmp2=new ArrayList<>();
            int indx=path.indexOf(new Integer(strt));
            while(path.get(indx)!=end) {
                tmp1.add(path.get(indx));
                indx++;
                indx%=path.size();
            }
            indx=path.indexOf(new Integer(strt));
            while(path.get(indx)!=end) {
                tmp2.add(path.get(indx));
                indx--;
                if(indx<0) {
                    indx+=path.size();
                }
            }
            if(tmp1.size()<tmp2.size()) {
                path=tmp1;
            }
            else {
                path=tmp2;
            }
            path.add(end);
        }
        StringBuilder ans=new StringBuilder("");
        if(path.size()<=k) {
            ans.append(2+"\n"+path.size()+"\n");
            for(int i=0;i<path.size();i++) {
                ans.append((path.get(i)+1)+" ");
            }
            ans.append("\n");
        }
        else {
            ans.append(1+"\n");
            int lim=k/2+k%2;
            for(int i=0,j=0;i<lim;i++,j+=2) {
                ans.append((path.get(j)+1)+" ");
            }
            ans.append("\n");
        }
        System.out.println(ans);
    }
    public static void DFS(int root,int par,int dep) {
        depth[root]=dep;
        if(strt!=-1) {
            return;
        }
        vis[root]=true;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==par) {
                continue;
            }
            if(vis[adj_lst[root].get(i)]) {
                strt=root;
                end=adj_lst[root].get(i);
                return;
            }
            DFS(adj_lst[root].get(i),root,dep+1);
        }
    }
    public static void find_path(int root,int prev,ArrayList<Integer> tmp) {
        if(path!=null) {
            return;
        }
        tmp.add(root);
        if(root==end) {
            path=new ArrayList<>(tmp);
            return;
        }
        vis[root]=true;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(vis[adj_lst[root].get(i)] || adj_lst[root].get(i)==prev) {
                continue;
            }
            find_path(adj_lst[root].get(i),root,tmp);
        }
        tmp.remove(tmp.size()-1);
    }
}
