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
public class C_Pashmak_and_Buses {
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
        int k=input.scanInt();
        int d=input.scanInt();
        if(d==1 && k<n) {
            System.out.println(-1);
            return;
        }
        int arr[][]=new int[d][n];
        int pow=1,indx=0;
        while(pow<n) {
            for(int i=0;i<pow;i++) {
                for(int j=i,cnt=0;j<n;j+=pow,cnt++) {
                    arr[indx][j]=cnt%k;
                    arr[indx][j]++;
                }
            }
            pow*=k;
            indx++;
            if(indx==d) {
                break;
            }
        }
        if(pow<n) {
            System.out.println(-1);
            return;
        }
        
        StringBuilder ans=new StringBuilder("");
        for(int i=0;i<d;i++) {
            for(int j=0;j<n;j++) {
                if(arr[i][j]==0) {
                    arr[i][j]++;
                }
                ans.append(arr[i][j]+" ");
            }
            ans.append("\n");
        }
        System.out.println(ans);
    }
    public static void copy(int n,int indx,int arr[][]) {
        for(int i=0;i<n;i++) {
            arr[indx][i]=arr[indx-1][i];
        }
    }
    public static void rotate(int indx,int arr[][],int l,int r,int times) {
        if(times==0) {
            return;
        }
        int tmp[]=new int[times];
        for(int i=l,j=0;j<times;i++,j++) {
            tmp[j]=arr[indx][i];
        }
        for(int i=l+times;i<=r;i++) {
//            System.out.println(i);
            arr[indx][i-times]=arr[indx][i];
        }
        for(int i=times-1,j=r;i>=0;i--,j--) {
            arr[indx][j]=tmp[i];
        }
    }
    public static boolean check(int d,int n,int k,int arr[][]) {
        int check[][]=new int[n][n];
        for(int i=0;i<d;i++) {
            int tmp[]=new int[k];
            for(int j=0;j<n;j++) {
                
            }
        }
        return false;
    }
}
