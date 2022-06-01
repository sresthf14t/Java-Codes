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
public class D_Pair_Of_Lines {
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
        long x[]=new long[n];
        long y[]=new long[n];
        for(int i=0;i<n;i++) {
            x[i]=input.scanInt();
            y[i]=input.scanInt();
        }
        if(n<=4) {
            System.out.println("YES");
            return;
        }
        if(check(n,x,y,0,1)) {
            System.out.println("YES");
            return;
        }
        if(check(n,x,y,0,2)) {
            System.out.println("YES");
            return;
        }
        if(check(n,x,y,1,2)) {
            System.out.println("YES");
            return;
        }
        ArrayList<Integer> set1=new ArrayList<>();
        ArrayList<Integer> set2=new ArrayList<>();
        for(int i=2;i<n;i++) {
            if((y[1]-y[0])*(x[1]-x[i])==(x[1]-x[0])*(y[1]-y[i])) {
                set1.add(0);
                set1.add(1);
                break;
            }
        }
        if(set1.size()>0) {
            if(check(n,x,y,set1.get(0),set1.get(1))) {
                System.out.println("YES");
                return;
            }
            System.out.println("NO");
            return;
        }
        set1.add(0);
        set2.add(1);
        for(int i=3;i<n;i++) {
            if((y[2]-y[0])*(x[0]-x[i])==(x[2]-x[0])*(y[0]-y[i])) {
                set1.add(2);
                break;
            }
        }
        if(set1.size()==1) {
            set2.add(2);
        }
        if(set1.size()>set2.size()) {
            if(check(n,x,y,set1.get(0),set1.get(1))) {
                System.out.println("YES");
                return;
            }
            System.out.println("NO");
            return;
        }
        if(check(n,x,y,set2.get(0),set2.get(1))) {
            System.out.println("YES");
            return;
        }
        System.out.println("NO");
        return;
    }
    public static boolean check(int n,long x[],long y[],int indx1,int indx2) {
//        System.out.println(indx1+" "+indx2);
        boolean taken[]=new boolean[n];
        taken[indx1]=taken[indx2]=true;
        for(int i=0;i<n;i++) {
            if(taken[i]) {
                continue;
            }
            if((y[indx1]-y[indx2])*(x[indx1]-x[i])==(x[indx1]-x[indx2])*(y[indx1]-y[i])) {
                taken[i]=true;
            }
        }
        indx1=indx2=-1;
        for(int i=0;i<n;i++) {
            if(taken[i]) {
                continue;
            }
            if(indx1==-1) {
                indx1=i;
            }
            else if(indx2==-1) {
                indx2=i;
            }
            else {
                break;
            }
        }
        if(indx1==-1 || indx2==-1) {
            return true;
        }
        taken[indx1]=taken[indx2]=true;
        for(int i=0;i<n;i++) {
            if(taken[i]) {
                continue;
            }
            if((y[indx1]-y[indx2])*(x[indx1]-x[i])==(x[indx1]-x[indx2])*(y[indx1]-y[i])) {
                taken[i]=true;
            }
        }
        for(int i=0;i<n;i++) {
//            System.out.println(taken[i]);
            if(taken[i]) {
                continue;
            }
            return false;
        }
        return true;
    }
}
