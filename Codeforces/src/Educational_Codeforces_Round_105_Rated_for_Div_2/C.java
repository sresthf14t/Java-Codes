/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Educational_Codeforces_Round_105_Rated_for_Div_2;

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
        StringBuilder ans=new StringBuilder("");
        int test=input.scanInt();
        for(int tt=1;tt<=test;tt++) {
            int n=input.scanInt();
            int m=input.scanInt();
            int box[]=new int[n];
            int pos[]=new int[m];
            int pb=0,pp=0,nb=0,np=0;
            for(int i=0;i<n;i++) {
                box[i]=input.scanInt();
                if(box[i]<0) {
                    nb++;
                }
                else {
                    pb++;
                }
            }
            for(int i=0;i<m;i++) {
                pos[i]=input.scanInt();
                if(pos[i]<0) {
                    np++;
                }
                else {
                    pp++;
                }
            }
            
            int pbox[]=new int[pb];
            int nbox[]=new int[nb];
            int ppos[]=new int[pp];
            int npos[]=new int[np];
            
            pb=pp=nb=np=0;
            for(int i=0;i<n;i++) {
                if(box[i]<0) {
                    nbox[nb]=-1*box[i];
                    nb++;
                }
                else {
                    pbox[pb]=box[i];
                    pb++;
                }
            }
            for(int i=0;i<m;i++) {
                if(pos[i]<0) {
                    npos[np]=-1*pos[i];
                    np++;
                }
                else {
                    ppos[pp]=pos[i];
                    pp++;
                }
            }
            
            if(nbox.length>0) {
                sort(nbox,0,nbox.length-1);
            }
            if(pbox.length>0) {
                sort(pbox,0,pbox.length-1);
            }
            if(npos.length>0) {
                sort(npos,0,npos.length-1);
            }
            if(ppos.length>0) {
                sort(ppos,0,ppos.length-1);
            }
            
            
            int fin=solve(nb,np,nbox,npos)+solve(pb,pp,pbox,ppos);
            ans.append(fin+"\n");
        }
        System.out.println(ans);
    }
    public static int solve(int n,int m,int box[],int pos[]) {
        if(n==0 || m==0) {
            return 0;
        }
        int cc[]=new int[n];
        int tc=0;
        HashSet<Integer> hashset=new HashSet<>();
        for(int i=0;i<m;i++) {
            hashset.add(pos[i]);
        }
        for(int i=n-1;i>=0;i--) {
            if(hashset.contains(box[i])) {
                tc++;
            }
            cc[i]=tc;
        }
        int cnt=1,max=0;
        int bindx=0,pindx=-2,bpos=box[0];
        for(int i=0;i<m;i++) {
            if(pos[i]>=box[0]) {
                pindx=i-1;
                break;
            }
        }
        
        if(pindx==-2) {
            return 0;
        }
        
        
        while(true) {
            int l=bpos-cnt+1,r=bpos;
            int lindx=jgt(pos,l),rindx=jlt(pos,r);
            if(lindx==-1 || rindx==-1 || lindx>rindx);
            else {
                max=Math.max(max,rindx-lindx+1+((bindx+1)<n?cc[bindx+1]:0));
            }
//            System.out.println(n+" "+m+" "+bindx+" "+pindx);
            if(bindx==n-1 && pindx==m-1) {
                break;
            }
            if(bindx<n-1 && pindx<m-1 && pos[pindx+1]<box[bindx+1]) {
                bpos=pos[pindx+1];
                pindx++;
            }
            else if(bindx<n-1 && pindx<m-1 && pos[pindx+1]>box[bindx+1]) {
                bpos=box[bindx+1];
                cnt++;
                bindx++;
            }
            else if(bindx<n-1 && pindx<m-1 && pos[pindx+1]==box[bindx+1]) {
                bpos=box[bindx+1];
                cnt++;
                bindx++;
                pindx++;
            }
            else if(bindx==n-1) {
                bpos=pos[pindx+1];
                pindx++;
            }
            else if(pindx==m-1) {
                bpos=box[bindx+1];
                cnt++;
                bindx++;
            }
            l=bpos-cnt+1;
            r=bpos;
            lindx=jgt(pos,l);
            rindx=jlt(pos,r);
            if(lindx==-1 || rindx==-1 || lindx>rindx) {
                continue;
            }
            max=Math.max(max,rindx-lindx+1+((bindx+1)<n?cc[bindx+1]:0));
        }
        return max;
    }
    
    public static int jlt(int arr[],int val) {
        int l=0,r=arr.length-1,ans=-1;
        while(l<=r) {
            int mid=(l+r)/2;
            if(arr[mid]<=val) {
                ans=mid;
                l=mid+1;
            }
            else {
                r=mid-1;
            }
        }
        return ans;
    }
    
    public static int jgt(int arr[],int val) {
        int l=0,r=arr.length-1,ans=-1;
        while(l<=r) {
            int mid=(l+r)/2;
            if(arr[mid]>=val) {
                ans=mid;
                r=mid-1;
            }
            else {
                l=mid+1;
            }
        }
        return ans;
    }
}
