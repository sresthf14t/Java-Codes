/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hack_the_Interview_VI_Asia_Pacific;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class A {
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
        StringBuilder ans=new StringBuilder("");
        int n=input.scanInt();
        int m=input.scanInt();
        HashMap<Integer,Integer> map=new HashMap<>();
        long sum=0;
        int arr[]=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=i+1;
            sum+=i+1;
            map.put(i+1, 1);
        }
        for(int i=0;i<m;i++) {
            int num=input.scanInt();
            if(map.containsKey(num)) {
                int tmp=arr[0];
                arr[0]=arr[n-1];
                arr[n-1]=tmp;
                ans.append(sum+"\n");
            }
            else {
                sum-=arr[n-1];
                sum+=num;
                ans.append(sum+"\n");
//                System.out.println("1111  "+arr[n-1]);
                map.replace(arr[n-1], map.get(arr[n-1])-1);
                if(map.get(arr[n-1])==0) {
                    map.remove(arr[n-1]);
                }
                arr[n-1]=num;
                if(!map.containsKey(num)) {
                    map.put(num, 0);
                }
                map.replace(arr[n-1], map.get(arr[n-1])+1);
            }
        }
        System.out.println(ans);
    }
}
