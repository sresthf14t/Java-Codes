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
public class C_Garland {
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
    static int n,arr[],dp[][][][];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n=input.scanInt();
        arr=new int[n];
        int even=0,odd=0;
        ArrayList<Integer> arrli=new ArrayList<>();
        
        for(int i=1;i<=n;i++) {
            arrli.add(i);
        }
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
            if(arr[i]!=0) {
                arrli.remove(new Integer(arr[i]));
            }
        }
        for(int i=0;i<arrli.size();i++) {
            if(arrli.get(i)%2==0) {
                even++;
            }
            else {
                odd++;
            }
        }
        int tmp=0;
        for(int i=0;i<n-1;i++) {
            if(arr[i]==0 || arr[i+1]==0) {
                continue;
            }
            if(arr[i]%2!=arr[i+1]%2) {
                tmp++;
            }
        }
        dp=new int[101][100][100][3];
        for(int i=0;i<100;i++) {
            for(int j=0;j<100;j++) {
                for(int k=0;k<100;k++) {
                    for(int l=0;l<3;l++) {
                        dp[i][j][k][l]=-1;
                    }
                }
            }
        }
        System.out.println(tmp+solve(0,even,odd,0));
    }
    public static int solve(int indx,int even,int odd,int prev) {
        if(indx==n) {
            return 0;
        }
        if(even==0 && odd==0) {
            return 0;
        }
        while(arr[indx]!=0) {
            prev=0;
            indx++;
        }
        if(dp[indx][even][odd][prev]!=-1) {
            return dp[indx][even][odd][prev];
        }
        int min=Integer.MAX_VALUE;
        if(even!=0) {
            arr[indx]=2;
            int tmp=solve(indx+1,even-1,odd,2);
            arr[indx]=0;
            if(indx!=0 && arr[indx-1]%2!=0) {
                tmp++;
            }
            if(indx!=n-1 && arr[indx+1]!=0 && arr[indx+1]%2!=0) {
                tmp++;
            }
            min=Math.min(min,tmp);
        }
        if(odd!=0) {
            arr[indx]=1;
            int tmp=solve(indx+1,even,odd-1,1);
            arr[indx]=0;
            if(indx!=0 && arr[indx-1]%2==0) {
                tmp++;
            }
            if(indx!=n-1 && arr[indx+1]!=0 && arr[indx+1]%2==0) {
                tmp++;
            }
            min=Math.min(min,tmp);
        }
        dp[indx][even][odd][prev]=min;
        return min;
    }
}
