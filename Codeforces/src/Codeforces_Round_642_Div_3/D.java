/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_642_Div_3;

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
        int test=input.scanInt();
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            int n=input.scanInt();
            ans.append(solve(n)+"\n");
        }
        System.out.println(ans);
    }
    public static StringBuilder solve(int n) {
        ArrayList<Integer> arrli[]=new ArrayList[n+1];
        for(int i=0;i<n+1;i++) {
            arrli[i]=new ArrayList<>();
        }
        arrli[n].add(0);
        int cnt=n;
        int arr[]=new int[n];
        int i=1;
        for(;i<=n;i++) {
            boolean in=false;
            while(arrli[cnt].size()==0) {
                cnt--;
                in=true;
            }
            if(in) {
               arrli[cnt].sort(null); 
            }
            if(cnt==2) {
                break;
            }
            int indx=arrli[cnt].get(0);
            if(cnt%2==0) {
                int l=indx;
                int r=indx+cnt-1;
                int pivot=(r+l-1)/2;
                arr[pivot]=i;
                int lft=pivot-1-l+1;
                arrli[lft].add(l);
                int rgt=r-(pivot+1)+1;
                arrli[rgt].add(pivot+1);
            }
            else {
                int l=indx;
                int r=indx+cnt-1;
                int pivot=(r+l)/2;
                arr[pivot]=i;
                int lft=pivot-1-l+1;
                arrli[lft].add(l);
                int rgt=r-(pivot+1)+1;
                arrli[rgt].add(pivot+1);
            }
            arrli[cnt].remove(0);
        }
        for(int j=0;j<n-1;j++) {
            if(arr[j]==0 && arr[j+1]==0) {
                arr[j]=i;
                i++;
            }
        }
        for(int j=0;j<n;j++) {
            if(arr[j]==0) {
                arr[j]=i;
                i++;
            }
        }
        StringBuilder ans=new StringBuilder("");
        for(i=0;i<n;i++) {
            ans.append(arr[i]+" ");
        }
        return ans;
    }
}
