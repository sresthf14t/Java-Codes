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
public class B_Monopole_Magnets {
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
    static int mark[][];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n=input.scanInt();
        m=input.scanInt();
        arr=new char[n][m];
        vis=new boolean[n][m];
        mark=new int[n][m];
        int cnt=0;
        for(int i=0;i<n;i++) {
            String str=input.scanString();
            for(int j=0;j<m;j++) {
                arr[i][j]=str.charAt(j);
                if(arr[i][j]=='#') {
                    cnt++;
                }
                else {
                    vis[i][j]=true;
                }
                mark[i][j]=-1;
            }
        }
        if(cnt==0) {
            System.out.println(0);
            return;
        }
        int ans=0;
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(!vis[i][j]) {
                    ans++;
                    DFS(i,j,ans);
                }
            }
        }
//        for(int i=0;i<n;i++) {
//            for(int j=0;j<m;j++) {
//                System.out.print(mark[i][j]+" ");
//            }
//            System.out.println();
//        }
        if(!check()) {
            System.out.println(-1);
            return;
        }
        System.out.println(ans);
    }
    public static boolean check() {
        for(int i=0;i<n;i++) {
            int curr=mark[i][0];
            for(int j=1;j<m;j++) {
                if(mark[i][j]!=-1) {
                    if(curr==-1) {
                        curr=mark[i][j];
                    }
                    else if(mark[i][j]!=curr){
                        return false;
                    }
                }
            }
        }
        
        for(int i=0;i<m;i++) {
            int curr=mark[0][i];
            for(int j=1;j<n;j++) {
                if(mark[j][i]!=-1) {
                    if(curr==-1) {
                        curr=mark[j][i];
                    }
                    else if(mark[j][i]!=curr){
                        return false;
                    }
                }
            }
        }
        
        
        for(int i=0;i<n;i++) {
            int cnt=0;
            char curr=arr[i][0];
            if(curr=='#') {
                cnt++;
            }
            for(int j=0;j<m;j++) {
                if(arr[i][j]!=curr) {
                    if(arr[i][j]=='#') {
                        cnt++;
                    }
                    curr=arr[i][j];
                }
            }
            if(cnt>1) {
                return false;
            }
        }
        
        for(int i=0;i<m;i++) {
            int cnt=0;
            char curr=arr[0][i];
            if(curr=='#') {
                cnt++;
            }
            for(int j=0;j<n;j++) {
                if(arr[j][i]!=curr) {
                    if(arr[j][i]=='#') {
                        cnt++;
                    }
                    curr=arr[j][i];
                }
            }
            if(cnt>1) {
                return false;
            }
        }
        
        int cnt1=0,cnt2=0;
        for(int i=0;i<n;i++) {
            boolean is_pos=true;
            for(int j=0;j<m;j++) {
                if(arr[i][j]=='#') {
                    is_pos=false;
                }
            }
            if(is_pos) {
                cnt1++;
            }
        }
        for(int i=0;i<m;i++) {
            boolean is_pos=true;
            for(int j=0;j<n;j++) {
                if(arr[j][i]=='#') {
                    is_pos=false;
                }
            }
            if(is_pos) {
                cnt2++;
            }
        }
//        System.out.println(cnt1+" "+cnt2);
        if(cnt1==0 && cnt2>0) {
            return false;
        }
        if(cnt1>0 && cnt2==0) {
            return false;
        }
        return true;
    }
    public static void DFS(int x,int y,int cnt) {
        Stack<Integer> stckx=new Stack<>();
        Stack<Integer> stcky=new Stack<>();
        stckx.add(x);
        stcky.add(y);
        while(!stckx.isEmpty()) {
            x=stckx.pop();
            y=stcky.pop();
            vis[x][y]=true;
            mark[x][y]=cnt;
            if(x!=0 && !vis[x-1][y]) {
                stckx.add(x-1);
                stcky.add(y);
            }
            if(x!=n-1 && !vis[x+1][y]) {
                stckx.add(x+1);
                stcky.add(y);
            }
            if(y!=0 && !vis[x][y-1]) {
                stckx.add(x);
                stcky.add(y-1);
            }
            if(y!=m-1 && !vis[x][y+1]) {
                stckx.add(x);
                stcky.add(y+1);
            }
        }
    }
}
