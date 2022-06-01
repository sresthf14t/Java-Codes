import java.util.*;
import java.io.*;
public class Main {
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
     
    static  class seg_tree {
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
    
    
    static ArrayList<Integer> adj_lst[],et,etd;
    static int parent[],euler_tour_depth[],euler_tour[],depth[],max_path;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int test=input.scanInt();
        StringBuilder ans=new StringBuilder("");
        for(int tt=1;tt<=test;tt++) {
            int n=input.scanInt();
            int a=input.scanInt()-1;
            int b=input.scanInt()-1;
            int da=input.scanInt();
            int db=input.scanInt();
            depth=new int[n];
            adj_lst=new ArrayList[n];
            parent=new int[n];
            et=new ArrayList<>();
            etd=new ArrayList<>();
            for(int i=0;i<n;i++) {
                adj_lst[i]=new ArrayList<>();
            }
            for(int i=0;i<n-1;i++) {
                int u=input.scanInt()-1;
                int v=input.scanInt()-1;
                adj_lst[u].add(v);
                adj_lst[v].add(u);
            }
            if(n==2) {
                ans.append("Alice\n");
                continue;
            }
            int root=-1;
            for(int i=0;i<n;i++) {
                if(adj_lst[i].size()>1) {
                    root=i;
                    break;
                }
            }
            DFS(root,-1,0);
            euler_tour_depth=new int[et.size()];
            euler_tour=new int[etd.size()];
            for(int i=0;i<et.size();i++) {
                euler_tour[i]=et.get(i);
                euler_tour_depth[i]=etd.get(i);
            }
            seg_tree st=new seg_tree(euler_tour_depth.length,euler_tour_depth);
            int first[]=new int[n];
            Arrays.fill(first, -1);
            for(int i=0;i<euler_tour.length;i++) {
                if(first[euler_tour[i]]==-1) {
                    first[euler_tour[i]]=i;
                }
            }
            boolean is_pos=false;
            if(dist(a,b,first,st)<=da) {
                ans.append("Alice\n");
                continue;
            }
            for(int i=0;i<n;i++) {
                if(i==a || i==b) {
                    continue;
                }
                int dist_a=dist(a,i,first,st);
                int dist_b=dist(b,i,first,st);
//                System.out.println(i+" "+dist_a+" "+dist_b);
                if(dist_a>da && dist_b<=db) {
                    is_pos=true;
                    break;
                }
            }
            if(!is_pos) {
                ans.append("Alice\n");
                continue;
            }
            
            if(2*da>=db) {
                ans.append("Alice\n");
                continue;
            }
//            System.out.println(root+1);
            max_path=0;
            check(root,-1,0);
//            System.out.println(max_path);
            if(max_path>2*da) {
                ans.append("Bob\n");
                continue;
            }
            ans.append("Alice\n");
        }
        System.out.println(ans);
    }
    public static void DFS(int root,int par,int dep) {
        parent[root]=par;
        et.add(root);
        etd.add(dep);
        depth[root]=dep;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==par) {
                continue;
            }
            DFS(adj_lst[root].get(i),root,dep+1);
            et.add(root);
            etd.add(dep);
        }
    }
    static int dist(int u,int v,int first[],seg_tree st) {
        int l=Math.min(first[u],first[v]);
        int r=Math.max(first[u],first[v]);
        int indx=st.min(0, 0, euler_tour_depth.length-1, l, r)[1];
        indx=euler_tour[indx];
//        System.out.println(u+" "+v+" "+indx);
        return Math.abs(depth[indx]-depth[u])+Math.abs(depth[indx]-depth[v]);
    }
    public static int check(int root,int par,int dep) {
        if(adj_lst[root].size()==1) {
//            System.out.println(root+1);
            return 1;
        }
        ArrayList<Integer> cnt=new ArrayList<>();
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==par) {
                continue;
            }
            cnt.add(check(adj_lst[root].get(i),root,dep+1));
        }
        cnt.sort(null);
        if(cnt.size()==1) {
            return cnt.get(0)+1;
        }
//        System.out.println((root+1)+" "+cnt.get(cnt.size()-1)+" "+cnt.get(cnt.size()-2));
        max_path=Math.max(max_path,cnt.get(cnt.size()-1)+cnt.get(cnt.size()-2));
        return cnt.get(cnt.size()-1)+1;
    }
}
