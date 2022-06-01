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
public class D_An_overnight_dance_in_discotheque {
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
        int n=input.scanInt();
        int x[]=new int[n];
        int y[]=new int[n];
        int r[]=new int[n];
        int mark[]=new int[n];
        for(int i=0;i<n;i++) {
            x[i]=input.scanInt();
            y[i]=input.scanInt();
            r[i]=input.scanInt();
        }
        sort(n,x,y,r);
        for(int i=0;i<n;i++) {
//            System.out.println(x[i]+" "+y[i]+" "+r[i]);
        }
        int cnt=1;
        for(int i=0;i<n;i++) {
            if(mark[i]!=0) {
                continue;
            }
            mark[i]=cnt;
            cnt++;
            for(int j=i+1;j<n;j++) {
//                System.out.println(i+" "+j+" "+overlap(x,y,r,i,j));
                if(overlap(x,y,r,i,j)) {
                    mark[j]=mark[i];
                }
            }
        }
        int depth[]=new int[n];
        for(int i=0;i<n;i++) {
            for(int j=i+1;j<n;j++) {
                if(overlap(x,y,r,i,j)) {
                    depth[j]++;
                }
            }
        }
        for(int i=0;i<n;i++) {
//            System.out.println(i+" "+mark[i]+" "+depth[i]);
        }
        BigDecimal ans=new BigDecimal("0");
        for(int i=1;i<cnt;i++) {
            BigDecimal gain=new BigDecimal("0");
            BigDecimal tmp=null,tmp_g=null;
            for(int j=0;j<n;j++) {
                if(mark[j]!=i) {
                    continue;
                }
                if(depth[j]%2==1) {
                    continue;
                }
                BigDecimal add=new BigDecimal("0");
                BigDecimal sub=new BigDecimal("0");
                BigDecimal gain_add=new BigDecimal("0");
                BigDecimal gain_sub=new BigDecimal("0");
                for(int k=j;k<n;k++) {
                    if(mark[k]!=i) {
                        continue;
                    }
                    if(!overlap(x,y,r,j,k)) {
                        continue;
                    }
                    if(depth[k]%2==0) {
                        add=add.add(area(r[k]), MathContext.DECIMAL64);
                        if(depth[k]-depth[j]>=2) {
                            gain_add=gain_add.add(area(r[k]), MathContext.DECIMAL64);
                        }
                    }
                    else {
                        sub=sub.add(area(r[k]), MathContext.DECIMAL64);
                        if(depth[k]-depth[j]>=1) {
                            gain_sub=gain_sub.add(area(r[k]), MathContext.DECIMAL64);
                        }
                    }
                }
                add=add.subtract(sub, MathContext.DECIMAL64);
                if(tmp==null) {
                    tmp=new BigDecimal(""+add);
                }
                gain=new BigDecimal(""+gain_sub);
                gain=gain.multiply(new BigDecimal("2"), MathContext.DECIMAL64);
                gain=gain.subtract(gain_add, MathContext.DECIMAL64);
                gain=gain.subtract(gain_add, MathContext.DECIMAL64);
//                System.out.println(gain);
                if(tmp_g==null && gain.compareTo(new BigDecimal("0"))==1) {
                    tmp_g=new BigDecimal(""+gain);
                }
                else if(tmp_g!=null){
                    if(tmp_g.compareTo(gain)==-1) {
                        tmp_g=new BigDecimal(""+gain);
                    }
                }
            }
//            System.out.println("tmp"+tmp);
            ans=ans.add(tmp, MathContext.DECIMAL64);
//            System.out.println(tmp_g);
            if(tmp_g!=null) {
                ans=ans.add(tmp_g, MathContext.DECIMAL64);
            }
        }
        System.out.println(ans);
    }
    public static void sort(int n,int x[],int y[],int r[]) {
        for(int i=0;i<n;i++) {
            for(int j=0;j<n-1;j++) {
                if(r[j]<r[j+1]) {
                    swap(x,j,j+1);
                    swap(y,j,j+1);
                    swap(r,j,j+1);
                }
            }
        }
    }
    public static void swap(int arr[],int l,int r) {
        int tmp=arr[l];
        arr[l]=arr[r];
        arr[r]=tmp;
    }
    public static boolean overlap(int x[],int y[],int r[],int indx1,int indx2) {
        long x1=x[indx1];
        long y1=y[indx1];
        long x2=x[indx2];
        long y2=y[indx2];
        long tmp1=(x1-x2)*(x1-x2);
        long tmp2=(y1-y2)*(y1-y2);
        long tmp=tmp1+tmp2;
        long r1=r[indx1];
        r1*=r[indx1];
        long r2=r[indx2];
        r2*=r[indx2];
//        System.out.println(indx1+" "+indx2+" "+tmp+" "+r1+" "+r2);
        if(tmp<=r1 || tmp<=r2) {
            return true;
        }
        return false;
    }
    public static BigDecimal area(int r) {
        BigDecimal ans=new BigDecimal(""+r);
        ans=ans.multiply(ans,MathContext.DECIMAL64);
        ans=ans.multiply(new BigDecimal(""+3.1415926535897932384626433832), MathContext.DECIMAL64);
        return ans;
    }
}
