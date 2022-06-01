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
public class WIREL1 {
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
    
    static int cities,wires,cx[],cy[],w1x[],w1y[],w2x[],w2y[],finx[],finy[],length[];
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        
        cities=input.scanInt();
        wires=input.scanInt();
        cx=new int[cities];
        cy=new int[cities];
        w1x=new int[wires];
        w1y=new int[wires];
        w2x=new int[wires];
        w2y=new int[wires];
        length=new int[wires];
        double slope[]=new double[wires];
        int windx[]=new int[wires];
        
        for(int i=0;i<cities;i++) {
            cx[i]=input.scanInt();
            cy[i]=input.scanInt();
        }
        
        for(int i=0;i<wires;i++) {
            w1x[i]=input.scanInt();
            w1y[i]=input.scanInt();
            w2x[i]=input.scanInt();
            w2y[i]=input.scanInt();
            
            length[i]=dist(w1x[i],w1y[i],w2x[i],w2y[i]);
            
            windx[i]=i;
            slope[i]=(w1y[i]-w2y[i]);
            slope[i]/=(w1x[i]-w2x[i]);
        }
        
//        sort(wires,length,windx,w1x,w1y,w2x,w2y);
        
        finx=new int[wires];
        finy=new int[wires];
        
        boolean taken[]=new boolean[wires];
        
        Queue<Integer> que=new LinkedList<>();
        int cnt=0;
        while(true) {
            cnt++;
            if(cnt>=1000) {
                break;
            }
            
            if(que.isEmpty()) {
                
                for(int i=0;i<2;i++) {
                    
                    for(int j=0;j<wires;j++) {
                        if(taken[j]) {
                            continue;
                        }
                        finx[j]=gethk(j,-1,0)[0];
                        finy[j]=gethk(j,-1,0)[1];

                        if(!check(j)) {
                            finx[j]=finy[j]=0;
                            continue;
                        }

                        que.add(j);
                        taken[j]=true;
                        break;
                    }
                    
                    
                }
                
                continue;
            }
            else {
                
                for(int i=0;i<2;i++) {
                    
                    for(int j=0;j<wires;j++) {
                        
                        if(taken[j]) {
                            continue;
                        }
                        
                        int x=getxy(que.peek())[0];
                        int y=getxy(que.peek())[1];
                        finx[j]=gethk(j,x,y)[0];
                        finy[j]=gethk(j,x,y)[1];

                        if(!check(j)) {
                            finx[j]=finy[j]=0;
                            continue;
                        }

                        que.add(j);
                        taken[j]=true;
                        break;
                    }
                }
            }
            
            que.poll();
        }
        
        
        
        que=new LinkedList<>();
        
        cnt=0;
        
        while(true) {
            cnt++;
            if(cnt>=1000) {
                break;
            }
            
            if(que.isEmpty()) {
                
                for(int i=0;i<2;i++) {
                    
                    for(int j=0;j<wires;j++) {
                        
                        if(taken[j]) {
                            continue;
                        }
                        
                        finx[j]=gethkupp(j,1000000+1,1000000)[0];
                        finy[j]=gethkupp(j,1000000+1,1000000)[1];

                        if(!checkupp(j)) {
                            finx[j]=finy[j]=0;
                            continue;
                        }

                        que.add(j);
                        taken[j]=true;
                        break;
                    }
                    
                    
                }
                
                continue;
            }
            else {
                
                for(int i=0;i<2;i++) {
                    
                    for(int j=0;j<wires;j++) {
                        
                        if(taken[j]) {
                            continue;
                        }
                        
                        int x=getxyupp(que.peek())[0];
                        int y=getxyupp(que.peek())[1];
                        finx[j]=gethkupp(j,x,y)[0];
                        finy[j]=gethkupp(j,x,y)[1];

                        if(!checkupp(j)) {
                            finx[j]=finy[j]=0;
                            continue;
                        }

                        que.add(j);
                        taken[j]=true;
                        break;
                    }
                    
                    
                }
            }
            
            que.poll();
        }
        
        
        for(int i=0;i<wires;i++) {
            
            if(taken[i]) {
                continue;
            }
            
            finx[i]=gethk(i,1000000+1,1000000)[0];
            finy[i]=gethk(i,1000000+1,1000000)[1];
            
            if(Math.abs(finx[i])>1000000 || Math.abs(finy[i])>1000000) {
                finx[i]=finy[i]=0;
                continue;
            }
            
            break;
        }
        
//        sort(wires,windx,finx,finy);
        
        for(int i=0;i<wires;i++) {
            ans.append(finx[i]+" "+finy[i]+"\n");
        }
        
//        System.out.println();
//        for(int i=0;i<wires;i++) {
//            System.out.println((w1x[i]+finx[i])+" "+(w1y[i]+finy[i])+" "+(w2x[i]+finx[i])+" "+(w2y[i]+finy[i]));
//        }
        
