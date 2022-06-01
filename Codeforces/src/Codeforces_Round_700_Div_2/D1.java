/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_700_Div_2;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class D1 {
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
    
    static int n,arr[],dp[][][];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
//        int test=input.scanInt();
        int test=1;
        for(int tt=1;tt<=test;tt++) {
            n=input.scanInt();
//            n=100;
            arr=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt();
//                arr[i]=(int)(Math.random()*(n-1));
//                System.out.print(arr[i]+" ");
            }
//            System.out.println();
            int prev1=-1,prev2=-1,size1=0,size2=0;
            for(int i=0;i<n;i++) {
                if(arr[i]!=prev1 && arr[i]!=prev2) {
                    if(i!=n-1) {
                        if(arr[i+1]!=prev1 && arr[i+1]!=prev2) {
                            prev1=arr[i];
                            size1++;
                        }
                        else if(arr[i+1]==prev1) {
                            prev1=arr[i];
                            size1++;
                        }
                        else if(arr[i+1]==prev2) {
                            prev2=arr[i];
                            size2++;
                        }
                    }
                    else {
                        if(arr[i]!=prev1) {
                            prev1=arr[i];
                            size1++;
                        }
                        else if(arr[i]!=prev2) {
                            prev2=arr[i];
                            size2++;
                        }
                    }
                }
                else if(arr[i]!=prev1) {
                    prev1=arr[i];
                    size1++;
                }
                else if(arr[i]!=prev2) {
                    prev2=arr[i];
                    size2++;
                }
            }

//            dp=new int[n+5][n+5][n+5];
//            for(int i=0;i<dp.length;i++) {
//                for(int j=0;j<dp[0].length;j++) {
//                    for(int k=0;k<dp[0][0].length;k++) {
//                        dp[i][j][k]=-1;
//                    }
//                }
//            }
//
//            if(solve(0,-1,-1)!=(size1+size2)) {
//                for(int i=0;i<n;i++) {
//                    System.out.print(arr[i]+" ");
//                }
//                System.out.println();
//                System.out.println(solve(0,-1,-1)+" "+(size1+size2));
////                System.out.println(-1);
//                break;
//            }

//            System.out.println(solve(0,-1,-1)+" "+(size1+size2));
            System.out.println(size1+size2);
        }
        
        
    }
    public static int solve(int indx,int prev1,int prev2) {
        if(indx==n) {
            return 0;
        }
        if(dp[indx][prev1+1][prev2+1]!=-1) {
            return dp[indx][prev1+1][prev2+1];
        }
        int ans=0;
        ans=Math.max(ans, (prev1==arr[indx]?0:1)+solve(indx+1,arr[indx],prev2));
        ans=Math.max(ans, (prev2==arr[indx]?0:1)+solve(indx+1,prev1,arr[indx]));
        dp[indx][prev1+1][prev2+1]=ans;
        return ans;
    }
}

