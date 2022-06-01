/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Educational_Codeforces_Round_94_Rated_for_Div_2;

/**
 *
 * @author Srest
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
            int arr[]=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt()-1;
            }
            ans.append(solve(n,arr)+"\n");
        }
        System.out.println(ans);
    }
    public static long solve(int n,int arr[]) {
        long freq[][]=new long[n][n];
        for(int i=0;i<n;i++) {
            if(arr[0]==i) {
                freq[i][0]=1;
            }
            for(int j=1;j<n;j++) {
                freq[i][j]=freq[i][j-1];
                if(arr[j]==i) {
                    freq[i][j]++;
                }
            }
        }
        long map[][]=new long[n][n];
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                map[i][j]=-1;
            }
        }
        int nxt[][]=new int[n][n];
        for(int i=0;i<n;i++) {
            int curr=-1;
            for(int j=n-1;j>=0;j--) {
                nxt[i][j]=curr;
                if(arr[j]==i) {
                    curr=j;
                }
            }
        }
        long ans=0;
        for(int i=0;i<n;i++) {
            for(int j=i+1;j<n;j++) {
                if(map[arr[i]][j]!=-1) {
                    ans+=map[arr[i]][j];
                    continue;
                }
                long tmp=0;
                int k=nxt[arr[i]][j];
                while(k!=-1) {
                    if(arr[k]==arr[i]) {
                        tmp+=(freq[arr[j]][n-1]-freq[arr[j]][k]);
                    }
                    k=nxt[arr[i]][k];
                }
                ans+=tmp;
                map[arr[i]][j]=tmp;
            }
        }
        return ans;
    }
}
