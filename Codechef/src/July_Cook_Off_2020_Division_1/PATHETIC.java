/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package July_Cook_Off_2020_Division_1;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class PATHETIC {
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
    static int depth[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int test=input.scanInt();
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            adj_lst=new ArrayList[n];
            depth=new int[n];
            for(int i=0;i<n;i++) {
                adj_lst[i]=new ArrayList<>();
            }
            for(int i=0;i<n-1;i++) {
                int u=input.scanInt()-1;
                int v=input.scanInt()-1;
                adj_lst[u].add(v);
                adj_lst[v].add(u);
            }
            DFS(0,-1,0);
            long arr[]={1724869837681984L,7846028966259L,5151734590925L};
            for(int i=0;i<n;i++) {
                ans.append((arr[depth[i]%3]*6)+" ");
            }
            ans.append("\n");
        }
        System.out.println(ans);
    }
    public static void DFS(int root,int par,int dep) {
        depth[root]=dep;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(adj_lst[root].get(i)==par) {
                continue;
            }
            DFS(adj_lst[root].get(i),root,dep+1);
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static void solve() {
        ArrayList<Integer> arr=new ArrayList<>();
        for(int i=2;i<=100;i++) {
            if(is_prime(i)) {
                arr.add(i);
            }
        }
        int count[]=new int[arr.size()];
        long ans1=1,ans2=1,ans3=1;
        for(int i=2;i<=100;i++) {
            int tmp=i;
            for(int j=0;j<arr.size();j++) {
                int cnt=0;
                while(tmp%arr.get(j)==0) {
                    tmp/=arr.get(j);
                    cnt++;
                }
                count[j]=Math.max(count[j],cnt);
            }
        }
        for(int i=0;i<arr.size();i++) {
            if(i%3==0) {
                ans1*=(long)Math.pow(arr.get(i),count[i]);
            }
            else if(i%3==1) {
                ans2*=(long)Math.pow(arr.get(i),count[i]);
            }
            else {
                ans3*=(long)Math.pow(arr.get(i),count[i]);
            }
//            System.out.println(ans1+" "+ans2);
//            System.out.println(arr.get(i)+" "+count[i]);
        }
        System.out.println(ans1);
        System.out.println(ans2);
        System.out.println(ans3);
    }
    public static boolean is_prime(int n) {
        for(int i=2;i<n;i++) {
            if(n%i==0) {
                return false;
            }
        }
        return true;
    }
}
