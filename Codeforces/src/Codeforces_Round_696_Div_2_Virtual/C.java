/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_696_Div_2_Virtual;

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
    
    static int n,arr[],freq[],fin[],nxt[];
    static boolean done;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        int test=input.scanInt();
        for(int tt=1;tt<=test;tt++) {
            n=2*input.scanInt();
            arr=new int[n];
            freq=new int[1000001];
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt();
//                arr[i]=(int)(Math.random()*(1000000-1))+1;
                freq[arr[i]]++;
            }
            int tmp=freq.length;
            nxt=new int[freq.length];
            for(int i=freq.length-1;i>=0;i--) {
                nxt[i]=tmp;
                if(freq[i]!=0) {
                    tmp=i;
                }
            }
            sort(arr,0,arr.length-1);
            done=false;
            fin=new int[n/2];
            
            solve(0,0,false);
            
            if(!done) {
                ans.append("NO\n");
                continue;
            }
            ans.append("YES\n");
//            for(int i=0;i<fin.length;i++) {
//                System.out.print(fin[i]+" ");
//            }
//            System.out.println();
            ArrayList<Integer> arrli1=new ArrayList<>();
            ArrayList<Integer> arrli2=new ArrayList<>();
            for(int i=0;i<fin.length-1;i++) {
                arrli1.add(fin[i]);
                arrli2.add(fin[i+1]-fin[i]);
                rem(fin[i]);
                rem(fin[i+1]-fin[i]);
            }
            arrli1.add(fin[fin.length-1]);
            rem(fin[fin.length-1]);
            for(int i=0;i<n;i++) {
//                System.out.print(arr[i]+" ");
                if(arr[i]!=-1) {
                    arrli2.add(arr[i]);
                    rem(arr[i]);
                }
            }
//            System.out.println();
//            for(int i=0;i<arrli1.size();i++) {
//                System.out.print(arrli1.get(i)+" ");
//            }
//            System.out.println();
//            for(int i=0;i<arrli2.size();i++) {
//                System.out.print(arrli2.get(i)+" ");
//            }
//            System.out.println();
            ans.append((arrli1.get(arrli1.size()-1)+arrli2.get(arrli2.size()-1))+"\n");
            for(int i=arrli1.size()-1;i>=0;i--) {
                ans.append(arrli1.get(i)+" "+arrli2.get(i));
                ans.append("\n");
            }
        }
        System.out.println(ans);
    }
    public static void rem(int ele) {
        for(int i=0;i<n;i++) {
            if(arr[i]==ele) {
                arr[i]=-1;
                break;
            }
        }
    }
    public static void solve(int findx,int indx,boolean strt) {
        if(indx==(n/2)-1) {
            boolean is_pos=false;
            for(int i=0;i<n && arr[i]<findx;i++) {
                if(freq[arr[i]]!=0) {
                    is_pos=true;
                    break;
                }
            }
            if(is_pos) {
                fin[indx]=findx;
                done=true;
            }
            
        }
        if(done || findx>=freq.length) {
            return;
        }
        
        if(!strt) {
            solve(nxt[findx],indx,false);
        }
        if(done) {
            return;
        }
        
        
        if(freq[findx]!=0 || strt) {
            for(int i=0;i<n && arr[i]<=findx && findx+arr[i]<freq.length;i++) {
                if(freq[arr[i]]==0) {
                    continue;
                }
                if(freq[findx+arr[i]]==0) {
                    continue;
                }
                freq[findx]--;
                freq[arr[i]]--;
                
                if(freq[findx+arr[i]]<0 || freq[findx]<0 || freq[arr[i]]<0) {
                    freq[findx]++;
                    freq[arr[i]]++;
                    continue;
                }
                fin[indx]=findx;
                
                solve(findx+arr[i],indx+1,true);
                
                if(done) {
                    break;
                }

                freq[findx]++;
                freq[arr[i]]++;
            }
        }
        
    }
}

