/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Introductory_Problems;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class Grid_Paths {
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
    static char arr[];
    static boolean vis[][];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        String str=input.scanString();
        arr=str.toCharArray();
        vis=new boolean[7][7];
        System.out.println(solve(0,0,0,-1,-1));
    }
    public static int solve(int indx,int x,int y,int prev_x,int prev_y) {
        if(x<0 || y<0 || x>6 || y>6) {
            return 0;
        }
        if(vis[x][y]) {
            return 0;
        }
        if(vis[5][0] && vis[6][1] && !(x==6 && y==0)) {
            return 0;
        }
        vis[x][y]=true;
        if(indx==arr.length) {
            vis[x][y]=false;
            if(x==6 && y==0) {
                return 1;
            }
            return 0;
        }
        if(x==0 || x==6) {
            if(y!=0 && y!=6) {
                if(!vis[x][y-1] && !vis[x][y+1]) {
                    vis[x][y]=false;
                    return 0;
                }
            }
        }
        if(y==0 || y==6) {
            if(x!=0 && x!=6) {
                if(!vis[x-1][y] && !vis[x+1][y]) {
                    vis[x][y]=false;
                    return 0;
                }
            }
        }
        if(vis[6][0]) {
            vis[x][y]=false;
            return 0;
        }
        if(vis[0][6] && !vis[1][6] && !vis[0][5]) {
            vis[x][y]=false;
            return 0;
        }
        if(vis[6][6] && !vis[5][6] && !vis[6][5]) {
            vis[x][y]=false;
            return 0;
        }
        int cnt=0;
        if(arr[indx]=='U') {
            cnt+=solve(indx+1,x-1,y,x,y);
        }
        else if(arr[indx]=='D') {
            cnt+=solve(indx+1,x+1,y,x,y);
        }
        else if(arr[indx]=='L') {
            cnt+=solve(indx+1,x,y-1,x,y);
        }
        else if(arr[indx]=='R') {
            cnt+=solve(indx+1,x,y+1,x,y);
        }
        else {
            if(x>prev_x) {
                if(x!=6 && vis[x+1][y]) {
                    if(y!=0 && y!=6 && !vis[x][y-1] && !vis[x][y+1]) {
                        vis[x][y]=false;
                        return 0;
                    } 
                } 
            }
            if(x<prev_x) {
                if(x!=0 && vis[x-1][y]) {
                    if(y!=0 && y!=6 && !vis[x][y-1] && !vis[x][y+1]) {
                        vis[x][y]=false;
                        return 0;
                    } 
                } 
            }
            
            if(y>prev_y) {
                if(y!=6 && vis[x][y+1]) {
                    if(x!=0 && x!=6 && !vis[x-1][y] && !vis[x+1][y]) {
                        vis[x][y]=false;
                        return 0;
                    } 
                } 
            }
            if(y<prev_y) {
                if(y!=0 && vis[x][y-1]) {
                    if(x!=0 && x!=6 && !vis[x-1][y] && !vis[x+1][y]) {
                        vis[x][y]=false;
                        return 0;
                    } 
                } 
            }
            if(x!=6 && !vis[x+1][y]) {
                cnt+=solve(indx+1,x+1,y,x,y);
            }
            if(x!=0 && !vis[x-1][y]) {
                cnt+=solve(indx+1,x-1,y,x,y);
            }
            if(y!=6 && !vis[x][y+1]) {
                cnt+=solve(indx+1,x,y+1,x,y);
            }
            if(y!=0 && !vis[x][y-1]) {
                cnt+=solve(indx+1,x,y-1,x,y);
            }
        }
        vis[x][y]=false;
        return cnt;
    }
}
