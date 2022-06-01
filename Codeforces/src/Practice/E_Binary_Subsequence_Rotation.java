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
public class E_Binary_Subsequence_Rotation {
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
        String str1=input.scanString();
        String str2=input.scanString();
        int cnt0=0,cnt1=0;
        for(int i=0;i<n;i++) {
            if(str1.charAt(i)=='0') {
                cnt0++;
            }
            else {
                cnt1++;
            }
            if(str2.charAt(i)=='0') {
                cnt0--;
            }
            else {
                cnt1--;
            }
        }
        if(cnt0!=0 || cnt1!=0) {
            System.out.println(-1);
            return;
        }
        int max=0;
        ArrayList<Integer> arrli=new ArrayList<>();
        for(int i=0;i<n;i++) {
            if(str1.charAt(i)==str2.charAt(i)) {
                continue;
            }
            if(str1.charAt(i)=='0' && str2.charAt(i)=='1') {
                arrli.add(1);
            }
            else {
                arrli.add(2);
            }
        }
        ArrayList<Integer> tmp=new ArrayList<>();
        for(int i=0;i<arrli.size();i++) {
            int j=i,cnt=0;
            while(j<arrli.size() && arrli.get(j)==arrli.get(i)) {
                cnt++;
                j++;
            }
            j--;
            i=j;
            tmp.add(cnt);
        }
        int arr[]=new int[tmp.size()];
        for(int i=0;i<tmp.size();i++) {
            arr[i]=tmp.get(i);
        }
        int cnt=0;
        while(tmp.size()>0) {
            cnt++;
            int lim=arr.length-1;
            if(lim%2==0) {
                lim--;
            }
            for(int i=0;i<=lim;i++) {
                arr[i]--;
            }
            tmp=new ArrayList<>();
            int tmp_cnt=0;
            for(int i=0;i<arr.length;i++) {
                if(arr[i]==0) {
                    tmp_cnt++;
                }
                else {
                    if(tmp.size()>0 && tmp_cnt%2==1) {
                        tmp.set(tmp.size()-1, tmp.get(tmp.size()-1)+arr[i]);
                    }
                    else {
                        tmp.add(arr[i]);
                    }
                    tmp_cnt=0;
                }
            }
            arr=new int[tmp.size()];
            for(int i=0;i<arr.length;i++) {
                arr[i]=tmp.get(i);
            }
        }
        System.out.println(cnt);
    }
}
