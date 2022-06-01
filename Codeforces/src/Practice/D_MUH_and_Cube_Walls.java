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
public class D_MUH_and_Cube_Walls {
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
    public static void main(String arsg[]) throws IOException {
        Scan input=new Scan();
        int n=input.scanInt();
        int m=input.scanInt();
        int txt[]=new int[n];
        int pattern[]=new int[m];
        for(int i=0;i<n;i++) {
            txt[i]=input.scanInt();
        }
        for(int i=0;i<m;i++) {
            pattern[i]=input.scanInt();
        }
        if(m==1) {
            System.out.println(n);
            return;
        }
        int txt_1[]=new int[n-1];
        int pattern_1[]=new int[m-1];
        for(int i=0;i<n-1;i++) {
            txt_1[i]=txt[i]-txt[i+1];
        }
        for(int i=0;i<m-1;i++) {
            pattern_1[i]=pattern[i]-pattern[i+1];
        }
        System.out.println(search(pattern_1,txt_1));
    }
    public static int search(int pattern[],int txt[]) {
        int cnt=0;
        int kmp[]=create_KMP_Array(pattern.length ,pattern);
        int j=0;
        for(int i=0;i<txt.length;i++) {
            if(pattern[j]==txt[i]) {
                j++;
            }
            else {
                if(j!=0) {
                    j=kmp[j-1];
                    i--;
                }
            }
            if(j==pattern.length) {
//                System.out.println("Pattern Found at "+(i-j+1));
                cnt++;
                j=kmp[j-1];
            }
        }
        return cnt;
    }
    
    
    public static int[] create_KMP_Array(int n,int str[]) {
        int kmp[]=new int[n];
        kmp[0]=0;
        for(int i=1;i<n;i++) {
            int j=kmp[i-1];
            while(j>0 && str[i]!=str[j]) {
                j=kmp[j-1];
            }
            if(str[i]==str[j]) {
                j++;
            }
            kmp[i]=j;
        }
        return kmp;
    }
   
}
