/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Educational_Codeforces_Round_87_Virtual;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class B {
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
        int test=input.scanInt();
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            String str=input.scanString();
            ans.append(solve(str)+"\n");
        }
        System.out.println(ans);
    }
    public static int solve(String str) {
        int n=str.length();
        int arr_1[]=new int[n];
        int arr_2[]=new int[n];
        int arr_3[]=new int[n];
        for(int i=0;i<n;i++) {
            if(str.charAt(i)=='1') {
                arr_1[i]=-1;
            }
            int j=i;
            while(j<n && str.charAt(j)!='1') {
                j++;
            }
            int indx=j;
            if(j==n) {
                indx=-1;
            }
            j--;
            for(;j>=i;j--) {
                arr_1[j]=indx;
            }
            if(indx==-1) {
                break;
            }
            i=indx;
        }
        
        for(int i=0;i<n;i++) {
            if(str.charAt(i)=='2') {
                arr_2[i]=-1;
            }
            int j=i;
            while(j<n && str.charAt(j)!='2') {
                j++;
            }
            int indx=j;
            if(j==n) {
                indx=-1;
            }
            j--;
            for(;j>=i;j--) {
                arr_2[j]=indx;
            }
            if(indx==-1) {
                break;
            }
            i=indx;
        }
        
        for(int i=0;i<n;i++) {
            if(str.charAt(i)=='3') {
                arr_3[i]=-1;
            }
            int j=i;
            while(j<n && str.charAt(j)!='3') {
                j++;
            }
            int indx=j;
            if(j==n) {
                indx=-1;
            }
            j--;
            for(;j>=i;j--) {
                arr_3[j]=indx;
            }
            if(indx==-1) {
                break;
            }
            i=indx;
        }
//        for(int i=0;i<n;i++) {
//            System.out.print(arr_1[i]+" ");
//        }
//        System.out.println();
//        for(int i=0;i<n;i++) {
//            System.out.print(arr_2[i]+" ");
//        }
//        System.out.println();
//        for(int i=0;i<n;i++) {
//            System.out.print(arr_3[i]+" ");
//        }
//        System.out.println();
        int min=Integer.MAX_VALUE;
        for(int i=0;i<n;i++) {
            if(str.charAt(i)=='1') {
                if(arr_2[i]==-1 || arr_3[i]==-1) {
                    break;
                }
                int indx=Math.max(arr_2[i],arr_3[i]);
                min=Math.min(min,indx-i+1);
            }
            if(str.charAt(i)=='2') {
                if(arr_1[i]==-1 || arr_3[i]==-1) {
                    break;
                }
                int indx=Math.max(arr_1[i],arr_3[i]);
                min=Math.min(min,indx-i+1);
            }
            if(str.charAt(i)=='3') {
                if(arr_1[i]==-1 || arr_2[i]==-1) {
                    break;
                }
                int indx=Math.max(arr_1[i],arr_2[i]);
                min=Math.min(min,indx-i+1);
            }
        }
        if(min==Integer.MAX_VALUE) {
            min=0;
        }
        return min;
    }
}