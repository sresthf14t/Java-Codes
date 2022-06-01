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
public class C_Valera_and_Tubes {
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
        int k=input.scanInt();
        int x=1,y=1;
        StringBuilder ans=new StringBuilder("");
        boolean inc=true;
        for(int i=1;i<k;i++) {
            ans.append(2+" ");
            ans.append(y+" "+x+" ");
            if(x==m && inc) {
                inc=false;
                y++;
            }
            else if(x==1 && !inc) {
                inc=true;
                y++;
            }
            else {
                if(inc) {
                    x++;
                }
                else {
                    x--;
                }
            }
            
            ans.append(y+" "+x+" ");
            
            if(x==m && inc) {
                inc=false;
                y++;
            }
            else if(x==1 && !inc) {
                inc=true;
                y++;
            }
            else {
                if(inc) {
                    x++;
                }
                else {
                    x--;
                }
            }
            ans.append("\n");
        }
        int cnt=0;
        StringBuilder ans1=new StringBuilder("");
        while(true) {
            cnt++;
            ans1.append(y+" "+x+" ");
            if(x==m && y==n && inc) {
                break;
            }
            if(x==1 && y==n && !inc) {
                break;
            }
            if(x==m && inc) {
                inc=false;
                y++;
            }
            else if(x==1 && !inc) {
                inc=true;
                y++;
            }
            else {
                if(inc) {
                    x++;
                }
                else {
                    x--;
                }
            }
        }
        ans.append(cnt+" "+ans1);
        System.out.println(ans);
    }
}
