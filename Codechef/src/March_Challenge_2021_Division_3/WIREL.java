/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package March_Challenge_2021_Division_3;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class WIREL {
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
    
    public static void sort(int arr[],int l,int r) {    //sort(arr,0,n-1);
        if(l==r) {
            return;
        }
        int mid=(l+r)/2;
        sort(arr,l,mid);
        sort(arr,mid+1,r);
        merge(arr,l,mid,mid+1,r);
    }
    public static void merge(int arr[],int l1,int r1,int l2,int r2) {
        int tmp[]=new int[r2-l1+1];
        int indx1=l1,indx2=l2;
        //sorting the two halves using a tmp array
        for(int i=0;i<tmp.length;i++) {
            if(indx1>r1) {
                tmp[i]=arr[indx2];
                indx2++;
                continue;
            }
            if(indx2>r2) {
                tmp[i]=arr[indx1];
                indx1++;
                continue;
            }
            if(arr[indx1]<arr[indx2]) {
                tmp[i]=arr[indx1];
                indx1++;
                continue;
            }
            tmp[i]=arr[indx2];
            indx2++;
        }
        //Copying the elements of tmp into the main array
        for(int i=0,j=l1;i<tmp.length;i++,j++) {
            arr[j]=tmp[i];
        }
    }
    
    public static void sort(long arr[],int l,int r) {    //sort(arr,0,n-1);
        if(l==r) {
            return;
        }
        int mid=(l+r)/2;
        sort(arr,l,mid);
        sort(arr,mid+1,r);
        merge(arr,l,mid,mid+1,r);
    }
    public static void merge(long arr[],int l1,int r1,int l2,int r2) {
        long tmp[]=new long[r2-l1+1];
        int indx1=l1,indx2=l2;
        //sorting the two halves using a tmp array
        for(int i=0;i<tmp.length;i++) {
            if(indx1>r1) {
                tmp[i]=arr[indx2];
                indx2++;
                continue;
            }
            if(indx2>r2) {
                tmp[i]=arr[indx1];
                indx1++;
                continue;
            }
            if(arr[indx1]<arr[indx2]) {
                tmp[i]=arr[indx1];
                indx1++;
                continue;
            }
            tmp[i]=arr[indx2];
            indx2++;
        }
        //Copying the elements of tmp into the main array
        for(int i=0,j=l1;i<tmp.length;i++,j++) {
            arr[j]=tmp[i];
        }
    }
    
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        
        int cities=input.scanInt();
        int wires=input.scanInt();
        int cx[]=new int[cities];
        int cy[]=new int[cities];
        int w1x[]=new int[wires];
        int w1y[]=new int[wires];
        int w2x[]=new int[wires];
        int w2y[]=new int[wires];
        
        for(int i=0;i<cities;i++) {
            cx[i]=input.scanInt();
            cy[i]=input.scanInt();
        }
        
        for(int i=0;i<wires;i++) {
            w1x[i]=input.scanInt();
            w1y[i]=input.scanInt();
            w2x[i]=input.scanInt();
            w2y[i]=input.scanInt();
        }
        
        
        if(w1y[0]<w2y[0]) {
            int h=1000000+1-w1x[0];
            int k=1000000-w1y[0];
            ans.append(h+" "+k+"\n");
        }
        else if(w2y[0]<w1y[0]) {
            int h=1000000+1-w2x[0];
            int k=1000000-w2y[0];
            ans.append(h+" "+k+"\n");
        }
        else {
            if(w1x[0]<w2x[0]) {
                int h=1000000+1-w1x[0];
                int k=1000000-w1y[0];
                ans.append(h+" "+k+"\n");
            }
            else {
                int h=1000000+1-w2x[0];
                int k=1000000-w2y[0];
                ans.append(h+" "+k+"\n");
            }
        }
        
        long dist=Long.MAX_VALUE,indx=-1,side=-1;
        for(int i=1;i<wires;i++) {
            int h=-1-w1x[i];
            int k=0-w1y[i];
            long x1=w1x[i]+h,y1=w1y[i]+k,x2=w2x[i]+h,y2=w2y[i]+k,total=0;
            for(int j=0;j<cities;j++) {
                total+=Math.min(((x1-cx[i])*(x1-cx[i]))+((y1-cy[i])*(y1-cy[i])),((x2-cx[i])*(x2-cx[i]))+((y2-cy[i])*(y2-cy[i])));
            }
            if(total<dist) {
                dist=total;
                indx=i;
                side=0;
            }
            
            
            h=-1-w2x[i];
            k=0-w2y[i];
            x1=w1x[i]+h;
            y1=w1y[i]+k;
            x2=w2x[i]+h;
            y2=w2y[i]+k;
            total=0;
            for(int j=0;j<cities;j++) {
                total+=Math.min(((x1-cx[i])*(x1-cx[i]))+((y1-cy[i])*(y1-cy[i])),((x2-cx[i])*(x2-cx[i]))+((y2-cy[i])*(y2-cy[i])));
            }
            if(total<dist) {
                dist=total;
                indx=i;
                side=1;
            }
        }
        
        for(int i=1;i<wires;i++) {
            if(i!=indx) {
                ans.append(0+" "+0+"\n");
                continue;
            }
            if(side==0) {
                int h=-1-w1x[i];
                int k=0-w1y[i];
                ans.append(h+" "+k+"\n");
            }
            else {
                int h=-1-w2x[i];
                int k=0-w2y[i];
                ans.append(h+" "+k+"\n");
            }
        }
        
        System.out.println(ans);
    }
}
