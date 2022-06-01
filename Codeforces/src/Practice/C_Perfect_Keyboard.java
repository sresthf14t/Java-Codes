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
public class C_Perfect_Keyboard {
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
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int test=input.scanInt();
        for(int tt=1;tt<=test;tt++) {
            String str=input.scanString();
            adj_lst=new ArrayList[26];
            for(int i=0;i<26;i++) {
                adj_lst[i]=new ArrayList<>();
            }
            for(int i=0;i<str.length()-1;i++) {
                if(!adj_lst[str.charAt(i)-'a'].contains(str.charAt(i+1)-'a')) {
                    adj_lst[str.charAt(i)-'a'].add(str.charAt(i+1)-'a');
                }
                if(!adj_lst[str.charAt(i+1)-'a'].contains(str.charAt(i)-'a')) {
                    adj_lst[str.charAt(i+1)-'a'].add(str.charAt(i)-'a');
                }
            }
            vis=new boolean[adj_lst.length];
            boolean is_pos=true;
            for(int i=0;i<adj_lst.length;i++) {
//                System.out.println(i+" "+adj_lst[i].size());
                if(adj_lst[i].size()>2) {
                    is_pos=false;
                    break;
                }
            }
            if(!is_pos) {
                System.out.println("NO");
                continue;
            }
            ArrayList<Integer> ans=new ArrayList<>();
            for(int i=0;i<26;i++) {
                if(adj_lst[i].size()==0) {
                    ans.add(i);
                    vis[i]=true;
                    continue;
                }
                if(!vis[i] && adj_lst[i].size()==1) {
                    DFS(i,ans);
                } 
            }
            if(ans.size()!=26) {
                System.out.println("NO");
                continue;
            }
            System.out.println("YES");
            for(int i=0;i<ans.size();i++) {
                System.out.print((char)(ans.get(i)+'a'));
            }
            System.out.println();
        }
    }
    public static void DFS(int root,ArrayList<Integer> tmp) {
        vis[root]=true;
        tmp.add(root);
        for(int i=0;i<adj_lst[root].size();i++) {
            if(!vis[adj_lst[root].get(i)]) {
                DFS(adj_lst[root].get(i),tmp);
            }
        }
    }
}
