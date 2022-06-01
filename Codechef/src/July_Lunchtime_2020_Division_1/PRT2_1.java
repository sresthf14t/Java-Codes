/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package July_Lunchtime_2020_Division_1;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class PRT2_1 {
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
    static long arr[];
    static int n,k;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int test=input.scanInt();
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            n=input.scanInt();
            k=input.scanInt();
            adj_lst=new ArrayList[n];
            for(int i=0;i<n;i++) {
                adj_lst[i]=new ArrayList<>();
            }
            arr=new long[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt();
            }
            for(int i=0;i<n-1;i++) {
                int u=input.scanInt()-1;
                int v=input.scanInt()-1;
                adj_lst[u].add(v);
                adj_lst[v].add(u);
            }
            ans.append(solve()+"\n");
        }
        System.out.println(ans);
    }
    public static long solve() {
        long ans=0;
        Arrays.sort(arr);
        if(n>2) {
            int zero=0,one=0,two=0;
            int root=1,prev=0;
            System.out.println("\n");
            for(int i=0;i<k-1;i++) {
//                System.out.println(i+" "+root+" "+prev);
                if(root==1 && prev==0) {
                    one++;
                    root=2;
                    prev=1;
                }
                else if(root==1 && prev==2) {
                    one++;
                    root=0;
                    prev=1;
                }
                else if(root==0 && prev==1) {
                    zero++;
                    root=0;
                    prev=-1;
                }
                else if(root==0 && prev==-1) {
                    zero++;
                    root=1;
                    prev=0;
                }
                else if(root==2 && prev==1) {
                    two++;
                    root=2;
                    prev=-1;
                }
                else if(root==2 && prev==-1) {
                    two++;
                    root=1;
                    prev=2;
                }
            }
            long tmp[]={zero,one,two};
            tmp[root]++;
            Arrays.sort(tmp);
//            System.out.println(tmp[0]+" "+tmp[1]+" "+tmp[2]);
            return (arr[n-3]*tmp[0])+(arr[n-2]*tmp[1])+(arr[n-1]*tmp[2]);
        }
        int root=0,prev=-1;
        long tmp[]=new long[2];
        for(int i=0;i<k-1;i++) {
            tmp[root]++;
            if(root==0 && prev==-1) {
                root=1;
                prev=0;
            }
            else if(root==0 && prev==1) {
                root=0;
                prev=-1;
            }
            else if(root==1 && prev==-1) {
                root=0;
                prev=1;
            }
            else if(root==1 && prev==0) {
                root=1;
                prev=-1;
            }
        }
        tmp[root]++;
        Arrays.sort(tmp);
        ans=(tmp[0]*arr[0])+(tmp[1]*arr[1]);
        return ans;
    } 
}
