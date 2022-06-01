/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_644_Div_3;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class F {
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
    static int n,m;
    static String str[],ans;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int test=input.scanInt();
        for(int t=1;t<=test;t++) {
            n=input.scanInt();
            m=input.scanInt();
            str=new String[n];
            ans="";
            for(int i=0;i<n;i++) {
                str[i]=input.scanString();
            }
            solve(0,new boolean[n],new StringBuilder(""));
            if(ans.length()==0) {
                ans="-1";
            }
            System.out.println(ans);
        }
    }
    public static void solve(int indx,boolean diff[],StringBuilder tmp) {
        if(ans.length()!=0) {
            return;
        }
        if(!check(tmp)) {
            return;
        }
        if(indx==m) {
            if(check(tmp)) {
                ans=""+tmp;
            }
            return;
        }
        boolean all_f=true;
        for(int i=0;i<n;i++) {
            if(diff[i]) {
                all_f=false;
                break;
            }
        }
        
        if(all_f) {
            for(int i=0;i<n;i++) {
                ArrayList<Integer> arrli=new ArrayList<>();
                for(int j=0;j<n;j++) {
                    if(diff[j]) {
                        continue;
                    }
                    if(str[j].charAt(indx)!=str[i].charAt(indx)) {
                        diff[j]=true;
                        arrli.add(j);
                    }
                }
                tmp.append(str[i].charAt(indx));
                solve(indx+1,diff,tmp);
                tmp.deleteCharAt(tmp.length()-1);
                for(int j=0;j<arrli.size();j++) {
                    diff[arrli.get(j)]=false;
                }
            }
            return;
        }
        for(int i=0;i<n;i++) {
            if(!diff[i]) {
                continue;
            }
            ArrayList<Integer> arrli=new ArrayList<>();
            for(int j=0;j<n;j++) {
                if(diff[j]) {
                    continue;
                }
                if(str[j].charAt(indx)!=str[i].charAt(indx)) {
                    diff[j]=true;
                    arrli.add(j);
                }
            }
            tmp.append(str[i].charAt(indx));
            solve(indx+1,diff,tmp);
            tmp.deleteCharAt(tmp.length()-1);
            for(int j=0;j<arrli.size();j++) {
                diff[arrli.get(j)]=false;
            }
        }
    }
    public static boolean check(StringBuilder tmp) {
        for(int i=0;i<n;i++) {
            int cnt=0;
            for(int j=0;j<tmp.length();j++) {
                if(str[i].charAt(j)!=tmp.charAt(j)) {
                    cnt++;
                }
            }
            if(cnt>1) {
                return  false;
            }
        }
        return true;
    }
}

