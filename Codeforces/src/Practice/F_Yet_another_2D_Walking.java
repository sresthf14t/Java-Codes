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
public class F_Yet_another_2D_Walking {
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
    static int n,x[],y[],lft[],rgt[];
    static long dp[][];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n=input.scanInt()+1;
        int max=0;
        x=new int[n];
        y=new int[n];
        ArrayList<Integer> arrli=new ArrayList<>();
        Set<Integer> hashset=new HashSet<>();
        hashset.add(0);
        arrli.add(0);
        for(int i=1;i<n;i++) {
            x[i]=input.scanInt();
            y[i]=input.scanInt();
            if(!hashset.contains(Math.max(x[i],y[i]))) {
                hashset.add(Math.max(x[i],y[i]));
                arrli.add(Math.max(x[i],y[i]));
            }
        }
        arrli.sort(null);
        max=arrli.size();
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<arrli.size();i++) {
//            System.out.print(arrli.get(i)+" ");
            map.put(arrli.get(i), i);
        }
//        System.out.println();
        lft=new int[max];
        rgt=new int[max];
        Arrays.fill(lft, -1);
        Arrays.fill(rgt, -1);
        for(int i=0;i<n;i++) {
//            System.out.println(Math.max(x[i], y[i]));
            int indx=map.get(Math.max(x[i], y[i]));
            if(lft[indx]==-1) {
                lft[indx]=rgt[indx]=i;
                continue;
            }
            if(x[i]<x[lft[indx]]) {
                lft[indx]=i;
            }
            else if(x[i]==x[lft[indx]] && y[i]>y[lft[indx]]) {
                lft[indx]=i;
            }
            if(y[i]<y[rgt[indx]]) {
                rgt[indx]=i;
            }
            else if(y[i]==y[rgt[indx]] && x[i]>x[rgt[indx]]) {
                rgt[indx]=i;
            }
        }
        dp=new long[lft.length][2];
        for(int i=0;i<dp.length;i++) {
            for(int j=0;j<dp[0].length;j++) {
                dp[i][j]=-1;
            }
        }
        System.out.println(solve(0,false));
    }
    public static long solve(int indx,boolean curr) {
        if(indx==lft.length-1) {
            return dist(lft[indx],rgt[indx]);
        }
        if(dp[indx][curr?0:1]!=-1) {
            return dp[indx][curr?0:1];
        }
        int tmpx=x[curr?lft[indx]:rgt[indx]];
        int tmpy=y[curr?lft[indx]:rgt[indx]];
        long min=Long.MAX_VALUE;
        min=Math.min(min,dist(curr?lft[indx]:rgt[indx],lft[indx+1])+dist(lft[indx],rgt[indx])+solve(indx+1,false));
        min=Math.min(min,dist(curr?lft[indx]:rgt[indx],rgt[indx+1])+dist(lft[indx],rgt[indx])+solve(indx+1,true));
        dp[indx][curr?0:1]=min;
        return min;
    }
    public static long dist(int indx1,int indx2) {
        return Math.abs(x[indx1]-x[indx2])+Math.abs(y[indx1]-y[indx2]);
    }
}
