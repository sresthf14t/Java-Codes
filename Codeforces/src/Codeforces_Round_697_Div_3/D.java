/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_697_Div_3;

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
    
    
    static int n,m,arr[],brr[];
    
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        int test=input.scanInt();
        for(int tt=1;tt<=test;tt++) {
            n=input.scanInt();
            m=input.scanInt();
            arr=new int[n];
            brr=new int[n];
            ArrayList<Long> one=new ArrayList<>();
            ArrayList<Long> two=new ArrayList<>();
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt();
            }
            for(int i=0;i<n;i++) {
                brr[i]=input.scanInt();
                if(brr[i]==1) {
                    one.add((long)arr[i]);
                }
                else {
                    two.add((long)arr[i]);
                }
            }
            one.sort(null);
            two.sort(null);
            for(int i=0,j=one.size()-1;i<j;i++,j--) {
                long tmp=one.get(i);
                one.set(i, one.get(j));
                one.set(j, tmp);
            }
            
            for(int i=0,j=two.size()-1;i<j;i++,j--) {
                long tmp=two.get(i);
                two.set(i, two.get(j));
                two.set(j, tmp);
            }
            
            for(int i=1;i<one.size();i++) {
                one.set(i, one.get(i)+one.get(i-1));
            }
            
            for(int i=1;i<two.size();i++) {
                two.set(i, two.get(i)+two.get(i-1));
            }
            
            int fin=Integer.MAX_VALUE;
            
            for(int i=0;i<one.size();i++) {
                if(one.get(i)>=m) {
                    fin=i+1;
                    break;
                }
            }
            
            for(int i=0;i<two.size();i++) {
                if(two.get(i)>=m) {
                    fin=2*(i+1);
                    break;
                }
            }
            
            for(int i=0;i<one.size();i++) {
                if(one.get(i)>=m) {
                    fin=Math.min(fin,i+1);
                }
                long ttt=one.get(i);
                int tmp=search(two,m-(int)ttt);
                if(tmp==-1) {
                    continue;
                }
                fin=Math.min(fin,i+1+2*(tmp+1));
            }
            if(fin==Integer.MAX_VALUE) {
                fin=-1;
            }
            ans.append(fin+"\n");
        }
        System.out.println(ans);
    }
    
    public static int search(ArrayList<Long> arrli, int val) {
        int l=0,r=arrli.size()-1,ans=-1;
        while(l<=r) {
            int mid=(l+r)/2;
            if(arrli.get(mid)>=val) {
                ans=mid;
                r=mid-1;
            }
            else {
                l=mid+1;
            }
        }
        return ans;
    }
}
