/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_720_Div_2;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
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
        
        StringBuilder fin=new StringBuilder("! ");
        
        int test=input.scanInt();
        for(int tt=1;tt<=test;tt++) {
            int n=input.scanInt();
            
//            int n=(int)(Math.random()*100);
            
            int arr[]=new int[n];
            int ff[]=new int[n];
            int ss[]=new int[n];
            
//            int acc[]=new int[n];
//            for(int i=0;i<n;i++) {
////                acc[i]=i+1;
//                acc[i]=input.scanInt();
//            }
//            acc[0]=2;
//            acc[1]=1;
//            acc[8]=10;
//            acc[9]=9;
            
//            per(n,acc);
            
//            for(int i=0;i<n;i++) {
//                System.out.print(acc[i]+" ");
//            }
//            System.out.println();
            
            for(int i=0;i<n-1;i++) {
                System.out.println("? 1 "+(i+1)+" "+(i+1+1)+" "+(n-1));
                System.out.flush();
                ff[i]=input.scanInt();
//                ff[i]=Math.max(Math.min(acc[i],(n-1)), Math.min(acc[i+1],(n-1+1)));
                System.out.println("? 2 "+(i+1)+" "+(i+1+1)+" "+(1));
                System.out.flush();
                ss[i]=input.scanInt();
                
                if(ff[i]==1 || ff[i]==2) {
                    ff[i]=0;
                }
                if(ss[i]==n-1 || ss[i]==n) {
                    ss[i]=0;
                }
//                ss[i]=Math.min(Math.max(acc[i],1), Math.max(acc[i+1],(1+1)));
//                System.out.println(ff[i]+" "+ss[i]);
                
                if(ff[i]==ss[i]) {
                    System.out.println("? 1 "+(i+1+1)+" "+(i+1)+" "+(n-1));
                    System.out.flush();
                    ff[i]=input.scanInt();
//                    ff[i]=Math.max(Math.min(acc[i+1],(n-1)), Math.min(acc[i],(n-1+1)));
                    System.out.println("? 2 "+(i+1+1)+" "+(i+1)+" "+(1));
                    System.out.flush();
                    ss[i]=input.scanInt();
//                    ss[i]=Math.min(Math.max(acc[i+1],1), Math.max(acc[i],(1+1)));
//                    System.out.println(ff[i]+" "+ss[i]);
                    if(ff[i]==1 || ff[i]==2) {
                        ff[i]=0;
                    }
                    if(ss[i]==n-1 || ss[i]==n) {
                        ss[i]=0;
                    }
                }
                
            }
            boolean taken[]=new boolean[n+1];
            for(int i=1;i<n;i++) {
                if(!taken[ff[i]] && ff[i]==ff[i-1] || ff[i]==ss[i-1]) {
                    arr[i]=ff[i];
                    taken[ff[i]]=true;
                    continue;
                }
                if(!taken[ss[i]] && ss[i]==ff[i-1] || ss[i]==ss[i-1]) {
                    arr[i]=ss[i];
                    taken[ss[i]]=true;
                }
            }
            
            int vote[]=new int[n+1];
            for(int i=0;i<n;i++) {
                
                if(arr[i]!=0) {
                    continue;
                }
                int cnt=0;
                for(int j=0;j<n;j++) {
                    if(cnt>11) {
                        break;
                    }
                    if(arr[j]==0) {
                        continue;
                    }
                    cnt++;
                    System.out.println("? 1 "+(i+1)+" "+(j+1)+" "+(n-1));
                    System.out.flush();
                    int tmp=input.scanInt();
                    vote[input.scanInt()]++;
//                    vote[Math.max(Math.min(acc[i],(n-1)), Math.min(acc[j],(n-1+1)))]++;
                    System.out.println("? 2 "+(i+1)+" "+(j+1)+" "+(1));
                    System.out.flush();
                    vote[input.scanInt()]++;
//                    vote[Math.min(Math.max(acc[i],1), Math.max(acc[j],(1+1)))]++;
//                    System.out.println(ff[i]+" "+ss[i]);
                }
                
                int max=-1,val=-1;
                for(int j=1;j<n+1;j++) {
                    if(taken[j]);
                    else if(vote[j]>max) {
                        max=vote[j];
                        val=j;
                    }
                    vote[j]=0;
                }
                arr[i]=val;
                taken[val]=true;
            }
            
            StringBuilder ans=new StringBuilder("! ");
            int diff=0;
            for(int i=0;i<n;i++) {
                ans.append(arr[i]+" ");
//                if(arr[i]!=acc[i]) {
//                    diff++;
//                }
            }
            System.out.println(ans);
            System.out.flush();
            
//            if(diff>0) {
//                System.out.println(diff+" "+n);
//                for(int i=0;i<n;i++) {
//                    System.out.print(acc[i]+" ");
//                }
//                System.out.println();
//                for(int i=0;i<n;i++) {
//                    System.out.print(arr[i]+" ");
//                }
//                System.out.println();
//                break;
//            }
            
        }
//        System.out.println(fin);
    }
    public static void per(int n,int arr[]) {
        for(int i=0;i<n/4;i++) {
            int x=(int)(Math.random()*(n-1));
            int y=(int)(Math.random()*(n-1));
            int tmp=arr[x];
            arr[x]=arr[y];
            arr[y]=tmp;
        }
    }
}
