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
public class C_Yet_Another_Counting_Problem {
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
        Scanner input=new Scanner(System.in);
        StringBuilder ans=new StringBuilder("");
        int test=input.nextInt();
        for(int tt=1;tt<=test;tt++) {
            long a=input.nextInt();
            long b=input.nextInt();
            if(a>b) {
                long tmp=a;
                a=b;
                b=tmp;
            }
            long lcm=lcm(a,b);
            int prefix[]=new int[(int)lcm];
            for(int i=1;i<lcm;i++) {
                prefix[i]=prefix[i-1];
                if(i%a==(i%b)%a) {
                    prefix[i]++;
                }
            }
            int q=input.nextInt();
            for(int qq=1;qq<=q;qq++) {
                long l=input.nextLong();
                long r=input.nextLong();
                long lft=l/lcm;
                long rgt=r/lcm;
//                System.out.println(lft+" "+rgt);
                lft+=2;
                rgt-=2;
                long fin=0;
                if(l<lcm) {
                    fin=prefix[(int)Math.min(lcm-1,r)]-prefix[(int)l-1];
                }
//                System.out.println(fin);
                if(lft<=rgt && lft>0) {
                    fin+=(rgt-lft+1)*b;
                }
//                System.out.println(fin);
//                System.out.println(lft+" "+rgt);
                Set<Long> hashset=new HashSet<>();
                for(long i=lft-2;i<lft;i++) {
                    long tmp=i*lcm;
                    for(int j=0;j<b;j++) {
                        long tmp1=tmp+j;
//                        System.out.println(tmp1);
                        if(!hashset.contains(tmp1) && tmp1>=lcm && tmp1>=l && tmp1<=r && tmp1%a==(tmp1%b)%a) {
                            fin++;
                            hashset.add(tmp1);
                        }
                    }
                }
                for(long i=rgt+2;i>rgt;i--) {
                    long tmp=i*lcm;
                    for(int j=0;j<b;j++) {
                        long tmp1=tmp+j;
//                        System.out.println("rgt"+" "+tmp1);
                        if(!hashset.contains(tmp1) && tmp1>=lcm && tmp1>=l && tmp1<=r && tmp1%a==(tmp1%b)%a) {
                            fin++;
                        }
                    }
                }
                fin=(r-l+1)-fin;
                ans.append(fin+" ");
            }
            ans.append("\n");
        }
        System.out.println(ans);
    }
    public static long lcm(long a,long b) {
        for(long i=b;;i++) {
            if(i%a==0 && i%b==0) {
                return i;
            }
        }
    }
}
