/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Educational_Codeforces_Round_95_Rated_for_Div_2;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class G {
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
        int freq[]=new int[n+1];
        for(int i=0;i<n;i++) {
            arr[i]=input.scanInt();
            freq[arr[i]]++;
        }
        Set<Integer> less=new HashSet<>();
        for(int i=0;i<freq.length;i++) {
            if(freq[i]<3) {
                less.add(i);
            }
        }
        long ans=0;
        for(int i=0;i<n;i++) {
            if(less.contains(arr[i])) {
                continue;
            }
            TreeMap<Integer,Integer> map=new TreeMap<>();
            Set<Integer> hashset=new HashSet<>();
            for(int j=i;j<n;j++) {
                if(less.contains(arr[j])) {
                    break;
                }
                if(!map.containsKey(arr[j])) {
                    map.put(arr[j], 0);
                }
                map.replace(arr[j], map.get(arr[j])+1);
                if(map.get(arr[j])<3) {
                    hashset.add(arr[j]);
                }
                else if(map.get(arr[j])==3) {
                    hashset.remove(arr[j]);
                }
                else {
                    break;
                }
                if(hashset.size()==0) {
                    ans++;
                }
            }
            freq[arr[i]]--;
            if(freq[arr[i]]<3) {
                less.add(arr[i]);
            }
        }
        System.out.println(ans);
    }
}
