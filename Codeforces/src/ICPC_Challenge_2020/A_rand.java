/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ICPC_Challenge_2020;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class A_rand {
    static ArrayList<Integer> adj_lst[];
    static boolean vis[];
    static int color[];
    public static void main(String args[]) throws IOException {
        Scanner input=new Scanner(new File("C:\\Users\\User\\Desktop\\ICPC Challenge 2020 Practice\\Main\\icpc-challenge-2020-tests(1)\\a1.in"));
        int n=200000;
        adj_lst=new ArrayList[n];
        vis=new boolean[adj_lst.length];
        color=new int[adj_lst.length];
        int max_indx=-1;
        while(input.hasNext()) {
            int u=input.nextInt();
            int v=input.nextInt();
            if(adj_lst[u]==null) {
                adj_lst[u]=new ArrayList<>();
            }
            if(adj_lst[v]==null) {
                adj_lst[v]=new ArrayList<>();
            }
            max_indx=Math.max(max_indx,u);
            max_indx=Math.max(max_indx,v);
            adj_lst[u].add(v);
            adj_lst[v].add(u);
        }
//        System.out.println(max_indx);
//        System.exit(0);
        int size[]=new int[n];
        int indx[]=new int[n];
        for(int i=0;i<n;i++) {
            indx[i]=i;
        }
        for(int i=0;i<n;i++) {
            if(adj_lst[i]!=null) {
                size[i]=adj_lst[i].size();
            }
        }
        sort(size,indx,n);
        System.out.println("Input Done");
        int clr=1;
        for(int i=n-1;i>=0;i--) {
            int j=indx[i];
            if(adj_lst[j]!=null && !vis[j]) {
                BFS(j,clr);
                clr++;
            }
        }
        ArrayList<Integer> freq[]=new ArrayList[n+1];
        for(int i=0;i<n;i++) {
            freq[i]=new ArrayList<>();
        }
        for(int i=0;i<n;i++) {
            if(adj_lst[i]!=null) {
                freq[color[i]].add(i);
            }
//            System.out.println(color[i]);
        }
        StringBuilder ans= new StringBuilder("");
        int max=0;
        for(int i=0;i<n+1;i++) {
            if(freq[i]==null || freq[i].size()==0) {
                continue;
            }
            max=i;
            for(int j=0;j<freq[i].size();j++) {
                ans.append(freq[i].get(j)+" ");
            }
            ans.append("\n");
        }
        System.out.println("Max="+max);
        System.out.println("Writing To File");
        write(ans);
    }
    
    static void BFS(int source,int clr) {
        int depth[]=new int[adj_lst.length];
        Queue<Integer> que = new LinkedList<>();
        que.add(source);
        depth[source]=0;
        vis[source]=true;
        while(!que.isEmpty()) {
//            if(depth[que.peek()]>2) {
//                que.poll();
//                continue;
//            }
            if(depth[que.peek()]>2) {
                long cnt=0;
                for(int i=0;i<adj_lst[que.peek()].size();i++) {
                    if(color[adj_lst[que.peek()].get(i)]==clr) {
                        cnt++;
                    }
                }
                long rem=adj_lst[que.peek()].size();
                rem-=cnt;
//                cnt=(int)((float)cnt*(float)1.5);
                cnt*=(depth[que.peek()]+1);
                if(rem>(int)(((float)1.9)*(float)cnt)) {
                    
                }
                else {
                    que.poll();
                    continue;
                }
            }
            for(int i=0;i<adj_lst[que.peek()].size();i++) {
                if(!vis[adj_lst[que.peek()].get(i)]) {
                    que.add(adj_lst[que.peek()].get(i));
                    vis[adj_lst[que.peek()].get(i)]=true;
                    color[adj_lst[que.peek()].get(i)]=clr;
                    depth[adj_lst[que.peek()].get(i)]=depth[que.peek()]+1;
                }
            }
            que.poll();
        }
    }
    
    public static void write(StringBuilder ans) {
        PrintWriter outputstream=null;
        try {
        outputstream=new PrintWriter(new FileOutputStream("C:\\Users\\User\\Desktop\\ICPC Challenge 2020 Practice\\Main\\icpc-challenge-2020-tests(1)\\a1_ans.txt",false));
        }
        catch(FileNotFoundException e) {
            
        }
        outputstream.println(ans);
        outputstream.close();
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
  
  static void sort(int arr[],int brr[], int n) 
  { 
    buildMaxHeap(arr,brr, n); 
  
    for (int i = n - 1; i > 0; i--) 
    { 
      // swap value of first indexed 
      // with last indexed 
      swap(arr, 0, i); 
  
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
        if (index < i && arr[j] < arr[index])  {
          swap(arr, j, index); 
          swap(brr, j, index);
        }
  
        j = index; 
  
      } while (index < i); 
    } 
  } 
  
  public static void swap(int[] a, int i, int j) { 
    int temp = a[i]; 
    a[i]=a[j]; 
    a[j] = temp; 
  } 
}
