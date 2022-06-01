/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Educational_Codeforces_Round_89;

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
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int test=input.scanInt();
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            int m=input.scanInt();
            int cnt_one[]=new int[100];
            int cnt_zero[]=new int[100];
            int arr[][]=new int[n][m];
            create(arr,n,m);
//            for(int i=0;i<n;i++) {
//                for(int j=0;j<m;j++) {
//                    System.out.print(arr[i][j]+" ");
//                }
//                System.out.println();
//            }
            int rem=-1;
            if((n+m-1)%2==1) {
                for(int i=0;i<n;i++) {
                    for(int j=0;j<m;j++) {
                        rem=Math.max(rem,arr[i][j]);
                    }
                }
            }
            for(int i=0;i<n;i++) {
                for(int j=0;j<m;j++) {
                    int tmp=input.scanInt();
                    if(tmp==0) {
                        cnt_zero[arr[i][j]]++;
                    }
                    else {
                        cnt_one[arr[i][j]]++;
                    }
                }
            }
            int cnt=0;
            for(int i=0;i<100;i++) {
                if(i==rem) {
                    continue;
                }
                cnt+=Math.min(cnt_one[i], cnt_zero[i]);
            }
            System.out.println(cnt);
        }
    }
    public static void create(int arr[][],int n,int m) {
        int cnt=1,cnt_prev=-1;
        int mid=(int)Math.ceil((double)(n+m-1)/2.0);
        boolean inc=true;
        for(int i=0;i<m;i++) {
            int x=0,y=i;
            while(x<n && y>=0) {
                arr[x][y]=cnt;
                x++;
                y--;
            }
            
            if((n+m-1)%2==1 && cnt==mid) {
                inc=false;
            }
            else if((n+m-1)%2==0 && cnt==cnt_prev && cnt==mid){
                inc=false;
            }
            cnt_prev=cnt;
            if(inc && cnt!=mid) {
                cnt++;
            }
            else if(!inc){
                cnt--;
            }
        }
        for(int i=1;i<n;i++) {
            int x=i,y=m-1;
            while(x<n && y>=0) {
                arr[x][y]=cnt;
                x++;
                y--;
            }
            
            if((n+m-1)%2==1 && cnt==mid) {
                inc=false;
            }
            else if((n+m-1)%2==0 && cnt==cnt_prev && cnt==mid){
                inc=false;
            }
            cnt_prev=cnt;
            if(inc && cnt!=mid) {
                cnt++;
            }
            else if(!inc){
                cnt--;
            }
        }
    }
}
