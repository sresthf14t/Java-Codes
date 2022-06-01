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
public class D_Lucky_Number_2 {
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
        int a1=input.scanInt();
        int a2=input.scanInt();
        int a3=input.scanInt();
        int a4=input.scanInt();
        int ta1=a1,ta2=a2;
        StringBuilder str1=new StringBuilder("");
        if(Math.abs(a4-a3)>1) {
            System.out.println(-1);
            return;
        }
        if(a3>=a4) {
            for(int i=0;i<2*a3+1;i++) {
                if(i%2==0) {
                    str1.append(4);
                }
                else {
                    str1.append(7);
                }
            }
            if(a3-a4==1) {
                str1.deleteCharAt(str1.length()-1);
            }
        }
        else {
            for(int i=0;i<2*a4+1;i++) {
                if(i%2==0) {
                    str1.append(7);
                }
                else {
                    str1.append(4);
                }
            }
            if(a4-a3==1) {
                str1.deleteCharAt(str1.length()-1);
            }
        }
        for(int i=0;i<str1.length();i++) {
            if(str1.charAt(i)=='4') {
                a1--;
            }
            else {
                a2--;
            }
        }
        if(a1<0 || a2<0) {
            str1=new StringBuilder("");
            if(a3==a4) {
                for(int i=0;i<2*a4+1;i++) {
                    if(i%2==0) {
                        str1.append(7);
                    }
                    else {
                        str1.append(4);
                    }
                }
                a1=ta1;
                a2=ta2;
                for(int i=0;i<str1.length();i++) {
                    if(str1.charAt(i)=='4') {
                        a1--;
                    }
                    else {
                        a2--;
                    }
                }
            }
            if(a1<0 || a2<0) {
                System.out.println(-1);
                return;
            }
        }
        StringBuilder fr=new StringBuilder("");
        StringBuilder se=new StringBuilder("");
        for(int i=0;i<a1;i++) {
            fr.append(4);
        }
        for(int i=0;i<a2;i++) {
            se.append(7);
        }
        for(int i=0;i<str1.length();i++) {
            if(str1.charAt(i)=='4') {
                str1.insert(i, fr);
                break;
            }
        }
        for(int i=str1.length()-1;i>=0;i--) {
            if(str1.charAt(i)=='7') {
                str1.insert(i, se);
                break;
            }
        }
        System.out.println(str1);
    }
}
