/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_666_Div_2;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class D {
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
        long startTime = System.nanoTime();
        Scan input=new Scan();
        int test=input.scanInt();
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            int arr[]=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt();
            }
            solve(n,arr);
        }
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(totalTime);
        System.out.println(ans);
    }
//    public static void solve(int n,int arr[]) {
//        TreeMap<Integer,Integer> map=new TreeMap<>();
//        for(int i=0;i<n;i++) {
//            if(!map.containsKey(arr[i])) {
//                map.put(arr[i], 0);
//            }
//            map.replace(arr[i], map.get(arr[i])+1);
//        }
//        int cnt=0,prev=-1;
//        while(true) {
//            if(map.isEmpty()) {
//                break;
//            }
//            int max=map.lastKey();
//            if(max==0) {
//                break;
//            }
//            map.replace(max, map.get(max)-1);
//            if(map.get(max)==0) {
//                map.remove(max);
//            }
//            max--;
//            
//            if(prev!=-1) {
//                if(!map.containsKey(prev)) {
//                    map.put(prev, 0);
//                }
//                map.replace(prev, map.get(prev)+1);
//            }
//            prev=max;
//            cnt++;
//        }
//        if(cnt%2==0) {
//            System.out.println("HL");
//        }
//        else {
//            System.out.println("T");
//        }
//    }
//    
    public static void solve(int n,int arr[]) {
        int cnt=0,prev=-1;
        while(true) {
            int max=0,max_indx=-1;
            for(int i=0;i<n;i++) {
                if(i==prev) {
                    continue;
                }
                if(arr[i]>max) {
                    max=arr[i];
                    max_indx=i;
                }
            }
            if(max_indx==-1) {
                break;
            }
            arr[max_indx]--;
            prev=max_indx;
            cnt++;
        }
        if(cnt%2==0) {
            System.out.println("HL");
        }
        else {
            System.out.println("T");
        }
        
    }
}