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
public class D_Dr_Evil_Underscores {
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
    static int n,arr[],pow[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        n=input.scanInt();
        arr=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
        }
        pow=new int[31];
        pow[0]=1;
        for(int i=1;i<pow.length;i++) {
            pow[i]=2*pow[i-1];
        }
        Arrays.sort(arr);
//        System.out.println(find(0,n-1,2));
//        System.exit(0);
        System.out.println(solve(30,0,0,n-1));
    }
    public static int solve(int indx,int val,int l,int r) {
        if(indx==-1) {
            
            return max(l,r,val);
        }
        int mid=find(l,r,indx);
//        System.out.println(indx+" "+val+" "+l+" "+r+" "+mid);
        if(mid==l) {
            return solve(indx-1,val+pow[indx],l,r);
        }
        if(mid==-1) {
            return solve(indx-1,val,l,r);
        }
        int min=Integer.MAX_VALUE;
        min=Math.min(min,solve(indx-1,val,mid,r));
        min=Math.min(min, solve(indx-1,val+pow[indx],l,mid-1));
        
        return min;
    }
    public static int max(int l,int r,int num) {
        int max=0;
        for(int i=l;i<=r;i++) {
            max=Math.max(max,arr[i]^num);
        }
        return max;
    }
    public static int find(int l,int r,int indx) {
        int ans=-1;
        while(l<=r) {
            int pivot=(l+r)/2;
//            System.out.println(l+" "+r+" "+pivot+" "+arr[pivot]+" "+pow[indx]);
            if((arr[pivot]&pow[indx])==0) {
                l=pivot+1;
            }
            else {
                ans=pivot;
                r=pivot-1;
            }
        }
        return ans;
    }
}
