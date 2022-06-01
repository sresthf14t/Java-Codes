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
public class D_Secret_Passwords {
    static ArrayList<Integer> adj_lst[];
    static boolean vis[];
    public static void main(String args[]){
        Scanner input=new Scanner(System.in);
        //No of nodes
        int n=input.nextInt();
        boolean arr[][]=new boolean[n][26];
        for(int i=0;i<n;i++) {
            String str=input.next();
            for(int j=0;j<str.length();j++) {
                arr[i][str.charAt(j)-97]=true;
            }
        }
        adj_lst=new ArrayList[n];
        vis=new boolean[adj_lst.length];
        for(int i=0;i<adj_lst.length;i++) {
            adj_lst[i]=new ArrayList<Integer>();
        }
        //No of edges
        for(int i=0;i<26;i++) {
            int first=-1;
            for(int j=0;j<n;j++) {
                if(arr[j][i]) {
                    first=j;
                    break;
                }
            }
            if(first==-1) {
                continue;
            }
            for(int j=first+1;j<n;j++) {
                if(arr[j][i]) {
                    adj_lst[first].add(j);
                    adj_lst[j].add(first);
                }
            }
        }
//        for(int i=0;i<adj_lst.length;i++) {
//            System.out.print(i+"->");
//            for(int j=0;j<adj_lst[i].size();j++) {
//                System.out.print(adj_lst[i].get(j)+" ");
//            }
//            System.out.println();
//        }
        int count=0;
        for(int i=0;i<n;i++) {
            if(!vis[i]) {
                DFS(i);
                count++;
            }
        }
        System.out.println(count);
    }
    
    public static void DFS(int root) {
        vis[root]=true;
        for(int i=0;i<adj_lst[root].size();i++) {
            if(!vis[adj_lst[root].get(i)]) {
                DFS(adj_lst[root].get(i));
            }
        }
    }
}
