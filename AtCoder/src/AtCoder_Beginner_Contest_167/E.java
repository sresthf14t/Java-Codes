/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AtCoder_Beginner_Contest_167;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class E {
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
    static long mod=998244353;
    static HashMap<Integer,Long> map[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n=input.scanInt();
        m=input.scanInt();
        k=input.scanInt();
        map=new HashMap[n];
        for(int i=0;i<map.length;i++) {
            map[i]=new HashMap<>();
        }
//        System.out.println(solve1(0,0));
        solve1();
    }
    public static long solve(int indx,int same) {
        if(indx==n) {
            return 1;
        }
        if(map[indx].containsKey(same)) {
//            System.out.println("DP");
            return map[indx].get(same);
        }
        long ways=0;
        if(indx!=0 && same<k) {
            ways+=solve(indx+1,same+1);
        }
        ways+=((m-1)*solve(indx+1,same));
        if(indx==0) {
            ways+=solve(indx+1,same);
        }
        ways%=mod;
        map[indx].put(same, ways);
        return ways;
    }
    public static void solve1() {
        int arr[][]=new int[2][k+1];
        for(int i=0;i<k+1;i++) {
            arr[1][i]=1;
        }
        for(int i=n-1;i>0;i--) {
            for(int j=0;j<Math.min(i, k);j++) {
                arr[0][j]=arr[1][j]+(m-1)*arr[1][j+1];
                arr[0][j]%=mod;
            }
            arr[0][k]=arr[1][k];
            copy(arr);
        }
        arr[0][0]=2*arr[1][0];
        System.out.println(arr[0][0]%mod);
    }
    public static void copy(int arr[][]) {
        for(int i=0;i<k+1;i++) {
//            System.out.print(arr[0][i]+" ");
            arr[1][i]=arr[0][i];
        }
//        System.out.println();
    }
}
