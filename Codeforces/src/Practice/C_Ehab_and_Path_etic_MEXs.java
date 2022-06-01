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
public class C_Ehab_and_Path_etic_MEXs {
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
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        //No of nodes
        int n=input.scanInt();
        adj_lst=new ArrayList[n];
        for(int i=0;i<adj_lst.length;i++) {
            adj_lst[i]=new ArrayList<Integer>();
        }
        //No of edges
        int query[][]=new int[2][n-1];
        int ans[]=new int[n-1];
        for(int i=0;i<n-1;i++) {
            ans[i]=-1;
            // input u & v
            int u=input.scanInt();
            int v=input.scanInt();
            u--;
            v--;
            query[0][i]=u;
            query[1][i]=v;
            adj_lst[u].add(v);
            adj_lst[v].add(u);
        }
//        for(int i=0;i<adj_lst.length;i++) {
//            System.out.print(i+"->");
//            for(int j=0;j<adj_lst[i].size();j++) {
//                System.out.print(adj_lst[i].get(j)+" ");
//            }
//            System.out.println();
//        }
        boolean is_taken=false;
        int indx=-1;
        for(int i=0;i<n;i++) {
            if(adj_lst[i].size()>2) {
                indx=i;
                break;
            }
        }
        if(indx!=-1) {
            int u1=adj_lst[indx].get(0),u2=adj_lst[indx].get(1),u3=adj_lst[indx].get(2);
            for(int i=0;i<n-1;i++) {
                if(query[0][i]==indx ) {
                    if(query[1][i]==u1) {
                        ans[i]=0;
                    }
                    else if(query[1][i]==u2) {
                        ans[i]=1;
                    }
                    else if(query[1][i]==u3) {
                        ans[i]=2;
                    }
                }
                else if(query[1][i]==indx ) {
                    if(query[0][i]==u1) {
                        ans[i]=0;
                    }
                    else if(query[0][i]==u2) {
                        ans[i]=1;
                    }
                    else if(query[0][i]==u3) {
                        ans[i]=2;
                    }
                }
            }
            for(int i=0,j=3;i<n-1;i++) {
                if(ans[i]==-1) {
                    ans[i]=j;
                    j++;
                }
            }
        }
        else {
            for(int i=0;i<n-1;i++) {
                ans[i]=i;
            }
        }
        for(int i=0;i<n-1;i++) {
            System.out.println(ans[i]);
        }
    }
}
