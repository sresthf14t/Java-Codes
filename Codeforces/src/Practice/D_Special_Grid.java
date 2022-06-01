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
public class D_Special_Grid {
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
    static int n,m,arr[][],prefixr[][],prefixc[][],prefixdp[][],prefixdn[][];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n=input.scanInt();
        m=input.scanInt();
//        n=50;
//        m=50;
        arr=new int[n][m];
        prefixr=new int[n][m];
        prefixc=new int[n][m];
        prefixdp=new int[n][m];
        prefixdn=new int[n][m];
        for(int i=0;i<n;i++) {
            String str=input.scanString();
            for(int j=0;j<m;j++) {
                arr[i][j]=str.charAt(j)-'0';
//                arr[i][j]=0;
            }
        }
        for(int i=0;i<n;i++) {
            int sum=0;
            for(int j=0;j<m;j++) {
                sum+=arr[i][j];
                prefixr[i][j]=sum;
            }
        }
        
        for(int i=0;i<m;i++) {
            int sum=0;
            for(int j=0;j<n;j++) {
                sum+=arr[j][i];
                prefixc[j][i]=sum;
            }
        }
        
        for(int x=0;x<n;x++) {
            int i=x,j=0,sum=0;
            while(i<n && j<m) {
                sum+=arr[i][j];
                prefixdp[i][j]=sum;
                i++;
                j++;
            }
        }
        
        for(int x=0;x<m;x++) {
            int i=0,j=x,sum=0;
            while(i<n && j<m) {
                sum+=arr[i][j];
                prefixdp[i][j]=sum;
                i++;
                j++;
            }
        }
        
        
        for(int x=0;x<n;x++) {
            int i=x,j=m-1,sum=0;
            while(i<n && j>=0) {
                sum+=arr[i][j];
                prefixdn[i][j]=sum;
                i++;
                j--;
            }
        }
        
        for(int x=0;x<m;x++) {
            int i=0,j=x,sum=0;
            while(i<n && j>=0) {
                sum+=arr[i][j];
                prefixdn[i][j]=sum;
                i++;
                j--;
            }
        }
        
        int ans=0;
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                ans+=get(i,j);
//                System.out.println(i+" "+j+" "+ans);
            }
        }
        System.out.println(ans);
    }
    public static int get(int x,int y) {
        int ans=0;
        int i=x+1;
        int j=y;
        while(i<n) {
            ans+=solvec(x,y,i,j);
            i++;
        }
        

        i=x;
        j=y+1;
        while(j<m) {
            ans+=solver(x,y,i,j);
            j++;
        }

        return ans;
    }
    public static int solver(int r1,int c1,int r2,int c2) {
        if(!check(r1,c1,r2,c2)) {
            return 0;
        }
        int ans=0;
        
        int diff=Math.abs(c1-c2);
        if(r1-diff>=0) {
            if(check(r1,c1,r1-diff,c1) && check(r2,c2,r1-diff,c1)) {
                ans++;
            }
        }
        if(r1+diff<n) {
            if(check(r1,c1,r1+diff,c1) && check(r2,c2,r1+diff,c1)) {
                ans++;
            }
        }
        
        if(r2-diff>=0) {
            if(check(r1,c1,r2-diff,c2) && check(r2,c2,r2-diff,c2)) {
                ans++;
            }
        }
        if(r2+diff<n) {
            if(check(r1,c1,r2+diff,c2) && check(r2,c2,r2+diff,c2)) {
                ans++;
            }
        }
        
        if(Math.abs(c1-c2)%2==0) {
            int r3=r1-(Math.abs(c1-c2)/2);
            int c3=(c1+c2)/2;
            if(r3>=0 && check(r1,c1,r3,c3) && check(r2,c2,r3,c3)) {
                ans++;
            }
        }
        if(Math.abs(c1-c2)%2==0) {
            int r3=r1+(Math.abs(c1-c2)/2);
            int c3=(c1+c2)/2;
            if(r3<n && check(r1,c1,r3,c3) && check(r2,c2,r3,c3)) {
                ans++;
            }
        }
        return ans;
    }
    
    public static int solvec(int r1,int c1,int r2,int c2) {
        if(!check(r1,c1,r2,c2)) {
            return 0;
        }
        int ans=0;
        if(Math.abs(r1-r2)%2==0) {
            int r3=(r1+r2)/2;
            int c3=c1+Math.abs(r1-r2)/2;
            if(c3<m && check(r1,c1,r3,c3) && check(r2,c2,r3,c3)) {
                ans++;
            }
        }
        if(Math.abs(r1-r2)%2==0) {
            int r3=(r1+r2)/2;
            int c3=c1-Math.abs(r1-r2)/2;
            if(c3>=0 && check(r1,c1,r3,c3) && check(r2,c2,r3,c3)) {
                ans++;
            }
        }
        return ans;
    }
    
    public static boolean check(int r1,int c1,int r2,int c2) {
        
//        System.out.println(r1+" "+c1+" "+r2+" "+c2);
        
        if(r1==r2) {
            int sum=prefixr[r1][Math.max(c1,c2)]-((Math.min(c1,c2)==0)?0:prefixr[r1][Math.min(c1,c2)-1]);
            if(sum>0) {
                return false;
            }
            return true;
        }
        if(c1==c2) {
            int sum=prefixc[Math.max(r1,r2)][c1]-((Math.min(r1,r2)==0)?0:prefixc[Math.min(r1,r2)-1][c1]);
            if(sum>0) {
                return false;
            }
            return true;
        }
        if(Math.abs(r1-r2)!=Math.abs(c1-c2)) {
            return false;
        }
        if(r2>r1 && c2>c1) {
            int sum=prefixdp[r2][c2]-(r1==0|c1==0?0:prefixdp[r1-1][c1-1]);
            if(sum>0) {
                return false;
            }
            return true;
        }
        if(r1>r2 && c1>c2) {
            int sum=prefixdp[r1][c1]-(r2==0|c2==0?0:prefixdp[r2-1][c2-1]);
            if(sum>0) {
                return false;
            }
            return true;
        }
        if(r2>r1) {
            int sum=prefixdn[r2][c2]-(r1==0|c1==m-1?0:prefixdn[r1-1][c1+1]);
            if(sum>0) {
                return false;
            }
            return true;
        }
        if(r1>r2) {
            int sum=prefixdn[r1][c1]-(r2==0|c2==m-1?0:prefixdn[r2-1][c2+1]);
            if(sum>0) {
                return false;
            }
            return true;
        }
        return true;
    }
}
