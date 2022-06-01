/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

/**
 *
 * @author SRESTH
 */
import java.util.*;
import java.io.*;
public class B_School_Marks {
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
        int k=input.scanInt();
        int p=input.scanInt();
        int x=input.scanInt();
        int y=input.scanInt();
        
        int arr[]=new int[k];
        int mid=(n+1)/2;
        mid--;
        int max=1;
        for(int i=0;i<k;i++) {
            arr[i]=input.scanInt();
            max=Math.max(max,arr[i]);
        }
        Arrays.sort(arr);
        
        boolean done=false;
        if(k==0) {
            done=true;
            StringBuilder tmp=new StringBuilder("");
            int brr[]=new int[n];
            int sum=0;
            for(int j=0;j<n;j++) {
                if(j<mid) {
                    brr[j]=1;
                }
                else {
                    brr[j]=y;
                }
                sum+=brr[j];
                tmp.append(brr[j]+" ");
            }
            if(sum<=x) {
                ans.append(tmp);
            }
            else {
                ans.append(-1);
            }
        }
        
        
        for(int i=0;!done && i<k;i++) {
            int cnt=0;
            if(arr[i]<y) {
                continue;
            }
            StringBuilder tmp=new StringBuilder("");
            int brr[]=new int[n];
            boolean is_pos=true;
            brr[mid]=arr[i];
            int indx1=mid-1;
            int indx2=mid+1;
            for(int j=0;j<k;j++) {
                if(j==i) {
                    continue;
                }
                if(arr[j]==arr[i]) {
                    continue;
                }
                if(arr[j]<arr[i]) {
                    if(indx1<0) {
                        is_pos=false;
                        break;
                    }
                    brr[indx1]=arr[j];
                    indx1--;
                }
                else {
                    if(indx2>n-1) {
                        is_pos=false;
                        break;
                    }
                    brr[indx2]=arr[j];
                    indx2++;
                }
            }
            boolean skip=true;
            for(int j=0;j<k;j++) {
                if(arr[j]==arr[i]) {
                    if(skip) {
                        skip=false;
                        continue;
                    }
                    if(indx2<=n-1) {
                        brr[indx2]=arr[j];
                        indx2++;
                    }
                    else if(indx1>=0) {
                        brr[indx1]=arr[j];
                        indx1--;
                    }
                    else {
                        is_pos=false;
                        break;
                    }
                }
            }
            if(!is_pos) {
                continue;
            }
            for(int j=0;j<n;j++) {
                if(brr[j]==0) {
                    brr[j]=1;
                    cnt++;
                    tmp.append(brr[j]+" ");
                }
                else {
                    break;
                }
            }
            for(int j=n-1;j>=0;j--) {
                if(brr[j]==0) {
                    brr[j]=arr[i];
                    cnt++;
                    tmp.append(brr[j]+" ");
                }
                else {
                    break;
                }
            }
            int sum=0;
            for(int j=0;j<n;j++) {
                sum+=brr[j];
            }
            if(sum>x) {
                continue;
            }
//            System.out.println(cnt);
            done=true;
            ans.append(tmp);
            break;
        }
        if(!done) {
            int cnt=0;
            StringBuilder tmp=new StringBuilder("");
            tmp.append(y+" ");
            int brr[]=new int[n];
            boolean is_pos=true;
            brr[mid]=y;
            int indx1=mid-1;
            int indx2=mid+1;
            for(int j=0;j<k;j++) {
                if(arr[j]==y) {
                    continue;
                }
                if(arr[j]<y) {
                    if(indx1<0) {
                        is_pos=false;
                        break;
                    }
                    brr[indx1]=arr[j];
                    indx1--;
                }
                else {
                    if(indx2>n-1) {
                        is_pos=false;
                        break;
                    }
                    brr[indx2]=arr[j];
                    indx2++;
                }
            }
            for(int j=0;j<k;j++) {
                if(arr[j]==y) {
                    if(indx2<=n-1) {
                        brr[indx2]=arr[j];
                        indx2++;
                    }
                    else if(indx1>=0) {
                        brr[indx1]=arr[j];
                        indx1--;
                    }
                    else {
                        is_pos=false;
                        break;
                    }
                }
            }
            for(int j=0;j<n;j++) {
                if(brr[j]==0) {
                    cnt++;
                    brr[j]=1;
                    tmp.append(brr[j]+" ");
                }
                else {
                    break;
                }
            }
            for(int j=n-1;j>=0;j--) {
                if(brr[j]==0) {
                    cnt++;
                    brr[j]=y;
                    tmp.append(brr[j]+" ");
                }
                else {
                    break;
                }
            }
//            System.out.println(cnt);
            int sum=0;
            for(int j=0;j<n;j++) {
                sum+=brr[j];
            }
            if(sum>x) {
                is_pos=false;
            }
            if(!is_pos) {
                ans.append(-1);
            }
            else {
                ans.append(tmp);
            }
        }
        System.out.println(ans);
    }
}
