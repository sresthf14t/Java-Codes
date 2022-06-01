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
import java.io.*; 
import java.util.*;
public class D_Valid_BFS {
    static ArrayList<Integer> adj_lst[];
    public static void main(String args[]){
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        adj_lst=new ArrayList[n];
        for(int i=0;i<n;i++) {
            adj_lst[i]=new ArrayList<Integer>();
        }
        for(int i=0;i<n-1;i++) {
            int u=input.nextInt()-1;
            int v=input.nextInt()-1;
            adj_lst[u].add(v);
            adj_lst[v].add(u);
        }
        int arr[]=new int[n];
        for(int i=0;i<n;i++) {
            arr[i]=input.nextInt()-1;
        }
        if(BFS(n,arr)) {
            System.out.println("YES");
        }
        else  {
            System.out.println("NO");
        }
    }
    public static boolean BFS(int n,int arr[]) {
        boolean vis[]=new boolean[n];
        Queue<Integer> que = new LinkedList<>();
        que.add(0);
        vis[0]=true;
        if(arr[0]!=0) {
            return false;
        }
        int indx=1;
        Set<Integer> hashset=new HashSet<>();
        while(!que.isEmpty()) {
            for(int i=0;i<adj_lst[que.peek()].size();i++) {
                if(!vis[adj_lst[que.peek()].get(i)]) {
                    vis[adj_lst[que.peek()].get(i)]=true;
                    hashset.add(adj_lst[que.peek()].get(i));
                }
            }
            while(!hashset.isEmpty()) {
                if(hashset.contains(arr[indx])) {
                    que.add(arr[indx]);
                    hashset.remove(arr[indx]);
                    indx++;
                }
                else {
                    return false;
                }
            }
            que.poll();
        }
        return true;
    }
}
