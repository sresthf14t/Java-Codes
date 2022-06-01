/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class D_Tavas_and_Malekas {
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
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int n=input.scanInt();
        int m=input.scanInt();
        String str=input.scanString();
        int arr[]=new int[m];
        for(int i=0;i<m;i++) {
            arr[i]=input.scanInt()-1;
        }
        System.out.println(solve(n,m,str,arr));
    }
    public static long solve(int n,int m,String str,int arr[]) {
        int chr[]=new int[str.length()];
        for(int i=0;i<str.length();i++) {
            chr[i]=str.charAt(i);
        }
//        for(int i=0;i<chr.length;i++) {
//            System.out.print(chr[i]+" ");
//        }
//        System.out.println();
        int ans[]=new int[n];
        for(int i=0;i<m;i++) {
            for(int j=arr[i];j<arr[i]+str.length();j++) {
                if(i<m-1 && j==arr[i+1]) {
                    break;
                }
                ans[j]=chr[j-arr[i]];
            }
        }
        boolean pattern_indx[]=new boolean[n];
        int kmp[]=create_KMP_Array(chr.length ,chr);
        int j=0;
        for(int i=0;i<n;i++) {
            if(chr[j]==ans[i]) {
                j++;
            }
            else {
                if(j!=0) {
                    j=kmp[j-1];
                    i--;
                }
            }
            if(j==chr.length) {
//                System.out.println("Pattern Found at "+(i-j+1));
                pattern_indx[(i-j+1)]=true;
                j=kmp[j-1];
            }
        }
//        for(int i=0;i<ans.length;i++) {
//            System.out.print(ans[i]+" ");
//        }
//        System.out.println();
        for(int i=0;i<m;i++) {
            if(!pattern_indx[arr[i]]) {
                return 0;
            }
        }
        long cnt=1,mod=1000000007L;
        for(int i=0;i<n;i++) {
//            System.out.print(ans[i]+" ");
            if(ans[i]==0) {
                cnt*=26;
                cnt%=mod;
            }
        }
        System.out.println();
        return cnt;
    }
    
    public static int[] create_KMP_Array(int n,int str[]) {
        int kmp[]=new int[n];
        kmp[0]=0;
        for(int i=1;i<n;i++) {
            int j=kmp[i-1];
            while(j>0 && str[i]!=str[j]) {
                j=kmp[j-1];
            }
            if(str[i]==str[j]) {
                j++;
            }
            kmp[i]=j;
        }
        return kmp;
    }
}
