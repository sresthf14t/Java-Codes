/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package April_Challenge_2021_Division_3;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class WTRSORT {
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
        
        int n=input.scanInt();
        int m=input.scanInt();
        
        for(int i=0;i<n+2;i++) {
            int tmp=input.scanInt();
        }
        
        boolean match[][]=new boolean[n][n];
        
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                match[i][j]=(input.scanInt()>0);
            }
        }
        
        Stack<Integer> tube[]=new Stack[n+2];
        for(int i=0;i<n+2;i++) {
            tube[i]=new Stack<>();
        }
        
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                tube[i].push(input.scanInt()-1);
            }
        }
        
        int ops=0;
        
        int max[]=new int[n+2];
        for(int i=0;i<n;i++) {
            max[i]=100;
        }
        max[n]=max[n+1]=10000;
        
        for(int i=0;i<n+2;i++) {
            for(int j=0;j<max[i];j++) {
                ops++;
                ans.append(2+" "+(i+1)+"\n");
            }
        }
        
        while(true) {
            boolean fin=true;
            for(int i=0;i<n;i++) {
//                System.out.print(tube[i].size()+" ");
                if(!tube[i].isEmpty()) {
                    fin=false;
                    break;
                }
            }
//            System.out.println();
            if(fin) {
                break;
            }
            boolean done=false;
            
            for(int i=0;i<n;i++) {
                
                int to=(int)(Math.random()*1000);
                to%=2;
                to+=n;
                
                if(tube[i].isEmpty()) {
                    continue;
                }
                if(tube[to].isEmpty()) {
                    tube[to].push(tube[i].pop());
                    ops++;
                    ans.append(1+" "+(i+1)+" "+(to+1)+"\n");
                    done=true;
                    break;
                }
                if(match[tube[i].peek()][tube[to].peek()]) {
                    tube[to].push(tube[i].pop());
                    ops++;
                    ans.append(1+" "+(i+1)+" "+(to+1)+"\n");
                    done=true;
                    break;
                }
            }
            
            
            if(!done) {
                for(int i=0;i<5;i++) {
                    int u=(int)(Math.random()*(n+2));
                    int v=(int)(Math.random()*(n+2));
                    if(tube[u].isEmpty() ||tube[v].size()==max[v] || u==v) {
                        i--;
                        continue;
                    }
                    if(tube[v].isEmpty() || match[tube[u].peek()][tube[v].peek()]) {
                        ops++;
                        tube[v].push(tube[u].pop());
                        ans.append(1+" "+(u+1)+" "+(v+1)+"\n");
                    }
                }
            }
        }
        
        while(!tube[n].isEmpty()) {
            ops++;
            ans.append(1+" "+(n+1)+" "+(tube[n].pop()+1)+"\n");
        }
        
        while(!tube[n+1].isEmpty()) {
            ops++;
            ans.append(1+" "+(n+1+1)+" "+(tube[n+1].pop()+1)+"\n");
        }
        
        System.out.println(0+" "+ops);
        System.out.println(ans);
    }
    
}
