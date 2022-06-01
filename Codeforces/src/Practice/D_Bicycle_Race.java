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
public class D_Bicycle_Race {
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
        int n=input.scanInt()+1;
        float x[]=new float[n];
        float y[]=new float[n];
        for(int i=0;i<n;i++) {
            x[i]=input.scanInt();
            y[i]=input.scanInt();
        }
        int ans=0;
        for(int i=0;i<n-1;i++) {
            if(x[i]==x[(i+1)%n]) {
//                System.out.println("vert");
                if(vert(n,x,y,i,y[i]>y[(i+1)%n]?0:1)) {
                    ans++;
                }
            }
            else {
//                System.out.println("horiz");
                if(horiz(n,x,y,i,x[i]>x[(i+1)%n]?0:1)) {
                    ans++;
                }
            }
        }
        System.out.println(ans);
    }
    public static boolean horiz(int n,float x[],float y[],int indx,int dir) {
        float x1=x[indx];
        float x2=x[(indx+1)%n];
        float y1=y[indx];
        int cnt=0;
        for(int i=0;i<n-1;i++) {
             if(x[i]==x[(i+1)%n]) {
                continue;
             }
             if(y[i]!=y1) {
                 continue;
             }
             if(dir==0 && x[i]<Math.min(x1,x2)) {
                 y1+=0.5F;
                 break;
             }
             if(dir==1 && x[i]>Math.max(x1,x2)) {
                 y1+=0.5F;
                 break;
             }
        }
        for(int i=0;i<n-1;i++) {
            if(y[i]==y[(i+1)%n]) {
                continue;
            }
            if(y1<Math.min(y[i],y[(i+1)%n]) || y1>Math.max(y[i],y[(i+1)%n])) {
                continue;
            }
            if(dir==0 && x[i]<Math.min(x1,x2)) {
                cnt++;
            }
            if(dir==1 && x[i]>Math.max(x1,x2)) {
                cnt++;
            }
        }
//        System.out.println(cnt);
        if(cnt%2==1) {
            return true;
        }
        return false;
    }
    public static boolean vert(int n,float x[],float y[],int indx,int dir) {
        float y1=y[indx];
        float y2=y[(indx+1)%n];
        float x1=x[indx];
        int cnt=0;
        for(int i=0;i<n-1;i++) {
             if(y[i]==y[(i+1)%n]) {
                continue;
             }
             if(x[i]!=x1) {
                 continue;
             }
             if(dir==0 && y[i]<Math.min(y1,y2)) {
                 x1+=0.5F;
                 break;
             }
             if(dir==1 && y[i]>Math.max(y1,y2)) {
                 x1+=0.5F;
                 break;
             }
        }
        for(int i=0;i<n-1;i++) {
            if(x[i]==x[(i+1)%n]) {
                continue;
            }
            if(x1<Math.min(x[i],x[(i+1)%n]) || x1>Math.max(x[i],x[(i+1)%n])) {
                continue;
            }
            if(dir==0 && y[i]<Math.min(y1,y2)) {
                cnt++;
            }
            if(dir==1 && y[i]>Math.max(y1,y2)) {
                cnt++;
            }
        }
//        System.out.println(cnt);
        if(cnt%2==1) {
            return true;
        }
        return false;
    }
}
