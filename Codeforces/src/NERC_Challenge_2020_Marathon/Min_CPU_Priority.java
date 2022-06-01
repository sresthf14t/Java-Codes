/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NERC_Challenge_2020_Marathon;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class Min_CPU_Priority {
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
    
    
    static long final_score;
    static StringBuilder final_ans;
    public static void main(String args[]) throws IOException {
        long startTime = System.nanoTime();
        final_score=Long.MAX_VALUE;
        
        
        
        Scan input=new Scan();
        
        
        int n=input.scanInt();
        int m=input.scanInt();
        int sc[]=new int[n];
        int sr[]=new int[n];
        int vc[]=new int[m];
        int vr[]=new int[m];
        int curr[]=new int[m];
        int move[]=new int[m];
        for(int i=0;i<n;i++) {
            sc[i]=input.scanInt();
            sr[i]=input.scanInt();
        }
        
        for(int i=0;i<m;i++) {
            vc[i]=input.scanInt();
            vr[i]=input.scanInt();
        }
        
        
        for(int i=0;i<m;i++) {
            curr[i]=input.scanInt();
            move[i]=input.scanInt();
            sc[curr[i]]-=vc[i];
            sr[curr[i]]-=vr[i];
        }
        
        
        int sc1[]=new int[sc.length];
        int sr1[]=new int[sr.length];
        int vc1[]=new int[vc.length];
        int vr1[]=new int[vr.length];
        int curr1[]=new int[curr.length];
        int move1[]=new int[move.length];
        copy(sc,sc1);
        copy(sr,sr1);
        copy(vc,vc1);
        copy(vr,vr1);
        copy(curr,curr1);
        copy(move,move1);
        solve_min_cpu_priority(startTime,n,m,sc1,sr1,vc1,vr1,curr1,move1,false);
        
        System.out.println(final_ans);
    }
    
    
    public static void solve_min_cpu_priority(long startTime,int n,int m,int sc[],int sr[],int vc[],int vr[],int curr[],int move[],boolean rand) {
        long steps=0,mem=0;
        StringBuilder ans=new StringBuilder("");
        TreeMap<Integer,TreeSet<Integer>> tm=new TreeMap<>();
        for(int i=0;i<m;i++) {
            if(curr[i]==move[i]) {
                continue;
            }
            if(!tm.containsKey(vc[i])) {
                TreeSet<Integer> ts=new TreeSet<>();
                tm.put(vc[i], ts);
            }
            TreeSet<Integer> ts=tm.get(vc[i]);
            ts.add(i);
        }
        int total=0;
        Stack<Integer> stck_tm=new Stack<>();
        Stack<Integer> stck_ts=new Stack<>();
        
        while(!tm.isEmpty()) {
            int count[]=new int[n];
            long endTime   = System.nanoTime();
            long totalTime = endTime - startTime;
            if(totalTime>5700000000L) {
                break;
            }
            StringBuilder fin=new StringBuilder("");
            int cnt=0;
            for(int i:tm.keySet()) {
                TreeSet<Integer> ts=tm.get(i);
                for(int ii:ts) {
                    if(curr[ii]==move[ii]) {
                        stck_tm.add(i);
                        stck_ts.add(ii);
                        continue;
                    }
                    if(sc[move[ii]]>=vc[ii] && sr[move[ii]]>=vr[ii] && count[curr[ii]]<2 && count[move[ii]]<2) {
                        count[curr[ii]]++;
                        count[move[ii]]++;
                        stck_tm.add(i);
                        stck_ts.add(ii);
                        cnt++;
                        fin.append(curr[ii]+" "+move[ii]+" "+ii+"\n");
                        sc[move[ii]]-=vc[ii];
                        sr[move[ii]]-=vr[ii];
                        mem+=vr[ii];
                    }
                }
                
            }
            if(cnt==0) {
                Stack<Integer> tmp=new Stack<>();
                Stack<Integer> nc=new Stack<>();
                for(int i:tm.keySet()) {
                    TreeSet<Integer> ts=tm.get(i);
                    for(int ii:ts) {
                        int arrange[]=arrange(n);
                        for(int k=0;k<n;k++) {
                            int j=arrange[k];
                            if(j==curr[ii]) {
                                continue;
                            }
                            if(sc[j]>=vc[ii] && sr[j]>=vr[ii] && count[curr[ii]]<2 && count[j]<2) {
                                count[curr[ii]]++;
                                count[j]++;
                                cnt++;
                                fin.append(curr[ii]+" "+j+" "+ii+"\n");
                                sc[j]-=vc[ii];
                                sr[j]-=vr[ii];
                                tmp.add(ii);
                                nc.add(j);
                                mem+=vr[ii];
                                break;
                            }
                        }
                    }
                }
                while(!tmp.isEmpty()) {
                    sc[curr[tmp.peek()]]+=vc[tmp.peek()];
                    sr[curr[tmp.peek()]]+=vr[tmp.peek()];
                    curr[tmp.peek()]=nc.pop();
                    tmp.pop();
                }
            }
            while(!stck_tm.isEmpty()) {
                int indx=stck_tm.peek();
                TreeSet<Integer> ts=tm.get(stck_tm.pop());
                sc[curr[stck_ts.peek()]]+=vc[stck_ts.peek()];
                sr[curr[stck_ts.peek()]]+=vr[stck_ts.peek()];
                ts.remove(stck_ts.pop());
                if(ts.isEmpty()) {
                    tm.remove(indx);
                }
            }
            if(cnt==0) {
                break;
            }
            else {
                total++;
                ans.append(cnt+"\n"+fin);
            }
            steps=total;
            long score=steps*mem; 
            if(score>=final_score) {
                return;
            }
        }
        if(!tm.isEmpty()) {
            return;
        }
        steps=total;
        long score=steps*mem;
        if(score<final_score) {
            final_score=score;
            final_ans=new StringBuilder(total+"\n"+ans);
        }
//        return new StringBuilder(total+"\n"+ans);
//        System.out.println(total+"\n"+ans);
    }
    
    public static void rand_arr(int n,int arr[]) {
        for(int i=0;i<Math.min(20000, n/2);i++) {
            int l=(int)(Math.random()*(arr.length-1));
            int r=(int)(Math.random()*(arr.length-1));
            swap(arr,l,r);
        }
    }
    public static void swap(int arr[],int l,int r) {
        int tmp=arr[r];
        arr[r]=arr[l];
        arr[l]=tmp;
    }
    public static void copy(int arr[],int cpy[]) {
        for(int i=0;i<arr.length;i++) {
            cpy[i]=arr[i];
        }
    }
    
    public static int[] arrange(int n) {
        int arr[]=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=i;
        }
        rand_arr(n,arr);
        return arr;
    }
}
