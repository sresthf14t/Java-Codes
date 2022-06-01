/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Global_Round_11;

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
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int n=input.scanInt();
        int arr[]=new int[n];
        ArrayList<Integer> arrli[]=new ArrayList[n];
        for(int i=0;i<n;i++) {
            arrli[i]=new ArrayList<>();
            arr[i]=input.scanInt();
        }
        for(int i=0;;i++) {
//            for(int j=0;j<n;j++) {
//                System.out.print(arr[j]+" ");
//            }
//            System.out.println();
            if(check(n,arr)) {
                break;
            }
            int mark[]=make_group(n,arr);
            int indx1=-1,indx2=-1;
            for(int j=0;j<n;j++) {
                for(int k=j+1;k<n;k++) {
//                    System.out.println(arr[j]+" "+arr[k]);
                    if(arr[k]==arr[j]-1) {
                        indx1=j;
                        indx2=k;
                        break;
                    }
                }
                if(indx1!=-1) {
                    break;
                }
            }
            arrli[i]=merge(n,arr,mark,indx1,indx2);
        }
        StringBuilder ans=new StringBuilder("");
        int cnt=0;
        for(int i=0;i<n;i++) {
            if(arrli[i].size()==0) {
                break;
            }
            cnt++;
            ans.append(arrli[i].size()+" ");
            for(int j=0;j<arrli[i].size();j++) {
                ans.append(arrli[i].get(j)+" ");
            }
            ans.append("\n");
        }
        System.out.println(cnt+"\n"+ans);
    }
    public static ArrayList<Integer> merge(int n,int arr[],int mark[],int indx1,int indx2) {
//        System.out.println(indx1+" "+indx2);
        ArrayList<Integer> arrli=new ArrayList<>();
        arrli.add(indx1);
        int cnt=0;
        for(int i=indx1;i<indx2;i++) {
            if(mark[i]!=mark[indx1]) {
                break;
            }
            cnt++;
        }
        arrli.add(cnt);
        arrli.add(indx2-indx1+1-cnt);
        arrli.add(n-1-indx2);
        for(int i=0;i<arrli.size();i++) {
            if(arrli.get(i)==0) {
                arrli.remove(i);
                i--;
            }
        }
//        System.out.println("arrli");
//        for(int i=0;i<arrli.size();i++) {
//            System.out.print(arrli.get(i)+" ");
//        }
//        System.out.println();
        int tmp[]=new int[n];
        int curr=0;
        for(int i=arrli.size()-1;i>=0;i--) {
            int indx=0;
            for(int j=0;j<i;j++) {
                indx+=arrli.get(j);
            }
            cnt=0;
            for(int j=indx;cnt<arrli.get(i);j++,cnt++) {
                tmp[curr]=arr[j];
                curr++;
            }
        }
        for(int i=0;i<n;i++) {
            arr[i]=tmp[i];
        }
        return arrli;
    }
    public static int[] make_group(int n,int arr[]) {
        int mark[]=new int[n];
        int cnt=1;
        for(int i=0;i<n;i++) {
            int j=i+1;
            mark[i]=cnt;
            while(j<n && arr[j]==arr[j-1]+1) {
                mark[j]=cnt;
                j++;
            }
            cnt++;
            i=j-1;
        }
        return mark;
    }
    public static boolean check(int n,int arr[]) {
        for(int i=0;i<n;i++) {
            if(arr[i]!=i+1) {
                return false;
            }
        }
        return true;
    }
}

