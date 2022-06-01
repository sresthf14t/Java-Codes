/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_648_Div_2;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class D {
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
        int test=input.scanInt();
        for(int t=1;t<=test;t++) {
            n=input.scanInt();
            m=input.scanInt();
            arr=new char[n][m];
            boolean good=false;
            for(int i=0;i<n;i++) {
                String str=input.scanString();
                for(int j=0;j<m;j++) {
                    arr[i][j]=str.charAt(j);
                    if(arr[i][j]=='G') {
                        good=true;
                    }
                }
            }
            if((n==1 && m==1) || !good) {
                System.out.println("YES");
                continue;
            }
            if(n>1 && arr[n-2][m-1]=='B') {
                System.out.println("NO");
                continue;
            }
            if(m>1 && arr[n-1][m-2]=='B') {
                System.out.println("NO");
                continue;
            }
            boolean is_pos=true;
            for(int i=0;i<n;i++) {
                for(int j=0;j<m;j++) {
                    if(arr[i][j]!='B') {
                        continue;
                    }
                    if(i>0 && arr[i-1][j]=='G') {
                        is_pos=false;
                        break;
                    }
                    if(i<n-1 && arr[i+1][j]=='G') {
                        is_pos=false;
                        break;
                    }
                    
                    if(j>0 && arr[i][j-1]=='G') {
                        is_pos=false;
                        break;
                    }
                    if(j<m-1 && arr[i][j+1]=='G') {
                        is_pos=false;
                        break;
                    }
                }
                if(!is_pos) {
                    break;
                }
            }
            if(!is_pos) {
                System.out.println("NO");
                continue;
            }
            for(int i=0;i<n;i++) {
                for(int j=0;j<m;j++) {
                    if(arr[i][j]!='B') {
                        continue;
                    }
                    if(i>0 && arr[i-1][j]=='.') {
                        arr[i-1][j]='#';
                    }
                    if(i<n-1 && arr[i+1][j]=='.') {
                        arr[i+1][j]='#';
                    }
                    
                    if(j>0 && arr[i][j-1]=='.') {
                        arr[i][j-1]='#';
                    }
                    if(j<m-1 && arr[i][j+1]=='.') {
                        arr[i][j+1]='#';
                    }
                }
            }
            boolean done=false;
            vis=new boolean[n][m];
            for(int i=0;i<n;i++) {
                for(int j=0;j<m;j++) {
                    if(arr[i][j]!='G') {
                        continue;
                    }
                    can_reach(i,j,arr[i][j]);
                    done=true;
                    break;
                }
                if(done) {
                    break;
                }
            }
//            for(int i=0;i<n;i++) {
//                for(int j=0;j<m;j++) {
//                    System.out.print(vis[i][j]?1:0);
//                    System.out.print(" ");
//                }
//                System.out.println();
//            }
            if(!vis[n-1][m-1]) {
                System.out.println("NO");
                continue;
            }
            for(int i=0;i<n;i++) {
                for(int j=0;j<m;j++) {
                    if(arr[i][j]=='G' && !vis[i][j]) {
                        is_pos=false;
                        break;
                    }
                }
                if(!is_pos) {
                    break;
                }
            }
            if(!is_pos) {
                System.out.println("NO");
                continue;
            }
            vis=new boolean[n][m];
            for(int i=0;i<n;i++) {
                for(int j=0;j<m;j++) {
                    if(arr[i][j]!='B' || vis[i][j]) {
                        continue;
                    }
                    vis=new boolean[n][m];
                    if(can_reach_bad(i,j,arr[i][j])) {
                        is_pos=false;
                        break;
                    }
                }
                if(!is_pos) {
                    break;
                }
            }
            if(!is_pos) {
                System.out.println("NO");
                continue;
            }
            
            
            System.out.println("YES");
        }
    }
    public static void can_reach(int i,int j, char chr) {
        vis[i][j]=true;
        if(i!=0 && !vis[i-1][j]) {
            if('G'==arr[i-1][j] || arr[i-1][j]=='.') {
                can_reach(i-1,j,chr);
            }
        }
        
        if(i!=n-1 && !vis[i+1][j]) {
            if('G'==arr[i+1][j] || arr[i+1][j]=='.') {
                can_reach(i+1,j,chr);
            }
        }
        
        if(j!=0 && !vis[i][j-1]) {
            if('G'==arr[i][j-1] || arr[i][j-1]=='.') {
                can_reach(i,j-1,chr);
            }
        }
        
        if(j!=m-1 && !vis[i][j+1]) {
            if('G'==arr[i][j+1] || arr[i][j+1]=='.') {
                can_reach(i,j+1,chr);
            }
        }
    }
    
    
    public static boolean can_reach_bad(int i,int j, char chr) {
        if(i==n-1 && j==m-1) {
            return true;
        }
        vis[i][j]=true;
        if(i!=0 && !vis[i-1][j]) {
            if('B'==arr[i-1][j] || arr[i-1][j]=='G') {
                if(can_reach_bad(i-1,j,chr)) {
                    return true;
                }
            }
        }
        
        if(i!=n-1 && !vis[i+1][j]) {
            if('B'==arr[i+1][j] || arr[i+1][j]=='G') {
                if(can_reach_bad(i+1,j,chr)) {
                    return true;
                }
            }
        }
        
        if(j!=0 && !vis[i][j-1]) {
            if('B'==arr[i][j-1] || arr[i][j-1]=='G') {
                if(can_reach_bad(i,j-1,chr)) {
                    return true;
                }
            }
        }
        
        if(j!=m-1 && !vis[i][j+1]) {
            if('B'==arr[i][j+1] || arr[i][j+1]=='G') {
                if(can_reach_bad(i,j+1,chr)) {
                    return true;
                }
            }
        }
        return false;
    }
}
