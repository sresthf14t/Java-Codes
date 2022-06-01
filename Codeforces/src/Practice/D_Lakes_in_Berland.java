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
public class D_Lakes_in_Berland {
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
    static int n,m;
    static char arr[][];
    static boolean vis[][];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n=input.scanInt();
        m=input.scanInt();
        int k=input.scanInt();
        arr=new char[n][m];
        vis=new boolean[n][m];
        for(int i=0;i<n;i++) {
            String str=input.scanString();
            for(int j=0;j<m;j++) {
                arr[i][j]=str.charAt(j);
                if(arr[i][j]=='*') {
                    vis[i][j]=true;
                }
            }
        }
        for(int i=0;i<n;i++) {
            if(!vis[i][0]) {
                DFS(i,0,false);
            }
            if(!vis[i][m-1]) {
                DFS(i,m-1,false);
            }
        }
        for(int i=0;i<m;i++) {
            if(!vis[0][i]) {
                DFS(0,i,false);
            }
            if(!vis[n-1][i]) {
                DFS(n-1,i,false);
            }
        }
        ArrayList<Integer> arrli=new ArrayList<>();
        ArrayList<Integer> indx_x=new ArrayList<>();
        ArrayList<Integer> indx_y=new ArrayList<>();
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(!vis[i][j]) {
                    arrli.add(DFS(i,j,false));
                    indx_x.add(i);
                    indx_y.add(j);
                }
            }
        }
        vis=new boolean[n][m];
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(arr[i][j]=='*') {
                    vis[i][j]=true;
                }
            }
        }
        int sum=0;
        for(int i=0;i<arrli.size()-k;i++) {
            int min=Integer.MAX_VALUE,indx=-1;
            for(int j=0;j<arrli.size();j++) {
                if(arrli.get(j)<min) {
                    min=arrli.get(j);
                    indx=j;
                }
            }
            sum+=min;
            arrli.set(indx, Integer.MAX_VALUE);
            DFS(indx_x.get(indx),indx_y.get(indx),true);
        }
        System.out.println(sum);
        StringBuilder ans=new StringBuilder("");
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                ans.append(arr[i][j]);
            }
            ans.append("\n");
        }
        System.out.println(ans);
    }
    public static int DFS(int i,int j,boolean chng) {
        if(chng) {
            arr[i][j]='*';
        }
        vis[i][j]=true;
        int cnt=1;
        if(i!=0 && !vis[i-1][j]) {
            cnt+=DFS(i-1,j,chng);
        }
        if(i!=n-1 && !vis[i+1][j]) {
            cnt+=DFS(i+1,j,chng);
        }
        
        if(j!=0 && !vis[i][j-1]) {
            cnt+=DFS(i,j-1,chng);
        }
        if(j!=m-1 && !vis[i][j+1]) {
            cnt+=DFS(i,j+1,chng);
        }
        return cnt;
    }
}
