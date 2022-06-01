/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Round_C_2020;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class Stable_Wall {
    static ArrayList<Integer> adj_lst[];
    static boolean vis[];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        StringBuilder ans=new StringBuilder("");
        for(int t=1;t<=test;t++) {
            adj_lst=new ArrayList[26];
            vis=new boolean[26];
            for(int i=0;i<26;i++) {
                adj_lst[i]=new ArrayList<Integer>();
            }
            int n=input.nextInt();
            int m=input.nextInt();
            boolean app[]=new boolean[26];
            int arr[][]=new int[n][m];
            for(int i=0;i<n;i++) {
                String tmp=input.next();
                for(int j=0;j<m;j++) {
                    arr[i][j]=(int)(tmp.charAt(j))-65;
//                    System.out.println(arr[i][j]);
                    app[arr[i][j]]=true;
                }
            }
            for(int i=0;i<m;i++) {
                for(int j=n-1;j>0;j--) {
                    if(arr[j][i]!=arr[j-1][i]) {
                        if(!adj_lst[arr[j][i]].contains(arr[j-1][i])) {
                            adj_lst[arr[j][i]].add(arr[j-1][i]);
                        }
                    }
                }
            }
//            for(int i=0;i<adj_lst.length;i++) {
//                System.out.print(i+"->");
//                for(int j=0;j<adj_lst[i].size();j++) {
//                    System.out.print(adj_lst[i].get(j)+" ");
//                }
//                System.out.println();
//            }
            boolean is_pos=true;
            color=new int[26];
            StringBuilder tmp=new StringBuilder("");
            for(int i=0;i<26;i++) {
                if(vis[i] || !app[i]) {
                    continue;
                }
                if(mod_DFS(i)) {
                    is_pos=false;
                    break;
                }
            }
            System.out.print("Case #"+t+": ");
            if(!is_pos) {
                System.out.println(-1);
                continue;
            } 
            vis=new boolean[26];
            Stack<Integer> stck=new Stack<>();
            for(int i=0;i<26;i++) {
                if(!vis[i] && app[i]) {
                    top_sort(i,stck);
                }
            }
            while(!stck.isEmpty()) {
                char tmp1=(char)((int)stck.pop()+65);
                System.out.print(tmp1);
            }
            System.out.println();
        }
    }
    public static void top_sort(int root,Stack<Integer> stck) {
        vis[root]=true;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(!vis[adj_lst[root].get(i)]) {
                top_sort(adj_lst[root].get(i),stck);
            }
        }
        stck.push(root);
    }
    static int color[];
    public static boolean mod_DFS(int root) {
        color[root]=1;
        vis[root]=true;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(color[adj_lst[root].get(i)]==1) {
                return true;
            }
            if(!vis[adj_lst[root].get(i)]) {
                if(mod_DFS(adj_lst[root].get(i))) {
                    return true;
                }
            }
        }
        color[root]=-1;
        return false;
    }
}
