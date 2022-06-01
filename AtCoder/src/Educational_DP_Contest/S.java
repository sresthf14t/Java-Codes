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
public class S {
    static StringBuilder k;
    static int d;
    static int dp[][],mod=1000000007;
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        k=new StringBuilder(input.next());
        d=input.nextInt();
        dp=new int[10001][10001];
        for(int i=0;i<dp.length;i++) {
            for(int j=0;j<dp[0].length;j++) {
                dp[i][j]=-1;
            }
        }
        System.out.println(solve(new StringBuilder(""),0));
    }
    public static int solve(StringBuilder num,int sum) {
        if(num.length()==k.length()) {
            if(compare(num) && sum%d==0) {
//                System.out.println(num);
                return 1;
            }
            return 0;
        }
        if(dp[num.length()][sum]!=-1) {
            return dp[num.length()][sum];
        }
        int ways=0;
        if(sum!=0 && sum%d==0) {
//            System.out.println(num);
            ways++;
        }
        for(int i=0;i<10;i++) {
            if(num.length()==0 && i==0) {
                continue;
            }
            num.append(i);
            sum+=i;
            ways+=solve(num,sum);
            ways%=mod;
            num.deleteCharAt(num.length()-1);
            sum-=i;
        }
        dp[num.length()][sum]=ways;
        return dp[num.length()][sum];
    }
    public static boolean compare(StringBuilder num) {
        for(int i=0;i<num.length();i++) {
            if(num.charAt(i)>k.charAt(i)) {
                return false;
            }
            else if(num.charAt(i)<k.charAt(i)) {
                return true;
            }
        }
        return true;
    }
}
