/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Code_Ensemble_Rated_for_Division_2;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class CENS20A {
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
        int m=input.scanInt();
        int arr[][]=new int[n][m];
        for(int i=0;i<n;i++) {
            String str=input.scanString();
            for(int j=0;j<m;j++) {
                arr[i][j]=str.charAt(j)-48;
            }
        }
        int brr[][]=new int[n][m];
        int q=input.scanInt();
        for(int Q=0;Q<q;Q++) {
            int x1=input.scanInt()-1;
            int y1=input.scanInt()-1;
            int x2=input.scanInt()-1;
            int y2=input.scanInt()-1;
            brr[x1][y1]++;
            if(x2+1<n) {
                brr[x2+1][y1]--;
            }
            if(y2+1<m) {
                brr[x1][y2+1]--;
            }
            if(x2+1<n && y2+1<m) {
                brr[x2+1][y2+1]++;
            }
        }
        for(int i=0;i<m;i++) {
            int sum=0;
            for(int j=0;j<n;j++) {
                sum+=brr[j][i];
                brr[j][i]=sum;
            }
        }
        for(int i=0;i<n;i++) {
            int sum=0;
            for(int j=0;j<m;j++) {
                sum+=brr[i][j];
                brr[i][j]=sum;
            }
        }
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                brr[i][j]+=arr[i][j];
            }
        }
        StringBuilder ans=new StringBuilder("");
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                ans.append(brr[i][j]%2);
            }
            ans.append("\n");
        }
        System.out.println(ans);
    }
}
