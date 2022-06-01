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
public class C_Hills {
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
    static int n,arr[];
    static long dp[][][];
    static int val[][][];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        n=input.scanInt();
        arr=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
        }
        dp=new long[n][n/2+(n%2)+1][2];
        val=new int[n][n/2+(n%2)+1][2];
        for(int i=0;i<dp.length;i++) {
            for(int j=0;j<dp[0].length;j++) {
                for(int k=0;k<dp[0][0].length;k++) {
                    dp[i][j][k]=-1;
                    val[i][j][k]=-1;
                }
            }
        }
        int lim=n/2+(n%2);
        for(int i=1;i<=lim;i++) {
            ans.append(solve(0,i,0)+" ");
        }
        ans.append("\n");
        System.out.println(ans);
    }
    public static long solve(int indx,int rem,int prev) {
        if(indx==n) {
            if(rem==0) {
                return 0;
            }
            return Long.MAX_VALUE/2;
        }
        int tmp1=get(indx,rem,prev);
        if(tmp1!=-1) {
            return dp[indx][rem][tmp1];
        }
        long ans=Long.MAX_VALUE/2;
        ans=Math.min(ans,solve(indx+1,rem,arr[indx]));
        if(rem>0 && indx==n-1) {
            if(prev>=arr[indx]) {
                ans=Math.min(ans,prev-(arr[indx]-1)+solve(indx+1,rem-1,arr[indx]));
            }
            else {
                ans=Math.min(ans,solve(indx+1,rem-1,arr[indx]));
            }
        }
        else if(rem>0){
            long tmp=(prev>=arr[indx]?prev-(arr[indx]-1):0)+(arr[indx+1]>=arr[indx]?arr[indx+1]-(arr[indx]-1):0)+solve(indx+2,rem-1,arr[indx+1]>=arr[indx]?arr[indx]-1:arr[indx+1]);
            ans=Math.min(ans, tmp);
        }
        tmp1=free(indx,rem);
        val[indx][rem][tmp1]=prev;
        dp[indx][rem][tmp1]=ans;
        return ans;
    }
    public static int get(int indx,int rem,int prev) {
        for(int i=0;i<val[indx][rem].length;i++) {
            if(val[indx][rem][i]==prev) {
                return i;
            }
        }
        return -1;
    }
    public static int free(int indx,int rem) {
        for(int i=0;i<val[indx][rem].length;i++) {
            if(val[indx][rem][i]==-1) {
                return i;
            }
        }
        return -1;
    }
}
