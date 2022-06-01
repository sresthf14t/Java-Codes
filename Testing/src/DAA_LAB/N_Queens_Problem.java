/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAA_LAB;

/**
 *
 * @author Srest
 */
import java.util.*;
import java.io.*;
public class N_Queens_Problem {
    static int n;
    public static void main(String args[]) {
        n=8;
        System.out.println(solve(0,new boolean[n][n]));
    }
    public static long solve(int indx,boolean vis[][]) {
        if(indx==n) {
            return 1;
        }
        long ans=0;
        for(int i=0;i<n;i++) {
            if(!vis[indx][i]) {
                vis[indx][i]=true;
                ans+=solve(indx+1,mark(vis,indx,i));
                vis[indx][i]=false;
            }
        }
        return ans;
    }
    public static boolean[][] mark(boolean vis1[][],int x,int y) {
        boolean vis[][]=new boolean[n][n];
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                vis[i][j]=vis1[i][j];
            }
        }
        for(int i=0;i<n;i++) {
            vis[i][y]=true;
            vis[x][i]=true;
        }
        int i=x,j=y;
        while(i>=0 && j>=0) {
            vis[i][j]=true;
            i--;
            j--;
        }
        i=x;
        j=y;
        while(i<n && j<n) {
            vis[i][j]=true;
            i++;
            j++;
        }
        i=x;
        j=y;
        while(i>=0 && j<n) {
            vis[i][j]=true;
            i--;
            j++;
        }
        i=x;
        j=y;
        while(i<n && j>=0) {
            vis[i][j]=true;
            i++;
            j--;
        }
        return vis;
    }
}
