/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package January_Challenge_2021_Division_3;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class SQSORT {
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
        int b=input.scanInt();
//        int n=10;
//        int b=100;
        Queue<Integer> que[]=new LinkedList[n];
        for(int i=0;i<n;i++) {
            int tmp=input.scanInt();
        }
        for(int i=0;i<n;i++) {
            int tmp=input.scanInt();
        }
        for(int i=0;i<b;i++) {
            int tmp=input.scanInt();
        }
        
        for(int i=0;i<n;i++) {
            que[i]=new LinkedList<>();
            int num=input.scanInt();
//            int num=b;
//            if(i!=0) {
//                continue;
//            }
            for(int j=1;j<=num;j++) {
                que[i].add(input.scanInt());
//                que[i].add(num-j+1);
            }
        }
        
        for(int i=0;i<n;i++) {
            System.out.print("Q");
        }
        System.out.println();
        
        int cnt=0;
        while(!que[0].isEmpty()) {
            int indx=(int)(Math.random()*(n-1));
            if(indx==0) {
                continue;
            }
            ans.append(1+" "+(indx+1)+"\n");
            cnt++;
            que[indx].add(que[0].poll());
        }
        
        int lim=(b/(n-1))+1;
        for(int i=1;i<n;i++) {
            if(que[i].size()<=lim) {
                continue;
            }
            for(int j=1;j<n;j++) {
                if(que[j].size()<lim) {
                    ans.append((i+1)+" "+(j+1)+"\n");
                    cnt++;
                    que[j].add(que[i].poll());
                    break;
                }
            }
        }
        
        for(int i=1;i<=b;i++) {
            int indx=-1;
            for(int j=1;j<n;j++) {
                if(que[j].contains(i)) {
                    indx=j;
                    break;
                }
            }
//            System.out.println(i);
            while(que[indx].peek()!=i) {
                ans.append((indx+1)+" "+(indx+1)+"\n");
                cnt++;
                que[indx].add(que[indx].poll());
            }
            ans.append((indx+1)+" "+1+"\n");
            cnt++;
            que[0].add(que[indx].poll());
        }
        
        while(cnt>(b*b)/2);
        
        System.out.println(cnt+"\n"+ans);
//        
//        System.out.println();
//        while(!que[0].isEmpty()) {
//            System.out.print(que[0].poll()+" ");
//        }
//        System.out.println();
    }
}
