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
import java.util.*;
import java.io.*;
public class C_Fox_And_Names {
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
    static int parent[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int n=input.scanInt();
        String str[]=new String[n];
        for(int i=0;i<n;i++) {
            str[i]=input.scanString();
        }
        adj_lst=new ArrayList[26];
        vis=new boolean[26];
        color=new int[26];
        parent=new int[26];
        for(int i=0;i<26;i++) {
            adj_lst[i]=new ArrayList<Integer>();
            parent[i]=-1;
        }
        boolean is_pos=true;
        for(int i=0;i<n-1;i++) {
            int len=Math.min(str[i].length(),str[i+1].length());
            is_pos=false;
            for(int j=0;j<len;j++) {
                if(str[i].charAt(j)!=str[i+1].charAt(j)) {
                    adj_lst[str[i].charAt(j)-97].add(str[i+1].charAt(j)-97);
                    is_pos=true;
                    break;
                }
            }
            if(!is_pos) {
                if(str[i].length()>str[i+1].length()) {
                    break;
                }
                else {
                    is_pos=true;
                }
            }
        }
        if(!is_pos) {
            System.out.println("Impossible");
            System.exit(0);
        }
        for(int i=0;i<26;i++) {
            if(!vis[i]) {
                if(mod_DFS(i)) {
                    System.out.println("Impossible");
                    System.exit(0);
                }
            }
        }
        vis=new boolean[26];
        Stack<Integer> stck=new Stack<>();
        for(int i=0;i<26;i++) {
            if(!vis[i]) {
                top_sort(i,stck);
            }
        }
        while(!stck.isEmpty()) {
            System.out.print((char)(97+stck.pop()));
        }
        System.out.println();
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
}
