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
public class D_Points_and_Powers_of_Two {
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
        long arr[]=new long[n];
        HashMap<Long,Integer> map=new HashMap<>();
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
            if(!map.containsKey(arr[i])) {
                map.put(arr[i], 0);
            }
            map.replace(arr[i], map.get(arr[i])+1);
        }
        long pow[]=new long[40];
        pow[0]=1;
        for(int i=1;i<pow.length;i++) {
            pow[i]=2*pow[i-1];
        }
        int max=-1;
        long a=-Integer.MIN_VALUE,b=Integer.MIN_VALUE,c=Integer.MIN_VALUE;
        for(int i=0;i<n;i++) {
            for(int j=0;j<pow.length;j++) {
                if(map.containsKey(arr[i]+pow[j]) && map.containsKey(arr[i]+2*pow[j])) {
                    int cnt=map.get(arr[i])+map.get(arr[i]+pow[j])+map.get(arr[i]+2*pow[j]);
                    if(cnt>max) {
                        max=cnt;
                        a=arr[i];
                        b=arr[i]+pow[j];
                        c=arr[i]+2*pow[j];
                    }
                }
            }
        }
        if(a!=Integer.MIN_VALUE) {
            StringBuilder ans=new StringBuilder(max+"\n");
            for(int i=0;i<map.get(a);i++) {
                ans.append(a+" ");
            }
            for(int i=0;i<map.get(b);i++) {
                ans.append(b+" ");
            }
            for(int i=0;i<map.get(c);i++) {
                ans.append(c+" ");
            }
            System.out.println(ans);
            return;
        }
        for(int i=0;i<n;i++) {
            for(int j=0;j<pow.length;j++) {
                if(map.containsKey(arr[i]+pow[j])) {
                    int cnt=map.get(arr[i])+map.get(arr[i]+pow[j]);
                    if(cnt>max) {
                        max=cnt;
                        a=arr[i];
                        b=arr[i]+pow[j];
                    }
                }
            }
        }
        if(a!=Integer.MIN_VALUE) {
            StringBuilder ans=new StringBuilder(max+"\n");
            for(int i=0;i<map.get(a);i++) {
                ans.append(a+" ");
            }
            for(int i=0;i<map.get(b);i++) {
                ans.append(b+" ");
            }
            System.out.println(ans);
            return;
        }
        for(int i=0;i<n;i++) {
            if(map.get(arr[i])>max) {
                max=map.get(arr[i]);
                a=arr[i];
            }
        }
        StringBuilder ans=new StringBuilder(max+"\n");
            for(int i=0;i<map.get(a);i++) {
                ans.append(a+" ");
            }
            System.out.println(ans);
    }
}
