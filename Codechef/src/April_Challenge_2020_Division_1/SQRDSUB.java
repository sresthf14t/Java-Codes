/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package April_Challenge_2020_Division_1;

/**
 *
 * @author User
 */
import java.io.*; 
import java.util.*; 
public class SQRDSUB {
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
            int arr[]=new int[n];
            long sum=0;
            for(int i=0;i<n;i++) {
                arr[i]=input.scanInt();
            }
            for(int i=0;i<n;i++) {
                long count=0;
                while(i<n && arr[i]%2==1) {
                    count++;
                    i++;
                }
                sum+=(count*(count+1))/2;
            }
            boolean mul_2[]=new boolean[n];
            boolean mul_4[]=new boolean[n];
            for(int i=0;i<n;i++) {
                if(arr[i]%4==0) {
                    mul_2[i]=mul_4[i]=true;
                }
                else if(arr[i]%2==0) {
                    mul_2[i]=true;
                }
            }
            for(int i=0;i<n;i++) {
                if(mul_4[i]) {
                    sum+=(n-i);
                }
                else if(mul_2[i]) {
                    for(int j=i+1;j<n;j++) {
                        if(mul_2[j]||mul_4[j]) {
                            sum+=(n-j);
                            break;
                        }
                    }
                }
                else {
                    int count=0;
                    for(int j=i+1;j<n;j++) {
                        if(mul_4[j]) {
                            count+=2;
                        }
                        else if(mul_2[j]) {
                            count++;
                        }
                        if(count>1) {
                            sum+=(n-j);
                            break;
                        }
                    }
                }
            }
            System.out.println(sum);
        }
    }
}
