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
Given an array A[ ] denoting the time taken to complete N tasks, 
determine the minimum amount of time required to finish the tasks 
considering that you can skip any task, but skipping two consecutive 
tasks is forbidden.
*/
import java.util.*;
import java.io.*;
public class Skip_the_work {
    static int dp[][];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int test=input.nextInt();
        for(int t=1;t<=test;t++) {
            int n=input.nextInt();
            int arr[]=new int[n];
            for(int i=0;i<n;i++) {
                arr[i]=input.nextInt();
            }
            dp=new int[2][n+1];
            for(int i=0;i<dp.length;i++) {
                for(int j=0;j<dp[i].length;j++) {
                    dp[i][j]=-1;
                }
            }
            System.out.println(ways(n,true,0,arr));
        }
    }
    
    static int ways(int n, boolean last_task,int curr_task,int[] arr) {
        if(curr_task>=n) {
            return 0;
        }
        if(last_task) {
            if(dp[0][curr_task+1]==-1) {
                dp[0][curr_task+1]=ways(n,false,curr_task+1,arr);
            }
            if(dp[1][curr_task+1]==-1) {
                dp[1][curr_task+1]=ways(n,true,curr_task+1,arr);
            }
            return Math.min(dp[0][curr_task+1], dp[1][curr_task+1]+arr[curr_task]);
        }
        if(dp[1][curr_task+1]==-1) {
            dp[1][curr_task+1]=ways(n,true,curr_task+1,arr);
        }
        return dp[1][curr_task+1]+arr[curr_task];
    }
}
