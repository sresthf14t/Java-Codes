/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Round_A_2021;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class B {
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
    
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        int test=input.scanInt();
        for(int tt=1;tt<=test;tt++) {
            int n=input.scanInt();
            int m=input.scanInt();
            int arr[][]=new int[n][m];
            for(int i=0;i<n;i++) {
                for(int j=0;j<m;j++) {
                    arr[i][j]=input.scanInt();
                }
            }
            ans.append("Case #"+tt+": "+solve(n,m,arr)+"\n");
        }
        System.out.println(ans);
    }
    public static long solve(int n,int m,int arr[][]) {
        long ans=0;
        
        int right[][]=new int[n][m];
        int lft[][]=new int[n][m];
        
        for(int i=0;i<n;i++) {
            int cnt=0;
            for(int j=m-1;j>=0;j--) {
                if(arr[i][j]==1) {
                    cnt++;
                }
                else {
                    cnt=0;
                }
                right[i][j]=cnt;
            }
        }
        
        for(int i=0;i<n;i++) {
            int cnt=0;
            for(int j=0;j<m;j++) {
                if(arr[i][j]==1) {
                    cnt++;
                }
                else {
                    cnt=0;
                }
                lft[i][j]=cnt;
            }
        }
        
        for(int j=0;j<m;j++) {
            
            int cnt=0;
            for(int i=0;i<n;i++) {
                if(arr[i][j]==1) {
                    cnt++;
                }
                else {
                    cnt=0;
                    continue;
                }
                
                long val=cnt;
                long ll=lft[i][j]/2;
                ans+=cnt(Math.min(val,ll));
                long rr=right[i][j]/2;
                ans+=cnt(Math.min(val,rr));
                
                ll=lft[i][j];
                ans+=cnt(Math.min(val/2,ll));
                rr=right[i][j];
                ans+=cnt(Math.min(val/2,rr));
                
            }  
        }
        
        
        for(int j=0;j<m;j++) {
            
            int cnt=0;
            for(int i=n-1;i>=0;i--) {
                if(arr[i][j]==1) {
                    cnt++;
                }
                else {
                    cnt=0;
                    continue;
                }
                
                long val=cnt;
                long ll=lft[i][j]/2;
                ans+=cnt(Math.min(val,ll));
                long rr=right[i][j]/2;
                ans+=cnt(Math.min(val,rr));
                
                ll=lft[i][j];
                ans+=cnt(Math.min(val/2,ll));
                rr=right[i][j];
                ans+=cnt(Math.min(val/2,rr));
                
            }  
        }
        
        
        
        return ans;
    }
    public static long cnt(long n) {
        if(n<2) {
            return 0;
        }
        return n-1;
    }
}
