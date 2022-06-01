/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Educational_Codeforces_Round_91_Virtual;

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
    static int n,m,k;
    static long x,y,arr[],brr[];
    static HashMap<Integer,Long> dp[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n=input.scanInt()+2;
        m=input.scanInt()+2;
        arr=new long[n];
        brr=new long[m];
        x=input.scanInt();
        k=input.scanInt();
        y=input.scanInt();
        for(int i=1;i<n-1;i++) {
            arr[i]=input.scanInt();
        }
        for(int i=1;i<m-1;i++) {
            brr[i]=input.scanInt();
        }
        arr[0]=brr[0]=-1;
        arr[n-1]=brr[m-1]=-1;
        System.out.println(solve());
    }
    public static long solve() {
        boolean mark[]=new boolean[n];
        for(int i=0,j=0;i<n;i++) {
            if(j==m) {
                break;
            }
            if(arr[i]==brr[j]) {
                mark[i]=true;
                j++;
            }
            if(i==n-1 && j!=m) {
                return -1;
            }
        }
        long ans=0;
        for(int i=0;i<n;i++) {
            if(mark[i]) {
                for(int j=i+1;j<n;j++) {
                    if(mark[j]) {
                        long tmp=del(i,j);
                        if(tmp==-1) {
                            return -1;
                        }
                        ans+=tmp;
                        break;
                    }
                }
            }
        }
        return ans;
    }
    public static long del(int strt,int end) {
        if(strt+1==end) {
            return 0;
        }
        long max=0;
        for(int i=strt;i<=end;i++) {
            max=Math.max(max,arr[i]);
        }
        long ans=0;
        if(x>=k*y) {
            if(arr[strt]==max || arr[end]==max) {
                return y*(end-strt-1);
            }
            if(end-strt-1>=k) {
                int cnt=end-strt-1;
                while(cnt!=k) {
                    ans+=y;
                    cnt--;
                }
                return ans+x;
            }
            return -1;
        }
        int cnt=end-strt-1;
        while(cnt>=k) {
            cnt-=k;
            ans+=x;
        }
        if(cnt==0) {
            return ans;
        }
        if(arr[strt]==max || arr[end]==max) {
            ans+=cnt*y;
            return ans;
        }
        int l=-1,r=-1;
        for(int i=strt+1;i<end;i++) {
            if(l==-1 && arr[i]>Math.max(arr[strt],arr[end])) {
                l=i;
            }
            if(arr[i]>Math.max(arr[strt],arr[end])) {
                r=i;
            }
        }
        int mod=-1;
        for(int i=1;;i++) {
            if(k*i>=(r-l+1)) {
                mod=(k*i)-(r-l+1);
                break;
            }
        }
        int gap=0;
        for(int i=strt+1;i<l;i++) {
            gap++;
        }
        for(int i=r+1;i<end;i++) {
            gap++;
        }
        if(gap>=mod) {
            ans+=cnt*y;
            return ans;
        }
        cnt=end-strt-1;
        if(cnt<k) {
            return -1;
        }
        ans=0;
        while(cnt%k!=0) {
            ans+=y;
            cnt--;
        }
        ans+=x*(cnt/k);
        return ans;
    }
}
