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
public class D_Lucky_Common_Subsequence {
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
    
    static String str1,str2,virus;
    static int dp[][][],done[][][],kmp[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        
        str1=input.scanString();
        str2=input.scanString();
        virus=input.scanString();
        
        kmp=create_KMP_Array(virus.length(),virus);
        
//        for(int i=0;i<virus.length();i++) {
//            System.out.print(kmp[i]+" ");
//        }
//        System.out.println();
        
        dp=new int[105][105][105];
        done=new int[105][105][105];
        for(int i=0;i<dp.length;i++) {
            for(int j=0;j<dp[0].length;j++) {
                for(int k=0;k<dp[0][0].length;k++) {
                    dp[i][j][k]=-1;
                    done[i][j][k]=-1;
                }
            }
        }
        int curr = solve(0,0,0);
        int mi=-1,mj=-1,vp=0;
        
        for(int i=0;i<str1.length() && curr>0;i++) {
            for(int j=0;j<str2.length() && curr>0;j++) {
                for(int k=0;k<virus.length() && curr>0;k++) {
                    if(mi!=-1 && k!=vp) {
                        continue;
                    }
                    if(done[i][j][k]!=1) {
                        continue;
                    }
                    if(str1.charAt(i)!=str2.charAt(j)) {
                        continue;
                    }
                    if(i<=mi || j<=mj) {
                        continue;
                    }
                    if(dp[i][j][k]!=curr) {
                        continue;
                    }
                    ans.append(str1.charAt(i));
                    if(str1.charAt(i)==virus.charAt(vp)) {
                        vp++;
                    }
                    else {
                        while(vp>0 && str1.charAt(i)!=virus.charAt(vp)) {
                            vp=kmp[vp-1];
                        }
                        if(str1.charAt(i)==virus.charAt(vp)) {
                            vp++;
                        }
                    }
                    mi=i;
                    mj=j;
                    curr--;
                }
            }
        }
        
        if(ans.length()==0) {
            ans.append(curr);
        }
        
//        System.out.println(solve(0,0,0)+" "+ans.length());
        
        System.out.println(ans);
    }
    
    public static int solve(int indx1,int indx2,int indx3) {
        
        if(indx1==str1.length() || indx2==str2.length()) {
            return 0;
        }
        if(dp[indx1][indx2][indx3]!=-1) {
            return dp[indx1][indx2][indx3];
        }
        done[indx1][indx2][indx3]=0;
        int ans=0;
        ans=Math.max(ans,solve(indx1+1,indx2,indx3));
        ans=Math.max(ans,solve(indx1,indx2+1,indx3));
        if(str1.charAt(indx1)==str2.charAt(indx2)) {
            if(str1.charAt(indx1)!=virus.charAt(indx3)) {
                int nxt=indx3;
                while(nxt>0 && str1.charAt(indx1)!=virus.charAt(nxt)) {
                    nxt=kmp[nxt-1];
                }
                if(str1.charAt(indx1)==virus.charAt(nxt)) {
                    nxt++;
                }
                if(nxt!=virus.length()) {
                    ans=Math.max(ans,1+solve(indx1+1,indx2+1,nxt));
                    if(ans==1+solve(indx1+1,indx2+1,nxt)) {
                        done[indx1][indx2][indx3]=1;
                    }
                }
                
            }
            else if(indx3+1!=virus.length()) {
                ans=Math.max(ans,1+solve(indx1+1,indx2+1,indx3+1));
                if(ans==1+solve(indx1+1,indx2+1,indx3+1)) {
                    done[indx1][indx2][indx3]=1;
                }
            }
        }
        dp[indx1][indx2][indx3]=ans;
//        System.out.println(indx1+" "+indx2+" "+indx3+" "+ans);
        return ans;
    }
    
    public static int[] create_KMP_Array(int n,String str) {
        int kmp[]=new int[n];
        kmp[0]=0;
        for(int i=1;i<n;i++) {
            int j=kmp[i-1];
            while(j>0 && str.charAt(i)!=str.charAt(j)) {
                j=kmp[j-1];
            }
            if(str.charAt(i)==str.charAt(j)) {
                j++;
            }
            kmp[i]=j;
        }
        return kmp;
    }
}
