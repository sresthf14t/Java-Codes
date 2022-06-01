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
Suppose you have N eggs and you want to determine from which floor in a K-floor
building you can drop an egg such that it doesn't break. You have to determine 
the minimum number of attempts you need in order find the critical floor in the
worst case while using the best strategy.There are few rules given below. 

    An egg that survives a fall can be used again.
    A broken egg must be discarded.
    The effect of a fall is the same for all eggs.
    If the egg doesn't break at a certain floor, it will not break at any floor below.
    If the eggs breaks at a certain floor, it will break at any floor above.

*/

import java.util.*;
import java.io.*;
public class Egg_Dropping_Puzzle {
    static int dp[][];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int eggs=input.nextInt();
        int height=input.nextInt();
        dp=new int[height+1][eggs+1];
        for(int i=0;i<=height;i++) {
            for(int j=0;j<=eggs;j++) {
               dp[i][j]=-1; 
            }
        }
        System.out.println(min_ways(height,eggs));
    }
    public static int min_ways(int height,int eggs) {
        if(height==0 || height==1) {
            return height;
        }
        if(eggs==1) {
            return height;
        }
        if(dp[height][eggs]!=-1) {
            return dp[height][eggs];
        }
        int min=2000000000;
        for(int i=1;i<=height;i++) {
            if(dp[i-1][eggs-1]==-1) {
                dp[i-1][eggs-1]=min_ways(i-1,eggs-1);
            }
            if(dp[height-1][eggs]==-1) {
                dp[height-1][eggs]=min_ways(height-1,eggs);
            }
            min=Math.min(min,Math.max(dp[i-1][eggs-1],dp[height-i][eggs]));
        }
        dp[height][eggs]=min+1;
        return min+1;
    }
}
