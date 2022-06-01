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
public class C_Vasya_and_Basketball {
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
        int a[]=new int[n];
        
        for(int i=0;i<n;i++) {
            a[i]=input.scanInt();
        }
        int m=input.scanInt();
        int b[]=new int[m];
        for(int i=0;i<m;i++) {
            b[i]=input.scanInt();
        }
        solve(n,m,a,b);
    }
    public static void solve(int n,int m,int a[],int b[]) {
        int arr[]=new int[n+m];
        for(int i=0;i<n;i++) {
            arr[i]=a[i];
        }
        for(int i=0;i<m;i++) {
            arr[n+i]=b[i];
        }
        Arrays.sort(a);
        Arrays.sort(b);
        Arrays.sort(arr);
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<arr.length;i++) {
            if(!map.containsKey(arr[i])) {
                map.put(arr[i], i);
            }
        }
        int freq_a[]=new int[arr.length];
        int freq_b[]=new int[arr.length];
        for(int i=0;i<n;i++) {
            int tmp=map.get(a[i]);
            freq_a[tmp]++;
        }
        for(int i=0;i<m;i++) {
            int tmp=map.get(b[i]);
            freq_b[tmp]++;
        }
        for(int i=1;i<arr.length;i++) {
            freq_a[i]+=freq_a[i-1];
            freq_b[i]+=freq_b[i-1];
        }
//        for(int i=0;i<arr.length;i++) {
//            System.out.print(freq_a[i]+" ");
//        }
//        System.out.println();
//        for(int i=0;i<arr.length;i++) {
//            System.out.print(freq_b[i]+" ");
//        }
//        System.out.println();
        int score_a=3*n,score_b=3*m,diff=score_a-score_b;
        for(int i=0;i<arr.length;i++) {
            int tmp_a=(2*freq_a[i])+(3*(n-freq_a[i]));
            int tmp_b=(2*freq_b[i])+(3*(m-freq_b[i]));
//            System.out.println(tmp_a+" "+tmp_b);
            if(tmp_a-tmp_b>diff) {
                diff=tmp_a-tmp_b;
                score_a=tmp_a;
                score_b=tmp_b;
            }
        }
        System.out.println(score_a+":"+score_b);
    }
}
