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
public class Cache_System {
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
    
    
    static long final_score,final_total_moves;
    static StringBuilder final_ans1,final_ans2;
    static int n,m,sc[],sr[],vc[],vr[],curr[],move[],cache[],total_moves;
    static HashSet<Integer> cache_set;
    static HashSet<Integer> hashset[];
    public static void main(String args[]) throws IOException {
        long startTime = System.nanoTime();
        

        Scan input=new Scan();
        final_score=100000000000L;
        total_moves=0;
        n=input.scanInt();
        m=input.scanInt();
        sc=new int[n];
        sr=new int[n];
        vc=new int[m];
        vr=new int[m];
        curr=new int[m];
        move=new int[m];
        cache_set=new HashSet<>();
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
        cache=new int[Math.min(1,n/10)];
        int resrc[]=new int[n];
        int indx[]=new int[n];
        for(int i=0;i<n;i++) {
            indx[i]=i;
            resrc[i]=sc[i]+sr[i];
        }
        
        heapSort(resrc,indx,n);
        solve_with_cache();
        for(int i=0;i<cache.length;i++) {
            cache[i]=indx[i];
            cache_set.add(indx[i]);
        }
        hashset=new HashSet[n];
        for(int j=0;j<n;j++) {
            hashset[j]=new HashSet<>();
        }
//        solve(startTime);
        if(final_ans1==null) {
            final_ans1=new StringBuilder("");
        }
        if(final_ans2==null) {
            final_ans2=new StringBuilder("");
        }
        System.out.println(final_total_moves+"\n"+final_ans1+final_ans2);
    }
    
    public static void solve_with_cache() {
        StringBuilder fin=new StringBuilder("");
        StringBuilder tmp_fin=new StringBuilder("");
        boolean done[]=new boolean[m];
        Stack<Integer> vm_stck=new Stack<>();
        int move_count[]=new int[n];
        int sub_moves=0;
        for(int i=0;i<m;i++) {
            if(curr[i]==move[i]) {
                done[i]=true;
                continue;
            }
            if(cache_set.contains(curr[i])) {
                for(int j=0;j<n;j++) {
                    if(cache_set.contains(j)) {
                        continue;
                    }
                    if(move[i]==j) {
                        continue;
                    }
                    if(move_count[j]>=2) {
                        continue;
                    }
                    if(sc[j]>=vc[i] && sr[j]>=vr[i] && move_count[j]<2 && move_count[j]<2) {
                        tmp_fin.append(curr[i]+" "+j+" "+i+"\n");
                        sub_moves++;
                        sc[j]+=vc[i];
                        sr[j]+=vr[i];
                        vm_stck.add(i);
                        move_count[curr[i]]++;
                        move_count[j]++;
                    }
                }
            }
        }
        while(!vm_stck.isEmpty()) {
            sc[curr[vm_stck.peek()]]-=vc[vm_stck.peek()];
            sr[curr[vm_stck.peek()]]-=vr[vm_stck.peek()];
            curr[vm_stck.peek()]=move[vm_stck.peek()];
            done[vm_stck.peek()]=true;
            vm_stck.pop();
        }
        if(sub_moves>0) {
            total_moves++;
            fin.append(sub_moves+"\n"+tmp_fin);
        }
        
        
        for(int i=0;i<100;i++) {
            tmp_fin=new StringBuilder("");
            
            sub_moves=0;
            move_count=new int[n];
            for(int j=0;j<m;j++) {
                if(done[j]) {
                    continue;
                }
                if(curr[j]==move[j]) {
                    done[j]=true;
                    continue;
                }
                if(cache_set.contains(move[j])) {
                    continue;
                }
                if(sc[move[j]]>=vc[j] && sr[move[j]]>=vr[j] && move_count[move[j]]<2 && move_count[move[j]]<2 && move_count[curr[j]]<2 && move_count[curr[j]]<2) {
                    tmp_fin.append(curr[j]+" "+move[j]+" "+j+"\n");
                    sub_moves++;
                    sc[move[j]]+=vc[j];
                    sr[move[j]]+=vr[j];
                    vm_stck.add(j);
                    move_count[curr[j]]++;
                    move_count[j]++;
                }
            }
            if(sub_moves==0) {
                
                for(int j=0;j<m;j++) {
                    for(int k=0;k<cache.length;k++) {
                        if(sc[k]>=vc[j] && sr[k]>=vr[j] && move_count[k]<2 && move_count[k]<2 && move_count[curr[j]]<2 && move_count[curr[j]]<2) {
                            tmp_fin.append(curr[j]+" "+k+" "+j+"\n");
                            sub_moves++;
                            sc[k]+=vc[j];
                            sr[k]+=vr[j];
                            vm_stck.add(j);
                            move_count[curr[j]]++;
                            move_count[k]++;
                        }
                    }
                }
                while(!vm_stck.isEmpty()) {
                    sc[curr[vm_stck.peek()]]-=vc[vm_stck.peek()];
                    sr[curr[vm_stck.peek()]]-=vr[vm_stck.peek()];
                    vm_stck.pop();
                }
            }
            while(!vm_stck.isEmpty()) {
                sc[curr[vm_stck.peek()]]-=vc[vm_stck.peek()];
                sr[curr[vm_stck.peek()]]-=vr[vm_stck.peek()];
                curr[vm_stck.peek()]=move[vm_stck.peek()];
                done[vm_stck.peek()]=true;
                vm_stck.pop();
            }
            if(sub_moves>0) {
                total_moves++;
                fin.append(sub_moves+"\n"+tmp_fin);
            }
        }
        final_ans1=new StringBuilder(""+fin);
    }
    
    
    
