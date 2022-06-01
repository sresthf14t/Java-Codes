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
public class D_The_Door_Problem {
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
    
    static int n,m,state[];
    static ArrayList<Integer> arrli[],indx[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n=input.scanInt();
        m=input.scanInt();
        arrli=new ArrayList[m];
        indx=new ArrayList[n];
        for(int i=0;i<n;i++) {
            indx[i]=new ArrayList<>();
        }
        state=new int[n];
        for(int i=0;i<n;i++) {
            state[i]=input.scanInt();
        }
        for(int i=0;i<m;i++) {
            int cnt=input.scanInt();
            arrli[i]=new ArrayList<>();
            for(int j=0;j<cnt;j++) {
                int tmp=input.scanInt()-1;
                arrli[i].add(tmp);
                indx[tmp].add(i);
            }
        }
        
        
        for(int i=0;i<n;i++) {
            if(state[i]==1) {
                continue;
            }
            if(!get(i,indx[i].get(0)) && !get(i,indx[i].get(1))) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }
    public static boolean get(int val,int strt) {
        TreeSet<Integer> zero=new TreeSet<>();
        TreeSet<Integer> one=new TreeSet<>();
        TreeSet<Integer> taken=new TreeSet<>();
        taken.add(strt);
        for(int i=0;i<arrli[strt].size();i++) {
            if(state[arrli[strt].get(i)]==1) {
                one.add(arrli[strt].get(i));
            }
            else {
                zero.add(arrli[strt].get(i));
            }
        }
        while(true) {
            if(one.size()==0) {
                break;
            }
            int tmp=one.first();
            int idx=indx[tmp].get(0);
            if(taken.contains(idx)) {
                idx=indx[tmp].get(1);
            }
            if(taken.contains(idx)) {
                return false;
            }
            strt=idx;
            taken.add(strt);
            for(int i=0;i<arrli[strt].size();i++) {
                if(state[arrli[strt].get(i)]==1) {
                    if(one.contains(arrli[strt].get(i))) {
                        one.remove(arrli[strt].get(i));
                    }
                    else {
                        one.add(arrli[strt].get(i));
                    } 
                }
                else {
                    if(zero.contains(arrli[strt].get(i))) {
                        zero.remove(arrli[strt].get(i));
                    }
                    else {
                        zero.add(arrli[strt].get(i));
                    } 
                }
            }
        }
        if(zero.size()==0) {
            return false;
        }
        boolean is_pos=false;
        for(int i:zero) {
            state[i]=1;
            if(i==val) {
                is_pos=true;
            }
        }
        if(!is_pos) {
            for(int i:zero) {
                state[i]=0;
            }
            return false;
        }
        for(int i:one) {
            state[i]=0;
        }
        return true;
    }
}
