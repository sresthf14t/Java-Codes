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
public class D_Xenia_and_Bit_Operations {
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
    static boolean or[];
    static int seg_tree[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int n=input.scanInt();
        int query=input.scanInt();
        int arr[]=new int[(int)Math.pow(2, n)];
        for(int i=0;i<arr.length;i++) {
            arr[i]=input.scanInt();
        }
        create_graph(n,arr);
        for(int q=0;q<query;q++) {
            int indx=input.scanInt()-1;
            int val=input.scanInt();
            System.out.println(solve(indx,val,n));
        }
    }
    public static void create_graph(int n,int arr[]) {
        seg_tree=new int[(int)Math.pow(2,n+1)-1];
        or=new boolean[seg_tree.length];
        int tmp_n=n,cnt=0;
        for(int i=or.length-1;i>=0;) {
            int lim=(int)Math.pow(2,tmp_n);
            for(int k=0;k<lim;k++) {
                if(cnt%2==0) {
                    or[i]=false;
                }
                else {
                    or[i]=true;
                }
                i--;
            }
            tmp_n--;
            cnt++;
        }
        int j=seg_tree.length-1;
        for(int i=arr.length-1;i>=0;i--,j--) {
            seg_tree[j]=arr[i];
        }
        for(;j>=0;j--) {
            if(or[j]) {
                seg_tree[j]=seg_tree[2*j+1]|seg_tree[2*j+2];
            }
            else {
                seg_tree[j]=seg_tree[2*j+1]^seg_tree[2*j+2];
            }
        }
    }
    public static int solve(int indx,int val,int n) {
        int tmp_indx=seg_tree.length-(int)Math.pow(2, n);
        indx=tmp_indx+indx;
        seg_tree[indx]=val;
        while(true) {
            if(indx%2==0) {
                indx/=2;
                indx--;
            }
            else {
                indx/=2;
            }
            if(or[indx]) {
                seg_tree[indx]=seg_tree[2*indx+1]|seg_tree[2*indx+2];
            }
            else {
                seg_tree[indx]=seg_tree[2*indx+1]^seg_tree[2*indx+2];
            }
            if(indx==0) {
                break;
            }
        }
        return seg_tree[0];
    }
}
