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
public class E_Generate_a_String {
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        long x=input.nextLong();
        long y=input.nextLong();
        System.out.println(solve(n,x,y));
    }
    static long solve(int n,long x,long y) {
        long dp[][]=new long[3][n+2];   //0->add    1->multiply     2->subtract
        for(int i=1;i<n+2;i++) {
            dp[0][i]=dp[1][i]=dp[2][i]=Long.MAX_VALUE-1;
            if(i%2==0) {
                dp[0][i]=Math.min(dp[0][i-1],dp[1][i-1])+x;
                dp[1][i]=Math.min(Math.min(dp[0][i/2],dp[1][i/2]),dp[2][i/2])+y;
            }
            else {
                dp[0][i]=Math.min(dp[0][i-1],dp[1][i-1])+x;
            }
            dp[2][i-1]=Math.min(dp[0][i], dp[1][i])+x;
        }
        return Math.min(Math.min(dp[0][n],dp[1][n]),dp[2][n]);
    }
}
