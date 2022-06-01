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
public class E_Fire {
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
    static int n,tym[],dest[],cost[],indx11[],dp[][];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n=input.scanInt();
        tym=new int[n];
        dest=new int[n];
        cost=new int[n];
        indx11=new int[n];
        for(int i=0;i<n;i++) {
            tym[i]=input.scanInt();
            dest[i]=input.scanInt();
            cost[i]=input.scanInt();
//            tym[i]=(int)(Math.random()*20)+1;
//            dest[i]=(int)(Math.random()*2000)+1;
//            cost[i]=(int)(Math.random()*20)+1;
            indx11[i]=i;
        }
        dp=new int[n][5000];
        for(int i=0;i<dp.length;i++) {
            for(int j=0;j<dp[0].length;j++) {
                dp[i][j]=-1;
            }
        }
        sort();
        System.out.println(solve(0,0));
        int indx=0,time=0,total=0;
        ArrayList<Integer> arrli=new ArrayList<>();
        for(int i=0;i<dp.length;i++) {
            if(i==dp.length-1) {
                if(time+tym[i]<dest[i]) {
                    arrli.add((indx11[i]+1));
                    total+=cost[i];
                }
                continue;
            }
            if(dp[i][indx]==dp[i+1][indx]) {
                continue;
            }
            arrli.add(indx11[i]+1);
            total+=cost[i];
            indx+=tym[i];
            time+=tym[i];
        }
//        arrli.sort(null);
        System.out.println(arrli.size());
        StringBuilder ans=new StringBuilder("");
        for(int i=0;i<arrli.size();i++) {
            ans.append(arrli.get(i)+" ");
        }
        System.out.println(ans);
        
//        System.out.println();
//        for(int i=0;i<dp.length;i++) {
//            for(int j=0;j<dp[0].length;j++) {
//                System.out.print(dp[i][j]+" ");
//            }
//            System.out.println();
//        }
    }
    public static int solve(int indx,int time) {
        if(indx==n) {
            return 0;
        }
        if(dp[indx][time]!=-1) {
            return dp[indx][time];
        }
        int ans=0;
        ans=Math.max(ans,solve(indx+1,time));
        if(time+tym[indx]<dest[indx]) {
            ans=Math.max(ans,cost[indx]+solve(indx+1,time+tym[indx]));
        }
        dp[indx][time]=ans;
        return ans;
    }
    
    public static int solve1(int time,boolean taken[]) {
        int ans=0;
        for(int i=0;i<n;i++) {
            if(taken[i]) {
                continue;
            }
            if(time+tym[i]<dest[i]) {
                taken[i]=true;
                ans=Math.max(ans,cost[i]+solve1(time+tym[i],taken));
                taken[i]=false;
            }
        }
        return ans;
    }
    
    public static void sort() {
        for(int i=0;i<n;i++) {
            for(int j=0;j<n-1;j++) {
                if(dest[j]>dest[j+1]) {
                    swap(tym,j,j+1);
                    swap(dest,j,j+1);
                    swap(cost,j,j+1);
                    swap(indx11,j,j+1);
                }
            }
        }
    }
    public static void swap(int arr[],int i,int j) {
        int tmp=arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }
}
