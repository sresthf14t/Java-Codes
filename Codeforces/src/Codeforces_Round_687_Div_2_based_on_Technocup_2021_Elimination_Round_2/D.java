/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_687_Div_2_based_on_Technocup_2021_Elimination_Round_2;

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
    static int n,strt1,strt2,dp[][];
    static long arr[],prefix[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        long pow[]=new long[32];
        pow[0]=1;
        for(int i=1;i<pow.length;i++) {
            pow[i]=2*pow[i-1];
        }
        n=input.scanInt();
        arr=new long[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
        }
        if(n==2) {
            System.out.println(-1);
            return;
        }
        int max_set[]=new int[n];
        Arrays.fill(max_set, -1);
        for(int i=0;i<n;i++) {
            for(int j=pow.length-1;j>=0;j--) {
                if((arr[i]&pow[j])!=0) {
                    max_set[i]=j;
                    break;
                }
            }
        }
        boolean is_pos=false;
        for(int i=0;i<n;i++) {
            if(i+2<n) {
                if(max_set[i]==max_set[i+1] && max_set[i]==max_set[i+2]) {
                    is_pos=true;
                    break;
                }
            }
        }
        if(is_pos) {
            System.out.println(1);
            return;
        }
        
        prefix=new long[n];
        prefix[0]=arr[0];
        for(int i=1;i<n;i++) {
            prefix[i]=arr[i]^prefix[i-1];
        }
        
        long ans=Integer.MAX_VALUE/10;
        for(int i=0;i<n-1;i++) {
            init();
            strt1=i;
            strt2=i+1;
            ans=Math.min(ans,solve(i-1,i+2));
        }
        if(ans>n) {
            ans=-1;
        }
        System.out.println(ans);
    }
    public static int solve(int indx1,int indx2) {
        
        long curr1=get(indx1+1,strt1);
        long curr2=get(strt2,indx2-1);
        
        if(curr1>curr2) {
            return 0;
        }
        
        if(indx1<0 && indx2>=n) {
            return Integer.MAX_VALUE/10;
        }
//        System.out.println(strt1+" "+strt2+" "+indx1+" "+indx2);
        
        
        if(dp[indx1+2][indx2+2]!=-1) {
            return dp[indx1+2][indx2+2];
        }
        
        int ans=Integer.MAX_VALUE/10;
        if(indx1>=0) {
            ans=Math.min(ans, 1+solve(indx1-1,indx2));
        }
        if(indx2<n) {
            ans=Math.min(ans, 1+solve(indx1,indx2+1));
        }
        dp[indx1+2][indx2+2]=ans;
        return ans;
    }
    public static long get(int l,int r) {
        return prefix[r]^(l==0?0:prefix[l-1]);
    }
    public static void init() {
        dp=new int[n+5][n+5];
        for(int i=0;i<dp.length;i++) {
            for(int j=0;j<dp[0].length;j++) {
                dp[i][j]=-1;
            }
        }
    }
}
