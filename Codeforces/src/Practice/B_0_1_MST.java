/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

/**
 *
 * @author User
 */
import java.io.*; 
import java.util.*;
public class B_0_1_MST {
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
    static int parent[],size[];
    static TreeSet<Integer> ts;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int n=input.scanInt();
        int m=input.scanInt();
        ArrayList<Integer> adj_lst[]=new ArrayList[n];
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<>();
        }
        for(int i=0;i<n;i++) {
            int u=input.scanInt()-1;
            int v=input.scanInt()-1;
            adj_lst[u].add(v);
            adj_lst[v].add(u);
        }
        ts=new TreeSet<>();
        parent=new int[n];
        size=new int[n];
        for(int i=0;i<n;i++) {
            parent[i]=i;
            size[i]=1;
            ts.add(i);
        }
        ArrayList<Integer> update=new ArrayList<>();
        for(int i=0;i<n;i++) {
            TreeMap<Integer,Integer> map=new TreeMap<>();
            for(int j=0;j<adj_lst[i].size();j++) {
                int par=find(adj_lst[i].get(j));
                if(!map.containsKey(par)) {
                    map.put(par, 0);
                }
                map.replace(par, map.get(par)+1);
            }
            
            for(int j:ts) {
                if(i==j) {
                    continue;
                }
                if(!map.containsKey(j)) {
                    update.add(j);
                }
                else if(size[j]>map.get(j)) {
                    update.add(j);
                }
            }
            while(!update.isEmpty()) {
                System.out.println(i+" "+update.get(0));
                union(i,update.get(0));
                update.remove(0);
            }
        }
        Set<Integer> hashset=new HashSet<>();
        for(int i=0;i<n;i++) {
            hashset.add(find(i));
        }
        System.out.println(hashset.size()-1);
    }
    
    public static void union(int x,int y) {
        int x_root=find(x);
        int y_root=find(y);
        if(x_root==y_root) {
            return;
        }
        size[y]+=size[x_root];
        parent[x_root]=y;
        ts.remove(x_root);
    }
    public static int find(int x) {
        if(parent[x]==x) {
            return x;
        }
        return find(parent[x]);
    }
}
