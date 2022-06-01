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
public class C_The_Labyrinth {
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
    static int color[][];
    static boolean vis[][];
    static char arr[][];
    static int n,m;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n=input.scanInt();
        m=input.scanInt();
        arr=new char[n][m];
        color=new int[n][m];
        vis=new boolean[n][m];
        for(int i=0;i<n;i++) {
            String str=input.scanString();
            for(int j=0;j<m;j++) {
                arr[i][j]=str.charAt(j);
            }
        }
        int clr=1;
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(arr[i][j]=='*' || vis[i][j]) {
                    continue;
                }
                DFS(i,j,clr);
                clr++;
            }
        }
//        for(int i=0;i<n;i++) {
//            for(int j=0;j<m;j++) {
//                System.out.print(color[i][j]);
//            }
//            System.out.println();
//        }
//        System.out.println();
        int clr_cnt[]=new int[clr+1];
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                clr_cnt[color[i][j]]++;
            }
        }
        clr_cnt[0]=0;
        StringBuilder ans=new StringBuilder("");
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(arr[i][j]=='.') {
                    ans.append('.');
                    continue;
                }
                ans.append(check(i,j,clr_cnt));
            }
            ans.append("\n");
        }
        System.out.println(ans);
    }
    public static int check(int i,int j,int clr_cnt[]) {
        int cnt=0;
        Set<Integer> hashset=new HashSet<>();
        if(i>0 && !hashset.contains(color[i-1][j])) {
            cnt+=clr_cnt[color[i-1][j]];
            hashset.add(color[i-1][j]);
        }
        if(i<n-1 && !hashset.contains(color[i+1][j])) {
            cnt+=clr_cnt[color[i+1][j]];
            hashset.add(color[i+1][j]);
        }
        if(j>0 && !hashset.contains(color[i][j-1])) {
            cnt+=clr_cnt[color[i][j-1]];
            hashset.add(color[i][j-1]);
        }
        if(j<m-1 && !hashset.contains(color[i][j+1])) {
            cnt+=clr_cnt[color[i][j+1]];
            hashset.add(color[i][j+1]);
        }
        return (cnt+1)%10;
    }
    public static void DFS(int i,int j,int clr) {
        color[i][j]=clr;
        vis[i][j]=true;
        if(i>0 && !vis[i-1][j] && arr[i-1][j]=='.') {
            DFS(i-1,j,clr);
        }
        if(i<n-1 && !vis[i+1][j] && arr[i+1][j]=='.') {
            DFS(i+1,j,clr);
        }
        if(j>0 && !vis[i][j-1] && arr[i][j-1]=='.') {
            DFS(i,j-1,clr);
        }
        if(j<m-1 && !vis[i][j+1] && arr[i][j+1]=='.') {
            DFS(i,j+1,clr);
        }
    }
}
