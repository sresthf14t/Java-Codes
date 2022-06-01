/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_643_Div_2;

/**
 *
 * @author User
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
        Scan input=new Scan();
        int n=input.scanInt();
        int s=input.scanInt();
        solve(n,s);
    }
    public static void solve(int n,int s) {
        int arr[]=new int[n];
        for(int i=0;i<n-1;i++) {
            arr[i]=1;
        }
        arr[n-1]=s-(n-1);
        Set<Integer> hashset=new HashSet<>();
        int sum=0;
        for(int i=0;i<n-1;i++) {
            sum+=arr[i];
            hashset.add(sum);
        }
        for(int i=n-1;i>=0;i--) {
            sum+=arr[i];
            if(!hashset.contains(sum)) {
                hashset.add(sum);
            }
        }
        StringBuilder ans=new StringBuilder("");
        for(int i=0;i<n;i++) {
            ans.append(arr[i]+" ");
        }
        ans.append("\n");
        boolean is_pos=false;
        int k=-1;
        for(int i=0;i<=s;i++) {
            if(!hashset.contains(i) && !hashset.contains(s-i)) {
                k=i;
                is_pos=true;
                break;
            }
        }
        if(!is_pos) {
            System.out.println("NO");
            return;
        }
        System.out.println("YES");
        System.out.println(ans);
        System.out.println(k);
    }
}
