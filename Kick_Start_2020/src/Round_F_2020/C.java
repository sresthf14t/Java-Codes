/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Round_F_2020;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class C {
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
    static boolean block[][];
    static int s;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        int test=input.scanInt();
        for(int tt=1;tt<=test;tt++) {
            s=input.scanInt();
            int r1=input.scanInt();
            int c1=input.scanInt();
            int r2=input.scanInt();
            int c2=input.scanInt();
            int c=input.scanInt();
            block=new boolean[100][100];
            block[r1][c1]=block[r2][c2]=true;
            for(int i=0;i<c;i++) {
                block[input.scanInt()][input.scanInt()]=true;
            }
            ans.append("Case #"+tt+": ");
            ans.append(solve(r1,c1,r2,c2,true,0)+"\n");
        }
        System.out.print(ans);
    }
    public static int solve(int i1,int j1,int i2,int j2,boolean chance,int cnt) {
        if(cnt>s) {
            return 0;
        }
//        System.out.println(chance+" "+i1+" "+j1+" "+i2+" "+j2+" "+cnt);
        int ans=0;
        boolean move=false;
        if(chance) {
            ans=Integer.MIN_VALUE;
            if(j1!=1) {
                if(!block[i1][j1-1]) {
                    move=true;
                    block[i1][j1-1]=true;
                    ans=Math.max(ans,1+solve(i1,j1-1,i2,j2,!chance,0));
                    block[i1][j1-1]=false;
                }
            }
            if(j1!=2*i1-1) {
                if(!block[i1][j1+1]) {
                    move=true;
                    block[i1][j1+1]=true;
                    ans=Math.max(ans,1+solve(i1,j1+1,i2,j2,!chance,0));
                    block[i1][j1+1]=false;
                }
            }
            if(i1!=1 && j1%2==0) {
                if(!block[i1-1][j1-1]) {
                    move=true;
                    block[i1-1][j1-1]=true;
                    ans=Math.max(ans,1+solve(i1-1,j1-1,i2,j2,!chance,0));
                    block[i1-1][j1-1]=false;
                }
            }
            if(i1!=s && j1%2==1) {
                if(!block[i1+1][j1+1]) {
                    move=true;
                    block[i1+1][j1+1]=true;
                    ans=Math.max(ans,1+solve(i1+1,j1+1,i2,j2,!chance,0));
                    block[i1+1][j1+1]=false;
                }
            }
        }
        else {
            ans=Integer.MAX_VALUE;
            if(j2!=1) {
                if(!block[i2][j2-1]) {
                    move=true;
                    block[i2][j2-1]=true;
                    ans=Math.min(ans,-1+solve(i1,j1,i2,j2-1,!chance,0));
                    block[i2][j2-1]=false;
                }
            }
            if(j2!=2*i2-1) {
                if(!block[i2][j2+1]) {
                    move=true;
                    block[i2][j2+1]=true;
                    ans=Math.min(ans,-1+solve(i1,j1,i2,j2+1,!chance,0));
                    block[i2][j2+1]=false;
                }
            }
            if(i2!=1 && j2%2==0) {
                if(!block[i2-1][j2-1]) {
                    move=true;
                    block[i2-1][j2-1]=true;
                    ans=Math.min(ans,-1+solve(i1,j1,i2-1,j2-1,!chance,0));
                    block[i2-1][j2-1]=false;
                }
            }
            if(i2!=s && j2%2==1) {
                if(!block[i2+1][j2+1]) {
                    move=true;
                    block[i2+1][j2+1]=true;
                    ans=Math.min(ans,-1+solve(i1,j1,i2+1,j2+1,!chance,0));
                    block[i2+1][j2+1]=false;
                }
            }
        }
        if(!move) {
            ans=solve(i1,j1,i2,j2,!chance,cnt+1);
        }
//        System.out.println("RET");
        return ans;
    }
}
