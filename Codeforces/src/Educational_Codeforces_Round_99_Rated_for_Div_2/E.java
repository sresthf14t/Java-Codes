/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Educational_Codeforces_Round_99_Rated_for_Div_2;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class E {
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
    
    static long x[],y[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        int test=input.scanInt();
        for(int tt=1;tt<=test;tt++) {
            x=new long[4];
            y=new long[4];
            for(int i=0;i<4;i++) {
                x[i]=input.scanInt();
                y[i]=input.scanInt();
            }
            ans.append(solve(0,new long[4],new long[4],new boolean[4])+"\n");
        }
        System.out.println(ans);
    }
    public static long solve(int indx,long X[],long Y[],boolean taken[]) {
        if(indx==4) {
            boolean tmp[]=new boolean[4];
            long ans=Long.MAX_VALUE;
            for(int i=0;i<4;i++) {
                tmp[i]=true;
                ans=Math.min(ans,get(X[0],X[1],X[2],X[3],Y[0],Y[1],Y[2],Y[3],tmp));
                tmp[i]=false;
            }
            return ans;
        }
        long ans=Long.MAX_VALUE;
        for(int i=0;i<4;i++) {
            if(taken[i]) {
                continue;
            }
            taken[i]=true;
            X[indx]=x[i];
            Y[indx]=y[i];
            ans=Math.min(ans,solve(indx+1,X,Y,taken));
            taken[i]=false;
        }
        return ans;
    }
    public static long get(long tlxx,long trxx,long blxx,long brxx,long tlyy,long tryy,long blyy,long bryy,boolean strt[]) {
        long ans=0;
        int indx=-1;
        for(int i=0;i<4;i++) {
            if(strt[i]) {
                indx=i;
            }
        }
        if(Math.max(tlxx, blxx)>Math.min(trxx,brxx)) {
            return Long.MAX_VALUE;
        }
        if(Math.max(blyy, bryy)>Math.min(tlyy,tryy)) {
            return Long.MAX_VALUE;
        }
        if(indx==0) {
            ans+=Math.abs(tryy-tlyy);
            tryy=tlyy;
            ans+=Math.abs(blxx-tlxx);
            blxx=tlxx;
            ans+=Math.abs(blyy-bryy);
            ans+=Math.abs(trxx-brxx);
            bryy=blyy;
            brxx=trxx;
        }
        if(indx==1) {
            ans+=Math.abs(tryy-tlyy);
            tlyy=tryy;
            ans+=Math.abs(brxx-trxx);
            brxx=trxx;
            ans+=Math.abs(blyy-bryy);
            ans+=Math.abs(tlxx-blxx);
        }
        if(indx==2) {
            ans+=Math.abs(tlxx-blxx);
            tlxx=blxx;
            ans+=Math.abs(bryy-blyy);
            bryy=blyy;
            ans+=Math.abs(trxx-brxx);
            ans+=Math.abs(tryy-tlyy);
        }
        if(indx==3) {
            ans+=Math.abs(trxx-brxx);
            trxx=brxx;
            ans+=Math.abs(blyy-bryy);
            blyy=bryy;
            ans+=Math.abs(tlxx-blxx);
            ans+=Math.abs(tlyy-tryy);
        }
        if(tlxx==trxx) {
            ans+=2;
        }
        if(blyy==tlyy) {
            ans+=2;
        }
        return ans;
    }
}
