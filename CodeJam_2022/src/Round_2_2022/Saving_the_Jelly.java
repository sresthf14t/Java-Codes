/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Round_2_2022;

/**
 *
 * @author SRESTH
 */
import java.util.*;
import java.io.*;
public class Saving_the_Jelly {
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
    
    static int n,childx[],childy[],toffeex[],toffeey[];
    static LinkedList<Integer> fin;
    public static void main(String args[]) throws IOException {
        Scan input=new Scan();
        StringBuilder ans=new StringBuilder("");
        int test=input.scanInt();
        for(int tt=1;tt<=test;tt++) {
            n=input.scanInt();
            childx=new int[n];
            childy=new int[n];
            toffeex=new int[n+1];
            toffeey=new int[n+1];
            for(int i=0;i<n;i++) {
                childx[i]=input.scanInt();
                childy[i]=input.scanInt();
            }
            for(int i=0;i<n+1;i++) {
                toffeex[i]=input.scanInt();
                toffeey[i]=input.scanInt();
            }
            fin=null;
            solve(new boolean[n],new boolean[n+1],new LinkedList<Integer>());
            if(fin==null) {
                ans.append("Case #"+tt+": "+"IMPOSSIBLE"+"\n");
                continue;
            }
            ans.append("Case #"+tt+": "+"POSSIBLE"+"\n");
            int indx=0;
            for(int i:fin) {
                if(indx%2==0) {
                    ans.append(i+" ");
                }
                else {
                    ans.append(i+"\n");
                }
                indx++;
            }
        }
        System.out.print(ans);
    }
    
    public static void solve(boolean child_done[],boolean toffee_taken[],LinkedList<Integer> way) {
        boolean all_done=true;
        for(int i=0;i<n;i++) {
            if(!child_done[i]) {
                all_done=false;
                break;
            }
        }
        if(all_done) {
            cpy(way);
            return;
        }
        for(int i=0;i<n;i++) {
            if(child_done[i]) {
                continue;
            }
            child_done[i]=true;
            LinkedList<Integer> ll=closest_toffee(i,toffee_taken);
            while(!ll.isEmpty()) {
                int toffee_indx=ll.removeLast();
                if(toffee_indx==0) {
                    continue;
                }
                
                way.add(i+1);
                way.add(toffee_indx+1);
                
                toffee_taken[toffee_indx]=true;
                solve(child_done,toffee_taken,way);
                toffee_taken[toffee_indx]=false;
                
                way.removeLast();
                way.removeLast();
            }
            child_done[i]=false;
        }
    }
    
    public static void cpy(LinkedList<Integer> way) {
        fin=new LinkedList<>();
        for(int i:way) {
            fin.add(i);
        }
    }
    
    public static LinkedList<Integer> closest_toffee(int child_indx,boolean toffee_taken[]) {
        long dist[]=new long[n+1];
        long min=Long.MAX_VALUE;
        for(int i=0;i<n+1;i++) {
            if(toffee_taken[i]) {
                dist[i]=Long.MAX_VALUE;
                continue;
            }
            long x=Math.abs(childx[child_indx]-toffeex[i]);
            long y=Math.abs(childy[child_indx]-toffeey[i]);
            x*=x;
            y*=y;
            dist[i]=x+y;
            min=Math.min(min,dist[i]);
        }
        LinkedList<Integer> ll=new LinkedList<>();
        for(int i=0;i<n+1;i++) {
            if(dist[i]==min) {
                ll.add(i);
            }
        }
        return ll;
    }
}
