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
public class A_DZY_Loves_Sequences {
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
        int arr[]=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
        }
        System.out.println(solve(n,arr));
    }
    public static int solve(int n,int arr[]) {
        int freq[]=new int[n];
        int max_len=1;
        ArrayList<Integer> strt=new ArrayList<>();
        ArrayList<Integer> end=new ArrayList<>();
        for(int i=0;i<n;i++) {
            int j=i+1;
            while(j<n && arr[j]>arr[j-1]) {
                j++;
            }
            j--;
            max_len=Math.max(max_len,j-i+1);
            for(int k=i;k<=j;k++) {
                freq[k]=j-i+1;
            }
            strt.add(i);
            end.add(j);
            i=j;
        }
        if(max_len==n) {
            return max_len;
        }
        int tmp_max_len=max_len;
//        for(int i=0;i<n;i++) {
//            System.out.print(freq[i]+" ");
//        }
//        System.out.println();
        
        for(int i=0;i<end.size();i++) {
            if(freq[end.get(i)]==1) {
               if(end.get(i)>0 && end.get(i)<n-1 && arr[end.get(i)-1]+1<arr[end.get(i)+1]) {
                   max_len=Math.max(max_len,freq[end.get(i)-1]+freq[end.get(i)+1]+1);
               }
               if(end.get(i)>0) {
                   max_len=Math.max(max_len,freq[end.get(i)-1]+1);
               }
               if(end.get(i)<n-1) {
                   max_len=Math.max(max_len,freq[end.get(i)+1]+1);
               }
            }
            else {
                if(i<end.size()-1 && freq[end.get(i+1)]!=1) {
                    if(arr[end.get(i)-1]+1<arr[strt.get(i+1)]) {
                        max_len=Math.max(max_len,freq[end.get(i)]+freq[end.get(i)+1]);
                    }
               }
                if(i<end.size()-1 && freq[end.get(i+1)]!=1) {
                    if(arr[end.get(i)]+1<arr[strt.get(i+1)+1]) {
                        max_len=Math.max(max_len,freq[end.get(i)]+freq[end.get(i)+1]);
                    }
               }
            }
        }
        max_len+=max_len==tmp_max_len?1:0;
        return max_len;
    }
}
