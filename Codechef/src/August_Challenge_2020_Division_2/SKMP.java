/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package August_Challenge_2020_Division_2;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class SKMP {
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
        StringBuilder ans=new StringBuilder("");
        int test=input.scanInt();
        for(int t=1;t<=test;t++) {
            String str=input.scanString();
            String sub=input.scanString();
            if(str.length()<=1000) {
                ans.append(solve1(str,sub)+"\n");
                continue;
            }
            ans.append(solve(str,sub)+"\n");
        }
        System.out.println(ans);
    }
    public static String solve1(String str,String sub) {
        int chr[]=new int[26];
        for(int i=0;i<str.length();i++) {
            chr[str.charAt(i)-'a']++;
        }
        for(int i=0;i<sub.length();i++) {
            chr[sub.charAt(i)-'a']--;
        }
        StringBuilder tmp=new StringBuilder("");
        String ans[]=new String[str.length()-sub.length()+1];
        boolean app=false;
        for(int i=0;i<chr.length;i++) {
            for(int j=0;j<chr[i];j++) {
                tmp.append((char)(i+'a'));
            }
        }
        for(int i=0;i<ans.length;i++) {
            StringBuilder tmp1=new StringBuilder(tmp);
            tmp1.insert(i, sub);
            ans[i]=""+tmp1;
        }
        Arrays.sort(ans);
        return ans[0];
    }
    public static StringBuilder solve(String str,String sub) {
        int chr[]=new int[26];
        for(int i=0;i<str.length();i++) {
            chr[str.charAt(i)-'a']++;
        }
        for(int i=0;i<sub.length();i++) {
            chr[sub.charAt(i)-'a']--;
        }
        StringBuilder ans=new StringBuilder("");
        boolean app=false;
        for(int i=0;i<chr.length;i++) {
            if(chr[i]==0) {
                continue;
            }
            StringBuilder tmp=new StringBuilder("");
            for(int j=0;j<chr[i];j++) {
                tmp.append((char)(i+'a'));
            }
            if(!app && cmp(sub,""+tmp)==0) {
                ans.append(sub);
                app=true;
            }
            ans.append(tmp);
        }
        if(!app) {
            ans.append(sub);
        }
        return ans;
    }
    public static int cmp(String str1,String str2) {
        String[] tmp={str1,str2};
        Arrays.sort(tmp);
        if(tmp[0].equals(str1)) {
            return 0;
        }
        return 1;
    }
}
