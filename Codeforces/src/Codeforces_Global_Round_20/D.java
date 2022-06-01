/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Global_Round_20;

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
    
    static int target[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        int test=input.scanInt();
        for(int tt=1;tt<=test;tt++) {
            int n=input.scanInt();
//            int n=5;
            int arr[]=new int[n];
            int brr[]=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt()-1;
//                arr[i]=(int)(Math.random()*n);
            }
            for(int i=0;i<n;i++) {
                brr[i]=input.scanInt()-1;
//                brr[i]=arr[i];
            }
//            for(int i=0;i<n/2;i++) {
//                int l=(int)(Math.random()*n);
//                int r=(int)(Math.random()*n);
//                
//                int tmp=arr[l];
//                arr[l]=arr[r];
//                arr[r]=tmp;
//            }
            target=brr;
            int[] arr_1=solve(n,arr);
            int[] arr_2=solve(n,brr);
            boolean is_pos=true;
            for(int i=0;i<n;i++) {
//                System.out.println(i+" "+arr_1[i]+" "+arr_2[i]);
                if(arr_1[i]!=arr_2[i]) {
                    is_pos=false;
                    break;
                }
            }
            if(is_pos) {
                int req[]=new int[n];
                
                int nxt=-1;
                for(int i=n-1,j=n-1;i>=0 || j>=0;i--) {
//                    System.out.println(j+" "+i+" "+nxt);
                    if(i<0) {
                        if(req[arr[j]]>0) {
                            req[arr[j]]--;
                        }
                        else {
                            is_pos=false;
                            break;
                        }
                        j--;
                        continue;
                    }
                    if(arr[j]!=brr[i]) {
                        if(nxt!=brr[i]) {
                            if(req[arr[j]]>0) {
                                req[arr[j]]--;
                                j--;
                                i++;
                                continue;
                            }
                            is_pos=false;
                            
                            break;
                        }
                        else {
                            req[brr[i]]++;
                        }
                    }
                    else {
                        nxt=arr[j];
                        j--;
                    }
                }
                
                for(int i=0;i<n;i++) {
//                    System.out.println(i+" "+req[i]);
                    if(req[i]!=0) {
                        is_pos=false;
//                        break;
                    }
                }
                
                int tog_a[]=new int[n];
                int tog_b[]=new int[n];
                for(int i=n-1;i>=0;i--) {
                    if(tog_a[arr[i]]!=0) {
                        continue;
                    }
                    int j=i,cnt=1;
                    while(j>0 && arr[j-1]==arr[i]) {
                        cnt++;
                        j--;
                    }
                    tog_a[arr[i]]=cnt;
                }
                
                for(int i=n-1;i>=0;i--) {
                    if(tog_b[brr[i]]!=0) {
                        continue;
                    }
                    int j=i,cnt=1;
                    while(j>0 && brr[j-1]==brr[i]) {
                        cnt++;
                        j--;
                    }
                    tog_b[brr[i]]=cnt;
                }
                
//                for(int i=0;i<n;i++) {
//                    System.out.println(i+" "+tog_a[i]+" "+tog_b[i]);
//                }
//                System.out.println();
//                for(int i=0;i<n;i++) {
//                    if(tog_a[i]>tog_b[i]) {
//                        is_pos=false;
//                        break;
//                    }
//                }
            }
//            for(int i=0;i<n;i++) {
//                System.out.print(arr_1[i]+" ");
//            }
//            System.out.println();
//            for(int i=0;i<n;i++) {
//                System.out.print(arr_2[i]+" ");
//            }
//            System.out.println();
            
//            boolean bf=bf(n,arr,0);
//            if(is_pos!=bf) {
//                System.out.println(is_pos+" "+bf);
//                for(int i=0;i<n;i++) {
//                    System.out.print(arr[i]+" ");
//                }
//                System.out.println();
//                for(int i=0;i<n;i++) {
//                    System.out.print(brr[i]+" ");
//                }
//                System.out.println();
//            }
            
            if(is_pos) {
                ans.append("YES\n");
            }
            else {
                ans.append("NO\n");
            }
        }
        System.out.println(ans);
    }
    
    public static int[] solve(int n,int arr[]) {
        HashMap<Integer,Integer> map=new HashMap<>();
        HashMap<Integer,Integer> cnt=new HashMap<>();
        for(int i=n-1;i>=0;i--) {
            if(!map.containsKey(arr[i])) {
                map.put(arr[i], i);
                cnt.put(arr[i], 0);
            }
            cnt.replace(arr[i], cnt.get(arr[i])+1);
        }
        
        int brr[]=new int[n];
        for(int i=0,indx=0;i<n;i++) {
            if(map.get(arr[i])==i) {
                int rep=cnt.get(arr[i]);
                for(int j=0;j<rep;j++) {
                    brr[indx]=arr[i];
                    indx++;
                }
            }
        }
        return brr;
    }
    
    public static boolean bf(int n,int arr[],int dep) {
        if(check(n,arr)) {
            return true;
        }
        if(dep>10) {
            return false;
        }
        for(int i=0;i<n;i++) {
            for(int j=i+1;j<n;j++) {
                if(arr[i]==arr[j]) {
                    swap(arr,i,j);
                    if(bf(n,arr,dep+1)) {
                        rev_swap(arr,i,j);
                        return true;
                    }
                    rev_swap(arr,i,j);
                }
            }
        }
        return false;
    }
    
    public static void swap(int arr[],int l,int r) {
        int tmp=arr[l];
        for(int i=l;i<r;i++) {
            arr[i]=arr[i+1];
        }
        arr[r]=tmp;
    }
    
    public static void rev_swap(int arr[],int l,int r) {
        int tmp=arr[r];
        for(int i=r;i>l;i--) {
            arr[i]=arr[i-1];
        }
        arr[l]=tmp;
    }
    
    public static boolean check(int n,int arr[]) {
        for(int i=0;i<n;i++) {
            if(arr[i]!=target[i]) {
                return false;
            }
        }
        return true;
    }
}
