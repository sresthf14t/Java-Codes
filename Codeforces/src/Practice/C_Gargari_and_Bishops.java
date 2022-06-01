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
public class C_Gargari_and_Bishops {
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
        long arr[][]=new long[n][n];
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                arr[i][j]=input.scanInt();
            }
        }
        if(n%2==0) {
            solve_0(n,arr);
        }
        else {
            solve_0(n,arr);
        }
    }
    public static void solve_0(int n,long arr[][]) {
        long sum[][]=sum(n,arr);
        long max_1=-1,max_2=-1;
        int x1,y1,x2,y2;
        x1=y1=x2=y2=-1;
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(j%2==i%2) {
                    if(sum[i][j]>max_1) {
                        max_1=sum[i][j];
                        x1=i;
                        y1=j;
                    }
                }
                else {
                    if(sum[i][j]>max_2) {
                        max_2=sum[i][j];
                        x2=i;
                        y2=j;
                    }
                }
            }
        }
        x1++;
        y1++;
        x2++;
        y2++;
        System.out.println(max_1+max_2);
        System.out.println(x1+" "+y1+" "+x2+" "+y2);
    }
    public static void solve_1(int n,long arr[][]) {
        long sum[][]=sum(n,arr);
        long max_1=0,max_2=0;
        int x1,y1,x2,y2;
        x1=y1=x2=y2=-1;
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(i%2==0 &&j%2==i%2) {
                    if(sum[i][j]>max_1) {
                        max_1=sum[i][j];
                        x1=i;
                        y1=j;
                    }
                }
                else if(i%2==0 &&j%2!=i%2){
                    if(sum[i][j]>max_2) {
                        max_2=sum[i][j];
                        x2=i;
                        y2=j;
                    }
                }
                else if(i%2!=0 &&j%2==i%2) {
                    if(sum[i][j]>max_2) {
                        max_2=sum[i][j];
                        x2=i;
                        y2=j;
                    }
                }
                else if(i%2!=0 &&j%2!=i%2){
                    if(sum[i][j]>max_1) {
                        max_1=sum[i][j];
                        x1=i;
                        y1=j;
                    }
                }
            }
        }
        x1++;
        y1++;
        x2++;
        y2++;
        System.out.println(max_1+max_2);
        System.out.println(x1+" "+y1+" "+x2+" "+y2);
        
    }
    public static long[][] sum(int n,long arr[][]) {
        long lft[][]=new long[n][n];
        long rght[][]=new long[n][n];
        for(int i=0;i<n;i++) {
            int x=0,y=i;
            long sum=0;
            while(x<n && y<n) {
                sum+=arr[x][y];
                x++;
                y++;
            }
            x=0;
            y=i;
            while(x<n && y<n) {
                lft[x][y]=sum;
                x++;
                y++;
            }
        }
        for(int i=0;i<n;i++) {
            int x=i,y=0;
            long sum=0;
            while(x<n && y<n) {
                sum+=arr[x][y];
                x++;
                y++;
            }
            x=i;
            y=0;
            while(x<n && y<n) {
                lft[x][y]=sum;
                x++;
                y++;
            }
        }
        
        
        for(int i=0;i<n;i++) {
            int x=0,y=i;
            long sum=0;
            while(x<n && y>=0) {
                sum+=arr[x][y];
                x++;
                y--;
            }
            x=0;
            y=i;
            while(x<n && y>=0) {
                rght[x][y]=sum;
                x++;
                y--;
            }
        }
        for(int i=0;i<n;i++) {
            int x=i,y=n-1;
            long sum=0;
            while(x<n && y>=0) {
                sum+=arr[x][y];
                x++;
                y--;
            }
            x=i;
            y=n-1;
            while(x<n && y>=0) {
                rght[x][y]=sum;
                x++;
                y--;
            }
        }
        
        long sum_arr[][]=new long[n][n];
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                sum_arr[i][j]=lft[i][j]+rght[i][j]-arr[i][j];
            }
        }
        sum_arr[0][0]=lft[0][0];
        sum_arr[0][n-1]=rght[0][n-1];
        sum_arr[n-1][0]=rght[n-1][0];
        sum_arr[n-1][n-1]=lft[n-1][n-1];
//        for(int i=0;i<n;i++) {
//            for(int j=0;j<n;j++) {
//                System.out.print(lft[i][j]+" ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//        for(int i=0;i<n;i++) {
//            for(int j=0;j<n;j++) {
//                System.out.print(rght[i][j]+" ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//        for(int i=0;i<n;i++) {
//            for(int j=0;j<n;j++) {
//                System.out.print(sum_arr[i][j]+" ");
//            }
//            System.out.println();
//        }
//        System.out.println();
        return sum_arr;
    }
}
