/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AtCoder_Beginner_Contest_178;

/**
 *
 * @author Srest
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
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        int n=input.scanInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
        }
        TreeMap<Integer,Integer> map=new TreeMap<>();
        int freq[]=new int[n+1];
        for(int i=0;i<n;i++) {
            int tmp=input.scanInt();
            freq[tmp]++;
            if(!map.containsKey(tmp)) {
                map.put(tmp, 0);
            }
            map.replace(tmp, map.get(tmp)+1);
        }
        int ans[]=new int[n];
        for(int i=0;i<n;i++) {
            int max=map.lastKey();
            if(max==arr[i]) {
                max=map.firstKey();
            }
            ans[i]=max;
            freq[max]--;
            map.replace(max, map.get(max)-1);
            if(map.get(max)==0) {
                map.remove(max);
            }
        }
        int indx1=0,indx2=n-1;
        for(int i=1;i<n-1;i++) {
            if(ans[i]==arr[i]) {
                if(ans[indx1]!=arr[i] && ans[i]!=arr[indx1]) {
                    int tmp=ans[indx1];
                    ans[indx1]=ans[i];
                    ans[i]=tmp;
                    indx1++;
                }
                else if(ans[indx2]!=arr[i] && ans[i]!=arr[indx2]) {
                    int tmp=ans[indx2];
                    ans[indx2]=ans[i];
                    ans[i]=tmp;
                    indx2--;
                }
            }
        }
        for(int i=0;i<n;i++) {
            if(arr[i]==ans[i]) {
                System.out.println("No");
                return;
            }
        }
        StringBuilder fin=new StringBuilder("");
        for(int i=0;i<n;i++) {
            fin.append(ans[i]+" ");
        }
        System.out.println("Yes");
        System.out.println(fin);
    }
}
