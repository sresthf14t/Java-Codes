/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_641_Div_2;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class C_1 {
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
    static int primes[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        sieve(200001);
        int n=input.scanInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
        }
//        fact(n,arr);
        System.out.println(solve(n,arr));
    }
    public static long solve(int n,int arr[]) {
        HashMap<Integer,Integer> map=new HashMap<>();
        int fact[][]=new int[30][primes.length];
        for(int i=0;i<primes.length;i++) {
            fact[0][i]=n;
            map.put(primes[i], i);
        }
        for(int i=0;i<n;i++) {
            if(arr[i]==1) {
                continue;
            }
            if(arr[i]!=1 && !sieve[arr[i]]) {
                fact[1][map.get(arr[i])]++;
                fact[0][map.get(arr[i])]--;
                continue;
            }
            for(int j=0;j<primes.length;j++) {
                int cnt=0;
                while(arr[i]%primes[j]==0) {
                    arr[i]/=primes[j];
                    cnt++;
                }
                fact[cnt][j]++;
                fact[0][j]--;
                if(arr[i]==1) {
                    break;
                }
            }
        }
        long ans=1;
        for(int i=0;i<primes.length;i++) {
            int sum=0;
            for(int j=0;j<30;j++) {
                sum+=fact[j][i];
                if(sum>=2) {
                    ans*=(long)Math.pow(primes[i],j);
                    break;
                }
            }
        }
        return ans;
    }
    static boolean sieve[];
    //false for prime number and true for composite number
    public static void sieve(int n) {
        sieve=new boolean[n];
        primes=new int[17984];
        int indx=0;
        for(int i=2;i<n;i++) {
            if(!sieve[i]) {
                primes[indx]=i;
                indx++;
                for(int j=2*i;j<n;j=j+i) {
                    sieve[j]=true;
                }
            }
        }
//        System.out.println(indx);
    }
}
