/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Educational_DP_Contest;

/**
 *
 * @author User
 */
import java.util.*;
import java.io.*;
public class F {
    static String s1,s2;
    static int dp[][];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        s1=input.next();
        s2=input.next();
        lcs();
        System.out.println(find_lcs());
    }
    public static int lcs() {
        dp=new int[s1.length()+1][s2.length()+1];
        for(int i=0;i<=s1.length();i++) {
            for(int j=0;j<=s2.length();j++) {
                if(i==0 || j==0) {
                    dp[i][j]=0;
                }
                else if(s1.charAt(i-1)==s2.charAt(j-1)) {
                    dp[i][j]=1+dp[i-1][j-1];
                }
                else {
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }
    
    public static String find_lcs() {
        int i=s1.length(),j=s2.length();
        StringBuilder ans=new StringBuilder("");
        while(i!=0 && j!=0) {
            if(s1.charAt(i-1)==s2.charAt(j-1)) {
                ans.append(s1.charAt(i-1));
                i--;
                j--;
            }
            else {
                if(dp[i-1][j]>dp[i][j-1]) {
                    i--;
                }
                else {
                    j--;
                }
            }
        }
        ans.reverse();
        return ""+ans;
    }
}
