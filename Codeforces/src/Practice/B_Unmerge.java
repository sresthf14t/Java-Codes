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
public class B_Unmerge {
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
    static int n,arr[],brr[],dp[][];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        int test=input.scanInt();
        for(int t=1;t<=test;t++) {
            n=input.scanInt();
            arr=new int[2*n];
            brr=new int[2*n];
            for(int i=0;i<arr.length;i++) {
                arr[i]=input.scanInt();
                brr[i]=1;
            }
            while(true) {
                ArrayList<Integer> arrli=new ArrayList<>();
                ArrayList<Integer> cnt=new ArrayList<>();
                for(int i=0;i<arr.length;i++) {
                    int j=i+1,sum=brr[i];
                    while(j<arr.length && arr[j]<arr[j-1]) {
                        sum+=brr[j];
                        j++;
                    }
                    j--;
                    arrli.add(arr[i]);
                    cnt.add(sum);
                    i=j;
                }
                if(arrli.size()==arr.length) {
                    break;
                }
                arr=new int[arrli.size()];
                brr=new int[cnt.size()];
                for(int i=0;i<arrli.size();i++) {
                    arr[i]=arrli.get(i);
                    brr[i]=cnt.get(i);
//                    System.out.println(arr[i]+" "+brr[i]);
                }
//                System.out.println();
            }
            dp=new int[arr.length][2*n+1];
            for(int i=0;i<dp.length;i++) {
                for(int j=0;j<dp[0].length;j++) {
                    dp[i][j]=-1;
                }
            }
//            for(int i=0;i<brr.length;i++) {
//                System.out.print(brr[i]+" ");
//            }
//            System.out.println();
            int cnt=solve(0,0);
//            System.out.println(cnt);
            if(cnt>0) {
                ans.append("YES\n");
            }
            else {
                ans.append("NO\n");
            }
        }
        System.out.println(ans);
    }
    public static int solve(int indx,int sum) {
//        System.out.println(indx+" "+sum);
        if(indx==brr.length) {
            if(sum==n) {
                return 1;
            }
            return 0;
        }
        if(dp[indx][sum]!=-1) {
            return dp[indx][sum];
        }
        int cnt=0;
        cnt+=solve(indx+1,sum);
        cnt+=solve(indx+1,sum+brr[indx]);
        cnt=Math.min(cnt,1);
        dp[indx][sum]=cnt;
        return cnt;
    }
}
