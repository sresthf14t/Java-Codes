/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_604_Div_2_Virtual;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class D {
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
        int a=input.scanInt();
        int b=input.scanInt();
        int c=input.scanInt();
        int d=input.scanInt();
        StringBuilder tmp=new StringBuilder("");
        StringBuilder ans=new StringBuilder("");
        for(int i=0;i<a;i++) {
            tmp.append(0);
        }
        for(int i=0;i<c;i++) {
            tmp.append(2);
        }
        for(int i=0;i<a+c;i++) {
            ans.append(tmp.charAt(i)+" ");
            if(i==a+c-1) {
                break;
            }
            if(tmp.charAt(i)=='0' && tmp.charAt(i+1)=='0') {
                if(b>0) {
                    ans.append(1+" ");
                    b--;
                }
                else {
                    System.out.println("NO");
                    return;
                }
            }
            if(tmp.charAt(i)=='0' && tmp.charAt(i+1)=='2') {
                if(b>0) {
                    ans.append(1+" ");
                    b--;
                }
                else {
                    System.out.println("NO");
                    return;
                }
            }
            if(tmp.charAt(i)=='2' && tmp.charAt(i+1)=='2') {
                if(b>0) {
                    ans.append(1+" ");
                    b--;
                }
                else if(d>0) {
                    ans.append(3+" ");
                    d--;
                }
                else {
                    System.out.println("NO");
                    return;
                }
            }
        }
        if(b>0) {
            ans.insert(0, 1+" ");
            b--;
        }
        if(d>0) {
            ans.append(3+" ");
            d--;
        }
        if(d==1 && b==0 && ans.charAt(0)=='0') {
            boolean done=false;
            for(int i=1;i<ans.length()-1;i++) {
                if(ans.charAt(i)!='1') {
                    continue;
                }
                if(ans.charAt(i-2)=='2' && ans.charAt(i+2)=='2') {
                    ans.replace(i, i+1, "3");
                    done=true;
                    break;
                }
            }
            if(done) {
                ans.insert(0, 1+" ");
                b=d=0;
            }
            
        }
        if(ans.charAt(0)=='2' && d==1) {
            ans.insert(0, 3+" ");
            d--;
        }
        if((ans.charAt(ans.length()-2)=='2' || ans.charAt(ans.length()-2)=='0') && b==1) {
            ans.append(1+" ");
            b--;
        }
        if(b!=0 || d!=0) {
            System.out.println("NO");
            return;
        }
        for(int i=2;i<ans.length()-2;i+=2) {
            if(Math.abs(ans.charAt(i)-ans.charAt(i-2))!=1) {
                System.out.println("NO");
                return;
            }
            if(Math.abs(ans.charAt(i)-ans.charAt(i+2))!=1) {
                System.out.println("NO");
                return;
            }
        }
        if(ans.length()>2) {
            if(Math.abs(ans.charAt(0)-ans.charAt(2))!=1) {
                System.out.println("NO");
                return;
            }
        }
        
        System.out.println("YES\n"+ans);
    }
}
