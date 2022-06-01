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
public class D_Mr_Bender_and_Square {
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
        Scanner input=new Scanner(System.in);
        long n=input.nextLong();
        long x=input.nextLong();
        long y=input.nextLong();
        long c=input.nextLong();
        if(c==1) {
            System.out.println(0);
            return;
        }
        long ans=-1,l=1,r=1000000000;
        while(l<=r) {
            long mid=(l+r)/2;
            long tmp=get(mid,n,x,y);
//            System.out.println(mid+" "+tmp);
            if(tmp<c) {
                l=mid+1;
            }
            else {
                ans=mid;
                r=mid-1;
            }
        }
        System.out.println(ans-1);
    }
    public static long get(long dep,long n,long x,long y) {
        long ans=get_4(dep);
//        return ans;
        //up
        if(x-dep<0) {
            ans-=get_3(dep-x);
        }
        if(x+dep>n+1) {
            ans-=get_3((dep+x)-(n+1));
        }
        if(y-dep<0) {
            ans-=get_3(dep-y);
        }
        if(y+dep>n+1) {
            ans-=get_3((dep+y)-(n+1));
        }
        //top left
        if(x-dep<0 && y-(dep-x)<0) {
//            System.out.println(111111);
            ans+=get_2(dep-x-y);
        }
        //top right
        if(x-dep<0 && y+(dep-x)>n+1) {
//            System.out.println(222222);
            ans+=get_2(y+(dep-x)-(n+1));
        }
        //bottom left
        if(x+dep>n+1 && y-(x+dep-(n+1))<0) {
//            System.out.println(333333);
            ans+=get_2((x+dep-(n+1))-y);
        }
        if(x+dep>n+1 && y+(x+dep-(n+1))>n+1) {
//            System.out.println(444444+" "+((n+1)-(y+(x+dep-(n+1)))));
            ans+=get_2((y+(x+dep-(n+1)))-(n+1));
        }
        return ans;
    }
    public static long get_2(long dep) {
//        System.out.println(dep);
        return (dep*(dep+1))/2;
    }
    public static long get_3(long dep) {
        long ans=(dep*(dep-1))/2;
        ans*=2;
        ans+=dep;
        return ans;
    }
    public static long get_4(long dep) {
        return 1+4*(dep*(dep-1))/2;
    }
}
