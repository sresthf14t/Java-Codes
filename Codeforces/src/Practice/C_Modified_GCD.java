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
public class C_Modified_GCD {
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
        int a=input.scanInt();
        int b=input.scanInt();
        Set<Integer> hashset=new HashSet<>();
        for(int i=1;i<=Math.sqrt(a);i++) {
            if(a%i==0) {
                if(a/i==i) {
                    hashset.add(i);
                }
                else {
                    hashset.add(i);
                    hashset.add(a/i);
                }
            }
        }
        int cnt=0;
        for(int i=1;i<=Math.sqrt(b);i++) {
            if(b%i==0) {
                if(b/i==i) {
                    if(hashset.contains(i)) {
                        cnt++;
                    }
                }
                else {
                    if(hashset.contains(i)) {
                        cnt++;
                    }
                    if(hashset.contains(b/i)) {
                        cnt++;
                    }
                }
            }
        }
        int arr[]=new int[cnt];
        cnt=0;
        for(int i=1;i<=Math.sqrt(b);i++) {
            if(b%i==0) {
                if(b/i==i) {
                    if(hashset.contains(i)) {
                        arr[cnt]=i;
                        cnt++;
                    }
                }
                else {
                    if(hashset.contains(i)) {
                        arr[cnt]=i;
                        cnt++;
                    }
                    if(hashset.contains(b/i)) {
                        arr[cnt]=b/i;
                        cnt++;
                    }
                }
            }
        }
        sort(arr,0,arr.length-1);
        int test=input.scanInt();
        for(int tt=1;tt<=test;tt++) {
            int l=input.scanInt();
            int r=input.scanInt();
            int indx=search(arr,r);
            if(indx==-1) {
                ans.append(-1);
                ans.append("\n");
                continue;
            }
            if(arr[indx]<l) {
                ans.append(-1);
                ans.append("\n");
            }
            else {
                ans.append(arr[indx]);
                ans.append("\n");
            }
        }
        System.out.println(ans);
    }
    public static int search(int arr[],int val) {
        int ans=-1,l=0,r=arr.length-1;
        while(l<=r) {
            int mid=(l+r)/2;
            if(arr[mid]<=val) {
                ans=mid;
                l=mid+1;
            }
            else {
                r=mid-1;
            }
        }
        return ans;
    }
}
