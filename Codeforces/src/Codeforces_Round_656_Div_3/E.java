/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_656_Div_3;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class E {
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
    //-1->Black 0->White 1->Grey
    static int color[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int test=input.scanInt();
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            int m=input.scanInt();
            adj_lst=new ArrayList[n];
            vis=new boolean[adj_lst.length];
            color=new int[adj_lst.length];
            for(int i=0;i<adj_lst.length;i++) {
                adj_lst[i]=new ArrayList<Integer>();
            }
            ArrayList<Integer> edge_u=new ArrayList<>();
            ArrayList<Integer> edge_v=new ArrayList<>();
            for(int i=0;i<m;i++) {
                int type=input.scanInt();
                int u=input.scanInt()-1;
                int v=input.scanInt()-1;
                if(type==1) {
                    adj_lst[u].add(v);
                }
                else {
                    edge_u.add(u);
                    edge_v.add(v);
                }
            }
            Stack<Integer> stck=new Stack<>();
            for(int i=0;i<n;i++) {
                if(!vis[i]) {
                    top_sort(i,stck);
                }
            }
            HashMap<Integer,Integer> map=new HashMap<>();
            int indx=0;
            while(!stck.isEmpty()) {
                int ele=stck.pop();
//                System.out.println(ele);
                map.put(ele, indx);
                indx++;
            }
            for(int i=0;i<edge_u.size();i++) {
                int u=map.get(edge_u.get(i));
                int v=map.get(edge_v.get(i));
                if(u<v) {
                    adj_lst[edge_u.get(i)].add(edge_v.get(i));
                }
                else {
                    adj_lst[edge_v.get(i)].add(edge_u.get(i));
                }
            }
            vis=new boolean[n];
            color=new int[n];
            boolean is_pos=true;
            for(int i=0;i<n;i++) {
                if(!vis[i]) {
                    if(mod_DFS(i)) {
                        is_pos=false;
                        break;
                    }
                }
            }
            if(!is_pos) {
                ans.append("NO\n");
                continue;
            }
            ans.append("YES\n");
            for(int i=0;i<n;i++) {
                for(int j=0;j<adj_lst[i].size();j++) {
                    ans.append((i+1)+" "+(adj_lst[i].get(j)+1)+"\n");
                }
            }
        }
        System.out.println(ans);
    }
    public static void top_sort(int root,Stack<Integer> stck) {
//        System.out.println(root);
        vis[root]=true;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(!vis[adj_lst[root].get(i)]) {
                top_sort(adj_lst[root].get(i),stck);
            }
        }
        stck.push(root);
    }
    public static boolean mod_DFS(int root) {
        color[root]=1;
        vis[root]=true;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(color[adj_lst[root].get(i)]==1) {
                return true;
            }
            if(!vis[adj_lst[root].get(i)]) {
                if(mod_DFS(adj_lst[root].get(i))) {
                    return true;
                }
            }
        }
        color[root]=-1;
        return false;
    }
}
