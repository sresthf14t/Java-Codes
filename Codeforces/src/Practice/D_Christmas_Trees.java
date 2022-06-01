/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class D_Christmas_Trees {
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
        int m=input.scanInt();
        Set<Integer> hashset=new HashSet<>();
        TreeMap<Integer,Stack<Integer>> map=new TreeMap<>();
        map.put(0, new Stack<Integer>());
        for(int i=0;i<n;i++) {
            int tmp=input.scanInt();
            Stack stck=map.get(0);
            stck.add(tmp);
            hashset.add(tmp);
        }
        long ans=0;
        StringBuilder fin=new StringBuilder("");
        for(int i=0;i<m;i++) {
            int key=map.firstKey();
            Stack<Integer> stck=map.get(key);
            int indx=stck.peek();
            if(!hashset.contains(indx+1)) {
                fin.append((indx+1)+" ");
                hashset.add(indx+1);
                ans+=key+1;
                if(!map.containsKey(key+1)) {
                    Stack<Integer> tmp=new Stack<>();
                    tmp.add(indx+1);
                    map.put(key+1, tmp);
                }
                else {
                    Stack<Integer> tmp=map.get(key+1);
                    tmp.add(indx+1);
                }
            }
            else if(!hashset.contains(indx-1)) {
                fin.append((indx-1)+" ");
                hashset.add(indx-1);
                ans+=key+1;
                if(!map.containsKey(key+1)) {
                    Stack<Integer> tmp=new Stack<>();
                    tmp.add(indx-1);
                    map.put(key+1, tmp);
                }
                else {
                    Stack<Integer> tmp=map.get(key+1);
                    tmp.add(indx-1);
                }
            }
            else {
                stck.pop();
                if(stck.isEmpty()) {
                    map.remove(key);
                }
                i--;
            }
        }
        System.out.println(ans);
        System.out.println(fin);
    }
}