        System.out.println(ans);
    }
    
    
    public static void sort(int n,int arr[],int brr[],int crr[],int drr[],int err[],int frr[]) {
        for(int i=0;i<n;i++) {
            for(int j=0;j<n-1;j++) {
                if(arr[j]>arr[j+1]) {
                    swap(arr,j,j+1);
                    swap(brr,j,j+1);
                    swap(crr,j,j+1);
                    swap(drr,j,j+1);
                    swap(err,j,j+1);
                    swap(frr,j,j+1);
                }
            }
        }
    }
    
    public static void sort(int n,int arr[],int brr[],int crr[]) {
        for(int i=0;i<n;i++) {
            for(int j=0;j<n-1;j++) {
                if(arr[j]>arr[j+1]) {
                    swap(arr,j,j+1);
                    swap(brr,j,j+1);
                    swap(crr,j,j+1);
                }
            }
        }
    }
    
    public static void swap(int arr[],int i,int j) {
        int tmp=arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }
    
    public static int dist(long x1,long y1,long x2,long y2) {
        long dist=(x1-x2)*(x1-x2);
        dist+=(y1-y2)*(y1-y2);
        dist=(long)(Math.sqrt(dist));
        return (int)dist;
    }
    
    public static int[] getxy(int indx) {
        int xy[]=new int[2];
        if(w1y[indx]>w2y[indx]) {
            xy[0]=w1x[indx];
            xy[1]=w1y[indx];
        }
        else if(w2y[indx]>w1y[indx]) {
            xy[0]=w2x[indx];
            xy[1]=w2y[indx];
        }
        else {
            if(w1x[indx]>w2x[indx]) {
                xy[0]=w1x[indx];
                xy[1]=w1y[indx];
            }
            else {
                xy[0]=w2x[indx];
                xy[1]=w2y[indx];
            }
        }
        
        xy[0]+=finx[indx];
        xy[1]+=finy[indx];
        
        return xy;
    }
    
    public static int[] gethk(int indx,int x,int y) {
        int hk[]=new int[2];
        if(w1y[indx]<w2y[indx]) {
            hk[0]=x-w1x[indx];
            hk[1]=y-w1y[indx];
        }
        else if(w2y[indx]<w1y[indx]) {
            hk[0]=x-w2x[indx];
            hk[1]=y-w2y[indx];
        }
        else {
            if(w1x[indx]<w2x[indx]) {
                hk[0]=x-w1x[indx];
                hk[1]=y-w1y[indx];
            }
            else {
                hk[0]=x-w2x[indx];
                hk[1]=y-w2y[indx];
            }
        }
        return hk;
    }
    
    public static int[] getxyupp(int indx) {
        int xy[]=new int[2];
        if(w1y[indx]<w2y[indx]) {
            xy[0]=w1x[indx];
            xy[1]=w1y[indx];
        }
        else if(w2y[indx]<w1y[indx]) {
            xy[0]=w2x[indx];
            xy[1]=w2y[indx];
        }
        else {
            if(w1x[indx]<w2x[indx]) {
                xy[0]=w1x[indx];
                xy[1]=w1y[indx];
            }
            else {
                xy[0]=w2x[indx];
                xy[1]=w2y[indx];
            }
        }
        
        xy[0]+=finx[indx];
        xy[1]+=finy[indx];
        
        return xy;
    }
    
    public static int[] gethkupp(int indx,int x,int y) {
        int hk[]=new int[2];
        if(w1y[indx]>w2y[indx]) {
            hk[0]=x-w1x[indx];
            hk[1]=y-w1y[indx];
        }
        else if(w2y[indx]>w1y[indx]) {
            hk[0]=x-w2x[indx];
            hk[1]=y-w2y[indx];
        }
        else {
            if(w1x[indx]>w2x[indx]) {
                hk[0]=x-w1x[indx];
                hk[1]=y-w1y[indx];
            }
            else {
                hk[0]=x-w2x[indx];
                hk[1]=y-w2y[indx];
            }
        }
        return hk;
    }
    
    public static boolean check(int indx) {
        if(Math.abs(finx[indx])>1000000 || Math.abs(finy[indx])>1000000) {
            return false;
        }
        if(w1x[indx]+finx[indx]>=500000) {
            return false;
        }
//        if(w1y[indx]+finy[indx]>=500000) {
//            return false;
//        }
        if(w2x[indx]+finx[indx]>=500000) {
            return false;
        }
//        if(w2y[indx]+finy[indx]>=500000) {
//            return false;
//        }
        return true;
    }
    
    public static boolean checkupp(int indx) {
        if(Math.abs(finx[indx])>1000000 || Math.abs(finy[indx])>1000000) {
            return false;
        }
        if(w1x[indx]+finx[indx]<=500000) {
            return false;
        }
//        if(w1y[indx]+finy[indx]<=500000) {
//            return false;
//        }
        if(w2x[indx]+finx[indx]<=500000) {
            return false;
        }
//        if(w2y[indx]+finy[indx]<=500000) {
//            return false;
//        }
        return true;
    }
    
    
    
}
