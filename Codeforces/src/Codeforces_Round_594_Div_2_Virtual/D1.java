/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_594_Div_2_Virtual;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class D1 {
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
//        String tmp=input.scanString();
        String tmp="";
        for(int i=0;i<n;i++) {
            if(i%2==0) {
                tmp+="(";
            }
            else {
                tmp+=")";
            }
        }
        char chr[]=new char[n];
        int ccc=0;
        for(int i=0;i<n;i++) {
            chr[i]=tmp.charAt(i);
            if(chr[i]=='(') {
                ccc++;
            }
            else {
                ccc--;
            }
        }
        if(ccc!=0) {
            System.out.println(0);
            System.out.println(1+" "+1);
            return;
        }
        int max=-1,indx1=-1,indx2=-1;
        for(int i=0;i<n;i++) {
            for(int j=i;j<n;j++) {
                swap(chr,i,j);
                int tmp1=solve(n,chr);
                if(tmp1>max) {
                    max=tmp1;
                    indx1=i+1;
                    indx2=j+1;
                }
                swap(chr,i,j);
            }
        }
        System.out.println(max);
        System.out.println(indx1+" "+indx2);
    }
    public static int solve(int n,char chr[]) {
        for(int i=0;i<n;i++) {
            if(chr[i]==')') {
                continue;
            }
            int tmp=1,cnt=0;
            boolean is_pos=true;
            for(int j=(i+1)%n;j!=i;j=(j+1)%n) {
                if(chr[j]=='(') {
                    tmp++;
                }
                else {
                    tmp--;
                }
                if(tmp==0) {
                    cnt++;
                }
                if(tmp<0) {
                    if(j>i) {
                        i=j;
                    }
                    else {
                        i=n;
                    }
                    is_pos=false;
                    break;
                }
            }
            if(is_pos) {
                return cnt;
            }
        }
        return 0;
    }
    
    public static void swap(char arr[],int i,int j) {
        char tmp=arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }
}
