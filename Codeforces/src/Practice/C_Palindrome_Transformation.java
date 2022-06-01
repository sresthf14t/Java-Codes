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
public class C_Palindrome_Transformation {
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
        int p=input.scanInt()-1;
        String str=input.scanString();
        System.out.println(solve(n,p,str));
    }
    public static int solve(int n,int p,String str) {
        boolean done[]=new boolean[n];
        int l,r;
        if(p<=n/2) {
            l=0;
            r=(n/2)-1;
        }
        else {
            if(n%2==0) {
                l=n/2;
            }
            else {
                l=(n/2)+1;
            }
            r=n-1;
        }
        int count1=0,count2=0,count3=0,counter3=0;
        int lft1=p,lft2=p,rgt1=p,rgt2=p;
        for(int i=p;i>=l;i--) {
            if(done[i]) {
                continue;
            }
            if(str.charAt(i)!=str.charAt(n-i-1)) {
                if(lft1==-1 || i<lft1) {
                    lft1=i;
                }
                if(rgt1==-1 || i>rgt1) {
                    rgt1=i;
                }
                int diff=Math.abs(str.charAt(i)-str.charAt(n-i-1));
                diff=Math.min(diff,Math.abs(26-diff));
                count1+=diff;
                done[i]=done[n-i-1]=true;
            }
        }
        for(int i=lft1;i<=r;i++) {
            if(done[i]) {
                continue;
            }
            if(str.charAt(i)!=str.charAt(n-i-1)) {
                if(lft1==-1 || i<lft1) {
                    lft1=i;
                }
                if(rgt1==-1 || i>rgt1) {
                    rgt1=i;
                }
                int diff=Math.abs(str.charAt(i)-str.charAt(n-i-1));
                diff=Math.min(diff,Math.abs(26-diff));
                count1+=diff;
                done[i]=done[n-i-1]=true;
            }
        }
        if(rgt1==p) {
            count1+=(rgt1-lft1);
        }
        else {
            count1+=2*(p-lft1);
            count1+=(rgt1-p);
        }
        done=new boolean[n];
        for(int i=p;i<=r;i++) {
            if(done[i]) {
                continue;
            }
            if(str.charAt(i)!=str.charAt(n-i-1)) {
                int diff=Math.abs(str.charAt(i)-str.charAt(n-i-1));
                diff=Math.min(diff,Math.abs(26-diff));
                count2+=diff;
                done[i]=done[n-i-1]=true;
                if(lft2==-1 || i<lft2) {
                    lft2=i;
                }
                if(rgt2==-1 || i>rgt2) {
                    rgt2=i;
                }
            }
        }
        for(int i=rgt2;i>=l;i--) {
            if(done[i]) {
                continue;
            }
            if(str.charAt(i)!=str.charAt(n-i-1)) {
                int diff=Math.abs(str.charAt(i)-str.charAt(n-i-1));
                diff=Math.min(diff,Math.abs(26-diff));
                count2+=diff;
                done[i]=done[n-i-1]=true;
            }
            if(lft2==-1 || i<lft2) {
                    lft2=i;
                }
                if(rgt2==-1 || i>rgt2) {
                    rgt2=i;
                }
        }
        if(lft2==p) {
            count2+=(rgt2-p);
        }
        else {
            count2+=2*(rgt2-p);
            count2+=(p-lft2);
        }
        if(p<=n/2) {
            done=new boolean[n];
            for(int i=p;i<n;i++) {
                if(done[i]) {
                    counter3++;
                    continue;
                }
                if(str.charAt(i)!=str.charAt(n-i-1)) {
                    int diff=Math.abs(str.charAt(i)-str.charAt(n-i-1));
                    diff=Math.min(diff,Math.abs(26-diff));
                    count3+=diff;
                    count3+=counter3;
                    counter3=0;
                    done[i]=done[n-i-1]=true;
                }
                counter3++;
            }
        }
        else {
            done=new boolean[n];
            for(int i=p;i>=0;i--) {
                if(done[i]) {
                    counter3++;
                    continue;
                }
                if(str.charAt(i)!=str.charAt(n-i-1)) {
                    int diff=Math.abs(str.charAt(i)-str.charAt(n-i-1));
                    diff=Math.min(diff,Math.abs(26-diff));
                    count3+=diff;
                    count3+=counter3;
                    counter3=0;
                    done[i]=done[n-i-1]=true;
                }
                counter3++;
            }
        }
//        System.out.println(count1+" "+count2+" "+count3);
//        System.out.println(lft1+" "+rgt1+" "+lft2+" "+rgt2);
        return Math.min(count1,Math.min(count3,count2));
    }
}
