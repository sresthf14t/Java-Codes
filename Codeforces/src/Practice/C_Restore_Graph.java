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
public class C_Restore_Graph {
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
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int n=input.scanInt();
        int k=input.scanInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
        }
        solve(n,k,arr);
    }
    public static void solve(int n,int k,int arr[]) {
        ArrayList<Integer> arrli[]=new ArrayList[100000];
        for(int i=0;i<arrli.length;i++) {
            arrli[i]=new ArrayList<>();
        }
        for(int i=0;i<n;i++) {
            arrli[arr[i]].add(i);
        }
        if(arrli[0].size()!=1) {
            System.out.println(-1);
            return;
        }
        Arrays.sort(arr);
        for(int i=0;i<n-1;i++) {
            if(arr[i+1]-arr[i]>1) {
                System.out.println(-1);
                return;
            }
        }
        ArrayList<Integer> adj_lst[];
        adj_lst=new ArrayList[n];
        for(int i=0;i<adj_lst.length;i++) {
            adj_lst[i]=new ArrayList<Integer>();
        }
        for(int i=0;i<arrli.length-1;i++) {
            if(arrli[i].size()==0) {
                continue;
            }
            int j=i+1;
            while(j<arrli.length && arrli[j].size()==0) {
                j++;
            }
            if(j==arrli.length) {
                break;
            }
//            System.out.println("TST "+i+" "+j);
            add_edge(arrli,adj_lst,i,j);
            i=j-1;
        }
        int count=0;
        StringBuilder ans=new StringBuilder("");
        Set<String> hashset=new HashSet<>();
        for(int i=0;i<adj_lst.length;i++) {
            if(adj_lst[i].size()>k) {
                System.out.println(-1);
                return;
            }
            for(int j=0;j<adj_lst[i].size();j++) {
                if(!hashset.contains((adj_lst[i].get(j)+1)+" "+(i+1))) {
                    count++;
                    ans.append((i+1)+" "+(adj_lst[i].get(j)+1)+"\n");
                    hashset.add((i+1)+" "+(adj_lst[i].get(j)+1));
                }
            }
        }
        System.out.println(count+"\n"+ans);
    }
    public static void add_edge(ArrayList<Integer> arrli[],ArrayList<Integer> adj_lst[],int indx1,int indx2) {
        for(int i=0;i<arrli[indx2].size();i++) {
            adj_lst[arrli[indx1].get(i%arrli[indx1].size())].add(arrli[indx2].get(i));
            adj_lst[arrli[indx2].get(i)].add(arrli[indx1].get(i%arrli[indx1].size()));
        }
    }
}
