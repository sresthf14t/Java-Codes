/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AtCoder_Beginner_Contest_173;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class C {
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
    static int n,m,k;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n=input.scanInt();
        m=input.scanInt();
        k=input.scanInt();
        char arr[][]=new char[n][m];
        for(int i=0;i<n;i++) {
            String tmp=input.scanString();
            for(int j=0;j<m;j++) {
                arr[i][j]=tmp.charAt(j);
            }
        }
        System.out.println(solve_row(0,arr));
    }
    public static int solve_row(int r,char arr[][]) {
        if(r>=n) {
            return solve_col(0,arr);
        }
        int cnt=0;
        cnt+=solve_row(r+1,arr);
        char tmp[][]=new char[n][m];
        copy(arr,tmp);
        color_row(r,tmp);
        cnt+=solve_row(r+1,tmp);
        return cnt;
    }
    public static int solve_col(int c,char arr[][]) {
        if(c>=m) {
            if(check(arr)) {
                return 1;
            }
            return 0;
        }
        int cnt=0;
        cnt+=solve_col(c+1,arr);
        char tmp[][]=new char[n][m];
        copy(arr,tmp);
        color_col(c,tmp);
        cnt+=solve_col(c+1,tmp);
        return cnt;
    }
    public static void color_row(int indx,char arr[][]) {
        for(int i=0;i<m;i++) {
            arr[indx][i]='R';
        }
    }
    public static void color_col(int indx,char arr[][]) {
        for(int i=0;i<n;i++) {
            arr[i][indx]='R';
        }
    }
    public static boolean check(char arr[][]) {
        int cnt=0;
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(arr[i][j]=='#') {
                    cnt++;
                }
            }
        }
        if(cnt==k) {
            return true;
        }
        return false;
    }
    public static void copy(char ori[][],char tmp[][]) {
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                tmp[i][j]=ori[i][j];
            }
        }
    }
}
