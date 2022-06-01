/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_Code_2_0;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class ENGRIS {
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
            int q=input.scanInt();
            String str[]=new String[n];
            Set<String> hashset=new HashSet<>();
            for(int i=0;i<n;i++) {
                str[i]=input.scanString();
                hashset.add(str[i]);
            }
            for(int i=0;i<q;i++) {
                String tmp=input.scanString();
                if(hashset.contains(tmp)) {
                    ans.append(tmp+"\n");
                    continue;
                }
                ans.append(str[find(n,str,tmp)]+"\n");
            }
        }
        System.out.println(ans);
    }
    public static int find(int n,String str[],String tmp) {
//        System.out.println(n+" "+tmp);
        for(int i=0;i<n;i++) {
            if(tmp.length()==str[i].length()) {
                int diff=0;
                for(int j=0;j<tmp.length();j++) {
                    if(tmp.charAt(j)!=str[i].charAt(j)) {
                        diff++;
                    }
                    if(diff>1) {
                        break;
                    }
                }
                if(diff==1) {
                    return i;
                }
            }
            if(tmp.length()+1==str[i].length()) {
                int diff=0;
                for(int j=0;j<str[i].length();j++) {
                    if(diff==0) {
                        if(j==tmp.length()) {
                            diff++;
                        }
                        else if(tmp.charAt(j)!=str[i].charAt(j)) {
                            diff++;
                        }
                    }
                    else {
                        if(tmp.charAt(j-1)!=str[i].charAt(j)) {
                            diff++;
                        }
                    }
                    if(diff>1) {
                        break;
                    }
                }
                if(diff==1) {
                    return i;
                }
            }
        }
        return -1;
    }
}
