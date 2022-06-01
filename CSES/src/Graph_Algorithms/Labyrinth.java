/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph_Algorithms;

/**
 *
 * @author User
 */
import java.io.*; 
import java.util.*; 
public class Labyrinth {
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
    static StringBuilder ans;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n=input.scanInt();
        m=input.scanInt();
        arr=new char[n][m];
        vis=new boolean[n][m];
        for(int i=0;i<n;i++) {
            String str=input.scanString();
            for(int j=0;j<m;j++) {
                arr[i][j]=str.charAt(j);
                if(arr[i][j]=='#') {
                    vis[i][j]=true;
                }
            }
        }
        boolean done=false;
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(arr[i][j]=='A') {
                    DFS(i,j,new StringBuilder(""),0);
                    done=true;
                    break;
                }
            }
            if(done) {
                break;
            }
        }
        if(ans==null) {
            System.out.println("NO");
        }
        else {
            System.out.println("YES");
            System.out.println(ans.length()+"\n"+ans);
        }
    }
    public static void DFS(int i,int j,StringBuilder tmp,int dep) {
        if(arr[i][j]=='B') {
            if(ans==null || tmp.length()<ans.length()) {
                ans=new StringBuilder(tmp);
            }
            return;
        }
        if(ans!=null && dep>ans.length()) {
            return;
        }
        vis[i][j]=true;   
        if(i>0 && !vis[i-1][j]) {
            tmp.append("U");
            DFS(i-1,j,tmp,dep+1);
            tmp.deleteCharAt(tmp.length()-1);
        }
        if(i<n-1 && !vis[i+1][j]) {
            tmp.append("D");
            DFS(i+1,j,tmp,dep+1);
            tmp.deleteCharAt(tmp.length()-1);
        } 
        if(j>0 && !vis[i][j-1]) {
            tmp.append("L");
            DFS(i,j-1,tmp,dep+1);
            tmp.deleteCharAt(tmp.length()-1);
        }
        if(j<m-1 && !vis[i][j+1]) {
            tmp.append("R");
            DFS(i,j+1,tmp,dep+1);
            tmp.deleteCharAt(tmp.length()-1);
        }
        vis[i][j]=false;
    }
}
