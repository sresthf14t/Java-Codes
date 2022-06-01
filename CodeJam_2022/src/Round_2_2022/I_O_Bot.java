/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Round_2_2022;

/**
 *
 * @author SRESTH
 */
import java.util.*;
import java.io.*;
public class I_O_Bot {
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
    
    static int n,c,arr[],shape[];
    static ArrayList<Integer> zero,one;
    static long dp[][];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        int test=input.scanInt();
        for(int tt=1;tt<=test;tt++) {
            n=input.scanInt();
            c=input.scanInt();
            arr=new int[n];
            shape=new int[n];
            zero=new ArrayList<>();
            one=new ArrayList<>();
            
            
            
            long fin=0;
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt();
                shape[i]=input.scanInt();
                if(arr[i]<0) {
                    if(shape[i]==0) {
                        zero.add(-1*arr[i]);
                    }
                    else {
                        one.add(-1*arr[i]);
                    }
                }
            }
            zero.sort(null);
            one.sort(null);
            for(int i=0,j=zero.size()-1;i<=j;i++,j--) {
                int tmp=zero.get(i);
                zero.set(i, zero.get(j));
                zero.set(j, tmp);
            }
            for(int i=0,j=one.size()-1;i<=j;i++,j--) {
                int tmp=one.get(i);
                one.set(i, one.get(j));
                one.set(j, tmp);
            }
            dp=new long[zero.size()+5][one.size()+5];
            for(int i=0;i<dp.length;i++) {
                for(int j=0;j<dp[0].length;j++) {
                    dp[i][j]=-1;
                }
            }
            fin+=solve(0,0);

            zero=new ArrayList<>();
            one=new ArrayList<>();
            for(int i=0;i<n;i++) {
                if(arr[i]>0) {
                    if(shape[i]==0) {
                        zero.add(arr[i]);
                    }
                    else {
                        one.add(arr[i]);
                    }
                }
            }
            zero.sort(null);
            one.sort(null);
//            for(int i=0,j=zero.size()-1;i<=j;i++,j--) {
//                int tmp=zero.get(i);
//                zero.set(i, zero.get(j));
//                zero.set(j, tmp);
//            }
//            for(int i=0,j=one.size()-1;i<=j;i++,j--) {
//                int tmp=one.get(i);
//                one.set(i, one.get(j));
//                one.set(j, tmp);
//            }
            
            dp=new long[zero.size()+5][one.size()+5];
            for(int i=0;i<dp.length;i++) {
                for(int j=0;j<dp[0].length;j++) {
                    dp[i][j]=-1;
                }
            }
            fin+=solve(0,0);
            
            ans.append("Case #"+tt+": "+fin+"\n");
        }
        System.out.print(ans);
    }

    public static long solve(int zero_indx,int one_indx) {
        if(zero_indx==zero.size() && one_indx==one.size()) {
            return 0;
        }
        
        if(dp[zero_indx][one_indx]!=-1) {
            return dp[zero_indx][one_indx];
        }
        
        long ans=Long.MAX_VALUE/10;
        
        //take one ball
        
        if(zero_indx<zero.size()) {
            ans=Math.min(ans,zero.get(zero_indx) + zero.get(zero_indx) + solve(zero_indx+1,one_indx));
        }
        if(one_indx<one.size()) {
            ans=Math.min(ans,one.get(one_indx) + one.get(one_indx) + solve(zero_indx,one_indx+1));
        }
        
        //take two ball
        if((zero_indx+1)<zero.size()) {
            long add=Math.max(zero.get(zero_indx),zero.get(zero_indx+1));
            ans=Math.min(ans,add + add + c + solve(zero_indx+1+1,one_indx));
        }
        if((one_indx+1)<one.size()) {
            long add=Math.max(one.get(one_indx),one.get(one_indx+1));
            ans=Math.min(ans,add + add + c + solve(zero_indx,one_indx+1+1));
        }
        if(zero_indx<zero.size() && one_indx<one.size()) {
            long add=Math.max(zero.get(zero_indx),one.get(one_indx));
            ans=Math.min(ans,add + add + solve(zero_indx+1,one_indx+1));
        }
        dp[zero_indx][one_indx]=ans;
        return ans;
    }
}
