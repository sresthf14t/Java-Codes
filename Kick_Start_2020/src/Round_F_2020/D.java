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
    static int n,m,k,arr[];
    static float fin,rolls,tmp_roll,tmp_chance;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        int test=input.scanInt();
        for(int tt=1;tt<=test;tt++) {
            n=input.scanInt();
            m=input.scanInt();
            k=input.scanInt();
            arr=new int[k];
            for(int i=0;i<k;i++) {
                arr[i]=input.scanInt();
            }
            fin=Integer.MAX_VALUE;
            sort(arr,0,k-1);
            rolls=Integer.MAX_VALUE;
            solve(0,0,new int[n]);
            System.out.println(rolls);
        }
        System.out.println(ans);
    }
    public static void solve(int indx,float roll,int tmp[]) {
        if(indx==n) {
            if(check(tmp)) {
                System.out.println(roll);
                rolls=Math.min(rolls,roll);
            }
            return;
        }
        for(int i=0;i<m;i++) {
            tmp[indx]=i;
            solve(indx+1,roll+1,tmp);
        }
        //In this set
        if(indx!=0) {
            Set<Integer> hashset=new HashSet<>();
            for(int i=0;i<indx;i++) {
                hashset.add(tmp[indx]);
            }
            for(int i=0;i<m;i++) {
                if(hashset.contains(i)) {
                    tmp[indx]=i;
                    tmp_roll=0;
                    tmp_chance=0;
                    get(0,hashset.size(),-1,0);
                    solve(indx+1,roll+(hashset.size()*tmp_roll)/(tmp_chance*m),tmp);
                }
            }
        }
        //Not In this set
        if(indx!=0) {
            Set<Integer> hashset=new HashSet<>();
            for(int i=0;i<indx;i++) {
                hashset.add(tmp[indx]);
            }
            for(int i=0;i<m;i++) {
                if(!hashset.contains(i)) {
                    tmp[indx]=i;
                    tmp_roll=0;
                    tmp_chance=0;
                    get(0,m-hashset.size(),-1,0);
                    solve(indx+1,roll+(m-(hashset.size())*tmp_roll)/(tmp_chance*m),tmp);
                }
            }
        }
    }
    public static void get(int indx,int val,int prev,int roll) {
        if(prev>=val) {
            tmp_roll+=roll;
            tmp_chance++;
        }
        for(int i=indx;i<m;i++) {
            get(indx+1,val,i,roll+1);
        }
    }
    public static boolean check(int tmp[]) {
        int cpy[]=new int[n];
        for(int i=0;i<n;i++) {
            cpy[i]=tmp[i];
        }
        sort(cpy,0,n-1);
        ArrayList<Integer> arrli=new ArrayList<>();
        for(int i=0;i<n;i++) {
            int j=i;
            int cnt=0;
            while(j<n && cpy[j]==cpy[i]) {
                j++;
                cnt++;
            }
            j--;
            arrli.add(cnt);
            i=j;
        }
        if(arrli.size()!=k) {
            return false;
        }
        for(int i=0;i<k;i++) {
            if(arr[i]!=arrli.get(i)) {
                return false;
            }
        }
        return true;
    }
}
