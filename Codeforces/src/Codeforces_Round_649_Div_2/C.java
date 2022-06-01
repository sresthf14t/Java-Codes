/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_649_Div_2;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class C {
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
        int mex=0;
        Set<Integer> hashset=new HashSet<>();
        int brr[]=new int[n];
        ArrayList<Integer> rem=new ArrayList<>();
        boolean is_pos=true;
        for(int i=0;i<n;i++) {
//            System.out.println(i);
            if(arr[i]==mex) {
//                System.out.println(i);
                rem.add(i);
                continue;
            }
            rem.add(i);
            while(!rem.isEmpty() && mex!=arr[i]) {
                while(hashset.contains(mex)) {
                    mex++;
                }
                brr[rem.get(rem.size()-1)]=mex;
                rem.remove(rem.size()-1);
                hashset.add(mex);
                while(hashset.contains(mex)) {
                    mex++;
                }
            }
            while(hashset.contains(mex)) {
                mex++;
            }
            if(arr[i]==mex) {
                continue;
            }
            else {
                is_pos=false;
                break;
            }
        }
        for(int i=0;i<rem.size();i++) {
            brr[rem.get(i)]=1000000;
        }
        if(!is_pos) {
            System.out.println(-1);
            return;
        }
        StringBuilder ans=new StringBuilder("");
        for(int i=0;i<n;i++) {
            ans.append(brr[i]+" ");
        }
        System.out.println(ans);
    }
        
}
