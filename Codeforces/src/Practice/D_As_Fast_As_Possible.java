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
import java.math.BigDecimal;
import java.math.MathContext;
public class D_As_Fast_As_Possible {
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
    static BigDecimal tmp1;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int n=input.scanInt();
        int len=input.scanInt();
        int v1=input.scanInt();
        int v2=input.scanInt();
        int k=input.scanInt();
        System.out.println(search(n,len,v1,v2,k));
    }
    public static BigDecimal search(int n,int len,int v1,int v2,int k) {
        BigDecimal lft=new BigDecimal(""+(0));
        BigDecimal rgt=new BigDecimal(""+len);
        BigDecimal add=new BigDecimal(""+0.00001);
        BigDecimal cmp=null;
        BigDecimal ans=null;
        while(lft.compareTo(rgt)!=1) {
            tmp1=null;
            BigDecimal mid=lft.add(rgt, MathContext.DECIMAL64);
            mid=mid.divide(new BigDecimal("2"), MathContext.DECIMAL64);
            BigDecimal tmp=get(mid,len,n,v1,v2,k);
            BigDecimal diff=tmp.subtract(new BigDecimal(""+len), MathContext.DECIMAL64);
            diff=diff.abs();
//            System.out.println(diff);
            if(cmp==null || diff.compareTo(cmp)!=1) {
                ans=new BigDecimal(""+tmp1);
                cmp=diff;
            }
            if(cmp.compareTo(add)!=1) {
                break;
            }
            if(tmp.compareTo(new BigDecimal(""+len))==-1) {
                lft=mid.add(add, MathContext.DECIMAL64);
            }
            else {
                rgt=mid.subtract(add, MathContext.DECIMAL64);
            }
        }
        return ans;
    }
    public static BigDecimal get(BigDecimal drop,int len,int n,int v1,int v2,int k) {
        BigDecimal time=drop.divide(new BigDecimal(""+v2), MathContext.DECIMAL64);
        n-=k;
        while(n>0) {
            BigDecimal strt=new BigDecimal(""+v1).multiply(time, MathContext.DECIMAL64);
            BigDecimal dist=drop.subtract(strt, MathContext.DECIMAL64);
            BigDecimal time_tmp=dist.divide(new BigDecimal(""+(v2+v1)), MathContext.DECIMAL64);
            strt=strt.add(new BigDecimal(""+v1).multiply(time_tmp, MathContext.DECIMAL64),MathContext.DECIMAL64);
            drop=drop.add(new BigDecimal(""+v1).multiply(time_tmp, MathContext.DECIMAL64),MathContext.DECIMAL64);
            time=time.add(time_tmp, MathContext.DECIMAL64);
            
            dist=drop.subtract(strt, MathContext.DECIMAL64);
            time_tmp=dist.divide(new BigDecimal(""+(v2-v1)), MathContext.DECIMAL64);
            drop=drop.add(new BigDecimal(""+v1).multiply(time_tmp, MathContext.DECIMAL64), MathContext.DECIMAL64);
            time=time.add(time_tmp, MathContext.DECIMAL64);
            n-=k;
        }
        tmp1=time;
        return drop;
    }
}
