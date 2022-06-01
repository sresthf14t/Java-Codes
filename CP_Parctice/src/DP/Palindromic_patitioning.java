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
Given a string, a partitioning of the string is a palindrome partitioning if 
every sub-string of the partition is a palindrome. For example, 
“aba|b|bbabb|a|b|aba” is a palindrome partitioning of “ababbbabbababa”.
Determine the fewest cuts needed for palindrome partitioning of a given string.
For example, minimum 3 cuts are needed for “ababbbabbababa”. The three cuts are
“a|babbbab|b|ababa”.
Complexity->O(n^3)
*/
import java.util.*;
import java.io.*;
public class Palindromic_patitioning {
    public static void main(String arsg[]) {
        Scanner input=new Scanner(System.in);
        String str=input.next();
        System.out.println(find_min_partitions(str,str.length()));
    }
    public static int find_min_partitions(String str,int n) {
        boolean is_pal[][]=new boolean[n][n];
        int min_cut[][]=new int[n][n];
        for(int i=0;i<n;i++) {
            is_pal[i][i]=true;
            min_cut[i][i]=0;
        }
        for(int dist=1;dist<n;dist++) {
            for(int i=0,j=i+dist;i<n && j<n;i++,j++) {
                if(dist==1) {
                    is_pal[i][j]=(str.charAt(i)==str.charAt(j));
                }
                else {
                    is_pal[i][j]=(str.charAt(i)==str.charAt(j)) && is_pal[i+1][j-1];
                }
                if(is_pal[i][j]) {
                    min_cut[i][j]=0;
                }
                else {
                    min_cut[i][j]=Integer.MAX_VALUE;
                    for(int k=i;k<j;k++) {
                        min_cut[i][j]=Math.min(min_cut[i][j],min_cut[i][k]+min_cut[k+1][j]+1);
                    }
                }
            }
        }
        return min_cut[0][n-1];
    }
}
