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
public class D_Power_Products {
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
    static boolean sieve[];
    static int primes[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int n=input.scanInt();
        int k=input.scanInt();
        long arr[]=new long[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
        }
        if(k==2) {
            System.out.println(solve_2(n,k,arr));
        }
        else {
            System.out.println(solve(n,k,arr));
        }
    }
    public static long solve_2(int n,int k,long arr[]) {
        sieve=new boolean[100001];
        primes=new int[9592];
        sieve(sieve.length);
        for(int i=0;i<n;i++) {
            long rem=1;
            for(int j=0;j<primes.length;j++) {
                int cnt=0;
                while(arr[i]%primes[j]==0) {
                    arr[i]/=primes[j];
                    cnt++;
                }
                if(cnt%2==1) {
                    rem*=primes[j];
                }
                if(arr[i]==1) {
                    break;
                }
            }
            arr[i]=rem;
        }
        Arrays.sort(arr);
        long ans=0;
//        for(int i=0;i<n;i++) {
//            System.out.print(arr[i]+" ");
//        }
//        System.out.println();
        for(int i=0;i<n;i++) {
            int j=i;
            long cnt=0;
            while(j<n && arr[i]==arr[j]) {
                j++;
                cnt++;
            }
            i=j-1;
            ans+=(cnt*(cnt-1))/2;
        }
        return ans;
    }
    
    public static long solve(int n,int k,long arr[]) {
        Arrays.sort(arr);
        long pow[];
        if(k>=35) {
            pow=new long[]{1};
        }
        else {
           int indx=0;
            long lim=(long)Math.pow(10,10);
            for(int i=1;;i++) {
                long tmp=(long)Math.pow(i, k);
                if(tmp<=lim) {
                    indx++;
                }
                else {
                    break;
                }
            }
            pow=new long[indx];
            for(int i=1;i<=pow.length;i++) {
                pow[i-1]=(long)Math.pow(i, k);
            } 
        }
        HashMap<Long,Long> map=new HashMap<>();
        long ans=0;
        for(int i=0;i<n;i++) {
            for(int j=0;j<pow.length;j++) {
                if(pow[j]>arr[i]*arr[i]) {
                    break;
                }
                if(pow[j]>=arr[i] && pow[j]%arr[i]==0) {
                    if(map.containsKey(pow[j]/arr[i])) {
                        ans+=map.get(pow[j]/arr[i]);
                    }
                }
            }
            if(!map.containsKey(arr[i])) {
                map.put(arr[i], 0L);
            }
            map.replace(arr[i], map.get(arr[i])+1);
        }
        return ans;
    }
    //false for prime number and true for composite number
    public static void sieve(int n) {
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
