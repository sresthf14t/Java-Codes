/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Contest_2050_and_Codeforces_Round_718;

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
    
    static int n,m,k,uu[][],rr[][],dd[][],ll[][],dp[][][];
    
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        
        n=input.scanInt();
        m=input.scanInt();
        k=input.scanInt();
        
        dp=new int[n][m][k];
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                for(int kk=0;kk<k;kk++) {
                    dp[i][j][kk]=-1;
                }
            }
        }
        
        uu=new int[n][m];
        rr=new int[n][m];
        dd=new int[n][m];
        ll=new int[n][m];
        
        for(int i=0;i<n;i++) {
            for(int j=0;j<m-1;j++) {
                int tmp=input.scanInt();
                rr[i][j]=tmp;
                ll[i][j+1]=tmp;
            }
        }
        
        for(int i=0;i<n-1;i++) {
            for(int j=0;j<m;j++) {
                int tmp=input.scanInt();
                dd[i][j]=tmp;
                uu[i+1][j]=tmp;
            }
        }
        
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(k%2!=0) {
                    ans.append(-1+" ");
                    continue;
                }
                ans.append((2*solve(i,j,k/2))+" ");
            }
            ans.append("\n");
        }
        
        System.out.println(ans);
    }
    
    public static int solve(int x,int y,int rem) {
        if(rem==0) {
            return 0;
        }
        
        if(dp[x][y][rem]!=-1) {
            return dp[x][y][rem];
        }
        
        int ans=Integer.MAX_VALUE/10;
        if(uu[x][y]!=0) {
            ans=Math.min(ans,uu[x][y]+solve(x-1,y,rem-1));
        }
        if(rr[x][y]!=0) {
            ans=Math.min(ans,rr[x][y]+solve(x,y+1,rem-1));
        }
        if(dd[x][y]!=0) {
            ans=Math.min(ans,dd[x][y]+solve(x+1,y,rem-1));
        }
        if(ll[x][y]!=0) {
            ans=Math.min(ans,ll[x][y]+solve(x,y-1,rem-1));
        }
        
        dp[x][y][rem]=ans;
        
        return ans;
    }
}
