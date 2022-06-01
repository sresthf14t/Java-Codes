/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grakn_Forces_2020;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
import java.math.BigDecimal;
import java.math.MathContext;
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
    
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        int test=input.scanInt();
        for(int tt=1;tt<=test;tt++) {
            int n=input.scanInt()+2;
            int l=input.scanInt();
            int arr[]=new int[n];
            for(int i=1;i<n-1;i++) {
                arr[i]=input.scanInt();
            }
            arr[n-1]=l;
            ans.append(solve(n,arr)+"\n");
        }
        System.out.println(ans);
    }
    public static String solve(int n,int arr[]) {
        int speed=1;
        BigDecimal time_l[]=new BigDecimal[n];
        BigDecimal time=new BigDecimal("0");
        time_l[0]=new BigDecimal("0");
        for(int i=1;i<n;i++) {
            int dist=arr[i]-arr[i-1];
            BigDecimal tmp=new BigDecimal(""+dist).divide(new BigDecimal(""+speed), MathContext.DECIMAL64);
            time=time.add(tmp,MathContext.DECIMAL64);
            time_l[i]=new BigDecimal(""+time);
            speed++;
        }
        speed=1;
        BigDecimal time_r[]=new BigDecimal[n];
        time_r[n-1]=new BigDecimal("0");
        time=new BigDecimal("0");
        for(int i=n-2;i>=0;i--) {
            int dist=arr[i+1]-arr[i];
            BigDecimal tmp=new BigDecimal(""+dist).divide(new BigDecimal(""+speed), MathContext.DECIMAL64);
            time=time.add(tmp,MathContext.DECIMAL64);
            time_r[i]=new BigDecimal(""+time);
            speed++;
        }
        for(int i=0;i<n;i++) {
            if(time_l[i].compareTo(time_r[i])==0) {
                return ""+time_l[i];
            }
        }
//        for(int i=0;i<n;i++) {
//            System.out.print(time_l[i]+"\t");
//        }
//        System.out.println();
//        for(int i=0;i<n;i++) {
//            System.out.print(time_r[i]+"\t");
//        }
//        System.out.println();
        for(int i=0;i<n-1;i++) {
            if(time_l[i].compareTo(time_r[i])==-1 && time_l[i+1].compareTo(time_r[i+1])==1) {
                BigDecimal sub=new BigDecimal("0");
//                System.out.println("cmp "+time_l[i]+" "+time_r[i]+" "+time_l[i].compareTo(time_r[i+1]));
                if(time_l[i].compareTo(time_r[i+1])==-1) {
                    BigDecimal diff=time_r[i+1].subtract(time_l[i]);
                    time_l[i]=time_l[i].add(diff, MathContext.DECIMAL64);
                    int speed_l=1+i;
                    sub=new BigDecimal(""+speed_l).multiply(diff, MathContext.DECIMAL64);
                }
                else if(time_l[i].compareTo(time_r[i+1])==1){
                    BigDecimal diff=time_l[i].subtract(time_r[i+1]);
                    int speed_r=1+(n-i-2);
                    sub=new BigDecimal(""+speed_r).multiply(diff, MathContext.DECIMAL64);
                }
                int speed_l=1+i;
                int speed_r=1+(n-i-2);
                speed=speed_l+speed_r;
                BigDecimal dist=new BigDecimal(""+(arr[i+1]-arr[i]));
                dist=dist.subtract(sub);
                BigDecimal tmp=new BigDecimal(""+dist).divide(new BigDecimal(""+speed), MathContext.DECIMAL64);
                time_l[i]=time_l[i].add(tmp,MathContext.DECIMAL64);
                return ""+time_l[i];
            }
        }
//        BigDecimal cmp=new BigDecimal("0.000001");
//        for(int i=0;i<n;i++) {
//            BigDecimal diff=time_l[i].subtract(time_r[i], MathContext.DECIMAL64);
//            diff=diff.abs();
//            if(diff.compareTo(cmp)==-1 || diff.compareTo(cmp)==0) {
//                return ""+time_l[i];
//            }
//        }
        return "11";
    }
}
