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
public class C_Ice_Cave {
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
        arr=new char[n][m];
        vis=new boolean[n][m];
        for(int i=0;i<n;i++) {
            String str=input.scanString();
            for(int j=0;j<m;j++) {
                arr[i][j]=str.charAt(j);
                if(arr[i][j]=='X') {
                    vis[i][j]=true;
                }
            }
        }
        int x1=input.scanInt()-1;
        int y1=input.scanInt()-1;
        int x2=input.scanInt()-1;
        int y2=input.scanInt()-1;
        char ori=arr[x2][y2];
        arr[x2][y2]='.';
        vis[x2][y2]=false;
        DFS(x1,y1);
        if(!vis[x2][y2]) {
            System.out.println("NO");
            return;
        }
        int cnt=0;
        if(x2!=0 && arr[x2-1][y2]=='.') {
            cnt++;
        }
        if(x2!=n-1 && arr[x2+1][y2]=='.') {
            cnt++;
        }
        if(y2!=0 && arr[x2][y2-1]=='.') {
            cnt++;
        }
        if(y2!=m-1 && arr[x2][y2+1]=='.') {
            cnt++;
        }
        if(Math.abs(x1-x2)==1 && y1==y2) {
            cnt++;
        }
        if(Math.abs(y1-y2)==1 && x1==x2) {
            cnt++;
        }
//        System.out.println(cnt);
        if(x1==x2 && y1==y2) {
            if(cnt==0) {
                System.out.println("NO");
            }
            else {
                System.out.println("YES");
            }
        }
        else if(ori=='X') {
            System.out.println("YES");
        }
        else if(cnt>1) {
            System.out.println("YES");
        }
        else {
            System.out.println("NO");
        }
    }
    public static void DFS(int i,int j) {
        vis[i][j]=true;
        if(i!=0 && !vis[i-1][j]) {
            DFS(i-1,j);
        }
        if(i!=n-1 && !vis[i+1][j]) {
            DFS(i+1,j);
        }
        if(j!=0 && !vis[i][j-1]) {
            DFS(i,j-1);
        }
        if(j!=m-1 && !vis[i][j+1]) {
            DFS(i,j+1);
        }
    }
}
