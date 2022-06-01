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
public class E_Games_on_a_CD {
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
    static long p,m,pow[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        
        p=131;
        m=1000000007;
        
        //Calculating power (p^n%)m
        pow=new long[2000001];
        pow[0]=1;
        for(int i=1;i<pow.length;i++) {
            pow[i]=pow[i-1]*p;
            pow[i]%=m;
        }
        
        
        int n=input.scanInt();
        int k=input.scanInt();
        String cd=input.scanString();
        int names_len=input.scanInt();
        long names[]=new long[names_len];
        for(int i=0;i<names.length;i++) {
            String tmp=input.scanString();
            names[i]=hash(tmp);
        }
        solve(n,k,names,cd);
    }
    public static void solve(int n,int k,long names[],String cd) {
        HashMap<Long,Integer> map=new HashMap<>();
        for(int i=0;i<names.length;i++) {
            map.put(names[i], i);
        }
        int strt=-1;
        for(int i=0;i<k;i++) {
            boolean is_pos=true;
            boolean cont[]=new boolean[names.length];
            int indx=i;
            for(int j=0;j<n;j++) {
                long hash=0;
                for(int r=0;r<k;r++) {
                    hash+=(cd.charAt(indx)*pow[r])%m;
                    indx=(indx+1)%cd.length();
                }
                if(map.containsKey(hash)) {
                    int idx=map.get(hash);
                    if(cont[idx]) {
                        is_pos=false;
                        break;
                    }
                    else {
                        cont[idx]=true;
                    }
                }
                else {
                    is_pos=false;
                    break;
                }
                if(!is_pos) {
                    break;
                }
            }
            if(is_pos) {
                strt=i;
                break;
            }
        }
        if(strt==-1) {
            System.out.println("NO");
            return;
        }
        int indx=strt;
        StringBuilder ans=new StringBuilder("");
        for(int i=0;i<n;i++) {
            long hash=0;
            for(int j=0;j<k;j++) {
                hash+=(cd.charAt(indx)*pow[j])%m;
                indx=(indx+1)%cd.length();
            }
            ans.append((map.get(hash)+1)+" ");
        }
        System.out.println("YES\n"+ans);
    }
    public static long hash(String str) {
        long hash=0;
        for(int i=0;i<str.length();i++) {
            hash+=(str.charAt(i)*pow[i])%m;
        }
        return hash;
    }
}
