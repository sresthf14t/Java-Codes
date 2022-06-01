/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Round_C_2022;

/**
 *
 * @author SRESTH
 */
import java.util.*;
import java.io.*;
public class Palindromic_Deletions {
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
    
    public static void sort(int arr[],int l,int r) {    //sort(arr,0,n-1);
        if(l==r) {
            return;
        }
        int mid=(l+r)/2;
        sort(arr,l,mid);
        sort(arr,mid+1,r);
        merge(arr,l,mid,mid+1,r);
    }
    public static void merge(int arr[],int l1,int r1,int l2,int r2) {
        int tmp[]=new int[r2-l1+1];
        int indx1=l1,indx2=l2;
        //sorting the two halves using a tmp array
        for(int i=0;i<tmp.length;i++) {
            if(indx1>r1) {
                tmp[i]=arr[indx2];
                indx2++;
                continue;
            }
            if(indx2>r2) {
                tmp[i]=arr[indx1];
                indx1++;
                continue;
            }
            if(arr[indx1]<arr[indx2]) {
                tmp[i]=arr[indx1];
                indx1++;
                continue;
            }
            tmp[i]=arr[indx2];
            indx2++;
        }
        //Copying the elements of tmp into the main array
        for(int i=0,j=l1;i<tmp.length;i++,j++) {
            arr[j]=tmp[i];
        }
    }
    
    public static void sort(long arr[],int l,int r) {    //sort(arr,0,n-1);
        if(l==r) {
            return;
        }
        int mid=(l+r)/2;
        sort(arr,l,mid);
        sort(arr,mid+1,r);
        merge(arr,l,mid,mid+1,r);
    }
    public static void merge(long arr[],int l1,int r1,int l2,int r2) {
        long tmp[]=new long[r2-l1+1];
        int indx1=l1,indx2=l2;
        //sorting the two halves using a tmp array
        for(int i=0;i<tmp.length;i++) {
            if(indx1>r1) {
                tmp[i]=arr[indx2];
                indx2++;
                continue;
            }
            if(indx2>r2) {
                tmp[i]=arr[indx1];
                indx1++;
                continue;
            }
            if(arr[indx1]<arr[indx2]) {
                tmp[i]=arr[indx1];
                indx1++;
                continue;
            }
            tmp[i]=arr[indx2];
            indx2++;
        }
        //Copying the elements of tmp into the main array
        for(int i=0,j=l1;i<tmp.length;i++,j++) {
            arr[j]=tmp[i];
        }
    }
    
    static long mod,fact[];
    static final int MAX=100;
    static final int MAX_CHAR=26;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        int test=input.scanInt();
        mod=1000000007;
        fact=new long[1000];
        fact[0]=1;
        for(int i=1;i<fact.length;i++) {
            fact[i]=i*fact[i-1];
            fact[i]%=mod;
        }
        for(int tt=1;tt<=test;tt++) {
            int n=input.scanInt();
            StringBuilder str=new StringBuilder(input.scanString());
//            long num=solve(str);
//            long den=fact[n];
//            den=inv(den,mod);
//            long fin=num*den;
//            fin%=mod;

            long l[][]=new long[MAX_CHAR][MAX];
            long r[][]=new long[MAX_CHAR][MAX];
            precompute(str, n, l, r);

            long mul=1;
            for(int i=str.length();i>=0;i--) {
//                mul=fact[i];
                System.out.println(i+" "+countPalindromes(i, n, l, r));
            }
            
            
            
//            ans.append("Case #"+tt+": "+fin+"\n");
        }
        System.out.print(ans);
    }
    
    public static long solve(StringBuilder str) {
        long ans=0; 
        
        for(int i=0;i<str.length();i++) {
            char tmp=str.charAt(i);
            str.deleteCharAt(i);
            if(is_pal(str)) {
                ans+=fact[str.length()];
            }
            ans+=solve(str);
            str.insert(i, tmp);
            ans%=mod;
        }
        ans%=mod;
        return ans;
    }
    
    public static boolean is_pal(StringBuilder str) {
        for(int i=0,j=str.length()-1;i<=j;i++,j--) {
            if(str.charAt(i)!=str.charAt(j)) {
                return false;
            }
        }
        return true;
    }
    
    public static long inv(long a,long n) {
        long q,r1=n,r2=a,r,t1=0,t2=1,t;
        while(true) {
            q=r1/r2;
            r=r1%r2;
            t=t1-(q*t2);
            r1=r2;
            r2=r;
            t1=t2;
            t2=t;
            if(r2==0) {
                    break;
            }
        }
        if(r1!=1) {
                return -1;
        }
        t1%=n;
        if(t1<0) {
                t1+=n;
        }
        return t1;
    }
    
    
    
    
    static void precompute(StringBuilder s, int n, long l[][], long r[][]) {
        l[s.charAt(0) - 'a'][0] = 1;

        // Precompute the prefix 2D array
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < MAX_CHAR; j++)
                l[j][i] += l[j][i - 1];    

            l[s.charAt(i) - 'a'][i]++;
        }

        r[s.charAt(n - 1) - 'a'][n - 1] = 1;

        // Precompute the Suffix 2D array.
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < MAX_CHAR; j++)
                r[j][i] += r[j][i + 1];    

            r[s.charAt(i) - 'a'][i]++;
        }
    }
 
    // Find the number of palindromic subsequence of
    // length k
    static long countPalindromes(int k, int n, long l[][], long r[][]) {
        long ans = 0;

        // If k is 1.
        if (k == 1) {
            for (int i = 0; i < MAX_CHAR; i++) {
                ans += l[i][n - 1];
                ans%=mod;
            }

            return ans;
        }

        // If k is 2
        if (k == 2) {

            // Adding all the products of prefix array
            for (int i = 0; i < MAX_CHAR; i++) {           
                ans += ((l[i][n - 1] * (l[i][n - 1] - 1)) / 2);
                ans%=mod;
            }

            return ans;
        }

        // For k greater than 2. Adding all the products
        // of value of prefix and suffix array.
        for (int i = 1; i < n - 1; i++) {
            for (int j = 0; j < MAX_CHAR; j++) {           
                ans += l[j][i - 1] * r[j][i + 1];
                ans%=mod;
            }
        }

        return ans;
    }
}
