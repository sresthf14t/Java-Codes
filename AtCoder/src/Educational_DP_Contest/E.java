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
public class E {
    static int n;
    static int capacity;
    static long wei[];
    static long val[];
    static HashMap<Long,Long> dp[]; //dp[n][capacity];
    public static void main(String args[]) {
        Scanner input=new Scanner(System.in);
        n=input.nextInt();
        capacity=input.nextInt();
        wei=new long[n];
        val=new long[n];
        dp=new HashMap[n];
        for(int i=0;i<n;i++) {
            dp[i]=new HashMap<>();
            wei[i]=input.nextInt();
            val[i]=input.nextInt();
        }
        System.out.println(solve(n-1,capacity));
    }
    public static long solve(int n,long cap) {
        if(n==-1 || cap==0) {
            return 0;
        }
        if(!dp[n].containsKey(cap)) {
            long tmp=Integer.MIN_VALUE;
            if(cap>=wei[n]) {
                tmp=Math.max(tmp, solve(n-1,cap-wei[n])+val[n]);
            }
            tmp=Math.max(tmp, solve(n-1,cap));
            dp[n].put(cap, tmp);
        }
        return dp[n].get(cap);
    }
}
