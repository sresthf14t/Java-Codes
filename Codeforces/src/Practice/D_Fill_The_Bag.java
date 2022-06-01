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
public class D_Fill_The_Bag {
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
    static long n,pow[];
    static int m,arr[];
    static HashMap<Long,Integer> map;
    public static void main(String args[]) throws IOException {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        pow=new long[32];
        pow[0]=1;
        map=new HashMap<>();
        map.put(1L, 0);
        for(int i=1;i<pow.length;i++) {
            pow[i]=2*pow[i-1];
            map.put(pow[i], i);
        }
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            n=input.nextLong();
            m=input.nextInt();
            arr=new int[pow.length];
            for(int i=0;i<m;i++) {
                arr[map.get(input.nextLong())]++;
            }
            ans.append(solve()+"\n");
        }
        System.out.println(ans);
    }
    public static int solve() {
        int ans=0;
        if(n%2==1) {
            if(arr[0]>0) {
                arr[0]--;
                n--;
            }
            else {
                for(int i=1;i<pow.length;i++) {
                    if(arr[i]>0) {
                        arr[i]--;
                        long tmp=pow[i];
                        while(tmp!=1) {
//                            System.out.println(tmp);
                            tmp/=2;
                            arr[map.get(tmp)]++;
                            ans++;
                        }
                        arr[0]--;
                        n--;
                        break;
                    }
                }
            } 
        }
//        System.out.println("III");
        arr[1]+=arr[0]/2;
        arr[0]=0;
        for(int i=0;i<=62;i++) {
//            System.out.println("i "+i);
            if((n&(long)Math.pow(2, i))!=0) {
                int tmp=check((long)Math.pow(2, i));
                if(tmp==-1) {
                    return -1;
                }
                ans+=tmp;
            }
        }
        return ans;
    }
    public static int check(long tmp) {
        long tmp1=tmp;
        int tmp_arr[]=new int[arr.length];
        for(int i=0;i<arr.length;i++) {
            tmp_arr[i]=arr[i];
        }
        for(int i=pow.length-1;i>0;i--) {
            while(tmp_arr[i]>0 && pow[i]<=tmp1) {
                tmp1-=pow[i];
                tmp_arr[i]--;
            }
        }
        if(tmp1==0) {
            arr=tmp_arr;
            return 0;
        }
        for(int i=1;i<pow.length;i++) {
            if(arr[i]>0 && pow[i]>=tmp) {
                arr[i]--;
                tmp1=pow[i];
                int cnt=0;
                while(tmp1!=tmp) {
                    tmp1/=2;
                    arr[map.get(tmp1)]++;
                    cnt++;
                }
                return cnt;
            }
        }
        return -1;
    }
}
