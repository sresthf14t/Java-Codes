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
import java.io.*; 
import java.util.*;
public class A_String_Game {
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
        String str1=input.scanString();
        String str2=input.scanString();
        int n=str1.length();
        int arr[]=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt()-1;
        }
        System.out.println(solve(n,arr,str1,str2));
    }
    public static int solve(int n,int arr[],String str1,String str2) {
        int l=0,r=n-1;
        int indx=-1;
        while(true) {
            int pivot=(l+r)/2;
            StringBuilder tmp=create_substr(pivot,arr,str1);
//            System.out.println(l+" "+r+" "+pivot+" "+tmp);
            if(contains(tmp,str2)) {
                if(pivot>indx) {
                    indx=pivot;
                }
                l=pivot+1;
            }
            else {
                r=pivot-1;
            }
            if(l>r) {
                break;
            }
        }
        return indx+1;
    }
    public static StringBuilder create_substr(int indx,int arr[],String str) {
        StringBuilder sub=new StringBuilder("");
        boolean contains[]=new boolean[str.length()];
        for(int i=0;i<=indx;i++) {
            contains[arr[i]]=true;
        }
        for(int i=0;i<str.length();i++) {
            if(contains[i]) {
                continue;
            }
            sub.append(str.charAt(i));
        }
        return sub;
    }
    public static boolean contains(StringBuilder str,String seq) {
        int indx=0;
        for(int i=0;i<str.length();i++) {
            if(str.charAt(i)==seq.charAt(indx)) {
                indx++;
            }
            if(indx==seq.length()) {
                return true;
            }
        }
        return false;
    }
}
