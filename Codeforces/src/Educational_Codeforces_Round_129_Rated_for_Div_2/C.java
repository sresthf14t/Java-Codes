/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Educational_Codeforces_Round_129_Rated_for_Div_2;

/**
 *
 * @author SRESTH
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
        StringBuilder ans=new StringBuilder("");
        int test=input.scanInt();
        for(int tt=1;tt<=test;tt++) {
            int n=input.scanInt();
//            int n=5;
            int arr[]=new int[n];
            int brr[]=new int[n];
            int a_cpy[]=new int[n];
            int b_cpy[]=new int[n];
            int a_tmp[]=new int[n];
            int b_tmp[]=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt();
//                arr[i]=(int)(Math.random()*10);
                a_cpy[i]=arr[i];
                a_tmp[i]=arr[i];
            }
            for(int i=0;i<n;i++) {
                brr[i]=input.scanInt();
//                brr[i]=(int)(Math.random()*10);
                b_cpy[i]=brr[i];
                b_tmp[i]=brr[i];
            }
            sort(a_cpy,0,n-1);
            sort(b_cpy,0,n-1);
            boolean is_pos=true;
            boolean taken[]=new boolean[n];
            int cnt=0;
            StringBuilder tmp=new StringBuilder("");
            for(int i=0;i<n;i++) {
                int indx=check(n,arr,brr,a_cpy[i],b_cpy[i],taken);
                if(indx==-1) {
                    is_pos=false;
                    break;
                }
                if(indx==i) {
                    taken[i]=true;
                    continue;
                }
                cnt++;
                swap(arr,i,indx);
                swap(brr,i,indx);
                taken[i]=true;
                tmp.append((i+1)+" "+(indx+1)+"\n");
//                System.out.println("-----------------------"+i+"-----------------------");
//                System.out.println(indx);
//                print(arr);
//                print(brr);
//                System.out.println("---------------------------------------------------");
            }
            for(int i=1;is_pos && i<n;i++) {
                if(arr[i]<arr[i-1]) {
//                    print(a_tmp);
//                    print(b_tmp);
//                    print(arr);
//                    print(brr);
//                    print(a_cpy);
//                    print(b_cpy);
//                    System.out.println(cnt+"\n"+tmp+"\n");
//                    System.exit(0);
//                    System.out.println(1111111);
//                    is_pos=false;
                }
                if(brr[i]<brr[i-1]) {
//                    print(a_tmp);
//                    print(b_tmp);
//                    print(arr);
//                    print(brr);
//                    print(a_cpy);
//                    print(b_cpy);
//                    System.out.println(cnt+"\n"+tmp+"\n");
//                    System.exit(0);
//                    System.out.println(1111111);
//                    is_pos=false;
                }
            }
            if(!is_pos) {
                ans.append(-1+"\n");
                continue;
            }
            ans.append(cnt+"\n"+tmp+"\n");
        }
        System.out.print(ans);
    }
    
    public static void print(int arr[]) {
        System.out.println();
        for(int i=0;i<arr.length;i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
    
    public static void swap(int arr[],int i,int j) {
        int tmp=arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }
    
    public static int check(int n,int arr[],int brr[],int up,int dwn,boolean taken[]) {
        for(int i=0;i<n;i++) {
            if(taken[i]) {
                continue;
            }
            if(arr[i]==up && brr[i]==dwn) {
                return i;
            } 
        }
        return -1;
    }
    
    public static void solve(int indx,int arr[],int brr[]) {
        
    }
    
}
