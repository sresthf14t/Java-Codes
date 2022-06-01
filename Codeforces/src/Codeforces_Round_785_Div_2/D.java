/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_785_Div_2;

/**
 *
 * @author SRESTH
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
    
    static long b0,bd,bn,c0,cd,cn,mod;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        int test=input.scanInt();
        
        for(int tt=1;tt<=test;tt++) {
            b0=input.scanInt();
            bd=input.scanInt();
            bn=input.scanInt();
            c0=input.scanInt();
            cd=input.scanInt();
            cn=input.scanInt();
            mod=1000000007;
            
            if((Math.abs(c0-b0))%bd==0) {
                long val=(c0-b0)/bd;
//                System.out.println(val);
                if(val<0 || val>=bn) {
                    ans.append(0+"\n");
                    continue;
                }
            }  
            else {
                ans.append(0+"\n");
                continue;
            }
            long c_last=c0+(cn-1)*cd;
            if((Math.abs(c_last-b0))%bd==0) {
                long val=(c_last-b0)/bd;
//                System.out.println(val);
                if(val<0 || val>=bn) {
                    ans.append(0+"\n");
                    continue;
                }
            }
            else {
                ans.append(0+"\n");
                continue;
            }
            
            if(lcm(bd,cd)!=cd) {
                ans.append(0+"\n");
                continue;
            }
            
            if(!b_contains(c0-cd) || !b_contains(c0+((cn-1)*cd)+cd)) {
                ans.append(-1+"\n");
                continue;
            }
            
            long fin=0;
            TreeSet<Integer> ts=new TreeSet<>();
            for(int i=1;i<32000;i++) {
                if (cd % i != 0) {
                    continue;
                }
                ts.add(i);
                ts.add((int)cd/i);
            }
            while(!ts.isEmpty()) {
                int i=ts.last();
                ts.remove(ts.last());
//                System.out.println(i+" "+(lcm(i,bd)==cd)+" "+(bd%i!=0));
                if(lcm(i,bd)==cd) {
                    fin+=cnt(i);
                }
                fin%=mod;
            }
            ans.append(fin+"\n");
        }
        System.out.print(ans);
    }
    
    public static long cnt(long diff) {
        long lft=(cd/diff)-1;
        long rgt=(cd/diff)-1;
        long val=((lft+1)*(rgt+1))%mod;
//        System.out.println(diff+" "+val);
        return val;
    }
    
    public static boolean b_contains(long num) {
        if((Math.abs(num-b0))%bd==0) {
            long val=(num-b0)/bd;
//            System.out.println(val);
            if(val<0 || val>=bn) {
                return false;
            }
            return true;
        }
        else {
            return false;
        }
    }
    
    public static long gcd(long a, long b)
    {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }
     
    // method to return LCM of two numbers
    public static long lcm(long a, long b)
    {
        return (a / gcd(a, b)) * b;
    }
}
