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
    
    static int n,arr[][],cost[][],row[],col[];
    static TreeSet<Long> ts;
    
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        int test=input.scanInt();
        ts=new TreeSet<>(); 
       for(int tt=1;tt<=test;tt++) {
            n=input.scanInt();
//           n=2;
            arr=new int[n][n];
            for(int i=0;i<n;i++) {
                for(int j=0;j<n;j++) {
                    arr[i][j]=input.scanInt();
//                    arr[i][j]=((int)(Math.random()*10000))%2;
                }
            }
            cost=new int[n][n];
            for(int i=0;i<n;i++) {
                for(int j=0;j<n;j++) {
                    cost[i][j]=input.scanInt();
//                    cost[i][j]=((int)(Math.random()*10000))%1000+1;
                }
            }
            row=new int[n];
            for(int i=0;i<n;i++) {
                row[i]=input.scanInt();
//                row[i]=((int)(Math.random()*10000))%2;
            }
            col=new int[n];
            for(int i=0;i<n;i++) {
                col[i]=input.scanInt();
//                col[i]=((int)(Math.random()*10000))%2;
            }
            
            int tmp[][]=new int[n][n];
            for(int i=0;i<n;i++) {
                for(int j=0;j<n;j++) {
                    tmp[i][j]=-1;
                }
            }
            
           ans.append("Case #"+tt+": "+solve(0,0,tmp)+"\n");
            
        }
        System.out.println(ans);
    }
    
    public static int solve(int i,int j,int tmp[][]) {
        if(i==n) {
            
            ts.clear();
            gen(0,0,tmp);

            if(ts.size()==1) {
                return calc(n,tmp);
            }
            return Integer.MAX_VALUE;
        }
        
        int ans=Integer.MAX_VALUE;
        
        if(arr[i][j]!=-1) {
            tmp[i][j]=arr[i][j];
            if(j!=n-1) {
                ans=Math.min(ans,solve(i,j+1,tmp));
            }
            else {
                ans=Math.min(ans,solve(i+1,0,tmp));
            }
        }
        else {
            
            if(j!=n-1) {
                ans=Math.min(ans,solve(i,j+1,tmp));
            }
            else {
                ans=Math.min(ans,solve(i+1,0,tmp));
            }
            
            tmp[i][j]=0;
            if(j!=n-1) {
                ans=Math.min(ans,solve(i,j+1,tmp));
            }
            else {
                ans=Math.min(ans,solve(i+1,0,tmp));
            }

            tmp[i][j]=1;
            if(j!=n-1) {
                ans=Math.min(ans,solve(i,j+1,tmp));
            }
            else {
                ans=Math.min(ans,solve(i+1,0,tmp));
            }

            tmp[i][j]=-1;
        }
        
        return ans;
    }
    
    public static int calc(int n,int tmp[][]) {
        int sum=0;
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(arr[i][j]==-1 && tmp[i][j]!=-1) {
                    sum+=cost[i][j];
                }
            }
        }
        return sum;
    } 
   
    public static void gen(int i,int j,int tmp[][]) {
        
        if(ts.size()>1) {
            return;
        }
        
        if(i==n) {
            if(!check(n,tmp)) {
                return;
            }
            ts.add(hash(n,tmp));
            return;
        }
        
        if(tmp[i][j]!=-1) {
            if(j!=n-1) {
                gen(i,j+1,tmp);
            }
            else {
                gen(i+1,0,tmp);
            }
            return;
        }
        
        tmp[i][j]=0;
        if(j!=n-1) {
            gen(i,j+1,tmp);
        }
        else {
            gen(i+1,0,tmp);
        }
        
        tmp[i][j]=1;
        if(j!=n-1) {
            gen(i,j+1,tmp);
        }
        else {
            gen(i+1,0,tmp);
        }
        
        tmp[i][j]=-1;
    }
    
    public static boolean check(int n,int tmp[][]) {
        for(int i=0;i<n;i++) {
            int cnt=0;
            for(int j=0;j<n;j++) {
                cnt^=tmp[i][j];
            }
            if(cnt!=row[i]) {
                return false;
            }
        }
        
        for(int j=0;j<n;j++) {
            int cnt=0;
            for(int i=0;i<n;i++) {
                cnt^=tmp[i][j];
            }
            if(cnt!=col[j]) {
                return false;
            }
        }
        
        return true;
    }
    
    public static long hash(int n,int tmp[][]) {
        long ans=0,pow=1;
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                ans+=tmp[i][j]*pow;
                pow*=2;
            }
        }
        return ans;
    }
}
