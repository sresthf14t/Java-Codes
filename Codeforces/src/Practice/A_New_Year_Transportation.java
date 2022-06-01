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
public class A_New_Year_Transportation {
    static ArrayList<Integer> adj_lst[];
    public static void main(String args[]){
        Scanner input=new Scanner(System.in);
        //No of nodes
        int n=input.nextInt();
        int t=input.nextInt();
        adj_lst=new ArrayList[n];
        for(int i=0;i<n-1;i++) {
            adj_lst[i]=new ArrayList<Integer>();
            int in=input.nextInt();
            adj_lst[i].add(i+in);
        }
        adj_lst[n-1]=new ArrayList<Integer>();
//        for(int i=0;i<adj_lst.length;i++) {
//            System.out.print(i+"->");
//            for(int j=0;j<adj_lst[i].size();j++) {
//                System.out.print(adj_lst[i].get(j)+" ");
//            }
//            System.out.println();
//        }
        if(BFS(0,t-1)) {
            System.out.println("YES");
        }
        else {
            System.out.println("NO");
        }
    }
    
    static boolean BFS(int strt,int target) {
        boolean vis[]=new boolean[adj_lst.length];
        Queue<Integer> que = new LinkedList<>();
        que.add(strt);
        vis[strt]=true;
        while(!que.isEmpty()) {
            for(int i=0;i<adj_lst[que.peek()].size();i++) {
                if(!vis[adj_lst[que.peek()].get(i)]) {
                    que.add(adj_lst[que.peek()].get(i));
                    vis[adj_lst[que.peek()].get(i)]=true;
                }
            }
            que.poll();
        }
        if(vis[target]) {
            return true;
        }
        return false;
    }
}
