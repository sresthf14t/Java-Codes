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
public class C_Arithmetic_Progression {
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
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
        }
        solve(n,arr);
    }
    public static void solve(int n, long arr[]) {
        Arrays.sort(arr);
        if(n==1) {
            System.out.println(-1);
            return;
        }
        boolean all_eq=true;
        for(int i=0;i<n;i++) {
            if(arr[i]!=arr[0]) {
                all_eq=false;
                break;
            }
        }
        if(all_eq) {
            System.out.println(1+"\n"+arr[0]);
            return;
        }
        long diff[]=new long[n];
        for(int i=0;i<n-1;i++) {
            diff[i]=arr[i+1]-arr[i];
        }
        if(n==2) {
            long new_ele=(arr[0]+arr[1])/2;
            if(new_ele-arr[0]==arr[1]-new_ele) {
                System.out.println(3);
                System.out.print((arr[0]-diff[0])+" "+(new_ele)+" "+(arr[1]+diff[0])+"\n");
            }
            else {
                System.out.println(2);
                System.out.print((arr[0]-diff[0])+" "+(arr[1]+diff[0])+"\n");
            }
            return;
        }
        HashMap<Long,Long> map=new HashMap<>();
        for(int i=0;i<n-1;i++) {
            if(!map.containsKey(diff[i])) {
                map.put(diff[i],0L);
            }
            map.replace(diff[i], map.get(diff[i])+1L);
        }
        if(map.size()>2) {
            System.out.println(0);
            return;
        }
        if(map.size()==1) {
            System.out.println(2);
            System.out.print((arr[0]-diff[0])+" "+(arr[n-1]+diff[0])+"\n");
            return;
        }
        long diff1=-1,cnt=0;
        for (Long key : map.keySet()) {
            if(map.get(key)>cnt) {
                diff1=key;
                cnt=map.get(key);
            }
        }
        for (Long key : map.keySet()) {
            if(key!=diff1) {
                if(map.get(key)!=1L) {
                    System.out.println(0);
                    return;
                }
            }
        }
        
        for(int i=0;i<n-1;i++) {
            if(arr[i+1]-arr[i]==diff1) {
                continue;
            }
            long new_ele=(arr[i]+arr[i+1])/2;
            if(new_ele-arr[i]==diff1 && arr[i+1]-new_ele==diff1) {
                System.out.println(1+"\n"+new_ele);
                return;
            }
            else {
                System.out.println(0);
                return;
            }
        }
    }
}
