/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_Code_1_0;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class POWK {
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
    static ArrayList<Integer> adj_lst[];
    static boolean vis[];
    static int depth[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int test=input.scanInt();
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            int k=input.scanInt();
            adj_lst=new ArrayList[n];
            vis=new boolean[n];
            depth=new int[n];
            color=new int[n];
            int indx[]=new int[n];
            for(int i=0;i<n;i++) {
                adj_lst[i]=new ArrayList<Integer>();
                indx[i]=i;
            }
            boolean inc[]=new boolean[n];
            for(int i=0;i<k;i++) {
                // input u & v
                int u=input.scanString().charAt(1)-49;
                int v=input.scanString().charAt(1)-49;
//                adj_lst[u].add(v);
                adj_lst[v].add(u);
                inc[u]=true;
            }
            boolean is_pos=true;
            int cnt=0,root=-1;
            for(int i=0;i<n;i++) {
                if(!inc[i]) {
                    cnt++;
                    root=i;
                }
            }
//            System.out.println(cnt+" "+root);
            if(cnt!=1) {
                System.out.println("NO");
                continue;
            }
            for(int i=0;i<n;i++) {
                if(vis[i]) {
                    continue;
                }
                if(cycle_DFS(i)) {
                    is_pos=false;
                    break;
                }
            }
            if(!is_pos) {
                System.out.println("NO");
                continue;
            }
            vis=new boolean[n];
            DFS(root,1);
//            for(int i=0;i<n;i++) {
//                System.out.print(depth[i]+" ");
//            }
//            System.out.println();
            
            for(int i=0;i<n;i++) {
                if(!vis[i]) {
                    is_pos=false;
                    break;
                }
            }
            if(!is_pos) {
                System.out.println("NO");
                continue;
            }
            sort(n,depth,indx);
            
            for(int i=0;i<n-1;i++) {
                if(depth[i+1]!=depth[i]+1) {
                    is_pos=false;
                    break;
                }
            }
            if(!is_pos) {
                System.out.println("NO");
                continue;
            }
            System.out.println("YES");
            for(int i=0;i<n;i++) {
                System.out.println("k"+(indx[i]+1));
            }
//            System.out.println();
        }
    }
    public static void DFS(int root,int dep) {
//        System.out.println(root);
        vis[root]=true;
        depth[root]=Math.max(depth[root],dep);
        for(int i=0;i<adj_lst[root].size();i++) {
            DFS(adj_lst[root].get(i),dep+1);
        }
    }
    
    
    public static void sort(int n,int a[],int b[]) {
        for(int i=0;i<n;i++) {
            for(int j=0;j<n-1;j++) {
                if(a[j]>a[j+1]) {
                    swap(a,j,j+1);
                    swap(b,j,j+1);
                }
            }
        }
    }
    public static void swap(int arr[],int l,int r) {
        int tmp=arr[l];
        arr[l]=arr[r];
        arr[r]=tmp;
    }
    
    //-1->Black 0->White 1->Grey
    static int color[];
    public static boolean cycle_DFS(int root) {
        color[root]=1;
        vis[root]=true;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(color[adj_lst[root].get(i)]==1) {
                return true;
            }
            if(!vis[adj_lst[root].get(i)]) {
                if(cycle_DFS(adj_lst[root].get(i))) {
                    return true;
                }
            }
        }
        color[root]=-1;
        return false;
    }
}
