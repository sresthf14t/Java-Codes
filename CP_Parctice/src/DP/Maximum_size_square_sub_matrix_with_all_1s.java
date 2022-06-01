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
Given a binary matrix, find out the maximum size square sub-matrix with all 1s.
*/
import java.util.*;
import java.io.*;
public class Maximum_size_square_sub_matrix_with_all_1s {
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
        System.out.println(solve(arr,n,m));
    }
    
    public static int solve(int[][] arr,int n,int m) {
        int[][] mrr=new int[n][m];
        for(int i=0;i<n;i++) {
            if(arr[i][0]==1) {
                mrr[i][0]=1;
            }
        }
        for(int i=0;i<m;i++) {
            if(arr[0][i]==1) {
                mrr[0][i]=1;
            }
        }
        for(int i=1;i<n;i++) {
            for(int j=1;j<m;j++) {
                if(arr[i][j]==1) {
                    mrr[i][j]=Math.min(mrr[i-1][j],Math.min(mrr[i][j-1],mrr[i-1][j-1]))+1;
                }
            }
        }
        int max=0;
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                max=Math.max(max, mrr[i][j]);
            }
         }
        
//        for(int i=0;i<n;i++) {
//            for(int j=0;j<m;j++) {
//                System.out.print(arr[i][j]+" ");
//            }
//            System.out.println();
//         }
//        System.out.println();
//        for(int i=0;i<n;i++) {
//            for(int j=0;j<m;j++) {
//                System.out.print(mrr[i][j]+" ");
//            }
//            System.out.println();
//         }
        return max;
    }
}
