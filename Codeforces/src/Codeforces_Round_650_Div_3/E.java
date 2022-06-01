/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_650_Div_3;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class E {
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
    public static void main(String arsg[]) throws IOException {
        Scan input=new Scan();
        int test=input.scanInt();
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            int k=input.scanInt();
            String str=input.scanString();
            System.out.println(solve(n,k,str));
        }
    }
    public static int solve(int n,int k,String str) {
        int arr[]=new int[26];
        for(int i=0;i<n;i++) {
            arr[str.charAt(i)-97]++;
        }
//        for(int i=0;i<26;i++) {
//            System.out.print(arr[i]+" ");
//        }
//        System.out.println();
        int max=0;
        for(int i=1;i<=k;i++) {
            if(k%i==0) {
                max=Math.max(max,i*check(i,arr));
            }
        }
        return max;
    }
    public static int check(int len,int arr[]) {
//        System.out.println(len);
        int brr[]=new int[26];
        for(int i=0;i<26;i++) {
            brr[i]=arr[i];
        }
        
        
        int req[]=new int[26];
        for(int i=0;i<len;i++) {
            float max=-1;
            int max_indx=-1,max_ele=-1;
            for(int j=0;j<26;j++) {
                if((float)brr[j]/(float)(req[j]+1)>max) {
                    max=(float)brr[j]/(float)(req[j]+1);
                    max_ele=brr[j];
                    max_indx=j;
                }
                else if((float)brr[j]/(float)(req[j]+1)==max && brr[j]>max_ele){
                    max_ele=brr[j];
                    max_indx=j;
                }
            }
            if(max_indx==-1) {
                return 0;
            }
            brr[max_indx]--;
            req[max_indx]++;
        }
        
        
//        for(int i=0;i<req.length;i++) {
//            System.out.print(req[i]+" ");
//        }
//        System.out.println();
        int min=Integer.MAX_VALUE;
        for(int i=0;i<26;i++) {
            if(req[i]==0) {
                continue;
            }
            min=Math.min(min,arr[i]/req[i]);
        }
//        System.out.println(len+" "+min);
        return min;
    }
    
//    public static int calc(int indx,int brr[],int req[],int target) {
//        if(indx==target) {
//            
//        }
//    }
}
