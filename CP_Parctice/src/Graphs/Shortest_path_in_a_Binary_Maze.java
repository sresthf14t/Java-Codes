/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphs;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
//0->TRAVERSABLE 1->Blcoked
public class Shortest_path_in_a_Binary_Maze {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int m=input.nextInt();
        int arr[][]=new int[n][m];
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                arr[i][j]=input.nextInt();
            }
        }
        System.out.println(BFS(arr,0,0,n,m,m-1,m-1));
    }
    static StringBuilder BFS(int arr[][],int src_x,int src_y,int n,int m,int target_i,int target_j) {
        boolean vis[][]=new boolean[n][m];
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(arr[i][j]==1) {
                    vis[i][j]=true;
                }
            }
        }
        int parent_i[][]=new int[n][m];
        int parent_j[][]=new int[n][m];
        Queue<Integer> que_i = new LinkedList<>();
        Queue<Integer> que_j = new LinkedList<>();
        que_i.add(src_x);
        que_j.add(src_y);
        vis[src_x][src_y]=true;
        parent_i[src_x][src_y]=-1;
        parent_j[src_x][src_y]=-1;
        while(!que_i.isEmpty() && !que_j.isEmpty()) {
            int i=que_i.peek();
            int j=que_j.peek();
            if(target_i==i && target_j==j) {
                break;
            }
            if(i!=0 && !vis[i-1][j]) {
                que_i.add(i-1);
                que_j.add(j);
                vis[i-1][j]=true;
                parent_i[i-1][j]=i;
                parent_j[i-1][j]=j;
            }
            if(i!=n-1 && !vis[i+1][j]) {
                que_i.add(i+1);
                que_j.add(j);
                vis[i+1][j]=true;
                parent_i[i+1][j]=i;
                parent_j[i+1][j]=j;
            }
            if(j!=0 && !vis[i][j-1]) {
                que_i.add(i);
                que_j.add(j-1);
                vis[i][j-1]=true;
                parent_i[i][j-1]=i;
                parent_j[i][j-1]=j;
            }
            if(j!=m-1 && !vis[i][j+1]) {
                que_i.add(i);
                que_j.add(j+1);
                vis[i][j+1]=true;
                parent_i[i][j+1]=i;
                parent_j[i][j+1]=j;
            }
            que_i.poll();
            que_j.poll();
        }
        System.out.println();
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                System.out.print(parent_i[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                System.out.print(parent_j[i][j]+" ");
            }
            System.out.println();
        }
        StringBuilder path=new StringBuilder("");
        int i=target_i,j=target_j;
        while(parent_i[i][j]!=-1 && parent_j[i][j]!=-1) {
            if(parent_i[i][j]==i-1) {
                i--;
                path.append('D');
            }
            else if(parent_i[i][j]==i+1) {
                i++;
                path.append('U');
            }
            else if(parent_j[i][j]==j-1) {
                j--;
                path.append('R');
            }
            else if(parent_i[i][j]==j+1) {
                j++;
                path.append('L');
            }
        }
        path.reverse();
        return path;
    }
}
