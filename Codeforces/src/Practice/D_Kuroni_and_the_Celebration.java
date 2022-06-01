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
public class D_Kuroni_and_the_Celebration {
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
    static int max_dist,n1,n2,mid;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int n=input.scanInt();
        adj_lst=new ArrayList[n];
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<>();
        }
        for(int i=0;i<n-1;i++) {
            int u=input.scanInt()-1;
            int v=input.scanInt()-1;
            adj_lst[u].add(v);
            adj_lst[v].add(u);
        }
        max_dist=0;
        n1=n2=-1;
        DFS(0,0,-1);
        mid=-1;
        find(n1,0,-1);
        int root=mid;
//        System.out.println(n1+" "+n2+" "+root);
        boolean done=true;
        while(done) {
//            System.out.println("root="+(root+1));
            done=false;
            if(adj_lst[root].size()==1) {
                System.out.println("? "+(adj_lst[root].get(0)+1)+" "+(root+1));
                System.out.flush();
                int tmp=input.scanInt()-1;
                adj_lst[root].remove(new Integer(tmp));
                adj_lst[tmp].remove(new Integer(root));
                if(tmp!=root) {
                    root=tmp;
                    done=true;
                }
                continue;
            }
            while(adj_lst[root].size()>1) {
                int i=0;
                if(i+1==adj_lst[root].size()) {
                    break;
                }
                System.out.println("? "+(adj_lst[root].get(i)+1)+" "+(adj_lst[root].get(i+1)+1));
                System.out.flush();
                int tmp=input.scanInt()-1;
                if(tmp==root) {
                    adj_lst[adj_lst[root].get(i)].remove(new Integer(root));
                    adj_lst[adj_lst[root].get(i+1)].remove(new Integer(root));
                    adj_lst[root].remove(new Integer(adj_lst[root].get(i)));
                    adj_lst[root].remove(new Integer(adj_lst[root].get(i)));
                    continue;
                }
                adj_lst[root].remove(new Integer(tmp));
                adj_lst[tmp].remove(new Integer(root));
                root=tmp;
                done=true;
                break;
            }
            if(done) {
                continue;
            }
            
            if(adj_lst[root].size()%2==1) {
                root=adj_lst[root].get(adj_lst[root].size()-1);
                done=true;
            }
        }
        System.out.println("! "+(root+1));
        System.out.flush();
    }
    public static int[] DFS(int root,int dep,int par) {
        ArrayList<Integer> dist,node;
        dist=new ArrayList<>();
        node=new ArrayList<>();
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)!=par) {
                int tmp[]=DFS(adj_lst[root].get(i),dep+1,root);
                dist.add(tmp[0]);
                node.add(tmp[1]);
            }
        }
        if(dist.size()==0) {
            return new int[]{1,root};
        }
        if(dist.size()==1) {
            if(dist.get(0)>max_dist) {
                max_dist=dist.get(0);
                n1=node.get(0);
                n2=root;
            }
            return new int[]{dist.get(0)+1,node.get(0)};
        }
        int max=-1,max_indx=-1,sec_max=-1,sec_max_indx=-1;
        for(int i=0;i<dist.size();i++) {
            if(dist.get(i)>max) {
                max=dist.get(i);
                max_indx=i;
            }
        }
        for(int i=0;i<dist.size();i++) {
            if(i==max_indx) {
                continue;
            }
            if(dist.get(i)>sec_max) {
                sec_max=dist.get(i);
                sec_max_indx=i;
            }
        }
        if(max+sec_max>max_dist) {
            max_dist=max+sec_max;
            n1=node.get(max_indx);
            n2=node.get(sec_max_indx);
        }
        return new int[]{max+1,node.get(max_indx)};
    }
    public static int find(int root,int dep,int par) {
        if(root==n2) {
            return dep;
        }
        int tmp=-1;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)!=par) {
                tmp=find(adj_lst[root].get(i),dep+1,root);
                if(tmp!=-1) {
                    break;
                }
            }
        }
        if(tmp==-1) {
            return -1;
        }
        if(Math.abs(dep-(tmp/2))==0) {
            mid=root;
        }
        if(mid==-1 && Math.abs(dep-(tmp/2))==1) {
            mid=root;
        }
        return tmp;
    }
}
