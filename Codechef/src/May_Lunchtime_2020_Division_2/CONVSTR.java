/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package May_Lunchtime_2020_Division_2;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class CONVSTR {
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
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            String a=input.scanString();
            String b=input.scanString();
            System.out.println(solve(n,a,b));
        }
    }
    public static StringBuilder solve(int n,String a,String b) {
        ArrayList<Integer> arr[]=new ArrayList[26];
        int contains[]=new int[26];
        for(int i=0;i<26;i++) {
            arr[i]=new ArrayList<>();
            contains[i]=-1;
        }
        for(int i=0;i<n;i++) {
            contains[a.charAt(i)-97]=i;
        }
        for(int i=0;i<n;i++) {
            if(arr[b.charAt(i)-97].add(i));
        }
        boolean is_pos=true;
        for(int i=0;i<26;i++) {
            for(int j=0;j<arr[i].size();j++) {
                if(a.charAt(arr[i].get(j))<97+i) {
                    is_pos=false;
                }
            }
        }
//        for(int i=0;i<26;i++) {
//            for(int j=0;j<arr[i].size();j++) {
//                System.out.print(arr[i].get(j)+" ");
//            }
//            System.out.println();
//        }
        for(int i=0;i<26;i++) {
            if(arr[i].size()>0 && contains[i]==-1) {
                is_pos=false;
                break;
            }
        }
        if(!is_pos) {
            return new StringBuilder("-1");
        }
        StringBuilder ans=new StringBuilder("");
        int cnt=0;
        for(int i=25;i>=0;i--) {
            boolean to_sel=false,cont=false;
            for(int j=0;j<arr[i].size();j++) {
                if(a.charAt(arr[i].get(j))!=97+i) {
                    to_sel=true;
                }
                if(a.charAt(arr[i].get(j))==97+i) {
                    cont=true;
                }
            }
            if(arr[i].size()>0 && to_sel) {
                cnt++;
                if(!cont) {
                    ans.append((arr[i].size()+1)+" ");
                    ans.append(contains[i]+" ");
                }
                else {
                    ans.append(arr[i].size()+" ");
                }
                for(int j=0;j<arr[i].size();j++) {
                    ans.append(arr[i].get(j)+" ");
                }
                
                ans.append("\n");
            }
        }
        StringBuilder ret=new StringBuilder("");
        ret.append(cnt);
        ret.append("\n");
        ret.append(ans);
        return ret;
    }
}
