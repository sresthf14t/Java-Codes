/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codeforces_Round_712_Div_2;

/**
 *
 * @author Srest
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
        int n=input.scanInt();
        
        int arr[][]=new int[n][n];
        
        Stack<Integer>  stckx[]=new Stack[3];
        Stack<Integer>  stcky[]=new Stack[3];
        
        for(int i=0;i<3;i++) {
            stckx[i]=new Stack<>();
            stcky[i]=new Stack<>();
        }
        
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(i==0) {
                    if(j%2==0) {
                        arr[i][j]=1;
                    }
                    else {
                        arr[i][j]=2;
                    }
                    stckx[arr[i][j]].add(i);
                    stcky[arr[i][j]].add(j);
                    continue;
                }
                if(arr[i-1][j]==1) {
                    arr[i][j]=2;
                }
                else {
                    arr[i][j]=1;
                }
                stckx[arr[i][j]].add(i);
                stcky[arr[i][j]].add(j);
            }
        }
        
        for(int i=0;i<n*n;i++) {
            int val=input.scanInt();
            
            if(stckx[1].isEmpty()) {
                if(val==1 || val==3) {
                    System.out.println(2+" "+(stckx[2].pop()+1)+" "+(stcky[2].pop()+1));
                    System.out.flush();
                }
                else  {
                    System.out.println(3+" "+(stckx[2].pop()+1)+" "+(stcky[2].pop()+1));
                    System.out.flush();
                }
                continue;
            }
            
            if(stckx[2].isEmpty()) {
                if(val==2 || val==3) {
                    System.out.println(1+" "+(stckx[1].pop()+1)+" "+(stcky[1].pop()+1));
                    System.out.flush();
                }
                else {
                    System.out.println(3+" "+(stckx[1].pop()+1)+" "+(stcky[1].pop()+1));
                    System.out.flush();
                }
                continue;
            }
            
            if(val==1) {
                System.out.println(2+" "+(stckx[2].pop()+1)+" "+(stcky[2].pop()+1));
                System.out.flush();
                continue;
            }
            
            if(val==2) {
                System.out.println(1+" "+(stckx[1].pop()+1)+" "+(stcky[1].pop()+1));
                System.out.flush();
                continue;
            }
            
            if(stckx[1].size()>stckx[2].size()) {
                System.out.println(1+" "+(stckx[1].pop()+1)+" "+(stcky[1].pop()+1));
                System.out.flush();
                continue;
            }
            System.out.println(2+" "+(stckx[2].pop()+1)+" "+(stcky[2].pop()+1));
            System.out.flush();
        }
        
    }
}

