
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DP;

/**
 *
 * @author User
 */
/*
given a 2D grid find a path from top left to bottom right with the maximum 
sum only allowed moves are right and down
*/
import java.util.*;
import java.io.*;
public class Paths_in_a_grid {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int arr[][]=new int[n][n];
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                arr[i][j]=input.nextInt();
            }
        }
        System.out.println(max_path_sum(arr,n));
    }
    public static int max_path_sum(int arr[][],int n) {
        int path_sum[][]=new int[n][n];
        path_sum[0][0]=arr[0][0];
        for(int i=1;i<n;i++) {
            path_sum[0][i]=path_sum[0][i-1]+arr[0][i];
            path_sum[i][0]=path_sum[i-1][0]+arr[i][0];
        }
        for(int i=1;i<n;i++) {
            for(int j=1;j<n;j++) {
                path_sum[i][j]=arr[i][j]+Math.max(path_sum[i-1][j],path_sum[i][j-1]);
            }
        }
        return path_sum[n-1][n-1];
    }
}
