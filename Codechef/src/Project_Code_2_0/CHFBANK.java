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
public class CHFBANK {
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
        int n=700;
        Set<Integer> hashset=new HashSet<>();
        ArrayList<Integer> arrli=new ArrayList<>();
        for(int i=1,cnt=0;cnt<n;i++) {
            boolean is_pos=true;
            for(int j=0;j<arrli.size();j++) {
                if(hashset.contains(arrli.get(j)+i)) {
                    is_pos=false;
                    break;
                }
            }
            if(!is_pos) {
                continue;
            }
            if(hashset.contains(2*i)) {
                continue;
            }
            arrli.add(i);
            cnt++;
            for(int j=0;j<arrli.size();j++) {
                hashset.add(arrli.get(j)+i);
            }
        }
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            n=input.scanInt();
            long sum=0;
            for(int i=0;i<n;i++) {
                ans.append(arrli.get(i)+" ");
                sum+=arrli.get(i);
            }
            ans.append("\n"+sum+"\n");
        }
        System.out.println(ans);
    }
}
