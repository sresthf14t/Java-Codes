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
public class D_Tennis_Game {
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
        int n=input.scanInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
//            arr[i]=(int)(Math.random()*2+1);
//            arr[i]=2;
//            System.out.print(arr[i]+" ");
        }
//        System.out.println();
        int one[]=new int[n];
        int two[]=new int[n];
        int tmp1=0,tmp2=0;
        TreeMap<Integer,TreeSet<Integer>> tm=new TreeMap<>();
        for(int i=0;i<n;i++) {
            if(arr[i]==1) {
                tmp1++;
            }
            else {
                tmp2++;
            }
            one[i]=tmp1;
            two[i]=tmp2;
        }
        int lim;
        if(arr[n-1]==1) {
            lim=tmp1;
        }
        else {
            lim=tmp2;
        }
        int cnt=0;
        for(int i=1;i<=lim;i++) {
//            System.out.println(i);
            int tmp=check(n,arr,one,two,i);
            if(tmp==-1) {
                continue;
            }
            cnt++;
            if(!tm.containsKey(tmp)) {
                TreeSet<Integer> stck=new TreeSet<>();
                stck.add(i);
                tm.put(tmp, stck);
            }
            else {
                TreeSet<Integer> stck=tm.get(tmp);
                stck.add(i);
            }
        }
        while(!tm.isEmpty()) {
            int key=tm.firstKey();
            TreeSet<Integer> ts=tm.get(key);
            while(!ts.isEmpty()) {
                ans.append(key+" "+ts.first()+"\n");
                ts.remove(ts.first());
            }
            tm.remove(key);
        }
        System.out.println(cnt+"\n"+ans);
    }
    public static int check(int n,int arr[],int one[],int two[],int t) {
        int w1=0,w2=0,indx1=0,indx2=0,curr1=0,curr2=0;
        while(true) {
            indx1=next(one,curr1+t);
            indx2=next(two,curr2+t);
//            if(true || t==1) {
//                System.out.println(t+" "+(curr1+t)+" "+(curr2+t)+" "+indx1+" "+indx2);
//            }
            
            if(indx1==-1 && indx2==-1) {
                return -1;
            }
            
            if(indx1==-1) {
                w2++;
            }
            else if(indx2==-1) {
                w1++;
            }
            else {
                if(indx1<indx2) {
                    w1++;
                }
                else {
                    w2++;
                }
            }
            
            if((indx2==-1 ||indx1<indx2) && indx1==n-1) {
                if(w1<=w2) {
                    return -1;
                }
                return w1;
            }
            if((indx1==-1 || indx2<indx1) && indx2==n-1) {
                if(w2<=w1) {
                    return -1;
                }
                return w2;
            }
            
            
            if(indx1!=-1 && (indx2==-1 || indx1<indx2)) {
                curr1=one[indx1];
                curr2=two[indx1];
            }
            if(indx2!=-1 && (indx1==-1 || indx2<indx1)) {
                curr1=one[indx2];
                curr2=two[indx2];
            }
        }
    }
    public static int next(int arr[],int val) {
        int l=0,r=arr.length-1,ans=-1;
        while(l<=r) {
            int mid=(l+r)/2;
            if(arr[mid]==val) {
                ans=mid;
                r=mid-1;
            }
            if(arr[mid]<val) {
                l=mid+1;
            }
            else {
                r=mid-1;
            }
        }
        return ans;
    }
}
