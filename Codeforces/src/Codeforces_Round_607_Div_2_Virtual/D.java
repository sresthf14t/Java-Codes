/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_607_Div_2_Virtual;

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
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int test=input.scanInt();
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            int m=input.scanInt();
            char arr[][]=new char[n][m];
            for(int i=0;i<n;i++) {
                String str=input.scanString();
                for(int j=0;j<m;j++) {
                    arr[i][j]=str.charAt(j);
                }
            }
            int tmp=solve(n,m,arr);
            if(tmp==-1) {
                ans.append("MORTAL");
            }
            else {
                ans.append(tmp);
            }
            ans.append("\n");
        }
        System.out.println(ans);
    }
    public static int solve(int n,int m,char arr[][]) {
        int min_i=Integer.MAX_VALUE,max_i=Integer.MIN_VALUE,min_j=Integer.MAX_VALUE,max_j=Integer.MIN_VALUE;
        boolean mortal=true;
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(arr[i][j]=='A') {
                    mortal=false;
                    continue;
                }
                min_i=Math.min(min_i,i);
                max_i=Math.max(max_i,i);
                min_j=Math.min(min_j,j);
                max_j=Math.max(max_j,j);
            }
        }
        if(mortal) {
            return -1;
        }
        if(min_i==Integer.MAX_VALUE) {
            return 0;
        }
        int min=4;
        for(int i=0;i<m;i++) {
            boolean is_pos=true;
            for(int j=min_i;j<=max_i;j++) {
                if(arr[j][i]=='P') {
                    is_pos=false;
                    break;
                }
            }
            if(!is_pos) {
                continue;
            }
            if(i<=min_j || i>=max_j) {
                min=Math.min(min,1);
            }
            else {
                min=Math.min(min,2);
            }
        }
//        System.out.println(min_i+" "+min_j+" "+max_i+" "+max_j);
        for(int i=0;i<n;i++) {
            boolean is_pos=true;
            for(int j=min_j;j<=max_j;j++) {
                if(arr[i][j]=='P') {
                    is_pos=false;
                    break;
                }
            }
            if(!is_pos) {
                continue;
            }
            if(i<=min_i || i>=max_i) {
                min=Math.min(min,1);
            }
            else {
                min=Math.min(min,2);
            }
        }
        if(arr[0][0]=='A' || arr[0][m-1]=='A' || arr[n-1][0]=='A' || arr[n-1][m-1]=='A') {
            min=Math.min(min,2);
        }
        
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(arr[i][j]=='P') {
                    continue;
                }
                if((i<=min_i || i>=max_i) && (j<=min_j && j>=max_j)) {
                    min=Math.min(min,2);
                }
                else if((i<=min_i || i>=max_i) || (j<=min_j && j>=max_j)) {
                    min=Math.min(min,3);
                }
            }
        }
        for(int i=min_i;i<=max_i;i++) {
            for(int j=min_j;j<=max_j;j++) {
                if((i==min_i || i==max_i) || (j==min_j || j==max_j)) {
                    if(arr[i][j]=='A') {
                        min=Math.min(min,3);
                    }
                }
                
            }
        }
        return min;
    }
}
