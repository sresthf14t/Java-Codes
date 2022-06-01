/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing_Round_16_Unrated;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class C {
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
            String str=input.scanString();
            ans.append(solve(str));
            ans.append("\n");
        }
        System.out.println(ans);
    }
    public static long solve(String str) {
        Set<String> hashset=new HashSet<>();
        int x=0,y=0;
        long time=0;
        for(int i=0;i<str.length();i++) {
            if(str.charAt(i)=='N') {
                if(hashset.contains(x+" "+y+" "+(x+1)+" "+y)) {
                    time++;
                }
                else if(hashset.contains((x+1)+" "+y+" "+x+" "+y)) {
                    time++;
                }
                else {
                    time+=5;
                    hashset.add((x+" "+y+" "+(x+1)+" "+y));
                }
                x++;
            }
            if(str.charAt(i)=='S') {
                if(hashset.contains(x+" "+y+" "+(x-1)+" "+y)) {
                    time++;
                }
                else if(hashset.contains((x-1)+" "+y+" "+x+" "+y)) {
                    time++;
                }
                else {
                    time+=5;
                    hashset.add((x+" "+y+" "+(x-1)+" "+y));
                }
                x--;
            }
            if(str.charAt(i)=='E') {
                if(hashset.contains(x+" "+y+" "+x+" "+(y+1))) {
                    time++;
                }
                else if(hashset.contains(x+" "+(y+1)+" "+x+" "+y)) {
                    time++;
                }
                else {
                    time+=5;
                    hashset.add((x+" "+y+" "+x+" "+(y+1)));
                }
                y++;
            }
            if(str.charAt(i)=='W') {
                if(hashset.contains(x+" "+y+" "+x+" "+(y-1))) {
                    time++;
                }
                else if(hashset.contains(x+" "+(y-1)+" "+x+" "+y)) {
                    time++;
                }
                else {
                    time+=5;
                    hashset.add((x+" "+y+" "+x+" "+(y-1)));
                }
                y--;
            }
        }
        return time;
    }
}
