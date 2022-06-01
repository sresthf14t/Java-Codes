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
public class B_The_least_round_way {
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
        int n=input.scanInt();
        int arr[][]=new int[n][n];
        boolean zero=false;
        int r=-1,c=-1;
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                arr[i][j]=input.scanInt();
                if(arr[i][j]==0) {
                    zero=true;
                    r=i;
                    c=j;
                }
            }
        }
        solve(arr,n,zero,r,c);
    }
    public static void solve(int arr[][],int n,boolean zero,int r,int c) {
        int two[][]=new int[n][n];
        int five[][]=new int[n][n];
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                two[i][j]=factorize(arr[i][j],2);
                five[i][j]=factorize(arr[i][j],5);
            }
        }
        int dp2[][]=new int[n][n];
        int dp5[][]=new int[n][n];
        dp2[0][0]=two[0][0];
        dp5[0][0]=five[0][0];
        for(int i=1;i<n;i++) {
            dp2[0][i]=dp2[0][i-1]+two[0][i];
            dp5[0][i]=dp5[0][i-1]+five[0][i];
            
            dp2[i][0]=dp2[i-1][0]+two[i][0];
            dp5[i][0]=dp5[i-1][0]+five[i][0];
        }
        StringBuilder ans=new StringBuilder("");
        for(int i=1;i<n;i++) {
            for(int j=1;j<n;j++) {
                dp2[i][j]=Math.min(dp2[i-1][j],dp2[i][j-1])+two[i][j];
                dp5[i][j]=Math.min(dp5[i-1][j],dp5[i][j-1])+five[i][j];
            }
        }
        int count;
        if(dp2[n-1][n-1]<dp5[n-1][n-1]) {
            int i,j;
            i=j=n-1;
            count=dp2[i][j];
            while(i!=0 || j!=0) {
                if(i==0) {
                    while(j!=0) {
                        j--;
                        ans.append('R');
                    }
                }
                else if(j==0) {
                    while(i!=0) {
                        i--;
                        ans.append('D');
                    }
                }
                else {
                    if(dp2[i-1][j]<dp2[i][j-1]) {
                        ans.append('D');
                        i--;
                    }
                    else {
                        ans.append('R');
                        j--;
                    }
                }
            }
        }
        else {
            int i,j;
            i=j=n-1;
            count=dp5[i][j];
            while(i!=0 || j!=0) {
                if(i==0) {
                    while(j!=0) {
                        j--;
                        ans.append('R');
                    }
                }
                else if(j==0) {
                    while(i!=0) {
                        i--;
                        ans.append('D');
                    }
                }
                else {
                    if(dp5[i-1][j]<dp5[i][j-1]) {
                        ans.append('D');
                        i--;
                    }
                    else {
                        ans.append('R');
                        j--;
                    }
                }
            }
        }
        if(zero && count>1) {
            ans=new StringBuilder("");
            for(int i=0;i<c;i++) {
                ans.append("R");
            }
            for(int i=0;i<n-1;i++) {
                ans.append("D");
            }
            for(int i=c;i<n-1;i++) {
                ans.append("R");
            }
            System.out.println(1+"\n"+ans);
            return;
        }
        ans.reverse();
        System.out.println(count+"\n"+ans);
    }
    public static int factorize(int n,int prime) {
        if(n==0) {
            return 0;
        }
        int count=0;
        while(n%prime==0) {
            n/=prime;
            count++;
        }
        return count;
    }
}