    public static void solve(long startTime) {
        long steps=0,mem=0;
        StringBuilder ans=new StringBuilder("");
        TreeSet<Integer> ts=new TreeSet<>();
        for(int i=0;i<m;i++) {
            if(curr[i]==move[i]) {
                continue;
            }
            ts.add(i);
        }
        int total=0;
        Stack<Integer> stck=new Stack<>();
        int fail_cnt=0;
        while(!ts.isEmpty()) {
            int count[]=new int[n];
            long endTime   = System.nanoTime();
            long totalTime = endTime - startTime;
            if(totalTime>5700000000L) {
                break;
            }
            StringBuilder fin=new StringBuilder("");
            int arr[]=new int[ts.size()];
            int indx=0;
            int tmp_vr[]=new int[ts.size()];
            for(int i:ts) {
                arr[indx]=i;
                tmp_vr[indx]=vr[i];
                indx++;
            }
            vmsort(arr.length,arr,tmp_vr);
            rand_arr(arr.length,arr,arr.length/10);
            int cnt=0;
            for(int i=0;i<arr.length;i++) {
                
                int ii=arr[i];
                if(curr[ii]==move[ii]) {
                    stck.add(ii);
                    continue;
                }
                if(sc[move[ii]]>=vc[ii] && sr[move[ii]]>=vr[ii] && count[curr[ii]]<2 && count[move[ii]]<2) {
                    count[curr[ii]]++;
                    count[move[ii]]++;
                    stck.add(ii);
                    cnt++;
                    fin.append(curr[ii]+" "+move[ii]+" "+ii+"\n");
                    sc[move[ii]]-=vc[ii];
                    sr[move[ii]]-=vr[ii];
                    mem+=vr[ii];
                    hashset[move[ii]].remove(i);
                }
            }
            if(cnt==0) {
                Stack<Integer> tmp=new Stack<>();
                Stack<Integer> nc=new Stack<>();
                for(int i=0;i<arr.length;i++) {
                    int free_cnt=0;
                    int ii=arr[i];
                    Queue<Integer> que=new LinkedList<>();
                    for(int j=0;j<n;j++) {
                        que.add(j);
                    }
                    for(int j=0;j<n;j++) {
                        if(hashset[que.peek()].size()!=0) {
                            int tmp1=que.poll();
                            que.add(tmp1);
                        }
                        else {
                            free_cnt++;
                        }
                    }
                    free_cnt=Math.max(free_cnt,1000);
                    while(!que.isEmpty() && free_cnt>0) {
                        free_cnt--;
                        int j=que.poll();
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
                            if(j==move[ii]) {
                                hashset[move[ii]].remove(ii);
                            }
                            break;
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
            while(!stck.isEmpty()) {
                sc[curr[stck.peek()]]+=vc[stck.peek()];
                sr[curr[stck.peek()]]+=vr[stck.peek()];
                ts.remove(stck.pop());
            }
            if(cnt==0) {
                break;
            }
            else {
                total++;
                ans.append(cnt+"\n"+fin);
            }
        }
        if(!ts.isEmpty()) {
            return;
        }
        steps=total;
        long score=steps*mem;
        if(score<final_score) {
            final_score=score;
        }
        final_total_moves=total_moves+total;
        final_ans2=new StringBuilder(ans);
    }
    
    
    public static void rand_arr(int n,int arr[],int lim) {
        for(int i=0;i<Math.min(20000, lim);i++) {
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
    public static void vmsort(int n,int arr[],int vr[]) {
        int tmp[]=new int[vr.length];
        for(int i=0;i<tmp.length;i++) {
            tmp[i]=vr[i];
        }
        heapSort(tmp,arr,n);
        for(int i=0,j=n-1;i<j;i++,j--) {
            swap(arr,i,j);
        }
    }
    
    
    static void buildMaxHeap(int arr[],int brr[], int n) 
  { 
    for (int i = 1; i < n; i++) 
    { 
      // if child is bigger than parent 
      if (arr[i] > arr[(i - 1) / 2]) 
      { 
        int j = i; 
  
        // swap child and parent until 
        // parent is smaller 
        while (arr[j] > arr[(j - 1) / 2]) 
        { 
          swap(arr, j, (j - 1) / 2); 
          swap(brr, j, (j - 1) / 2);
          j = (j - 1) / 2; 
        } 
      } 
    } 
  } 
  
  static void heapSort(int arr[],int brr[], int n) 
  { 
    buildMaxHeap(arr,brr, n); 
  
    for (int i = n - 1; i > 0; i--) 
    { 
      // swap value of first indexed 
      // with last indexed 
      swap(arr, 0, i); 
      swap(brr, 0, i); 
  
      // maintaining heap property 
      // after each swapping 
      int j = 0, index; 
  
      do
      { 
        index = (2 * j + 1); 
  
        // if left child is smaller than 
        // right child point index variable 
        // to right child 
        if (index < (i - 1) && arr[index] < arr[index + 1]) 
          index++; 
  
        // if parent is smaller than child 
        // then swapping parent with child 
        // having higher value 
        if (index < i && arr[j] < arr[index]) {
          swap(arr, j, index); 
          swap(brr, j, index); 
        }
  
        j = index; 
  
      } while (index < i); 
    } 
  } 
}
