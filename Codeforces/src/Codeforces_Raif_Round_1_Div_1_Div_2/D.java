/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Raif_Round_1_Div_1_Div_2;

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
        StringBuilder ans=new StringBuilder("");
        int n=input.scanInt();
        int arr[]=new int[n];
        boolean taken[]=new boolean[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
            if(arr[i]==0) {
                taken[i]=true;
            }
        }
        int cnt=0,curr=1;
        ArrayList<Integer> arrli=new ArrayList<>();
        for(int i=n-1;i>=0;i--) {
            if(arrli.size()==0) {
                if(arr[i]==1) {
                    arrli.add(i);
                }
            }
            else if(arrli.size()==1) {
                if(arr[i]==2 || arr[i]==3) {
                    arrli.add(i);
                }
            }
            else {
                if(arr[i]==3) {
                    arrli.add(i);
                }
            }
        }
        for(int i=arrli.size()-1;i>=0 && arrli.size()>1;i--) {
            taken[arrli.get(i)]=true;
            if(i==arrli.size()-1) {
                ans.append(curr+" "+(arrli.get(i)+1)+"\n");
                cnt++;
            }
            else if(i!=0) {
                ans.append(curr+" "+(arrli.get(i)+1)+"\n");
                ans.append((curr+1)+" "+(arrli.get(i)+1)+"\n");
                curr++;
                cnt+=2;
            }
            else {
                ans.append(curr+" "+(arrli.get(i)+1)+"\n");
                if(arr[arrli.get(1)]==3) {
                    ans.append((curr+1)+" "+(arrli.get(i)+1)+"\n");
                    cnt++;
                    curr++;
                }
                cnt++;
                curr++;
            }
        }
        int indx2=0,indx1=0;
        for(int i=0;i<n;i++) {
            while(indx2<n) {
                if(arr[indx2]==2 && !taken[indx2]) {
                    break;
                }
                indx2++;
            }
            indx1=Math.max(indx2, indx1);
            while(indx1<n) {
                if(arr[indx1]==1 && !taken[indx1]) {
                    break;
                }
                indx1++;
            }
            if(indx2==n || indx1==n) {
                break;
            }
            taken[indx2]=taken[indx1]=true;
            ans.append(curr+" "+(indx2+1)+"\n");
            ans.append(curr+" "+(indx1+1)+"\n");
            curr++;
            cnt+=2;
        }
        for(int i=0;i<n;i++) {
            if(arr[i]==1 && !taken[i]) {
                ans.append(curr+" "+(i+1)+"\n");
                curr++;
                cnt++;
                taken[i]=true;
            }
        }
        for(int i=0;i<n;i++) {
            if(!taken[i]) {
                System.out.println(-1);
                return;
            }
        }
        if(cnt>2*n) {
            System.out.println(-1);
            return;
        }
        System.out.println(cnt+"\n"+ans);
    }
}
