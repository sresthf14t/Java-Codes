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
public class C_Berzerk {
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
    static int n,k1,k2,arr1[],arr2[],dp[][];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        n=input.scanInt();
        k1=input.scanInt();
        arr1=new int[k1];
        for(int i=0;i<k1;i++) {
            arr1[i]=input.scanInt();
        }
        k2=input.scanInt();
        arr2=new int[k2];
        for(int i=0;i<k2;i++) {
            arr2[i]=input.scanInt();
        }
        init();
        for(int i=1;i<n;i++) {
            int tmp=solve(i,0,1);
            if(tmp==0) {
                ans.append("Lose ");
            }
            if(tmp==1) {
                ans.append("Loop ");
            }
            if(tmp==2) {
                ans.append("Win ");
            }
        }
        ans.append("\n");
        for(int i=1;i<n;i++) {
            int tmp=solve(i,0,2);
            if(tmp==0) {
                ans.append("Lose ");
            }
            if(tmp==1) {
                ans.append("Loop ");
            }
            if(tmp==2) {
                ans.append("Win ");
            }
        }
        ans.append("\n");
        System.out.println(ans);
    }
    public static int solve(int indx,int dep,int chance) {
//        System.out.println(indx+" "+chance);
        if(indx==0) {
            return 0;
        } 
        if(dep>=2*n) {
            return 1;
        }
        if(dp[indx][chance]!=-1) {
            return dp[indx][chance];
        }
        int ans=-1;
        if(chance==1) {
            for(int i=0;i<k1;i++) {
                int tmp=solve((arr1[i]+indx)%n,dep+1,2);
                if(tmp==0) {
                    tmp=2;
                }
                else if(tmp==2) {
                    tmp=0;
                }
                ans=Math.max(ans,tmp);
            }
        }
        else {
            for(int i=0;i<k2;i++) {
                int tmp=solve((arr2[i]+indx)%n,dep+1,1);
                if(tmp==0) {
                    tmp=2;
                }
                else if(tmp==2) {
                    tmp=0;
                }
                ans=Math.max(ans,tmp);
            }
        }
//        System.out.println(indx+" "+chance+" "+ans);
        dp[indx][chance]=ans;
        return ans;
    }
    public static void init() {
        dp=new int[n][3];
        for(int i=0;i<dp.length;i++) {
            for(int j=0;j<dp[0].length;j++) {
                dp[i][j]=-1;
            }
        }
    }
}
