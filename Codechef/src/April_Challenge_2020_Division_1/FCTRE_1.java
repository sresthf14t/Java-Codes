/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package April_Challenge_2020_Division_1;

/**
 *
 * @author User
 */
import java.io.*; 
import java.util.*; 
public class FCTRE_1 {
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
    static int cost[],depth[];
    static boolean sieve[];
    static int primes[],factors[][];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        sieve=new boolean[1000001];
        primes=new int[78498];
        sieve(1000001);
        int test=input.scanInt();
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
             //No of nodes
            adj_lst=new ArrayList[n];
            vis=new boolean[n];
            cost=new int[n];
            depth=new int[n];
            for(int i=0;i<n;i++) {
                adj_lst[i]=new ArrayList<Integer>();
            }
            //No of edges
            for(int i=0;i<n-1;i++) {
                // input u & v
                int u=input.scanInt();
                int v=input.scanInt();
                u--;
                v--;
                adj_lst[u].add(v);
                adj_lst[v].add(u);
            }
            for(int i=0;i<n;i++) {
                cost[i]=input.scanInt();
            }
            factors(n);
//            for(int i=0;i<adj_lst.length;i++) {
//                System.out.print(i+"->");
//                for(int j=0;j<adj_lst[i].size();j++) {
//                    System.out.print(adj_lst[i].get(j)+" ");
//                }
//                System.out.println();
//            }
            depth_DFS(0,0);
            StringBuilder ans=new StringBuilder("");
            int q=input.scanInt();
            for(int i=1;i<=q;i++) {
                vis=new boolean[n];
                int u=input.scanInt();
                int v=input.scanInt();
                u--;
                v--;
                ArrayList<Integer> path_u=new ArrayList<>();
                Set<Integer> hash_path_u=new HashSet<>();
                trav_up_DFS(u,path_u,hash_path_u);
                ArrayList<Integer> path_v=new ArrayList<>();
                Set<Integer> hash_path_v=new HashSet<>();
                trav_up_DFS(v,path_v,hash_path_v);
                
                if(hash_path_u.contains(v)) {
                    ArrayList<Integer> new_path=new ArrayList<>();
                    for(int j=0;j<path_u.size();j++) {
                        new_path.add(path_u.get(j));
                        if(path_u.get(j)==v) {
                            break;
                        }
                    }
                    ans.append(find_n_factors(new_path)+"\n");
                    continue;
                }
                
                if(hash_path_v.contains(u)) {
                    ArrayList<Integer> new_path=new ArrayList<>();
                    for(int j=0;j<path_v.size();j++) {
                        new_path.add(path_v.get(j));
                        if(path_v.get(j)==u) {
                            break;
                        }
                    }
                    ans.append(find_n_factors(new_path)+"\n");
                    continue;
                }
                
                int indx=-1;
                for(int j=0;j<path_u.size();j++) {
                    for(int k=0;k<path_v.size();k++) {
                        if(path_u.get(j).equals(path_v.get(k))) {
                            indx=path_u.get(j);
                            break;
                        }
                    }
                    if(indx!=-1) {
                        break;
                    }
                }
                ArrayList<Integer> new_path=new ArrayList<>();
                for(int j=0;j<path_u.size();j++) {
                    new_path.add(path_u.get(j));
                    if(path_u.get(j)==indx) {
                        break;
                    }
                }
                for(int j=0;j<path_v.size();j++) {
                    if(path_v.get(j)==indx) {
                        break;
                    }
                    new_path.add(path_v.get(j));
                }
                ans.append(find_n_factors(new_path)+"\n");
            }
            System.out.println(ans);
        }
    }
    public static void depth_DFS(int root,int dep) {
        vis[root]=true;
        depth[root]=dep;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(!vis[adj_lst[root].get(i)]) {
                depth_DFS(adj_lst[root].get(i),dep+1);
            }
        }
    }
    
    public static void trav_up_DFS(int root,ArrayList<Integer> path,Set<Integer> hash_path) {
        path.add(root);
        hash_path.add(root);
        for(int i=0;i<adj_lst[root].size();i++) {
            if(depth[adj_lst[root].get(i)]<depth[root]) {
                trav_up_DFS(adj_lst[root].get(i),path,hash_path);
            }
        }
    }
    
    //false for prime number and true for composite number
    public static void sieve(int n) {
        int k=-1;
        for(int i=2;i<n;i++) {
            if(!sieve[i]) {
                k++;
                primes[k]=i;
                for(int j=2*i;j<n;j=j+i) {
                    sieve[j]=true;
                }
            }
        }
    }
    
    public static long find_n_factors(ArrayList<Integer> path) {
        long mod=1000000007L,product=1;
        for(int i=0;i<primes.length;i++) {
            long count=0;
            for(int j=0;j<path.size();j++) {
                count+=factors[path.get(j)][i];
            }
            product*=(count+1);
            product%=mod;
        }
        return product;
    }
    
    public static void factors(int n) {
        factors=new int[n][primes.length];
        for(int i=0;i<cost.length;i++) {
            int tmp=cost[i];
            for(int j=0;j<primes.length;j++) {
                int count=0;
                while(tmp%primes[j]==0) {
                    tmp/=primes[j];
                    count++;
                }
                factors[i][j]=count;
            }
        }
    }
}
