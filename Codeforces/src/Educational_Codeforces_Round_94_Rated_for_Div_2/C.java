/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Educational_Codeforces_Round_94_Rated_for_Div_2;

/**
 *
 * @author Srest
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
        int test=input.scanInt();
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            String str=input.scanString();
            int x=input.scanInt();
            ans.append(solve(x,str)+"\n");
        }
        System.out.println(ans);
    }
    public static StringBuilder solve(int x,String str) {
        int arr[]=new int[str.length()];
        Arrays.fill(arr, -1);
        for(int i=0;i<arr.length;i++) {
            if(str.charAt(i)=='0') {
                if(i-x>=0) {
                    arr[i-x]=0;
                }
                if(i+x<arr.length) {
                    arr[i+x]=0;
                }
            }
            else {
                if(i-x>=0 && (arr[i-x]==-1 || arr[i-x]==1)) {
                    arr[i-x]=1;
                }
                else if(i+x<arr.length) {
                    arr[i+x]=1;
                }
            }
        }
        for(int i=0;i<str.length();i++) {
            if(arr[i]==-1) {
                arr[i]=0;
            }
        }
        if(check(x,str,arr)) {
            StringBuilder ans=new StringBuilder("");
            for(int i=0;i<str.length();i++) {
                ans.append(arr[i]);
            }
            return ans;
        }
        return new StringBuilder("-1");
    }
    public static boolean check(int x,String str,int arr[]) {
        for(int i=0;i<str.length();i++) {
            if(str.charAt(i)=='0') {
                if(i-x>0 && arr[i-x]==1) {
                    return false;
                }
                if(i+x<str.length() && arr[i+x]==1) {
                    return false;
                }
            }
            else{
                int cnt=0;
                if(i-x>=0 && arr[i-x]==1) {
                    cnt++;
                }
                if(i+x<str.length() && arr[i+x]==1) {
                    cnt++;
                }
                if(cnt==0) {
                    return false;
                }
            }
        }
        return true;
    }
}
