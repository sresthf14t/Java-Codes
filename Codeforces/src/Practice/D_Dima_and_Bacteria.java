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
public class D_Dima_and_Bacteria {
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
        int seg_tree[],seg_indx[];
        public seg_tree(int n,int arr[]) {
            seg_tree=new int[4*n];
            seg_indx=new int[4*n];
            create_seg_tree(arr,0,0,n-1);
        }

        //0 index-Left child-(2*i+1) Right Child-(2*i+2)

        public void create_seg_tree(int arr[],int vertex,int l,int r) {
            if(l==r) {
                seg_indx[vertex]=r;
                seg_tree[vertex]=arr[r];
                return;
            }
            int mid=(l+r)/2;
            //Left Child
            create_seg_tree(arr,(2*vertex)+1,l,mid);
            //Right Child
            create_seg_tree(arr,(2*vertex)+2,mid+1,r);
            //Filling this node
            if(seg_tree[(2*vertex)+1]<seg_tree[(2*vertex)+2]) {
                seg_tree[vertex]=seg_tree[(2*vertex)+1];
                seg_indx[vertex]=seg_indx[(2*vertex)+1];
            }
            else {
                seg_tree[vertex]=seg_tree[(2*vertex)+2];
                seg_indx[vertex]=seg_indx[(2*vertex)+2];
            }
        }

        public int[] min(int vertex,int l,int r,int ql,int qr) {  //ql->query left , qr-> query right l->curr Segmrnt left    r->curr segment right
            if(ql>qr) {
                return new int[]{Integer.MAX_VALUE,-1};
            }

            if(ql==l && qr==r) {
                return new int[]{seg_tree[vertex],seg_indx[vertex]};
            }
            int mid=(l+r)/2;

            //Left Child
            int min1[]=min((2*vertex)+1,l,mid,ql,Math.min(qr, mid));

            //Right Child
            int min2[]=min((2*vertex)+2,mid+1,r,Math.max(mid+1,ql),qr);

            if(min1[0]<min2[0]) {
                return min1;
            }
            else {
                return min2;
            }
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
            if(seg_tree[(2*vertex)+1]<seg_tree[(2*vertex)+2]) {
                seg_tree[vertex]=seg_tree[(2*vertex)+1];
                seg_indx[vertex]=seg_indx[(2*vertex)+1];
            }
            else {
                seg_tree[vertex]=seg_tree[(2*vertex)+2];
                seg_indx[vertex]=seg_indx[(2*vertex)+2];
            }
        }
    }

    
    
    
    
    static ArrayList<Integer> adj_lst[],weight[];
    static boolean vis[];
    static int dist[],prev[],k;
    static seg_tree s_tree;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("Yes\n");
        int n=input.scanInt();
        int m=input.scanInt();
        k=input.scanInt();
        int mark[]=new int[n];
        int indx=0;
        for(int i=0;i<k;i++) {
            int tmp=input.scanInt();
            for(int j=0;j<tmp;j++,indx++) {
                mark[indx]=i;
            }
        }
        adj_lst=new ArrayList[n];
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<>();
        }
        vis=new boolean[n];
        int u[]=new int[m];
        int v[]=new int[m];
        int wei[]=new int[m];
        int component[]=new int[n];
        for(int i=0;i<m;i++) {
            u[i]=input.scanInt()-1;
            v[i]=input.scanInt()-1;
            wei[i]=input.scanInt();
            if(wei[i]==0) {
                adj_lst[u[i]].add(v[i]);
                adj_lst[v[i]].add(u[i]);
            }
        }
        int cnt=1;
        for(int i=0;i<n;i++) {
            if(!vis[i]) {
                ArrayList<Integer> arrli=new ArrayList<>();
                DFS(i,arrli);
                for(int j=0;j<arrli.size();j++) {
                    component[arrli.get(j)]=cnt;
                }
                cnt++;
            }
        }
        boolean is_pos=true;
        for(int i=0;i<n-1;i++) {
            if(mark[i]==mark[i+1] && component[i]!=component[i+1]) {
                is_pos=false;
                break;
            }
        }
        if(!is_pos) {
            System.out.println("No");
            return;
        }
        adj_lst=new ArrayList[k];
        weight=new ArrayList[k];
        for(int i=0;i<k;i++) {
            adj_lst[i]=new ArrayList<>();
            weight[i]=new ArrayList<>();
        }
        for(int i=0;i<m;i++) {
            if(mark[u[i]]==mark[v[i]]) {
                continue;
            }
            if(adj_lst[mark[u[i]]].contains(mark[v[i]])) {
                indx=adj_lst[mark[u[i]]].indexOf(mark[v[i]]);
                if(weight[mark[u[i]]].get(indx)<=wei[i]) {
                    continue;
                }
                weight[mark[u[i]]].set(indx, wei[i]);
                indx=adj_lst[mark[v[i]]].indexOf(mark[u[i]]);
                weight[mark[v[i]]].set(indx, wei[i]);
            }
            else {
                adj_lst[mark[u[i]]].add(mark[v[i]]);
                adj_lst[mark[v[i]]].add(mark[u[i]]);
                weight[mark[u[i]]].add(wei[i]);
                weight[mark[v[i]]].add(wei[i]);
            }
        }
//        for(int i=0;i<n;i++) {
//            System.out.println(mark[i]+" "+component[i]);
//        }
//        for(int i=0;i<adj_lst.length;i++) {
//            System.out.print(i+"->");
//            for(int j=0;j<adj_lst[i].size();j++) {
//                System.out.print(adj_lst[i].get(j)+" "+weight[i].get(j));
//            }
//            System.out.println();
//        }
        for(int i=0;i<k;i++) {
            vis=new boolean[k];
            dist=new int[k];
            prev=new int[k];
            Arrays.fill(prev, -1);
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[i]=0;
            s_tree=new seg_tree(k,dist);
            Dijkstras(i);
            for(int j=0;j<k;j++) {
                if(dist[j]==Integer.MAX_VALUE) {
                    ans.append(-1+" ");
                }
                else {
                    ans.append(dist[j]+" ");
                }
            }
            ans.append("\n");
        }
        System.out.println(ans);
    }
    
    public static void Dijkstras(int root) {
//        System.out.println("ROOT="+root);
        vis[root]=true;
        dist[root]=0;
        prev[root]=-1;
        while(true) {
//            System.out.println(root);
            vis[root]=true;
            for(int i=0;i<adj_lst[root].size();i++) {
                if(vis[adj_lst[root].get(i)]) {
                    continue;
                }
                if(dist[root]+weight[root].get(i)<dist[adj_lst[root].get(i)]) {
                    dist[adj_lst[root].get(i)]=dist[root]+weight[root].get(i);
                    prev[adj_lst[root].get(i)]=root;
                    s_tree.update(0, 0, k-1, adj_lst[root].get(i), dist[adj_lst[root].get(i)]);
                }
            }
            s_tree.update(0, 0, k-1, root, Integer.MAX_VALUE);
            int tmp[]=s_tree.min(0, 0, k-1, 0, k-1);
            if(tmp[0]==Integer.MAX_VALUE) {
                break;
            }
            root=tmp[1];
        }
    }
    
    public static void DFS(int root,ArrayList<Integer> arrli) {
        vis[root]=true;
        arrli.add(root);
        for(int i=0;i<adj_lst[root].size();i++) {
            if(!vis[adj_lst[root].get(i)]) {
                DFS(adj_lst[root].get(i),arrli);
            }
        }
    }
}
